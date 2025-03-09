package com.github.losevskiyfz.action;

import com.github.losevskiyfz.map.Cell;
import com.github.losevskiyfz.map.Map;
import com.github.losevskiyfz.provider.RandomEntityProvider;

public class MaintainAction implements Action {

    private final RandomEntityProvider
            randomEntityProvider = new RandomEntityProvider();

    @Override
    public void doAction(Map map) {
        Cell[][] cells = map.getGrid();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].isFree())
                    cells[i][j].setEntity(randomEntityProvider.getRandomEntity());
            }
        }
    }
}
