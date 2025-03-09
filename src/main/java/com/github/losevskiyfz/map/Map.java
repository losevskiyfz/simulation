package com.github.losevskiyfz.map;

import com.github.losevskiyfz.domain.EmptyEntity;

public class Map {
    private final Cell[][] grid;

    public Map(Integer cols, Integer rows) {
        grid = new Cell[cols][rows];
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                grid[x][y] = new Cell(x, y);
                grid[x][y].setEntity(new EmptyEntity());
            }
        }
    }

    public int getCols() {
        return grid.length;
    }

    public int getRows() {
        return grid[0].length;
    }

    public Cell getCell(int col, int row) {
        return grid[col][row];
    }

    public Cell[][] getGrid() {
        return grid;
    }
}
