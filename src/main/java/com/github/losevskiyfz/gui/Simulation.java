package com.github.losevskiyfz.gui;

import com.github.losevskiyfz.domain.*;
import com.github.losevskiyfz.map.Point;
import com.github.losevskiyfz.provider.EntityProvider;
import com.github.losevskiyfz.provider.RandomEntityProvider;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Simulation extends JFrame {
    private final int mapCols = 50;
    private final int mapRows = 25;
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
    private final JPanel gameFieldPanel = new GameFieldPanel(map);

    private void init() {
        add(gameFieldPanel, BorderLayout.CENTER);
        setTitle("Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setVisible(true);
    }

    public void run() {
        SwingUtilities.invokeLater(this::init);
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
}
