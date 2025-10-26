package src.PowerUp.Implementations;

import src.AssetManager.Sprites.SpriteEnum;
import src.Enum.PowerUpCategoryEnum;
import src.Enum.RarityEnum;
import src.GameObject.Paddle;
import src.PowerUp.Abstractions.NoScalingPowerUp;
import src.PowerUp.Actions.GoalActions;

public class SwapScores extends NoScalingPowerUp {
    public SwapScores() {
        super(
                "Swap the goal amounts",
                "Swap the current goal amounts.",
                PowerUpCategoryEnum.ATTACK,
                RarityEnum.LEGENDARY,
                1,
                SpriteEnum.SKULL
        );
    }

    @Override
    public void doEffect(Paddle paddle, int level) {
        GoalActions.swapScores();
    }
}
