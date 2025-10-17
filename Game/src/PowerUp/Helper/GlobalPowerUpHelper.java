package src.PowerUp.Helper;

import src.PowerUp.Abstractions.BasePowerUp;

import java.util.ArrayList;

public class GlobalPowerUpHelper {
    private static ArrayList<BasePowerUp> globalPowerUpList;

    public static ArrayList<BasePowerUp> getGlobalPowerUpList() {
        return globalPowerUpList;
    }

    public void setGlobalPowerUpList(ArrayList<BasePowerUp> globalPowerUpList) {
        this.globalPowerUpList = globalPowerUpList;
    }
}
