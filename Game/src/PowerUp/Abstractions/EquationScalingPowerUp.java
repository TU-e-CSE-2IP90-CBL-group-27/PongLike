package PowerUp.Abstractions;
import Enum.PowerUpCategoryEnum;
import Enum.RarityEnum;

public abstract class EquationScalingPowerUp extends BasePowerUp {
    public abstract float equation(float value, int level);

    @Override
    public float calculateValue(float value, int level) {
        return equation(value, level);
    }
    public EquationScalingPowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum     ) {
        super(name, description, powerUpCategoryEnum, rarityEnum);
    }

    public EquationScalingPowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum, int maximumLevel) {
        super(name, description, powerUpCategoryEnum, rarityEnum, maximumLevel);
    }

    public EquationScalingPowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum, String imagePath) {
        super(name, description, powerUpCategoryEnum, rarityEnum, imagePath);
    }

    public EquationScalingPowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum, int maximumLevel, String imagePath) {
        super(name, description, powerUpCategoryEnum, rarityEnum, maximumLevel, imagePath);
    }
}
