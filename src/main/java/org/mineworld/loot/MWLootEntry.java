package org.mineworld.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions;
import net.minecraftforge.registries.ForgeRegistries;
import org.mineworld.MineWorld;

import java.util.List;
import java.util.Optional;

/**
 * Record class for a {@link MineWorld MineWorld} loot item
 *
 * @param item {@link Item The Item handled by the Loot modifier}
 * @param chance {@link Float The chance for the item to be handled by the Loot modifier}
 * @param functions {@link LootItemFunction The functions to apply to the Item}
 */
public record MWLootEntry(Item item, Optional<Float> chance, Optional<List<LootItemFunction>> functions) {

    /**
     * The {@link MineWorld MineWorld} {@link MWLootEntry Loot Item} {@link Codec codec}
     */
    public static final Codec<MWLootEntry> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    ForgeRegistries.ITEMS.getCodec().fieldOf("item").forGetter(modifier -> modifier.item),
                    Codec.FLOAT.optionalFieldOf("chance").forGetter(modifier -> modifier.chance),
                    LootItemFunctions.CODEC.listOf().optionalFieldOf("functions").forGetter(modifier -> modifier.functions)
            ).apply(instance, MWLootEntry::new)
    );

}