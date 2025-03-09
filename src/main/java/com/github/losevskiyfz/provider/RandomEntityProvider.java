package com.github.losevskiyfz.provider;

import com.github.losevskiyfz.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class RandomEntityProvider {
    private final Logger log = LoggerFactory.getLogger(RandomEntityProvider.class);
    private double totalProbability = 0.0;
    private NavigableMap<Double,Class<? extends Entity>> entityProbability = new TreeMap<>();
    private static final Random RANDOM = new Random();

    public RandomEntityProvider() {
        addEntity(Grass.class, 0.01);
        addEntity(Herbivore.class, 0.005);
        addEntity(Predator.class, 0.001);
        addEntity(Tree.class, 0.001);
        addEntity(Rock.class, 0.001);
        addEntity(EmptyEntity.class, 0.982);
        entityProbability = Collections.unmodifiableNavigableMap(entityProbability);
    }

    private void addEntity(Class<? extends Entity> entity, double probability) {
        if (probability <= 0) throw new IllegalArgumentException("Probability must be positive.");
        totalProbability += probability;
        entityProbability.put(totalProbability, entity);
    }

    public Entity getRandomEntity() {
        double value = RANDOM.nextDouble() * totalProbability;
        try {
            return entityProbability.ceilingEntry(value)
                    .getValue()
                    .getDeclaredConstructor()
                    .newInstance();
        } catch (Exception e) {
            log.error("EmptyEntity will be returned.\n{}", e.getMessage());
            return new EmptyEntity();
        }
    }
}