package org.mineworld.core;

import com.mojang.datafixers.util.Pair;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import org.mineworld.MineWorld;

/**
 * {@link MineWorld MineWorld} {@link FoodProperties food properties}
 */
public final class MWFoods {

    //#region Foods

    public static final FoodProperties COB = basicProperties(2, 0.1F).build();
    public static final FoodProperties COOKED_COB = cookedProperties(COB).build();

    //#endregion

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
}
