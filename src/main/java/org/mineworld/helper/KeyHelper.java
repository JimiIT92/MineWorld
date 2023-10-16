package org.mineworld.helper;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import org.mineworld.MineWorld;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Helper methods for handling {@link ResourceKey resource keys}
 */
public final class KeyHelper {

    /**
     * Register a {@link ResourceKey resource key} for a {@link ConfiguredFeature configured feature}
     *
     * @param name {@link String The configured feature name}
     * @return {@link ResourceKey<ConfiguredFeature> Configured feature resource key}
     */
    public static ResourceKey<ConfiguredFeature<?, ?>> registerConfiguredFeatureKey(final String name) {
        return register(Registries.CONFIGURED_FEATURE, name);
    }

    /**
     * Register a {@link ResourceKey resource key} for a {@link PlacedFeature placed feature}
     *
     * @param name {@link String The placed feature name}
     * @return {@link ResourceKey<PlacedFeature> Configured feature resource key}
     */
    public static ResourceKey<PlacedFeature> registerPlacedFeatureKey(final String name) {
        return register(Registries.PLACED_FEATURE, name + "_placed");
    }

    /**
     * Register a {@link ResourceKey resource key} for a {@link TrimMaterial trim material}
     *
     * @param name {@link String The trim material name}
     * @return {@link ResourceKey<TrimMaterial> Trim material resource key}
     */
    public static ResourceKey<TrimMaterial> registerTrimMaterialKey(final String name) {
        return register(Registries.TRIM_MATERIAL, name);
    }

    /**
     * Register a {@link ResourceKey resource key} for a {@link PoiType villager poi type}
     *
     * @param name {@link String The villager poi type name}
     * @return {@link ResourceKey<PoiType> The villager poi type resource key}
     */
    public static ResourceKey<PoiType> registerPOITypeKey(final String name) {
        return register(Registries.POINT_OF_INTEREST_TYPE, name);
    }

    /**
     * Register a {@link ResourceKey resource key} for a {@link DamageType damage type}
     *
     * @param name {@link String The damage type name}
     * @return {@link ResourceKey<DamageType> The damage type resource key}
     */
    public static ResourceKey<DamageType> registerDamageType(final String name) {
        return register(Registries.DAMAGE_TYPE, name);
    }

    /**
     * Register a {@link ResourceKey resource key} for a {@link StructureProcessorList structure processor}
     *
     * @param name {@link String The placed feature name}
     * @return {@link ResourceKey<StructureProcessorList> The structure processor resource key}
     */
    public static ResourceKey<StructureProcessorList> registerStructureProcessorKey(final String name) {
        return register(Registries.PROCESSOR_LIST, name);
    }

    /**
     * Register a {@link ResourceKey resource key} for a {@link Biome biome}
     *
     * @param name {@link String The biome name}
     * @return {@link ResourceKey<Biome> The biome resource key}
     */
    public static ResourceKey<Biome> registerBiome(final String name) {
        return register(Registries.BIOME, name);
    }

    /**
     * Register a {@link ResourceKey resource key} for a dimension
     *
     * @param name {@link String The dimension name}
     * @return {@link ResourceKey<Level> The dimension resource key}
     */
    public static ResourceKey<Level> registerDimension(final String name) {
        return register(Registries.DIMENSION, name);
    }

    /**
     * Register a {@link ResourceKey resource key} for a {@link DimensionType dimension type}
     *
     * @param dimensionResourceKey {@link ResourceKey<Level> The dimension resource key}
     * @return {@link ResourceKey<DimensionType> The dimension type resource key}
     */
    public static ResourceKey<DimensionType> registerDimensionType(final  ResourceKey<Level> dimensionResourceKey) {
        return registerDimensionType(dimensionResourceKey.registry().getPath());
    }

    /**
     * Register a {@link ResourceKey resource key} for a {@link DimensionType dimension type}
     *
     * @param name {@link String The dimension name}
     * @return {@link ResourceKey<DimensionType> The dimension type resource key}
     */
    public static ResourceKey<DimensionType> registerDimensionType(final String name) {
        return register(Registries.DIMENSION_TYPE, name);
    }

