package com.github.losevskiyfz.map;

import com.github.losevskiyfz.provider.RandomEntityProvider;

public class Map {

    private Cell[][] cells;
    private final RandomEntityProvider randomEntityProvider = new RandomEntityProvider();

    public Map(Integer cols, Integer rows) {
        cells = new Cell[cols][rows];
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                cells[x][y] = new Cell();
                cells[x][y].setEntity(randomEntityProvider.getRandomEntity());
            }
        }
    }

    public int getCols() {
        return cells.length;
    }

    public int getRows() {
        return cells[0].length;
    }

    public Cell getCell(int col, int row) {
        return cells[col][row];
    }
}
