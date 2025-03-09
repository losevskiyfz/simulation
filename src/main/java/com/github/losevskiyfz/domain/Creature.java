package com.github.losevskiyfz.domain;

import com.github.losevskiyfz.map.Cell;

import java.util.List;

public abstract class Creature extends Entity {
    protected int hp = 1;
    protected int speed = 1;
    public abstract void makeMove(List<Cell> path);
    public abstract Class<?> getTarget();
}
