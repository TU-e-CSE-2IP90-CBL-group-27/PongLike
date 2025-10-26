package src.PowerUp.Abstractions;

import src.AssetManager.Sprites.SpriteEnum;
import src.Enum.PowerUpCategoryEnum;
import src.Enum.RarityEnum;
import src.GameObject.Paddle;

public class NoScalingPowerUp extends BasePowerUp{

    public NoScalingPowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum) {
        super(name, description, powerUpCategoryEnum, rarityEnum);
    }

    public NoScalingPowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum, int maximumLevel) {
        super(name, description, powerUpCategoryEnum, rarityEnum, maximumLevel);
    }

    public NoScalingPowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum, SpriteEnum itemSprite) {
        super(name, description, powerUpCategoryEnum, rarityEnum, itemSprite);
    }

    public NoScalingPowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum, int maximumLevel, SpriteEnum itemSprite) {
        super(name, description, powerUpCategoryEnum, rarityEnum, maximumLevel, itemSprite);
    }

    @Override
    public void doEffect(Paddle player, int level) {
    }

    @Override
    public float calculateValue(float value, int level) {
        return 0;
    }
}
