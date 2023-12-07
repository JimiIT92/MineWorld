package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegisterHelper;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link WoodType wood types}
 */
public final class MWWoodTypes {

    public static final WoodType APPLE = RegisterHelper.registerWoodType("apple");
    public static final WoodType PALM = RegisterHelper.registerWoodType("palm", BlockSetType.BAMBOO, SoundType.BAMBOO_WOOD, SoundType.BAMBOO_WOOD_HANGING_SIGN, SoundEvents.BAMBOO_WOOD_FENCE_GATE_CLOSE, SoundEvents.BAMBOO_WOOD_FENCE_GATE_OPEN);
    public static final WoodType DEAD = RegisterHelper.registerWoodType("dead");
    public static final WoodType ICE = RegisterHelper.registerWoodType("ice", MWBlockSetTypes.ICE, SoundType.GLASS, SoundType.HANGING_SIGN, SoundEvents.GLASS_HIT, SoundEvents.GLASS_PLACE);
    public static final Supplier<WoodType> SCULK = Suppliers.memoize(() -> RegisterHelper.registerWoodType("sculk", MWBlockSetTypes.SCULK.get(), MWSoundTypes.SCULK_WOOD, MWSoundTypes.SCULK_WOOD_HANGING_SIGN, MWSounds.SCULK_FENCE_GATE_CLOSE.get(), MWSounds.SCULK_FENCE_GATE_OPEN.get()));

}