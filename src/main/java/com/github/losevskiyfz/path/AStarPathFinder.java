package com.github.losevskiyfz.path;

import com.github.losevskiyfz.domain.Predator;
import com.github.losevskiyfz.map.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class AStarPathFinder<T extends Passable> implements PathFinder<T> {
    private static final Logger LOG = LogManager.getLogger(AStarPathFinder.class);

    @Override
    public List<Point> findPath(com.github.losevskiyfz.map.Map<T> map, Point start, Class<? extends T> target) {
        LOG.info("A* pathfinding started. Start: {}. Target: {}", start, target);

        List<Point> goals = goals(map, target);
        Queue<Node> queue = new PriorityQueue<>();
        Set<Point> visited = new HashSet<>();

        if (goals.contains(start)) return List.of(start);
        if (goals.isEmpty()) return List.of();

        queue.add(new Node(start, 0, minManhattan(start, goals), null));
        visited.add(start);
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            for (Point neighbour : neighbourPoints(map, n.point)) {
                if (visited.contains(neighbour)) continue;
                Node neighbourNode = new Node(neighbour, n.g + 1, minManhattan(neighbour, goals), n);
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

    private List<Point> constructPathToRoot(Node node) {
        List<Point> path = new LinkedList<>();
        Node current = node;
        while (current != null) {
            path.addFirst(current.point);
            current = current.parent;
        }
        return path;
    }

    private List<Point> goals(com.github.losevskiyfz.map.Map<T> map, Class<? extends T> target) {
        List<Point> targets = new LinkedList<>();
        for (int i = 0; i < map.cols(); i++) {
            for (int j = 0; j < map.rows(); j++) {
                if (target.isInstance(map.get(new Point(i, j)))) {
                    targets.add(new Point(i, j));
                }
            }
        }
        return targets;
    }

    private int minManhattan(Point p, List<Point> goals) {
        return goals.stream()
                .mapToInt(goal -> manhattan(p, goal))
                .min()
                .orElse(Integer.MAX_VALUE);
    }

    private int manhattan(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    static class Node implements Comparable<Node> {
        Point point;
        int g;
        int h;
        Node parent;

        Node(Point point, int g, int h, Node parent) {
            this.point = point;
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