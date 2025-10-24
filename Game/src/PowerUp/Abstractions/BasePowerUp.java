package src.PowerUp.Abstractions;

import src.AssetManager.Sprites.SpriteEnum;
import src.Enum.PowerUpCategoryEnum;
import src.Enum.RarityEnum;
import src.GameObject.Paddle;

import java.util.Optional;


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

    private final PowerUpCategoryEnum powerUpCategory;

    public PowerUpCategoryEnum getPowerUpCategory() {
        return powerUpCategory;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    private SpriteEnum itemSprite;

    public Optional<String> getImagePath() {
        return Optional.ofNullable(itemSprite.getPath());
    }

    private RarityEnum rarityEnum;

    public RarityEnum getRarityEnum() {
        return rarityEnum;
    }

    //TODO: implement this when the player paddle is implemented
    public abstract void doEffect(Paddle player, int level);
    public abstract float calculateValue(float value, int level);

    public BasePowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum) {
        this.name = name;
        this.description = description;
        this.powerUpCategory = powerUpCategoryEnum;
        this.description = description;
        this.rarityEnum = rarityEnum;
    }

    public BasePowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum, int maximumLevel) {
        this(name, description, powerUpCategoryEnum, rarityEnum);
        this.maximumLevel = maximumLevel;
    }

    public BasePowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum, SpriteEnum itemSprite) {
        this(name, description, powerUpCategoryEnum, rarityEnum);
        this.itemSprite = itemSprite;
    }

    public BasePowerUp(String name, String description, PowerUpCategoryEnum powerUpCategoryEnum, RarityEnum rarityEnum, int maximumLevel, SpriteEnum itemSprite) {
        this(name, description, powerUpCategoryEnum, rarityEnum, maximumLevel);
        this.itemSprite = itemSprite;
    }
}