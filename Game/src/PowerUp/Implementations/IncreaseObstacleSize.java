package src.PowerUp.Implementations;

import src.AssetManager.Sprites.SpriteEnum;
import src.Enum.OperationType;
import src.Enum.PowerUpCategoryEnum;
import src.Enum.RarityEnum;
import src.GameObject.Paddle;
import src.GameValuesManager.MainValues;
import src.PowerUp.Abstractions.SetScalingPowerUp;

public class IncreaseObstacleSize extends SetScalingPowerUp {
    public IncreaseObstacleSize() {
        super(
                "Bigger obstacle",
                "Make the obstacle bigger. More annoying to you but also your opponent",
                PowerUpCategoryEnum.BRICK,
                RarityEnum.COMMON,
                SpriteEnum.COLUMN,
                new float[]{10, 25, 50, 75, 150},
                OperationType.ADDITION
        );
    }


    @Override
    public void doEffect(Paddle player, int level) {
        int currentObstacleHeight = MainValues.getObstacleHeight();
        MainValues.setObstacleHeight((int)calculateValue((float) currentObstacleHeight, level));
    }
}
