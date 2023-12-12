package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWItems;
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.PlayerHelper;
import org.mineworld.helper.PropertyHelper;

import javax.annotation.Nullable;

/**
 * Class for an Ancient Temple Block
 */
public class AncientAltarBlock extends Block implements SimpleWaterloggedBlock {

    /**
     * {@link BooleanProperty The Block Waterlogged Property}
     */
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    /**
     * The {@link BooleanProperty hearted property}
     */
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");

    /**
     * Constructor. Set the block properties
     */
    public AncientAltarBlock() {
        super(PropertyHelper.basicBlockProperties(MapColor.COLOR_BLACK, -1.0F, 3600000.0F, true, SoundType.SCULK_SHRIEKER)
                .noLootTable()
                .lightLevel(state -> state.getValue(ACTIVATED) ? 15 : 0));
        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVATED, Boolean.FALSE).setValue(WATERLOGGED, Boolean.FALSE));
    }

    /**
     * Create the {@link BlockState Block State} definition
     *
     * @param stateBuilder {@link StateDefinition.Builder The Block State builder}
     */
    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(ACTIVATED, WATERLOGGED);
    }

    /**
     * Get the {@link BlockState block state} for the block when is placed
     *
     * @param blockPlaceContext {@link BlockPlaceContext The block place context}
     * @return {@link BlockState The placed block state}
     */
    @Nullable
    public BlockState getStateForPlacement(final BlockPlaceContext blockPlaceContext) {
        BlockState blockState = this.defaultBlockState();
        final LevelReader level = blockPlaceContext.getLevel();
        final BlockPos blockPos = blockPlaceContext.getClickedPos();
        if (blockState.canSurvive(level, blockPos)) {
            return blockState.setValue(WATERLOGGED, level.getFluidState(blockPos).is(Fluids.WATER));
        }
        return null;
    }

    /**
     * Update the {@link BlockState block state} based on neighbor updates
     *
     * @param blockState {@link BlockState The current block state}
     * @param direction {@link Direction The update direction}
     * @param neighborState {@link BlockState The neighbor block state}
     * @param levelAccessor {@link LevelAccessor The level accessor reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param neighborPos {@link BlockPos The neighbor block pos}
     * @return {@link BlockState The updated block state}
     */
    public @NotNull BlockState updateShape(final BlockState blockState, final @NotNull Direction direction, final @NotNull BlockState neighborState, final @NotNull LevelAccessor levelAccessor, final @NotNull BlockPos blockPos, final @NotNull BlockPos neighborPos) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }
        return super.updateShape(blockState, direction, neighborState, levelAccessor, blockPos, neighborPos);
    }

    /**
     * Get the {@link FluidState block fluid state}
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link FluidState The block fluid state}
     */
    public @NotNull FluidState getFluidState(final BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    /**
     * Lit the Ethereal Rune when the {@link Player Player} right clicks
     * it with the {@link Item Sculk Heart}
     *
     * @param state {@link BlockState The current Block State}
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current Block Pos}
     * @param player {@link Player The player that interacted with the Block}
     * @param hand {@link InteractionHand The hand used to interact with the Block}
     * @param hitResult {@link BlockHitResult The Block hit result}
     * @return {@link InteractionResult The interaction result}
     */
    @Override
    public @NotNull InteractionResult use(final @NotNull BlockState state, final @NotNull Level level, final @NotNull BlockPos pos, final @NotNull Player player, final @NotNull InteractionHand hand, final @NotNull BlockHitResult hitResult) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(itemstack.is(MWItems.DARK_SOUL.get()) && !state.getValue(ACTIVATED)) {
            level.setBlock(pos, state.setValue(ACTIVATED, Boolean.TRUE), 2);
            spawnParticles(level, pos);
            ItemHelper.hurt(itemstack, player);
            if (!level.isClientSide) {
                final Vec3 vec3 = Vec3.atLowerCornerWithOffset(pos, 0.5D, 1.01D, 0.5D).offsetRandom(level.random, 0.1F);
                final ItemEntity echoingChargeFragment = new ItemEntity(level, vec3.x(), vec3.y(), vec3.z(), ItemHelper.getDefaultStack(MWItems.ECHOING_CHARGE_FRAGMENT));
                echoingChargeFragment.setDefaultPickUpDelay();
                level.addFreshEntity(echoingChargeFragment);
            }
            PlayerHelper.playSound(player, SoundEvents.WARDEN_ROAR);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    /**
     * Spawn the activation particles
     *
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current BlockPos}
     */
    private static void spawnParticles(final Level level, final BlockPos pos) {
        final double multiplier = 0.5625D;
        final RandomSource random = level.random;

        for(Direction direction : Direction.values()) {
            final BlockPos relativePos = pos.relative(direction);
            if (!level.getBlockState(relativePos).isSolidRender(level, relativePos)) {
                final Direction.Axis axis = direction.getAxis();
                final double x = axis.equals(Direction.Axis.X) ? 0.5D + 0.5625D * (double)direction.getStepX() : (double)random.nextFloat();
                final double y = axis.equals(Direction.Axis.Y) ? 0.5D + 0.5625D * (double)direction.getStepY() : (double)random.nextFloat();
                final double z = axis.equals(Direction.Axis.Z) ? 0.5D + 0.5625D * (double)direction.getStepZ() : (double)random.nextFloat();
                level.addParticle(ParticleTypes.WARPED_SPORE, (double)pos.getX() + x, (double)pos.getY() + y, (double)pos.getZ() + z, 0.0D, 0.0D, 0.0D);
            }
        }

    }
}