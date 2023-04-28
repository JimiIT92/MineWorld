package org.mineworld.helper;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Collection;
import java.util.Optional;
import java.util.Random;

/**
 * Helper methods for {@link Random random values}
 */
public final class RandomHelper {

    /**
     * Get a {@link Random random instance}
     *
     * @return {@link Random The random instance}
     */
    public static Random getRandom() {
        return new Random();
    }

    /**
     * Get a {@link Integer random integer} between 0 and the specified value
     *
     * @param max {@link Integer The max value}
     *
     * @return {@link Integer A random integer}
     */
    public static int range(final int max) {
        return range(0, max);
    }

    /**
     * Get a {@link Integer random integer} between the specified values
     *
     * @param min {@link Integer The min value}
     * @param max {@link Integer The max value}
     * @return {@link Integer A random integer}
     */
    public static int range(final int min, final int max) {
        return getRandom().nextInt(min, max);
    }

    /**
     * Check if the middle value between 0 and the specified value
     * has been randomly chosen from a range
     *
     * @param max {@link Integer The max value}
     * @return {@link Boolean True if the chosen value is the middle one}
     */
    public static boolean choose(final int max) {
        return choose(0, max);
    }

    /**
     * Check if the middle value between the specified values has been
     * randomly chosen from a range
     *
     * @param min {@link Integer The min value}
     * @param max {@link Integer The max value}
     * @return {@link Boolean True if the chosen value is the middle one}
     */
    public static boolean choose(final int min, final int max) {
        return choose(min, max, max / 2);
    }

    /**
     * Check if the specified value between the range has been randomly chosen
     *
     * @param min {@link Integer The min value}
     * @param max {@link Integer The max value}
     * @param choice {@link Integer The value to check}
     * @return {@link Boolean True if the chosen value is specified one}
     */
    public static boolean choose(final int min, final int max, final int choice) {
        return getRandom().nextInt(min, max) == choice;
    }

    /**
     * Check if the chosen boolean value is true
     *
     * @return {@link Boolean The chosen valuye}
     */
    public static boolean choose() {
        return getRandom().nextBoolean();
    }

    /**
     * Get a {@link T random value} from a collection
     *
     * @param collection {@link Collection<T> The collection to get the value from}
     * @return {@link T A random value from the collection}
     * @param <T> {@link T The collection type}
     */
    public static <T> Optional<T> randomValue(final @NonNull Collection<T> collection) {
        return collection.stream().skip((int) (collection.size() * Math.random())).findAny();
    }

}