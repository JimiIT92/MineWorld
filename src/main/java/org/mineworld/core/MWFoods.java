package org.mineworld.core;

import com.mojang.datafixers.util.Pair;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.block.CornBlock;

/**
 * {@link MineWorld MineWorld} {@link FoodProperties food properties}
 */
public final class MWFoods {

    //#region Food properties

    public static final FoodProperties COB_FOOD = basicProperties(2, 0.1F).build();
    public static final FoodProperties COOKED_COB_FOOD = cookedProperties(COB_FOOD).build();

    //#endregion

    //#region Foods

    public static final RegistryObject<Block> CORN = MWBlocks.registerBlockWithoutBlockItem("corn", CornBlock::new);
    public static final RegistryObject<Item> CORN_SEEDS = MWItems.registerItem("corn_seeds", () -> new ItemNameBlockItem(CORN.get(), MWItems.basicProperties()));
    public static final RegistryObject<Item> COB = registerFood("cob", MWFoods.COB_FOOD);
    public static final RegistryObject<Item> COOKED_COB = registerFood("cooked_cob", MWFoods.COOKED_COB_FOOD);

    //#endregion

    /**
     * Register a {@link FoodProperties food item}
     *
     * @param name {@link String The item name}
     * @param foodProperties {@link FoodProperties The food properties}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    public static RegistryObject<Item> registerFood(final String name, final FoodProperties foodProperties, final FeatureFlag... featureFlags) {
        return MWItems.registerItem(name, () -> new Item(MWItems.basicProperties(featureFlags).food(foodProperties)));
    }

    /**
     * Create some {@link FoodProperties basic food properties}
     *
     * @param nutrition {@link Integer How much hunger this food will restore}
     * @param saturation {@link Float How much saturation this food will restore}
     * @return {@link FoodProperties.Builder Food properties builder}
     */
    private static FoodProperties.Builder basicProperties(final int nutrition, final float saturation) {
        return new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation);
    }

    /**
     * Create a cooked variant of {@link FoodProperties the provided food properties}
     *
     * @param foodProperties {@link FoodProperties The raw food properties}
     * @return {@link FoodProperties.Builder The cooked food properties builder}
     */
    private static FoodProperties.Builder cookedProperties(final FoodProperties foodProperties) {
        return from(foodProperties).nutrition(foodProperties.getNutrition() * 2).saturationMod(foodProperties.getSaturationModifier() * 2);
    }

    /**
     * Get a {@link FoodProperties.Builder food properties builder} instance from an existing {@link FoodProperties food properties}
     *
     * @param foodProperties {@link FoodProperties The food properties to get the builder instance}
     * @return {@link FoodProperties.Builder The food properties builder instance}
     */
    private static FoodProperties.Builder from(final FoodProperties foodProperties) {
        FoodProperties.Builder cookedProperties = new FoodProperties.Builder()
                .nutrition(foodProperties.getNutrition())
                .saturationMod(foodProperties.getSaturationModifier());
        if(foodProperties.isFastFood()) {
            cookedProperties = cookedProperties.fast();
        }
        if(foodProperties.isMeat()) {
            cookedProperties = cookedProperties.meat();
        }
        if(foodProperties.canAlwaysEat()) {
            cookedProperties = cookedProperties.alwaysEat();
        }
        for(Pair<MobEffectInstance, Float> effect : foodProperties.getEffects()) {
            cookedProperties = cookedProperties.effect(effect::getFirst, effect.getSecond());
        }
        return cookedProperties;
    }

    /**
     * Register the {@link MineWorld MineWorld} {@link Item foods}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(final IEventBus eventBus) { }
}
