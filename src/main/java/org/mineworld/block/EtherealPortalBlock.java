package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleType;
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
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWDimensions;
import org.mineworld.core.MWSounds;
import org.mineworld.core.MWTags;
import org.mineworld.world.dimension.EtherealTeleporter;
import org.mineworld.world.dimension.MWTeleporter;

import java.util.Optional;

/**
 * {@link MineWorld MineWorld} {@link MWPortalBlock Ethereal Portal Block}
 */
public class EtherealPortalBlock extends MWPortalBlock {

    /**
     * Get the {@link TagKey<Block> portal blocks tag}
     *
     * @return {@link TagKey<Block> The portal blocks tag}
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
     * @return {@link SoundEvent The portal opening sound}
     */
    @Override
    public Optional<SoundEvent> getPortalSound() {
        return Optional.of(MWSounds.ETHEREAL_PORTAL_OPEN.get());
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
     * Display the {@link ParticleType Block Particles}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param randomSource {@link RandomSource The random reference}
     */
    @Override
    public void animateTick(final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull RandomSource randomSource) {
        if (randomSource.nextInt(0, 20) == 10) {
            level.playLocalSound((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D, (double) blockPos.getZ() + 0.5D, SoundEvents.SCULK_BLOCK_CHARGE, SoundSource.BLOCKS, 0.5F, randomSource.nextFloat() * 0.4F + 0.8F, false);
            double particleX = (double) blockPos.getX() + randomSource.nextDouble();
            final double particleY = (double) blockPos.getY() + randomSource.nextDouble();
            double particleZ = (double) blockPos.getZ() + randomSource.nextDouble();
            double particleXSpeed = ((double) randomSource.nextFloat() - 0.5D) * 0.15D;
            final double particleYSpeed = ((double) randomSource.nextFloat() - 0.5D) * 0.15D;
            double particleZSpeed = ((double) randomSource.nextFloat() - 0.5D) * 0.15D;
            int particleMultiplier = randomSource.nextInt(2) * 2 - 1;
            if (!level.getBlockState(blockPos.west()).is(this) && !level.getBlockState(blockPos.east()).is(this)) {
                particleX = (double) blockPos.getX() + 0.5D + 0.25D * (double) particleMultiplier;
                particleXSpeed = randomSource.nextFloat() * 0.15F * (float) particleMultiplier;
            } else {
                particleZ = (double) blockPos.getZ() + 0.5D + 0.25D * (double) particleMultiplier;
                particleZSpeed = randomSource.nextFloat() * 0.15F * (float) particleMultiplier;
            }
            level.addParticle(ParticleTypes.SCULK_SOUL, particleX, particleY, particleZ, particleXSpeed, particleYSpeed, particleZSpeed);
        }
    }

}