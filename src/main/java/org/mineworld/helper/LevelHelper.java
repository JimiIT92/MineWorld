package org.mineworld.helper;

import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

/**
 * Helper class for {@link Level Level}
 */
public final class LevelHelper {

    /**
     * Get a {@link Direction Random Horizontal Direction}
     *
     * @param random {@link RandomSource The Random reference}
     * @return {@link Direction The Random Horizontal Direction}
     */
    public static Direction getRandomHorizontalDirection(final RandomSource random) {
        Direction direction;
        do {
            direction = Direction.getRandom(random);
        } while (!direction.getAxis().isHorizontal());
        return direction;
    }

}