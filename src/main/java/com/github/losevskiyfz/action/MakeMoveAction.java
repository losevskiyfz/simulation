package com.github.losevskiyfz.action;

import com.github.losevskiyfz.domain.Creature;
import com.github.losevskiyfz.domain.Entity;
import com.github.losevskiyfz.map.Map;
import com.github.losevskiyfz.map.Point;
import com.github.losevskiyfz.path.AStarPathFinder;
import com.github.losevskiyfz.path.PathFinder;

import java.util.HashMap;
import java.util.List;

public class MakeMoveAction implements Action<Entity> {
    private final PathFinder<Entity> pathFinder = new AStarPathFinder<>();

    @Override
    public void act(Map<Entity> map) {
        java.util.Map<Point, Entity> entityRegistry = new HashMap<>();

        for (int i = 0; i < map.cols(); i++) {
            for (int j = 0; j < map.rows(); j++) {
                Point p = new Point(i, j);
                entityRegistry.put(p, map.get(p));
            }
        }
        for (java.util.Map.Entry<Point, Entity> entry : entityRegistry.entrySet()) {
            Point key = entry.getKey();
            if (entityRegistry.get(key) instanceof Creature creature) {
                List<Point> path = pathFinder.findPath(map, key, creature.getTarget());
                creature.makeMove(path, map);
            }
        }
    }
}