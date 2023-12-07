package org.mineworld.world.structure;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWStructures;

import java.util.Optional;

/**
 * {@link Structure Implementation class for an Ethereal Ruin structure}
 */
public class EtherealRuin extends MWJigsawStructure {

    /**
     * Constructor. Set the structure properties
     *
     * @param config                  {@link StructureSettings The structure settings}
     * @param startPool               {@link StructureTemplatePool The starting structure template pool}
     * @param startJigsawName         {@link ResourceLocation The starting structure jigsaw name}
     * @param size                    {@link Integer The structure size}
     * @param startHeight             {@link HeightProvider The structure starting height for generation}
     * @param projectStartToHeightmap {@link Heightmap.Types The structure generation heightmap types}
     * @param maxDistanceFromCenter   {@link Integer The max distance from the structure center} that branches can spawn
     */
    protected EtherealRuin(StructureSettings config, Holder<StructureTemplatePool> startPool, Optional<ResourceLocation> startJigsawName, int size, HeightProvider startHeight, Optional<Heightmap.Types> projectStartToHeightmap, int maxDistanceFromCenter) {
        super(config, startPool, startJigsawName, size, startHeight, projectStartToHeightmap, maxDistanceFromCenter);
    }

    /**
     * Get the {@link StructureType structure type}
     *
     * @return {@link MWStructures#ICE_CASTLE The ice castle structure type}
     */
    @Override
    public @NotNull StructureType<?> type() {
        return MWStructures.ETHEREAL_RUIN.get();
    }
}
