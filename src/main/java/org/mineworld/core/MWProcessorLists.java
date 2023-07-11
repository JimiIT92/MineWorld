package org.mineworld.core;

import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import org.mineworld.MineWorld;
import org.mineworld.helper.KeyHelper;
import org.mineworld.helper.RegisterHelper;

/**
 * {@link MineWorld MineWorld} {@link StructureProcessorList structure processors}
 */
public class MWProcessorLists {

    public static ResourceKey<StructureProcessorList> ICE_CASTLE = KeyHelper.registerStructureProcessorKey("ice_castle");

    /**
     * Register the {@link StructureProcessorList structure processors}
     *
     * @param context {@link BootstapContext<StructureProcessorList> The bootstrap context}
     */
    public static void bootstrap(BootstapContext<StructureProcessorList> context) {
        RegisterHelper.registerBlockRotStructureProcessor(context, ICE_CASTLE, 0.9F);
    }

}