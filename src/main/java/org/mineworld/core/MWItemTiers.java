package org.mineworld.core;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import org.mineworld.MineWorld;

/**
 * {@link MineWorld MineWorld} {@link ForgeTier Item Tiers}
 */
public final class MWItemTiers {

    //#region Item Tiers

    public static final ForgeTier ALUMINUM_ITEM_TIER = new ForgeTier(1, 95, 7.0F, 1.0F, 10, BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(MWItems.ALUMINUM_INGOT.get()));
    public static final ForgeTier BRONZE_ITEM_TIER = new ForgeTier(1, 190, 8.0F, 1.5F, 10, BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(MWItems.BRONZE_INGOT.get()));
    public static final ForgeTier COPPER_ITEM_TIER = new ForgeTier(2, 905, 7.0F, 2.5F, 12, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(Items.COPPER_INGOT));
    public static final ForgeTier SILVER_ITEM_TIER = new ForgeTier(2, 905, 7.0F, 2.5F, 12, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(MWItems.SILVER_INGOT.get()));
    public static final ForgeTier EMERALD_ITEM_TIER = new ForgeTier(3, 1796, 8.5F, 3.5F, 13, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(Items.EMERALD));
    public static final ForgeTier RUBY_ITEM_TIER = new ForgeTier(3, 1796, 8.5F, 3.5F, 13, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(MWItems.RUBY.get()));
    public static final ForgeTier SAPPHIRE_ITEM_TIER = new ForgeTier(3, 1796, 8.5F, 3.5F, 13, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(MWItems.SAPPHIRE.get()));

    //#endregion

}