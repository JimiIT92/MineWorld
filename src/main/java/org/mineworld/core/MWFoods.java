package org.mineworld.core;

import net.minecraft.world.food.FoodProperties;
import org.mineworld.MineWorld;
import org.mineworld.helper.PropertyHelper;

/**
 * {@link MineWorld MineWorld} {@link FoodProperties food properties}
 */
public class MWFoods {

    public static FoodProperties COB = PropertyHelper.basicFoodProperties(2, 0.1F).build();
    public static FoodProperties BAKED_COB = PropertyHelper.cookedFoodProperties(COB).build();
    public static FoodProperties BLUEBERRIES = PropertyHelper.basicFoodProperties(2, 0.1F).build();

}