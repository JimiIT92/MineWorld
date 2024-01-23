package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.helper.PropertyHelper;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link TorchBlock Torch Block}
 */
public class MWTorchBlock extends TorchBlock {

    /**
     * {@link Supplier<ParticleOptions> The Torch Particle Supplier}
     */
    private final Supplier<? extends ParticleOptions> particleSupplier;

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param lightLevel {@link Integer The Torch light level}
     * @param particleSupplier {@link Supplier<ParticleOptions> The Torch Particle Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public MWTorchBlock(final int lightLevel, final Supplier<? extends ParticleOptions> particleSupplier, final FeatureFlag... featureFlags) {
        super(PropertyHelper.copy(Blocks.TORCH, featureFlags).noCollission().instabreak().lightLevel(blockState -> lightLevel).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY), ParticleTypes.FLAME);
        this.particleSupplier = particleSupplier;
    }

    /**
     * Display the {@link ParticleType Block Particles}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param randomSource {@link RandomSource The random reference}
     */
    public void animateTick(final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull RandomSource randomSource) {
        final double particleX = (double)blockPos.getX() + 0.5D;
        final double particleY = (double)blockPos.getY() + 0.7D;
        final double particleZ = (double)blockPos.getZ() + 0.5D;
        level.addParticle(ParticleTypes.SMOKE, particleX, particleY, particleZ, 0.0D, 0.0D, 0.0D);
        level.addParticle(this.particleSupplier.get(), particleX, particleY, particleZ, 0.0D, 0.0D, 0.0D);
    }

}
