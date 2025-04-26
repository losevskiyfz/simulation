package com.github.losevskiyfz.domain;

import com.github.losevskiyfz.map.Map;
import com.github.losevskiyfz.map.Point;

import java.util.List;

public class Herbivore extends Creature {

    private Class<? extends Entity> target = Grass.class;

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
                map.put(next, map.get(source));
                map.put(source, new EmptyEntity());
                break;
            }
            if (map.get(next) instanceof EmptyEntity) {
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
