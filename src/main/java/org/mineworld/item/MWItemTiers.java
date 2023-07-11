package org.mineworld.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import org.mineworld.MineWorld;
import org.mineworld.core.MWItems;

/**
 * {@link MineWorld MineWorld} {@link ForgeTier id tiers}
 */
public class MWItemTiers {

    public static ForgeTier ALUMINUM_ITEM_TIER = new ForgeTier(1, 95, 7.0F, 1.0F, 10, BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(MWItems.ALUMINUM_INGOT.get()));
    public static ForgeTier BRONZE_ITEM_TIER = new ForgeTier(1, 190, 8.0F, 1.5F, 10, BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(MWItems.BRONZE_INGOT.get()));
    public static ForgeTier COPPER_ITEM_TIER = new ForgeTier(2, 905, 7.0F, 2.5F, 12, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(Items.COPPER_INGOT));
    public static ForgeTier SILVER_ITEM_TIER = new ForgeTier(2, 905, 7.0F, 2.5F, 12, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(MWItems.SILVER_INGOT.get()));
    public static ForgeTier EMERALD_ITEM_TIER = new ForgeTier(3, 1796, 8.5F, 3.5F, 13, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(Items.EMERALD));
    public static ForgeTier RUBY_ITEM_TIER = new ForgeTier(3, 1796, 8.5F, 3.5F, 13, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(MWItems.RUBY.get()));
    public static ForgeTier SAPPHIRE_ITEM_TIER = new ForgeTier(3, 1796, 8.5F, 3.5F, 13, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(MWItems.SAPPHIRE.get()));

}