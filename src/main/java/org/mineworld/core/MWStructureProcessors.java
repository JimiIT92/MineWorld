package org.mineworld.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockRotProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.ProtectedBlockProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegistryHelper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@link MineWorld MineWorld} {@link StructureProcessorList Structure Processors}
 */
public final class MWStructureProcessors {

    //#region Structure Processors

    public static final ResourceKey<StructureProcessorList> SLIGHTLY_DAMAGED = registerStructureProcessorKey("slightly_damaged");
    public static final ResourceKey<StructureProcessorList> ANCIENT_TEMPLE = registerStructureProcessorKey("ancient_temple");

    //#endregion

    //#region Methods

    /**
     * Register a {@link StructureProcessorList Structure Processor} {@link ResourceKey <StructureProcessorList> Resource Key}
     *
     * @param name {@link String The Structure Processor name}
     * @return {@link ResourceKey<StructureProcessorList> The Structure Processor Resource Key}
     */
    private static ResourceKey<StructureProcessorList> registerStructureProcessorKey(final String name) {
        return RegistryHelper.register(Registries.PROCESSOR_LIST, name);
    }

    /**
     * Get a {@link BlockRotProcessor Block Rot Processor}
     *
     * @param integrity {@link Float The Structure integrity}
     * @return {@link BlockRotProcessor The Block Rot Processor}
     */
    private static BlockRotProcessor getBlockRotProcessor(final float integrity) {
        return new BlockRotProcessor(integrity);
    }

    /**
     * Register a {@link StructureProcessorList Structure Processor}
     *
     * @param context {@link BootstapContext<StructureProcessorList> The Structure Processor bootstrap context}
     * @param key {@link ResourceKey<StructureProcessorList> The Structure Processor Resource Key}
     * @param structureProcessors {@link List<StructureProcessor> The Structure Processors}
     */
    public static void registerStructureProcessor(final BootstapContext<StructureProcessorList> context, final ResourceKey<StructureProcessorList> key, final StructureProcessor... structureProcessors) {
        context.register(key, new StructureProcessorList(Arrays.stream(structureProcessors).collect(Collectors.toList())));
    }

    //#endregion

    //#region Register

    /**
     * Register all {@link StructureProcessorList Structure Processors}
     *
     * @param context {@link BootstapContext<StructureProcessorList> The Bootstrap Context}
     */
    public static void bootstrap(final BootstapContext<StructureProcessorList> context) {
        final BlockRotProcessor slightlyDamagedProcessor = getBlockRotProcessor(0.9F);
        registerStructureProcessor(context, SLIGHTLY_DAMAGED, slightlyDamagedProcessor);
        registerStructureProcessor(context, ANCIENT_TEMPLE, slightlyDamagedProcessor, new ProtectedBlockProcessor(MWTags.Blocks.ANCIENT_TEMPLE_CANNOT_REPLACE));
    }

    //#endregion

}