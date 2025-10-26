package src.PowerUp.Actions;

import src.GameObject.Paddle;
import src.PowerUp.Abstractions.BasePowerUp;
import src.PowerUp.PowerUpWithLevel;

import java.util.Optional;

public class PowerUpFinder {
    public static boolean hasPlayerPowerUp(Paddle player, BasePowerUp powerUp) {
        return player.getPowerUps().stream()
                .anyMatch(x -> x.getPowerUp().getName()
                        .equals(powerUp.getName()));
    }

    public static boolean hasPlayerPowerUpByName(Paddle player, String powerUpName) {
        return player.getPowerUps().stream()
                .anyMatch(x -> x.getPowerUp().getName()
                        .equals(powerUpName));
    }

    public static Optional<PowerUpWithLevel> findPowerUpIfExists(Paddle player, BasePowerUp powerUp) {
        return player.getPowerUps().stream().filter(x -> x.getPowerUp().getName().equals(powerUp.getName())).findFirst();
    }

    public static boolean checkForBrickInvincibility(Paddle player) {
        return PowerUpFinder.hasPlayerPowerUpByName(player, "Brick invincibility");
    }
}
