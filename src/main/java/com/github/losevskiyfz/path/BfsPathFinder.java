package com.github.losevskiyfz.path;

import com.github.losevskiyfz.map.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class BfsPathFinder<T extends Passable> implements PathFinder<T> {
    private static final Logger LOG = LogManager.getLogger(BfsPathFinder.class);

    @Override
    public List<Point> findPath(com.github.losevskiyfz.map.Map<T> map, Point start, Class<? extends T> target) {
        LOG.info("Finding path with BFS from {} to {}", start, target);
        Queue<Node> queue = new LinkedList<>();
        Set<Point> visited = new HashSet<>();

        if (target.isInstance(map.get(start))) return List.of(start);

        queue.add(new Node(start, null));
        visited.add(start);
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            for (Point neighbour : neighbourPoints(map, n.point)) {
                if (visited.contains(neighbour)) continue;
                Node neighbourNode = new Node(neighbour, n);
                if (target.isInstance(map.get(neighbour))) {
                    return constructPathToRoot(neighbourNode);
                }
                if (map.get(neighbour).isPassable()) {
                    visited.add(neighbour);
                    queue.add(neighbourNode);
                }
            }
        }
        return List.of();
    }

    private List<Point> neighbourPoints(com.github.losevskiyfz.map.Map<T> map, Point point) {
        List<Point> neighbours = new LinkedList<>();
        if (point.x() + 1 < map.cols()) {
            neighbours.add(new Point(point.x() + 1, point.y()));
        }
        if ((point.x() - 1) >= 0) {
            neighbours.add(new Point(point.x() - 1, point.y()));
        }
        if (point.y() + 1 < map.rows()) {
            neighbours.add(new Point(point.x(), point.y() + 1));
        }
        if ((point.y() - 1) >= 0) {
            neighbours.add(new Point(point.x(), point.y() - 1));
        }
        return neighbours;
    }

    private List<Point> constructPathToRoot(Node node) {
        List<Point> path = new LinkedList<>();
        Node current = node;
        while (current != null) {
            path.addFirst(current.point);
            current = current.parent;
        }
        return path;
    }

    static class Node{
        Point point;
        Node parent;

        Node(Point point, Node parent) {
            this.point = point;
            this.parent = parent;
        }
    }
}
