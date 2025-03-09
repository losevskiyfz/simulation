package com.github.losevskiyfz.route;

import com.github.losevskiyfz.map.Cell;

import java.util.List;

public interface PathFinder {
    List<Cell> findPath(Cell[][] grid, Cell start, Class<?> target);
}
