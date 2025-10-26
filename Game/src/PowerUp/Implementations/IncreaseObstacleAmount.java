package src.PowerUp.Implementations;

import src.AssetManager.Sprites.SpriteEnum;
import src.Enum.OperationType;
import src.Enum.PowerUpCategoryEnum;
import src.Enum.RarityEnum;
import src.GameObject.Paddle;
import src.GameValuesManager.MainValues;
import src.PowerUp.Abstractions.EquationScalingPowerUp;
import src.PowerUp.Abstractions.NoScalingPowerUp;
import src.PowerUp.Abstractions.SetScalingPowerUp;

public class IncreaseObstacleAmount extends NoScalingPowerUp {
    public IncreaseObstacleAmount() {
        super(
                "Increase obstacle amount",
                "For ultimate chaos",
                PowerUpCategoryEnum.BRICK,
                RarityEnum.LEGENDARY,
                3,
                SpriteEnum.WALLS
        );
    }

    @Override
    public void doEffect(Paddle player, int level) {
        MainValues.incrementObstacleAmount();
    }
}
