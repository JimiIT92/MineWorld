package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;

/**
 * {@link MineWorld MineWorld} {@link MWSlabBlock Crying Obsidian Slab}
 */
public class CryingObsidianSlab extends MWSlabBlock {

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     */
    public CryingObsidianSlab() {
        super(Blocks.CRYING_OBSIDIAN::defaultBlockState, PushReaction.BLOCK);
    }

    /**
     * Display the {@link ParticleType Block Particles}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param randomSource {@link RandomSource The random reference}
     */
    public void animateTick(final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final RandomSource randomSource) {
        if (randomSource.nextInt(5) == 0) {
            final Direction direction = Direction.getRandom(randomSource);
            if (!direction.equals(Direction.UP)) {
                final BlockPos blockpos = blockPos.relative(direction);
                final BlockState blockstate = level.getBlockState(blockpos);
                if (!blockState.canOcclude() || !blockstate.isFaceSturdy(level, blockpos, direction.getOpposite())) {
                    final double offsetX = direction.getStepX() == 0 ? randomSource.nextDouble() : 0.5D + (double)direction.getStepX() * 0.6D;
                    final double offsetY = direction.getStepY() == 0 ? randomSource.nextDouble() : 0.5D + (double)direction.getStepY() * 0.6D;
                    final double offsetZ = direction.getStepZ() == 0 ? randomSource.nextDouble() : 0.5D + (double)direction.getStepZ() * 0.6D;
                    level.addParticle(ParticleTypes.DRIPPING_OBSIDIAN_TEAR, (double)blockPos.getX() + offsetX, (double)blockPos.getY() + offsetY, (double)blockPos.getZ() + offsetZ, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

}