package org.mineworld.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.common.Tags;

/**
 * Helper class for {@link Block Blocks}
 */
public final class BlockHelper {

    /**
     * Get the {@link PushReaction pistons push reaction} for the provided {@link BlockState Block State}
     *
     * @param blockState {@link BlockState The Block State to get the push reaction}
     * @return {@link PushReaction The piston push reaction}
     */
    public static PushReaction getPushReaction(final BlockState blockState) {
        return blockState.is(Tags.Blocks.OBSIDIAN) || blockState.is(Blocks.REINFORCED_DEEPSLATE) ? PushReaction.BLOCK : blockState.getPistonPushReaction();
    }

    /**
     * Check if a Block can catch fire
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Boolean True if the source Block is flammable}
     */
    public static boolean isFlammable(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return blockState.isFlammable(blockGetter, blockPos, direction) && !blockState.isFireSource((LevelReader) blockGetter, blockPos, direction);
    }

}