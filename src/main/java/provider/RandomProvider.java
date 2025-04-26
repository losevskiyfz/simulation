package provider;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 * <code>RandomProvider</code> generates a random value based on the provided probabilities.
 * <code>HashMap</code> is used under the hood so always define <code>equals()</code> and <code>hashCode()</code>
 * for provided <code>T</code> class, otherwise you might encounter unpredictable behavior.
 * </p>
 * <p>
 * The probability is calculated for a given entity by formula:
 * <p>probability = entity rating / sum of all ratings</p>
 * </p>
 *
 * @author Fyodor Losevksiy
 * @version 1.0
 */
public class RandomProvider<T> {
    private final Map<T, Integer> ratings = new HashMap<>();
    private final Random rand = new Random();

    public void putProbability(T entity, int rate) {
        if (rate <= 0) throw new IllegalArgumentException();
        ratings.put(entity, rate);
    }

    public T get() {
        if (ratings.isEmpty()) {
            throw new IllegalStateException();
        }
        long r = rand.nextLong(totalRating());
        long cumulative = 0;
        for (Map.Entry<T, Integer> entry : ratings.entrySet()) {
            cumulative += entry.getValue();
            if (r < cumulative) {
                return entry.getKey();
            }
        }
        throw new IllegalStateException();
    }

    //todo - may be optimized for large number of entities
    private long totalRating() {
        return ratings.values().stream().mapToLong(Integer::intValue).sum();
    }
}
