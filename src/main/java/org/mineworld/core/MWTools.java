package org.mineworld.core;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;

/**
 * {@link MineWorld MineWorld} {@link Item tools}
 */
public final class MWTools {

    //#region Tool tiers

    public static final ForgeTier ALUMINUM_ITEM_TIER = new ForgeTier(1, 95, 7.0F, 1.0F, 10, BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(MWItems.ALUMINUM_INGOT.get()));
    public static final ForgeTier BRONZE_ITEM_TIER = new ForgeTier(1, 190, 8.0F, 1.5F, 10, BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(MWItems.BRONZE_INGOT.get()));
    public static final ForgeTier COPPER_ITEM_TIER = new ForgeTier(2, 905, 7.0F, 2.5F, 12, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(Items.COPPER_INGOT));
    public static final ForgeTier SILVER_ITEM_TIER = new ForgeTier(2, 905, 7.0F, 2.5F, 12, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(MWItems.SILVER_INGOT.get()));
    public static final ForgeTier EMERALD_ITEM_TIER = new ForgeTier(3, 1796, 8.5F, 3.5F, 13, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(Items.EMERALD));
    public static final ForgeTier RUBY_ITEM_TIER = new ForgeTier(3, 1796, 8.5F, 3.5F, 13, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(MWItems.RUBY.get()));
    public static final ForgeTier SAPPHIRE_ITEM_TIER = new ForgeTier(3, 1796, 8.5F, 3.5F, 13, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(MWItems.SAPPHIRE.get()));

    //#endregion

    //#region Tools

    public static final RegistryObject<Item> ALUMINUM_SWORD = registerSword("aluminum_sword", ALUMINUM_ITEM_TIER);
    public static final RegistryObject<Item> ALUMINUM_SHOVEL = registerShovel("aluminum_shovel", ALUMINUM_ITEM_TIER);
    public static final RegistryObject<Item> ALUMINUM_PICKAXE = registerPickaxe("aluminum_pickaxe", ALUMINUM_ITEM_TIER);
    public static final RegistryObject<Item> ALUMINUM_AXE = registerAxe("aluminum_axe", ALUMINUM_ITEM_TIER, 6.0F, -3.1F);
    public static final RegistryObject<Item> ALUMINUM_HOE = registerHoe("aluminum_hoe", ALUMINUM_ITEM_TIER, -3.0F);
    public static final RegistryObject<Item> BRONZE_SWORD = registerSword("bronze_sword", BRONZE_ITEM_TIER);
    public static final RegistryObject<Item> BRONZE_SHOVEL = registerShovel("bronze_shovel", BRONZE_ITEM_TIER);
    public static final RegistryObject<Item> BRONZE_PICKAXE = registerPickaxe("bronze_pickaxe", BRONZE_ITEM_TIER);
    public static final RegistryObject<Item> BRONZE_AXE = registerAxe("bronze_axe", BRONZE_ITEM_TIER, 6.5F, -3.1F);
    public static final RegistryObject<Item> BRONZE_HOE = registerHoe("bronze_hoe", BRONZE_ITEM_TIER, -2.5F);
    public static final RegistryObject<Item> COPPER_SWORD = registerSword("copper_sword", COPPER_ITEM_TIER);
    public static final RegistryObject<Item> COPPER_SHOVEL = registerShovel("copper_shovel", COPPER_ITEM_TIER);
    public static final RegistryObject<Item> COPPER_PICKAXE = registerPickaxe("copper_pickaxe", COPPER_ITEM_TIER);
    public static final RegistryObject<Item> COPPER_AXE = registerAxe("copper_axe", COPPER_ITEM_TIER, 5.5F, -3.0F);
    public static final RegistryObject<Item> COPPER_HOE = registerHoe("copper_hoe", COPPER_ITEM_TIER, -0.5F);
    public static final RegistryObject<Item> SILVER_SWORD = registerSword("silver_sword", SILVER_ITEM_TIER);
    public static final RegistryObject<Item> SILVER_SHOVEL = registerShovel("silver_shovel", SILVER_ITEM_TIER);
    public static final RegistryObject<Item> SILVER_PICKAXE = registerPickaxe("silver_pickaxe", SILVER_ITEM_TIER);
    public static final RegistryObject<Item> SILVER_AXE = registerAxe("silver_axe", SILVER_ITEM_TIER, 5.5F, -3.0F);
    public static final RegistryObject<Item> SILVER_HOE = registerHoe("silver_hoe", SILVER_ITEM_TIER, -0.5F);
    public static final RegistryObject<Item> EMERALD_SWORD = registerSword("emerald_sword", EMERALD_ITEM_TIER);
    public static final RegistryObject<Item> EMERALD_SHOVEL = registerShovel("emerald_shovel", EMERALD_ITEM_TIER);
    public static final RegistryObject<Item> EMERALD_PICKAXE = registerPickaxe("emerald_pickaxe", EMERALD_ITEM_TIER);
    public static final RegistryObject<Item> EMERALD_AXE = registerAxe("emerald_axe", EMERALD_ITEM_TIER, 5.0F, -3.0F);
    public static final RegistryObject<Item> EMERALD_HOE = registerHoe("emerald_hoe", EMERALD_ITEM_TIER, 0.0F);
    public static final RegistryObject<Item> RUBY_SWORD = registerSword("ruby_sword", RUBY_ITEM_TIER);
    public static final RegistryObject<Item> RUBY_SHOVEL = registerShovel("ruby_shovel", RUBY_ITEM_TIER);
    public static final RegistryObject<Item> RUBY_PICKAXE = registerPickaxe("ruby_pickaxe", RUBY_ITEM_TIER);
    public static final RegistryObject<Item> RUBY_AXE = registerAxe("ruby_axe", RUBY_ITEM_TIER, 5.0F, -3.0F);
    public static final RegistryObject<Item> RUBY_HOE = registerHoe("ruby_hoe", RUBY_ITEM_TIER, 0.0F);
    public static final RegistryObject<Item> SAPPHIRE_SWORD = registerSword("sapphire_sword", SAPPHIRE_ITEM_TIER);
    public static final RegistryObject<Item> SAPPHIRE_SHOVEL = registerShovel("sapphire_shovel", SAPPHIRE_ITEM_TIER);
    public static final RegistryObject<Item> SAPPHIRE_PICKAXE = registerPickaxe("sapphire_pickaxe", SAPPHIRE_ITEM_TIER);
    public static final RegistryObject<Item> SAPPHIRE_AXE = registerAxe("sapphire_axe", SAPPHIRE_ITEM_TIER, 5.0F, -3.0F);
    public static final RegistryObject<Item> SAPPHIRE_HOE = registerHoe("sapphire_hoe", SAPPHIRE_ITEM_TIER, 0.0F);

