package src.PowerUp.Implementations;

import src.AssetManager.Sprites.SpriteEnum;
import src.Enum.OperationType;
import src.Enum.PowerUpCategoryEnum;
import src.Enum.RarityEnum;
import src.GameObject.Paddle;
import src.GameValuesManager.MainValues;
import src.PowerUp.Abstractions.SetScalingPowerUp;

public class IncreaseBallDiameter extends SetScalingPowerUp {
    public IncreaseBallDiameter() {
        super(
                "Bigger ball",
                "Makes the ball bigger. Easier to defend but will also bounce off more",
                PowerUpCategoryEnum.OTHER,
                RarityEnum.COMMON,
                SpriteEnum.BALL_BOMB,
                new float[]{5, 10, 15, 25, 50},
                OperationType.ADDITION
        );
    }

    //TODO: fix bug where ball clips into goal sometimes;

    @Override
    public void doEffect(Paddle player, int level) {
        int currentDiameter = MainValues.getBallDiameter();
        MainValues.setBallDiameter((int)calculateValue((float) MainValues.getBallDiameter(), level));
    }
}
