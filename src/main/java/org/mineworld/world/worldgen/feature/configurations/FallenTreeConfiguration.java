package org.mineworld.world.worldgen.feature.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import org.mineworld.MineWorld;

/**
 * {@link MineWorld MineWorld} {@link FeatureConfiguration Fallen Tree Feature Configuration}
 *
 * @param logProvider {@link BlockStateProvider The Log Block State Provider}
 * @param hollowLogProvider {@link BlockStateProvider The Hollow Log Block State Provider}
 * @param ignoreMoss {@link Boolean If the Fallen Tree should not generate Moss}
 */
public record FallenTreeConfiguration(BlockStateProvider logProvider, BlockStateProvider hollowLogProvider, boolean ignoreMoss) implements FeatureConfiguration {

    /**
     * {@link Codec<FallenTreeConfiguration> The Fallen Tree Configuration Codec}
     */
    public static final Codec<FallenTreeConfiguration> CODEC = RecordCodecBuilder.create(fallenTreeConfig -> fallenTreeConfig.group(
            BlockStateProvider.CODEC.fieldOf("log_provider").forGetter(config -> config.logProvider),
            BlockStateProvider.CODEC.fieldOf("hollow_log_provider").forGetter(config -> config.hollowLogProvider),
            Codec.BOOL.fieldOf("ignore_moss").orElse(false).forGetter(config -> config.ignoreMoss)
    ).apply(fallenTreeConfig, FallenTreeConfiguration::new));

}