package com.github.losevskiyfz.gui;

import com.github.losevskiyfz.action.Action;
import com.github.losevskiyfz.action.Actions;
import com.github.losevskiyfz.action.MaintainAction;
import com.github.losevskiyfz.action.MakeMoveAction;
import com.github.losevskiyfz.domain.*;
import com.github.losevskiyfz.map.Point;
import com.github.losevskiyfz.provider.EntityProvider;
import com.github.losevskiyfz.provider.RandomEntityProvider;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class Simulation extends JFrame {
    private final int mapCols = 50;
    private final int mapRows = 25;
    private long moveCounter = 0;

    private final Map<Class<? extends Entity>, Integer> probabilities = Map.of(
            EmptyEntity.class, 982,
            Grass.class, 10,
            Herbivore.class, 5,
            Predator.class, 1,
            Rock.class, 1,
            Tree.class, 1
    );
    private final EntityProvider<Entity> entityProvider =
            initializeEntityProvider(probabilities);
    private final com.github.losevskiyfz.map.Map<Entity> map = initializeMap(entityProvider);
    private final Actions<Entity> turnActions = new Actions<>();
    private final Actions<Entity> initActions = new Actions<>();
    private final Renderer renderer = new Renderer();
    private final java.util.List<Timer> asyncTasks = new ArrayList<>();

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
        initActions.add(new MaintainAction(entityProvider));
        turnActions.add(new MakeMoveAction());
    }

    private void runInitActions() {
        for (int i = 0; i < 3; i++) {
            for (Action<Entity> action : initActions) {
                action.act(map);
            }
        }
        renderer.rerender(gameFieldPanel);
    }

    public void incrementMoveCounter() {
        moveCounter++;
    }

    public void nextTurn() {
        for (Action<Entity> action : turnActions) {
            action.act(map);
            renderer.rerender(gameFieldPanel);
        }
    }

    public void run() {
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

    private RandomEntityProvider<Entity> initializeEntityProvider(Map<Class<? extends Entity>, Integer> probabilities) {
        RandomEntityProvider<Entity> entityProvider = new RandomEntityProvider<>();
        for (var entry : probabilities.entrySet()) {
            Class<? extends Entity> entityClass = entry.getKey();
            int probability = entry.getValue();
            try {
                entityProvider.putProbability(entityClass.getDeclaredConstructor().newInstance(), probability);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return entityProvider;
    }

    private com.github.losevskiyfz.map.Map<Entity> initializeMap(EntityProvider<Entity> entityProvider) {
        com.github.losevskiyfz.map.Map<Entity> map = new com.github.losevskiyfz.map.Map<>(mapCols, mapRows);
        for (int i = 0; i < mapCols; i++) {
            for (int j = 0; j < mapRows; j++) {
                map.put(new Point(i, j), entityProvider.get());
            }
        }
        return map;
    }

    private static class Renderer {
        void rerender(JPanel panel) {
            panel.repaint();
        }
    }
}
