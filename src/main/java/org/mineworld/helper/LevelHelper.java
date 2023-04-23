package org.mineworld.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * Helper methods for {@link Level level}
 */
public final class LevelHelper {

    /**
     * Convert a {@link Vec3 coordinate vector} to a {@link BlockPos block pos}
     *
     * @param vec3 {@link Vec3 The coordinate vector}
     * @return {@link BlockPos The block pos}
     */
    public static BlockPos toBlockPos(final Vec3 vec3) {
        return new BlockPos(adjustCoordinate(vec3.x), adjustCoordinate(vec3.y), adjustCoordinate(vec3.z));
    }

    /**
     * Adjust a {@link Double coordinate} for the {@link BlockPos block pos} conversion.
     * Specifically subtract 0.5 if the coordinate is negative, otherwise leaves it unchanged
     * and returns it casted to int.
     *
     * @param coordinate {@link Double The coordinate to adjust}
     * @return {@link Integer The adjusted coordinate}
     */
    private static int adjustCoordinate(double coordinate) {
        return (int)(coordinate >= 0 ? coordinate : (coordinate - 0.5D));
    }

}