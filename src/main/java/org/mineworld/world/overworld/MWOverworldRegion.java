package org.mineworld.world.overworld;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import org.mineworld.MineWorld;
import org.mineworld.helper.KeyHelper;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link Region terrablender region}
 */
public class MWOverworldRegion extends Region {

    /**
     * Construtor. Set the {@link Region terrablender region} properties
     */
    public MWOverworldRegion() {
        super(KeyHelper.location("overworld"), RegionType.OVERWORLD, 10);
    }

    /**
     * Add biomes to thge region
     *
     * @param biomeRegistry {@link Registry<Biome> The biome registry}
     * @param mapper {@link Pair The region mapper}
     */
    @Override
    public void addBiomes(Registry<Biome> biomeRegistry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        new MWOverworldBiomeBuilder().addBiomes(mapper);
    }

}