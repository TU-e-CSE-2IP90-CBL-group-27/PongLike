package src.GameObject;

import src.GameValuesManager.MainValues;
import src.Utils.ColorUtils;

import java.awt.*;

public class Obstacle extends Rectangle {

    private final Color color;

    public Obstacle(int x, int y) {
        super(x, y, MainValues.getObstacleWidth(), MainValues.getObstacleHeight());
        this.color = ColorUtils.getRandomBrightColor();
    }

    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRect(x, y, width, height);
    }
}
