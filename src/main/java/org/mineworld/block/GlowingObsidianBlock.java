package org.mineworld.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;
import org.mineworld.helper.PropertyHelper;

/**
 * Implementation class for the {@link Block glowing obsidian block}
 */
public class GlowingObsidianBlock extends Block {

    /**
     * Constructor. Set the block properties
     */
    public GlowingObsidianBlock() {
        super(PropertyHelper.copyFromBlock(Blocks.OBSIDIAN).lightLevel(state -> 15));
    }

    /**
     * Get the {@link PushReaction block push reaction} when moved by pistons
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link PushReaction#BLOCK Block push reaction}
     */
    @Override
    public @NotNull PushReaction getPistonPushReaction(@NotNull BlockState blockState) {
        return PushReaction.BLOCK;
    }

}