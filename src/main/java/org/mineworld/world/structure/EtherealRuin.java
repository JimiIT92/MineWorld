package org.mineworld.world.structure;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Optional;

public class EtherealRuin extends MWJigsawStructure {

    /**
     * Constructor. Set the Structure properties
     *
     * @param config                  {@link StructureSettings The Structure settings}
     * @param startPool               {@link StructureTemplatePool The starting Structure template pool}
     * @param startJigsawName         {@link ResourceLocation The starting Structure jigsaw name}
     * @param size                    {@link Integer The Structure size}
     * @param startHeight             {@link HeightProvider The Structure starting height for generation}
     * @param projectStartToHeightmap {@link Heightmap.Types The Structure generation heightmap types}
     * @param maxDistanceFromCenter   {@link Integer The max distance from the Structure center} that branches can spawn
     */
    protected EtherealRuin(StructureSettings config, Holder<StructureTemplatePool> startPool, Optional<ResourceLocation> startJigsawName, int size, HeightProvider startHeight, Optional<Heightmap.Types> projectStartToHeightmap, int maxDistanceFromCenter) {
        super(config, startPool, startJigsawName, size, startHeight, projectStartToHeightmap, maxDistanceFromCenter);
    }

    /**
     * Check if the Structure can spawn
     *
     * @param context {@link GenerationContext The Structure generation context}
     * @return {@link Boolean True if the Structure Y coordinate if greater than 60}
     */
    @Override
    protected boolean extraSpawningChecks(final Structure.GenerationContext context) {
        return  this.getLowestYIn5by5BoxOffset7Blocks(context, Rotation.getRandom(context.random())).getY() < 60 && super.extraSpawningChecks(context);
    }
}
