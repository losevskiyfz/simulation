package com.github.losevskiyfz.path;

import com.github.losevskiyfz.map.Point;

import java.util.*;

public class BfsPathFinder<T extends Passable> implements PathFinder<T> {

    @Override
    public List<Point> findPath(com.github.losevskiyfz.map.Map<T> map, Point start, Class<? extends T> target) {
        Queue<Point> queue = new LinkedList<>();
        Set<Point> visited = new HashSet<>();
        Map<Point, Point> paths = new HashMap<>();

        if (!map.get(start).isPassable()) throw new IllegalArgumentException();
        queue.add(start);
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (Point neighbour : getNeighbourPoints(map, p)) {
                if (visited.contains(neighbour)) continue;
                if (map.get(neighbour).isPassable()) {
                    queue.add(neighbour);
                    visited.add(neighbour);
                    paths.put(neighbour, p);
                }
                if (target.isInstance(map.get(neighbour))) {
                    return constructPathToRoot(neighbour, paths);
                }
            }
        }
        return List.of();
    }

    private List<Point> getNeighbourPoints(com.github.losevskiyfz.map.Map<T> map, Point point) {
        List<Point> neighbours = new LinkedList<>();
        if (point.x + 1 < map.cols()) {
            neighbours.add(new Point(point.x + 1, point.y));
        }
        if ((point.x - 1) >= 0) {
            neighbours.add(new Point(point.x - 1, point.y));
        }
        if (point.y + 1 < map.rows()) {
            neighbours.add(new Point(point.x, point.y + 1));
        }
        if ((point.y - 1) >= 0) {
            neighbours.add(new Point(point.x, point.y - 1));
        }
        return neighbours;
    }

    private List<Point> constructPathToRoot(Point point, Map<Point, Point> paths) {
        List<Point> path = new LinkedList<>();
        Point current = point;
        while (current != null) {
            path.add(current);
            current = paths.get(current);
        }
        Collections.reverse(path);
        return path;
    }
}
