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
import org.mineworld.core.MWStructures;

import java.util.Optional;

/**
 * {@link Structure Implementation class for an Ice Castle structure}
 */
public class IceCastle extends Structure {

    /**
     * {@link StructureTemplatePool The starting structure template pool}
     */
    private final Holder<StructureTemplatePool> startPool;
    /**
     * {@link ResourceLocation The starting structure jigsaw name}
     */
    private final Optional<ResourceLocation> startJigsawName;
    /**
     * {@link Integer The structure size}
     */
    private final int size;
    /**
     * {@link HeightProvider The structure starting height for generation}
     */
    private final HeightProvider startHeight;
    /**
     * {@link Heightmap.Types The structure generation heightmap types}
     */
    private final Optional<Heightmap.Types> projectStartToHeightmap;
    /**
     * {@link Integer The max distance from the structure center} that branches can spawn
     */
    private final int maxDistanceFromCenter;

    /**
     * {@link Codec The structure serialization codec}
     */
    public static final Codec<IceCastle> CODEC = RecordCodecBuilder.<IceCastle>mapCodec(instance ->
            instance.group(IceCastle.settingsCodec(instance),
                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                    Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                    Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter)
            ).apply(instance, IceCastle::new)).codec();

    /**
     * Constructor. Set the structure properties
     *
     * @param config {@link StructureSettings The structure settings}
     * @param startPool {@link StructureTemplatePool The starting structure template pool}
     * @param startJigsawName {@link ResourceLocation The starting structure jigsaw name}
     * @param size {@link Integer The structure size}
     * @param startHeight {@link HeightProvider The structure starting height for generation}
     * @param projectStartToHeightmap {@link Heightmap.Types The structure generation heightmap types}
     * @param maxDistanceFromCenter {@link Integer The max distance from the structure center} that branches can spawn
     */
    protected IceCastle(Structure.StructureSettings config,
                        Holder<StructureTemplatePool> startPool,
                        Optional<ResourceLocation> startJigsawName,
                        int size,
                        HeightProvider startHeight,
                        Optional<Heightmap.Types> projectStartToHeightmap,
                        int maxDistanceFromCenter) {
        super(config);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.size = size;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
    }

    /**
     * Check if the structure can spawn
     *
     * @param context {@link GenerationContext The structure generation context}
     * @return {@link Boolean True if the structure can spawn}
     */
    private static boolean extraSpawningChecks(Structure.GenerationContext context) {
        ChunkPos chunkpos = context.chunkPos();
        return context.chunkGenerator().getFirstOccupiedHeight(
                chunkpos.getMinBlockX(),
                chunkpos.getMinBlockZ(),
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                context.heightAccessor(),
                context.randomState()) < 150;
    }

    /**
     * Find the structure generation point
     *
     * @param context {@link GenerationContext The structure generation context}
     * @return {@link Optional<GenerationStub> The structure generation point, if any}
     */
    @Override
    protected @NotNull Optional<GenerationStub> findGenerationPoint(@NotNull GenerationContext context) {
        if (!extraSpawningChecks(context)) {
            return Optional.empty();
        }
        int startY = this.startHeight.sample(context.random(), new WorldGenerationContext(context.chunkGenerator(), context.heightAccessor()));
        ChunkPos chunkPos = context.chunkPos();
        BlockPos blockPos = new BlockPos(chunkPos.getMinBlockX(), startY, chunkPos.getMinBlockZ());
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
     * Get the {@link StructureType structure type}
     *
     * @return {@link MWStructures#ICE_CASTLE The ice castle structure type}
     */
    @Override
    public @NotNull StructureType<?> type() {
        return MWStructures.ICE_CASTLE.get();
    }
}
