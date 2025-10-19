package src.PowerUp;

import src.Enum.RarityEnum;
import src.PowerUp.Abstractions.BasePowerUp;

import java.util.List;

//TODO: possibly refactor to map if applicable
public class RarityWithPowerUps {
    private RarityEnum rarityEnum;

    public RarityEnum getRarityEnum() {
        return rarityEnum;
    }

    public void setRarityEnum(RarityEnum rarityEnum) {
        this.rarityEnum = rarityEnum;
    }

    private List<BasePowerUp> powerUps;

    public List<BasePowerUp> getPowerUps() {
        return powerUps;
    }

    public void setPowerUps(List<BasePowerUp> powerUps) {
        this.powerUps = powerUps;
    }

    public boolean isEmpty() {
        return powerUps.isEmpty();
    }

    public RarityWithPowerUps(RarityEnum rarityEnum, List<BasePowerUp> powerUps) {
        setRarityEnum(rarityEnum);
        setPowerUps(powerUps);
    }
}
