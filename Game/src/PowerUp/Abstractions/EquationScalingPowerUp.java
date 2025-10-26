package src.PowerUp.Abstractions;
import src.AssetManager.Sprites.SpriteEnum;
import src.Enum.PowerUpCategoryEnum;
import src.Enum.RarityEnum;

public abstract class EquationScalingPowerUp extends BasePowerUp {
    public abstract float equation(float value, int level);

    @Override
    public float calculateValue(float value, int level) {
        return equation(value, level);
    }

    public EquationScalingPowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum) {
        super(name, description, powerUpCategoryEnum, rarityEnum);
    }

    public EquationScalingPowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum, int maximumLevel) {
        super(name, description, powerUpCategoryEnum, rarityEnum, maximumLevel);
    }

    public EquationScalingPowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum, SpriteEnum itemSprite) {
        super(name, description, powerUpCategoryEnum, rarityEnum, itemSprite);
    }

    public EquationScalingPowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum, int maximumLevel, SpriteEnum itemSprite) {
        super(name, description, powerUpCategoryEnum, rarityEnum, maximumLevel, itemSprite);
    }
}
