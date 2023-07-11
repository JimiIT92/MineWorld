package org.mineworld.core;

import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegisterHelper;
import org.mineworld.world.structure.IceCastle;

/**
 * {@link MineWorld MineWorld} {@link StructureType structure types}
 */
public class MWStructures {

    public static RegistryObject<StructureType<IceCastle>> ICE_CASTLE = RegisterHelper.registerStructureType("ice_castle", IceCastle.CODEC);

    /**
     * Register the {@link MineWorld MineWorld} {@link StructureType structure types}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(IEventBus eventBus) {
        RegisterHelper.registerStructureTypes(eventBus);
    }

}