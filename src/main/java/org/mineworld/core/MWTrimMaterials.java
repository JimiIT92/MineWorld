package org.mineworld.core;

import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.armortrim.TrimMaterial;
import org.mineworld.MineWorld;
import org.mineworld.helper.KeyHelper;
import org.mineworld.helper.RegisterHelper;

/**
 * {@link MineWorld MineWorld} {@link TrimMaterial trim materials}
 */
public class MWTrimMaterials {

    public static ResourceKey<TrimMaterial> PRISMARINE = KeyHelper.registerTrimMaterialKey("prismarine");
    public static ResourceKey<TrimMaterial> ECHO_SHARD = KeyHelper.registerTrimMaterialKey("echo_shard");
    public static ResourceKey<TrimMaterial> RUBY = KeyHelper.registerTrimMaterialKey("ruby");
    public static ResourceKey<TrimMaterial> SAPPHIRE = KeyHelper.registerTrimMaterialKey("sapphire");
    public static ResourceKey<TrimMaterial> SILVER = KeyHelper.registerTrimMaterialKey("silver");
    public static ResourceKey<TrimMaterial> ALUMINUM = KeyHelper.registerTrimMaterialKey("aluminum");
    public static ResourceKey<TrimMaterial> BRONZE = KeyHelper.registerTrimMaterialKey("bronze");
    public static ResourceKey<TrimMaterial> EMERALD_DARKER = KeyHelper.registerTrimMaterialKey("emerald_darker");

    /**
     * Register the {@link TrimMaterial trim materials}
     *
     * @param context {@link BootstapContext<TrimMaterial> The bootstrap context}
     */
    public static void nextUpdate(BootstapContext<TrimMaterial> context) {
        RegisterHelper.registerTrimMaterial(context, PRISMARINE, Items.PRISMARINE_SHARD, MWColors.PRISMARINE, 0.01F, ArmorMaterials.DIAMOND);
        RegisterHelper.registerTrimMaterial(context, ECHO_SHARD, Items.ECHO_SHARD, MWColors.ECHO_SHARD, 0.02F);
        RegisterHelper.registerTrimMaterial(context, RUBY, MWItems.RUBY.get(), MWColors.RUBY, 0.03F);
        RegisterHelper.registerTrimMaterial(context, SAPPHIRE, MWItems.SAPPHIRE.get(), MWColors.SAPPHIRE, 0.04F);
        RegisterHelper.registerTrimMaterial(context, SILVER, MWItems.SILVER_INGOT.get(), MWColors.SILVER, 0.05F, ArmorMaterials.CHAIN, ArmorMaterials.IRON);
        RegisterHelper.registerTrimMaterial(context, ALUMINUM, MWItems.ALUMINUM_INGOT.get(), MWColors.ALUMINUM, 0.06F);
        RegisterHelper.registerTrimMaterial(context, BRONZE, MWItems.BRONZE_INGOT.get(), MWColors.BRONZE, 0.07F);
    }

}
