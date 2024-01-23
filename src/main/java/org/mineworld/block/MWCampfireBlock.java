package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlockEntityTypes;
import org.mineworld.entity.block.MWCampfireBlockEntity;
import org.mineworld.helper.PropertyHelper;

/**
 * {@link MineWorld MineWorld} {@link CampfireBlock Campfire Block}
 */
public class MWCampfireBlock extends CampfireBlock {

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param fireType {@link MWFireBlock.MWFireType The Fire Type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public MWCampfireBlock(final MWFireBlock.MWFireType fireType, final FeatureFlag... featureFlags) {
        super(false, fireType.campfireDamage(), PropertyHelper.block(MapColor.PODZOL,2.0F, 2.0F, false, SoundType.WOOD, featureFlags).instrument(NoteBlockInstrument.BASS).lightLevel(state -> fireType.lightLevel()).noOcclusion().ignitedByLava());
    }

    /**
     * Create the {@link MWCampfireBlockEntity Campfire Block Entity}
     *
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @return {@link BlockEntity The Campfire Block Entity}
     */
    @Override
    public BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        return new MWCampfireBlockEntity(blockPos, blockState);
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
        final BlockEntityType<CampfireBlockEntity> campfireBlockEntityType = MWBlockEntityTypes.CAMPFIRE.get();
        if (level.isClientSide) {
            return blockState.getValue(LIT) ? createTickerHelper(blockEntityType,MWBlockEntityTypes.CAMPFIRE.get(), CampfireBlockEntity::particleTick) : null;
        }
        return blockState.getValue(LIT) ? createTickerHelper(blockEntityType, MWBlockEntityTypes.CAMPFIRE.get(), CampfireBlockEntity::cookTick) : createTickerHelper(blockEntityType,MWBlockEntityTypes.CAMPFIRE.get(), CampfireBlockEntity::cooldownTick);
    }

}