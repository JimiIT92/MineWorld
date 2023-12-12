package org.mineworld.core;

import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockRotProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.ProtectedBlockProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import org.mineworld.MineWorld;
import org.mineworld.helper.KeyHelper;
import org.mineworld.helper.RegisterHelper;

import java.util.List;

/**
 * {@link MineWorld MineWorld} {@link StructureProcessorList structure processors}
 */
public final class MWProcessorLists {

    public static final ResourceKey<StructureProcessorList> SLIGHTLY_DAMAGED = KeyHelper.registerStructureProcessorKey("slightly_damaged");
    public static final ResourceKey<StructureProcessorList> ANCIENT_TEMPLE = KeyHelper.registerStructureProcessorKey("ancient_temple");

    /**
     * Register the {@link StructureProcessorList structure processors}
     *
     * @param context {@link BootstapContext<StructureProcessorList> The bootstrap context}
     */
    public static void bootstrap(final BootstapContext<StructureProcessorList> context) {
        RegisterHelper.registerBlockRotStructureProcessor(context, SLIGHTLY_DAMAGED, 0.9F);
        RegisterHelper.registerStructureProcessor(context, ANCIENT_TEMPLE, List.of(new BlockRotProcessor(0.9F), new ProtectedBlockProcessor(MWTags.Blocks.ANCIENT_TEMPLE_CANNOT_REPLACE)));
    }

}