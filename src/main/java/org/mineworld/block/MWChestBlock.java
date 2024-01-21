package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
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
 * Implementation class for a {@link MineWorld MineWorld} {@link ChestBlock Trapped Chest Block}
 */
public class MWChestBlock extends ChestBlock {

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
    public MWChestBlock(final Supplier<WoodType> woodTypeSupplier, final Supplier<BlockEntityType<? extends ChestBlockEntity>> blockEntityTypeSupplier, final FeatureFlag... featureFlags) {
        super(PropertyHelper.copy(Blocks.CHEST, featureFlags), blockEntityTypeSupplier);
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
            return new SpruceChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(WoodType.BIRCH)) {
            return new BirchChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(WoodType.JUNGLE)) {
            return new JungleChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(WoodType.ACACIA)) {
            return new AcaciaChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(WoodType.DARK_OAK)) {
            return new DarkOakChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(WoodType.MANGROVE)) {
            return new MangroveChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(WoodType.CHERRY)) {
            return new CherryChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(WoodType.BAMBOO)) {
            return new BambooChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(WoodType.CRIMSON)) {
            return new CrimsonChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(WoodType.WARPED)) {
            return new WarpedChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(MWWoodTypes.APPLE.get())) {
            return new AppleChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(MWWoodTypes.PALM.get())) {
            return new PalmChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(MWWoodTypes.DEAD.get())) {
            return new DeadChestBlockEntity(blockPos, blockState);
        }
        if(woodType.equals(MWWoodTypes.SCULK.get())) {
            return new SculkChestBlockEntity(blockPos, blockState);
        }
        return new ChestBlockEntity(blockPos, blockState);
    }

}