package org.mineworld.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;

/**
 * {@link MineWorld MineWorld} class for an unmovable Block
 */
public class UnmovableBlock extends Block {

    /**
     * Constructor. Set the Block properties
     *
     * @param properties {@link BlockBehaviour.Properties The Block properties}
     */
    public UnmovableBlock(final Properties properties) {
        super(properties);
    }

    /**
     * Get the {@link PushReaction push reaction} when this block is pushed by pistons
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link PushReaction#BLOCK Block push reaction}
     */
    public @NotNull PushReaction getPistonPushReaction(final @NotNull BlockState blockState) {
        return PushReaction.BLOCK;
    }

}