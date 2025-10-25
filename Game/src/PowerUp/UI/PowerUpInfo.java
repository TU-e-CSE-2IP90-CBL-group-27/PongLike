package src.PowerUp.UI;

import src.PowerUp.PowerUpWithLevel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PowerUpInfo extends JPanel {
    private ArrayList<PowerUpWithLevel> powerUps;

    public PowerUpInfo(ArrayList<PowerUpWithLevel> powerUps) {
        this.powerUps = powerUps;
        setOpaque(false);
        refresh(powerUps);
    }

    // TODO: decide if this has to get changes so its only lcas or only other arguements
    // TODO: decide if more space for items is important, also maybe make some of these numbers constants to avoid magic numbers


    public void refresh(ArrayList<PowerUpWithLevel> powerUps) {
        removeAll();
        System.out.println(powerUps.size() + "size");

        for (PowerUpWithLevel powerUp : powerUps) {
            PowerUpStatus powerUpStatus = createPowerUpStatus(powerUp);
            add(powerUpStatus);
            add(Box.createRigidArea(new Dimension(0, 5)));
        }

        revalidate();
        repaint();
    }

    public PowerUpStatus createPowerUpStatus(PowerUpWithLevel powerUpWithLevel) {
        return new PowerUpStatus(powerUpWithLevel);
    }

    public PowerUpInfo() {
        this.powerUps = new ArrayList<>();
        setOpaque(false);
    }
}
