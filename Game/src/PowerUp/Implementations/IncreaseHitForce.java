package src.PowerUp.Implementations;

import src.AssetManager.Sprites.SpriteEnum;
import src.Enum.OperationType;
import src.Enum.PowerUpCategoryEnum;
import src.Enum.RarityEnum;
import src.GameObject.Paddle;
import src.PowerUp.Abstractions.SetScalingPowerUp;

public class IncreaseHitForce extends SetScalingPowerUp {
    public IncreaseHitForce() {
        super(
                "Stronger hit",
                "Makes your hits stronger and the ball faster. Makes both your and the opponents game harder",
                PowerUpCategoryEnum.ATTACK,
                RarityEnum.RARE,
                SpriteEnum.ATTACK_AXE,
                new float[]{1, 2.5f, 5f, 6.5f, 8},
                OperationType.ADDITION
        );
    }

    @Override
    public void doEffect(Paddle player, int level) {
        float hitForce = calculateValue(player.getHitForce(), level);
        player.setHitForce(hitForce);
    }

    // TODO: check if I have to implement constructors like this
}
