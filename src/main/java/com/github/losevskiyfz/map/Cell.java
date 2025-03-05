package com.github.losevskiyfz.map;

import com.github.losevskiyfz.domain.Entity;
import com.github.losevskiyfz.domain.Herbivore;

public class Cell {
    private Entity entity = new Herbivore();

    @Override
    public String toString() {
        return entity.toString();
    }
}
