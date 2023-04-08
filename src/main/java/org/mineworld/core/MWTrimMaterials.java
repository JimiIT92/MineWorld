package org.mineworld.core;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.armortrim.TrimMaterial;
import org.mineworld.MineWorld;
import org.mineworld.util.KeyHelper;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * {@link MineWorld MineWorld} {@link TrimMaterial trim materials}
 */
public final class MWTrimMaterials {

    //#region Trim Materials

    public static final ResourceKey<TrimMaterial> PRISMARINE = registerKey("prismarine");
    public static final ResourceKey<TrimMaterial> ECHO_SHARD = registerKey("echo_shard");
    public static final ResourceKey<TrimMaterial> RUBY = registerKey("ruby");
    public static final ResourceKey<TrimMaterial> SAPPHIRE = registerKey("sapphire");
    public static final ResourceKey<TrimMaterial> SILVER = registerKey("silver");
    public static final ResourceKey<TrimMaterial> ALUMINUM = registerKey("aluminum");
    public static final ResourceKey<TrimMaterial> BRONZE = registerKey("bronze");
    public static final ResourceKey<TrimMaterial> EMERALD_DARKER = registerKey("emerald_darker");

    //#endregion

    /**
     * Register the {@link TrimMaterial trim materials}
     *
     * @param context {@link BootstapContext<TrimMaterial> The bootstrap context}
     */
    public static void nextUpdate(final BootstapContext<TrimMaterial> context) {
        register(context, PRISMARINE, Items.PRISMARINE_SHARD, MWColors.PRISMARINE, 0.01F, ArmorMaterials.DIAMOND);
        register(context, ECHO_SHARD, Items.ECHO_SHARD, MWColors.ECHO_SHARD, 0.02F);
        register(context, RUBY, MWItems.RUBY.get(), MWColors.RUBY, 0.03F);
        register(context, SAPPHIRE, MWItems.SAPPHIRE.get(), MWColors.SAPPHIRE, 0.04F);
        register(context, SILVER, MWItems.SILVER_INGOT.get(), MWColors.SILVER, 0.05F, ArmorMaterials.CHAIN, ArmorMaterials.IRON);
        register(context, ALUMINUM, MWItems.ALUMINUM_INGOT.get(), MWColors.ALUMINUM, 0.06F);
        register(context, BRONZE, MWItems.BRONZE_INGOT.get(), MWColors.BRONZE, 0.07F);
    }

    /**
     * Register a {@link ResourceKey resource key} for a {@link TrimMaterial trim material}
     *
     * @param name {@link String The trim material name}
     * @return {@link ResourceKey<TrimMaterial> Trim material resource key}
     */
    private static ResourceKey<TrimMaterial> registerKey(final String name) {
        return KeyHelper.register(Registries.TRIM_MATERIAL, name);
    }

    /**
     * Register a {@link TrimMaterial trim material} with darker variants
     *
     * @param context {@link BootstapContext<TrimMaterial> The bootstrap context}
     * @param resourceKey {@link ResourceKey<TrimMaterial> The trim material resource key}
     * @param material {@link Item The item used to apply the trim material}
     * @param color {@link MWColors The text color for the item tooltip}
     * @param itemModelIndex {@link Float The index value for the item model override}
     * @param darkerVariants {@link Map The map of trim materials to be applied when the armor matches a specific armor material}
     */
    private static void register(final BootstapContext<TrimMaterial> context, final ResourceKey<TrimMaterial> resourceKey, final Item material, final MWColors color, final float itemModelIndex, final ArmorMaterials... darkerVariants) {
        final String name = resourceKey.location().getPath();
        final Map<ArmorMaterials, String> variants =
                darkerVariants != null ?
                Arrays.stream(darkerVariants).collect(Collectors.toMap((variant) -> variant, (variant) -> name + "_darker"))
                : Map.of();
        TrimMaterial trimmaterial = TrimMaterial.create(
                name,
                material,
                itemModelIndex,
                Component.translatable(Util.makeDescriptionId("trim_material", resourceKey.location())).withStyle(Style.EMPTY.withColor(color.toText())),
                variants);
        context.register(resourceKey, trimmaterial);
    }
}
