package com.github.losevskiyfz.action;

import com.github.losevskiyfz.domain.Entity;
import com.github.losevskiyfz.map.Map;
import com.github.losevskiyfz.map.Point;
import com.github.losevskiyfz.provider.EntityProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MaintainAction implements Action<Entity> {
    private final EntityProvider<Entity> randomEntityProvider;
    private static final Logger LOG = LogManager.getLogger(MaintainAction.class);

    public MaintainAction(EntityProvider<Entity> randomEntityProvider) {
        this.randomEntityProvider = randomEntityProvider;
    }

    @Override
    public void act(Map<Entity> map) {
        LOG.info("Run maintain action.");
        for (int i = 0; i < map.cols(); i++) {
            for (int j = 0; j < map.rows(); j++) {
                Point p = new Point(i, j);
                if (map.get(p).isPassable())
                    map.put(new Point(i, j), randomEntityProvider.get());
            }
        }
    }
}
