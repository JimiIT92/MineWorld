package org.mineworld.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.ComponentHelper;
import org.mineworld.helper.RegistryHelper;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link CreativeModeTab Creative Mode Tabs}
 */
public final class MWTabs {

    //#region Registry

    /**
     * The {@link DeferredRegister<CreativeModeTab> Creative Mode Tabs Registry}
     */
    private static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = RegistryHelper.registry(Registries.CREATIVE_MODE_TAB);

    //#endregion

    //#region Creative Tabs

    public static RegistryObject<CreativeModeTab> BUILDING_BLOCKS = registerCreativeTab("building_blocks", CreativeModeTabs.SPAWN_EGGS, () -> MWBlocks.GLOWING_OBSIDIAN.get().asItem().getDefaultInstance());
    public static RegistryObject<CreativeModeTab> COLORED_BLOCKS = registerCreativeTab("colored_blocks", BUILDING_BLOCKS, () -> MWColoredBlocks.PINK_MARBLE.get().asItem().getDefaultInstance());
    public static RegistryObject<CreativeModeTab> NATURAL = registerCreativeTab("natural", COLORED_BLOCKS, () -> MWBlocks.HOLLOW_BIRCH_LOG.get().asItem().getDefaultInstance());
    public static RegistryObject<CreativeModeTab> FUNCTIONAL = registerCreativeTab("functional", NATURAL, () -> MWBlocks.NETHERITE_CHAIN.get().asItem().getDefaultInstance());
    public static RegistryObject<CreativeModeTab> REDSTONE = registerCreativeTab("redstone", FUNCTIONAL, () -> MWBlocks.DAYLIGHT_LAMP.get().asItem().getDefaultInstance());
    public static RegistryObject<CreativeModeTab> TOOLS = registerCreativeTab("tools", REDSTONE, () -> MWItems.EMERALD_PICKAXE.get().getDefaultInstance());
    public static RegistryObject<CreativeModeTab> COMBAT = registerCreativeTab("combat", TOOLS, () -> MWItems.SAPPHIRE_SWORD.get().getDefaultInstance());
    public static RegistryObject<CreativeModeTab> FOOD_AND_DRINK = registerCreativeTab("food_and_drink", COMBAT, () -> MWItems.COB.get().getDefaultInstance());
    public static RegistryObject<CreativeModeTab> INGREDIENTS = registerCreativeTab("ingredients", FOOD_AND_DRINK, () -> MWItems.RUBY.get().getDefaultInstance());
    public static RegistryObject<CreativeModeTab> SPAWN_EGGS = registerCreativeTab("spawn_eggs", INGREDIENTS, () -> MWItems.REAPER_SPAWN_EGG.get().getDefaultInstance());

    //#endregion

    //#region Methods

    /**
     * Register a {@link CreativeModeTab Creative Mode Tab}
     *
     * @param name {@link String The Creative Mode Tab name}
     * @param beforeTab {@link RegistryObject<CreativeModeTab> Before which Tab this Creative Mode Tab should appear}
     * @param iconSupplier {@link Supplier The Creative Mode  Tab  Icon supplier}
     * @return {@link RegistryObject<CreativeModeTab> The registered Creative Mode Tab}
     */
    private static RegistryObject<CreativeModeTab> registerCreativeTab(final String name, final RegistryObject<CreativeModeTab> beforeTab, final Supplier<ItemStack> iconSupplier) {
        return registerCreativeTab(name, beforeTab.getKey(), iconSupplier);
    }

    /**
     * Register a {@link CreativeModeTab Creative Mode Tab}
     *
     * @param name {@link String The Creative Mode Tab name}
     * @param beforeTab {@link ResourceKey<CreativeModeTab> Before which Tab this Creative Mode Tab should appear}
     * @param iconSupplier {@link Supplier The Creative Mode  Tab  Icon supplier}
     * @return {@link RegistryObject<CreativeModeTab> The registered Creative Mode Tab}
     */
    private static RegistryObject<CreativeModeTab> registerCreativeTab(final String name, final ResourceKey<CreativeModeTab> beforeTab, final Supplier<ItemStack> iconSupplier) {
        return CREATIVE_TABS.register(name, () -> CreativeModeTab.builder()
                .title(ComponentHelper.tab(name))
                .icon(iconSupplier)
                .withTabsBefore(beforeTab).build()
        );
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link CreativeModeTab Creative Mode Tabs}
     *
     * @param eventBus {@link IEventBus The mod event bus}
     */
    public static void register(final IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }

    //#endregion

}