package src.PowerUp.Implementations;

import src.AssetManager.Sprites.SpriteEnum;
import src.Enum.PowerUpCategoryEnum;
import src.Enum.RarityEnum;
import src.PowerUp.Abstractions.NoScalingPowerUp;

public class BecomeInvincibleToBricks extends NoScalingPowerUp {
    public BecomeInvincibleToBricks() {
        super(
                "Brick invincibility",
                "Your shots no longer get intercepted by the obstacles",
                PowerUpCategoryEnum.BRICK,
                RarityEnum.INSANE,
                1,
                SpriteEnum.OPEN
        );
    }
}
