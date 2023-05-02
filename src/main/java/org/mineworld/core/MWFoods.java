package org.mineworld.core;

import net.minecraft.world.food.FoodProperties;
import org.mineworld.MineWorld;
import org.mineworld.helper.PropertyHelper;

/**
 * {@link MineWorld MineWorld} {@link FoodProperties food properties}
 */
public final class MWFoods {

    public static final FoodProperties COB = PropertyHelper.basicFoodProperties(2, 0.1F).build();
    public static final FoodProperties BAKED_COB = PropertyHelper.cookedFoodProperties(COB).build();
    public static final FoodProperties BLUEBERRIES = PropertyHelper.basicFoodProperties(2, 0.1F).build();

}