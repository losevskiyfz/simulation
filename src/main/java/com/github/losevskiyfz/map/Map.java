package com.github.losevskiyfz.map;

import java.util.HashMap;

public class Map<T> {
    private final java.util.Map<Point, T> grid;
    private final int cols;
    private final int rows;

    public Map(int cols, int rows) {
        if (cols <= 0 || rows <= 0) {
            throw new IllegalArgumentException();
        }
        this.cols = cols;
        this.rows = rows;
        this.grid = new HashMap<>();
    }

    public T get(Point point) {
        if (point.x() < 0 || point.x() >= cols || point.y() < 0 || point.y() >= rows) {
            throw new IllegalArgumentException();
        }
        return grid.get(point);
    }

    public void put(Point point, T value) {
        if (point.x() < 0 || point.x() >= cols || point.y() < 0 || point.y() >= rows) {
            throw new IllegalArgumentException();
        }
        grid.put(point, value);
    }

    public int cols(){
        return cols;
    }

    public int rows(){
        return rows;
    }
}
