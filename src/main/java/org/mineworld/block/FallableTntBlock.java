package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.entity.block.MWPrimedTnt;

/**
 * {@link MineWorld MineWorld} {@link Fallable Fallable} {@link MWTntBlock TNT Block}
 */
public class FallableTntBlock extends MWTntBlock implements Fallable {

    /**
     * The {@link Fallable falling block} {@link Integer dust color}
     */
    private final int dustColor;

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param type {@link MWPrimedTnt.Type The TNT Type}
     * @param dustColor {@link Integer The falling block dust color}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public FallableTntBlock(final MWPrimedTnt.Type type, final int dustColor, final FeatureFlag... featureFlags) {
        super(type, featureFlags);
        this.dustColor = dustColor;
    }

    /**
     * Get the {@link Fallable falling block} {@link Integer dust color}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Integer The dust color}
     */
    public int getDustColor(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos) {
        return dustColor;
    }

    /**
     * Check if the TNT should fall after being placed
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link ServerLevel The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param neighborBlockState {@link BlockState The neighbor Block State}
     * @param isClient {@link Boolean If the Block has been placed only on the Client}
     */
    @Override
    public void onPlace(final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull BlockState neighborBlockState, final boolean isClient) {
        level.scheduleTick(blockPos, this, this.getDelayAfterPlace());
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
    @Override
    public @NotNull BlockState updateShape(final @NotNull BlockState blockState, final @NotNull Direction direction, final @NotNull BlockState neighborBlockState, final LevelAccessor levelAccessor, final @NotNull BlockPos blockPos, final @NotNull BlockPos neighborBlockPos) {
        levelAccessor.scheduleTick(blockPos, this, this.getDelayAfterPlace());
        return super.updateShape(blockState, direction, neighborBlockState, levelAccessor, blockPos, neighborBlockPos);
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
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean True if the block can fall}
     */
    private boolean canFall(final BlockState blockState) {
        return blockState.isAir() || blockState.is(BlockTags.FIRE) || blockState.liquid() || blockState.canBeReplaced();
    }

    /**
     * Display the {@link ParticleType Block Particles}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param randomSource {@link RandomSource The random reference}
     */
    @Override
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