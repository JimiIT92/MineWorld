package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.mineworld.entity.MWPrimedTnt;

public class MWFallableTntBlock extends MWTntBlock implements Fallable {

    /**
     * The {@link Fallable falling block} {@link Integer dust color}
     */
    private final int dustColor;

    /**
     * Constructor. Set the {@link Float tnt explosion power}
     *
     * @param type           {@link MWPrimedTnt.Type The tnt type}
     * @param dustColor      {@link Integer The falling block dust color}
     */
    public MWFallableTntBlock(final MWPrimedTnt.Type type, final int dustColor) {
        super(type);
        this.dustColor = dustColor;
    }

    /**
     * Get the {@link Fallable falling block} {@link Integer dust color}
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @return {@link Integer The dust color}
     */
    public int getDustColor(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos) {
        return dustColor;
    }

    /**
     * Schedule a delay after place
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link Level The world reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param neighborBlockState {@link BlockState The neighbor block state}
     * @param isClient {@link Boolean If the code is executing on the client side}
     */
    @Override
    public void onPlace(final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull BlockState neighborBlockState, final boolean isClient) {
        level.scheduleTick(blockPos, this, this.getDelayAfterPlace());
    }

    /**
     * Update the block state on neighbor change
     *
     * @param blockState {@link BlockState The current block state}
     * @param direction {@link Direction The direction of the neighbor update}
     * @param neighborBlockState {@link BlockState The neighbor block state}
     * @param levelAccessor {@link LevelAccessor The level accessor reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param neighborBlockPos {@link BlockPos The neighbor block pos}
     * @return {@link BlockState The updated block state}
     */
    @Override
    public @NotNull BlockState updateShape(final @NotNull BlockState blockState, final @NotNull Direction direction, final @NotNull BlockState neighborBlockState, final LevelAccessor levelAccessor, final @NotNull BlockPos blockPos, final @NotNull BlockPos neighborBlockPos) {
        levelAccessor.scheduleTick(blockPos, this, this.getDelayAfterPlace());
        return super.updateShape(blockState, direction, neighborBlockState, levelAccessor, blockPos, neighborBlockPos);
    }

    /**
     * Tick the block to see if it should fall
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link ServerLevel The world reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param randomSource {@link RandomSource The random reference}
     */
    public void tick(final @NotNull BlockState blockState, final ServerLevel level, final BlockPos blockPos, final @NotNull RandomSource randomSource) {
        if (canFall(level.getBlockState(blockPos.below())) && blockPos.getY() >= level.getMinBuildHeight()) {
            final FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(level, blockPos, blockState);
            this.falling(fallingblockentity);
        }
    }

    /**
     * Make the block falls
     *
     * @param entity {@link FallingBlockEntity The falling block entity for this block}
     */
    protected void falling(final FallingBlockEntity entity) { }

    /**
     * Check if the block can fall
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link Boolean True if the block can fall}
     */
    private boolean canFall(final BlockState blockState) {
        return blockState.isAir() || blockState.is(BlockTags.FIRE) || blockState.getMaterial().isLiquid() || blockState.canBeReplaced();
    }

    /**
     * Animate the falling block
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link Level The world reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param randomSource {@link RandomSource The random reference}
     */
    public void animateTick(final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final RandomSource randomSource) {
        if (randomSource.nextInt(16) == 0) {
            if (canFall(level.getBlockState(blockPos.below()))) {
                ParticleUtils.spawnParticleBelow(level, blockPos, randomSource, new BlockParticleOption(ParticleTypes.FALLING_DUST, blockState));
            }
        }
    }

    /**
     * Get the {@link Integer delay ticks} after the block has been placed
     *
     * @return {@link Integer 2}
     */
    private int getDelayAfterPlace() {
        return 2;
    }
}
