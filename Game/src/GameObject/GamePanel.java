package src.GameObject;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {

    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;

    // OBSTACLE CONFIG
    static final long OBSTACLE_INTERVAL_NANOS = 10_000_000_000L; // 10 seconds
    static final int PADDLE_SAFE_MARGIN = 150; // distance from paddles
    static final int MAX_SPAWN_ATTEMPTS = 30;

    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;

    // Obstacle + timer
    Obstacle obstacle;
    long lastObstacleSpawnNs;

    // TIMER: start time and formatting
    long gameStartNs;
    Font timerFont = new Font("Consolas", Font.BOLD, 32);

    GamePanel() {
        random = new Random();

        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);

        // Obstacle init
        obstacle = null;
        lastObstacleSpawnNs = System.nanoTime();

        // start timer at panel creation
        gameStartNs = System.nanoTime();

        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall() {
        ball = new Ball(
            (GAME_WIDTH/2)-(BALL_DIAMETER/2),
            random.nextInt(GAME_HEIGHT - BALL_DIAMETER),
            BALL_DIAMETER, BALL_DIAMETER
        );
    }

    public void newPaddles() {
        paddle1 = new Paddle(0, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }

    // spawn obstacle (or respawn) obstacle at a safe random location
    private void newObstacle() {
        int minX = PADDLE_WIDTH + PADDLE_SAFE_MARGIN;
        int maxX = GAME_WIDTH - PADDLE_WIDTH - PADDLE_SAFE_MARGIN - Obstacle.WIDTH;
        if (maxX <= minX) return;

        int maxY = GAME_HEIGHT - Obstacle.HEIGHT;
        if (maxY < 0) return;

        for (int attempt = 0; attempt < MAX_SPAWN_ATTEMPTS; attempt++) {
            int x = minX + random.nextInt((maxX - minX) + 1);
            int y = random.nextInt(Math.max(1, maxY + 1));

            Obstacle candidate = new Obstacle(x, y);
            if (!candidate.intersects(ball)) {
                obstacle = candidate;
                return;
            }
        }
        obstacle = new Obstacle(
            minX + (maxX - minX) / 2,
            Math.max(0, Math.min(GAME_HEIGHT - Obstacle.HEIGHT, (GAME_HEIGHT - Obstacle.HEIGHT) / 2))
        );
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);

        if (obstacle != null) {
            obstacle.draw(g);
        }

        ball.draw(g);
        score.draw(g);

        // Draw timer centered at top (min:sec format)
        long elapsedNs = System.nanoTime() - gameStartNs;
        long elapsedSec = elapsedNs / 1_000_000_000L;
        String timeText = formatTime(elapsedSec);
        g.setColor(Color.WHITE);
        g.setFont(timerFont);
        int sw = g.getFontMetrics().stringWidth(timeText);
        g.drawString(timeText, (GAME_WIDTH - sw) / 2, 40);

        Toolkit.getDefaultToolkit().sync();
    }

    private String formatTime(long seconds) {
        long m = seconds / 60;
        long s = seconds % 60;
        return String.format("%02d:%02d", m, s);
    }

    public void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
        
    }

    public void checkCollision() {
        // top/bottom
        if (ball.y <= 0) {
            ball.setYDirection(-ball.yVelocity);
        }
        if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }

        // paddles
        if (ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if (ball.yVelocity > 0) ball.yVelocity++;
            else ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if (ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if (ball.yVelocity > 0) ball.yVelocity++;
            else ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        // bounce off obstacle
        if (obstacle != null && ball.intersects(obstacle)) {
            Rectangle inter = ball.intersection(obstacle);
            if (inter.width < inter.height) {
                ball.setXDirection(-ball.xVelocity);
            } else {
                ball.setYDirection(-ball.yVelocity);
            }
        }

        // keep paddles on screen
        if (paddle1.y <= 0) paddle1.y = 0;
        if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        if (paddle2.y <= 0) paddle2.y = 0;
        if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;

        // scoring
        if (ball.x <= 0) {
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("Player 2: " + score.player2);
        }
        if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("Player 1: " + score.player1);
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        while (true) {
            long now = System.nanoTime();

            // respawn obstacle every interval
            if (now - lastObstacleSpawnNs >= OBSTACLE_INTERVAL_NANOS) {
                newObstacle();
                lastObstacleSpawnNs = now;
            }

            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    public class AL extends KeyAdapter {
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
