package src.PowerUp.Implementations;

import src.AssetManager.Sprites.SpriteEnum;
import src.Enum.PowerUpCategoryEnum;
import src.Enum.RarityEnum;
import src.GameObject.Paddle;
import src.PowerUp.Abstractions.NoScalingPowerUp;

public class IncreasePowerUpSelectionRepeat extends NoScalingPowerUp {
    public IncreasePowerUpSelectionRepeat() {
        super(
                "Repeat power up selection",
                "Choose more than one power up",
                PowerUpCategoryEnum.OTHER,
                RarityEnum.COMMON,
                2,
                SpriteEnum.BALL_BOMB
        );
    }


    @Override
    public void doEffect(Paddle player, int level ) {
        player.setPowerUpSelectionRepeat(player.getPowerUpSelectionRepeat() + 1);
    }

}
