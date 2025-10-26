package src.AI;

import src.GameObject.Paddle;
import src.GameObject.Ball;

import java.util.Random;

public class AIPaddleController {
    private final Random random = new Random();

    private int recalcEveryTicks = 12;  // reaction delay 
    private int tickCounter = 0;
    private int errorOffset = 0;        // miss
    private int maxSpeed = 7;           // px/tick cap

    private final int paddleHeight;
    private final int ballDiameter;

    public AIPaddleController(int paddleHeight, int ballDiameter) {
        this.paddleHeight = paddleHeight;
        this.ballDiameter = ballDiameter;
    }

    public void update(Paddle paddle, Ball ball, int gameWidth, int gameHeight) {
        // forcing error
        if (++tickCounter % recalcEveryTicks == 0) {
            errorOffset = random.nextInt(61) - 30; 
        }

        int ballCenterY = ball.y + (ballDiameter / 2);
        int targetY = ballCenterY + errorOffset;
        int paddleCenterY = paddle.y + (paddleHeight / 2);

        int dy = targetY - paddleCenterY;
        if (Math.abs(dy) > maxSpeed) dy = (dy > 0) ? maxSpeed : -maxSpeed;

        paddle.y += dy;

        // clamp
        int minY = 0;
        int maxY = gameHeight - paddleHeight;
        paddle.y = Math.max(minY, Math.min(paddle.y, maxY));
    }

    // Tweaking the difficulty of the game.
    public void setRecalcEveryTicks(int v) {
        recalcEveryTicks = Math.max(1, v); 
        }
    public void setMaxSpeed(int v) {
        maxSpeed = Math.max(1, v); 
        }
}
