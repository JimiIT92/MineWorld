package org.mineworld.helper;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockRotProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.block.CoralFlowerPotBlock;
import org.mineworld.block.IcePointedDripstoneBlock;
import org.mineworld.block.MWPointedDripstoneBlock;
import org.mineworld.block.PebbleBlock;
import org.mineworld.core.*;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Helper methods for {@link RegistryObject registering some objects}
 */
public final class RegisterHelper {

    //#region Registries

    /**
     * {@link DeferredRegister<CreativeModeTab> The creative mode tab registry}
     */
    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MineWorld.MOD_ID);
    /**
     * {@link DeferredRegister<Block> The block registry}
     */
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MineWorld.MOD_ID);
    /**
     * {@link DeferredRegister<Item> The id registry}
     */
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MineWorld.MOD_ID);
    /**
     * {@link DeferredRegister<EntityType> The entity types registry}
     */
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MineWorld.MOD_ID);
    /**
     * {@link DeferredRegister<BlockEntityType> The block entity types registry}
     */
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MineWorld.MOD_ID);
    /**
     * {@link DeferredRegister<ResourceLocation> The statistics registry}
     */
    private static final DeferredRegister<ResourceLocation> STATISTICS = DeferredRegister.create(BuiltInRegistries.CUSTOM_STAT.key(), MineWorld.MOD_ID);
    /**
     * {@link DeferredRegister<MenuType> The menu type registry}
     */
    private static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MineWorld.MOD_ID);
    /**
     * {@link DeferredRegister<RecipeType> The recipe type registry}
     */
    private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, MineWorld.MOD_ID);
    /**
     * {@link DeferredRegister<RecipeSerializer> The recipe serializer registry}
     */
    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MineWorld.MOD_ID);
    /**
     * {@link DeferredRegister<PoiType> The villager poi type registry}
     */
    private static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, MineWorld.MOD_ID);
    /**
     * {@link DeferredRegister<VillagerProfession> The villager poi type registry}
     */
    private static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, MineWorld.MOD_ID);
    /**
     * {@link DeferredRegister<IGlobalLootModifier> The global loot modifier serializers registry}
     */
    private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MineWorld.MOD_ID);
    /**
     * {@link DeferredRegister<Biome> The biome registry}
     */
    private static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(Registries.BIOME, MineWorld.MOD_ID);
    /**
     * {@link DeferredRegister<Feature> The feature registry}
     */
    private static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, MineWorld.MOD_ID);
    /**
     * {@link DeferredRegister<TrunkPlacerType> The trunk placer type registry}
     */
    private static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, MineWorld.MOD_ID);
    /**
     * {@link DeferredRegister<FoliagePlacerType> The foliage placer type registry}
     */
    private static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, MineWorld.MOD_ID);
    /**
     * {@link DeferredRegister<StructureType> The structure type registry}
     */
    private static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(Registries.STRUCTURE_TYPE, MineWorld.MOD_ID);
    /**
     * {@link MineWorld MineWorld} flower pots. The key represents the {@link Block flower block}, the value is the {@link Block potted flower block}
     */
    private static final HashMap<Supplier<? extends Block>, RegistryObject<Block>> flowerPots = new HashMap<>();

    //#endregion

    /**
     * Register a {@link Item fuel item}
     *
     * @param name {@link String The item name}
     * @param burnTime {@link Integer The fuel burn time} in seconds
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    public static RegistryObject<Item> registerFuelItem(final String name, final int burnTime, final FeatureFlag... featureFlags) {
        return registerItem(name, () -> new Item(PropertyHelper.basicItemProperties(featureFlags)) {
            /**
             * Get the {@link ItemStack Item Stack} burn time in ticks
             *
             * @param itemStack {@link ItemStack The Item Stack} being used as fuel
             * @param recipeType {@link RecipeType The recipe type}
             * @return {@link Integer Burn time} in ticks
             */
            @Override
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return burnTime * 20;
            }
        });
    }

    /**
     * Create a basic {@link SmithingTemplateItem smithing template}
     *
     * @param name {@link String The id name}
     * @param templateName {@link String The smithing template name}
     * @param baseSlotIcon {@link ResourceLocation Icon to show inside the base slot}
     * @param additionSlotIcon {@link ResourceLocation Icon to show inside the additions slot}
     * @return {@link RegistryObject<Item> The registered id}
     */
    public static RegistryObject<Item> registerSmithingTemplate(final String name, final String templateName, final String baseSlotIcon, final String additionSlotIcon) {
        return registerSmithingTemplate(name, templateName,
                List.of(KeyHelper.emptySlot(baseSlotIcon)),
                List.of(KeyHelper.emptySlot(additionSlotIcon)));
    }

    /**
     * Create a basic {@link SmithingTemplateItem smithing template}
     *
     * @param name {@link String The item name}
     * @param templateName {@link String The smithing template name}
     * @param baseSlotIcons {@link List<ResourceLocation> List of icons to show inside the base slot}
     * @param additionSlotIcons {@link List<ResourceLocation> List of icons to show inside the additions slot}
     * @return {@link RegistryObject<Item> The registered id}
     */
    static RegistryObject<Item> registerSmithingTemplate(final String name,final String templateName, final List<ResourceLocation> baseSlotIcons, final List<ResourceLocation> additionSlotIcons) {
        return registerItem(name, () -> new SmithingTemplateItem(
                ComponentHelper.smithingTemplateDescription(templateName, "applies_to").withStyle(ChatFormatting.BLUE),
                ComponentHelper.smithingTemplateDescription(templateName, "ingredients").withStyle(ChatFormatting.BLUE),
                ComponentHelper.smithingTemplateUpgradeDescription(templateName).withStyle(ChatFormatting.GRAY),
                ComponentHelper.smithingTemplateDescription(templateName, "base_slot_description"),
                ComponentHelper.smithingTemplateDescription(templateName, "additions_slot_description"),
                baseSlotIcons,
                additionSlotIcons
        ));
    }

    /**
     * Register a {@link ItemNameBlockItem name block item}, which is an Item that can place a block
     * (like a bucket or a seed item)
     *
     * @param name {@link String The item name}
     * @param blockSupplier {@link Supplier<Block> The supplier for the block that the item will place}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    public static RegistryObject<Item> registerBlockItem(final String name, final Supplier<? extends Block> blockSupplier, final FeatureFlag... featureFlags) {
        return registerItem(name, () -> new ItemNameBlockItem(blockSupplier.get(), PropertyHelper.basicItemProperties(featureFlags)));
    }

    /**
     * Register a {@link ItemNameBlockItem name block item}, which is an Item that can place a block
     * (like a bucket or a seed item) and that can also be eaten
     *
     * @param name {@link String The item name}
     * @param blockSupplier {@link Supplier<Block> The supplier for the block that the item will place}
     * @param food {@link FoodProperties The food properties}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    public static RegistryObject<Item> registerFoodBlockItem(final String name, final Supplier<? extends Block> blockSupplier, final FoodProperties food, final FeatureFlag... featureFlags) {
        return registerItem(name, () -> new ItemNameBlockItem(blockSupplier.get(), PropertyHelper.basicItemProperties(featureFlags).food(food)));
    }

    /**
     * Register a {@link ItemNameBlockItem name block item}, which is an Item that can place a block
     * (like a bucket or a seed item) that has a special item renderer (like the chest block item)
     *
     * @param name {@link String The item name}
     * @param blockSupplier {@link Supplier<Block> The supplier for the block that the item will place}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    public static RegistryObject<Item> registerSpecialRendererBlockItem(final String name, final Supplier<? extends Block> blockSupplier, final FeatureFlag... featureFlags) {
        return registerItem(name, () -> new ItemNameBlockItem(blockSupplier.get(), PropertyHelper.basicItemProperties(featureFlags)) {

            /**
             * Initialize the id client rendering
             *
             * @param consumer {@link Consumer<IClientItemExtensions> The client item extensions renderer consumer}
             */
            @Override
            public void initializeClient(final @NotNull Consumer<IClientItemExtensions> consumer) {
                consumer.accept(new IClientItemExtensions() {
                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return MineWorld.getItemsRenderer();
                    }
                });
            }
        });
    }

    /**
     * Register a {@link FoodProperties food item}
     *
     * @param name {@link String The item name}
     * @param foodProperties {@link FoodProperties The food properties}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    public static RegistryObject<Item> registerFoodItem(final String name, final FoodProperties foodProperties, final FeatureFlag... featureFlags) {
        return registerItem(name, () -> new Item(PropertyHelper.basicItemProperties(featureFlags).food(foodProperties)));
    }

    /**
     * Register an {@link ArmorItem armor item}
     *
     * @param name {@link String The item name}
     * @param armorMaterial {@link ArmorMaterial The armor material}
     * @param slot {@link ArmorItem.Type The armor item type}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    public static RegistryObject<Item> registerArmorItem(final String name, final ArmorMaterial armorMaterial, final ArmorItem.Type slot, final FeatureFlag... featureFlags) {
        return registerItem(name, () -> new ArmorItem(armorMaterial, slot, PropertyHelper.basicItemProperties(featureFlags)));
    }

    /**
     * Register a rare {@link ArmorItem armor item}
     *
     * @param name {@link String The item name}
     * @param armorMaterial {@link ArmorMaterial The armor material}
     * @param slot {@link ArmorItem.Type The armor item type}
     * @param rarity {@link Rarity The armor rarity}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    public static RegistryObject<Item> registerCosmeticArmorItem(final String name, final ArmorMaterial armorMaterial, final ArmorItem.Type slot, final Rarity rarity, final FeatureFlag... featureFlags) {
        return registerItem(name, () -> new ArmorItem(armorMaterial, slot, PropertyHelper.basicItemProperties(featureFlags).rarity(rarity)));
    }

    /**
     * Register a {@link HorseArmorItem horse armor item}
     *
     * @param materialName {@link String The armor material name}
     * @param protection {@link Integer The armor protection amount}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    public static RegistryObject<Item> registerHorseArmorItem(final String materialName, final int protection, final FeatureFlag... featureFlags) {
        return registerItem(materialName + "_horse_armor", () -> new HorseArmorItem(protection, KeyHelper.entityTexture("horse/armor/horse_armor_" + materialName), PropertyHelper.basicItemProperties(featureFlags).stacksTo(1)));
    }

    /**
     * Register a {@link SwordItem sword}
     *
     * @param name {@link String The item name}
     * @param tier {@link Tier The item tier}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    public static RegistryObject<Item> registerSword(final String name, final Tier tier, final FeatureFlag... featureFlags) {
        return registerItem(name, () -> new SwordItem(tier, 3, -2.4F, PropertyHelper.basicItemProperties(featureFlags)));
    }

    /**
     * Register a {@link ShovelItem shovel}
     *
     * @param name {@link String The item name}
     * @param tier {@link Tier The item tier}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    public static RegistryObject<Item> registerShovel(final String name, final Tier tier, final FeatureFlag... featureFlags) {
        return registerItem(name, () -> new ShovelItem(tier, 1.5F, -3.0F, PropertyHelper.basicItemProperties(featureFlags)));
    }

    /**
     * Register a {@link PickaxeItem pickaxe}
     *
     * @param name {@link String The item name}
     * @param tier {@link Tier The item tier}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    public static RegistryObject<Item> registerPickaxe(final String name, final Tier tier, final FeatureFlag... featureFlags) {
        return registerItem(name, () -> new PickaxeItem(tier, 1, -2.8F, PropertyHelper.basicItemProperties(featureFlags)));
    }

    /**
     * Register a {@link AxeItem axe}
     *
     * @param name {@link String The item name}
     * @param tier {@link Tier The item tier}
     * @param attackDamageBonus {@link Float The axe attack damage bonus}
     * @param attackSpeed {@link Float The axe attack speed}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    public static RegistryObject<Item> registerAxe(final String name, final Tier tier, final float attackDamageBonus, final float attackSpeed, final FeatureFlag... featureFlags) {
        return registerItem(name, () -> new AxeItem(tier, attackDamageBonus, attackSpeed, PropertyHelper.basicItemProperties(featureFlags)));
    }

    /**
     * Register a {@link HoeItem hoe}
     *
     * @param name {@link String The item name}
     * @param tier {@link Tier The item tier}
     * @param attackSpeed {@link Float The hoe attack speed}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    public static RegistryObject<Item> registerHoe(final String name, final Tier tier, final float attackSpeed, final FeatureFlag... featureFlags) {
        final ForgeTier hoeTier = new ForgeTier(tier.getLevel(), tier.getUses(), tier.getSpeed(), 0, tier.getEnchantmentValue(), Objects.requireNonNull(tier.getTag()), tier::getRepairIngredient);
        return registerItem(name, () -> new HoeItem(hoeTier, 0, attackSpeed, PropertyHelper.basicItemProperties(featureFlags)));
    }

    /**
     * Register a basic {@link Item item}
     *
     * @param name {@link String The item name}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    public static RegistryObject<Item> registerItem(final String name, final FeatureFlag... featureFlags) {
        return registerItem(name, () -> new Item(PropertyHelper.basicItemProperties(featureFlags)));
    }

    /**
     * Register a basic {@link Item item} with a rarity
     *
     * @param name {@link String The item name}
     * @param rarity {@link Rarity The item rariry}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    public static RegistryObject<Item> registerRareItem(final String name, final Rarity rarity, final FeatureFlag... featureFlags) {
        return registerItem(name, () -> new Item(PropertyHelper.basicItemProperties(featureFlags).rarity(rarity)));
    }

    /**
     * Register an {@link Item item}
     *
     * @param name {@link String The item name}
     * @param itemSupplier {@link Supplier<Item> The item supplier}
     * @return {@link RegistryObject<Item> The registered item}
     */
    public static RegistryObject<Item> registerItem(final String name, final Supplier<? extends Item> itemSupplier) {
        return ITEMS.register(name, itemSupplier);
    }

    /**
     * Register an {@link Block ore block}
     *
     * @param name {@link String The block Name}
     * @param isDeepslateOre {@link Boolean Whether the ore is a deepslate ore}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerOverworldOreBlock(final String name, final boolean isDeepslateOre, final FeatureFlag... featureFlags) {
        return registerOreBlock(name, PropertyHelper.oreBlockProperties(isDeepslateOre, featureFlags), 3, 7);
    }

    /**
     * Register a Nether {@link Block ore block}
     *
     * @param name {@link String The block Name}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerNetherOreBlock(final String name, final FeatureFlag... featureFlags) {
        return registerOreBlock(name, PropertyHelper.oreBlockProperties(false, featureFlags).mapColor(MapColor.NETHER).sound(SoundType.NETHER_ORE), 2, 5);
    }

    /**
     * Register an {@link Block ore block}
     *
     * @param name {@link String The block Name}
     * @param blockProperties {@link BlockBehaviour.Properties The block properties}
     * @param minXp {@link Integer The minimum amount of XP the ore will drop}
     * @param maxXp {@link Integer The maximum amount of XP the ore will drop}
     * @return {@link RegistryObject<Block> The registered block}
     */
    static RegistryObject<Block> registerOreBlock(final String name, final BlockBehaviour.Properties blockProperties, final int minXp, final int maxXp) {
        return registerBlock(name, () -> new DropExperienceBlock(blockProperties, UniformInt.of(minXp, maxXp)));
    }

    /**
     * Register a {@link Block raw ore block}
     *
     * @param name {@link String The block name}
     * @param color {@link MapColor The block color on maps}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerRawOreBlock(final String name, final MapColor color, final FeatureFlag... featureFlags) {
        return registerOreStorageBlock(name, color,null, featureFlags);
    }

    /**
     * Register a {@link Block raw ore block}
     *
     * @param name {@link String The block name}
     * @param color {@link MapColor The block color on maps}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerMetalOreStorageBlock(final String name, MapColor color, final FeatureFlag... featureFlags) {
        return registerOreStorageBlock(name, color, SoundType.METAL, featureFlags);
    }

    /**
     * Register a {@link Block raw ore block}
     *
     * @param name {@link String The block name}
     * @param color {@link MapColor The block color on maps}
     * @param sound {@link SoundType The block sound}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    static RegistryObject<Block> registerOreStorageBlock(final String name, final MapColor color, final SoundType sound, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> PropertyHelper.oreStorageBlockProperties(color,sound, featureFlags));
    }

    /**
     * Register a {@link Block fuel block}
     *
     * @param name {@link String The block name}
     * @param color {@link MapColor The block color on maps}
     * @param burnTime {@link Integer The fuel burn time}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerFuelBlock(final String name, final MapColor color, final int burnTime, final FeatureFlag... featureFlags) {
        RegistryObject<Block> block = registerBlockWithoutBlockItem(name, () -> new Block(PropertyHelper.basicBlockProperties(color, 5.0F, 6.0F, true, featureFlags)));
        registerFuelBlockItem(name, block, burnTime, featureFlags);
        return block;
    }

    /**
     * Register a {@link Block block fuel item}
     *
     * @param name          {@link String The item name}
     * @param blockSupplier {@link Block The supplier for the block referred from this item}
     * @param burnTime      {@link Integer The fuel burn time} in seconds
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     */
    static void registerFuelBlockItem(final String name, final Supplier<? extends Block> blockSupplier, final int burnTime, final FeatureFlag... featureFlags) {
        registerItem(name, () -> new BlockItem(blockSupplier.get(), PropertyHelper.basicItemProperties(featureFlags)) {
            /**
             * Get the {@link ItemStack Item Stack} burn time in ticks
             *
             * @param itemStack {@link ItemStack The Item Stack} being used as fuel
             * @param recipeType {@link RecipeType The recipe type}
             * @return {@link Integer Burn time} in ticks
             */
            @Override
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return burnTime * 20;
            }
        });
    }

    /**
     * Register a {@link FlowerBlock flower block}
     *
     * @param name {@link String The block name}
     * @param effectSupplier {@link Supplier<MobEffect> The flower effect when used in suspicious stews}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerFlower(final String name, final Supplier<MobEffect> effectSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new FlowerBlock(effectSupplier.get(), 5, PropertyHelper.copyFromBlock(Blocks.POPPY, featureFlags)) {

            /**
             * Makes the block able to catch fire
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link Level The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The direction the fire is coming from}
             * @return {@link Boolean True}
             */
            @Override
            public boolean isFlammable(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return true;
            }

            /**
             * Get the block {@link Integer flammability value}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link Level The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The direction the fire is coming from}
             * @return {@link Integer 100}
             */
            @Override
            public int getFlammability(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return 100;
            }

            /**
             * Get the block {@link Integer fire spread speed value}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link Level The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The direction the fire is coming from}
             * @return {@link Integer 15}
             */
            @Override
            public int getFireSpreadSpeed(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return 60;
            }
        });
    }

    /**
     * Register a {@link TallFlowerBlock tall flower block}
     *
     * @param name {@link String The block name}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerTallFlower(final String name, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new TallFlowerBlock(PropertyHelper.copyFromBlock(Blocks.ROSE_BUSH, featureFlags)) {

            /**
             * Makes the block able to catch fire
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link Level The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The direction the fire is coming from}
             * @return {@link Boolean True}
             */
            @Override
            public boolean isFlammable(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return true;
            }

            /**
             * Get the block {@link Integer flammability value}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link Level The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The direction the fire is coming from}
             * @return {@link Integer 100}
             */
            @Override
            public int getFlammability(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return 100;
            }

            /**
             * Get the block {@link Integer fire spread speed value}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link Level The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The direction the fire is coming from}
             * @return {@link Integer 15}
             */
            @Override
            public int getFireSpreadSpeed(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return 60;
            }
        });
    }

    /**
     * Register a {@link FlowerPotBlock flower pot block}
     * that can emit light
     *
     * @param name {@link String The block name}
     * @param flowerSupplier {@link Supplier <Block> The supplier for the flower this pot is referring to}
     * @param light {@link Integer The block light level}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this block to be registered}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerLitFlowerPot(final String name, final Supplier<? extends Block> flowerSupplier, final int light, final FeatureFlag... featureFlags) {
        return registerFlowerPot(name, flowerSupplier, PropertyHelper.copyFromBlock(Blocks.FLOWER_POT, featureFlags).lightLevel(state -> light));
    }

    /**
     * Register a {@link CoralFlowerPotBlock coral flower pot block}
     *
     * @param name {@link String The block name}
     * @param deadCoralFlowerPotSupplier {@link Supplier<Block> The supplier for the dead coral flower pot variant}
     * @param coralSupplier {@link Supplier<Block> The supplier for the coral this pot is referring to}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this block to be registered}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerCoralFlowerPot(final String name, final Supplier<? extends Block> deadCoralFlowerPotSupplier, final Supplier<? extends Block> coralSupplier, final FeatureFlag... featureFlags) {
        final RegistryObject<Block> block = registerBlockWithoutBlockItem(name, () -> new CoralFlowerPotBlock(deadCoralFlowerPotSupplier, coralSupplier, featureFlags));
        flowerPots.put(coralSupplier, block);
        return block;
    }

    /**
     * Register a {@link FlowerPotBlock flower pot block}
     *
     * @param name {@link String The block name}
     * @param flowerSupplier {@link Supplier<Block> The supplier for the flower this pot is referring to}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this block to be registered}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerFlowerPot(final String name, final Supplier<? extends Block> flowerSupplier, final FeatureFlag... featureFlags) {
        return registerFlowerPot(name, flowerSupplier, PropertyHelper.copyFromBlock(Blocks.FLOWER_POT, featureFlags));
    }

    /**
     * Register a {@link FlowerPotBlock flower pot block}
     *
     * @param name {@link String The block name}
     * @param flowerSupplier {@link Supplier<Block> The supplier for the flower this pot is referring to}
     * @param properties {@link BlockBehaviour.Properties The block properties}
     * @return {@link RegistryObject<Block> The registered block}
     */
    static RegistryObject<Block> registerFlowerPot(final String name, final Supplier<? extends Block> flowerSupplier, final BlockBehaviour.Properties properties) {
        final RegistryObject<Block> block = registerBlockWithoutBlockItem(name, () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, flowerSupplier, properties));
        flowerPots.put(flowerSupplier, block);
        return block;
    }

    /**
     * Register a {@link FenceBlock fence block}
     *
     * @param name {@link String The block name}
     * @param blockSupplier {@link Supplier<Block> The supplier for the block this fence is referring to}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerFence(final String name, final Supplier<Block> blockSupplier, final FeatureFlag... featureFlags) {
        return registerFence(name, blockSupplier, true, featureFlags);
    }

    /**
     * Register a {@link FenceBlock fence block}
     *
     * @param name {@link String The block name}
     * @param blockSupplier {@link Supplier<Block> The supplier for the block this fence is referring to}
     * @param isFlammable {@link Boolean If the fence is flammable}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerFence(final String name, final Supplier<Block> blockSupplier, final boolean isFlammable, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new FenceBlock(BlockBehaviour.Properties.of().mapColor(blockSupplier.get().defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)) {

            /**
             * Determine if the {@link RotatedPillarBlock block} is flammable
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link BlockGetter The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The update direction}
             * @return {@link Boolean True if the block is flammable}
             */
            @Override
            public boolean isFlammable(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return isFlammable;
            }

            /**
             * Get the {@link Integer block flammability value}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link BlockGetter The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The update direction}
             * @return {@link Integer The block flammability value}
             */
            @Override
            public int getFlammability(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return isFlammable ? 5 : 0;
            }

            /**
             * Get the {@link Integer block fire spread chance value}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link BlockGetter The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The update direction}
             * @return {@link Integer The block fire spread chance value}
             */
            @Override
            public int getFireSpreadSpeed(final BlockState blockState,final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return isFlammable ? 20 : 0;
            }

        });
    }

    /**
     * Register a {@link FenceBlock fence gate block}
     *
     * @param name {@link String The block name}
     * @param blockSupplier {@link Supplier<Block> The supplier for the block this fence gate is referring to}
     * @param woodType {@link WoodType The fence gate wood type}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerFenceGate(final String name, final Supplier<Block> blockSupplier, final WoodType woodType, final FeatureFlag... featureFlags) {
        return registerFenceGate(name, blockSupplier, woodType,true, featureFlags);
    }

    /**
     * Register a {@link FenceBlock fence gate block}
     *
     * @param name {@link String The block name}
     * @param blockSupplier {@link Supplier<Block> The supplier for the block this fence gate is referring to}
     * @param woodType {@link WoodType The fence gate wood type}
     * @param isFlammable {@link Boolean If the fence gate is flammable}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerFenceGate(final String name, final Supplier<Block> blockSupplier, final WoodType woodType, final boolean isFlammable,  final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new FenceGateBlock(BlockBehaviour.Properties.of().mapColor(blockSupplier.get().defaultMapColor()).strength(2.0F, 3.0F), woodType) {

            /**
             * Determine if the {@link RotatedPillarBlock block} is flammable
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link BlockGetter The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The update direction}
             * @return {@link Boolean True if the block is flammable}
             */
            @Override
            public boolean isFlammable(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return isFlammable;
            }

            /**
             * Get the {@link Integer block flammability value}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link BlockGetter The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The update direction}
             * @return {@link Integer The block flammability value}
             */
            @Override
            public int getFlammability(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return isFlammable ? 5 : 0;
            }

            /**
             * Get the {@link Integer block fire spread chance value}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link BlockGetter The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The update direction}
             * @return {@link Integer The block fire spread chance value}
             */
            @Override
            public int getFireSpreadSpeed(final BlockState blockState,final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return isFlammable ? 20 : 0;
            }

        });
    }

    /**
     * Register a {@link SignBlock sign block}
     *
     * @param name {@link String The block name}
     * @param woodType {@link WoodType The sign wood type}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerStandingSign(final String name, final WoodType woodType, final FeatureFlag... featureFlags) {
        return registerBlockWithoutBlockItem(name, () -> new StandingSignBlock(PropertyHelper.copyFromBlock(Blocks.OAK_SIGN), woodType) {

            /**
             * Get the {@link BlockEntity sign block entity}
             *
             * @param blockPos {@link BlockPos The current block pos}
             * @param blockState {@link BlockState The current block state}
             * @return {@link BlockEntity The sign block entity}
             */
            @Override
            public BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
                return new SignBlockEntity(blockPos, blockState) {

                    /**
                     * Get the {@link BlockEntityType sign block entity type}
                     * @return {@link MWBlockEntityTypes#SIGN The sign block entity type}
                     */
                    @Override
                    public @NotNull BlockEntityType<?> getType() {
                        return MWBlockEntityTypes.SIGN.get();
                    }
                };
            }
        });
    }

    /**
     * Register a {@link SignBlock wall sign block}
     *
     * @param name {@link String The block name}
     * @param woodType {@link WoodType The sign wood type}
     * @param standingSignSupplier {@link Supplier<Block> The supplier for the standing sign block}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerWallSign(final String name, final WoodType woodType, final Supplier<Block> standingSignSupplier, final FeatureFlag... featureFlags) {
        return registerBlockWithoutBlockItem(name, () -> new WallSignBlock(PropertyHelper.copyFromBlock(Blocks.OAK_WALL_SIGN).dropsLike(standingSignSupplier.get()), woodType) {

            /**
             * Get the {@link BlockEntity sign block entity}
             *
             * @param blockPos {@link BlockPos The current block pos}
             * @param blockState {@link BlockState The current block state}
             * @return {@link BlockEntity The sign block entity}
             */
            @Override
            public BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
                return new SignBlockEntity(blockPos, blockState) {

                    /**
                     * Get the {@link BlockEntityType sign block entity type}
                     * @return {@link MWBlockEntityTypes#SIGN The sign block entity type}
                     */
                    @Override
                    public @NotNull BlockEntityType<?> getType() {
                        return MWBlockEntityTypes.SIGN.get();
                    }
                };
            }

        });
    }

    /**
     * Register an {@link SignBlock hanging sign block}
     *
     * @param name {@link String The block name}
     * @param blockSupplier {@link Supplier<Block> The supplier for the block this sign is referring to}
     * @param woodType {@link WoodType The sign wood type}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerHangingSign(final String name, final Supplier<Block> blockSupplier, final WoodType woodType) {
        return registerBlockWithoutBlockItem(name, () -> new CeilingHangingSignBlock(BlockBehaviour.Properties.of().mapColor(blockSupplier.get().defaultMapColor()).noCollission().strength(1.0F), woodType) {

            /**
             * Get the {@link BlockEntity sign block entity}
             *
             * @param blockPos {@link BlockPos The current block pos}
             * @param blockState {@link BlockState The current block state}
             * @return {@link BlockEntity The sign block entity}
             */
            @Override
            public BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
                return new HangingSignBlockEntity(blockPos, blockState) {

                    /**
                     * Get the {@link BlockEntityType sign block entity type}
                     * @return {@link MWBlockEntityTypes#SIGN The sign block entity type}
                     */
                    @Override
                    public @NotNull BlockEntityType<?> getType() {
                        return MWBlockEntityTypes.HANGING_SIGN.get();
                    }
                };
            }
        });
    }

    /**
     * Register an {@link SignBlock wall hanging sign block}
     *
     * @param name {@link String The block name}
     * @param blockSupplier {@link Supplier<Block> The supplier for the block this sign is referring to}
     * @param woodType {@link WoodType The sign wood type}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerWallHangingSign(final String name, final Supplier<Block> blockSupplier, final Supplier<Block> standingSignSupplier, final WoodType woodType) {
        return registerBlockWithoutBlockItem(name, () -> new WallHangingSignBlock(BlockBehaviour.Properties.of().mapColor(blockSupplier.get().defaultMapColor()).noCollission().strength(1.0F).dropsLike(standingSignSupplier.get()), woodType) {

            /**
             * Get the {@link BlockEntity sign block entity}
             *
             * @param blockPos {@link BlockPos The current block pos}
             * @param blockState {@link BlockState The current block state}
             * @return {@link BlockEntity The sign block entity}
             */
            @Override
            public BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
                return new HangingSignBlockEntity(blockPos, blockState) {

                    /**
                     * Get the {@link BlockEntityType sign block entity type}
                     * @return {@link MWBlockEntityTypes#SIGN The sign block entity type}
                     */
                    @Override
                    public @NotNull BlockEntityType<?> getType() {
                        return MWBlockEntityTypes.HANGING_SIGN.get();
                    }
                };
            }
        });
    }

    /**
     * Register a {@link DoorBlock door block}
     *
     * @param name {@link String The block name}
     * @param requiresPower {@link Boolean If the door needs redstone to be activated}
     * @param blockSetType {@link BlockSetType The door block set type}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerDoor(final String name, final boolean requiresPower, final BlockSetType blockSetType, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new DoorBlock(PropertyHelper.copyFromBlock(requiresPower ? Blocks.IRON_DOOR : Blocks.OAK_DOOR, featureFlags), blockSetType));
    }

    /**
     * Register a {@link TrapDoorBlock trapdoor block}
     *
     * @param name {@link String The block name}
     * @param requiresPower {@link Boolean If the trapdoor needs redstone to be activated}
     * @param blockSetType {@link BlockSetType The trapdoor block set type}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerTrapdoor(final String name, final boolean requiresPower, final BlockSetType blockSetType, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new TrapDoorBlock(PropertyHelper.copyFromBlock(requiresPower ? Blocks.IRON_TRAPDOOR : Blocks.OAK_TRAPDOOR, featureFlags), blockSetType));
    }

    /**
     * Register a {@link PressurePlateBlock pressure plate block}
     *
     * @param name {@link String The block name}
     * @param isWooden {@link Boolean If the pressure plate is a wooden pressure plate}
     * @param materialColor {@link MapColor The block color on maps}
     * @param blockSetType {@link BlockSetType The pressure plate block set type}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerPressurePlate(final String name, final boolean isWooden, final MapColor materialColor, final BlockSetType blockSetType, final FeatureFlag... featureFlags) {
        final BlockBehaviour.Properties properties = PropertyHelper.copyFromBlock(isWooden ? Blocks.OAK_PRESSURE_PLATE : Blocks.STONE_PRESSURE_PLATE, featureFlags).mapColor(materialColor);
        return registerBlock(name, () -> new PressurePlateBlock(isWooden ? PressurePlateBlock.Sensitivity.EVERYTHING : PressurePlateBlock.Sensitivity.MOBS, properties, blockSetType));
    }

    /**
     * Register a {@link PressurePlateBlock pressure plate block}
     *
     * @param name {@link String The block name}
     * @param maxWeight {@link Integer The max weight the pressure plate can detect}
     * @param materialColor {@link MapColor The block color on maps}
     * @param blockSetType {@link BlockSetType The pressure plate block set type}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerWeightedPressurePlate(final String name, final int maxWeight, final MapColor materialColor, final BlockSetType blockSetType, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new WeightedPressurePlateBlock(maxWeight, PropertyHelper.copyFromBlock(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE), blockSetType));
    }

    /**
     * Register a {@link ButtonBlock button block}
     *
     * @param name {@link String The block name}
     * @param isWooden {@link Boolean If the button is a wooden button}
     * @param blockSetType {@link BlockSetType The button block set type}
     * @param featureFlags @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerButton(final String name, final boolean isWooden, final BlockSetType blockSetType, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new ButtonBlock(PropertyHelper.copyFromBlock(isWooden ? Blocks.OAK_BUTTON : Blocks.STONE_BUTTON), blockSetType, isWooden ? 30 : 20, isWooden));
    }

    /**
     * Register a {@link StairBlock stair block}
     *
     * @param name {@link String The block name}
     * @param blockStateSupplier {@link Supplier<BlockState> The supplier for the block state this stair is based on}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerStair(final String name, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerStair(name, blockStateSupplier, () -> PropertyHelper.copyFromBlock(blockStateSupplier.get().getBlock(), featureFlags).requiresCorrectToolForDrops());
    }

    /**
     * Register a {@link StairBlock stair block}
     *
     * @param name {@link String The block name}
     * @param blockStateSupplier {@link Supplier<BlockState> The supplier for the block state this stair is based on}
     * @param propertiesSupplier {@link Supplier<BlockBehaviour.Properties> The supplier for the block properties this stair is based on}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerStair(final String name, final Supplier<BlockState> blockStateSupplier, final Supplier<BlockBehaviour.Properties> propertiesSupplier) {
        return registerBlock(name, () -> new StairBlock(blockStateSupplier, propertiesSupplier.get()) {

            /**
             * Get the {@link PushReaction push reaction} when this block is pushed by pistons
             *
             * @param blockState {@link BlockState The current block state}
             * @return {@link PushReaction#DESTROY Destroy push reaction}
             */
            public @NotNull PushReaction getPistonPushReaction(final @NotNull BlockState blockState) {
                return LevelHelper.getPushReaction(blockStateSupplier.get());
            }

            /**
             * Determine if the {@link RotatedPillarBlock block} is flammable
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link BlockGetter The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The update direction}
             * @return {@link Boolean True if the block is flammable}
             */
            @Override
            public boolean isFlammable(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return blockStateSupplier.get().isFlammable(blockGetter, blockPos, direction);
            }

            /**
             * Get the {@link Integer block flammability value}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link BlockGetter The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The update direction}
             * @return {@link Integer The block flammability value}
             */
            @Override
            public int getFlammability(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return 5;
            }

            /**
             * Get the {@link Integer block fire spread chance value}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link BlockGetter The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The update direction}
             * @return {@link Integer The block fire spread chance value}
             */
            @Override
            public int getFireSpreadSpeed(final BlockState blockState,final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return 20;
            }

        });
    }

    /**
     * Register a {@link StairBlock slab block}
     *
     * @param name {@link String The block name}
     * @param blockSupplier {@link Supplier<Block> The supplier for block this slab is based on}
     * @param isFlammable {@link Boolean If the slab is flammable}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerSlab(final String name, final Supplier<? extends Block> blockSupplier, final boolean isFlammable, final FeatureFlag... featureFlags) {
        return registerSlab(name, () -> PropertyHelper.copyFromBlock(blockSupplier.get(), featureFlags).requiresCorrectToolForDrops(), () -> LevelHelper.getPushReaction(blockSupplier.get().defaultBlockState()), isFlammable);
    }

    /**
     * Register a {@link StairBlock slab block}
     *
     * @param name {@link String The block name}
     * @param propertiesSupplier {@link Supplier<BlockBehaviour.Properties> The supplier for block properties this slab is based on}
     * @param isFlammable {@link Boolean If the slab is flammable}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerSlab(final String name, final Supplier<BlockBehaviour.Properties> propertiesSupplier, final boolean isFlammable) {
        return registerSlab(name, propertiesSupplier, () -> PushReaction.NORMAL, isFlammable);
    }

    /**
     * Register a {@link StairBlock stair block}
     *
     * @param name {@link String The block name}
     * @param propertiesSupplier {@link Supplier<BlockBehaviour.Properties> The supplier for block properties this stair is based on}
     * @param pistonPushReaction {@link Supplier<PushReaction> The piston push reaction supplier for this block}
     * @param isFlammable {@link Boolean If the slab is flammable}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerSlab(final String name, final Supplier<BlockBehaviour.Properties> propertiesSupplier, final Supplier<PushReaction> pistonPushReaction, final boolean isFlammable) {
        return registerBlock(name, () -> new SlabBlock(propertiesSupplier.get()) {

            /**
             * Get the {@link PushReaction push reaction} when this block is pushed by pistons
             *
             * @param blockState {@link BlockState The current block state}
             * @return {@link PushReaction#DESTROY Destroy push reaction}
             */
            public @NotNull PushReaction getPistonPushReaction(final @NotNull BlockState blockState) {
                return pistonPushReaction.get();
            }

            /**
             * Determine if the {@link RotatedPillarBlock block} is flammable
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link BlockGetter The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The update direction}
             * @return {@link Boolean True if the block is flammable}
             */
            @Override
            public boolean isFlammable(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return isFlammable;
            }

            /**
             * Get the {@link Integer block flammability value}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link BlockGetter The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The update direction}
             * @return {@link Integer The block flammability value}
             */
            @Override
            public int getFlammability(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return isFlammable ? 5 : 0;
            }

            /**
             * Get the {@link Integer block fire spread chance value}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link BlockGetter The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The update direction}
             * @return {@link Integer The block fire spread chance value}
             */
            @Override
            public int getFireSpreadSpeed(final BlockState blockState,final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return isFlammable ? 20 : 0;
            }
        });
    }

    /**
     * Register a {@link ChainBlock chain block}
     *
     * @param name {@link String The block name}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerChain(final String name, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new ChainBlock(PropertyHelper.copyFromBlock(Blocks.CHAIN, featureFlags)));
    }

    /**
     * Register a {@link LanternBlock lantern block}
     *
     * @param name {@link String The block name}
     * @param isSoulLantern {@link Boolean If the lantern is a soul lantern}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerLantern(final String name, final boolean isSoulLantern, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new LanternBlock(PropertyHelper.lanternProperties(isSoulLantern, featureFlags)));
    }

    /**
     * Register a {@link IronBarsBlock bar block}
     *
     * @param name {@link String The block name}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerBars(final String name, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new IronBarsBlock(PropertyHelper.copyFromBlock(Blocks.IRON_BARS, featureFlags)));
    }

    /**
     * Register a {@link WallBlock wall block}
     *
     * @param name {@link String The block name}
     * @param blockSupplier {@link Supplier<BlockBehaviour.Properties> The supplier for the block properties this wall is based on}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerWall(final String name, final Supplier<? extends Block> blockSupplier, final FeatureFlag... featureFlags) {
        return registerWall(name, () -> PropertyHelper.copyFromBlock(blockSupplier.get(), featureFlags).requiresCorrectToolForDrops(), () -> LevelHelper.getPushReaction(blockSupplier.get().defaultBlockState()), false);
    }

    /**
     * Register a {@link WallBlock wall block}
     *
     * @param name {@link String The block name}
     * @param propertiesSupplier {@link Supplier<Block> The supplier for the block properties this wall is based on}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerWall(final String name, final Supplier<BlockBehaviour.Properties> propertiesSupplier) {
        return registerWall(name, propertiesSupplier, () -> PushReaction.NORMAL, false);
    }

    /**
     * Register a {@link WallBlock wall block}
     *
     * @param name {@link String The block name}
     * @param propertiesSupplier {@link Supplier<Block> The supplier for the block properties this wall is based on}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerWoolWall(final String name, final Supplier<BlockBehaviour.Properties> propertiesSupplier) {
        return registerWall(name, propertiesSupplier, () -> PushReaction.NORMAL, true);
    }

    /**
     * Register a {@link WallBlock wall block}
     *
     * @param name {@link String The block name}
     * @param propertiesSupplier {@link Supplier<BlockBehaviour.Properties> The supplier for block properties this stair is based on}
     * @param pistonPushReaction {@link Supplier<PushReaction> The piston push reaction supplier for this block}
     * @param isFlammable {@link Boolean If the wall block is flammable}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerWall(final String name, final Supplier<BlockBehaviour.Properties> propertiesSupplier, final Supplier<PushReaction> pistonPushReaction, final boolean isFlammable) {
        return registerBlock(name, () -> new WallBlock(propertiesSupplier.get()) {
            /**
             * Get the {@link PushReaction push reaction} when this block is pushed by pistons
             *
             * @param blockState {@link BlockState The current block state}
             * @return {@link PushReaction#DESTROY Destroy push reaction}
             */
            public @NotNull PushReaction getPistonPushReaction(final @NotNull BlockState blockState) {
                return pistonPushReaction.get();
            }

            /**
             * Determine if the {@link RotatedPillarBlock block} is flammable
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link BlockGetter The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The update direction}
             * @return {@link Boolean True if the block is flammable}
             */
            @Override
            public boolean isFlammable(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return isFlammable;
            }

            /**
             * Get the {@link Integer block flammability value}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link BlockGetter The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The update direction}
             * @return {@link Integer The block flammability value}
             */
            @Override
            public int getFlammability(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return isFlammable ? 5 : 0;
            }

            /**
             * Get the {@link Integer block fire spread chance value}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link BlockGetter The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The update direction}
             * @return {@link Integer The block fire spread chance value}
             */
            @Override
            public int getFireSpreadSpeed(final BlockState blockState,final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return isFlammable ? 20 : 0;
            }
        });
    }

    /**
     * Register a {@link WallBlock glass wall block}
     *
     * @param name {@link String The block name}
     * @param glassSupplier {@link Supplier<Block> The supplier for the stained glass this wall is based on}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerGlassWall(final String name, final Supplier<? extends Block> glassSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new WallBlock(PropertyHelper.copyFromBlock(glassSupplier.get(), featureFlags).requiresCorrectToolForDrops()) {

            /**
             * Get the {@link VoxelShape block visual shape}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link BlockGetter The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param collisionContext {@link CollisionContext The collision context}
             * @return {@link Shapes#empty() Empty shape}
             */
            public @NotNull VoxelShape getVisualShape(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull CollisionContext collisionContext) {
                return Shapes.empty();
            }

            /**
             * Get the {@link Float block shade brightness}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link BlockGetter The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @return {@link Float 1.0}
             */
            public float getShadeBrightness(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos) {
                return 1.0F;
            }

            /**
             * Check if the block can propagate the skylight
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link BlockGetter The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @return {@link Boolean True}
             */
            public boolean propagatesSkylightDown(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos) {
                return true;
            }

            /**
             * Determine if the face should be rendered based on the neighbor state
             *
             * @param blockState {@link BlockState The current block state}
             * @param neighborState {@link BlockState The neighbor block state}
             * @param direction {@link Direction The direction of the face to be rendered}
             * @return {@link Boolean True if the face should be rendered}
             */
            public boolean skipRendering(final @NotNull BlockState blockState, final @NotNull BlockState neighborState, final @NotNull Direction direction) {
                return neighborState.is(this) || super.skipRendering(blockState, neighborState, direction);
            }
        });
    }

    /**
     * Register a {@link Block cage block}
     *
     * @param name {@link String The block name}
     * @param blockSupplier {@link Supplier<Block> The supplier for the block this cage is based on}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerCage(final String name, final Supplier<? extends Block> blockSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new Block(PropertyHelper.makeTranslucentBlock(blockSupplier.get(), featureFlags).requiresCorrectToolForDrops()));
    }

    /**
     * Register a {@link PebbleBlock pebble block}
     *
     * @param name {@link String The block name}
     * @param blockSupplier {@link Supplier<Block> The supplier for the block this pebble is based on}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerPebble(final String name, final Supplier<? extends Block> blockSupplier, final FeatureFlag... featureFlags) {
        return registerBlockWithoutBlockItem(name, () -> new PebbleBlock(blockSupplier.get(), featureFlags));
    }

    /**
     * Register a {@link LecternBlock lectern block}
     *
     * @param name {@link String The block name}
     * //@param lecternBlockEntityTypeSupplier {@link Supplier<BlockEntityType> The supplier for the lectern block entity type}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerLectern(final String name, final Supplier<BlockEntityType<LecternBlockEntity>> lecternBlockEntityTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new LecternBlock(PropertyHelper.copyFromBlock(Blocks.LECTERN, featureFlags)) {

            /**
             * Get the {@link LecternBlockEntity lectern block entity}
             *
             * @param blockPos {@link BlockPos The current block pos}
             * @param blockState {@link BlockState The current block state}
             * @return {@link BlockEntity The lectern block entity}
             */
            @Override
            public BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
                return new LecternBlockEntity(blockPos, blockState) {

                    /**
                     * Get the {@link BlockEntityType<LecternBlockEntity> lectern block entity type}
                     *
                     * @return {@link BlockEntityType<LecternBlockEntity> The lectern block entity type}
                     */
                    @Override
                    public @NotNull BlockEntityType<?> getType() {
                        return lecternBlockEntityTypeSupplier.get();
                    }
                };
            }

            /**
             * Makes the block able to catch fire
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link Level The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The direction the fire is coming from}
             * @return {@link Boolean True}
             */
            @Override
            public boolean isFlammable(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return true;
            }

            /**
             * Get the block {@link Integer flammability value}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link Level The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The direction the fire is coming from}
             * @return {@link Integer 20}
             */
            @Override
            public int getFlammability(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return 20;
            }

            /**
             * Get the block {@link Integer fire spread speed value}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link Level The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The direction the fire is coming from}
             * @return {@link Integer 5}
             */
            @Override
            public int getFireSpreadSpeed(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return 30;
            }
        });
    }

    /**
     * Register a {@link Block block} using the provided {@link BlockBehaviour.Properties properties}
     *
     * @param name {@link String The block name}
     * @param propertiesSupplier {@link BlockBehaviour.Properties The supplier for the block properties}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerBlock(final String name, final Supplier<BlockBehaviour.Properties> propertiesSupplier) {
        return registerBlock(name, () -> new Block(propertiesSupplier.get()));
    }

    /**
     * Register an {@link Block unmovable block} using the provided {@link BlockBehaviour.Properties properties}
     *
     * @param name {@link String The block name}
     * @param propertiesSupplier {@link BlockBehaviour.Properties The supplier for the block properties}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerUnmovableBlock(final String name, final Supplier<BlockBehaviour.Properties> propertiesSupplier) {
        return registerBlock(name, () -> new Block(propertiesSupplier.get()) {
            /**
             * Get the {@link PushReaction block push reaction} when moved by pistons
             *
             * @param blockState {@link BlockState The current block state}
             * @return {@link PushReaction#BLOCK Block push reaction}
             */
            @Override
            public @NotNull PushReaction getPistonPushReaction(@NotNull BlockState blockState) {
                return PushReaction.BLOCK;
            }
        });
    }

    /**
     * Register a {@link ChestBlock chest block}
     *
     * @param name {@link String The block name}
     * @param chestBlockEntitySupplier {@link Supplier<ChestBlockEntity> The supplier for the chest block entity type}
     * @param woodType {@link WoodType The chest wood type}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this block to be registered}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerChest(final String name, Supplier<BlockEntityType<? extends ChestBlockEntity>> chestBlockEntitySupplier, final WoodType woodType, final FeatureFlag... featureFlags) {
        return registerBlockWithoutBlockItem(name, () -> new ChestBlock(PropertyHelper.copyFromBlock(Blocks.CHEST, featureFlags).sound(woodType.equals(MWWoodTypes.ICE) ? SoundType.GLASS : SoundType.WOOD), chestBlockEntitySupplier) {

            /**
             * Get the {@link BlockEntity chest block entity}
             *
             * @param blockPos {@link BlockPos The current block pos}
             * @param blockState {@link BlockState The current block state}
             * @return {@link ChestBlockEntity The chest block entity}
             */
            public BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
                return PropertyHelper.getChestBlockEntity(woodType, blockPos, blockState, false);
            }
        });
    }

    /**
     * Register a {@link TrappedChestBlock trapped chest block}
     *
     * @param name {@link String The block name}
     * @param chestBlockEntitySupplier {@link Supplier<ChestBlockEntity> The supplier for the chest block entity type}
     * @param woodType {@link WoodType The chest wood type}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this block to be registered}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerTrappedChest(final String name, Supplier<BlockEntityType<? extends ChestBlockEntity>> chestBlockEntitySupplier, final WoodType woodType, final FeatureFlag... featureFlags) {
        return registerBlockWithoutBlockItem(name, () -> new ChestBlock(PropertyHelper.copyFromBlock(Blocks.TRAPPED_CHEST, featureFlags).sound(woodType.equals(MWWoodTypes.ICE) ? SoundType.GLASS : SoundType.WOOD), chestBlockEntitySupplier) {

            /**
             * Get the {@link TrappedChestBlockEntity trapped chest block entity}
             *
             * @param blockPos {@link BlockPos The current block pos}
             * @param blockState {@link BlockState The current block state}
             * @return {@link TrappedChestBlockEntity The trapped chest block entity}
             */
            public BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
                return PropertyHelper.getChestBlockEntity(woodType, blockPos, blockState, true);
            }

            /**
             * Get the {@link Stat<ResourceLocation> open chest statistic}
             *
             * @return {@link Stat<ResourceLocation> The open chest statistic}
             */
            protected @NotNull Stat<ResourceLocation> getOpenChestStat() {
                return Stats.CUSTOM.get(Stats.TRIGGER_TRAPPED_CHEST);
            }

            /**
             * Check if the block can conduct redstone
             *
             * @param blockState {@link BlockState The current block state}
             * @return {@link Boolean True}
             */
            public boolean isSignalSource(final @NotNull BlockState blockState) {
                return true;
            }

            /**
             * Get the redstone signal based on chest content
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link BlockGetter The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The signal direction}
             * @return {@link Integer The redstone signal based on chest content}
             */
            public int getSignal(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull Direction direction) {
                return Mth.clamp(ChestBlockEntity.getOpenCount(blockGetter, blockPos), 0, 15);
            }

            /**
             * Get the redstone signal power
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link BlockGetter The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The signal direction}
             * @return {@link Integer The redstone signal power}
             */
            public int getDirectSignal(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull Direction direction) {
                return direction.equals(Direction.UP) ? blockState.getSignal(blockGetter, blockPos, direction) : 0;
            }
        });
    }

    /**
     * Register a {@link Block bookshelf block}
     *
     * @param name {@link String The block name}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this block to be registered}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerBookshelf(final String name, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new Block(PropertyHelper.copyFromBlock(Blocks.BOOKSHELF, featureFlags)) {

            /**
             * Get the {@link Float bookshelf enchantment power bonus}
             *
             * @param state {@link BlockState The current block state}
             * @param level {@link LevelReader The level reader reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @return {@link Float 1.0}
             */
            @Override
            public float getEnchantPowerBonus(final BlockState state, final LevelReader level, final BlockPos blockPos) {
                return 1.0F;
            }

            /**
             * Makes the block able to catch fire
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link Level The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The direction the fire is coming from}
             * @return {@link Boolean True}
             */
            @Override
            public boolean isFlammable(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return true;
            }

            /**
             * Get the block {@link Integer flammability value}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link Level The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The direction the fire is coming from}
             * @return {@link Integer 20}
             */
            @Override
            public int getFlammability(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return 20;
            }

            /**
             * Get the block {@link Integer fire spread speed value}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link Level The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The direction the fire is coming from}
             * @return {@link Integer 5}
             */
            @Override
            public int getFireSpreadSpeed(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return 30;
            }
        });
    }

    /**
     * Register some {@link Block wood planks}
     *
     * @param name {@link String The block name}
     * @param materialColor {@link MapColor The block color on maps}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this block to be registered}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerPlanks(final String name, final MapColor materialColor, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new Block(PropertyHelper.copyFromBlock(Blocks.OAK_PLANKS, featureFlags).mapColor(materialColor)) {

            /**
             * Makes the block able to catch fire
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link Level The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The direction the fire is coming from}
             * @return {@link Boolean True}
             */
            @Override
            public boolean isFlammable(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return true;
            }

            /**
             * Get the block {@link Integer flammability value}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link Level The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The direction the fire is coming from}
             * @return {@link Integer 20}
             */
            @Override
            public int getFlammability(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return 5;
            }

            /**
             * Get the block {@link Integer fire spread speed value}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link Level The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The direction the fire is coming from}
             * @return {@link Integer 5}
             */
            @Override
            public int getFireSpreadSpeed(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return 20;
            }

        });
    }

    /**
     * Register some {@link Block leaves}
     *
     * @param name {@link String The block name}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this block to be registered}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerLeaves(final String name, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new LeavesBlock(PropertyHelper.copyFromBlock(Blocks.OAK_LEAVES, featureFlags)) {

            /**
             * Makes the block able to catch fire
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link Level The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The direction the fire is coming from}
             * @return {@link Boolean True}
             */
            @Override
            public boolean isFlammable(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return true;
            }

            /**
             * Get the block {@link Integer flammability value}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link Level The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The direction the fire is coming from}
             * @return {@link Integer 20}
             */
            @Override
            public int getFlammability(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return 30;
            }

            /**
             * Get the block {@link Integer fire spread speed value}
             *
             * @param blockState {@link BlockState The current block state}
             * @param blockGetter {@link Level The block getter reference}
             * @param blockPos {@link BlockPos The current block pos}
             * @param direction {@link Direction The direction the fire is coming from}
             * @return {@link Integer 5}
             */
            @Override
            public int getFireSpreadSpeed(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
                return 60;
            }

        });
    }

    /**
     * Register some {@link MWPointedDripstoneBlock pointed dripstone}
     *
     * @param name {@link String The block name}
     * @param blockSupplier {@link Supplier<Block> The supplier for the dripstone source block}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerDripstone(final String name, final Supplier<Block> blockSupplier) {
        return registerBlock(name, () -> new MWPointedDripstoneBlock(blockSupplier));
    }

    /**
     * Register some {@link IcePointedDripstoneBlock ice pointed dripstone}
     *
     * @param name {@link String The block name}
     * @param blockSupplier {@link Supplier<Block> The supplier for the dripstone source block}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerIceDripstone(final String name, final Supplier<Block> blockSupplier) {
        return registerBlock(name, () -> new IcePointedDripstoneBlock(blockSupplier));
    }

    /**
     * Register a {@link Block block}
     *
     * @param name {@link String The block name}
     * @param blockSupplier {@link Supplier<Block> The supplier for the block to register}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this block to be registered}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerBlock(final String name, final Supplier<? extends Block> blockSupplier, final FeatureFlag... featureFlags) {
        RegistryObject<Block> block = registerBlockWithoutBlockItem(name, blockSupplier);
        registerBlockItem(name, block, featureFlags);
        return block;
    }

    /**
     * Register a {@link BlockItem block id}
     *
     * @param name  {@link String The block name}
     * @param block {@link RegistryObject<Block> The supplier for the block the id is referring to}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this block to be registered}
     */
    static void registerBlockItem(final String name, final RegistryObject<? extends Block> block, final FeatureFlag... featureFlags) {
        registerItem(name, () -> new BlockItem(block.get(), PropertyHelper.basicItemProperties(featureFlags)));
    }

    /**
     * Register a {@link Block block} without also registering a {@link BlockItem block id}
     *
     * @param name {@link String The block name}
     * @param blockSupplier {@link Supplier<Block> The supplier for the block to register}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerBlockWithoutBlockItem(final String name, final Supplier<? extends Block> blockSupplier) {
        return BLOCKS.register(name, blockSupplier);
    }

    /**
     * Register an {@link EntityType entity type}
     *
     * @param name {@link String The entity type name}
     * @param entityBuilder {@link EntityType.Builder<T> The entity type builder}
     * @return {@link RegistryObject<EntityType> The registered entity type}
     * @param <T> {@link T The entity type}
     */
    public static <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(final String name, final EntityType.Builder<T> entityBuilder) {
        return ENTITY_TYPES.register(name, () -> entityBuilder.build(KeyHelper.location(name).toString()));
    }

    /**
     * Register a {@link BlockEntityType block entity type}
     *
     * @param name {@link String The block entity name}
     * @param blockEntitySupplier {@link BlockEntityType.BlockEntitySupplier<T> The supplier for the block entity}
     * @param blockSuppliers {@link Supplier<Block> The supplier for the blocks related to the block entity}
     * @return {@link RegistryObject<BlockEntityType> The registered block entity}
     * @param <T> {@link T The block entity type}
     */
    @SafeVarargs
    public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerBlockEntity(final String name, final BlockEntityType.BlockEntitySupplier<T> blockEntitySupplier, final Supplier<Block>... blockSuppliers) {
        return BLOCK_ENTITY_TYPES.register(name, () -> BlockEntityType.Builder.of(blockEntitySupplier, Arrays.stream(blockSuppliers).map(Supplier::get).toList().toArray(new Block[0])).build(null));
    }

    /**
     * Register an Overworld {@link ConfiguredFeature ore configured feature}
     *
     * @param context {@link BootstapContext <ConfiguredFeature> The bootstrap context}
     * @param key {@link ResourceKey The configured feature resource key}
     * @param stoneOre {@link Supplier<Block> The stone ore block supplier}
     * @param deepslateOre {@link Supplier<Block> The deepslate ore block supplier}
     * @param size {@link Integer The number of blocks per vein}
     */
    public static void registerOverworldOreConfiguredFeature(final BootstapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> key, final Supplier<Block> stoneOre, final Supplier<Block> deepslateOre, final int size) {
        registerOreConfiguredFeature(context, key, PropertyHelper.createOverworldTargetStates(stoneOre, deepslateOre), size);
    }

    /**
     * Register an Overworld {@link ConfiguredFeature ore configured feature}
     * that doesn't have a variant
     *
     * @param context {@link BootstapContext<ConfiguredFeature> The bootstrap context}
     * @param key {@link ResourceKey The configured feature resource key}
     * @param oreSupplier {@link Supplier<Block> The stone ore block supplier}
     * @param size {@link Integer The number of blocks per vein}
     */
    public static void registerOverworldOreConfiguredFeature(final BootstapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> key, final Supplier<Block> oreSupplier, final int size) {
        registerOreConfiguredFeature(context, key, PropertyHelper.createOverworldTargetStates(oreSupplier), size);
    }

    /**
     * Register a Nether {@link ConfiguredFeature ore configured feature}
     *
     * @param context {@link BootstapContext<ConfiguredFeature> The bootstrap context}
     * @param key {@link ResourceKey The configured feature resource key}
     * @param oreSupplier {@link Supplier<Block> The ore block supplier}
     * @param size {@link Integer The number of blocks per vein}
     */
    public static void registerNetherOreConfiguredFeature(final BootstapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> key, final Supplier<Block> oreSupplier, final int size) {
        registerOreConfiguredFeature(context, key, PropertyHelper.createNetherTargetState(oreSupplier), size);
    }

    /**
     * Register an {@link ConfiguredFeature ore configured feature}
     *
     * @param context {@link BootstapContext<ConfiguredFeature> The bootstrap context}
     * @param key {@link ResourceKey The configured feature resource key}
     * @param oreTargetStates {@link List<OreConfiguration.TargetBlockState> The ore block target states}
     * @param size {@link Integer The number of blocks per vein}
     */
    static void registerOreConfiguredFeature(final BootstapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> key, final Supplier<List<OreConfiguration.TargetBlockState>> oreTargetStates, final int size) {
        FeatureUtils.register(context, key, Feature.ORE, new OreConfiguration(oreTargetStates.get(), size));
    }

    /**
     * Register a {@link Feature#RANDOM_PATCH random patch feature}
     *
     * @param context {@link BootstapContext<ConfiguredFeature> The bootstrap context}
     * @param key {@link ResourceKey The configured feature resource key}
     * @param blockStateSupplier {@link Supplier<BlockState> The supplier for the block state to place}
     */
    public static void registerRandomPatchConfiguredFeature(final BootstapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> key, final Supplier<BlockState> blockStateSupplier) {
        FeatureUtils.register(context, key, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(blockStateSupplier.get())), List.of(Blocks.GRASS_BLOCK)));
    }

    /**
     * Register a common ore using the {@link HeightRangePlacement#triangle triangle height range placement}
     * with the {@link VerticalAnchor#absolute absolute values set}
     *
     * @param context {@link BootstapContext<PlacedFeature> The bootstrap context}
     * @param key {@link ResourceKey<PlacedFeature> The placed feature resource key}
     * @param oreConfiguredFeature {@link Holder <ConfiguredFeature> The ore feature that this placed feature will place}
     * @param count {@link Integer How many ores per chunk should be generated}
     * @param minHeight {@link Integer The minimum height that the ore can spawn}
     * @param maxHeight {@link Integer The maximum height that the ore can spawn}
     */
    public static void registerCommonOrePlacedFeature(final BootstapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> key, final Holder<ConfiguredFeature<?, ?>> oreConfiguredFeature, final int count, final int minHeight, final int maxHeight) {
        registerPlacedFeature(context, key, oreConfiguredFeature, PropertyHelper.commonOrePlacement(count, HeightRangePlacement.triangle(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight))));
    }

    /**
     * Register a common ore using the {@link HeightRangePlacement#uniform uniform height range placement}
     * with the {@link VerticalAnchor#absolute absolute values set}
     *
     * @param context {@link BootstapContext<PlacedFeature> The bootstrap context}
     * @param key {@link ResourceKey<PlacedFeature> The placed feature resource key}
     * @param oreConfiguredFeature {@link Holder <ConfiguredFeature> The ore feature that this placed feature will place}
     * @param count {@link Integer How many ores per chunk should be generated}
     * @param maxHeight {@link Integer The maximum height that the ore can spawn}
     */
    public static void registerCommonOrePlacedFeature(final BootstapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> key, final Holder<ConfiguredFeature<?, ?>> oreConfiguredFeature, final int count, final int maxHeight) {
        registerPlacedFeature(context, key, oreConfiguredFeature, PropertyHelper.commonOrePlacement(count, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(maxHeight))));
    }

    /**
     * Register a rare ore using the {@link HeightRangePlacement#triangle triangle height range placement}
     * with the {@link VerticalAnchor#aboveBottom above bottom} and {@link VerticalAnchor#belowTop below top} values set
     *
     * @param context {@link BootstapContext<PlacedFeature> The bootstrap context}
     * @param key {@link ResourceKey<PlacedFeature> The placed feature resource key}
     * @param oreConfiguredFeature {@link Holder<ConfiguredFeature> The ore feature that this placed feature will place}
     * @param chance {@link Integer The rarity, in chunks, for the ore to be placed (eg: 1 in X chunks)}
     * @param minHeight {@link Integer The minimum height that the ore can spawn}
     * @param maxHeight {@link Integer The maximum height that the ore can spawn}
     */
    private static void registerRareOrePlacedFeature(final BootstapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> key, final Holder<ConfiguredFeature<?, ?>> oreConfiguredFeature, final int chance, final int minHeight, final int maxHeight) {
        registerPlacedFeature(context, key, oreConfiguredFeature, PropertyHelper.rareOrePlacement(chance, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(minHeight), VerticalAnchor.belowTop(maxHeight))));
    }

    /**
     * Register a {@link PlacedFeature placed feature}
     *
     * @param context {@link BootstapContext<PlacedFeature> The bootstrap context}
     * @param key {@link ResourceKey<PlacedFeature> The placed feature resource key}
     * @param configuredFeatureHolder {@link Holder<ConfiguredFeature> The configured feature that this placed feature will place}
     */
    public static void registerPlacedFeature(final BootstapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> key, final Holder<ConfiguredFeature<?, ?>> configuredFeatureHolder) {
        PlacementUtils.register(context, key, configuredFeatureHolder);
    }

    /**
     * Register a {@link PlacedFeature placed feature}
     *
     * @param context {@link BootstapContext<PlacedFeature> The bootstrap context}
     * @param key {@link ResourceKey<PlacedFeature> The placed feature resource key}
     * @param configuredFeatureHolder {@link Holder<ConfiguredFeature> The configured feature that this placed feature will place}
     * @param placementModifiers {@link List<PlacementModifier> The placement modifiers}
     */
    public static void registerPlacedFeature(final BootstapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> key, final Holder<ConfiguredFeature<?, ?>> configuredFeatureHolder, final List<PlacementModifier> placementModifiers) {
        PlacementUtils.register(context, key, configuredFeatureHolder, placementModifiers);
    }

    /**
     * Register a {@link PlacedFeature patch placed feature}
     *
     * @param context {@link BootstapContext<PlacedFeature> The boostrap context}
     * @param key {@link ResourceKey<PlacedFeature> The placed feature resource key}
     * @param configuredFeatureHolder {@link Holder<ConfiguredFeature> The configured feature that this placed feature will place}
     * @param averageCount {@link Integer The average chunk count for the placement}
     */
    public static void registerPatchPlacedFeature(final BootstapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> key, final Holder<ConfiguredFeature<?, ?>> configuredFeatureHolder, final int averageCount) {
        registerPlacedFeature(context, key, configuredFeatureHolder, List.of(RarityFilter.onAverageOnceEvery(averageCount), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
    }

    /**
     * Register a {@link TrimMaterial trim material} with darker variants
     *
     * @param context {@link BootstapContext<TrimMaterial> The bootstrap context}
     * @param resourceKey {@link ResourceKey<TrimMaterial> The trim material resource key}
     * @param material {@link Item The id used to apply the trim material}
     * @param color {@link MWColors The text color for the id tooltip}
     * @param itemModelIndex {@link Float The index value for the id model override}
     * @param darkerVariants {@link Map The map of trim materials to be applied when the armor matches a specific armor material}
     */
    public static void registerTrimMaterial(final BootstapContext<TrimMaterial> context, final ResourceKey<TrimMaterial> resourceKey, final Item material, final MWColors color, final float itemModelIndex, final ArmorMaterials... darkerVariants) {
        final String name = resourceKey.location().getPath();
        final Map<ArmorMaterials, String> variants =
                darkerVariants != null ?
                        Arrays.stream(darkerVariants).collect(Collectors.toMap((variant) -> variant, (variant) -> name + "_darker"))
                        : Map.of();
        TrimMaterial trimmaterial = TrimMaterial.create(
                name,
                material,
                itemModelIndex,
                ComponentHelper.trimMaterial(resourceKey).withStyle(Style.EMPTY.withColor(color.toText())),
                variants);
        context.register(resourceKey, trimmaterial);
    }

    /**
     * Register a {@link CreativeModeTab creative tab}
     *
     * @param name {@link String The tab name}
     * @param beforeTab Before which {@link ResourceKey<CreativeModeTab> creative tab} this tab should appear
     * @param iconSupplier {@link Supplier<ItemStack> The icon supplier}. Determines which {@link Item id} to use as tab icon
     * @return {@link RegistryObject<CreativeModeTab> The registered creative mode tab}
     */
    public static RegistryObject<CreativeModeTab> registerCreativeTab(final String name, final ResourceKey<CreativeModeTab> beforeTab, final Supplier<ItemStack> iconSupplier) {
        return CREATIVE_MODE_TABS.register(name, () -> {
                    CreativeModeTab.Builder builder = CreativeModeTab.builder()
                    .icon(iconSupplier)
                    .title(ComponentHelper.itemGroup(name));

                    if(beforeTab != null) {
                         builder = builder.withTabsBefore(beforeTab);
                    }

                    return builder.build();
                });
    }

    /**
     * Register a {@link BlockSetType block set type}
     *
     * @param name {@link String The block set type name}
     * @param canOpenByHand {@link Boolean If the block set doors/trapdoors can be open using hands}
     * @param defaultSound {@link SoundType The block set type default sound}
     * @param doorCloseSound {@link SoundEvent The sound to play when a door or a trapdoor is closed}
     * @param doorOpenSound {@link SoundEvent The sound to play when a door or a trapdoor is opened}
     * @param pressurePlateClickOffSound {@link SoundEvent The sound to play when a pressure plate looses pressure}
     * @param pressurePlateClickOnSound {@link SoundEvent The sound to play when a pressure plate is pressed}
     * @param buttonClickOffSound {@link SoundEvent The sound to play when a button looses pressure}
     * @param buttonClickOnSound {@link SoundEvent The sound to play when a button is pressed}
     * @return {@link BlockSetType The registered block set type}
     */
    public static BlockSetType registerBlockSetType(final String name, final boolean canOpenByHand, final SoundType defaultSound, final SoundEvent doorCloseSound, final SoundEvent doorOpenSound, final SoundEvent pressurePlateClickOffSound, final SoundEvent pressurePlateClickOnSound, final SoundEvent buttonClickOffSound, final SoundEvent buttonClickOnSound) {
        return registerBlockSetType(name,
                canOpenByHand, defaultSound,
                doorCloseSound, doorOpenSound,
                doorCloseSound, doorOpenSound,
                pressurePlateClickOffSound, pressurePlateClickOnSound,
                buttonClickOffSound, buttonClickOnSound);
    }

    /**
     * Register a {@link BlockSetType block set type}
     *
     * @param name {@link String The block set type name}
     * @param canOpenByHand {@link Boolean If the block set doors/trapdoors can be open using hands}
     * @param defaultSound {@link SoundType The block set type default sound}
     * @param doorCloseSound {@link SoundEvent The sound to play when a door is closed}
     * @param doorOpenSound {@link SoundEvent The sound to play when a door is opened}
     * @param trapdoorCloseSound {@link SoundEvent The sound to play when a trapdoor is closed}
     * @param trapdoorOpenSound {@link SoundEvent The sound to play when a trapddoor is closed}
     * @param pressurePlateClickOffSound {@link SoundEvent The sound to play when a pressure plate looses pressure}
     * @param pressurePlateClickOnSound {@link SoundEvent The sound to play when a pressure plate is pressed}
     * @param buttonClickOffSound {@link SoundEvent The sound to play when a button looses pressure}
     * @param buttonClickOnSound {@link SoundEvent The sound to play when a button is pressed}
     * @return {@link BlockSetType The registered block set type}
     */
    private static BlockSetType registerBlockSetType(final String name, final boolean canOpenByHand, final SoundType defaultSound, final SoundEvent doorCloseSound, final SoundEvent doorOpenSound, final SoundEvent trapdoorCloseSound, final SoundEvent trapdoorOpenSound, final SoundEvent pressurePlateClickOffSound, final SoundEvent pressurePlateClickOnSound, final SoundEvent buttonClickOffSound, final SoundEvent buttonClickOnSound) {
        return BlockSetType.register(new BlockSetType(KeyHelper.location(name).toString(),
                canOpenByHand,
                defaultSound,
                doorCloseSound, doorOpenSound,
                trapdoorCloseSound, trapdoorOpenSound,
                pressurePlateClickOffSound, pressurePlateClickOnSound,
                buttonClickOffSound, buttonClickOnSound)
        );
    }

    /**
     * Register a {@link TagKey<Block> block tag}
     *
     * @param name {@link String The tag name}
     */
    public static TagKey<Block> registerBlockTag(final String name) {
        return BlockTags.create(KeyHelper.location(name));
    }

    /**
     * Register a {@link TagKey<Item> id tag}
     *
     * @param name {@link String The tag name}
     */
    public static TagKey<Item> registerItemTag(final String name) {
        return ItemTags.create(KeyHelper.location(name));
    }

    /**
     * Register some {@link DispenseItemBehavior dispense behaviors}
     *
     * @param dispenseBehavior {@link DispenseItemBehavior The dispense behavior to register}
     * @param items {@link Supplier<ItemLike> The items to apply the dispense behavior}
     */
    @SafeVarargs
    public static void registerDispenseBehaviors(final DispenseItemBehavior dispenseBehavior, final Supplier<? extends ItemLike>... items) {
        Arrays.stream(items).forEach(item -> registerDispenseBehavior(dispenseBehavior, item));
    }

    /**
     * Register some {@link DispenseItemBehavior dispense behaviors}
     *
     * @param dispenseBehaviors {@link Supplier<Map.Entry> The dispense behavior suppliers to register}
     */
    public static void registerDispenseBehaviors(final Map<DispenseItemBehavior, Supplier<? extends ItemLike>> dispenseBehaviors) {
        dispenseBehaviors.forEach(RegisterHelper::registerDispenseBehavior);
    }

    /**
     * Register a {@link DispenseItemBehavior dispense behavior}
     *
     * @param dispenseItemBehavior {@link DispenseItemBehavior The dispense behavior to register}
     * @param item {@link Supplier<ItemLike> The id to apply the dispense behavior}
     */
    static void registerDispenseBehavior(final DispenseItemBehavior dispenseItemBehavior, final Supplier<? extends ItemLike> item) {
        DispenserBlock.registerBehavior(item.get(), dispenseItemBehavior);
    }

    /**
     * Register a {@link ResourceLocation statistic}
     *
     * @param name {@link String The statistic name}
     * @return {@link RegistryObject<ResourceLocation> The registered statistic}
     */
    public static RegistryObject<ResourceLocation> registerStatistic(final String name) {
        return STATISTICS.register(name, () -> KeyHelper.location(name));
    }

    /**
     * Register a {@link MenuType menu type}
     *
     * @param name {@link String The menu name}
     * @param factory {@link IContainerFactory The menu container factory}
     * @return {@link RegistryObject<MenuType> The registered menu type}
     * @param <T> {@link T The menu type}
     */
    public static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(final String name, final IContainerFactory<T> factory) {
        return MENU_TYPES.register(name, () -> IForgeMenuType.create(factory));
    }

    /**
     * Register a {@link RecipeType recipe type}
     *
     * @param name {@link String The recipe type name}
     * @return {@link RegistryObject<RecipeType> The registered recipe type}
     * @param <T> {@link T The recipe type}
     */
    public static <T extends Recipe<?>> RegistryObject<RecipeType<T>> registerRecipeType(final String name) {
        return RECIPE_TYPES.register(name, () -> RecipeType.simple(KeyHelper.location(name)));
    }

    /**
     * Register a {@link RecipeSerializer recipe serializer}
     *
     * @param name {@link String The recipe serializer name}
     * @return {@link RegistryObject<RecipeType> The registered recipe serializer}
     * @param <T> {@link T The menu type}
     */
    public static <T extends Recipe<?>> RegistryObject<RecipeSerializer<T>> registerRecipeSerializer(final String name, final Supplier<RecipeSerializer<T>> recipeSerializerSupplier) {
        return RECIPE_SERIALIZERS.register(name, recipeSerializerSupplier);
    }

    /**
     * Register a {@link PoiType villager poi type}
     *
     * @param name {@link String The poi type name}
     * @param blockSupplier {@link Supplier<Block> The supplier for the block this poi type is referring to}
     * @return {@link RegistryObject<PoiType> The registered poi type}
     */
    public static RegistryObject<PoiType> registerPOIType(final String name, final Supplier<? extends Block> blockSupplier) {
        return POI_TYPES.register(name, () -> new PoiType(ImmutableSet.copyOf(blockSupplier.get().getStateDefinition().getPossibleStates()), 1, 1));
    }

    /**
     * Register a {@link MineWorld MineWorld} {@link VillagerProfession villager profession}
     *
     * @param name {@link String The villager profession name}
     * @param poiTypeSupplier {@link Supplier<PoiType> The supplier for the villager poi}
     * @param workSound {@link SoundEvent The sounde vent to play when the villager acquire some work}
     * @return {@link RegistryObject<VillagerProfession> The registered villager profession}
     */
    public static RegistryObject<VillagerProfession> registerVillagerProfession(final String name, final Supplier<? extends PoiType> poiTypeSupplier, final SoundEvent workSound) {
        return VILLAGER_PROFESSIONS.register(name, () -> new VillagerProfession(
                name,
                x -> x.get().equals(poiTypeSupplier.get()),
                x -> x.get().equals(poiTypeSupplier.get()),
                ImmutableSet.of(),
                ImmutableSet.of(),
                workSound
                )
        );
    }

    /**
     * Register a {@link IGlobalLootModifier loot modifier}
     *
     * @param name {@link String The loot modifier name}
     * @param codecSupplier {@link Supplier<IGlobalLootModifier> The loot modifier codec supplier}
     * @return {@link RegistryObject<IGlobalLootModifier> The registered loot modifier}
     */
    public static <T extends IGlobalLootModifier> RegistryObject<Codec<T>> registerLootModifier(final String name, Supplier<Codec<T>> codecSupplier) {
        return LOOT_MODIFIER_SERIALIZERS.register(name, codecSupplier);
    }

    /**
     * Register a {@link WoodType wood type}
     *
     * @param name {@link String The wood type name}
     * @return {@link WoodType The registered wood type}
     */
    public static WoodType registerWoodType(final String name) {
        return WoodType.register(new WoodType(MineWorld.MOD_ID + ":" + name, BlockSetType.OAK));
    }

    /**
     * Register a {@link Biome biome}
     *
     * @param key {@link ResourceKey<Biome> The biome resource key}
     * @param biomeSupplier {@link Supplier<Biome> The biome supplier}
     * @return {@link RegistryObject<Biome> The registered biome}
     */
    public static RegistryObject<Biome> registerBiome(final ResourceKey<Biome> key, final Supplier<Biome> biomeSupplier) {
        return BIOMES.register(key.location().getPath(), biomeSupplier);
    }

    /**
     * Register a {@link Feature feature}
     *
     * @param name {@link String The feature name}
     * @param featureSupplier {@link Supplier<Feature> The feature supplier}
     * @return {@link RegistryObject<Feature> The registered feature}
     */
    public static <FC extends FeatureConfiguration> RegistryObject<Feature<FC>> registerFeature(final String name, final Supplier<? extends Feature<FC>> featureSupplier) {
        return FEATURES.register(name, featureSupplier);
    }

    /**
     * Register a {@link TrunkPlacerType trunk placer type}
     *
     * @param name {@link String The trunk palcer type name}
     * @param trunkPlacerSupplier {@link Supplier<TrunkPlacerType> The trunk placer type supplier}
     * @return {@link RegistryObject<TrunkPlacerType> The registered trunk placer type}
     * @param <TP> {@link TP The trunk placer type}
     */
    public static <TP extends TrunkPlacer> RegistryObject<TrunkPlacerType<TP>> registerTrunkPlacerType(final String name, final Supplier<? extends TrunkPlacerType<TP>> trunkPlacerSupplier) {
        return TRUNK_PLACER_TYPES.register(name + "_trunk_placer", trunkPlacerSupplier);
    }

    /**
     * Register a {@link FoliagePlacerType foliage placer type}
     *
     * @param name {@link String The foliage palcer type name}
     * @param foliagePlacerSupplier {@link Supplier<FoliagePlacerType> The foliage placer type supplier}
     * @return {@link RegistryObject<FoliagePlacerType> The registered foliage placer type}
     * @param <FP> {@link FP The foliage placer type}
     */
    public static <FP extends FoliagePlacer> RegistryObject<FoliagePlacerType<FP>> registerFoliagePlacerType(final String name, final Supplier<? extends FoliagePlacerType<FP>> foliagePlacerSupplier) {
        return FOLIAGE_PLACER_TYPES.register(name + "_foliage_placer", foliagePlacerSupplier);
    }

    /**
     * Register a {@link StructureType structure type}
     *
     * @param name {@link String The structure name}
     * @param codec {@link Codec The structure codec}
     * @return {@link RegistryObject<StructureType> The registered structure type}
     * @param <T> {@link T The structure type}
     */
    public static <T extends Structure> RegistryObject<StructureType<T>> registerStructureType(final String name, final Codec<T> codec) {
        return STRUCTURE_TYPES.register(name, () -> () -> codec);
    }

    /**
     * Register the {@link MineWorld MineWorld} compostables
     */
    public static void registerCompostables() {
        registerCompostable(MWBlocks.GRASS_CARPET.get(), 0.1F);
        registerCompostable(MWBlocks.OAK_LEAVES_CARPET.get(), 0.1F);
        registerCompostable(MWBlocks.OAK_BUSH.get(), 0.65F);
        registerCompostable(MWBlocks.SPRUCE_LEAVES_CARPET.get(), 0.1F);
        registerCompostable(MWBlocks.SPRUCE_BUSH.get(), 0.65F);
        registerCompostable(MWBlocks.BIRCH_LEAVES_CARPET.get(), 0.1F);
        registerCompostable(MWBlocks.BIRCH_BUSH.get(), 0.65F);
        registerCompostable(MWBlocks.JUNGLE_LEAVES_CARPET.get(), 0.1F);
        registerCompostable(MWBlocks.JUNGLE_BUSH.get(), 0.65F);
        registerCompostable(MWBlocks.ACACIA_LEAVES_CARPET.get(), 0.1F);
        registerCompostable(MWBlocks.ACACIA_BUSH.get(), 0.65F);
        registerCompostable(MWBlocks.DARK_OAK_LEAVES_CARPET.get(), 0.1F);
        registerCompostable(MWBlocks.DARK_OAK_BUSH.get(), 0.65F);
        registerCompostable(MWBlocks.MANGROVE_LEAVES_CARPET.get(), 0.1F);
        registerCompostable(MWBlocks.MANGROVE_ROOTS_CARPET.get(), 0.1F);
        registerCompostable(MWBlocks.MANGROVE_BUSH.get(), 0.65F);
        registerCompostable(MWBlocks.CHERRY_LEAVES_CARPET.get(), 0.1F);
        registerCompostable(MWBlocks.CHERRY_BUSH.get(), 0.85F);
        registerCompostable(MWBlocks.AZALEA_LEAVES_CARPET.get(), 0.25F);
        registerCompostable(MWBlocks.FLOWERING_AZALEA_LEAVES_CARPET.get(), 0.3F);
        registerCompostable(MWBlocks.APPLE_LEAVES_CARPET.get(), 0.1F);
        registerCompostable(MWBlocks.APPLE_LEAVES.get(), 0.3F);
        registerCompostable(MWBlocks.APPLE_SAPLING.get(), 0.3F);
        registerCompostable(MWBlocks.APPLE_BUSH.get(), 0.65F);
        registerCompostable(MWBlocks.PALM_LEAVES_CARPET.get(), 0.1F);
        registerCompostable(MWBlocks.PALM_LEAVES.get(), 0.3F);
        registerCompostable(MWBlocks.PALM_SAPLING.get(), 0.3F);
        registerCompostable(MWBlocks.PALM_BUSH.get(), 0.65F);
        registerCompostable(MWBlocks.SCULK_LEAVES_CARPET.get(), 0.1F);
        registerCompostable(MWBlocks.SCULK_LEAVES.get(), 0.3F);
        registerCompostable(MWBlocks.SCULK_SAPLING.get(), 0.3F);
        registerCompostable(MWBlocks.SCULK_BUSH.get(), 0.65F);
        registerCompostable(MWBlocks.NETHER_WART_CARPET.get(), 0.3F);
        registerCompostable(MWBlocks.WARPED_WART_CARPET.get(), 0.3F);
        registerCompostable(MWBlocks.WARPED_WART.get(), 0.85F);
        registerCompostable(MWBlocks.BLUE_ROSE.get(), 0.65F);
        registerCompostable(MWBlocks.BLUE_ROSE_BUSH.get(), 0.65F);
        registerCompostable(MWBlocks.WHITE_ROSE.get(), 0.65F);
        registerCompostable(MWBlocks.WHITE_ROSE_BUSH.get(), 0.65F);
        registerCompostable(MWBlocks.CATTAIL.get(), 0.65F);
        registerCompostable(MWItems.CORN_SEEDS.get(), 0.3F);
        registerCompostable(MWItems.BLUEBERRIES.get(), 0.3F);
        registerCompostable(MWItems.CHERRY.get(), 0.3F);
    }

    /**
     * Register a {@link ItemLike compostable id}
     *
     * @param item {@link ItemLike The id to register}
     * @param chance {@link Float The compostable chance}
     */
    static void registerCompostable(final ItemLike item, final float chance) {
        ComposterBlock.COMPOSTABLES.put(ItemHelper.getItem(item), chance);
    }

    /**
     * Register all {@link MineWorld MineWorld} {@link FlowerPotBlock flower pots}
     */
    public static void registerFlowerPots() {
        final FlowerPotBlock flowerPot = (FlowerPotBlock) Blocks.FLOWER_POT;
        flowerPots.forEach((flower, pot) -> flowerPot.addPlant(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(flower.get())), pot));
    }

    /**
     * Register a {@link BlockRotProcessor block rot structure processor}
     * given a {@link Float degradation chance}
     *
     * @param context {@link BootstapContext<StructureProcessorList> The structure processor bootstrap context}
     * @param key {@link ResourceKey<StructureProcessorList> The structure processor resource key}
     * @param chance {@link Float The degradation chance}
     */
    public static void registerBlockRotStructureProcessor(final BootstapContext<StructureProcessorList> context, final ResourceKey<StructureProcessorList> key, final float chance) {
        registerStructureProcessor(context, key, ImmutableList.of(new BlockRotProcessor(chance)));
    }

    /**
     * Register a {@link StructureProcessorList structure processor}
     *
     * @param context {@link BootstapContext<StructureProcessorList> The structure processor bootstrap context}
     * @param key {@link ResourceKey<StructureProcessorList> The structure processor resource key}
     * @param structureProcessors {@link List<StructureProcessor> The structure processors list}
     */
    public static void registerStructureProcessor(final BootstapContext<StructureProcessorList> context, final ResourceKey<StructureProcessorList> key, final List<StructureProcessor> structureProcessors) {
        context.register(key, new StructureProcessorList(structureProcessors));
    }

    /**
     * Register all {@link MineWorld MineWorld} {@link CreativeModeTab creative mode tabs}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void registerCreativeModeTabs(final IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    /**
     * Register all {@link MineWorld MineWorld} {@link Block blocks}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void registerBlocks(final IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    /**
     * Register all {@link MineWorld MineWorld} {@link Item items}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void registerItems(final IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    /**
     * Register all {@link MineWorld MineWorld} {@link EntityType entity types}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void registerEntityTypes(final IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

    /**
     * Register all {@link MineWorld MineWorld} {@link BlockEntityType block entity types}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void registerBlockEntityTypes(final IEventBus eventBus) {
        BLOCK_ENTITY_TYPES.register(eventBus);
    }

    /**
     * Register all {@link MineWorld MineWorld} {@link ResourceLocation statistics}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void registerStatistics(final IEventBus eventBus) {
        STATISTICS.register(eventBus);
    }

    /**
     * Register all {@link MineWorld MineWorld} {@link MenuType menu types}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void registerMenuTypes(final IEventBus eventBus) {
        MENU_TYPES.register(eventBus);
    }

    /**
     * Register all {@link MineWorld MineWorld} {@link RecipeType recipe types}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void registerRecipeTypes(final IEventBus eventBus) {
        RECIPE_TYPES.register(eventBus);
    }

    /**
     * Register all {@link MineWorld MineWorld} {@link RecipeSerializer recipe serializers}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void registerRecipeSerializers(final IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }

    /**
     * Register all {@link MineWorld MineWorld} {@link PoiType villager poi types}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void registerPOITypes(final IEventBus eventBus) {
        POI_TYPES.register(eventBus);
    }

    /**
     * Register all {@link MineWorld MineWorld} {@link VillagerProfession villager professions}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void registerVillagerProfessions(final IEventBus eventBus) {
        VILLAGER_PROFESSIONS.register(eventBus);
    }

    /**
     * Register all {@link MineWorld MineWorld} {@link IGlobalLootModifier global loot modifiers}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void registerLootModifiers(final IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }

    /**
     * Register all {@link MineWorld MineWorld} {@link Biome biomes}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void registerBiomes(final IEventBus eventBus) {
        BIOMES.register(eventBus);
    }

    /**
     * Register all {@link MineWorld MineWorld} {@link Feature features}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void registerFeatures(final IEventBus eventBus) {
        FEATURES.register(eventBus);
    }

    /**
     * Register all {@link MineWorld MineWorld} {@link TrunkPlacerType trunk placer types}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void registerTrunkPlacerTypes(final IEventBus eventBus) {
        TRUNK_PLACER_TYPES.register(eventBus);
    }

    /**
     * Register all {@link MineWorld MineWorld} {@link FoliagePlacerType foliage placer types}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void registerFoliagePlacerTypes(final IEventBus eventBus) {
        FOLIAGE_PLACER_TYPES.register(eventBus);
    }

    /**
     * Register all {@link MineWorld MineWorld} {@link StructureType structure types}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void registerStructureTypes(final IEventBus eventBus) {
        STRUCTURE_TYPES.register(eventBus);
    }

}