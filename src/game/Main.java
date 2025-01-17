package game;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // TWORZENIE RAMKI
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Tamagoczi");

        // DODANIE PANELU DO RAMKI
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.start();


    }
}