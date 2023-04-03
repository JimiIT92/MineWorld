package org.mineworld.core;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import org.mineworld.MineWorld;

import java.util.Map;

public final class MWTrimMaterials {

    public static final ResourceKey<TrimMaterial> RUBY = registerKey("ruby");

    public static void nextUpdate(BootstapContext<TrimMaterial> context) {
        register(context, RUBY, MWItems.RUBY.get(), 0x9F3535, 0.85F);
    }

    private static ResourceKey<TrimMaterial> registerKey(final String name) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, new ResourceLocation(MineWorld.MODID, name));
    }

    private static void register(BootstapContext<TrimMaterial> context, ResourceKey<TrimMaterial> resourceKey, Item material, int color, float itemModelIndex) {
        register(context, resourceKey, material, color, itemModelIndex, Map.of());
    }

    private static void register(BootstapContext<TrimMaterial> context, ResourceKey<TrimMaterial> resourceKey, Item material, int color, float itemModelIndex, Map<ArmorMaterials, String> darkerVariants) {
        TrimMaterial trimmaterial = TrimMaterial.create(
                resourceKey.location().getPath(),
                material,
                itemModelIndex,
                Component.translatable(Util.makeDescriptionId("trim_material", resourceKey.location())).withStyle(Style.EMPTY.withColor(color)),
                darkerVariants);
        context.register(resourceKey, trimmaterial);
    }
}
