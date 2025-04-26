package com.github.losevskiyfz.domain;

import com.github.losevskiyfz.map.Map;
import com.github.losevskiyfz.map.Point;

import java.util.List;

public abstract class Creature extends Entity {
    protected int hp = 1;
    protected int speed = 1;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public abstract void makeMove(List<Point> path, Map<Entity> map);

    public abstract Class<? extends Entity> getTarget();

    public abstract void setTarget(Class<? extends Entity> target);
}
