package org.mineworld.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;

/**
 * Helper methods for {@link Level level}
 */
public final class LevelHelper {

    /**
     * Get the {@link BlockState block state} reaction when pushed by pistons
     *
     * @param blockState {@link BlockState The block state to check}
     * @return {@link PushReaction The piston push reaction}
     */
    public static PushReaction getPushReaction(final BlockState blockState) {
        return blockState.is(Tags.Blocks.OBSIDIAN) || blockState.is(Blocks.REINFORCED_DEEPSLATE) ? PushReaction.BLOCK : blockState.getPistonPushReaction();
    }

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
     * and returns it cast to int.
     *
     * @param coordinate {@link Double The coordinate to adjust}
     * @return {@link Integer The adjusted coordinate}
     */
    private static int adjustCoordinate(double coordinate) {
        return (int)(coordinate >= 0 ? coordinate : (coordinate - 0.5D));
    }

    /**
     * Check if a location is underwater
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The block pos to check}
     * @return {@link Boolean True if the block pos is underwater}
     */
    public static boolean isUnderwater(final Level level, final BlockPos blockPos) {
        return level.getFluidState(blockPos).is(Fluids.WATER) || level.getFluidState(blockPos.above()).is(Fluids.WATER)
                || level.getFluidState(blockPos.below()).is(Fluids.WATER) || level.getFluidState(blockPos.north()).is(Fluids.WATER)
                || level.getFluidState(blockPos.south()).is(Fluids.WATER) || level.getFluidState(blockPos.east()).is(Fluids.WATER)
                || level.getFluidState(blockPos.west()).is(Fluids.WATER);
    }

    /**
     * Get th{@link BlockPos block pos} offset by the specified {@link Direction direction}
     *
     * @param blockPos {@link BlockPos The current block pos}
     * @param direction {@link Direction The offset direction}
     * @return {@link BlockPos The offset block pos}
     */
    public static BlockPos offset(final BlockPos blockPos, final Direction direction) {
        return blockPos.offset(direction.getStepX(), direction.getStepY(), direction.getStepZ());
    }

    /**
     * Check if a block face is solid
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param direction {@link Direction The direction to check}
     * @return {@link Boolean True if the block face is solid}
     */
    public static boolean isFaceSolid(final Level level, final BlockPos blockPos, final Direction direction) {
        return level.getBlockState(blockPos).isFaceSturdy(level, blockPos, direction.getOpposite());
    }

    /**
     * Check if a {@link Block block} can be placed at the given {@link BlockPos location}
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @return {@link Boolean True if there isn't a block or is replaceable}
     */
    public static boolean canPlace(final Level level, final BlockPos blockPos) {
        final BlockState blockState = level.getBlockState(blockPos);
        return blockState.isAir() || blockState.canBeReplaced();
    }

    /**
     * Get a {@link Direction random horizontal direction}
     *
     * @param random {@link RandomSource The random reference}
     * @return {@link Direction The random horizontal direction}
     */
    public static Direction getRandomHorizontalDirection(final RandomSource random) {
        Direction direction;
        do {
            direction = Direction.getRandom(random);
        } while (!direction.getAxis().isHorizontal());
        return direction;
    }

}