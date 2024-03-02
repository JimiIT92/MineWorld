package org.mineworld.world.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import org.mineworld.MineWorld;
import org.mineworld.helper.ResourceHelper;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

/**
 * {@link MineWorld MineWorld} {@link Region Overworld Region}
 */
public final class MWOverworldRegion extends Region {

    /**
     * Constructor. Set the {@link Region Region} Properties
     */
    public MWOverworldRegion() {
        super(ResourceHelper.resourceLocation("overworld"), RegionType.OVERWORLD, 10);
    }

    /**
     * Add biomes to the {@link Region Region}
     *
     * @param biomeRegistry {@link Registry<Biome> The Biome Registry}
     * @param mapper {@link Pair The Biome Mapper}
     */
    @Override
    public void addBiomes(final Registry<Biome> biomeRegistry, final Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        new MWOverworldBiomeBuilder().addBiomes(mapper);
    }

}