package src.GameObject;

import java.awt.*;

public class Obstacle extends Rectangle {
    public static final int WIDTH = 20;   // tweak
    public static final int HEIGHT = 150; // tweak

    public Obstacle(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }

    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y, width, height);
    }
}
