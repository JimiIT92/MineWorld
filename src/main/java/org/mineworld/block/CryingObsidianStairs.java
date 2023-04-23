package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;
import org.mineworld.helper.PropertyHelper;

/**
 * Implementation class for the {@link StairBlock crying obsidian stairs}
 */
public class CryingObsidianStairs extends StairBlock {

    /**
     * Constructor. Set the block properties
     */
    public CryingObsidianStairs() {
        super(Blocks.CRYING_OBSIDIAN::defaultBlockState, PropertyHelper.copyFromBlock(Blocks.CRYING_OBSIDIAN).requiresCorrectToolForDrops());
    }

    /**
     * Display the {@link ParticleTypes#DRIPPING_OBSIDIAN_TEAR crying obsidian particles}
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param randomSource {@link RandomSource The random reference}
     */
    public void animateTick(final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final RandomSource randomSource) {
        if (randomSource.nextInt(5) == 0) {
            final Direction direction = Direction.getRandom(randomSource);
            if (!direction.equals(Direction.UP)) {
                final BlockPos blockpos = blockPos.relative(direction);
                final BlockState blockstate = level.getBlockState(blockpos);
                if (!blockState.canOcclude() || !blockstate.isFaceSturdy(level, blockpos, direction.getOpposite())) {
                    double offsetX = direction.getStepX() == 0 ? randomSource.nextDouble() : 0.5D + (double)direction.getStepX() * 0.6D;
                    double offsetY = direction.getStepY() == 0 ? randomSource.nextDouble() : 0.5D + (double)direction.getStepY() * 0.6D;
                    double offsetZ = direction.getStepZ() == 0 ? randomSource.nextDouble() : 0.5D + (double)direction.getStepZ() * 0.6D;
                    level.addParticle(ParticleTypes.DRIPPING_OBSIDIAN_TEAR, (double)blockPos.getX() + offsetX, (double)blockPos.getY() + offsetY, (double)blockPos.getZ() + offsetZ, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    /**
     * Get the {@link PushReaction push reaction} when this block is pushed by pistons
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link PushReaction#DESTROY Destroy push reaction}
     */
    public @NotNull PushReaction getPistonPushReaction(final @NotNull BlockState blockState) {
        return PushReaction.BLOCK;
    }

}
