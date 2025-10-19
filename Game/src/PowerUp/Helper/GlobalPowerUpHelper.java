package src.PowerUp.Helper;

import src.PowerUp.Abstractions.BasePowerUp;

import java.util.ArrayList;

public class GlobalPowerUpHelper {
    private static ArrayList<BasePowerUp> globalPowerUpList = new ArrayList<>();

    public static ArrayList<BasePowerUp> getGlobalPowerUpList() {
        return globalPowerUpList;
    }

    public static void setGlobalPowerUpList(ArrayList<BasePowerUp> globalPowerUpList) {
        GlobalPowerUpHelper.globalPowerUpList = globalPowerUpList;
    }
}
