package com.github.losevskiyfz.domain;

import com.github.losevskiyfz.map.Map;
import com.github.losevskiyfz.map.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Herbivore extends Creature {

    private Class<? extends Entity> target = Grass.class;
    private static final Logger LOG = LogManager.getLogger(Herbivore.class);

    @Override
    public String view() {
        return "🐰";
    }

    public void reduceHealth(int damage) {
        hp -= damage;
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public void makeMove(List<Point> path, Map<Entity> map) {
        if (path.isEmpty()) return;
        Point source = path.getFirst();
        for (int i = 0; i < speed; i++) {
            Point next = path.get(i + 1);
            if (getTarget().isInstance(map.get(next))) {
                LOG.info("Herbivore target acquired. Point: {}. Target: {}", next, getTarget());
                map.put(next, map.get(source));
                map.put(source, new EmptyEntity());
                break;
            }
            if (map.get(next) instanceof EmptyEntity) {
                LOG.info("Herbivore make move. From: {}. To: {}", source, next);
                map.put(next, map.get(source));
                map.put(source, new EmptyEntity());
            }
        }
    }

    @Override
    public Class<? extends Entity> getTarget() {
        return target;
    }

    @Override
    public void setTarget(Class<? extends Entity> target) {
        this.target =  target;
    }
}
