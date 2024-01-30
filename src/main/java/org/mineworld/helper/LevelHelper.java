package org.mineworld.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;

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

    /**
     * Check if a {@link BlockPos location} is underwater
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Boolean True if the location is underwater}
     */
    public static boolean isUnderwater(final Level level, final BlockPos blockPos) {
        return level.getFluidState(blockPos).is(Fluids.WATER) || level.getFluidState(blockPos.above()).is(Fluids.WATER)
                || level.getFluidState(blockPos.below()).is(Fluids.WATER) || level.getFluidState(blockPos.north()).is(Fluids.WATER)
                || level.getFluidState(blockPos.south()).is(Fluids.WATER) || level.getFluidState(blockPos.east()).is(Fluids.WATER)
                || level.getFluidState(blockPos.west()).is(Fluids.WATER);
    }

    /**
     * Get the {@link BlockPos Block Pos} offset by the specified {@link Direction Direction}
     *
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The offset direction}
     * @return {@link BlockPos The offset Block Pos}
     */
    public static BlockPos offset(final BlockPos blockPos, final Direction direction) {
        return blockPos.offset(direction.getStepX(), direction.getStepY(), direction.getStepZ());
    }

    /**
     * Check if a block face is solid
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction to check}
     * @return {@link Boolean True if the block face is solid}
     */
    public static boolean isFaceSolid(final Level level, final BlockPos blockPos, final Direction direction) {
        return level.getBlockState(blockPos).isFaceSturdy(level, blockPos, direction.getOpposite());
    }

}