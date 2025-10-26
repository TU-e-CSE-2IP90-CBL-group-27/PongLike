// Game/src/Menu/DeployMenu.java
package src.Menu;

import java.awt.*;

public class DeployMenu {
    public void draw(Graphics g, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        try {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            g2.setColor(Color.black);
            g2.fillRect(0, 0, width, height);

            // text
            g2.setComposite(AlphaComposite.SrcOver);
            g2.setColor(Color.white);

            String title = "PONG";
            String line1 = "Press 1: Two Players";
            String line2 = "Press 2: Play vs AI";
            String line3 = "Tip: P to pause  ·  ESC to return to menu";

            g2.setFont(new Font("SansSerif", Font.BOLD, 56));
            FontMetrics fm = g2.getFontMetrics();
            int y = (height / 2) - 60; //y position
            g2.drawString(title, (width - fm.stringWidth(title)) / 2, y);

            g2.setFont(new Font("SansSerif", Font.PLAIN, 28)); //font
            fm = g2.getFontMetrics();
            y += 60; 
            //draw strings
            g2.drawString(line1, (width - fm.stringWidth(line1)) / 2, y);
            y += 40; 
            g2.drawString(line2, (width - fm.stringWidth(line2)) / 2, y);
            y += 40; 
            g2.drawString(line3, (width - fm.stringWidth(line3)) / 2, y);
        } finally {
            g2.dispose();
        }
    }
}
