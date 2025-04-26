package com.github.losevskiyfz.path;

import com.github.losevskiyfz.map.Map;
import com.github.losevskiyfz.map.Point;

import java.util.List;

public interface PathFinder<T extends Passable> {
    List<Point> findPath(Map<T> map, Point start, Class<? extends T> target);
}
