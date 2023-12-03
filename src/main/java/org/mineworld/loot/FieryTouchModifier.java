package org.mineworld.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;

import java.util.Arrays;
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
    public static final Supplier<Codec<FieryTouchModifier>> CODEC = Suppliers.memoize(()  ->
            RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, FieryTouchModifier::new)));

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
            final RecipeManager recipeManager = context.getLevel().getRecipeManager();
            final List<SmeltingRecipe> smeltingRecipes = recipeManager.getAllRecipesFor(RecipeType.SMELTING).stream().map(RecipeHolder::value).toList();
            final List<SmithingRecipe> smithingRecipe = recipeManager.getAllRecipesFor(RecipeType.SMITHING).stream().map(RecipeHolder::value).toList();
            final List<BlastingRecipe> blastingRecipes = recipeManager.getAllRecipesFor(RecipeType.BLASTING).stream().map(RecipeHolder::value).toList();
            final List<SmokingRecipe> smokingRecipes = recipeManager.getAllRecipesFor(RecipeType.SMOKING).stream().map(RecipeHolder::value).toList();
            final List<CampfireCookingRecipe> campfireRecipes = recipeManager.getAllRecipesFor(RecipeType.CAMPFIRE_COOKING).stream().map(RecipeHolder::value).toList();

            IntStream.range(0, loot.size()).forEach(index -> {
                final ItemStack drop = loot.get(index);
                if(trySetSmeltedDrop(loot, index, campfireRecipes, drop)) {
                    return;
                }
                if(trySetSmeltedDrop(loot, index, smokingRecipes, drop)) {
                    return;
                }
                if(trySetSmeltedDrop(loot, index, blastingRecipes, drop)) {
                    return;
                }
                if(trySetSmeltedDrop(loot, index, smithingRecipe, drop)) {
                    return;
                }
                trySetSmeltedDrop(loot, index, smeltingRecipes, drop);
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
     * @param loot {@link ObjectArrayList<ItemStack> The block drops}
     * @param index {@link Integer The drop list index}
     * @param recipes {@link List<Recipe> The recipes to check into}
     * @param drop {@link ItemStack The drop to smelt}
     * @return {@link Boolean True if the smelted drop has been added}
     */
    private static boolean trySetSmeltedDrop(final ObjectArrayList<ItemStack> loot, final int index, List<? extends Recipe<?>> recipes, final ItemStack drop) {
        final Optional<? extends Recipe<?>> optionalRecipe = recipes.stream()
                .filter(recipe -> recipe.getIngredients().stream()
                        .map(ingredient -> ingredient.getItems())
                        .anyMatch(itemStacks -> Arrays.stream(itemStacks).anyMatch(itemStack -> itemStack.is(drop.getItem()))))
                .findFirst();
        if(optionalRecipe.isEmpty()) {
            return false;
        }
        loot.set(index, optionalRecipe.get().getResultItem(RegistryAccess.EMPTY));
        return true;
    }
}
