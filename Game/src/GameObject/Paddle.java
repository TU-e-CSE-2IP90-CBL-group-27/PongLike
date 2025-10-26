package src.GameObject;

import src.PowerUp.Abstractions.BasePowerUp;
import src.PowerUp.Actions.PowerUpTrigger;
import src.PowerUp.PowerUpWithLevel;
import src.PowerUp.UI.PowerUpPanel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Paddle extends Rectangle{
    // TODO: obviously make the player a child of paddle
    // TODO: consider using ints instead of floats for movement
	int id;
	private float yVelocity;
	private float speed = 10;

    private int powerUpSelectionRepeat = 1;

    public float getSpeed() {
        return speed;
    }

    public int getPowerUpSelectionRepeat() {
        return powerUpSelectionRepeat;
    }

    public void setPowerUpSelectionRepeat(int powerUpSelectionRepeat) {
        this.powerUpSelectionRepeat = powerUpSelectionRepeat;
    }

    public void setHeight(float height) {
        this.height = (int) height;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    private int powerUpChoiceAmount = 3;

    private float hitForce = 1f;

    public float getHitForce() {
        return hitForce;
    }

    public void setHitForce(float hitForce) {
        this.hitForce = hitForce;
    }

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
        PowerUpWithLevel power = new PowerUpWithLevel(powerUp);
        powerUpWithLevels.add(power);
        PowerUpTrigger.triggerPowerUp(this, power);
    }

    public void incrementPowerUp(PowerUpWithLevel powerUpWithLevel) {
        powerUpWithLevel.incrementLevel();
        PowerUpTrigger.triggerPowerUp(this, powerUpWithLevel);
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
	public void setYDirection(float yDirection) {
		yVelocity = yDirection;
	}
	public void move() {
		y = y + Math.round(yVelocity);
	}
	public void draw(Graphics g) {
		if(id==1)
			g.setColor(Color.blue);
		else
			g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}
}