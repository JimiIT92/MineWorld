package org.mineworld.core;

import net.minecraft.world.level.block.state.properties.WoodType;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegisterHelper;

/**
 * {@link MineWorld MineWorld} {@link WoodType wood types}
 */
public class MWWoodTypes {

    public static WoodType APPLE = RegisterHelper.registerWoodType("apple");
    public static WoodType PALM = RegisterHelper.registerWoodType("palm");
    public static WoodType DEAD = RegisterHelper.registerWoodType("dead");
    public static WoodType ICE = RegisterHelper.registerWoodType("ice");

}