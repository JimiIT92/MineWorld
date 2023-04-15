package org.mineworld.core;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.RegisterHelper;

import java.util.Arrays;
import java.util.List;

/**
 * {@link MineWorld MineWorld} {@link CreativeModeTab creative tabs}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class MWTabs {

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

    /**
     * Register the {@link CreativeModeTab creative tabs}
     *
     * @param event {@link CreativeModeTabEvent.Register Creative mode tab register event}
     */
    @SubscribeEvent
    public static void onRegisterCreativeTabs(final CreativeModeTabEvent.Register event) {
        BUILDING_BLOCKS = RegisterHelper.registerCreativeTab(event, "building_blocks", CreativeModeTabs.SPAWN_EGGS, () -> ItemHelper.getDefaultStack(MWBlocks.SAPPHIRE_BLOCK));
        COLORED_BLOCKS = RegisterHelper.registerCreativeTab(event, "colored_blocks", BUILDING_BLOCKS, () -> ItemHelper.getDefaultStack(MWBlocks.PINK_MARBLE));
        NATURAL = RegisterHelper.registerCreativeTab(event, "natural", COLORED_BLOCKS, () -> ItemHelper.getDefaultStack(MWBlocks.BLUE_ROSE));
        FUNCTIONAL = RegisterHelper.registerCreativeTab(event, "functional", NATURAL, () -> ItemHelper.getDefaultStack(MWBlocks.DAYLIGHT_LAMP));
        REDSTONE = RegisterHelper.registerCreativeTab(event, "redstone", FUNCTIONAL, () -> ItemHelper.getDefaultStack(MWBlocks.GOLDEN_DOOR));
        TOOLS = RegisterHelper.registerCreativeTab(event, "tools", REDSTONE, () -> ItemHelper.getDefaultStack(MWItems.EMERALD_PICKAXE));
        COMBAT = RegisterHelper.registerCreativeTab(event, "combat", TOOLS, () -> ItemHelper.getDefaultStack(MWBlocks.HYPER_TNT));
        FOOD_AND_DRINK = RegisterHelper.registerCreativeTab(event, "food_and_drink", COMBAT, () -> ItemHelper.getDefaultStack(MWItems.COB));
        INGREDIENTS = RegisterHelper.registerCreativeTab(event, "ingredients", FOOD_AND_DRINK, () -> ItemHelper.getDefaultStack(MWItems.RUBY));
        SPAWN_EGGS = RegisterHelper.registerCreativeTab(event, "spawn_eggs", INGREDIENTS, () -> ItemHelper.getDefaultStack(Blocks.BARRIER));
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
            setBuildingBlocksTab(event);
        }
        else if(tab.equals(COLORED_BLOCKS)) {
            setColoredBlocksTab(event);
        }
        else if(tab.equals(NATURAL)) {
            setNaturalBlocksTab(event);
        }
        else if(tab.equals(FUNCTIONAL)) {
            setFunctionalBlocksTab(event);
        }
        else if(tab.equals(REDSTONE)) {
            setRedstoneBlocksTab(event);
        }
        else if(tab.equals(TOOLS)) {
            setToolsTab(event);
        }
        else if(tab.equals(COMBAT)) {
            setCombatTab(event);
        }
        else if(tab.equals(FOOD_AND_DRINK)) {
            setFoodAndDrinkTab(event);
        }
        else if(tab.equals(INGREDIENTS)) {
            setIngredientsTab(event);
        }
        else if(tab.equals(SPAWN_EGGS)) {
            setSpawnEggsTab(event);
        }
    }

    /**
     * Set the content of the {@link #BUILDING_BLOCKS building blocks tab}
     *
     * @param event {@link CreativeModeTabEvent.BuildContents Creative mode tab build contents event}
     */
    private static void setBuildingBlocksTab(final CreativeModeTabEvent.BuildContents event) {
        addToTab(event,
                MWBlocks.CHARCOAL_BLOCK,
                MWBlocks.PYRITE_BLOCK,
                MWBlocks.IRON_STAIRS,
                MWBlocks.IRON_SLAB,
                MWBlocks.CUT_IRON,
                MWBlocks.CUT_IRON_STAIRS,
                MWBlocks.CUT_IRON_SLAB,
                MWBlocks.CUT_IRON_PRESSURE_PLATE,
                MWBlocks.IRON_CAGE,
                MWBlocks.GOLDEN_STAIRS,
                MWBlocks.GOLDEN_SLAB,
                MWBlocks.CUT_GOLD,
                MWBlocks.CUT_GOLDEN_STAIRS,
                MWBlocks.CUT_GOLDEN_SLAB,
                MWBlocks.GOLD_BARS,
                MWBlocks.GOLDEN_DOOR,
                MWBlocks.GOLDEN_TRAPDOOR,
                MWBlocks.CUT_GOLDEN_PRESSURE_PLATE,
                MWBlocks.GOLDEN_CHAIN,
                MWBlocks.GOLDEN_CAGE,
                MWBlocks.COPPER_STAIRS,
                MWBlocks.EXPOSED_COPPER_STAIRS,
                MWBlocks.WEATHERED_COPPER_STAIRS,
                MWBlocks.OXIDIZED_COPPER_STAIRS,
                MWBlocks.WAXED_COPPER_STAIRS,
                MWBlocks.WAXED_EXPOSED_COPPER_STAIRS,
                MWBlocks.WAXED_WEATHERED_COPPER_STAIRS,
                MWBlocks.WAXED_OXIDIZED_COPPER_STAIRS,
                MWBlocks.COPPER_SLAB,
                MWBlocks.EXPOSED_COPPER_SLAB,
                MWBlocks.WEATHERED_COPPER_SLAB,
                MWBlocks.OXIDIZED_COPPER_SLAB,
                MWBlocks.WAXED_COPPER_SLAB,
                MWBlocks.WAXED_EXPOSED_COPPER_SLAB,
                MWBlocks.WAXED_WEATHERED_COPPER_SLAB,
                MWBlocks.WAXED_OXIDIZED_COPPER_SLAB,
                MWBlocks.COPPER_BARS,
                MWBlocks.EXPOSED_COPPER_BARS,
                MWBlocks.WEATHERED_COPPER_BARS,
                MWBlocks.OXIDIZED_COPPER_BARS,
                MWBlocks.WAXED_COPPER_BARS,
                MWBlocks.WAXED_EXPOSED_COPPER_BARS,
                MWBlocks.WAXED_WEATHERED_COPPER_BARS,
                MWBlocks.WAXED_OXIDIZED_COPPER_BARS,
                MWBlocks.COPPER_DOOR,
                MWBlocks.COPPER_TRAPDOOR,
                MWBlocks.EXPOSED_COPPER_TRAPDOOR,
                MWBlocks.WEATHERED_COPPER_TRAPDOOR,
                MWBlocks.OXIDIZED_COPPER_TRAPDOOR,
                MWBlocks.WAXED_COPPER_TRAPDOOR,
                MWBlocks.WAXED_EXPOSED_COPPER_TRAPDOOR,
                MWBlocks.WAXED_WEATHERED_COPPER_TRAPDOOR,
                MWBlocks.WAXED_OXIDIZED_COPPER_TRAPDOOR,
                MWBlocks.COPPER_PRESSURE_PLATE,
                MWBlocks.EXPOSED_COPPER_PRESSURE_PLATE,
                MWBlocks.WEATHERED_COPPER_PRESSURE_PLATE,
                MWBlocks.OXIDIZED_COPPER_PRESSURE_PLATE,
                MWBlocks.WAXED_COPPER_PRESSURE_PLATE,
                MWBlocks.WAXED_EXPOSED_COPPER_PRESSURE_PLATE,
                MWBlocks.WAXED_WEATHERED_COPPER_PRESSURE_PLATE,
                MWBlocks.WAXED_OXIDIZED_COPPER_PRESSURE_PLATE,
                MWBlocks.CUT_COPPER_PRESSURE_PLATE,
                MWBlocks.EXPOSED_CUT_COPPER_PRESSURE_PLATE,
                MWBlocks.WEATHERED_CUT_COPPER_PRESSURE_PLATE,
                MWBlocks.OXIDIZED_CUT_COPPER_PRESSURE_PLATE,
                MWBlocks.WAXED_CUT_COPPER_PRESSURE_PLATE,
                MWBlocks.WAXED_EXPOSED_CUT_COPPER_PRESSURE_PLATE,
                MWBlocks.WAXED_WEATHERED_CUT_COPPER_PRESSURE_PLATE,
                MWBlocks.WAXED_OXIDIZED_CUT_COPPER_PRESSURE_PLATE,
                MWBlocks.COPPER_CHAIN,
                MWBlocks.EXPOSED_COPPER_CHAIN,
                MWBlocks.WEATHERED_COPPER_CHAIN,
                MWBlocks.OXIDIZED_COPPER_CHAIN,
                MWBlocks.WAXED_COPPER_CHAIN,
                MWBlocks.WAXED_EXPOSED_COPPER_CHAIN,
                MWBlocks.WAXED_WEATHERED_COPPER_CHAIN,
                MWBlocks.WAXED_OXIDIZED_COPPER_CHAIN,
                MWBlocks.COPPER_CAGE,
                MWBlocks.EXPOSED_COPPER_CAGE,
                MWBlocks.WEATHERED_COPPER_CAGE,
                MWBlocks.OXIDIZED_COPPER_CAGE,
                MWBlocks.WAXED_COPPER_CAGE,
                MWBlocks.WAXED_EXPOSED_COPPER_CAGE,
                MWBlocks.WAXED_WEATHERED_COPPER_CAGE,
                MWBlocks.WAXED_OXIDIZED_COPPER_CAGE,
                MWBlocks.NETHERITE_STAIRS,
                MWBlocks.NETHERITE_SLAB,
                MWBlocks.RUBY_BLOCK,
                MWBlocks.SAPPHIRE_BLOCK,
                MWBlocks.ALUMINUM_BLOCK,
                MWBlocks.CUT_NETHERITE,
                MWBlocks.CUT_NETHERITE_STAIRS,
                MWBlocks.CUT_NETHERITE_SLAB,
                MWBlocks.NETHERITE_BARS,
                MWBlocks.NETHERITE_DOOR,
                MWBlocks.NETHERITE_TRAPDOOR,
                MWBlocks.NETHERITE_PRESSURE_PLATE,
                MWBlocks.CUT_NETHERITE_PRESSURE_PLATE,
                MWBlocks.NETHERITE_CHAIN,
                MWBlocks.NETHERITE_CAGE,
                MWBlocks.ALUMINUM_STAIRS,
                MWBlocks.ALUMINUM_SLAB,
                MWBlocks.CUT_ALUMINUM,
                MWBlocks.CUT_ALUMINUM_STAIRS,
                MWBlocks.CUT_ALUMINUM_SLAB,
                MWBlocks.ALUMINUM_BARS,
                MWBlocks.ALUMINUM_DOOR,
                MWBlocks.ALUMINUM_TRAPDOOR,
                MWBlocks.ALUMINUM_PRESSURE_PLATE,
                MWBlocks.CUT_ALUMINUM_PRESSURE_PLATE,
                MWBlocks.ALUMINUM_CHAIN,
                MWBlocks.ALUMINUM_CAGE,
                MWBlocks.BRONZE_BLOCK,
                MWBlocks.BRONZE_STAIRS,
                MWBlocks.BRONZE_SLAB,
                MWBlocks.CUT_BRONZE,
                MWBlocks.CUT_BRONZE_STAIRS,
                MWBlocks.CUT_BRONZE_SLAB,
                MWBlocks.BRONZE_BARS,
                MWBlocks.BRONZE_DOOR,
                MWBlocks.BRONZE_TRAPDOOR,
                MWBlocks.BRONZE_PRESSURE_PLATE,
                MWBlocks.CUT_BRONZE_PRESSURE_PLATE,
                MWBlocks.BRONZE_CHAIN,
                MWBlocks.BRONZE_CAGE,
                MWBlocks.SILVER_BLOCK,
                MWBlocks.SILVER_STAIRS,
                MWBlocks.SILVER_SLAB,
                MWBlocks.CUT_SILVER,
                MWBlocks.CUT_SILVER_STAIRS,
                MWBlocks.CUT_SILVER_SLAB,
                MWBlocks.SILVER_BARS,
                MWBlocks.SILVER_DOOR,
                MWBlocks.SILVER_TRAPDOOR,
                MWBlocks.SILVER_PRESSURE_PLATE,
                MWBlocks.CUT_SILVER_PRESSURE_PLATE,
                MWBlocks.SILVER_CHAIN,
                MWBlocks.SILVER_CAGE,
                MWBlocks.RAW_ALUMINUM_BLOCK,
                MWBlocks.RAW_BRONZE_BLOCK,
                MWBlocks.RAW_SILVER_BLOCK
        );
    }

    /**
     * Set the content of the {@link #COLORED_BLOCKS colored blocks tab}
     *
     * @param event {@link CreativeModeTabEvent.BuildContents Creative mode tab build contents event}
     */
    private static void setColoredBlocksTab(final CreativeModeTabEvent.BuildContents event) {
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
                MWBlocks.PINK_MARBLE
        );
    }

    /**
     * Set the content of the {@link #NATURAL natural blocks tab}
     *
     * @param event {@link CreativeModeTabEvent.BuildContents Creative mode tab build contents event}
     */
    private static void setNaturalBlocksTab(final CreativeModeTabEvent.BuildContents event) {
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
                MWItems.CORN_SEEDS
        );
    }

    /**
     * Set the content of the {@link #FUNCTIONAL functional blocks tab}
     *
     * @param event {@link CreativeModeTabEvent.BuildContents Creative mode tab build contents event}
     */
    private static void setFunctionalBlocksTab(final CreativeModeTabEvent.BuildContents event) {
        addToTab(event,
                MWBlocks.GOLDEN_LANTERN,
                MWBlocks.GOLDEN_SOUL_LANTERN,
                MWBlocks.COPPER_LANTERN,
                MWBlocks.EXPOSED_COPPER_LANTERN,
                MWBlocks.WEATHERED_COPPER_LANTERN,
                MWBlocks.OXIDIZED_COPPER_LANTERN,
                MWBlocks.WAXED_COPPER_LANTERN,
                MWBlocks.WAXED_EXPOSED_COPPER_LANTERN,
                MWBlocks.WAXED_WEATHERED_COPPER_LANTERN,
                MWBlocks.WAXED_OXIDIZED_COPPER_LANTERN,
                MWBlocks.COPPER_SOUL_LANTERN,
                MWBlocks.EXPOSED_COPPER_SOUL_LANTERN,
                MWBlocks.WEATHERED_COPPER_SOUL_LANTERN,
                MWBlocks.OXIDIZED_COPPER_SOUL_LANTERN,
                MWBlocks.WAXED_COPPER_SOUL_LANTERN,
                MWBlocks.WAXED_EXPOSED_COPPER_SOUL_LANTERN,
                MWBlocks.WAXED_WEATHERED_COPPER_SOUL_LANTERN,
                MWBlocks.WAXED_OXIDIZED_COPPER_SOUL_LANTERN,
                MWBlocks.NETHERITE_LANTERN,
                MWBlocks.NETHERITE_SOUL_LANTERN,
                MWBlocks.ALUMINUM_LANTERN,
                MWBlocks.ALUMINUM_SOUL_LANTERN,
                MWBlocks.BRONZE_LANTERN,
                MWBlocks.BRONZE_SOUL_LANTERN,
                MWBlocks.SILVER_LANTERN,
                MWBlocks.SILVER_SOUL_LANTERN,
                MWBlocks.GOLDEN_CHAIN,
                MWBlocks.COPPER_CHAIN,
                MWBlocks.EXPOSED_COPPER_CHAIN,
                MWBlocks.WEATHERED_COPPER_CHAIN,
                MWBlocks.OXIDIZED_COPPER_CHAIN,
                MWBlocks.WAXED_COPPER_CHAIN,
                MWBlocks.WAXED_EXPOSED_COPPER_CHAIN,
                MWBlocks.WAXED_WEATHERED_COPPER_CHAIN,
                MWBlocks.WAXED_OXIDIZED_COPPER_CHAIN,
                MWBlocks.NETHERITE_CHAIN,
                MWBlocks.ALUMINUM_CHAIN,
                MWBlocks.BRONZE_CHAIN,
                MWBlocks.SILVER_CHAIN,
                MWBlocks.DAYLIGHT_LAMP
        );
    }

    /**
     * Set the content of the {@link #REDSTONE redstone blocks tab}
     *
     * @param event {@link CreativeModeTabEvent.BuildContents Creative mode tab build contents event}
     */
    private static void setRedstoneBlocksTab(final CreativeModeTabEvent.BuildContents event) {
        addToTab(event,
                MWBlocks.CUT_IRON_PRESSURE_PLATE,
                MWBlocks.CUT_GOLDEN_PRESSURE_PLATE,
                MWBlocks.COPPER_PRESSURE_PLATE,
                MWBlocks.EXPOSED_COPPER_PRESSURE_PLATE,
                MWBlocks.WEATHERED_COPPER_PRESSURE_PLATE,
                MWBlocks.OXIDIZED_COPPER_PRESSURE_PLATE,
                MWBlocks.WAXED_COPPER_PRESSURE_PLATE,
                MWBlocks.WAXED_EXPOSED_COPPER_PRESSURE_PLATE,
                MWBlocks.WAXED_WEATHERED_COPPER_PRESSURE_PLATE,
                MWBlocks.WAXED_OXIDIZED_COPPER_PRESSURE_PLATE,
                MWBlocks.CUT_COPPER_PRESSURE_PLATE,
                MWBlocks.EXPOSED_CUT_COPPER_PRESSURE_PLATE,
                MWBlocks.WEATHERED_CUT_COPPER_PRESSURE_PLATE,
                MWBlocks.OXIDIZED_CUT_COPPER_PRESSURE_PLATE,
                MWBlocks.WAXED_CUT_COPPER_PRESSURE_PLATE,
                MWBlocks.WAXED_EXPOSED_CUT_COPPER_PRESSURE_PLATE,
                MWBlocks.WAXED_WEATHERED_CUT_COPPER_PRESSURE_PLATE,
                MWBlocks.WAXED_OXIDIZED_CUT_COPPER_PRESSURE_PLATE,
                MWBlocks.NETHERITE_PRESSURE_PLATE,
                MWBlocks.CUT_NETHERITE_PRESSURE_PLATE,
                MWBlocks.ALUMINUM_PRESSURE_PLATE,
                MWBlocks.CUT_ALUMINUM_PRESSURE_PLATE,
                MWBlocks.BRONZE_PRESSURE_PLATE,
                MWBlocks.CUT_BRONZE_PRESSURE_PLATE,
                MWBlocks.SILVER_PRESSURE_PLATE,
                MWBlocks.CUT_SILVER_PRESSURE_PLATE,
                MWBlocks.GOLDEN_DOOR,
                MWBlocks.COPPER_DOOR,
                MWBlocks.NETHERITE_DOOR,
                MWBlocks.ALUMINUM_DOOR,
                MWBlocks.BRONZE_DOOR,
                MWBlocks.SILVER_DOOR,
                MWBlocks.GOLDEN_TRAPDOOR,
                MWBlocks.COPPER_TRAPDOOR,
                MWBlocks.EXPOSED_COPPER_TRAPDOOR,
                MWBlocks.WEATHERED_COPPER_TRAPDOOR,
                MWBlocks.OXIDIZED_COPPER_TRAPDOOR,
                MWBlocks.WAXED_COPPER_TRAPDOOR,
                MWBlocks.WAXED_EXPOSED_COPPER_TRAPDOOR,
                MWBlocks.WAXED_WEATHERED_COPPER_TRAPDOOR,
                MWBlocks.WAXED_OXIDIZED_COPPER_TRAPDOOR,
                MWBlocks.NETHERITE_TRAPDOOR,
                MWBlocks.ALUMINUM_TRAPDOOR,
                MWBlocks.BRONZE_TRAPDOOR,
                MWBlocks.SILVER_TRAPDOOR,
                MWBlocks.DISGUISED_GRASS_TNT,
                MWBlocks.DISGUISED_DIRT_TNT,
                MWBlocks.DISGUISED_SAND_TNT,
                MWBlocks.DISGUISED_RED_SAND_TNT,
                MWBlocks.DISGUISED_STONE_TNT,
                MWBlocks.MEGA_TNT,
                MWBlocks.SUPER_TNT,
                MWBlocks.HYPER_TNT,
                MWBlocks.DAYLIGHT_LAMP
        );
    }

    /**
     * Set the content of the {@link #TOOLS tools tab}
     *
     * @param event {@link CreativeModeTabEvent.BuildContents Creative mode tab build contents event}
     */
    private static void setToolsTab(final CreativeModeTabEvent.BuildContents event) {
        addToTab(event,
                MWItems.ALUMINUM_SHOVEL,
                MWItems.ALUMINUM_PICKAXE,
                MWItems.ALUMINUM_AXE,
                MWItems.ALUMINUM_HOE,
                MWItems.BRONZE_SHOVEL,
                MWItems.BRONZE_PICKAXE,
                MWItems.BRONZE_AXE,
                MWItems.BRONZE_HOE,
                MWItems.COPPER_SHOVEL,
                MWItems.COPPER_PICKAXE,
                MWItems.COPPER_AXE,
                MWItems.COPPER_HOE,
                MWItems.SILVER_SHOVEL,
                MWItems.SILVER_PICKAXE,
                MWItems.SILVER_AXE,
                MWItems.SILVER_HOE,
                MWItems.EMERALD_SHOVEL,
                MWItems.EMERALD_PICKAXE,
                MWItems.EMERALD_AXE,
                MWItems.EMERALD_HOE,
                MWItems.RUBY_SHOVEL,
                MWItems.RUBY_PICKAXE,
                MWItems.RUBY_AXE,
                MWItems.RUBY_HOE,
                MWItems.SAPPHIRE_SHOVEL,
                MWItems.SAPPHIRE_PICKAXE,
                MWItems.SAPPHIRE_AXE,
                MWItems.SAPPHIRE_HOE
        );
    }

    /**
     * Set the content of the {@link #COMBAT combat tab}
     *
     * @param event {@link CreativeModeTabEvent.BuildContents Creative mode tab build contents event}
     */
    private static void setCombatTab(final CreativeModeTabEvent.BuildContents event) {
        addToTab(event,
                MWItems.ALUMINUM_SWORD,
                MWItems.BRONZE_SWORD,
                MWItems.COPPER_SWORD,
                MWItems.SILVER_SWORD,
                MWItems.EMERALD_SWORD,
                MWItems.RUBY_SWORD,
                MWItems.SAPPHIRE_SWORD,
                MWItems.ALUMINUM_AXE,
                MWItems.BRONZE_AXE,
                MWItems.COPPER_AXE,
                MWItems.SILVER_AXE,
                MWItems.EMERALD_AXE,
                MWItems.RUBY_AXE,
                MWItems.SAPPHIRE_AXE,
                MWItems.ALUMINUM_HELMET,
                MWItems.ALUMINUM_CHESTPLATE,
                MWItems.ALUMINUM_LEGGINGS,
                MWItems.ALUMINUM_BOOTS,
                MWItems.BRONZE_HELMET,
                MWItems.BRONZE_CHESTPLATE,
                MWItems.BRONZE_LEGGINGS,
                MWItems.BRONZE_BOOTS,
                MWItems.COPPER_HELMET,
                MWItems.COPPER_CHESTPLATE,
                MWItems.COPPER_LEGGINGS,
                MWItems.COPPER_BOOTS,
                MWItems.SILVER_HELMET,
                MWItems.SILVER_CHESTPLATE,
                MWItems.SILVER_LEGGINGS,
                MWItems.SILVER_BOOTS,
                MWItems.EMERALD_HELMET,
                MWItems.EMERALD_CHESTPLATE,
                MWItems.EMERALD_LEGGINGS,
                MWItems.EMERALD_BOOTS,
                MWItems.RUBY_HELMET,
                MWItems.RUBY_CHESTPLATE,
                MWItems.RUBY_LEGGINGS,
                MWItems.RUBY_BOOTS,
                MWItems.SAPPHIRE_HELMET,
                MWItems.SAPPHIRE_CHESTPLATE,
                MWItems.SAPPHIRE_LEGGINGS,
                MWItems.SAPPHIRE_BOOTS,
                MWItems.ALUMINUM_HORSE_ARMOR,
                MWItems.CHAINMAIL_HORSE_ARMOR,
                MWItems.BRONZE_HORSE_ARMOR,
                MWItems.COPPER_HORSE_ARMOR,
                MWItems.SILVER_HORSE_ARMOR,
                MWItems.EMERALD_HORSE_ARMOR,
                MWItems.RUBY_HORSE_ARMOR,
                MWItems.SAPPHIRE_HORSE_ARMOR,
                MWItems.NETHERITE_HORSE_ARMOR,
                MWBlocks.DISGUISED_GRASS_TNT,
                MWBlocks.DISGUISED_DIRT_TNT,
                MWBlocks.DISGUISED_SAND_TNT,
                MWBlocks.DISGUISED_RED_SAND_TNT,
                MWBlocks.DISGUISED_STONE_TNT,
                MWBlocks.MEGA_TNT,
                MWBlocks.SUPER_TNT,
                MWBlocks.HYPER_TNT
        );
    }

    /**
     * Set the content of the {@link #FOOD_AND_DRINK food and drink tab}
     *
     * @param event {@link CreativeModeTabEvent.BuildContents Creative mode tab build contents event}
     */
    private static void setFoodAndDrinkTab(final CreativeModeTabEvent.BuildContents event) {
        addToTab(event,
                MWItems.COB,
                MWItems.BAKED_COB
        );
    }

    /**
     * Set the content of the {@link #INGREDIENTS ingredients tab}
     *
     * @param event {@link CreativeModeTabEvent.BuildContents Creative mode tab build contents event}
     */
    private static void setIngredientsTab(final CreativeModeTabEvent.BuildContents event) {
        addToTab(event,
                MWItems.PYRITE,
                MWItems.RAW_SILVER,
                MWItems.RAW_ALUMINUM,
                MWItems.RAW_BRONZE,
                MWItems.RUBY,
                MWItems.SAPPHIRE,
                MWItems.NETHERITE_NUGGET,
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

    /**
     * Set the content of the {@link #SPAWN_EGGS spawn eggs tab}
     *
     * @param event {@link CreativeModeTabEvent.BuildContents Creative mode tab build contents event}
     */
    private static void setSpawnEggsTab(final CreativeModeTabEvent.BuildContents event) {
        addToTab(event,
                Blocks.STRUCTURE_VOID
        );
    }

    /**
     * Add some {@link T items} to a {@link CreativeModeTab creative tab}
     *
     * @param event {@link CreativeModeTabEvent.BuildContents Creative mode tab build contents event}
     * @param items {@link T The items to add}
     */
    @SafeVarargs
    private static <T extends ItemLike> void addToTab(final CreativeModeTabEvent.BuildContents event, @NotNull final T... items) {
        addToTab(event, Arrays.stream(items).map(ItemHelper::getDefaultStack).toList());
    }

    /**
     * Add some {@link RegistryObject items} to a {@link CreativeModeTab creative tab}
     *
     * @param event {@link CreativeModeTabEvent.BuildContents Creative mode tab build contents event}
     * @param items {@link RegistryObject The items to add}
     */
    @SafeVarargs
    private static void addToTab(final CreativeModeTabEvent.BuildContents event, @NotNull final RegistryObject<? extends ItemLike>... items) {
        addToTab(event, Arrays.stream(items).map(ItemHelper::getDefaultStack).toList());
    }

    /**
     * Add some {@link ItemStack items} to a {@link CreativeModeTab creative tab}
     *
     * @param event {@link CreativeModeTabEvent.BuildContents Creative mode tab build contents event}
     * @param items {@link List<ItemStack> The items to add}
     */
    private static void addToTab(final CreativeModeTabEvent.BuildContents event, @NotNull final List<ItemStack> items) {
        event.acceptAll(items);
    }

}