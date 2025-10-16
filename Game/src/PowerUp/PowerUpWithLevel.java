package PowerUp;

import PowerUp.Abstractions.BasePowerUp;

public class PowerUpWithLevel {
    //TODO: decide whether inheritance or composition is better
    private final BasePowerUp powerUp;
    public BasePowerUp getPowerUp() {
        return powerUp;
    }
    private int level = 1;

    public void setLevel(int level) {
        if (level >= powerUp.getMaximumLevel()) {
            return;
        }


        this.level = level;
    }

    public boolean isMaxLevel() {
        return this.level == getPowerUp().getMaximumLevel();
    }
    public void incrementLevel() {
        if (isMaxLevel()) {
            return;
        }

        this.level += 1;
    }

    public int getLevel() {
        return level;
    }
    public PowerUpWithLevel(BasePowerUp powerUp) {
        this.powerUp = powerUp;
    }
}
