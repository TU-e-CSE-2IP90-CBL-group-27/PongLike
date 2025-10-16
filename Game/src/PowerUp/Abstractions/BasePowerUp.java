package PowerUp.Abstractions;

import Enum.PowerUpCategoryEnum;
import Enum.RarityEnum;


/*
This class is the base class for the power up object.
Different power ups are derived from this class and their properties and behavior
is set by this class
 */

public abstract class BasePowerUp {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    private int maximumLevel = 5;
    public void setMaximumLevel(int level) {
        maximumLevel = level;
    }
    public int getMaximumLevel() {
        return maximumLevel;
    }
    private PowerUpCategoryEnum powerUpCategory;
    public PowerUpCategoryEnum getPowerUpCategory() {
        return powerUpCategory;
    }
    private String description;
    public String getDescription() {
        return description;
    }
    private String imagePath;
    public String getImagePath() {
        return imagePath;
    }

    private RarityEnum rarityEnum;

    public RarityEnum getRarityEnum() {
        return rarityEnum;
    }

    //TODO: implement this when the player paddle is implemented
    public abstract void doEffect();
    public abstract float calculateValue(float value, int level);

    public BasePowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum) {
        this.name = name;
        this.description = description;
        this.powerUpCategory = powerUpCategoryEnum;
        this.description = description;
    }

    public BasePowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum, int maximumLevel) {
        this(name, description, powerUpCategoryEnum, rarityEnum);
        this.maximumLevel = maximumLevel;
    }

    public BasePowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum, String imagePath) {
        this(name, description, powerUpCategoryEnum, rarityEnum);
        this.imagePath = imagePath;
    }

    public BasePowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum, int maximumLevel, String imagePath) {
        this(name, description, powerUpCategoryEnum, rarityEnum, maximumLevel);
        this.imagePath = imagePath;
    }
}