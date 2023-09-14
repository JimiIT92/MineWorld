package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWDimensions;
import org.mineworld.core.MWTags;
import org.mineworld.world.dimension.CorruptedTeleporter;
import org.mineworld.world.dimension.MWTeleporter;

/**
 * Implementation class for the corrupted dimension {@link MWPortalBlock portal block}
 */
public class CorruptedPortalBlock extends MWPortalBlock {

    /**
     * Get the {@link TagKey<Block> portal block tags}
     *
     * @return {@link TagKey<Block> The portal block tags}
     */
    @Override
    public TagKey<Block> getPortalBlocks() {
        return MWTags.Blocks.CORRUPTED_PORTAL_FRAME_BLOCKS;
    }

    /**
     * Get the {@link BlockState portal frame block state}
     *
     * @return {@link BlockState The portal frame block state}
     */
    @Override
    public BlockState getPortalFrameState() {
        return MWBlocks.CORRUPTED_PORTAL.get().defaultBlockState();
    }

    /**
     * Get the {@link ResourceKey<Level> dimension key} this portal is referring to
     *
     * @return {@link ResourceKey<Level> The dimension key}
     */
    @Override
    public ResourceKey<Level> getDimension() {
        return MWDimensions.CORRUPTED_DIMENSION_KEY;
    }

    /**
     * Get the {@link MWTeleporter dimension teleporter}
     *
     * @param level {@link ServerLevel The level reference}
     * @return {@link MWTeleporter The dimension teleporter}
     */
    @Override
    public MWTeleporter getTeleporter(final ServerLevel level) {
        return new CorruptedTeleporter(level);
    }

    /**
     * Show the portal particles
     *
     * @param state {@link BlockState The current block state}
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param random {@link RandomSource The random reference}
     */
    @Override
    public void animateTick(final @NotNull BlockState state, final @NotNull Level level, final @NotNull BlockPos pos, final RandomSource random) {
        if (random.nextInt(100) == 0) {
            level.playLocalSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F, false);
        }

        for (int i = 0; i < 4; ++i) {
            double x = (double) pos.getX() + random.nextDouble();
            double y = (double) pos.getY() + random.nextDouble();
            double z = (double) pos.getZ() + random.nextDouble();
            double xSpeed = ((double) random.nextFloat() - 0.5D) * 0.5D;
            double ySpeed = ((double) random.nextFloat() - 0.5D) * 0.5D;
            double zSpeed = ((double) random.nextFloat() - 0.5D) * 0.5D;
            int j = random.nextInt(2) * 2 - 1;
            if (!level.getBlockState(pos.west()).is(this) && !level.getBlockState(pos.east()).is(this)) {
                x = (double) pos.getX() + 0.5D + 0.25D * (double) j;
                xSpeed = random.nextFloat() * 2.0F * (float) j;
            } else {
                z = (double) pos.getZ() + 0.5D + 0.25D * (double) j;
                zSpeed = random.nextFloat() * 2.0F * (float) j;
            }

            level.addParticle(ParticleTypes.WHITE_ASH, x, y, z, xSpeed, ySpeed, zSpeed);
        }

    }

}