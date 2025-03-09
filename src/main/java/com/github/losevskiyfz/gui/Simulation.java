package com.github.losevskiyfz.gui;

import com.github.losevskiyfz.action.Action;
import com.github.losevskiyfz.action.Actions;
import com.github.losevskiyfz.action.MakeMoveAction;
import com.github.losevskiyfz.action.MaintainAction;
import com.github.losevskiyfz.map.Map;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Simulation extends JFrame {
    private static final int MAP_COLS = 50;
    private static final int MAP_ROWS = 25;
    private static int MOVE_COUNTER = 0;

    private final Map map = new Map(MAP_COLS, MAP_ROWS);
    private final Actions turnActions = new Actions();
    private final Actions initActions = new Actions();
    private final Renderer renderer = new Renderer();
    private final List<Timer> asyncTasks = new ArrayList<>();

    private final JPanel gameFieldPanel = new GameFieldPanel(map);
    private final JPanel playStopPanel = new PlayStopPanel(this);

    private void init() {
        initializeActions();
        runInitActions();
        add(gameFieldPanel, BorderLayout.CENTER);
        add(playStopPanel, BorderLayout.SOUTH);
        setTitle("Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setVisible(true);
        addAsyncTasks();
    }

    private void initializeActions() {
        initActions.add(new MaintainAction());
        turnActions.add(new MakeMoveAction());
    }

    private void runInitActions() {
        for (int i = 0; i < 3; i++) {
            for (Action action : initActions) {
                action.doAction(map);
            }
        }
        renderer.rerender(gameFieldPanel);
    }

    public static void incrementMoveCounter() {
        MOVE_COUNTER++;
    }

    public void nextTurn() {
        for (Action action : turnActions) {
            action.doAction(map);
            renderer.rerender(gameFieldPanel);
        }
    }

    public void run(){
        SwingUtilities.invokeLater(this::init);
        SwingUtilities.invokeLater(this::startSimulation);
    }

    public void startSimulation() {
        SwingUtilities.invokeLater(() -> asyncTasks.forEach(Timer::start));
    }

    private void addAsyncTasks() {
        Timer t1 = new Timer(500, event -> {
            nextTurn();
        });
        Timer t2 = new Timer(15_000, event -> {
            runInitActions();
        });
        asyncTasks.add(t1);
        asyncTasks.add(t2);
    }

    public void pauseSimulation() {
        SwingUtilities.invokeLater(() -> asyncTasks.forEach(Timer::stop));
    }

    private static class Renderer {
        void rerender(JPanel panel) {
            panel.repaint();
        }
    }
}
