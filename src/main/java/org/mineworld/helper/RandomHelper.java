package org.mineworld.helper;

import net.minecraft.util.RandomSource;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Collection;
import java.util.Optional;

public final class RandomHelper {

    /**
     * {@link RandomSource The Random Source reference}
     */
    private static final RandomSource randomSource = RandomSource.create();

    /**
     * Get the {@link RandomSource Random Source reference}
     *
     * @return {@link RandomSource The Random Source reference}
     */
    public static RandomSource getRandom() {
        return randomSource;
    }

    /**
     * Check if a random value corresponds to half of the provided {@link Integer value}
     *
     * @param maxValue The {@link Integer maximum value to choose from}
     * @return {@link Boolean True if the value if half the provided one}
     */
    public static boolean nextInt(final int maxValue) {
        return nextInt(Math.min(0, maxValue), Math.max(0, maxValue));
    }

    /**
     * Check if a random value is between the provided {@link Integer minimum} and {@link Integer maximum} values
     *
     * @param minValue The {@link Integer minimum value to choose from}
     * @param maxValue The {@link Integer maximum value to choose from}
     * @return {@link Boolean True if the value if between the minimum and the maximum value}
     */
    public static boolean nextInt(final int minValue, final int maxValue) {
        return randomSource.nextInt(minValue, maxValue) == (maxValue / 2);
    }

    /**
     * Get a {@link T random value} from a Collection
     *
     * @param collection {@link Collection<T> The Collection to get the value from}
     * @return {@link T A random value from the Collection}
     * @param <T> {@link T The Collection Type}
     */
    public static <T> Optional<T> randomValue(final @NonNull Collection<T> collection) {
        return collection.stream().skip((int) (collection.size() * Math.random())).findAny();
    }

}