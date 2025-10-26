package src.PowerUp.UI;

import src.Enum.RarityEnum;
import src.PowerUp.Abstractions.BasePowerUp;
import src.PowerUp.PowerUpWithLevel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

public class PowerUpPanel extends JPanel {
    private BasePowerUp powerUp;
    private ArrayList<PowerUpWithLevel> currentPowerUps;
    private GridBagConstraints gbc;
    //TODO: maybe display current level as well

    public BasePowerUp getPowerUp() {
        return powerUp;
    }

    private GridBagConstraints setLayout(Color maincolor) {
        setBorder(BorderFactory.createLineBorder(maincolor));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        setBackground(Color.gray);

        return gbc;
    }

    private void setCategory(GridBagConstraints gridBagConstraints) {
        JLabel categoryLabel = new JLabel(String.valueOf(powerUp.getPowerUpCategory()));
        add(categoryLabel, gridBagConstraints);
    }

    private void trySetIcon(GridBagConstraints gridBagConstraints, String string) {
        try {
            File image = new File(string);

            if (!image.exists()) {
                throw new Exception("Image file does not exist");
            }

            JLabel iconLabel = new JLabel(new ImageIcon(string));
            System.out.println(iconLabel.getIcon());
            add(iconLabel, gridBagConstraints);
        }
        catch (NullPointerException exception) {
            // TODO: implement placeholder
            System.out.println("Error: image not loaded correctly for item:" + powerUp.getName());
            System.out.println("Error: " + exception);
            return;
        }
        catch (Exception exception) {
            System.out.println("Error trying to open image:" + powerUp.getName());
            System.out.println("Error: " + exception);
        }

    }

    public void setLevel(int level) {
        setMaxLevel(gbc, level);
        revalidate();
        repaint();
    }

    private void setName(GridBagConstraints gridBagConstraints, Color mainColor) {
        JLabel nameLabel = new JLabel(powerUp.getName());
        nameLabel.setForeground(mainColor);
        add(nameLabel, gridBagConstraints);
    }

    private void setDescription(GridBagConstraints gridBagConstraints) {
        JTextArea descriptionLabel = new JTextArea(powerUp.getDescription());

        descriptionLabel.setWrapStyleWord(true);
        descriptionLabel.setLineWrap(true);
        descriptionLabel.setOpaque(false);
        descriptionLabel.setEditable(false);
        descriptionLabel.setFocusable(false);
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        add(descriptionLabel, gridBagConstraints);
    }

    private void setIconIfExists(GridBagConstraints gridBagConstraints) {
        Optional<String> powerUpImage = powerUp.getImagePath();
        if (powerUpImage.isEmpty()) {
            System.out.println("Error: image not loaded correctly for item:" + powerUp.getName());
            return;
        }

        trySetIcon(gridBagConstraints, powerUpImage.get());
    }

    private void setRarity(GridBagConstraints gridBagConstraints, Color mainColor) {
        RarityEnum rarity = powerUp.getRarityEnum();
        JLabel rarityLabel = new JLabel(rarity.toString());
        rarityLabel.setForeground(mainColor);
        add(rarityLabel, gridBagConstraints);
    }

    private int getCurrentLevel() {
        PowerUpWithLevel powerUpWithLevel = currentPowerUps.stream()
                .filter(x -> x.getPowerUp().getName().equals(powerUp.getName()))
                .findFirst().orElse(null);

        if (powerUpWithLevel == null) {
            return 0;
        }

        return powerUpWithLevel.getLevel();
    }

    private void setMaxLevel(GridBagConstraints gridBagConstraints, int currentLevel) {
        String message = String.format("Level: %,d / %,d", currentLevel, powerUp.getMaximumLevel());
        JLabel maxLevelLabel = new JLabel(message);
        add(maxLevelLabel, gridBagConstraints);
    }

    public void createPowerUpPanel(PowerUpSelectionWindow selectionFrame) {
        Color mainColor = powerUp.getRarityEnum().getColor();
        System.out.println(mainColor);
        GridBagConstraints gbc = setLayout(mainColor);
        this.gbc = gbc;
        setName(gbc, mainColor);
        setIconIfExists(gbc);
        setDescription(gbc);

        gbc.weighty = 1.0;
        add(Box.createVerticalGlue(), gbc);
        gbc.weighty = 0;

        setRarity(gbc, mainColor);
        setCategory(gbc);
        setMaxLevel(gbc, getCurrentLevel());
        createButton(gbc, mainColor, selectionFrame);
    }

    private void buttonAction(PowerUpSelectionWindow selectionFrame) {
        selectionFrame.selectPowerUp(powerUp);
    }

    private void createButton(GridBagConstraints gridBagConstraints, Color mainColor, PowerUpSelectionWindow selectionFrame) {
        JButton selectButton = new JButton("Select");
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        selectButton.setBorder(new LineBorder(mainColor));
        selectButton.addActionListener(e -> {
            buttonAction(selectionFrame);
        });
        add(selectButton, gridBagConstraints);
    }

    public PowerUpPanel(PowerUpSelectionWindow selectionFrame, BasePowerUp powerUp, ArrayList<PowerUpWithLevel> currentPowerUps) {
        this.powerUp = powerUp;
        this.currentPowerUps = currentPowerUps;

        this.createPowerUpPanel(selectionFrame);
    }
}
