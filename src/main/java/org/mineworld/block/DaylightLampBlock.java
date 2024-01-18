package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlockEntityTypes;
import org.mineworld.entity.block.DaylightLampBlockEntity;
import org.mineworld.helper.PropertyHelper;

import javax.annotation.Nullable;

/**
 * {@link MineWorld MineWorld} Daylight Lamp Block
 */
public class DaylightLampBlock extends BaseEntityBlock {

    /**
     * {@link Boolean The lamp Lit property}
     */
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
    /**
     * {@link Boolean The lamp Inverted property}
     */
    public static final BooleanProperty INVERTED = BlockStateProperties.INVERTED;
    /**
     * {@link IntegerProperty THe lamp Power property}
     */
    public static final IntegerProperty POWER = BlockStateProperties.POWER;

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public DaylightLampBlock(final FeatureFlag... featureFlags) {
        super(PropertyHelper.copy(Blocks.REDSTONE_LAMP, featureFlags));
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, Boolean.FALSE).setValue(INVERTED, Boolean.FALSE).setValue(POWER, 0));
    }

    /**
     * Get the {@link BlockState Block State} after the block has been placed
     *
     * @param placeContext {@link BlockPlaceContext The block place context}
     * @return {@link BlockState The placed Block State}
     */
    @Nullable
    @Override
    public BlockState getStateForPlacement(final @NotNull BlockPlaceContext placeContext) {
        return this.defaultBlockState()
                .setValue(LIT, false)
                .setValue(POWER, 0);
    }

    /**
     * Interact with the Block
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link ServerLevel The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param player {@link Player The player who interacted with the Block}
     * @param hand {@link InteractionHand The hand the player has interacted with}
     * @param hitResult {@link BlockHitResult The hit result for the block interaction}
     * @return {@link InteractionResult The interaction result based on the Player's held Item}
     */
    @Override
    public @NotNull InteractionResult use(final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final Player player, final @NotNull InteractionHand hand, final @NotNull BlockHitResult hitResult) {
        if (player.mayBuild()) {
            if (level.isClientSide) {
                return InteractionResult.SUCCESS;
            } else {
                final BlockState invertedState = blockState.cycle(INVERTED);
                level.setBlock(blockPos, invertedState, 4);
                level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, invertedState));
                return InteractionResult.CONSUME;
            }
        }
        return super.use(blockState, level, blockPos, player, hand, hitResult);
    }

    /**
     * Ticks the Block
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link ServerLevel The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param randomSource {@link RandomSource The random reference}
     */
    @Override
    public void tick(final @NotNull BlockState blockState, final @NotNull ServerLevel level, final @NotNull BlockPos blockPos, final @NotNull RandomSource randomSource) {
        updateStatus(blockState, level, blockPos, randomSource);
    }

    /**
     * Cycle the lamp status based on time of the day
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param randomSource {@link RandomSource The random reference}
     */
    private static void updateStatus(final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull RandomSource randomSource) {
        updateSignalStrength(blockState, level, blockPos);
        if(shouldToggleDaylightLamp(blockState, level) || shouldToggleNightlightLamp(blockState, level)) {
            level.setBlock(blockPos, blockState.cycle(LIT), 2);
        }
    }

    /**
     * Check if the daylight lamp is inverted
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean True if the lamp is inverted}
     */
    private static boolean isNightlightLamp(final BlockState blockState) {
        return blockState.getValue(INVERTED);
    }

    /**
     * Check if the daylight lamp should be turned on or off
     * based on time of the day and if is already turned on or off
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link Level The level reference}
     * @return {@link Boolean True if is day and is turned off or is night and is turned on}
     */
    private static boolean shouldToggleDaylightLamp(final BlockState blockState, final Level level) {
        final boolean isDay = level.isDay();
        final boolean isLit = blockState.getValue(LIT);
        return !isNightlightLamp(blockState) && ((isDay && !isLit) || (!isDay && isLit));
    }

    /**
     * Check if the nightlight lamp should be turned on or off
     * based on time of the day and if is already turned on or off
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link Level The level reference}
     * @return {@link Boolean True if is night and is turned off or is day and is turned on}
     */
    private static boolean shouldToggleNightlightLamp(final BlockState blockState, final Level level) {
        final boolean isNight = level.isNight();
        final boolean isLit = blockState.getValue(LIT);
        return isNightlightLamp(blockState) && ((isNight && !isLit) || (!isNight && isLit));
    }

    /**
     * Create the {@link StateDefinition Block State definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The Block State builder}
     */
    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(LIT).add(INVERTED).add(POWER);
    }

    /**
     * Get the lamp signal strength based on time of the day
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the block is outputting power}
     * @return {@link Integer The block signal strength}
     */
    @Override
    public int getSignal(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull Direction direction) {
        return blockState.getValue(POWER);
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
     * Get the {@link Integer comparator signal} based on the daylight lamp status
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     */
    private static void updateSignalStrength(final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos) {
        int power = level.getBrightness(LightLayer.SKY, blockPos.above()) - level.getSkyDarken();
        float sunAngle = level.getSunAngle(1.0F);
        if (isNightlightLamp(blockState)) {
            power = 15 - power;
        } else if (power > 0) {
            float sunDegrees = sunAngle < (float)Math.PI ? 0.0F : ((float)Math.PI * 2F);
            sunAngle += (sunDegrees - sunAngle) * 0.2F;
            power = Math.round((float)power * Mth.cos(sunAngle));
        }

        power = Mth.clamp(power, 0, 15);
        if (blockState.getValue(POWER) != power) {
            level.setBlock(blockPos, blockState.setValue(POWER, power), 3);
        }
    }

    /**
     * Create the {@link DaylightLampBlockEntity Daylight Lamp Block Entity}
     *
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @return {@link BlockEntity The Daylight Lamp Block Entity}
     */
    @Override
    public BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        return new DaylightLampBlockEntity(blockPos, blockState);
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
        return !level.isClientSide && level.dimensionType().hasSkyLight() ? createTickerHelper(blockEntityType, MWBlockEntityTypes.DAYLIGHT_LAMP.get(), DaylightLampBlock::tickEntity) : null;
    }

    /**
     * Tick the daylight lamp
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @param blockEntity {@link DaylightLampBlockEntity The Daylight Lamp Block Entity}
     */
    private static void tickEntity(final Level level, final BlockPos blockPos, final BlockState blockState, final DaylightLampBlockEntity blockEntity) {
        if (level.getGameTime() % 20L == 0L) {
            updateStatus(blockState, level, blockPos, level.random);
        }
    }

    /**
     * Get the {@link RenderShape render shape} for this block
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link RenderShape#MODEL Model render shape}
     */
    @Override
    public @NotNull RenderShape getRenderShape(final @NotNull BlockState blockState) {
        return RenderShape.MODEL;
    }

}