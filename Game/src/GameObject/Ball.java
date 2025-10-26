package src.GameObject;

import src.Utils.ColorUtils;

import java.awt.*;
import java.util.*;

public class Ball extends Rectangle{

	Random random;
	float xVelocity;
	float yVelocity;
	int initialSpeed = 2;

    private Color color = ColorUtils.getRandomBrightColor();
	
	Ball(int x, int y, int width, int height){
		super(x,y,width,height);
		random = new Random();
		int randomXDirection = random.nextInt(2);
		if(randomXDirection == 0)
			randomXDirection--;
		setXDirection(randomXDirection*initialSpeed);
		
		int randomYDirection = random.nextInt(2);
		if(randomYDirection == 0)
			randomYDirection--;
		setYDirection(randomYDirection*initialSpeed);
		
	}

    private Paddle lastHit;

    public Paddle getLastHit() {
        return lastHit;
    }

     public void setLastHit(Paddle player) {
        lastHit = player;
     }

    public void setXDirection(float randomXDirection) {
		xVelocity = randomXDirection;
	}
	public void setYDirection(float randomYDirection) {
		yVelocity = randomYDirection;
	}
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, height, width);
	}
}