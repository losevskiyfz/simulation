package com.github.losevskiyfz.gui;

import com.github.losevskiyfz.map.Map;

import javax.swing.*;

public class Simulation extends JFrame {
    private static final int MAP_COLS = 50;
    private static final int MAP_ROWS = 25;
    private final Map map = new Map(MAP_COLS, MAP_ROWS);
    private final JPanel gameFieldPanel = new GameFieldPanel(map);

    private void init() {
        setTitle("Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        add(gameFieldPanel);
        setVisible(true);
    }

    public void run(){
        init();
    }
}
