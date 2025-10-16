package PowerUp;

import Enum.RarityEnum;
import PowerUp.Abstractions.BasePowerUp;

import java.util.List;

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
