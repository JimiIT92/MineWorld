package org.mineworld.item;

import net.minecraft.world.entity.vehicle.MinecartChest;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWItems;
import org.mineworld.core.MWWoodTypes;

/**
 * {@link MineWorld MineWorld} {@link MinecartChest Chest Minecart}
 */
public class MWMinecartChestItem extends MWMinecartItem {

    /**
     * Constructor. Set the {@link Properties Item Properties}
     *
     * @param type         {@link Type The minecart type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     */
    public MWMinecartChestItem(final Type type, final FeatureFlag... featureFlags) {
        super(type, featureFlags);
    }

    /**
     * Get the appropriate {@link Item Chest Minecart Item} based on the {@link WoodType Chest Wood Type}
     *
     * @param woodType {@link WoodType The Chest Wood Type}
     * @return {@link Item The Chest Minecart Item}
     */
    public static Item getChestMinecartItem(final WoodType woodType) {
        if(woodType.equals(WoodType.SPRUCE)) {
            return MWItems.SPRUCE_CHEST_MINECART.get();
        }
        if(woodType.equals(WoodType.BIRCH)) {
            return MWItems.BIRCH_CHEST_MINECART.get();
        }
        if(woodType.equals(WoodType.JUNGLE)) {
            return MWItems.JUNGLE_CHEST_MINECART.get();
        }
        if(woodType.equals(WoodType.ACACIA)) {
            return MWItems.ACACIA_CHEST_MINECART.get();
        }
        if(woodType.equals(WoodType.DARK_OAK)) {
            return MWItems.DARK_OAK_CHEST_MINECART.get();
        }
        if(woodType.equals(WoodType.MANGROVE)) {
            return MWItems.MANGROVE_CHEST_MINECART.get();
        }
        if(woodType.equals(WoodType.CHERRY)) {
            return MWItems.CHERRY_CHEST_MINECART.get();
        }
        if(woodType.equals(WoodType.BAMBOO)) {
            return MWItems.BAMBOO_CHEST_MINECART.get();
        }
        if(woodType.equals(WoodType.CRIMSON)) {
            return MWItems.CRIMSON_CHEST_MINECART.get();
        }
        if(woodType.equals(WoodType.WARPED)) {
            return MWItems.WARPED_CHEST_MINECART.get();
        }
        if(woodType.equals(MWWoodTypes.APPLE.get())) {
            return MWItems.APPLE_CHEST_MINECART.get();
        }
        if(woodType.equals(MWWoodTypes.PALM.get())) {
            return MWItems.PALM_CHEST_MINECART.get();
        }
        if(woodType.equals(MWWoodTypes.DEAD.get())) {
            return MWItems.DEAD_CHEST_MINECART.get();
        }
        if(woodType.equals(MWWoodTypes.ICE.get())) {
            return MWItems.ICE_CHEST_MINECART.get();
        }
        if(woodType.equals(MWWoodTypes.SCULK.get())) {
            return MWItems.SCULK_CHEST_MINECART.get();
        }
        return Items.CHEST_MINECART;
    }

    /**
     * Get the {@link Block Chest Block} based on the {@link WoodType Chest Wood Type}
     *
     * @param woodType {@link WoodType The Chest Wood Type}
     * @return {@link Block The Chest Block}
     */
    public static Block getChestBlock(final WoodType woodType) {
        if(woodType.equals(WoodType.SPRUCE)) {
            return MWBlocks.SPRUCE_CHEST.get();
        }
        if(woodType.equals(WoodType.BIRCH)) {
            return MWBlocks.BIRCH_CHEST.get();
        }
        if(woodType.equals(WoodType.JUNGLE)) {
            return MWBlocks.JUNGLE_CHEST.get();
        }
        if(woodType.equals(WoodType.ACACIA)) {
            return MWBlocks.ACACIA_CHEST.get();
        }
        if(woodType.equals(WoodType.DARK_OAK)) {
            return MWBlocks.DARK_OAK_CHEST.get();
        }
        if(woodType.equals(WoodType.MANGROVE)) {
            return MWBlocks.MANGROVE_CHEST.get();
        }
        if(woodType.equals(WoodType.CHERRY)) {
            return MWBlocks.CHERRY_CHEST.get();
        }
        if(woodType.equals(WoodType.BAMBOO)) {
            return MWBlocks.BAMBOO_CHEST.get();
        }
        if(woodType.equals(WoodType.CRIMSON)) {
            return MWBlocks.CRIMSON_CHEST.get();
        }
        if(woodType.equals(WoodType.WARPED)) {
            return MWBlocks.WARPED_CHEST.get();
        }
        if(woodType.equals(MWWoodTypes.APPLE.get())) {
            return MWBlocks.APPLE_CHEST.get();
        }
        if(woodType.equals(MWWoodTypes.PALM.get())) {
            return MWBlocks.PALM_CHEST.get();
        }
        if(woodType.equals(MWWoodTypes.DEAD.get())) {
            return MWBlocks.DEAD_CHEST.get();
        }
        if(woodType.equals(MWWoodTypes.ICE.get())) {
            return MWBlocks.ICE_CHEST.get();
        }
        if(woodType.equals(MWWoodTypes.SCULK.get())) {
            return MWBlocks.SCULK_CHEST.get();
        }
        return Blocks.CHEST;
    }

}
