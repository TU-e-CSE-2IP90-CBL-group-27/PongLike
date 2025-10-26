package src.GameValuesManager;

public class MainValues {
    private static long OBSTACLE_INTERVAL_NANOS = MainValuesConstants.OBSTACLE_INTERVAL_NANOS;
    private static int OBSTACLE_HEIGHT = MainValuesConstants.OBSTACLE_HEIGHT; // tweak
    private static int BALL_DIAMETER = MainValuesConstants.BALL_DIAMETER;
    private static float COMMON_CHANCE = MainValuesConstants.COMMON_CHANCE;
    private static float RARE_CHANCE = MainValuesConstants.RARE_CHANCE;
    private static float LEGENDARY_CHANCE = MainValuesConstants.LEGENDARY_CHANCE;
    private static float INSANE_CHANCE = MainValuesConstants.INSANE_CHANCE;
    private static int OBSTACLE_AMOUNT = MainValuesConstants.OBSTACLE_AMOUNT;

    public static float getCommonChance() {
        return COMMON_CHANCE;
    }

    public static float getRareChance() {
        return RARE_CHANCE;
    }

    public static float getInsaneChance() {
        return INSANE_CHANCE;
    }

    public static float getLegendaryChance() {
        return LEGENDARY_CHANCE;
    }

    public static int getObstacleAmount() {
        return OBSTACLE_AMOUNT;
    }

    public static void setObstacleAmount(int obstacleAmount) {
        OBSTACLE_AMOUNT = obstacleAmount;
    }

    public static void incrementObstacleAmount() {
        OBSTACLE_AMOUNT += 1;
    }


    public static int getBallDiameter() {
        return BALL_DIAMETER;
    }

    public static int getObstacleHeight() {
        return OBSTACLE_HEIGHT;
    }

    public static void setBallDiameter(int ballDiameter) {
        BALL_DIAMETER = ballDiameter;
    }

    public static void setCommonChance(float commonChance) {
        COMMON_CHANCE = commonChance;
    }

    public static void setInsaneChance(float insaneChance) {
        INSANE_CHANCE = insaneChance;
    }

    public static void setLegendaryChance(float legendaryChance) {
        LEGENDARY_CHANCE = legendaryChance;
    }

    public long getOBSTACLE_INTERVAL_NANOS() {
        return OBSTACLE_INTERVAL_NANOS;
    }

    public void setOBSTACLE_INTERVAL_NANOS(long OBSTACLE_INTERVAL_NANOS) {
        this.OBSTACLE_INTERVAL_NANOS = OBSTACLE_INTERVAL_NANOS;
    }

    public static void setObstacleHeight(int obstacleHeight) {
        OBSTACLE_HEIGHT = obstacleHeight;
    }

    public static void setRareChance(float rareChance) {
        RARE_CHANCE = rareChance;
    }

    public static long getObstacleIntervalNanos() {
        return OBSTACLE_INTERVAL_NANOS;
    }

    public static void setObstacleIntervalNanos(long obstacleIntervalNanos) {
        OBSTACLE_INTERVAL_NANOS = obstacleIntervalNanos;
    }

    public void reset() {
        // TODO: Implement for game loop
    }
}
