package com.github.losevskiyfz.domain;

public class Predator extends Creature {
    protected int damage = 1;

    @Override
    public String view() {
        return "🐺";
    }
}
