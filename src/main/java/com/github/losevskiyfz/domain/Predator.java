package com.github.losevskiyfz.domain;

import com.github.losevskiyfz.map.Cell;

import java.util.List;

public class Predator extends Creature {

    protected int damage = 1;

    @Override
    public void makeMove(List<Cell> path) {
        if (path.isEmpty()) return;
        Cell source = path.getFirst();
        for (int i = 0; i < speed; i++) {
            Cell next = path.get(i + 1);
            if (getTarget().isInstance(next.getEntity())) {
                Herbivore herbivore = (Herbivore) next.getEntity();
                herbivore.getDamage(damage);
                if (herbivore.hp <= 0){
                    next.setEntity(source.getEntity());
                    source.setEntity(new EmptyEntity());
                    break;
                }
            }
            if (next.getEntity() instanceof EmptyEntity) {
                next.setEntity(source.getEntity());
                source.setEntity(new EmptyEntity());
            }
        }
    }

    @Override
    public Class<?> getTarget() {
        return Herbivore.class;
    }

    @Override
    public String getView() {
        return "🐺";
    }
}
