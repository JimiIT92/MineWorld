package org.mineworld.core;

import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegisterHelper;
import org.mineworld.world.structure.MWJigsawStructure;

/**
 * {@link MineWorld MineWorld} {@link StructureType structure types}
 */
public final class MWStructures {

    public static final RegistryObject<StructureType<MWJigsawStructure>> ICE_CASTLE = RegisterHelper.registerStructureType("ice_castle", MWJigsawStructure.CODEC);
    public static final RegistryObject<StructureType<MWJigsawStructure>> ETHEREAL_RUIN = RegisterHelper.registerStructureType("ethereal_ruin", MWJigsawStructure.CODEC);
    public static final RegistryObject<StructureType<MWJigsawStructure>> RUINED_ETHEREAL_PORTAL = RegisterHelper.registerStructureType("ruined_ethereal_portal", MWJigsawStructure.CODEC);

    /**
     * Register the {@link MineWorld MineWorld} {@link StructureType structure types}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(final IEventBus eventBus) {
        RegisterHelper.registerStructureTypes(eventBus);
    }

}