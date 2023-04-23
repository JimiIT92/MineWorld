package org.mineworld;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.mineworld.core.*;
import org.mineworld.helper.RegisterHelper;

import java.util.logging.Logger;

/**
 * MineWorld! Just another Minecraft mod
 */
@Mod(MineWorld.MOD_ID)
public final class MineWorld {

    /**
     * {@link MineWorld MineWorld} {@link String mod ID}
     */
    public static final String MOD_ID = "mineworld";
    /**
     * {@link Logger Logger reference}
     */
    public static final Logger LOGGER = Logger.getLogger(MOD_ID);

    /**
     * Constructor. Initialize the mod
     */
    public MineWorld() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MWItems.register(eventBus);
        MWBlocks.register(eventBus);
        MWEntityTypes.register(eventBus);
        MWBlockEntityTypes.register(eventBus);

        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * Set up the {@link MineWorld MineWorld} client stuffs, like entity renderings
     *
     * @param event {@link FMLClientSetupEvent FML client setup event}
     */
    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(MWEntityTypes::registerRenderers);
    }

    /**
     * Set up the {@link MineWorld MineWorld} common stuffs, like flower pots,
     * entity spawns and dispenser behaviors
     *
     * @param event {@link FMLCommonSetupEvent FML common setup event}
     */
    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(RegisterHelper::registerFlowerPots);
        event.enqueueWork(MWDispenseBehaviors::registerDispenseBehaviors);
    }

}