package src.PowerUp.UI;

import src.PowerUp.PowerUpWithLevel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PowerUpInfo extends JPanel {
    private ArrayList<PowerUpWithLevel> powerUps;
    private final int MAX_ITEMS_PER_ROW = 4;

    public PowerUpInfo(ArrayList<PowerUpWithLevel> powerUps) {
        this.powerUps = powerUps;
        setOpaque(false);
        refresh(powerUps);
    }

    // TODO: decide if this has to get changes so its only lcas or only other arguements
    // TODO: decide if more space for items is important, also maybe make some of these numbers constants to avoid magic numbers


    private JPanel createRowPanel() {
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        rowPanel.setOpaque(false);
        return rowPanel;
    }


    public void refresh(ArrayList<PowerUpWithLevel> powerUps) {
        removeAll();
        System.out.println(powerUps.size() + "size");

        if (powerUps.isEmpty()) {
            revalidate();
            repaint();
            return;
        }

        JPanel row = createRowPanel();
        int count = 0;
        add(row);

        for (PowerUpWithLevel powerUp : powerUps) {
            if (count == MAX_ITEMS_PER_ROW) {
                add(Box.createRigidArea(new Dimension(0, 10)));
                row = createRowPanel();
                add(row);
                count = 0;
            }

            PowerUpStatus powerUpStatus = createPowerUpStatus(powerUp);
            row.add(powerUpStatus);
            count++;
        }

        revalidate();
        repaint();
    }

    public PowerUpStatus createPowerUpStatus(PowerUpWithLevel powerUpWithLevel) {
        return new PowerUpStatus(powerUpWithLevel);
    }

    public PowerUpInfo() {
        this.powerUps = new ArrayList<>();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
    }
}
