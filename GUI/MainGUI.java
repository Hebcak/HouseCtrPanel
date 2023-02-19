package GUI;

import light.LightManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {
    public JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel statusPanel;
    private JButton menuButton;
    private JLabel TimeLabel;
    private JButton lightButton;
    private JSeparator SeparatorV;

    //Light variables
    public static String LED_SVETLO_IP = "192.168.178.31";
    public static int LED_SVETLO_PORT = 100;
    public static LightManager mainLights;

    public MainGUI() {
        mainLights = new LightManager(LED_SVETLO_IP, LED_SVETLO_PORT);
        mainLights.createLight("READI", false);
        mainLights.createLight("TABLE", false);
        mainLights.createLight("CUPBO", false);

        Color color = new Color(0, 163, 234);
        menuButton.setBorder(BorderFactory.createLineBorder(color, 2));
        lightButton.setBorder(BorderFactory.createLineBorder(color, 2));

        menuButton.addActionListener(actionEvent -> System.out.println("Foficek"));
        lightButton.addActionListener(actionEvent -> mainLights.allToggle());
    }
}
