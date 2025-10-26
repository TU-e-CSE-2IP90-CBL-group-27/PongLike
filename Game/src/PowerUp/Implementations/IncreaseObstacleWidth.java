package src.PowerUp.Implementations;

import src.AssetManager.Sprites.SpriteEnum;
import src.Enum.OperationType;
import src.Enum.PowerUpCategoryEnum;
import src.Enum.RarityEnum;
import src.GameObject.Paddle;
import src.GameValuesManager.MainValues;
import src.PowerUp.Abstractions.SetScalingPowerUp;

public class IncreaseObstacleWidth extends SetScalingPowerUp {
    public IncreaseObstacleWidth() {
        super(
                "Wider obstacle",
                "Make the obstacle wider. The unstoppable obstacle reigns supreme.",
                PowerUpCategoryEnum.BRICK,
                RarityEnum.COMMON,
                SpriteEnum.COLUMN,
                new float[]{10, 20, 30, 50, 80},
                OperationType.ADDITION
        );
    }


    @Override
    public void doEffect(Paddle player, int level) {
        int currentObstacleWidth = MainValues.getObstacleWidth();
        MainValues.setObstacleWidth((int)calculateValue((float) currentObstacleWidth, level));
    }
}
