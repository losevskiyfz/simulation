package com.github.losevskiyfz.route;

import com.github.losevskiyfz.map.Cell;

import java.util.*;

public class BfsPathFinder implements PathFinder {
    public List<Cell> findPath(Cell[][] grid, Cell searchFrom, Class<?> target) {
        Cell start = grid[searchFrom.x][searchFrom.y];

        Queue<Cell> queue = new LinkedList<>();
        Map<Cell, Cell> parentMap = new HashMap<>();
        Set<Cell> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Cell current = queue.poll();

            if (target.isInstance(current.getEntity())) {
                List<Cell> foundPath = new LinkedList<>();
                while (current != null) {
                    foundPath.add(current);
                    current = parentMap.get(current);
                }
                Collections.reverse(foundPath);
                return foundPath;
            }

            for (Cell neighbor : getNeighbours(grid, current)) {
                if (!neighbor.isFree() && !target.isInstance(neighbor.getEntity())) {
                    continue;
                }
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }
        return Collections.emptyList();
    }

    public static List<Cell> getNeighbours(Cell[][] grid, Cell cell) {
        List<Cell> neighbours = new ArrayList<>();
        if (cell.x + 1 < grid.length) {
            neighbours.add(grid[cell.x + 1][cell.y]);
        }
        if ((cell.x - 1) >= 0) {
            neighbours.add(grid[cell.x - 1][cell.y]);
        }
        if (cell.y + 1 < grid[0].length) {
            neighbours.add(grid[cell.x][cell.y + 1]);
        }
        if ((cell.y - 1) >= 0) {
            neighbours.add(grid[cell.x][cell.y - 1]);
        }
        return neighbours;
    }
}
