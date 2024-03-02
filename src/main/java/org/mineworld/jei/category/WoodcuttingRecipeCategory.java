package org.mineworld.jei.category;

import com.google.common.base.Suppliers;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.block.WoodcutterBlock;
import org.mineworld.core.MWBlocks;
import org.mineworld.helper.ResourceHelper;
import org.mineworld.jei.JEIMineWorldPlugin;
import org.mineworld.recipe.WoodcutterRecipe;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link IRecipeCategory JEI Woodcutting Recipe Category}
 */
public class WoodcuttingRecipeCategory extends MWRecipeCategory<WoodcutterRecipe> {

    /**
     * {@link ResourceLocation The Woodcutting Recipe Category Id}
     */
    public static final ResourceLocation ID = ResourceHelper.resourceLocation("woodcutting");
    /**
     * {@link Supplier<ItemStack> The JEI Screen Icon Supplier}
     */
    public static final Supplier<ItemStack> ICON_SUPPLIER = Suppliers.memoize(() -> MWBlocks.WOODCUTTER.get().asItem().getDefaultInstance());

    /**
     * Constructor. Set the {@link IRecipeCategory Recipe Category properties}
     *
     * @param guiHelper {@link IGuiHelper The GUI Helper}
     */
    public WoodcuttingRecipeCategory(final IGuiHelper guiHelper) {
        super(guiHelper, ICON_SUPPLIER, JEIMineWorldPlugin.WOODCUTTING, WoodcutterBlock.CONTAINER_TITLE);
        this.background = guiHelper.createDrawable(JEIMineWorldPlugin.getVanillaTextureLocation(), 0, 220, 82, 34);
    }

    /**
     * Show a {@link Recipe Recipe} inside the JEI Screen
     *
     * @param recipeLayoutBuilder {@link IRecipeLayoutBuilder The Recipe Layout Builder}
     * @param recipe {@link WoodcutterRecipe The Recipe to show}
     * @param focusGroup {@link IFocusGroup The JEI Focus Group}
     */
    @Override
    public void setRecipe(final @NotNull IRecipeLayoutBuilder recipeLayoutBuilder, final @NotNull WoodcutterRecipe recipe, final @NotNull IFocusGroup focusGroup) {
        recipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT, 1, 9).addIngredients(recipe.ingredient);
        recipeLayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT, 61, 9).addItemStack(recipe.result);
    }

}