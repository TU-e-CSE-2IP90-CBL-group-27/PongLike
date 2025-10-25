package src.PowerUp.Implementations;

import src.AssetManager.Sprites.SpriteEnum;
import src.Enum.OperationType;
import src.Enum.PowerUpCategoryEnum;
import src.Enum.RarityEnum;
import src.GameObject.Paddle;
import src.PowerUp.Abstractions.SetScalingPowerUp;

public class IncreaseSize extends SetScalingPowerUp {
    public IncreaseSize() {
        super(
                "Bigger paddle",
                "Makes your paddle larger. Very useful for defense but limits attack angles a bit",
                PowerUpCategoryEnum.DEFENSE,
                RarityEnum.COMMON,
                SpriteEnum.LARGE_BRICK,
                new float[]{10, 25, 50f, 65f, 80},
                OperationType.ADDITION

        );
    }

    @Override
    public void doEffect(Paddle player, int level) {
        float height = calculateValue((float) player.getHeight(), level);
        player.setHeight(height);
    }
}
