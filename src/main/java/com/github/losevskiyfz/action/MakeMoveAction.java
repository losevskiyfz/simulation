package com.github.losevskiyfz.action;

import com.github.losevskiyfz.domain.Creature;
import com.github.losevskiyfz.domain.Entity;
import com.github.losevskiyfz.gui.Simulation;
import com.github.losevskiyfz.map.Cell;
import com.github.losevskiyfz.map.Map;
import com.github.losevskiyfz.route.AStarPathFinder;
import com.github.losevskiyfz.route.PathFinder;

import java.util.HashMap;


public class MakeMoveAction implements Action {

    private PathFinder pathFinder = new AStarPathFinder();

    @Override
    public void doAction(Map map) {
        Simulation.incrementMoveCounter();
        java.util.Map<Cell, Entity> entityRegistry = new HashMap<>();
        Cell[][] cells = map.getGrid();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                entityRegistry.put(cells[i][j], cells[i][j].getEntity());
            }
        }
        for (java.util.Map.Entry<Cell, Entity> entry : entityRegistry.entrySet()) {
            Cell key = entry.getKey();
            if (entityRegistry.get(key) instanceof Creature creature) {
                creature.makeMove(pathFinder.findPath(map.getGrid(), key, creature.getTarget()));
            }
        }
    }
}
