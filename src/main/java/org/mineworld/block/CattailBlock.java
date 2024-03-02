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
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.core.MWTags;
import org.mineworld.helper.PropertyHelper;

/**
 * {@link MineWorld MineWorld} {@link CattailBlock Cattail Block}
 */
public class CattailBlock extends DoublePlantBlock implements SimpleWaterloggedBlock {

    /**
     * {@link BooleanProperty The Block Waterlogged property}
     */
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     */
    public CattailBlock() {
        super(PropertyHelper.block(0F, false).noCollission().noOcclusion().instabreak().sound(SoundType.SMALL_DRIPLEAF).offsetType(BlockBehaviour.OffsetType.XZ));
        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(WATERLOGGED, Boolean.FALSE));
    }

    /**
     * Get the {@link VoxelShape Block Shape}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param collisionContext {@link CollisionContext The collision context}
     * @return {@link VoxelShape The Block Shape}
     */
    public @NotNull VoxelShape getShape(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull CollisionContext collisionContext) {
        final Vec3 offset = blockState.getOffset(blockGetter, blockPos);
        return Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D).move(offset.x, offset.y, offset.z);
    }

    /**
     * Check if the Block can be placed at the given {@link BlockPos location}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Boolean True if the Block can be placed}
     */
    protected boolean mayPlaceOn(final BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos) {
        return blockState.is(MWTags.Blocks.CATTAIL_PLACEABLE) && blockGetter.getFluidState(blockPos.above()).isSourceOfType(Fluids.WATER);
    }

    /**
     * Get the {@link BlockState Block State} after the block has been placed
     *
     * @param placeContext {@link BlockPlaceContext The block place context}
     * @return {@link BlockState The placed Block State}
     */
    @Nullable
    public BlockState getStateForPlacement(final @NotNull BlockPlaceContext placeContext) {
        BlockState blockstate = super.getStateForPlacement(placeContext);
        return blockstate != null ? copyWaterloggedFrom(placeContext.getLevel(), placeContext.getClickedPos(), blockstate) : null;
    }

    /**
     * Place the {@link DoubleBlockHalf#UPPER Upper variant} when a {@link DoublePlantBlock Cattail} is placed
     *
     * @param level {@link Level The Level reference}
     * @param blockPos {@link BlockPos The current BlockPos}
     * @param blockState {@link BlockState The current BlockState}
     * @param entity {@link LivingEntity The Entity} who placed the {@link DoublePlantBlock Cattail}
     * @param itemStack {@link ItemStack The ItemStack}
     */
    public void setPlacedBy(final Level level, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState, final @NotNull LivingEntity entity, final @NotNull ItemStack itemStack) {
        if (!level.isClientSide()) {
            final BlockPos aboveBlockPos = blockPos.above();
            final BlockState aboveBlockState = DoublePlantBlock.copyWaterloggedFrom(level, aboveBlockPos, this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER));
            level.setBlock(aboveBlockPos, aboveBlockState, 3);
        }
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
     * Check if the Block can stay at the given {@link BlockPos location}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param levelReader {@link LevelReader The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Boolean True if the block can survive}
     */
    public boolean canSurvive(final BlockState blockState, final @NotNull LevelReader levelReader, final @NotNull BlockPos blockPos) {
        if (blockState.getValue(HALF) == DoubleBlockHalf.UPPER) {
            return super.canSurvive(blockState, levelReader, blockPos);
        } else {
            final BlockPos belowBlockPos = blockPos.below();
            final BlockState belowBlockState = levelReader.getBlockState(belowBlockPos);
            return this.mayPlaceOn(belowBlockState, levelReader, belowBlockPos);
        }
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
     * Create the {@link StateDefinition Block State definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The Block State builder}
     */
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(HALF, WATERLOGGED);
    }

    /**
     * Get the {@link Float Block Maximum Horizontal Offset}
     *
     * @return {@link Float 0.1F}
     */
    public float getMaxVerticalOffset() {
        return 0.1F;
    }

}