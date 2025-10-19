package src.PowerUp.Actions;

import src.GameObject.GameFrame;
import src.GameObject.GamePanel;
import src.GameObject.Paddle;
import src.PowerUp.Abstractions.BasePowerUp;
import src.PowerUp.Helper.PowerUpSelector;
import src.PowerUp.PowerUpWithLevel;
import src.PowerUp.UI.PowerUpSelectionWindow;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Optional;

public class PowerUpAdder {

    public static ArrayList<BasePowerUp> selectPotentialPowerUps(Paddle player) {
        return PowerUpSelector.selectPowerUps(player.getPowerUps(),
                player.getPowerUpChoiceAmount());
    }

    public static void createSelectionUI(GameFrame gameFrame, Paddle player) {
        ArrayList<BasePowerUp> powerUps = selectPotentialPowerUps(player);
        PowerUpSelectionWindow.init(
                gameFrame, powerUps, player
        );
    }

    public static void addPowerUpToPlayer(Paddle player, BasePowerUp powerUp
    ) {
        Optional<PowerUpWithLevel> originalPowerUp = PowerUpFinder.findPowerUpIfExists(player, powerUp);

        if (originalPowerUp.isPresent()) {
            originalPowerUp.get().incrementLevel();
            return;
        }

        player.addPowerUpWithLevel(powerUp);
    }
}
