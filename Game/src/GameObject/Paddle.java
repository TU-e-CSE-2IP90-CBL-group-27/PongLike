package src.GameObject;

import src.PowerUp.Abstractions.BasePowerUp;
import src.PowerUp.PowerUpWithLevel;
import src.PowerUp.UI.PowerUpPanel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Paddle extends Rectangle{
    // TODO: obviously make the player a child of paddle
	int id;
	int yVelocity;
	int speed = 10;

    private int powerUpChoiceAmount = 3;

    public int getPowerUpChoiceAmount() {
        return this.powerUpChoiceAmount;
    }

    public void setPowerUpChoiceAmount(int amount) {
        this.powerUpChoiceAmount = amount;
    }

    public void increasePowerUpChoiceAmount() {
        this.powerUpChoiceAmount++;
    }

    private ArrayList<PowerUpWithLevel> powerUpWithLevels = new ArrayList<>();

    public ArrayList<PowerUpWithLevel> getPowerUps() {
        return powerUpWithLevels;
    }

    public void setPowerUpWithLevels(ArrayList<PowerUpWithLevel> powerUpWithLevels) {
        this.powerUpWithLevels = powerUpWithLevels;
    }

    public void addPowerUpWithLevel(BasePowerUp powerUp) {
        powerUpWithLevels.add(new PowerUpWithLevel(powerUp));
    }

    public void clearPowerUps() {
        powerUpWithLevels.clear();
    }
	
	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
		super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
		this.id=id;
	}
	
	public void keyPressed(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_W) {
				setYDirection(-speed);
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
				setYDirection(speed);
			}
			break;
		case 2:
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				setYDirection(-speed);
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				setYDirection(speed);
			}
			break;
		}
	}
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_W) {
				setYDirection(0);
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
				setYDirection(0);
			}
			break;
		case 2:
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				setYDirection(0);
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				setYDirection(0);
			}
			break;
		}
	}
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}
	public void move() {
		y= y + yVelocity;
	}
	public void draw(Graphics g) {
		if(id==1)
			g.setColor(Color.blue);
		else
			g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}
}