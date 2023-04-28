package org.mineworld.helper;

import org.mineworld.MineWorld;

import java.util.Optional;
import java.util.Set;

/**
 * Helper methods for splash screens
 */
public final class SplashScreenHelper {

    /**
     * The {@link MineWorld MineWorld} {@link Set<String> splash texts}
     */
    private static final Set<String> splashTexts;

    /**
     * Get a {@link String random splash text}
     *
     * @return {@link String A random splash text}
     */
    public static Optional<String> getSplashText() {
        if(!RandomHelper.choose()) {
            return Optional.empty();
        }
        return RandomHelper.randomValue(splashTexts);
    }

    /**
     * Load the {@link MineWorld MineWorld} {@link Set<String> splash texts}
     *
     * @return {@link Set<String> The MineWorld splash texts}
     */
    private static Set<String> loadSplashTexts() {
        return Set.of(
                "Now with MineWorld blocks!",
                "Has hollow logs!"
        );
    }

    static {
        splashTexts = loadSplashTexts();
    }
}