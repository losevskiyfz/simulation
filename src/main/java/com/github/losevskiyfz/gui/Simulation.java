package com.github.losevskiyfz.gui;

import com.github.losevskiyfz.map.Map;

import javax.swing.*;

public class Simulation extends JFrame {
    private final JPanel gameFieldPanel = new GameFieldPanel(new Map(25, 25));

    private void init() {
        setTitle("Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setResizable(false);
        add(gameFieldPanel);
        setVisible(true);
    }

    public void run(){
        init();
    }
}
