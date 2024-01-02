package org.mineworld.helper;

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

}