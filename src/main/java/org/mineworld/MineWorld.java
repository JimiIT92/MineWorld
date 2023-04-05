package org.mineworld;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWItems;

import java.util.logging.Logger;

/**
 * MineWorld! Just another Minecraft mod
 */
@Mod(MineWorld.MODID)
public final class MineWorld {

    /**
     * {@link MineWorld MineWorld} {@link String mod ID}
     */
    public static final String MODID = "mineworld";
    /**
     * {@link Logger Logger reference}
     */
    public static final Logger LOGGER = Logger.getLogger(MODID);

    /**
     * Constructor. Initialize the mod
     */
    public MineWorld() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MWItems.register(eventBus);
        MWBlocks.register(eventBus);

        eventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * Set up the {@link MineWorld MineWorld} common stuffs, like flower pots,
     * entity spawns and dispenser behaviors
     *
     * @param event {@link FMLCommonSetupEvent FML common setup event}
     */
    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(MWBlocks::registerFlowerPots);
    }
}