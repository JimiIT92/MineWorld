package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.world.worldgen.tree.PalmTreeGrower;

/**
 * Implementation class for a {@link SaplingBlock palm sapling block}
 */
public class PalmSaplingBlock extends SaplingBlock {

    /**
     * Constructor. Set the block properties
     */
    public PalmSaplingBlock() {
        super(new PalmTreeGrower(), PropertyHelper.copyFromBlock(Blocks.JUNGLE_SAPLING));
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
    protected boolean mayPlaceOn(@NotNull BlockState state, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos) {
        return state.is(Tags.Blocks.SAND) || super.mayPlaceOn(state, blockGetter, blockPos);
    }

}