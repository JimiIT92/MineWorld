package org.mineworld.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * {@link MineWorld MineWorld} loot modifier for smelting a drop from a block
 */
public final class FieryTouchModifier extends LootModifier {

    /**
     * {@link Supplier<Codec> The loot codec supplier}
     */
    public static final Supplier<Codec<FieryTouchModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, FieryTouchModifier::new)));

    /**
     * Constructor. Set the {@link LootItemCondition loot id conditions}
     *
     * @param lootConditions {@index ILootCondition The conditions that need to be matched before the loot is modified}
     */
    public FieryTouchModifier(final LootItemCondition[] lootConditions) {
        super(lootConditions);
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
        if(context.hasParam(LootContextParams.BLOCK_STATE)) {
            final Level level = context.getLevel();
            final RecipeManager recipeManager = level.getRecipeManager();
            final RegistryAccess registryAccess = level.registryAccess();

            IntStream.range(0, loot.size()).forEach(index -> {
                final ItemStack drop = loot.get(index);
                final ItemStack smeltedDrop = tryGetSmeltedDrop(recipeManager, RecipeType.SMELTING, level, registryAccess, drop)
                        .orElse(tryGetSmeltedDrop(recipeManager, RecipeType.SMITHING, level, registryAccess, drop)
                                .orElse(tryGetSmeltedDrop(recipeManager, RecipeType.BLASTING, level, registryAccess, drop)
                                        .orElse(tryGetSmeltedDrop(recipeManager, RecipeType.SMOKING, level, registryAccess, drop)
                                                .orElse(tryGetSmeltedDrop(recipeManager, RecipeType.CAMPFIRE_COOKING, level, registryAccess, drop)
                                                        .orElse(ItemStack.EMPTY)))));
                if(!smeltedDrop.isEmpty()) {
                    loot.set(index, smeltedDrop);
                }
            });
        }

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

    /**
     * Try to set the smelted drop to the {@link List<ItemStack> block drop list}
     *
     * @param recipeManager {@link RecipeManager The recipe manager}
     * @param recipeType {@link RecipeType The recipe type}
     * @param level {@link Level The level reference}
     * @param registryAccess {@link RegistryAccess The registry access}
     * @param drop {@link ItemStack The dropped item}
     * @return {@link ItemStack The smelted ItemStack}
     */
    private static <C extends Container, T extends Recipe<C>> Optional<ItemStack> tryGetSmeltedDrop(final RecipeManager recipeManager, final RecipeType<T> recipeType, final Level level, final RegistryAccess registryAccess, final ItemStack drop) {
        return recipeManager.getRecipeFor(recipeType, (C) new SimpleContainer(drop), level)
                .map(recipe -> recipe.value().getResultItem(registryAccess))
                .filter(itemStack -> !itemStack.isEmpty())
                .map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, drop.getCount() * itemStack.getCount()));
    }

}