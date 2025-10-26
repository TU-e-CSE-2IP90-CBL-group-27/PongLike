package src.PowerUp.UI;

import src.AssetManager.Sound.SoundEffectEnum;
import src.AssetManager.Sound.SoundManager;
import src.GameObject.GameFrame;
import src.GameObject.GamePanel;
import src.GameObject.Paddle;
import src.PowerUp.Abstractions.BasePowerUp;
import src.PowerUp.Actions.PowerUpAdder;
import src.PowerUp.Actions.PowerUpFinder;
import src.PowerUp.PowerUpWithLevel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PowerUpSelectionWindow extends JDialog {

    private Paddle player;

    private GamePanel mainGame;

    private int repeat = 0;

    private ArrayList<PowerUpPanel> panels = new ArrayList<>();

    private PowerUpInfo powerUpInfo;

    private Component mainPanel;

    private ArrayList<BasePowerUp> powerUps = new ArrayList<>();

    public PowerUpSelectionWindow(GameFrame gameFrame, GamePanel gamePanel, List<BasePowerUp> powerUps, Paddle player, PowerUpInfo powerUpInfo) {
        super(gameFrame, "Choose Your Power-Up!", true);
        this.player = player;
        this.mainGame = gamePanel;
        this.powerUpInfo = powerUpInfo;
        this.powerUps.addAll(powerUps);

        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        paintPowerUps();
    }

    private void paintPowerUps() {
        int count = super.getComponentCount();
        if (count > 1) {
            System.out.println(super.getComponent(0).getClass());
            System.out.println("Count " + count);
            remove(this.mainPanel);
        }

        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(1, powerUps.size(), 20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.mainPanel = mainPanel;

        this.panels.clear();
        this.panels.addAll(powerUps.stream().map(x -> new PowerUpPanel(this, x, player.getPowerUps())).toList());
        panels.forEach(mainPanel::add);
        add(mainPanel);
    }

    public void selectPowerUp(BasePowerUp powerUp) {
        PowerUpAdder.addPowerUpToPlayer(player, powerUp);
        SoundManager.playSound(SoundEffectEnum.POWER_UP_SELECT);
        powerUpInfo.refresh(player.getPowerUps());

        repeat++;
        if (repeat >= player.getPowerUpSelectionRepeat()) {
            close();
            return;
        }

        powerUps.remove(powerUp);
        remove(mainPanel);
        paintPowerUps();
        if (powerUps.isEmpty()) {
            close();
        }
        revalidate();
        repaint();
    }

    public void close() {
        mainGame.positionPowerUpInfo();
        mainGame.decrementIsPaused();
        this.dispose();
    }

    public int getPowerUpLevel(BasePowerUp powerUp) {
        Optional<PowerUpWithLevel> power = PowerUpFinder.findPowerUpIfExists(player, powerUp);

        if (power.isEmpty()) {
            return 0;
        }

        return power.get().getLevel();
    }

    public static void init(GameFrame gameFrame, GamePanel gamePanel, List<BasePowerUp> powerUps, Paddle player, PowerUpInfo powerUpInfo) {
        SwingUtilities.invokeLater(
                () -> {
                    new PowerUpSelectionWindow(gameFrame, gamePanel, powerUps, player, powerUpInfo).setVisible(true
                    );
                }
        );
    }

}
