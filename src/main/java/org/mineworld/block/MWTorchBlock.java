package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;

import java.util.function.Supplier;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link TorchBlock Torch Block}
 */
public class MWTorchBlock extends TorchBlock {

    /**
     * {@link Supplier<ParticleOptions> The flame particle supplier}
     */
    private final Supplier<ParticleOptions> flameParticleSupplier;

    /**
     * Constructor. Set the block properties
     */
    public MWTorchBlock(final int lightLevel, final Supplier<ParticleOptions> flameParticleSupplier) {
        super(BlockBehaviour.Properties.of().noCollission().instabreak().lightLevel(state -> lightLevel).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY), ParticleTypes.FLAME);
        this.flameParticleSupplier = flameParticleSupplier;
    }

    /**
     * Show the flame particles
     *
     * @param state {@link BlockState The current BlockState}
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current BlockPos}
     * @param randomSource {@link RandomSource The random reference}
     */
    @Override
    public void animateTick(final @NotNull BlockState state, final Level level, final BlockPos pos, final @NotNull RandomSource randomSource) {
        final double particleX = (double)pos.getX() + 0.5D;
        final double particleY = (double)pos.getY() + 0.7D;
        final double particleZ = (double)pos.getZ() + 0.5D;
        level.addParticle(ParticleTypes.SMOKE, particleX, particleY, particleZ, 0.0D, 0.0D, 0.0D);
        level.addParticle(this.flameParticleSupplier.get(), particleX, particleY, particleZ, 0.0D, 0.0D, 0.0D);
    }

}