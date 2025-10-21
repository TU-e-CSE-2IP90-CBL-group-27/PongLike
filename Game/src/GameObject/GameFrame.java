<<<<<<< Updated upstream:Game/src/GameObject/GameFrame.java
package src.GameObject;

=======
import java.awt.*;
import java.awt.event.*;
import java.util.*;
>>>>>>> Stashed changes:GameFrame.java
import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{

    GamePanel panel;
    GameFrame(){
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
   }
    
    
}