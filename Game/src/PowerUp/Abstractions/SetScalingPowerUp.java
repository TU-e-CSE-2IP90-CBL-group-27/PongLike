package src.PowerUp.Abstractions;

import src.AssetManager.Sprites.SpriteEnum;
import src.Enum.OperationType;
import src.Enum.RarityEnum;
import src.Enum.PowerUpCategoryEnum;

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
        float previousValue = index == 0 ? 0 : levels[index - 1];

        switch (operationType) {
            case ADDITION:
                return value - previousValue + levelValue;
            case SUBSTRACTION:
                return value + previousValue - levelValue;
            case MULTIPLICATION:
                if (previousValue == 0) {
                    previousValue = 1;
                }

                return value / previousValue * levelValue;
            case DIVISION:
                if (previousValue == 0) {
                    previousValue = 1;
                }

                return value * previousValue / levelValue;
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

    public void setScalingProperties(float[] levels, OperationType operationType) {
        this.levels = levels;
        this.operationType = operationType;
    }

    public SetScalingPowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum,
                             float[] levels, OperationType operationType
                             ) {
        super(name, description, powerUpCategoryEnum, rarityEnum);
        setScalingProperties(levels, operationType);
    }

    public SetScalingPowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum, int maximumLevel,
                             float[] levels, OperationType operationType
    ) {
        super(name, description, powerUpCategoryEnum, rarityEnum, maximumLevel);
        setScalingProperties(levels, operationType);
    }

    public SetScalingPowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum, SpriteEnum itemSprite, float[] levels, OperationType operationType
    ) {
        super(name, description, powerUpCategoryEnum, rarityEnum, itemSprite);
        setScalingProperties(levels, operationType);
    }

    public SetScalingPowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum, int maximumLevel, SpriteEnum itemSprite, float[] levels, OperationType operationType
    ) {
        super(name, description, powerUpCategoryEnum, rarityEnum, maximumLevel, itemSprite);
        setScalingProperties(levels, operationType);
    }
}
