package src.PowerUp.UI;

import javax.swing.*;

public class PowerUpSelectionWindow extends JDialog {
    public PowerUpSelectionWindow() {
        setTitle("Choose Your Power-Up!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Center the window
        setResizable(false);
    }

    public static void init() {
        SwingUtilities.invokeLater(
                () -> {
                    new PowerUpSelectionWindow().setVisible(true
                    );
                }
        );
    }

}
