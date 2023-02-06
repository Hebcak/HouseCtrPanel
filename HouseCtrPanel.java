import GUI.MainGUI;
import light.LightManager;

import javax.swing.*;

public class HouseCtrPanel {
    public static void main(String[] args) {
        JFrame mainWindow = new JFrame("Hebčákův mistrovský budík");
        mainWindow.setContentPane(new MainGUI().mainPanel);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.pack();
        mainWindow.setVisible(true);
    }
}
