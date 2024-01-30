package org.mineworld;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.raid.Raid;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.mineworld.client.renderer.MWItemRenderer;
import org.mineworld.core.*;

import java.util.logging.Logger;

/**
 * MineWorld! Just another Minecraft mod
 */
@Mod(MineWorld.MOD_ID)
public final class MineWorld {

    /**
     * The {@link MineWorld MineWorld} {@link String mod ID}
     */
    public static final String MOD_ID = "mineworld";
    /**
     * The {@link Logger Logger reference}
     */
    public static final Logger LOGGER = Logger.getLogger(MOD_ID);
    /**
     * The {@link MineWorld MineWorld} {@link MWItemRenderer custom Item Renderer}
     */
    private static MWItemRenderer ITEMS_RENDERER;

    /**
     * Constructor. Initialize the mod
     */
    public MineWorld() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        this.onModSetup(eventBus);
        eventBus.addListener(this::onCommonSetup);
        eventBus.addListener(this::onClientSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * Set up the mod objects
     *
     * @param eventBus {@link IEventBus The mod event bus}
     */
    private void onModSetup(final IEventBus eventBus) {
        MWParticleTypes.register(eventBus);
        MWSounds.register(eventBus);
        MWTabs.register(eventBus);
        MWBlocks.register(eventBus);
        MWItems.register(eventBus);
        MWEntityTypes.register(eventBus);
        MWBlockEntityTypes.register(eventBus);
        MWStats.register(eventBus);
        MWMenuTypes.register(eventBus);
        MWRecipeSerializers.register(eventBus);
        MWRecipeTypes.register(eventBus);
        MWPoiTypes.register(eventBus);
        MWVillagerProfessions.register(eventBus);
        MWLootModifiers.register(eventBus);

        MWTrunkPlacerTypes.register(eventBus);
        MWFoliagePlacerTypes.register(eventBus);
        MWDimensions.register();
        MWEnchantments.register(eventBus);
        MWStructureTypes.register(eventBus);
    }

    /**
     * Set up the {@link MineWorld MineWorld} common stuffs, like flower pots,
     * entity spawns and dispenser behaviors
     *
     * @param event {@link FMLCommonSetupEvent The FML common setup event}
     */
    private void onCommonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(MWFlowerPots::registerFlowerPotPlants);
        event.enqueueWork(MWDispenseBehaviors::register);
        event.enqueueWork(MWCompostables::registerCompostables);

        event.enqueueWork(() -> Raid.RaiderType.create("illusioner", EntityType.ILLUSIONER, new int[]{0, 0, 0, 0, 1, 1, 2, 2}));
    }

    /**
     * Set up the {@link MineWorld MineWorld} client stuffs, like entity renderers
     *
     * @param event {@link FMLClientSetupEvent FML client setup event}
     */
    private void onClientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(MWEntityTypes::registerRenderers);
        event.enqueueWork(MWBlockEntityTypes::registerRenderers);
        event.enqueueWork(MWMenuTypes::registerScreens);
        event.enqueueWork(MWItems::registerItemProperties);
    }

    /**
     * Get the {@link MineWorld MineWorld} {@link BlockEntityWithoutLevelRenderer custom Item Renderer}
     *
     * @return The {@link MineWorld MineWorld} {@link BlockEntityWithoutLevelRenderer custom Item Renderer}
     */
    public static BlockEntityWithoutLevelRenderer getItemsRenderer() {
        if(ITEMS_RENDERER == null) {
            ITEMS_RENDERER = new MWItemRenderer();
        }
        return ITEMS_RENDERER;
    }

}