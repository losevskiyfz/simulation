package com.github.losevskiyfz.domain;

import com.github.losevskiyfz.map.Map;
import com.github.losevskiyfz.map.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Predator extends Creature {
    protected int damage = 1;
    private Class<? extends Entity> target = Herbivore.class;
    private static final Logger LOG = LogManager.getLogger(Predator.class);

    @Override
    public String view() {
        return "🐺";
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    public int damage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void makeMove(List<Point> path, Map<Entity> map) {
        if (path.isEmpty()) return;
        Point source = path.getFirst();
        for (int i = 0; i < speed; i++) {
            Point next = path.get(i + 1);
            if (getTarget().isInstance(map.get(next))) {
                LOG.info("Predator target acquired. Point: {}. Target: {}", next, getTarget());
                Herbivore herbivore = (Herbivore) map.get(next);
                herbivore.reduceHealth(damage);
                if (herbivore.hp <= 0){
                    map.put(next, map.get(source));
                    map.put(source, new EmptyEntity());
                    break;
                }
            }
            if (map.get(next) instanceof EmptyEntity) {
                LOG.info("Predator make move. From: {}. To: {}", source, path.get(i + 1));
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
