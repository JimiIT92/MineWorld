package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.entity.block.MWSkullBlockEntity;
import org.mineworld.helper.PropertyHelper;

import java.util.function.Supplier;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link WallSkullBlock Wall Skull Block}
 */
public class MWWallSkullBlock extends WallSkullBlock {

    /**
     * Constructor. Set the {@link SkullBlock.Type Skull Block Type}
     *
     * @param type {@link SkullBlock.Type The Skull Block Type}
     * @param skullBlockSupplier {@link Supplier<Block> The Supplier for the Block this Wall Skull is based on}
     */
    public MWWallSkullBlock(final SkullBlock.Type type, final Supplier<Block> skullBlockSupplier) {
        super(type, PropertyHelper.block(1.0F, false).lootFrom(skullBlockSupplier));
    }

    /**
     * Create the {@link MWSkullBlockEntity Skull Block Entity}
     *
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @return {@link BlockEntity The Skull Block Entity}
     */
    @Override
    public BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        return new MWSkullBlockEntity(blockPos, blockState);
    }

}