package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.world.worldgen.tree.PalmTreeGrower;

/**
 * Implementation class for a {@link MineWorld MineWorld} palm bush block
 */
public class PalmBushBlock extends TreeBushBlock {

    /**
     * Constructor. Set the {@link Properties block properties}
     */
    public PalmBushBlock() {
        super(PalmTreeGrower::new);
    }

    /**
     * Check if the bush can be placed on a certain block
     *
     * @param state {@link BlockState The block state where the bush is being placed on}
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @return {@link Boolean True if the bush can be placed on top of the provided block}
     */
    @Override
    protected boolean mayPlaceOn(final @NotNull BlockState state, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos) {
        return state.is(Tags.Blocks.SAND) || super.mayPlaceOn(state, blockGetter, blockPos);
    }
}