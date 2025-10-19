package src.PowerUp.UI;

import src.Enum.RarityEnum;
import src.PowerUp.Abstractions.BasePowerUp;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.util.Optional;

public class PowerUpPanel extends JPanel {
    private BasePowerUp powerUp;
    //TODO: maybe display current level as well

    private GridBagConstraints setLayout(Color maincolor) {
        setBorder(BorderFactory.createLineBorder(maincolor));
        setLayout(new GridBagLayout());

        //Sets the main layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        return gbc;
    }

    private void trySetIcon(GridBagConstraints gridBagConstraints, String string) {
        try {
            JLabel iconLabel = new JLabel(new ImageIcon(string));
            add(iconLabel, gridBagConstraints);
        }
        catch (NullPointerException exception) {
            // TODO: implement placeholder
            System.out.println("Error: image not loaded correctly for item:" + powerUp.getName());
            System.out.println("Error: " + exception);
            return;
        }

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

    private void setMaxLevel(GridBagConstraints gridBagConstraints) {
        JLabel maxLevelLabel = new JLabel("Max level:" + powerUp.getMaximumLevel());
        add(maxLevelLabel, gridBagConstraints);
    }

    public void createPowerUpPanel(PowerUpSelectionWindow selectionFrame) {
        Color mainColor = powerUp.getRarityEnum().getColor();
        GridBagConstraints gbc = setLayout(mainColor);
        setIconIfExists(gbc);
        setName(gbc, mainColor);
        setDescription(gbc);

        gbc.weighty = 1.0;
        add(Box.createVerticalGlue(), gbc);
        gbc.weighty = 0;

        setRarity(gbc, mainColor);
        setMaxLevel(gbc);
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

    public PowerUpPanel(PowerUpSelectionWindow selectionFrame, BasePowerUp powerUp) {
        this.powerUp = powerUp;
        this.createPowerUpPanel(selectionFrame);
    }
}
