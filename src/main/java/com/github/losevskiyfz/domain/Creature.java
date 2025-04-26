package com.github.losevskiyfz.domain;

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
}
