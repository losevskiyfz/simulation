package com.github.losevskiyfz.domain;

public abstract class Creature extends Entity {
    protected int hp;
    protected int speed;
    public abstract void makeMove();
}
