package org.mineworld.block;

import net.minecraft.core.BlockPos;
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
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlockEntityTypes;
import org.mineworld.entity.block.MWCampfireBlockEntity;

import javax.annotation.Nullable;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link CampfireBlock campfire block}
 */
public class MWCampfireBlock extends CampfireBlock {

    /**
     * Constructor. Set the block properties
     *
     * @param fireDamage {@link Integer The campfire fire damage}
     * @param lightLevel {@link Integer The lit campfire light level}
     */
    public MWCampfireBlock(final int fireDamage, final int lightLevel) {
        super(false, fireDamage, BlockBehaviour.Properties.of().mapColor(MapColor.PODZOL).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).lightLevel(state -> lightLevel).noOcclusion().ignitedByLava());
    }

    /**
     * Get the {@link BlockEntity campfire block entity}
     *
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     * @return {@link BlockEntity The campfire block entity}
     */
    @Override
    public BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        return new MWCampfireBlockEntity(blockPos, blockState);
    }

    /**
     * Get the {@link BlockEntityTicker block ticker}
     *
     * @param level {@link Level The level reference}
     * @param state {@link BlockState The current BlockState}
     * @param type {@link BlockEntityType The block entity type}
     * @return {@link BlockEntityTicker The block ticker}
     * @param <T> {@link T The block entity type}
     */
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(final Level level, final @NotNull BlockState state, final @NotNull BlockEntityType<T> type) {
        final BlockEntityType<MWCampfireBlockEntity> blockEntityType = MWBlockEntityTypes.CAMPFIRE.get();
        if (level.isClientSide) {
            return state.getValue(LIT) ? createTickerHelper(type,MWBlockEntityTypes.CAMPFIRE.get(), CampfireBlockEntity::particleTick) : null;
        }
        return state.getValue(LIT) ? createTickerHelper(type,MWBlockEntityTypes.CAMPFIRE.get(), CampfireBlockEntity::cookTick) : createTickerHelper(type,MWBlockEntityTypes.CAMPFIRE.get(), CampfireBlockEntity::cooldownTick);
    }

}