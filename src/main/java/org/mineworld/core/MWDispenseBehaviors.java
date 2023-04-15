package org.mineworld.core;

import net.minecraft.core.dispenser.DispenseItemBehavior;
import org.mineworld.MineWorld;
import org.mineworld.entity.MWPrimedTnt;
import org.mineworld.helper.RegisterHelper;

import java.util.Map;

/**
 * {@link MineWorld MineWorld} {@link DispenseItemBehavior dispense item behaviors}
 */
public final class MWDispenseBehaviors {
    
    /**
     * Register the {@link DispenseItemBehavior dispense item behaviors}
     */
    public static void registerDispenseBehaviors() {
        RegisterHelper.registerTntDispenseBehaviors(Map.of(
                MWBlocks.DISGUISED_DIRT_TNT, MWPrimedTnt.Type.DISGUISED_DIRT,
                MWBlocks.DISGUISED_GRASS_TNT, MWPrimedTnt.Type.DISGUISED_GRASS,
                MWBlocks.DISGUISED_SAND_TNT, MWPrimedTnt.Type.DISGUISED_SAND,
                MWBlocks.DISGUISED_RED_SAND_TNT, MWPrimedTnt.Type.DISGUISED_RED_SAND,
                MWBlocks.DISGUISED_STONE_TNT, MWPrimedTnt.Type.DISGUISED_STONE,
                MWBlocks.MEGA_TNT, MWPrimedTnt.Type.MEGA,
                MWBlocks.SUPER_TNT, MWPrimedTnt.Type.SUPER,
                MWBlocks.HYPER_TNT, MWPrimedTnt.Type.HYPER
            )
        );
        RegisterHelper.registerHorseArmorDispenseBehaviors(
                MWItems.CHAINMAIL_HORSE_ARMOR,
                MWItems.EMERALD_HORSE_ARMOR,
                MWItems.RUBY_HORSE_ARMOR,
                MWItems.SAPPHIRE_HORSE_ARMOR,
                MWItems.NETHERITE_HORSE_ARMOR,
                MWItems.ALUMINUM_HORSE_ARMOR,
                MWItems.BRONZE_HORSE_ARMOR,
                MWItems.COPPER_HORSE_ARMOR,
                MWItems.SILVER_HORSE_ARMOR
        );
    }

}