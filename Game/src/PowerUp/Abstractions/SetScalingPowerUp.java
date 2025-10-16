package PowerUp.Abstractions;

import Enum.OperationType;
import Enum.RarityEnum;
import Enum.PowerUpCategoryEnum;

public abstract class SetScalingPowerUp extends BasePowerUp {
    private float[] levels;
    private OperationType operationType;
    public OperationType getOperationType() {
        return operationType;
    }
    public float[] getLevels() {
        return levels;
    }
    @Override
    public float calculateValue(float value, int level) {
        int index = level - 1;
        float levelValue = levels[index];

        switch (operationType) {
            case ADDITION:
                return value + levelValue;
            case SUBSTRACTION:
                return value - levelValue;
            case MULTIPLICATION:
                return value * levelValue;
            case DIVISION:
                return value / levelValue;
            case EXPONENTIAL:
                //TODO: extract into a helper function later
                int floored = (int)Math.floor((double) levelValue);
                float accumulator = 1;

                for (int i = 0; i < floored; i++) {
                    accumulator *= value;
                }

                return accumulator;
            default:
                return 0;
        }
    }

    public SetScalingPowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum) {
        super(name, description, powerUpCategoryEnum, rarityEnum);
    }

    public SetScalingPowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum, int maximumLevel) {
        super(name, description, powerUpCategoryEnum, rarityEnum, maximumLevel);
    }

    public SetScalingPowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum, String imagePath) {
        super(name, description, powerUpCategoryEnum, rarityEnum, imagePath);
    }

    public SetScalingPowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum, int maximumLevel, String imagePath) {
        super(name, description, powerUpCategoryEnum, rarityEnum, maximumLevel, imagePath);
    }
}
