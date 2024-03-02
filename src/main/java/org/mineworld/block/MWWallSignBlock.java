package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.entity.block.MWSignBlockEntity;
import org.mineworld.helper.PropertyHelper;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link WallSignBlock Wall Sign Block}
 */
public class MWWallSignBlock extends WallSignBlock {

    /**
     * Constructor. Set the {@link Properties Block Properties}
     *
     * @param woodTypeSupplier {@link Supplier<WoodType> The Supplier for the Wood Type this Sign is based on}
     * @param signSupplier {@link Supplier<Block> The Supplier for the Standing Sign this Wall Sign is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public MWWallSignBlock(final Supplier<WoodType> woodTypeSupplier, final Supplier<Block> signSupplier, final FeatureFlag... featureFlags) {
        super(PropertyHelper.copy(Blocks.OAK_WALL_SIGN, featureFlags).lootFrom(signSupplier), woodTypeSupplier.get());
    }

    /**
     * Create the {@link MWSignBlockEntity Sign Block Entity}
     *
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @return {@link BlockEntity The Sign Block Entity}
     */
    @Override
    public BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        return new MWSignBlockEntity(blockPos, blockState);
    }

}