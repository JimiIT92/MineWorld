package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWTags;
import org.mineworld.helper.PropertyHelper;

import javax.annotation.Nullable;

/**
 * Implementation class for a {@link DoublePlantBlock Cattail Block}
 */
public class CattailBlock extends DoublePlantBlock implements SimpleWaterloggedBlock {

    /**
     * {@link BooleanProperty The Block Waterlogged Property}
     */
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    /**
     * Constructor. Sets the {@link BlockBehaviour.Properties Block Properties}
     */
    public CattailBlock() {
        super(PropertyHelper.basicBlockProperties(0F, false).noCollission().noOcclusion().instabreak().sound(SoundType.SMALL_DRIPLEAF).offsetType(BlockBehaviour.OffsetType.XZ));
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(HALF, DoubleBlockHalf.LOWER)
                .setValue(WATERLOGGED, Boolean.FALSE)
        );
    }

    /**
     * Get the {@link VoxelShape Block Shape}
     *
     * @param state {@link BlockState The current BlockState}
     * @param level {@link BlockGetter The Level reference}
     * @param pos {@link BlockPos The current BlockPos}
     * @param context {@link CollisionContext The Collision Context}
     * @return {@link VoxelShape The Block Shape}
     */
    public @NotNull VoxelShape getShape(final @NotNull BlockState state, final @NotNull BlockGetter level, final @NotNull BlockPos pos, final @NotNull CollisionContext context) {
        Vec3 offset = state.getOffset(level, pos);
        return Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D).move(offset.x, offset.y, offset.z);
    }

    /**
     * Check if the {@link DoublePlantBlock Cattail} can be placed on a {@link Block Block}
     *
     * @param state {@link BlockState The current BlockState}
     * @param level {@link BlockGetter The Level reference}
     * @param pos {@link BlockPos The current BlockPos}
     * @return {@link Boolean True} if the {@link DoublePlantBlock Cattail} can be placed on a {@link Block Block}
     */
    protected boolean mayPlaceOn(final BlockState state, final @NotNull BlockGetter level, final @NotNull BlockPos pos) {
        return state.is(MWTags.Blocks.CATTAIL_PLACEABLE) && level.getFluidState(pos.above()).isSourceOfType(Fluids.WATER);
    }

    /**
     * Get the {@link BlockState BlockState} when the
     * {@link DoublePlantBlock Cattail} is placed
     *
     * @param context {@link BlockPlaceContext The Place Context}
     * @return Placed {@link BlockState The placed BlockState}
     */
    @Nullable
    public BlockState getStateForPlacement(final @NotNull BlockPlaceContext context) {
        BlockState blockstate = super.getStateForPlacement(context);
        return blockstate != null ? copyWaterloggedFrom(context.getLevel(), context.getClickedPos(), blockstate) : null;
    }

    /**
     * Place the {@link DoubleBlockHalf#UPPER Upper variant} on {@link DoublePlantBlock Cattail} placed
     *
     * @param level {@link Level The Level reference}
     * @param pos {@link BlockPos The current BlockPos}
     * @param state {@link BlockState The current BlockState}
     * @param entity {@link LivingEntity The Entity} who placed the {@link DoublePlantBlock Cattail}
     * @param stack {@link ItemStack The ItemStack}
     */
    public void setPlacedBy(final Level level, final @NotNull BlockPos pos, final @NotNull BlockState state, final @NotNull LivingEntity entity, final @NotNull ItemStack stack) {
        if (!level.isClientSide()) {
            BlockPos blockpos = pos.above();
            BlockState blockstate = DoublePlantBlock.copyWaterloggedFrom(level, blockpos, this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER));
            level.setBlock(blockpos, blockstate, 3);
        }
    }

    /**
     * Get the {@link FluidState Fluid State}
     *
     * @param state {@link BlockState The current BlockState}
     * @return {@link FluidState The Fluid State}
     */
    public @NotNull FluidState getFluidState(final BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    /**
     * Checks if the {@link DoublePlantBlock Cattail} should break
     * if one {@link #HALF Half} is broken
     *
     * @param state {@link BlockState The current BlockState}
     * @param level {@link LevelReader The Level reference}
     * @param pos {@link BlockPos The current BlockPos}
     * @return {@link Boolean True} if the {@link CattailBlock} should not break if one {@link #HALF Half} is broken
     */
    public boolean canSurvive(final BlockState state, final @NotNull LevelReader level, final @NotNull BlockPos pos) {
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            return super.canSurvive(state, level, pos);
        } else {
            BlockPos blockpos = pos.below();
            BlockState blockstate = level.getBlockState(blockpos);
            return this.mayPlaceOn(blockstate, level, blockpos);
        }
    }

    /**
     * Updated the {@link BlockState BlockState} if the
     * {@link DoublePlantBlock Cattail} is waterlogged
     *
     * @param state {@link BlockState The current BlockState}
     * @param facing {@link Direction The update Direction}
     * @param neighborState {@link BlockState The neighbor BlockState}
     * @param level {@link LevelAccessor The Level reference}
     * @param pos {@link BlockPos The current BlockPos}
     * @param neighborPos {@link BlockPos The neighbor BlockPos}
     * @return Placed {@link BlockState The updated BlockState}
     */
    public @NotNull BlockState updateShape(final BlockState state, final @NotNull Direction facing, final @NotNull BlockState neighborState, final @NotNull LevelAccessor level, final @NotNull BlockPos pos, final @NotNull BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, facing, neighborState, level, pos, neighborPos);
    }

    /**
     * Create the {@link StateDefinition BlockState Definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The BlockState Builder}
     */
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(HALF, WATERLOGGED);
    }

    /**
     * Get the {@link Float Block Vertical Offset}
     *
     * @return {@link Float The Block Vertical Offset}
     */
    public float getMaxVerticalOffset() {
        return 0.1F;
    }

}