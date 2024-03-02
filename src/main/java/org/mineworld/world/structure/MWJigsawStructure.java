package org.mineworld.world.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;

import java.util.Optional;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link Structure Jigsaw Structure}
 */
public class MWJigsawStructure extends Structure {

    /**
     * {@link StructureTemplatePool The starting Structure template pool}
     */
    private final Holder<StructureTemplatePool> startPool;
    /**
     * {@link ResourceLocation The starting Structure jigsaw name}
     */
    private final Optional<ResourceLocation> startJigsawName;
    /**
     * {@link Integer The Structure size}
     */
    private final int size;
    /**
     * {@link HeightProvider The Structure starting height for generation}
     */
    private final HeightProvider startHeight;
    /**
     * {@link Heightmap.Types The Structure generation heightmap types}
     */
    private final Optional<Heightmap.Types> projectStartToHeightmap;
    /**
     * {@link Integer The max distance from the Structure center} that branches can spawn
     */
    private final int maxDistanceFromCenter;

    /**
     * {@link Codec The Structure Codec}
     */
    public static final Codec<MWJigsawStructure> CODEC = RecordCodecBuilder.<MWJigsawStructure>mapCodec(instance ->
            instance.group(MWJigsawStructure.settingsCodec(instance),
                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(Structure -> Structure.startPool),
                    ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(Structure -> Structure.startJigsawName),
                    Codec.intRange(0, 30).fieldOf("size").forGetter(Structure -> Structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(Structure -> Structure.startHeight),
                    Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(Structure -> Structure.projectStartToHeightmap),
                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(Structure -> Structure.maxDistanceFromCenter)
            ).apply(instance, MWJigsawStructure::new)).codec();

    /**
     * Constructor. Set the Structure properties
     *
     * @param config {@link StructureSettings The Structure settings}
     * @param startPool {@link StructureTemplatePool The starting Structure template pool}
     * @param startJigsawName {@link ResourceLocation The starting Structure jigsaw name}
     * @param size {@link Integer The Structure size}
     * @param startHeight {@link HeightProvider The Structure starting height for generation}
     * @param projectStartToHeightmap {@link Heightmap.Types The Structure generation heightmap types}
     * @param maxDistanceFromCenter {@link Integer The max distance from the Structure center} that branches can spawn
     */
    protected MWJigsawStructure(final Structure.StructureSettings config, final Holder<StructureTemplatePool> startPool, final Optional<ResourceLocation> startJigsawName, final int size, final HeightProvider startHeight, final Optional<Heightmap.Types> projectStartToHeightmap, final int maxDistanceFromCenter) {
        super(config);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.size = size;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
    }

    /**
     * Check if the Structure can spawn
     *
     * @param context {@link GenerationContext The Structure generation context}
     * @return {@link Boolean True if the Structure can spawn}
     */
    protected boolean extraSpawningChecks(final Structure.GenerationContext context) {
        final ChunkPos chunkPos = context.chunkPos();
        return context.chunkGenerator().getFirstOccupiedHeight(
                chunkPos.getMinBlockX(),
                chunkPos.getMinBlockZ(),
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                context.heightAccessor(),
                context.randomState()) < 150;
    }

    /**
     * Find the Structure generation point
     *
     * @param context {@link GenerationContext The Structure generation context}
     * @return {@link Optional<GenerationStub> The Structure generation point, if any}
     */
    @Override
    protected @NotNull Optional<GenerationStub> findGenerationPoint(final @NotNull GenerationContext context) {
        if (!extraSpawningChecks(context)) {
            return Optional.empty();
        }
        final int startY = this.startHeight.sample(context.random(), new WorldGenerationContext(context.chunkGenerator(), context.heightAccessor()));
        final ChunkPos chunkPos = context.chunkPos();
        final BlockPos blockPos = new BlockPos(chunkPos.getMinBlockX(), startY, chunkPos.getMinBlockZ());
        return JigsawPlacement.addPieces(
                context,
                this.startPool,
                this.startJigsawName,
                this.size,
                blockPos,
                false,
                this.projectStartToHeightmap,
                this.maxDistanceFromCenter);
    }

    /**
     * Get the {@link StructureType Structure Type}
     *
     * @return {@link StructureType#JIGSAW The Jigsaw Structure Type}
     */
    @Override
    public @NotNull StructureType<?> type() {
        return StructureType.JIGSAW;
    }
}