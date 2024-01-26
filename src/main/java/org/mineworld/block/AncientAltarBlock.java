package org.mineworld.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.core.MWItems;
import org.mineworld.helper.PropertyHelper;

/**
 * {@link MineWorld MineWorld} {@link Block Ancient Altar Block}
 */
public class AncientAltarBlock extends Block implements SimpleWaterloggedBlock {

    /**
     * {@link BooleanProperty The Block Waterlogged property}
     */
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    /**
     * {@link BooleanProperty The Block Activated property}
     */
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     */
    public AncientAltarBlock() {
        super(PropertyHelper.block(MapColor.COLOR_BLACK, -1.0F, 3600000.0F, true, SoundType.SCULK_SHRIEKER)
                .noLootTable()
                .lightLevel(state -> state.getValue(ACTIVATED) ? 15 : 0)
        );
        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVATED, Boolean.FALSE).setValue(WATERLOGGED, Boolean.FALSE));
    }

    /**
     * Create the {@link StateDefinition Block State definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The Block State builder}
     */
    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(ACTIVATED, WATERLOGGED);
    }

    /**
     * Get the {@link BlockState Block State} after the block has been placed
     *
     * @param placeContext {@link BlockPlaceContext The block place context}
     * @return {@link BlockState The placed Block State}
     */
    @Nullable
    public BlockState getStateForPlacement(final BlockPlaceContext placeContext) {
        final BlockState blockState = this.defaultBlockState();
        final LevelReader level = placeContext.getLevel();
        final BlockPos blockPos = placeContext.getClickedPos();
        if (blockState.canSurvive(level, blockPos)) {
            return blockState.setValue(WATERLOGGED, level.getFluidState(blockPos).is(Fluids.WATER));
        }
        return null;
    }

    /**
     * Update the {@link BlockState Block State} on neighbor changes
     *
     * @param blockState {@link BlockState The current Block State}
     * @param direction {@link Direction The direction the changes are coming}
     * @param neighborBlockState {@link BlockState The neighbor Block State}
     * @param levelAccessor {@link LevelAccessor The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param neighborBlockPos {@link BlockPos The neighbor Block Pos}
     * @return {@link BlockState The updated Block State}
     */
    public @NotNull BlockState updateShape(final BlockState blockState, final @NotNull Direction direction, final @NotNull BlockState neighborBlockState, final @NotNull LevelAccessor levelAccessor, final @NotNull BlockPos blockPos, final @NotNull BlockPos neighborBlockPos) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }
        return super.updateShape(blockState, direction, neighborBlockState, levelAccessor, blockPos, neighborBlockPos);
    }

    /**
     * Get the {@link FluidState Block Fluid State}
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Fluids#WATER Water if is Waterlogged}
     */
    public @NotNull FluidState getFluidState(final BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
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
    public @NotNull InteractionResult use(final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull Player player, final @NotNull InteractionHand hand, final @NotNull BlockHitResult hitResult) {
        final ItemStack itemStack = player.getItemInHand(hand);
        if(itemStack.is(MWItems.DARK_SOUL.get()) && !blockState.getValue(ACTIVATED)) {
            spawnParticles(level, blockPos);
            player.displayClientMessage(Component.translatable("message.mineworld.ancient_altar_tease").withStyle(ChatFormatting.BLUE), true);
            player.playSound(SoundEvents.WARDEN_ROAR);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    /**
     * Spawn the Activation Particles
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     */
    private static void spawnParticles(final Level level, final BlockPos blockPos) {
        final double offset = 1.0625D;
        final RandomSource random = level.random;

        for(Direction direction : Direction.values()) {
            final BlockPos relativePos = blockPos.relative(direction);
            if (!level.getBlockState(relativePos).isSolidRender(level, relativePos)) {
                for (int i = 0; i < 3; i++) {
                    final Direction.Axis axis = direction.getAxis();
                    final double x = axis.equals(Direction.Axis.X) ? offset * (double)direction.getStepX() : (double)random.nextFloat();
                    final double y = axis.equals(Direction.Axis.Y) ? offset * (double)direction.getStepY() : (double)random.nextFloat();
                    final double z = axis.equals(Direction.Axis.Z) ? offset * (double)direction.getStepZ() : (double)random.nextFloat();
                    level.addParticle(ParticleTypes.SCULK_SOUL, (double)blockPos.getX() + x, (double)blockPos.getY() + y, (double)blockPos.getZ() + z, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

}