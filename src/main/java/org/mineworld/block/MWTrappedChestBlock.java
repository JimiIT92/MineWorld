package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.TrappedChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.TrappedChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWWoodTypes;
import org.mineworld.entity.block.chest.*;
import org.mineworld.helper.PropertyHelper;

import java.util.function.Supplier;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link TrappedChestBlock Trapped Chest Block}
 */
public class MWTrappedChestBlock extends ChestBlock {

    /**
     * {@link Supplier<WoodType> The Chest Wood Type Supplier}
     */
    private final Supplier<WoodType> woodTypeSupplier;

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param woodTypeSupplier {@link Supplier<WoodType> The Chest Wood Type Supplier}
     * @param blockEntityTypeSupplier {@link Supplier<BlockEntityType> The Chest Block Entity Type Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public MWTrappedChestBlock(final Supplier<WoodType> woodTypeSupplier, Supplier<BlockEntityType<? extends ChestBlockEntity>> blockEntityTypeSupplier, final FeatureFlag... featureFlags) {
        super(PropertyHelper.copy(Blocks.TRAPPED_CHEST, featureFlags).sound(woodTypeSupplier.get().soundType()), blockEntityTypeSupplier);
        this.woodTypeSupplier = woodTypeSupplier;
    }

    /**
     * Create the {@link ChestBlockEntity appropriate Chest Block Entity}
     * based on the {@link WoodType Chest Wood Type}
     *
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @return {@link BlockEntity The Chest Block Entity}
     */
    @Override
    public BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        return getBlockEntity(this.woodTypeSupplier.get(), blockPos, blockState);
    }

    /**
     * Get the {@link WoodType Chest Wood Type}
     *
     * @return {@link WoodType The Chest Wood Type}
     */
    public WoodType getWoodType() {
        return this.woodTypeSupplier.get();
    }

    /**
     * Get a {@link ChestBlockEntity Chest Block Entity} based on the provided {@link WoodType Wood Type}
     *
     * @param woodType {@link WoodType The Chest Wood Type}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @return {@link ChestBlockEntity The Chest Block Entity}
     */
    public static BlockEntity getBlockEntity(final WoodType woodType, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        if(woodType.equals(WoodType.SPRUCE)) {
            return new SpruceTrappedChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(WoodType.BIRCH)) {
            return new BirchTrappedChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(WoodType.JUNGLE)) {
            return new JungleTrappedChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(WoodType.ACACIA)) {
            return new AcaciaTrappedChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(WoodType.DARK_OAK)) {
            return new DarkOakTrappedChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(WoodType.MANGROVE)) {
            return new MangroveTrappedChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(WoodType.CHERRY)) {
            return new CherryTrappedChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(WoodType.BAMBOO)) {
            return new BambooTrappedChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(WoodType.CRIMSON)) {
            return new CrimsonTrappedChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(WoodType.WARPED)) {
            return new WarpedTrappedChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(MWWoodTypes.APPLE.get())) {
            return new AppleTrappedChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(MWWoodTypes.PALM.get())) {
            return new PalmTrappedChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(MWWoodTypes.DEAD.get())) {
            return new DeadTrappedChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(MWWoodTypes.SCULK.get())) {
            return new SculkTrappedChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(MWWoodTypes.ICE.get())) {
            return new IceTrappedChestBlockEntity(blockPos, blockState);
        }
        return new TrappedChestBlockEntity(blockPos, blockState);
    }

    /**
     * Get the {@link Stat<ResourceLocation> Open Chest Statistic}
     *
     * @return {@link Stats#TRIGGER_TRAPPED_CHEST The Trapped Chest Trigger Stat}
     */
    @Override
    protected @NotNull Stat<ResourceLocation> getOpenChestStat() {
        return Stats.CUSTOM.get(Stats.TRIGGER_TRAPPED_CHEST);
    }

    /**
     * Make the block output a comparator signal
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean#TRUE True}
     */
    @Override
    public boolean isSignalSource(final @NotNull BlockState blockState) {
        return true;
    }

    /**
     * Get the Chest signal strength based on how many players opened the Chest
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the block is outputting power}
     * @return {@link Integer The block signal strength}
     */
    @Override
    public int getSignal(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull Direction direction) {
        return Mth.clamp(ChestBlockEntity.getOpenCount(blockGetter, blockPos), 0, 15);
    }

    /**
     * Get the Chest direct signal strength
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the block is outputting power}
     * @return {@link Integer The block direct signal strength}
     */
    @Override
    public int getDirectSignal(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull Direction direction) {
        return direction == Direction.UP ? blockState.getSignal(blockGetter, blockPos, direction) : 0;
    }

}