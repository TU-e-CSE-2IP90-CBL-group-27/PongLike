package src.GameObject;

import java.awt.*;

public class GameClock {
    private final long startNs = System.nanoTime();
    private final Font font = new Font("Consolas", Font.BOLD, 32);

    public long elapsedSeconds() {
        return (System.nanoTime() - startNs) / 1_000_000_000L;
    }

    public String formatted() {
        long seconds = elapsedSeconds();
        long m = seconds / 60;
        long s = seconds % 60;
        return String.format("%02d:%02d", m, s);
    }

    //Draws the timer centered at y=85
    public void draw(Graphics g, int screenWidth) {
        String timeText = formatted();
        g.setColor(Color.WHITE);
        g.setFont(font);
        int sw = g.getFontMetrics().stringWidth(timeText);
        g.drawString(timeText, (screenWidth - sw) / 2, 85);
    }
}
