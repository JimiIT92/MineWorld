package org.mineworld.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
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
     * and returns it casted to int.
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

}