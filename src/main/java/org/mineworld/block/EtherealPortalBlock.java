package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
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
import org.mineworld.core.MWSounds;
import org.mineworld.core.MWTags;
import org.mineworld.helper.RandomHelper;
import org.mineworld.world.dimension.EtherealTeleporter;
import org.mineworld.world.dimension.MWTeleporter;

/**
 * Implementation class for the {@link MWPortalBlock Ethereal Portal Block}
 */
public class EtherealPortalBlock extends MWPortalBlock {

    /**
     * Get the {@link TagKey<Block> portal block tags}
     *
     * @return {@link TagKey<Block> The portal block tags}
     */
    @Override
    public TagKey<Block> getPortalBlocks() {
        return MWTags.Blocks.ETHEREAL_PORTAL_FRAME_BLOCKS;
    }

    /**
     * Get the {@link BlockState portal frame block state}
     *
     * @return {@link BlockState The portal frame block state}
     */
    @Override
    public BlockState getPortalFrameState() {
        return MWBlocks.ETHEREAL_PORTAL.get().defaultBlockState();
    }

    /**
     * Get the {@link ResourceKey<Level> dimension key} this portal is referring to
     *
     * @return {@link ResourceKey<Level> The dimension key}
     */
    @Override
    public ResourceKey<Level> getDimension() {
        return MWDimensions.ETHEREAL;
    }

    /**
     * Get the {@link SoundEvent portal opening sound}
     *
     * @return The {@link SoundEvent portal opening sound}
     */
    @Override
    public SoundEvent getPortalSound() {
        return MWSounds.ETHEREAL_PORTAL_OPEN.get();
    }

    /**
     * Get the {@link MWTeleporter dimension teleporter}
     *
     * @param level {@link ServerLevel The level reference}
     * @return {@link MWTeleporter The dimension teleporter}
     */
    @Override
    public MWTeleporter getTeleporter(ServerLevel level) {
        return new EtherealTeleporter(level);
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
    public void animateTick(final @NotNull BlockState state, final @NotNull Level level, final @NotNull BlockPos pos, final @NotNull RandomSource random) {
        if (RandomHelper.choose(20)) {
            level.playLocalSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, SoundEvents.SCULK_BLOCK_CHARGE, SoundSource.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F, false);
            double x = (double) pos.getX() + random.nextDouble();
            double y = (double) pos.getY() + random.nextDouble();
            double z = (double) pos.getZ() + random.nextDouble();
            double xSpeed = ((double) random.nextFloat() - 0.5D) * 0.15D;
            double ySpeed = ((double) random.nextFloat() - 0.5D) * 0.15D;
            double zSpeed = ((double) random.nextFloat() - 0.5D) * 0.15D;
            int j = random.nextInt(2) * 2 - 1;
            if (!level.getBlockState(pos.west()).is(this) && !level.getBlockState(pos.east()).is(this)) {
                x = (double) pos.getX() + 0.5D + 0.25D * (double) j;
                xSpeed = random.nextFloat() * 0.15F * (float) j;
            } else {
                z = (double) pos.getZ() + 0.5D + 0.25D * (double) j;
                zSpeed = random.nextFloat() * 0.15F * (float) j;
            }

            level.addParticle(ParticleTypes.SCULK_SOUL, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }

}
