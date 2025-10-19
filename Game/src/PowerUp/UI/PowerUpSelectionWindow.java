package src.PowerUp.UI;

import src.GameObject.GameFrame;
import src.GameObject.GamePanel;
import src.GameObject.Paddle;
import src.PowerUp.Abstractions.BasePowerUp;
import src.PowerUp.Actions.PowerUpAdder;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PowerUpSelectionWindow extends JDialog {

    Paddle player;

    public PowerUpSelectionWindow(GameFrame gameFrame, List<BasePowerUp> powerUps, Paddle player) {
        super(gameFrame, "Choose Your Power-Up!", true);
        this.player = player;
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(1, powerUps.size(), 20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        powerUps.forEach(x -> mainPanel.add(new PowerUpPanel(this, x)));
        add(mainPanel);
    }

    public void selectPowerUp(BasePowerUp powerUp) {
        PowerUpAdder.addPowerUpToPlayer(player, powerUp);
        JOptionPane.showMessageDialog(this, "You have chosen: " + powerUp.getName());
        this.dispose();
    }

    public static void init(GameFrame gameFrame, List<BasePowerUp> powerUps, Paddle player) {
        SwingUtilities.invokeLater(
                () -> {
                    new PowerUpSelectionWindow(gameFrame, powerUps, player).setVisible(true
                    );
                }
        );
    }

}
