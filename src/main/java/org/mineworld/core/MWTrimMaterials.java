package org.mineworld.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.armortrim.TrimMaterial;
import org.mineworld.MineWorld;
import org.mineworld.helper.ComponentHelper;
import org.mineworld.helper.RegistryHelper;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * {@link MineWorld MineWorld} {@link TrimMaterial Trim Materials}
 */
public final class MWTrimMaterials {

    //#region Trim Materials

    public static final ResourceKey<TrimMaterial> PRISMARINE = registerTrimMaterialKey("prismarine");
    public static final ResourceKey<TrimMaterial> ECHO_SHARD = registerTrimMaterialKey("echo_shard");
    public static final ResourceKey<TrimMaterial> RUBY = registerTrimMaterialKey("ruby");
    public static final ResourceKey<TrimMaterial> SAPPHIRE = registerTrimMaterialKey("sapphire");
    public static final ResourceKey<TrimMaterial> SILVER = registerTrimMaterialKey("silver");
    public static final ResourceKey<TrimMaterial> ALUMINUM = registerTrimMaterialKey("aluminum");
    public static final ResourceKey<TrimMaterial> BRONZE = registerTrimMaterialKey("bronze");
    public static final ResourceKey<TrimMaterial> EMERALD_DARKER = registerTrimMaterialKey("emerald_darker");

    //#endregion

    //#region Methods

    /**
     * Register a {@link TrimMaterial Trim Material} {@link ResourceKey<TrimMaterial> Resource Key}
     *
     * @param name {@link String The Trim Material name}
     * @return {@link ResourceKey<TrimMaterial> The Trim Material Resource Key}
     */
    private static ResourceKey<TrimMaterial> registerTrimMaterialKey(final String name) {
        return RegistryHelper.register(Registries.TRIM_MATERIAL, name);
    }

    /**
     * Register a {@link TrimMaterial Trim Material}
     *
     * @param context {@link BootstapContext<TrimMaterial> The Bootstrap Context}
     * @param resourceKey {@link ResourceKey<TrimMaterial> The Trim Material Resource Key}
     * @param material {@link Item The Item used to apply the Trim Material}
     * @param color {@link MWColors The text color for the Item Tooltip}
     * @param itemModelIndex {@link Float The index value for the Item Model Override}
     * @param darkerVariants {@link Map The map of Darker Trim Materials to be applied when the armor matches a specific Armor Material}
     */
    public static void registerTrimMaterial(final BootstapContext<TrimMaterial> context, final ResourceKey<TrimMaterial> resourceKey, final Item material, final MWColors color, final float itemModelIndex, final ArmorMaterials... darkerVariants) {
        final String name = resourceKey.location().getPath();
        final Map<ArmorMaterials, String> variants =
                darkerVariants != null ?
                        Arrays.stream(darkerVariants).collect(Collectors.toMap((variant) -> variant, (variant) -> name + "_darker"))
                        : Map.of();
        TrimMaterial trimmaterial = TrimMaterial.create(
                name,
                material,
                itemModelIndex,
                ComponentHelper.trimMaterial(resourceKey).withStyle(Style.EMPTY.withColor(color.toText())),
                variants);
        context.register(resourceKey, trimmaterial);
    }

    //#endregion

    //#region Register

    /**
     * Register the {@link TrimMaterial trim materials}
     *
     * @param context {@link BootstapContext<TrimMaterial> The bootstrap context}
     */
    public static void nextUpdate(final BootstapContext<TrimMaterial> context) {
        registerTrimMaterial(context, PRISMARINE, Items.PRISMARINE_SHARD, MWColors.PRISMARINE, 0.01F, ArmorMaterials.DIAMOND);
        registerTrimMaterial(context, ECHO_SHARD, Items.ECHO_SHARD, MWColors.SCULK, 0.02F);
        registerTrimMaterial(context, RUBY, MWItems.RUBY.get(), MWColors.RUBY, 0.03F);
        registerTrimMaterial(context, SAPPHIRE, MWItems.SAPPHIRE.get(), MWColors.SAPPHIRE, 0.04F);
        registerTrimMaterial(context, SILVER, MWItems.SILVER_INGOT.get(), MWColors.SILVER, 0.05F, ArmorMaterials.CHAIN, ArmorMaterials.IRON);
        registerTrimMaterial(context, ALUMINUM, MWItems.ALUMINUM_INGOT.get(), MWColors.ALUMINUM, 0.06F);
        registerTrimMaterial(context, BRONZE, MWItems.BRONZE_INGOT.get(), MWColors.BRONZE, 0.07F);
    }

    //#endregion

}