package org.mineworld.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.block.weathering.IMWWaxableBlock;
import org.mineworld.entity.MWPrimedTnt;
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.helper.RegisterHelper;

import java.util.Map;
import java.util.Optional;

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
        DispenserBlock.registerBehavior(Items.HONEYCOMB, new OptionalDispenseItemBehavior() {
            public @NotNull ItemStack execute(final @NotNull BlockSource blockSource, final @NotNull ItemStack itemStack) {
                final BlockPos blockpos = blockSource.getPos().relative(blockSource.getBlockState().getValue(DispenserBlock.FACING));
                final Level level = blockSource.getLevel();
                final Optional<BlockState> optionalBlockState = IMWWaxableBlock.getWaxed(level.getBlockState(blockpos));
                if (optionalBlockState.isPresent()) {
                    level.setBlockAndUpdate(blockpos, optionalBlockState.get());
                    level.levelEvent(3003, blockpos, 0);
                    ItemHelper.hurt(itemStack);
                    this.setSuccess(true);
                    return itemStack;
                }
                return super.execute(blockSource, itemStack);
            }
        });
    }

}