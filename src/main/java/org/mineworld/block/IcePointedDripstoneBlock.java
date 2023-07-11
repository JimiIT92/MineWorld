package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;

import java.util.function.Supplier;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link MWPointedDripstoneBlock ice pointed dripstone block}
 */
public class IcePointedDripstoneBlock extends MWPointedDripstoneBlock {

    /**
     * Supplier constructor. Sets the {@link Block pointed dripstone} properties
     *
     * @param blockSupplier {@link Supplier<Block> The supplier for the dripstone source block}
     */
    public IcePointedDripstoneBlock(Supplier<Block> blockSupplier) {
        super(blockSupplier.get());
    }

    /**
     * Constructor. Sets the {@link Block pointed dripstone} properties
     *
     * @param block {@link Block The dripstone source block}
     */
    public IcePointedDripstoneBlock(Block block) {
        super(block);
    }

    /**
     * Don't drop the {@link Block falling dripstones} after they fall
     *
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param fallingBlockEntity {@link FallingBlockEntity The falling dripstone entity}
     */
    @Override
    public void onBrokenAfterFall(@NotNull Level level, @NotNull BlockPos pos, FallingBlockEntity fallingBlockEntity) {

    }

    /**
     * Get the {@link FallingBlockEntity falling dripstone entity}
     *
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param state {@link BlockState The current block state}
     * @return {@link FallingBlockEntity The falling dripstone entity}
     */
    @Override
    public FallingBlockEntity getFallingBlockEntity(Level level, BlockPos pos, BlockState state) {
        FallingBlockEntity fallingDripstone = super.getFallingBlockEntity(level, pos, state);
        fallingDripstone.dropItem = false;
        return fallingDripstone;
    }
}