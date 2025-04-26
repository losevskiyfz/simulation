package com.github.losevskiyfz.action;

import com.github.losevskiyfz.domain.Entity;
import com.github.losevskiyfz.map.Map;
import com.github.losevskiyfz.map.Point;
import com.github.losevskiyfz.provider.EntityProvider;

public class MaintainAction implements Action<Entity> {
    private final EntityProvider<Entity> randomEntityProvider;

    public MaintainAction(EntityProvider<Entity> randomEntityProvider) {
        this.randomEntityProvider = randomEntityProvider;
    }

    @Override
    public void act(Map<Entity> map) {
        for (int i = 0; i < map.cols(); i++) {
            for (int j = 0; j < map.rows(); j++) {
                Point p = new Point(i, j);
                if (map.get(p).isPassable())
                    map.put(new Point(i, j), randomEntityProvider.get());
            }
        }
    }
}