    //#endregion

    /**
     * Register a {@link SwordItem sword}
     *
     * @param name {@link String The item name}
     * @param tier {@link Tier The item tier}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    private static RegistryObject<Item> registerSword(final String name, final Tier tier, final FeatureFlag... featureFlags) {
        return MWItems.registerItem(name, () -> new SwordItem(tier, 3, -2.4F, MWItems.basicProperties(featureFlags)));
    }

    /**
     * Register a {@link ShovelItem shovel}
     *
     * @param name {@link String The item name}
     * @param tier {@link Tier The item tier}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    private static RegistryObject<Item> registerShovel(final String name, final Tier tier, final FeatureFlag... featureFlags) {
        return MWItems.registerItem(name, () -> new ShovelItem(tier, 1.5F, -3.0F, MWItems.basicProperties(featureFlags)));
    }

    /**
     * Register a {@link PickaxeItem pickaxe}
     *
     * @param name {@link String The item name}
     * @param tier {@link Tier The item tier}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    private static RegistryObject<Item> registerPickaxe(final String name, final Tier tier, final FeatureFlag... featureFlags) {
        return MWItems.registerItem(name, () -> new PickaxeItem(tier, 1, -2.8F, MWItems.basicProperties(featureFlags)));
    }

    /**
     * Register a {@link AxeItem axe}
     *
     * @param name {@link String The item name}
     * @param tier {@link Tier The item tier}
     * @param attackDamageBonus {@link Float The axe attack damage bonus}
     * @param attackSpeed {@link Float The axe attack speed}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    private static RegistryObject<Item> registerAxe(final String name, final Tier tier, final float attackDamageBonus, final float attackSpeed, final FeatureFlag... featureFlags) {
        return MWItems.registerItem(name, () -> new AxeItem(tier, attackDamageBonus, attackSpeed, MWItems.basicProperties(featureFlags)));
    }

    /**
     * Register a {@link HoeItem hoe}
     *
     * @param name {@link String The item name}
     * @param tier {@link Tier The item tier}
     * @param attackSpeed {@link Float The hoe attack speed}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    private static RegistryObject<Item> registerHoe(final String name, final Tier tier, final float attackSpeed, final FeatureFlag... featureFlags) {
        final ForgeTier hoeTier = new ForgeTier(tier.getLevel(), tier.getUses(), tier.getSpeed(), 0, tier.getEnchantmentValue(), tier.getTag(), tier::getRepairIngredient);
        return MWItems.registerItem(name, () -> new HoeItem(hoeTier, 0, attackSpeed, MWItems.basicProperties(featureFlags)));
    }

    /**
     * Register the {@link MineWorld MineWorld} {@link Item items}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(final IEventBus eventBus) { }
}
