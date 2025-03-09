package com.github.losevskiyfz.map;

import com.github.losevskiyfz.domain.EmptyEntity;
import com.github.losevskiyfz.domain.Entity;

import java.util.Objects;

public class Cell {
    private Entity entity = new EmptyEntity();
    public final  int x;
    public final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public boolean isFree() {
        return entity instanceof EmptyEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
