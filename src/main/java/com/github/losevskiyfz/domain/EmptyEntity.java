package com.github.losevskiyfz.domain;

public class EmptyEntity extends Entity {
    @Override
    public String view() {
        return " ";
    }

    @Override
    public boolean isPassable(){
        return true;
    }
}
