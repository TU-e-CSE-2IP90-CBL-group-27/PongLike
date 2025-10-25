package src.PowerUp.UI;

import src.AssetManager.Sprites.SpriteEnum;
import src.PowerUp.PowerUpWithLevel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Optional;

public class PowerUpStatus extends JComponent {
    private PowerUpWithLevel powerUpWithLevel;
    private ImageIcon image;
    private static final int PADDING = 5;

    private ImageIcon getPowerUpIcon(){
        try {
            Optional<String> path = powerUpWithLevel.getPowerUp().getImagePath();

            if (path.isEmpty()) {
                throw new Exception("No image");
            }

            File testFile = new File(path.get());

            if (!testFile.exists()) {
                throw new Exception("Invalid path");
            }

            ImageIcon image = new ImageIcon(path.get());
            System.out.println("Loaded image");

            return image;
        }

        catch (Exception exception) {
            System.out.println("There was an error in loading the image " + exception.getMessage());
            return new ImageIcon(SpriteEnum.LARGE_BRICK.getPath()); // TODO: remove temporary placeholder
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        image.paintIcon(this, g, 0, 0);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));

        String levelText = "x" + powerUpWithLevel.getLevel();
        FontMetrics fm = g2d.getFontMetrics();
        int textX = image.getIconWidth() + PADDING;
        int textY = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();

        g2d.drawString(levelText, textX, textY);
        g2d.dispose();
    }

    public PowerUpStatus(PowerUpWithLevel powerUpWithLevel) {
        this.powerUpWithLevel = powerUpWithLevel;
        this.image = getPowerUpIcon();

        setOpaque(false);
        int textWidth = 40;
        setPreferredSize(new Dimension(image.getIconWidth() + PADDING + textWidth, image.getIconHeight()));
    }
}
