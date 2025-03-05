package com.github.losevskiyfz.map;

public class Map {

    private Cell[][] cells;

    public Map(Integer cols, Integer rows) {
        cells = new Cell[cols][rows];
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                cells[x][y] = new Cell();
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
