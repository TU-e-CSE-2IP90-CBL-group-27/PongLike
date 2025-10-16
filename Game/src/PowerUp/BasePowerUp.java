package PowerUp;

import Enum.PowerUpCategoryEnum;

/*
This class is the base class for the power up object.
Different power ups are derived from this class and their properties and behavior
is set by this class
 */

public abstract class BasePowerUp {
    private int id;
    private String name;
    private PowerUpCategoryEnum powerUpCategory;
    private String description;


}