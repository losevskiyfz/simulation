package com.github.losevskiyfz.route;

import com.github.losevskiyfz.map.Cell;

import java.util.*;

public class AStarPathFinder implements PathFinder {

    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public List<Cell> findPath(Cell[][] grid, Cell start, Class<?> target) {
        List<Cell> goals = getGoals(grid, target);
        if (goals.isEmpty()) {
            return Collections.emptyList();
        }
        int startX = start.x;
        int startY = start.y;
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<String> closedSet = new HashSet<>();
        openSet.add(new Node(startX, startY, 0, minManhattan(startX, startY, goals), null));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            for (Cell goal : goals) {
                if (current.x == goal.x && current.y == goal.y) {
                    return reconstructPath(current, grid);
                }
            }

            closedSet.add(current.x + "," + current.y);

            for (int[] dir : DIRECTIONS) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];

                if (isValid(grid, newX, newY, target) && !closedSet.contains(newX + "," + newY)) {
                    int newG = current.g + 1;
                    Node neighbor = new Node(newX, newY, newG, minManhattan(newX, newY, goals), current);
                    openSet.add(neighbor);
                }
            }
        }
        return Collections.emptyList();
    }

    private static int minManhattan(int x, int y, List<Cell> goals) {
        return goals.stream().mapToInt(goal -> Math.abs(x - goal.x) + Math.abs(y - goal.y)).min().orElse(Integer.MAX_VALUE);
    }

    private static boolean isValid(Cell[][] grid, int x, int y, Class<?> target) {
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && (grid[x][y].isFree() || target.isInstance(grid[x][y].getEntity()));
    }

    private static List<Cell> reconstructPath(Node node, Cell[][] grid) {
        List<Cell> path = new ArrayList<>();
        while (node != null) {
            path.add(grid[node.x][node.y]);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }

    private static List<Cell> getGoals(Cell[][] grid, Class<?> target) {
        List<Cell> goals = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (target.isInstance(grid[i][j].getEntity())) {
                    goals.add(grid[i][j]);
                }
            }
        }
        return goals;
    }

    private static class Node implements Comparable<Node> {
        int x, y, g, h;
        Node parent;

        Node(int x, int y, int g, int h, Node parent) {
            this.x = x;
            this.y = y;
            this.g = g;
            this.h = h;
            this.parent = parent;
        }

        int f() {
            return g + h;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.f(), other.f());
        }
    }
}