    /**
     * Register a {@link ResourceKey resource key}
     *
     * @param registry {@link Registry<T> The resource key registry}
     * @param name {@link String The resource key name}
     * @return {@link ResourceKey<T> The resource key}
     * @param <T> The resource key type
     */
    public static <T> ResourceKey<T> register(final ResourceKey<Registry<T>> registry, final String name) {
        return ResourceKey.create(registry, location(name));
    }

    /**
     * Get a {@link ResourceLocation resource location} for a container screen
     *
     * @param name {@link String The container name}
     * @return {@link ResourceLocation The container screen resource location}
     */
    public static ResourceLocation container(final String name) {
        return texture("gui/container/" + name);
    }

    /**
     * Get a {@link ResourceLocation resource location} for an entity
     *
     * @param path {@link String The entity texture path inside entity folder}
     * @return {@link ResourceLocation The entity resource location}
     */
    public static ResourceLocation entity(final String path) {
        return location("entity/" + path);
    }

    /**
     * Get a {@link ResourceLocation resource location} for an entity texture
     *
     * @param path {@link String The texture path inside the textures folder}
     * @return {@link ResourceLocation The entity texture resource location}
     */
    public static ResourceLocation entityTexture(final String path) {
        return texture("entity/" + path);
    }

    /**
     * Get a {@link ResourceLocation resource location} for an empty slot
     *
     * @param name {@link String The empty slot name}
     * @return {@link ResourceLocation The empty slot resource location}
     */
    public static ResourceLocation emptySlot(final String name) {
        return location("id/empty_slot_" + name);
    }

    /**
     * Get a {@link ResourceLocation resource location} for a texture
     *
     * @param path {@link String The texture path inside the textures folder}
     * @return {@link ResourceLocation The texture resource location}
     */
    public static ResourceLocation texture(final String path) {
        return texture(MineWorld.MOD_ID, path);
    }

    /**
     * Get a {@link ResourceLocation resource location} for a texture
     *
     * @param path {@link String The texture path inside the textures folder}
     * @return {@link ResourceLocation The texture resource location}
     */
    public static ResourceLocation texture(final String modId, final String path) {
        return parseLocation(modId + ":" + "textures/" + path + ".png");
    }

    /**
     * Get a {@link ResourceLocation resource location}
     *
     * @param name {@link String The resource name}
     * @return {@link ResourceLocation The resource location}
     */
    public static ResourceLocation location(final String name) {
        return parseLocation(MineWorld.MOD_ID + ":" + name);
    }

    /**
     * Get a {@link ResourceLocation resource location}
     *
     * @param name {@link String The resource name}
     * @return {@link ResourceLocation The resource location}
     */
    public static ResourceLocation parseLocation(final String name) {
        return new ResourceLocation(name);
    }

    /**
     * Get a {@link ResourceLocation resource location} from a {@link JsonObject json object}
     *
     * @param json {@link JsonObject The json object}
     * @param key {@link String The json key}
     * @return {@link ResourceLocation The resource location}
     */
    public static ResourceLocation fromJson(final JsonObject json, final String key) {
        return new ResourceLocation(GsonHelper.getAsString(json, key));
    }

    /**
     * Get all the {@link Recipe recipes} from a {@link RecipeType recipe type}
     *
     * @param recipeType {@link RecipeType The recipe type}
     * @return {@link List<Recipe> The recipe list}
     * @param <T> {@link T The recipe type}
     */
    public static <C extends Container, T extends Recipe<C>> List<T> getAllRecipes(final RecipeType<T> recipeType) {
        final RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        return getAllRecipes(recipeManager.getAllRecipesFor(recipeType));
    }

    /**
     * Get all the {@link Recipe recipes} from a {@link List<RecipeHolder> recipe holder list}
     *
     * @param recipeHolderList {@link List<RecipeHolder> The recipe holder list}
     * @return {@link List<Recipe> The recipe list}
     * @param <T> {@link T The recipe type}
     */
    public static <T extends Recipe<?>> List<T> getAllRecipes(final List<RecipeHolder<T>> recipeHolderList) {
        return recipeHolderList.stream().map(recipeHolder -> recipeHolder.value()).collect(Collectors.toList());
    }
}