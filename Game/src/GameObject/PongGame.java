package src.GameObject;

import src.PowerUp.Abstractions.BasePowerUp;
import src.PowerUp.Helper.GlobalPowerUpHelper;
import src.PowerUp.Implementations.*;

import java.util.ArrayList;
import java.util.Arrays;

public class PongGame {

	public static void main(String[] args) {
        ArrayList<BasePowerUp> powerUps = new ArrayList<>(Arrays.asList(
                new IncreaseSize(),
                new IncreaseMovementSpeed(),
                new IncreaseHitForce(),
                new IncreaseObstacleSpawnRate(),
                new IncreaseBallDiameter(),
                new IncreaseObstacleSize(),
                new BecomeInvincibleToBricks(),
                new IncreaseObstacleAmount()
        ));
        GlobalPowerUpHelper.setGlobalPowerUpList(powerUps);
		GameFrame frame = new GameFrame();
		
	}
}
