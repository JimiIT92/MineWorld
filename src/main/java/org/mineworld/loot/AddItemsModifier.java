package org.mineworld.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;

import java.util.List;
import java.util.function.Supplier;

/**
 * {@link LootModifier Loot Modifier} for adding some {@link MWLootEntry Items} to a {@link LootTable Loot Table}
 */
public final class AddItemsModifier extends LootModifier {

    /**
     * The {@link MineWorld MineWorld} {@link AddItemsModifier Add Items Modifier} {@link Codec codec}
     */
    public static final Supplier<Codec<AddItemsModifier>> CODEC = Suppliers.memoize(
            () -> RecordCodecBuilder.create(instance ->
                    codecStart(instance).and(
                            MWLootEntry.CODEC.listOf().fieldOf("entries").forGetter(modifier -> modifier.entries)
                    ).apply(instance, AddItemsModifier::new)
            )
    );
    /**
     * {@link List< MWLootEntry > The list of items that can be added to the loot}
     */
    private final List<MWLootEntry> entries;

    /**
     * Constructor. Set the {@link LootItemCondition Loot conditions} and the {@link List< MWLootEntry > Loot entries}
     *
     * @param lootConditions {@link LootItemCondition The ILootConditions that need to be matched before the loot is modified}
     * @param entries {@link List< MWLootEntry > The list of items that can be added to the loot}
     */
    public AddItemsModifier(final LootItemCondition[] lootConditions, final List<MWLootEntry> entries) {
        super(lootConditions);
        this.entries = entries;
    }

    /**
     * Add some {@link MWLootEntry items} to the loot
     *
     * @param generatedLoot {@link ObjectArrayList<ItemStack> The list of ItemStacks that will be dropped, generated by loot tables}
     * @param context {@link LootContext The LootContext, identical to what is passed to loot tables}
     * @return {@link ObjectArrayList<ItemStack> The updated loot}
     */
    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(final ObjectArrayList<ItemStack> generatedLoot, final LootContext context) {
        this.entries.forEach(entry -> apply(entry, generatedLoot, context));
        return generatedLoot;
    }

    /**
     * Try to add an {@link MWLootEntry item} to the loot
     *
     * @param entry {@link MWLootEntry The item to add to the loot}
     * @param generatedLoot {@link ObjectArrayList<ItemStack> The list of ItemStacks that will be dropped, generated by loot tables}
     * @param context {@link LootContext The loot context}
     */
    private void apply(final MWLootEntry entry, final ObjectArrayList<ItemStack> generatedLoot, final LootContext context) {
        final RandomSource random = context.getRandom();
        final float chance = entry.chance().orElse(1F);
        if(random.nextFloat() <= chance) {
            final ItemStack itemStack = entry.item().getDefaultInstance();
            entry.functions().ifPresent(functions -> functions.forEach(function -> function.apply(itemStack, context)));
            generatedLoot.add(itemStack);
        }
    }

    /**
     * Get the {@link Codec Loot Modifier Codec}
     *
     * @return {@link #CODEC The Loot Modifier Codec}
     */
    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }

}