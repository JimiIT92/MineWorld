package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import org.mineworld.MineWorld;
import org.mineworld.world.worldgen.tree.AppleTreeGrower;
import org.mineworld.world.worldgen.tree.MWTreeGrower;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link AbstractTreeGrower Tree Growers}
 */
public final class MWTreeGrowers {

    //#region Tree Growers

    public static final Supplier<AbstractTreeGrower> APPLE_TREE_GROWER = Suppliers.memoize(AppleTreeGrower::new);
    public static final Supplier<AbstractTreeGrower> PALM_TREE_GROWER = Suppliers.memoize(() -> new MWTreeGrower(Suppliers.memoize(() -> MWConfiguredFeatures.PALM_TREE)));
    public static final Supplier<AbstractTreeGrower> SCULK_TREE_GROWER = Suppliers.memoize(() -> new MWTreeGrower(Suppliers.memoize(() -> MWConfiguredFeatures.SCULK_TREE)));

    //#endregion
}