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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.core.MWBlockEntityTypes;
import org.mineworld.entity.block.DaylightLampBlockEntity;
import org.mineworld.helper.PropertyHelper;

/**
 * Implementation class for a day/night light lamp
 */
public class DaylightLampBlock extends BaseEntityBlock {

    /**
     * {@link Boolean Lit property}
     */
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
    /**
     * {@link Boolean Inverted property}
     */
    public static final BooleanProperty INVERTED = BlockStateProperties.INVERTED;
    /**
     * {@link IntegerProperty Power property}
     */
    public static final IntegerProperty POWER = BlockStateProperties.POWER;

    /**
     * Constructor. Set the {@link Block block properties}
     *
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     */
    public DaylightLampBlock(final FeatureFlag... featureFlags) {
        super(PropertyHelper.copyFromBlock(Blocks.REDSTONE_LAMP, featureFlags));
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, Boolean.FALSE).setValue(INVERTED, Boolean.FALSE).setValue(POWER, 0));
    }

    /**
     * Get the {@link BlockState block state} after the block has been placed
     *
     * @param placeContext {@link BlockPlaceContext The block place context}
     * @return {@link BlockState The placed block state}
     */
    @Nullable
    @Override
    public BlockState getStateForPlacement(final @NotNull BlockPlaceContext placeContext) {
        return this.defaultBlockState()
                .setValue(LIT, false)
                .setValue(POWER, 0);
    }

    /**
     * Change the mode on right click
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link Level The world reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param player {@link Player The player who right clicked the block}
     * @param hand {@link InteractionHand The hand the player has interacted with}
     * @param hitResult {@link BlockHitResult The hit result for the block interaction}
     * @return {@link InteractionResult The interaction result based on the id in the player's hand}
     */
    @Override
    public @NotNull InteractionResult use(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if (player.mayBuild()) {
            if (level.isClientSide) {
                return InteractionResult.SUCCESS;
            } else {
                BlockState blockstate = blockState.cycle(INVERTED);
                level.setBlock(blockPos, blockstate, 4);
                level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, blockstate));
                return InteractionResult.CONSUME;
            }
        }
        return super.use(blockState, level, blockPos, player, hand, hitResult);
    }

    /**
     * Tick the block to determine if it should be lit
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link ServerLevel The world reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param randomSource {@link RandomSource The random reference}
     */
    @Override
    public void tick(final @NotNull BlockState blockState, final @NotNull ServerLevel level, final @NotNull BlockPos blockPos, final @NotNull RandomSource randomSource) {
        updateStatus(blockState, level, blockPos, randomSource);
    }

    /**
     * Cycle the lamp status based on time of the day
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link Level The world reference}
     * @param blockPos {@link BlockPos The current block pos}
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
     * @param blockState {@link BlockState The current block state}
     * @return {@link Boolean True if the lamp is inverted}
     */
    private static boolean isNightlightLamp(final BlockState blockState) {
        return blockState.getValue(INVERTED);
    }

    /**
     * Check if the daylight lamp should be turned on or off
     * based on time of the day and if is already turned on or off
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link Level The world reference}
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
     * @param blockState {@link BlockState The current block state}
     * @param level {@link Level The world reference}
     * @return {@link Boolean True if is night and is turned off or is day and is turned on}
     */
    private static boolean shouldToggleNightlightLamp(final BlockState blockState, final Level level) {
        final boolean isNight = level.isNight();
        final boolean isLit = blockState.getValue(LIT);
        return isNightlightLamp(blockState) && ((isNight && !isLit) || (!isNight && isLit));
    }

    /**
     * Create the {@link StateDefinition block state definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The block state builder}
     */
    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(LIT).add(INVERTED).add(POWER);
    }

    /**
     * Get the lamp signal strength based on time of the day
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
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
     * @param blockState {@link BlockState The current block state}
     * @return {@link Boolean True}
     */
    @Override
    public boolean isSignalSource(final @NotNull BlockState blockState) {
        return true;
    }

    /**
     * Get the {@link Integer comparator signal} based on the daylight lamp status
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link Level The world reference}
     * @param blockPos {@link BlockPos The current block pos}
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
     * Create the {@link DaylightLampBlockEntity daylight lamp block entity}
     *
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     * @return {@link BlockEntity The daylight lamp block entity}
     */
    @Override
    public BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        return new DaylightLampBlockEntity(blockPos, blockState);
    }

    /**
     * Get the related ticker for this block
     *
     * @param level {@link Level The world reference}
     * @param blockState {@link BlockState The current block state}
     * @param blockEntityType {@link BlockEntityType The block entity type}
     * @return {@link BlockEntityTicker The block entity ticker}
     * @param <T> {@link T The block entity type}
     */
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(final Level level, final @NotNull BlockState blockState, final @NotNull BlockEntityType<T> blockEntityType) {
        return !level.isClientSide && level.dimensionType().hasSkyLight() ? createTickerHelper(blockEntityType, MWBlockEntityTypes.DAYLIGHT_LAMP.get(), DaylightLampBlock::tickEntity) : null;
    }

    /**
     * Tick the daylight lamp
     *
     * @param level {@link Level The world reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     * @param blockEntity {@link DaylightLampBlockEntity The daylight lamp block entity}
     */
    private static void tickEntity(final Level level, final BlockPos blockPos, final BlockState blockState, final DaylightLampBlockEntity blockEntity) {
        if (level.getGameTime() % 20L == 0L) {
            updateStatus(blockState, level, blockPos, level.random);
        }
    }

    /**
     * Get the {@link RenderShape render shape} for this block
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link RenderShape#MODEL Model render shape}
     */
    @Override
    public @NotNull RenderShape getRenderShape(final @NotNull BlockState blockState) {
        return RenderShape.MODEL;
    }

}