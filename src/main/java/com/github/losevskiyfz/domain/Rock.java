package com.github.losevskiyfz.domain;

public class Rock extends Entity{
    @Override
    public String view() {
        return "🪨";
    }

    @Override
    public boolean isPassable() {
        return false;
    }
}
