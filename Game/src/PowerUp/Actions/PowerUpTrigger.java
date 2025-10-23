package src.PowerUp.Actions;

import src.GameObject.Paddle;
import src.PowerUp.Abstractions.BasePowerUp;
import src.PowerUp.PowerUpWithLevel;

public class PowerUpTrigger {
    public static void triggerPowerUp(Paddle player, PowerUpWithLevel powerUpWithLevel) {
         BasePowerUp powerUp = powerUpWithLevel.getPowerUp();
         int level = powerUpWithLevel.getLevel();
         System.out.println(level);

         powerUp.doEffect(player, level);
    }
}
