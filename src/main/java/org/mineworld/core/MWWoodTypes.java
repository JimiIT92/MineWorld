package org.mineworld.core;

import net.minecraft.world.level.block.state.properties.WoodType;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegisterHelper;

/**
 * {@link MineWorld MineWorld} {@link WoodType wood types}
 */
public final class MWWoodTypes {

    public static final WoodType APPLE = RegisterHelper.registerWoodType("apple");
    public static final WoodType PALM = RegisterHelper.registerWoodType("palm");
    public static final WoodType DEAD = RegisterHelper.registerWoodType("dead");
    public static final WoodType ICE = RegisterHelper.registerWoodType("ice");
    public static final WoodType SCULK = RegisterHelper.registerWoodType("sculk");

}