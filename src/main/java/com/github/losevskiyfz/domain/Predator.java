package com.github.losevskiyfz.domain;

public class Predator extends Creature {
    protected int damage = 1;

    @Override
    public String view() {
        return "🐺";
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
