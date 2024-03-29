package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlockEntityTypes;
import org.mineworld.entity.block.MWBeehiveBlockEntity;
import org.mineworld.helper.BlockHelper;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.helper.ResourceHelper;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link BeehiveBlock Beehive Block}
 */
public class MWBeehiveBlock extends BeehiveBlock {

    /**
     * The {@link WoodType Beehive Wood Type Supplier}
     */
    private final Supplier<WoodType> woodTypeSupplier;
    /**
     * Constructor. Set the {@link Properties Block Properties}
     *
     * @param woodTypeSupplier {@link Supplier<WoodType> The Supplier for the Wood Type this Beehive is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public MWBeehiveBlock(final Supplier<WoodType> woodTypeSupplier, final FeatureFlag... featureFlags) {
        super(PropertyHelper.copy(Blocks.BEEHIVE, featureFlags).mapColor(ResourceHelper.woodColor(woodTypeSupplier.get()))
                .instrument(NoteBlockInstrument.BASS).strength(0.6F)
                .sound(woodTypeSupplier.get().soundType()).ignitedByLava()
        );
        this.woodTypeSupplier = woodTypeSupplier;
    }

    /**
     * Create the {@link MWBeehiveBlock Beehive Block Entity}
     *
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @return {@link BlockEntity The Beehive Block Entity}
     */
    @Override
    public BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        return new MWBeehiveBlockEntity(blockPos, blockState);
    }

    /**
     * Get the related ticker for this block
     *
     * @param level {@link Level The level reference}
     * @param blockState {@link BlockState The current Block State}
     * @param blockEntityType {@link BlockEntityType The Block Entity Type}
     * @return {@link BlockEntityTicker The Block Entity Ticker}
     * @param <T> {@link T The Block Entity Type}
     */
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(final Level level, final @NotNull BlockState blockState, final @NotNull BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : createTickerHelper(blockEntityType, MWBlockEntityTypes.BEEHIVE.get(), MWBeehiveBlockEntity::serverTick);
    }

    /**
     * Check if the Block can catch fire
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Boolean True if the source Block is flammable}
     */
    @Override
    public boolean isFlammable(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return true;
    }

    /**
     * Get the Block {@link Integer flammability value}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Integer 5}
     */
    @Override
    public int getFlammability(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return BlockHelper.isFlammable(woodTypeSupplier.get()) ? 5 : 0;
    }

    /**
     * Get the Block {@link Integer fire spread speed value}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Integer 20}
     */
    @Override
    public int getFireSpreadSpeed(final BlockState blockState,final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return BlockHelper.isFlammable(woodTypeSupplier.get()) ? 20 : 0;
    }
}