package com.github.losevskiyfz.route;

import com.github.losevskiyfz.map.Cell;

import java.util.List;

public class Router {
    public static List<Cell> findPath(PathFinder pathFinder, Cell[][] grid, Cell start, Class<?> target) {
        return pathFinder.findPath(grid, start, target);
    }
}
