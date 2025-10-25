package src.GameObject;

import src.AssetManager.Sound.SoundEffectEnum;
import src.AssetManager.Sound.SoundManager;
import src.PowerUp.Actions.PowerUpAdder;
import src.PowerUp.UI.PowerUpInfo;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;

    Thread gameThread;
    Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;

    private boolean isPaused = false;

    public boolean getIsPaused() {
        return isPaused;
    }

    public void setIsPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

    public void toggleIsPaused() {
        isPaused = !isPaused;
    }

    private final Point startingPointFirstPlayer = new Point(0, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2));
    private final Point startingPointSecondPlayer = new Point(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2));

    private ObstacleManager obstacleManager;                  // (kept from previous step)
    private GameClock gameClock;

    public PowerUpInfo powerUpInfoPlayerOne;

    public PowerUpInfo powerUpInfoPlayerTwo;

    private GameFrame gameFrame;

    public GamePanel(GameFrame gameFrame){
        random = new Random();

        setLayout(null);
        newPaddles();
		newBall();

        // Obstacle init
        obstacleManager = new ObstacleManager(
                GAME_WIDTH, GAME_HEIGHT, PADDLE_WIDTH
        );

        // gameclock()
        gameClock = new GameClock();

        score = new Score(GAME_WIDTH,GAME_HEIGHT);

        this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);

        this.powerUpInfoPlayerOne = new PowerUpInfo();
        this.powerUpInfoPlayerTwo = new PowerUpInfo();
        add(powerUpInfoPlayerOne);
        add(powerUpInfoPlayerTwo);
        positionPowerUpInfo();
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void newBall() {
		ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
	}
	public void newPaddles() {
		paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
		paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
	}

    @Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);

        obstacleManager.draw(graphics);
        gameClock.draw(g, GAME_WIDTH);
        Toolkit.getDefaultToolkit().sync();

	}
	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	public void checkCollision() {
		
		if(ball.y <= 0) {
			ball.setYDirection(-ball.yVelocity);
		}
		if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
			ball.setYDirection(-ball.yVelocity);
		}

		if(ball.intersects(paddle1)) {
            SoundManager.playSound(SoundEffectEnum.PADDLE_HIT);
			ball.xVelocity = Math.abs(ball.xVelocity);

            float paddleForce = paddle1.getHitForce();
            ball.xVelocity += paddleForce; //TODO: consider removing to remove acceleration

            if(ball.yVelocity>0)
				ball.yVelocity += paddleForce; //TODO: consider removing to remove acceleration
			else
				ball.yVelocity -= paddleForce;

			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);

            // TODO: refactor this
		}

		if(ball.intersects(paddle2)) {
            SoundManager.playSound(SoundEffectEnum.PADDLE_HIT);
            ball.xVelocity = Math.abs(ball.xVelocity);

            float paddleForce = paddle2.getHitForce();
            ball.xVelocity += paddleForce; //TODO: consider removing to remove acceleration

            if(ball.yVelocity>0)
				ball.yVelocity += paddleForce; //TODO: consider removing to remove acceleration
			else
				ball.yVelocity -= paddleForce;

			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}

        obstacleManager.handleCollision(ball);

		//TODO: handle this being ignored on certain ability
		if(paddle1.y<=0)
			paddle1.y=0;
		if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
			paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
		if(paddle2.y<=0)
			paddle2.y=0;
		if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
			paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;

		if(ball.x <=0) {
			score.player2++;
            SoundManager.playSound(SoundEffectEnum.GOAL);
            PowerUpAdder.createSelectionUI(gameFrame,this, paddle1, powerUpInfoPlayerOne);
            paddle1.setLocation(startingPointFirstPlayer);
            paddle2.setLocation(startingPointSecondPlayer);
            newBall();
			System.out.println("Player 2: "+score.player2);
		}

        if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
			score.player1++;
            SoundManager.playSound(SoundEffectEnum.GOAL);
            PowerUpAdder.createSelectionUI(gameFrame,this, paddle2, powerUpInfoPlayerTwo);
            paddle1.setLocation(startingPointFirstPlayer);
            paddle2.setLocation(startingPointSecondPlayer);
            newBall();
			System.out.println("Player 1: "+score.player1);
		}
	}

    public void positionPowerUpInfo() {
        int margin = 20;

        Dimension p1Size = powerUpInfoPlayerOne.getPreferredSize();
        powerUpInfoPlayerOne.setBounds(margin, margin, p1Size.width, p1Size.height);

        Dimension p2Size = powerUpInfoPlayerTwo.getPreferredSize();
        powerUpInfoPlayerTwo.setBounds(getWidth() - p2Size.width - margin, margin, p2Size.width, p2Size.height);
    }

	public void run() {
		//game loop
		long lastTime = System.nanoTime();
		double amountOfTicks =60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
        //TODO: change something here for endgame resolution
		while(true) {
			long now = System.nanoTime();
			delta += (now -lastTime)/ns;
			lastTime = now;
            obstacleManager.update(ball);
			if(delta >=1) {
				checkCollision();
				repaint();
				delta--;
                if (isPaused) {
                    continue;
                }
                move();
			}
		}
	}
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}