package org.mineworld.core;

import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.Items;
import org.mineworld.MineWorld;
import org.mineworld.entity.MWPrimedTnt;
import org.mineworld.entity.vehicle.MWBoat;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.helper.RegisterHelper;

import java.util.Map;

/**
 * {@link MineWorld MineWorld} {@link DispenseItemBehavior dispense id behaviors}
 */
public final class MWDispenseBehaviors {

    /**
     * Register the {@link DispenseItemBehavior dispense id behaviors}
     */
    public static void registerDispenseBehaviors() {
        RegisterHelper.registerDispenseBehaviors(Map.of(
                PropertyHelper.tntDispenseItemBehavior(MWPrimedTnt.Type.DISGUISED_DIRT), MWBlocks.DISGUISED_DIRT_TNT,
                PropertyHelper.tntDispenseItemBehavior(MWPrimedTnt.Type.DISGUISED_GRASS), MWBlocks.DISGUISED_GRASS_TNT,
                PropertyHelper.tntDispenseItemBehavior(MWPrimedTnt.Type.DISGUISED_SAND), MWBlocks.DISGUISED_SAND_TNT,
                PropertyHelper.tntDispenseItemBehavior(MWPrimedTnt.Type.DISGUISED_RED_SAND), MWBlocks.DISGUISED_RED_SAND_TNT,
                PropertyHelper.tntDispenseItemBehavior(MWPrimedTnt.Type.DISGUISED_STONE), MWBlocks.DISGUISED_STONE_TNT,
                PropertyHelper.tntDispenseItemBehavior(MWPrimedTnt.Type.DISGUISED_CAKE), MWBlocks.DISGUISED_CAKE_TNT,
                PropertyHelper.tntDispenseItemBehavior(MWPrimedTnt.Type.MEGA), MWBlocks.MEGA_TNT,
                PropertyHelper.tntDispenseItemBehavior(MWPrimedTnt.Type.SUPER), MWBlocks.SUPER_TNT,
                PropertyHelper.tntDispenseItemBehavior(MWPrimedTnt.Type.HYPER), MWBlocks.HYPER_TNT
                )
        );
        RegisterHelper.registerDispenseBehaviors(
                PropertyHelper.horseArmorItemDispenseBehavior(),
                MWItems.CHAINMAIL_HORSE_ARMOR,
                MWItems.EMERALD_HORSE_ARMOR,
                MWItems.RUBY_HORSE_ARMOR,
                MWItems.SAPPHIRE_HORSE_ARMOR,
                MWItems.NETHERITE_HORSE_ARMOR,
                MWItems.ALUMINUM_HORSE_ARMOR,
                MWItems.BRONZE_HORSE_ARMOR,
                MWItems.COPPER_HORSE_ARMOR,
                MWItems.SILVER_HORSE_ARMOR
        );
        RegisterHelper.registerDispenseBehaviors(
                PropertyHelper.pebbleDispenseBehavior(),
                MWItems.STONE_PEBBLE,
                MWItems.COBBLESTONE_PEBBLE,
                MWItems.MOSSY_STONE_PEBBLE,
                MWItems.MOSSY_COBBLESTONE_PEBBLE,
                MWItems.SMOOTH_STONE_PEBBLE,
                MWItems.STONE_BRICKS_PEBBLE,
                MWItems.MOSSY_STONE_BRICKS_PEBBLE,
                MWItems.GRANITE_PEBBLE,
                MWItems.POLISHED_GRANITE_PEBBLE,
                MWItems.DIORITE_PEBBLE,
                MWItems.POLISHED_DIORITE_PEBBLE,
                MWItems.ANDESITE_PEBBLE,
                MWItems.POLISHED_ANDESITE_PEBBLE,
                MWItems.DEEPSLATE_PEBBLE,
                MWItems.COBBLED_DEEPSLATE_PEBBLE,
                MWItems.POLISHED_DEEPSLATE_PEBBLE,
                MWItems.DEEPSLATE_BRICKS_PEBBLE,
                MWItems.DEEPSLATE_TILES_PEBBLE,
                MWItems.REINFORCED_DEEPSLATE_PEBBLE,
                MWItems.BRICKS_PEBBLE,
                MWItems.MUD_BRICKS_PEBBLE,
                MWItems.SANDSTONE_PEBBLE,
                MWItems.SMOOTH_SANDSTONE_PEBBLE,
                MWItems.RED_SANDSTONE_PEBBLE,
                MWItems.SMOOTH_RED_SANDSTONE_PEBBLE,
                MWItems.PRISMARINE_PEBBLE,
                MWItems.PRISMARINE_BRICKS_PEBBLE,
                MWItems.DARK_PRISMARINE_PEBBLE,
                MWItems.NETHERRACK_PEBBLE,
                MWItems.NETHER_BRICKS_PEBBLE,
                MWItems.RED_NETHER_BRICKS_PEBBLE,
                MWItems.BASALT_PEBBLE,
                MWItems.SMOOTH_BASALT_PEBBLE,
                MWItems.POLISHED_BASALT_PEBBLE,
                MWItems.BLACKSTONE_PEBBLE,
                MWItems.POLISHED_BLACKSTONE_PEBBLE,
                MWItems.POLISHED_BLACKSTONE_BRICKS_PEBBLE,
                MWItems.GILDED_BLACKSTONE_PEBBLE,
                MWItems.END_STONE_PEBBLE,
                MWItems.END_STONE_BRICKS_PEBBLE,
                MWItems.PURPUR_PEBBLE,
                MWItems.PURPUR_PILLAR_PEBBLE,
                MWItems.QUARTZ_PEBBLE,
                MWItems.SMOOTH_QUARTZ_PEBBLE,
                MWItems.QUARTZ_BRICKS_PEBBLE,
                MWItems.QUARTZ_PILLAR_PEBBLE,
                MWItems.TERRACOTTA_PEBBLE,
                MWItems.WHITE_TERRACOTTA_PEBBLE,
                MWItems.ORANGE_TERRACOTTA_PEBBLE,
                MWItems.MAGENTA_TERRACOTTA_PEBBLE,
                MWItems.LIGHT_BLUE_TERRACOTTA_PEBBLE,
                MWItems.YELLOW_TERRACOTTA_PEBBLE,
                MWItems.LIME_TERRACOTTA_PEBBLE,
                MWItems.PINK_TERRACOTTA_PEBBLE,
                MWItems.GRAY_TERRACOTTA_PEBBLE,
                MWItems.LIGHT_GRAY_TERRACOTTA_PEBBLE,
                MWItems.CYAN_TERRACOTTA_PEBBLE,
                MWItems.PURPLE_TERRACOTTA_PEBBLE,
                MWItems.BLUE_TERRACOTTA_PEBBLE,
                MWItems.BROWN_TERRACOTTA_PEBBLE,
                MWItems.GREEN_TERRACOTTA_PEBBLE,
                MWItems.RED_TERRACOTTA_PEBBLE,
                MWItems.BLACK_TERRACOTTA_PEBBLE,
                MWItems.WHITE_GLAZED_TERRACOTTA_PEBBLE,
                MWItems.ORANGE_GLAZED_TERRACOTTA_PEBBLE,
                MWItems.MAGENTA_GLAZED_TERRACOTTA_PEBBLE,
                MWItems.LIGHT_BLUE_GLAZED_TERRACOTTA_PEBBLE,
                MWItems.YELLOW_GLAZED_TERRACOTTA_PEBBLE,
                MWItems.LIME_GLAZED_TERRACOTTA_PEBBLE,
                MWItems.PINK_GLAZED_TERRACOTTA_PEBBLE,
                MWItems.GRAY_GLAZED_TERRACOTTA_PEBBLE,
                MWItems.LIGHT_GRAY_GLAZED_TERRACOTTA_PEBBLE,
                MWItems.CYAN_GLAZED_TERRACOTTA_PEBBLE,
                MWItems.PURPLE_GLAZED_TERRACOTTA_PEBBLE,
                MWItems.BLUE_GLAZED_TERRACOTTA_PEBBLE,
                MWItems.BROWN_GLAZED_TERRACOTTA_PEBBLE,
                MWItems.GREEN_GLAZED_TERRACOTTA_PEBBLE,
                MWItems.RED_GLAZED_TERRACOTTA_PEBBLE,
                MWItems.BLACK_GLAZED_TERRACOTTA_PEBBLE,
                MWItems.WHITE_CONCRETE_PEBBLE,
                MWItems.ORANGE_CONCRETE_PEBBLE,
                MWItems.MAGENTA_CONCRETE_PEBBLE,
                MWItems.LIGHT_BLUE_CONCRETE_PEBBLE,
                MWItems.YELLOW_CONCRETE_PEBBLE,
                MWItems.LIME_CONCRETE_PEBBLE,
                MWItems.PINK_CONCRETE_PEBBLE,
                MWItems.GRAY_CONCRETE_PEBBLE,
                MWItems.LIGHT_GRAY_CONCRETE_PEBBLE,
                MWItems.CYAN_CONCRETE_PEBBLE,
                MWItems.PURPLE_CONCRETE_PEBBLE,
                MWItems.BLUE_CONCRETE_PEBBLE,
                MWItems.BROWN_CONCRETE_PEBBLE,
                MWItems.GREEN_CONCRETE_PEBBLE,
                MWItems.RED_CONCRETE_PEBBLE,
                MWItems.BLACK_CONCRETE_PEBBLE,
                MWItems.CALCITE_PEBBLE,
                MWItems.TUFF_PEBBLE,
                MWItems.DRIPSTONE_PEBBLE,
                MWItems.OBSIDIAN_PEBBLE,
                MWItems.CRYING_OBSIDIAN_PEBBLE,
                MWItems.MARBLE_PEBBLE,
                MWItems.WHITE_MARBLE_PEBBLE,
                MWItems.ORANGE_MARBLE_PEBBLE,
                MWItems.MAGENTA_MARBLE_PEBBLE,
                MWItems.LIGHT_BLUE_MARBLE_PEBBLE,
                MWItems.YELLOW_MARBLE_PEBBLE,
                MWItems.LIME_MARBLE_PEBBLE,
                MWItems.PINK_MARBLE_PEBBLE,
                MWItems.GRAY_MARBLE_PEBBLE,
                MWItems.LIGHT_GRAY_MARBLE_PEBBLE,
                MWItems.CYAN_MARBLE_PEBBLE,
                MWItems.PURPLE_MARBLE_PEBBLE,
                MWItems.BLUE_MARBLE_PEBBLE,
                MWItems.BROWN_MARBLE_PEBBLE,
                MWItems.GREEN_MARBLE_PEBBLE,
                MWItems.RED_MARBLE_PEBBLE,
                MWItems.BLACK_MARBLE_PEBBLE
        );
        RegisterHelper.registerDispenseBehaviors(PropertyHelper.honeycombDispenseBehavior(), () -> Items.HONEYCOMB);
        RegisterHelper.registerDispenseBehaviors(Map.of(
                PropertyHelper.boatDispenseBehavior(MWBoat.Type.CRIMSON, false), MWItems.CRIMSON_BOAT,
                PropertyHelper.boatDispenseBehavior(MWBoat.Type.CRIMSON, true), MWItems.CRIMSON_CHEST_BOAT,
                PropertyHelper.boatDispenseBehavior(MWBoat.Type.WARPED, false), MWItems.WARPED_BOAT,
                PropertyHelper.boatDispenseBehavior(MWBoat.Type.WARPED, true), MWItems.WARPED_CHEST_BOAT,
                PropertyHelper.boatDispenseBehavior(MWBoat.Type.APPLE, false), MWItems.APPLE_BOAT,
                PropertyHelper.boatDispenseBehavior(MWBoat.Type.APPLE, true), MWItems.APPLE_CHEST_BOAT,
                PropertyHelper.boatDispenseBehavior(MWBoat.Type.PALM, false), MWItems.PALM_RAFT,
                PropertyHelper.boatDispenseBehavior(MWBoat.Type.PALM, true), MWItems.PALM_CHEST_RAFT,
                PropertyHelper.boatDispenseBehavior(MWBoat.Type.DEAD, false), MWItems.DEAD_BOAT,
                PropertyHelper.boatDispenseBehavior(MWBoat.Type.DEAD, true), MWItems.DEAD_CHEST_BOAT
            )
        );
    }

}