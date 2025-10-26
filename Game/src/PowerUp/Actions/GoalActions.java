package src.PowerUp.Actions;

import src.GameObject.GamePanel;
import src.GameObject.Paddle;

public class GoalActions {
    public static GamePanel panel;

    public static void setPanel(GamePanel gamePanel) {
        panel = gamePanel;
    }

    public static void decreaseGoalUse(Paddle player) {
        if (panel == null) {
            return;
        }

        panel.decreaseScoreUse(player);
    }

    public static void swapScores() {
        if (panel == null) {
            return;
        }

        panel.swapScores();
    }
}
