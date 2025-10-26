package src.PowerUp.Implementations;

import src.AssetManager.Sprites.SpriteEnum;
import src.Enum.PowerUpCategoryEnum;
import src.Enum.RarityEnum;
import src.GameObject.Paddle;
import src.PowerUp.Abstractions.NoScalingPowerUp;
import src.PowerUp.Actions.GoalActions;

public class DecreaseGoalAmount extends NoScalingPowerUp {
    public DecreaseGoalAmount() {
        super(
                "Decrease goal amount",
                "Decrease current goals against by one.",
                PowerUpCategoryEnum.DEFENSE,
                RarityEnum.RARE,
                10,
                SpriteEnum.RED
        );
    }

    @Override
    public void doEffect(Paddle paddle, int level) {
        GoalActions.decreaseGoalUse(paddle);
    }
}
