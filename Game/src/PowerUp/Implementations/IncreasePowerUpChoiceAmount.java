package src.PowerUp.Implementations;

import src.AssetManager.Sprites.SpriteEnum;
import src.Enum.PowerUpCategoryEnum;
import src.Enum.RarityEnum;
import src.GameObject.Paddle;
import src.PowerUp.Abstractions.NoScalingPowerUp;

public class IncreasePowerUpChoiceAmount extends NoScalingPowerUp {
    public IncreasePowerUpChoiceAmount() {
        super(
                "Add another power up choice",
                "Adds another power up choice on this screen.",
                PowerUpCategoryEnum.OTHER,
                RarityEnum.COMMON,
                2,
                SpriteEnum.CRATE
        );
    }

    @Override
    public void doEffect(Paddle player, int level) {
        player.setPowerUpChoiceAmount(player.getPowerUpChoiceAmount() + 1);
    }
}
