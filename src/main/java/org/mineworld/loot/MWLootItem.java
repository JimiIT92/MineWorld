package org.mineworld.loot;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.Deserializers;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.helper.ItemHelper;

import java.util.Arrays;
import java.util.Optional;

/**
 * Record class for a {@link MineWorld MineWorld} loot item
 *
 * @param item {@link Item The item stack to add to the loot}
 * @param chance {@link Float The chance for the id to be added to the loot}
 * @param functions {@link Optional<JsonElement> The serialized functions to apply to the item}
 */
public record MWLootItem(Item item, float chance, Optional<JsonElement> functions) {

    /**
     * {@link MWLootItem The loot id codec}
     */
    public static final Codec<MWLootItem> CODEC = RecordCodecBuilder
            .create(instance -> instance.group(
                            ForgeRegistries.ITEMS.getCodec().fieldOf("item").forGetter(modifier -> modifier.item),
                            Codec.FLOAT.fieldOf("chance").forGetter(modifier -> modifier.chance),
                            ExtraCodecs.JSON.optionalFieldOf("functions").forGetter(modifier -> modifier.functions)
                    ).apply(instance, MWLootItem::new));

    /**
     * Try to add the item to the loot
     *
     * @param loot {@link ObjectArrayList<ItemStack> The current loot}
     * @param context {@link LootContext The loot context}
     * @return {@link ObjectArrayList<ItemStack> The updated loot}
     */
    public @NotNull ObjectArrayList<ItemStack> apply(final ObjectArrayList<ItemStack> loot, final LootContext context) {
        final RandomSource random = context.getRandom();
        if(random.nextFloat() <= chance) {
            final ItemStack itemStack = ItemHelper.getDefaultStack(item);
            functions.ifPresent(func -> {
                LootItemFunction[] itemFunctions = Deserializers.createFunctionSerializer().create().fromJson(func, LootItemFunction[].class);
                if(itemFunctions != null) {
                    Arrays.stream(itemFunctions).forEach(function -> function.apply(itemStack, context));
                }
            });
            loot.add(itemStack);
        }
        return loot;
    }

}