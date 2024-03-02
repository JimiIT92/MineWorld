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