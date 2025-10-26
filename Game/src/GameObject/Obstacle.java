package src.GameObject;

import src.GameValuesManager.MainValues;

import java.awt.*;

public class Obstacle extends Rectangle {
    public static final int WIDTH = 20;   // tweak

    public Obstacle(int x, int y) {
        super(x, y, WIDTH, MainValues.getObstacleHeight());
    }

    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y, width, MainValues.getObstacleHeight());
    }
}
