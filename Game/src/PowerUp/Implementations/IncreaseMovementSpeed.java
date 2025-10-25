package src.PowerUp.Implementations;

import src.AssetManager.Sprites.SpriteEnum;
import src.Enum.OperationType;
import src.Enum.PowerUpCategoryEnum;
import src.Enum.RarityEnum;
import src.GameObject.Paddle;
import src.PowerUp.Abstractions.SetScalingPowerUp;

public class IncreaseMovementSpeed extends SetScalingPowerUp {
    public IncreaseMovementSpeed() {
        super(
                "Faster paddle",
                "Makes your paddle faster. While useful for faster defense make s the control more difficult as well",
                PowerUpCategoryEnum.MOBILITY,
                RarityEnum.COMMON,
                SpriteEnum.SPEED_KATANA,
                new float[]{1, 2.5f, 5f, 6.5f, 8},
                OperationType.ADDITION
        );
    }

    @Override
    public void doEffect(Paddle player, int level) {
        float movementSpeed = calculateValue(player.getSpeed(), level);
        player.setSpeed(movementSpeed);
    }

}
