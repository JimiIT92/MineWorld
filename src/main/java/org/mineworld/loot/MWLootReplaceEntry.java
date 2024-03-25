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
 * @param original {@link Item The Item to replace}
 * @param replacement {@link Item The replacement Item}
 * @param functions {@link LootItemFunction The functions to apply to the Item}
 */
public record MWLootReplaceEntry(Item original, Item replacement, Optional<List<LootItemFunction>> functions) {

    /**
     * The {@link MineWorld MineWorld} {@link MWLootReplaceEntry Loot Item} {@link Codec codec}
     */
    public static final Codec<MWLootReplaceEntry> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    ForgeRegistries.ITEMS.getCodec().fieldOf("original").forGetter(modifier -> modifier.original),
                    ForgeRegistries.ITEMS.getCodec().fieldOf("replacement").forGetter(modifier -> modifier.replacement),
                    LootItemFunctions.CODEC.listOf().optionalFieldOf("functions").forGetter(modifier -> modifier.functions)
            ).apply(instance, MWLootReplaceEntry::new)
    );

}