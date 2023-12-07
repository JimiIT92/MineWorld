package org.mineworld.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;

import java.util.List;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} loot modifier for adding some items to a loot table
 */
public class AddItemsModifier extends LootModifier {

    /**
     * {@link Supplier<Codec> The loot codec supplier}
     */
    public static final Supplier<Codec<AddItemsModifier>> CODEC = Suppliers.memoize(()  ->
            RecordCodecBuilder.create(inst -> codecStart(inst).and(
                    MWLootItem.CODEC.listOf().fieldOf("entries").forGetter(m -> m.entries)
            ).apply(inst, AddItemsModifier::new)));
    /**
     * {@link List<MWLootItem> The list of items to add to the loot}
     */
    private final List<MWLootItem> entries;

    /**
     * Constructor. Set the {@link LootItemCondition loot id conditions}
     *
     * @param lootConditions {@index ILootCondition The conditions that need to be matched before the loot is modified}
     * @param entries {@link List<MWLootItem> The list of items to add to the loot}
     */
    public AddItemsModifier(final LootItemCondition[] lootConditions, final List<MWLootItem> entries) {
        super(lootConditions);
        this.entries = entries;
    }

    /**
     * Add the {@link MWLootItem items} to the loot
     *
     * @param loot {@link ObjectArrayList<ItemStack> The current loot}
     * @param context {@link LootContext The loot context}
     * @return {@link ObjectArrayList<ItemStack> The modified loot}
     */
    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(final ObjectArrayList<ItemStack> loot, final LootContext context) {
        this.entries.forEach(entry -> entry.apply(loot, context));
        return loot;
    }

    /**
     * Get the {@link Codec loot modifier codec}
     *
     * @return {@link #CODEC The loot modifier codec}
     */
    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
