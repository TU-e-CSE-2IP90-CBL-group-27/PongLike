package src.GameObject;

import src.AssetManager.Sound.SoundEffectEnum;
import src.AssetManager.Sound.SoundManager;
import src.GameValuesManager.MainValues;
import src.GameValuesManager.MainValuesConstants;
import src.PowerUp.Actions.PowerUpFinder;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

/*
 * This class everything related to obstacles, including: 
 * Spawn timing (10s interval)
 * Random positioning (with safe margins)
 * Collision logic with the ball
 * Rendering the obstacle
 */
public class ObstacleManager {
    private static final int PADDLE_SAFE_MARGIN = 150;
    private static final int MAX_SPAWN_ATTEMPTS = 50;

    private final int gameWidth;
    private final int gameHeight;
    private final int paddleWidth;

    private final Random random = new Random();
    private final ArrayList<Obstacle> obstacles = new ArrayList<>();
    private long lastSpawnNs;

    public ObstacleManager(int gameWidth, int gameHeight, int paddleWidth) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.paddleWidth = paddleWidth;
        this.lastSpawnNs = System.nanoTime();
        
    }

    public void update(Ball ball) {
        long now = System.nanoTime();
        if (now - lastSpawnNs >= MainValues.getObstacleIntervalNanos()) {
            obstacles.clear();
            spawnObstacles(ball);
            lastSpawnNs = now;
        }
    }

    private void spawnObstacles(Ball ball) {
        for (int i = 0; i < MainValues.getObstacleAmount(); i++) {
            spawnNewObstacle(ball);
        }
    }

    private void spawnNewObstacle(Ball ball) {
        int minX = paddleWidth + PADDLE_SAFE_MARGIN;
        int maxX = gameWidth - paddleWidth - PADDLE_SAFE_MARGIN - Obstacle.WIDTH;
        if (maxX <= minX) return;

        int maxY = gameHeight - MainValues.getObstacleHeight();
        if (maxY < 0) return;

        for (int i = 0; i < MAX_SPAWN_ATTEMPTS; i++) {
            int x = minX + random.nextInt((maxX - minX) + 1);
            int y = random.nextInt(Math.max(1, maxY + 1));
            Obstacle candidate = new Obstacle(x, y);

            if (!candidate.intersects(ball)) {
                obstacles.add(candidate);
                return;
            }
        }
        // fallback center spawn
        Obstacle obstacle = new Obstacle(
            minX + (maxX - minX) / 2,
            Math.max(0, Math.min(gameHeight - MainValues.getObstacleHeight(), (gameHeight - MainValues.getObstacleHeight()) / 2))
        );

        obstacles.add(obstacle);
        // TODO: fix bug when its in the ball
    }

    public Optional<Obstacle> findCollision(Ball ball) {
        return obstacles.stream().filter(ball::intersects).findFirst();
    }

    public void handleCollision(Ball ball) {
        Optional<Obstacle> hitObstacle = findCollision(ball);
        if (hitObstacle.isEmpty()) return;
        if (ball.getLastHit() != null && PowerUpFinder.checkForBrickInvincibility(ball.getLastHit())) return;

        SoundManager.playSound(SoundEffectEnum.BRICK_HIT);
        Rectangle inter = ball.intersection(hitObstacle.get());
        if (inter.width < inter.height) {
            ball.setXDirection(-ball.xVelocity);
        } else {
            ball.setYDirection(-ball.yVelocity);
        }
    }

    public void draw(Graphics g) {
        obstacles.forEach(x -> x.draw(g));
    }

}
