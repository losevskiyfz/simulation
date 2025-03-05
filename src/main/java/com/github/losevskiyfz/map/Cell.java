package com.github.losevskiyfz.map;

import com.github.losevskiyfz.domain.Entity;

public class Cell {
    private Entity entity;

    @Override
    public String toString() {
        return entity.toString();
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
