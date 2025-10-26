package src.PowerUp.Implementations;

import src.AssetManager.Sprites.SpriteEnum;
import src.Enum.PowerUpCategoryEnum;
import src.Enum.RarityEnum;
import src.GameObject.Paddle;
import src.GameValuesManager.MainValues;
import src.PowerUp.Abstractions.EquationScalingPowerUp;

public class IncreaseObstacleSpawnRate extends EquationScalingPowerUp {
    public IncreaseObstacleSpawnRate() {
        super(
                "Faster obstacles",
                "Makes the obstacles more often. Good for chaos",
                PowerUpCategoryEnum.BRICK,
                RarityEnum.RARE,
                3,
                SpriteEnum.FIRE_WALL
        );
    }

    @Override
    public float equation(float value, int level) {
        return value / 2;
        //TODO: make this nicer
    }

    @Override
    public void doEffect(Paddle player, int level) {
        MainValues.setObstacleIntervalNanos((long) equation((float) MainValues.getObstacleIntervalNanos(), level));
        System.out.println(MainValues.getObstacleIntervalNanos());
    }
}
