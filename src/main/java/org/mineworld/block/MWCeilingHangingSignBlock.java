package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.entity.block.MWHangingSignBlockEntity;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.helper.ResourceHelper;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link CeilingHangingSignBlock Hanging Sign Block}
 */
public class MWCeilingHangingSignBlock extends CeilingHangingSignBlock {

    /**
     * Constructor. Set the {@link Properties Block Properties}
     *
     * @param woodTypeSupplier {@link Supplier<WoodType> The Supplier for the Wood Type this Hanging Sign is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public MWCeilingHangingSignBlock(final Supplier<WoodType> woodTypeSupplier, final FeatureFlag... featureFlags) {
        super(PropertyHelper.copy(Blocks.OAK_HANGING_SIGN, featureFlags)
                .mapColor(ResourceHelper.woodColor(woodTypeSupplier.get()))
                .sound(woodTypeSupplier.get().soundType())
                .strength(1.0F)
                .noCollission(), woodTypeSupplier.get()
        );
    }

    /**
     * Create the {@link MWHangingSignBlockEntity Hanging Sign Block Entity}
     *
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @return {@link BlockEntity The Sign Block Entity}
     */
    @Override
    public BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        return new MWHangingSignBlockEntity(blockPos, blockState);
    }

}