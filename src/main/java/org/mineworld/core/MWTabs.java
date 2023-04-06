package org.mineworld.core;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link CreativeModeTab creative tabs}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class MWTabs {

    //#region Tabs

    public static CreativeModeTab BUILDING_BLOCKS;
    public static CreativeModeTab COLORED_BLOCKS;
    public static CreativeModeTab NATURAL;
    public static CreativeModeTab FUNCTIONAL;
    public static CreativeModeTab REDSTONE;
    public static CreativeModeTab TOOLS;
    public static CreativeModeTab COMBAT;
    public static CreativeModeTab FOOD_AND_DRINK;
    public static CreativeModeTab INGREDIENTS;
    public static CreativeModeTab SPAWN_EGGS;

    //#endregion

    /**
     * Register the {@link CreativeModeTab creative tabs}
     *
     * @param event {@link CreativeModeTabEvent.Register Creative mode tab register event}
     */
    @SubscribeEvent
    public static void onRegisterCreativeTabs(final CreativeModeTabEvent.Register event) {
        BUILDING_BLOCKS = registerTab(event, "building_blocks", CreativeModeTabs.SPAWN_EGGS, () -> MWItems.getDefaultStack(MWBlocks.SAPPHIRE_BLOCK));
        COLORED_BLOCKS = registerTab(event, "colored_blocks", BUILDING_BLOCKS, () -> MWItems.getDefaultStack(MWBlocks.PINK_MARBLE));
        NATURAL = registerTab(event, "natural", COLORED_BLOCKS, () -> MWItems.getDefaultStack(MWBlocks.BLUE_ROSE));
        FUNCTIONAL = registerTab(event, "functional", NATURAL, MWTabs::getDisabledTabIcon);
        REDSTONE = registerTab(event, "redstone", FUNCTIONAL, MWTabs::getDisabledTabIcon);
        TOOLS = registerTab(event, "tools", REDSTONE, MWTabs::getDisabledTabIcon);
        COMBAT = registerTab(event, "combat", TOOLS, MWTabs::getDisabledTabIcon);
        FOOD_AND_DRINK = registerTab(event, "food_and_drink", COMBAT, () -> MWItems.getDefaultStack(MWItems.COB));
        INGREDIENTS = registerTab(event, "ingredients", FOOD_AND_DRINK, () -> MWItems.getDefaultStack(MWItems.RUBY));
        SPAWN_EGGS = registerTab(event, "spawn_eggs", INGREDIENTS, MWTabs::getDisabledTabIcon);
    }

    /**
     * Get the disabled {@link ItemStack tab icon}
     *
     * @return {@link ItemStack The item stack to use as tab icon}
     */
    private static ItemStack getDisabledTabIcon() {
        return MWItems.getDefaultStack(Blocks.BEDROCK);
    }

    /**
     * Set the {@link CreativeModeTab creative tabs} content
     *
     * @param event {@link CreativeModeTabEvent.BuildContents Creative mode tab build contents event}
     */
    @SubscribeEvent
    public static void onTabBuildContents(final CreativeModeTabEvent.BuildContents event) {
        final CreativeModeTab tab = event.getTab();
        if(tab.equals(BUILDING_BLOCKS)) {
        //woods - stones - storage blocks + derived - metals
            //log
            //wood
            //stripped log
            //stripped wood
            //planks
            //stairs
            //slabs
            //walls
            //fences
            //fence gate
            //door
            //trapdoor
            //pressure plate
            //button
            addToTab(event,
                    MWBlocks.CHARCOAL_BLOCK,
                    MWBlocks.SILVER_BLOCK,
                    MWBlocks.ALUMINUM_BLOCK,
                    MWBlocks.BRONZE_BLOCK,
                    MWBlocks.RUBY_BLOCK,
                    MWBlocks.SAPPHIRE_BLOCK,
                    MWBlocks.PYRITE_BLOCK,
                    MWBlocks.RAW_SILVER_BLOCK,
                    MWBlocks.RAW_ALUMINUM_BLOCK,
                    MWBlocks.RAW_BRONZE_BLOCK);
        }
        else if(tab.equals(COLORED_BLOCKS)) {
            addToTab(event,
                    MWBlocks.MARBLE,
                    MWBlocks.WHITE_MARBLE,
                    MWBlocks.LIGHT_GRAY_MARBLE,
                    MWBlocks.GRAY_MARBLE,
                    MWBlocks.BLACK_MARBLE,
                    MWBlocks.BROWN_MARBLE,
                    MWBlocks.RED_MARBLE,
                    MWBlocks.ORANGE_MARBLE,
                    MWBlocks.YELLOW_MARBLE,
                    MWBlocks.LIME_MARBLE,
                    MWBlocks.GREEN_MARBLE,
                    MWBlocks.CYAN_MARBLE,
                    MWBlocks.LIGHT_BLUE_MARBLE,
                    MWBlocks.BLACK_MARBLE,
                    MWBlocks.PURPLE_MARBLE,
                    MWBlocks.MAGENTA_MARBLE,
                    MWBlocks.PINK_MARBLE);
        }
        else if(tab.equals(NATURAL)) {
            //grass/terrain - stone - ores - nether ores - raw blocks - logs - leaves - saplings - flowers - seeds - deco blocks
            addToTab(event,
                    MWBlocks.MARBLE,
                    MWBlocks.SILVER_ORE,
                    MWBlocks.DEEPSLATE_SILVER_ORE,
                    MWBlocks.ALUMINUM_ORE,
                    MWBlocks.DEEPSLATE_ALUMINUM_ORE,
                    MWBlocks.RUBY_ORE,
                    MWBlocks.DEEPSLATE_RUBY_ORE,
                    MWBlocks.SAPPHIRE_ORE,
                    MWBlocks.DEEPSLATE_SAPPHIRE_ORE,
                    MWBlocks.PYRITE_ORE,
                    MWBlocks.BLUE_ROSE,
                    MWBlocks.WHITE_ROSE,
                    MWBlocks.BLUE_ROSE_BUSH,
                    MWBlocks.WHITE_ROSE_BUSH,
                    MWItems.CORN_SEEDS);
        }
        else if(tab.equals(FUNCTIONAL)) {
            //torches - lanterns - chains - lights - workbenches - ladders - paintings - bookshelfs - signs - chests
            event.accept(Blocks.STRUCTURE_VOID);
        }
        else if(tab.equals(REDSTONE)) {
            //components - buttons (if new kind) - pressure plates (if new kind) - pistons - dropper/hoppers - chests (if new kind) - furnaces (if new kind) - minecarts - boat with chest (if new kind) - doors (if new kind)
            //fence gates (if new kind) - trapdoors (if new kind) - tnt - lamps - other blocks - ore
            event.accept(Blocks.STRUCTURE_VOID);
        }
        else if(tab.equals(TOOLS)) {
        //tools tiers low to high - fluid buckets - unique tools - compasses - clock - useful items - boats(normal + chest) - minecarts - goat horns - music discs
            //shovel
            //pickaxe
            //axe
            //hoe

            event.accept(Blocks.STRUCTURE_VOID);
        }
        else if(tab.equals(COMBAT)) {
            //swords (low tier to high tier)
            //axes (low tier to high tier)
            //trident
            //shield
            //armor (low tier to high tier) -> helmet - chest - legs - boots
            //horse amors (low tier to high tier)
            //totem
            //tnt
            //projectiles
            //bows
            //arrows
            event.accept(Blocks.STRUCTURE_VOID);
        }
        else if(tab.equals(FOOD_AND_DRINK)) {
            //vegetables
            //meat (raw - cooked)
            //fish (raw - cooked)
            //crafted foods (bread, cakes)
            //rotten flesh
            //spider eye
            //stews
            //milk bucket
            //honey/drinks
            //potions (base - increased - level 2) base - splash - lingering
            addToTab(event,
                    MWItems.COB,
                    MWItems.COOKED_COB
            );
        }
        else if(tab.equals(INGREDIENTS)) {
            //raw
            //gems
            //nuggets
            //ingots
            //crafting materials
            //bricks
            //powders
            //banner patterns
            //pottery shard
            //smithing templates
            //enchants
            addToTab(event,
                    MWItems.PYRITE,
                    MWItems.RAW_SILVER,
                    MWItems.RAW_ALUMINUM,
                    MWItems.RAW_BRONZE,
                    MWItems.RUBY,
                    MWItems.SAPPHIRE,
                    MWItems.COPPER_NUGGET,
                    MWItems.SILVER_NUGGET,
                    MWItems.ALUMINUM_NUGGET,
                    MWItems.BRONZE_NUGGET,
                    MWItems.SILVER_INGOT,
                    MWItems.ALUMINUM_INGOT,
                    MWItems.BRONZE_INGOT,
                    MWItems.RAW_BRONZE_SMITHING_TEMPLATE
            );
        }
        else if(tab.equals(SPAWN_EGGS)) {
            event.accept(Blocks.STRUCTURE_VOID);
        }
    }

    //#region Utilities

    /**
     * Register a {@link CreativeModeTab creative tab}
     *
     * @param event {@link CreativeModeTabEvent.Register Creative mode tab register event}
     * @param name {@link String The tab name}
     * @param afterTab After which {@link CreativeModeTab creative tab} this tab should appear
     * @param iconSupplier {@link Supplier<ItemStack> The icon supplier}. Determines which {@link Item item} to use as tab icon
     * @return {@link CreativeModeTab The registered creative mode tab}
     */
    private static CreativeModeTab registerTab(final CreativeModeTabEvent.Register event, final String name, final CreativeModeTab afterTab, final Supplier<ItemStack> iconSupplier) {
        return event.registerCreativeModeTab(new ResourceLocation(MineWorld.MODID, name),
                List.of(),
                afterTab != null ? List.of(afterTab) : List.of(),
                builder -> builder
                        .icon(iconSupplier)
                        .title(Component.translatable("itemGroup." + MineWorld.MODID + "." + name))
                        .build());
    }

    /**
     * Add some {@link RegistryObject items} to a {@link CreativeModeTab creative tab}
     *
     * @param event {@link CreativeModeTabEvent.BuildContents Creative mode tab build contents event}
     * @param items {@link RegistryObject The items to add}
     */
    @SafeVarargs
    private static void addToTab(final CreativeModeTabEvent.BuildContents event, @NotNull final RegistryObject<? extends ItemLike>... items) {
        event.acceptAll(Arrays.stream(items).map(MWItems::getDefaultStack).toList());
    }

    //#endregion
}
