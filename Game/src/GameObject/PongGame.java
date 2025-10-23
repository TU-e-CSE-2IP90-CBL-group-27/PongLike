package src.GameObject;

import src.PowerUp.Abstractions.BasePowerUp;
import src.PowerUp.Helper.GlobalPowerUpHelper;
import src.PowerUp.Implementations.IncreaseHitForce;
import src.PowerUp.Implementations.IncreaseMovementSpeed;
import src.PowerUp.Implementations.IncreaseSize;

import java.util.ArrayList;
import java.util.Arrays;

public class PongGame {

	public static void main(String[] args) {
        ArrayList<BasePowerUp> powerUps = new ArrayList<>(Arrays.asList(
                new IncreaseSize(),
                new IncreaseMovementSpeed(),
                new IncreaseHitForce()
        ));
        GlobalPowerUpHelper.setGlobalPowerUpList(powerUps);
		GameFrame frame = new GameFrame();
		
	}
}
