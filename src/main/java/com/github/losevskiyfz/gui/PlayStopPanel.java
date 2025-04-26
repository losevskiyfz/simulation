package com.github.losevskiyfz.gui;

import javax.swing.*;

public class PlayStopPanel extends JPanel {

    JButton playButton = new JButton("▶");
    JButton stopButton = new JButton("⏹");

    PlayStopPanel(Simulation simulation) {
        playButton.addActionListener(e -> simulation.startSimulation());
        stopButton.addActionListener(e -> simulation.pauseSimulation());
        add(playButton);
        add(stopButton);
    }
}