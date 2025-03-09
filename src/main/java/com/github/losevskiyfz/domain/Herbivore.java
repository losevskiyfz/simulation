package com.github.losevskiyfz.domain;

import com.github.losevskiyfz.map.Cell;

import java.util.List;

public class Herbivore extends Creature {
    @Override
    public void makeMove(List<Cell> path) {
        if (path.isEmpty()) return;
        Cell source = path.getFirst();
        for (int i = 0; i < speed; i++) {
            Cell next = path.get(i + 1);
            if (getTarget().isInstance(next.getEntity())) {
                next.setEntity(source.getEntity());
                source.setEntity(new EmptyEntity());
                break;
            }
            if (next.getEntity() instanceof EmptyEntity) {
                next.setEntity(source.getEntity());
                source.setEntity(new EmptyEntity());
            }
        }
    }

    @Override
    public Class<?> getTarget() {
        return Grass.class;
    }

    @Override
    public String getView() {
        return "🐰";
    }

    public void getDamage(int damage) {
        hp -= damage;
    }
}
