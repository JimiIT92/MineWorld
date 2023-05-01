package org.mineworld.helper;

import com.google.common.collect.ImmutableSet;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.block.CoralFlowerPotBlock;
import org.mineworld.block.PebbleBlock;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWColors;
import org.mineworld.core.MWItems;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
     * {@link DeferredRegister <Block> The block registry}
     */
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MineWorld.MOD_ID);
    /**
     * {@link DeferredRegister<Item> The item registry}
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
    private static final DeferredRegister<VillagerProfession> VILLGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, MineWorld.MOD_ID);
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
     * @param name {@link String The item name}
     * @param templateName {@link String The smithing template name}
     * @param baseSlotIcon {@link ResourceLocation Icon to show inside the base slot}
     * @param additionSlotIcon {@link ResourceLocation Icon to show inside the additions slot}
     * @return {@link RegistryObject<Item> The registered item}
     */
    public static RegistryObject<Item> registerSmithingTemplate(final String name, final String templateName, final String baseSlotIcon, final String additionSlotIcon) {
        return registerSmithingTemplate(name, templateName,
                List.of(new ResourceLocation(MineWorld.MOD_ID, "item/empty_slot_" + baseSlotIcon)),
                List.of(new ResourceLocation(MineWorld.MOD_ID, "item/empty_slot_" + additionSlotIcon)));
    }

    /**
     * Create a basic {@link SmithingTemplateItem smithing template}
     *
     * @param name {@link String The item name}
     * @param templateName {@link String The smithing template name}
     * @param baseSlotIcons {@link List<ResourceLocation> List of icons to show inside the base slot}
     * @param additionSlotIcons {@link List<ResourceLocation> List of icons to show inside the additions slot}
     * @return {@link RegistryObject<Item> The registered item}
     */
    static RegistryObject<Item> registerSmithingTemplate(final String name,final String templateName, final List<ResourceLocation> baseSlotIcons, final List<ResourceLocation> additionSlotIcons) {
        return registerItem(name, () -> new SmithingTemplateItem(
                Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(MineWorld.MOD_ID, "smithing_template." + templateName + ".applies_to"))).withStyle(ChatFormatting.BLUE),
                Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(MineWorld.MOD_ID, "smithing_template." + templateName + ".ingredients"))).withStyle(ChatFormatting.BLUE),
                Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation(MineWorld.MOD_ID, templateName))).withStyle(ChatFormatting.GRAY),
                Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(MineWorld.MOD_ID, "smithing_template." + templateName + ".base_slot_description"))),
                Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(MineWorld.MOD_ID, "smithing_template." + templateName + ".additions_slot_description"))),
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
             * Initialize the item client rendering
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
     * Register a {@link HorseArmorItem horse armor item}
     *
     * @param materialName {@link String The armor material name}
     * @param protection {@link Integer The armor protection amount}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    public static RegistryObject<Item> registerHorseArmorItem(final String materialName, final int protection, final FeatureFlag... featureFlags) {
        final ResourceLocation textureLocation = new ResourceLocation(MineWorld.MOD_ID, "textures/entity/horse/armor/horse_armor_" + materialName + ".png");
        return registerItem(materialName + "_horse_armor", () -> new HorseArmorItem(protection, textureLocation, PropertyHelper.basicItemProperties(featureFlags).stacksTo(1)));
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
        final ForgeTier hoeTier = new ForgeTier(tier.getLevel(), tier.getUses(), tier.getSpeed(), 0, tier.getEnchantmentValue(), tier.getTag(), tier::getRepairIngredient);
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
        return registerOreBlock(name, PropertyHelper.oreBlockProperties(false, featureFlags).color(MaterialColor.NETHER).sound(SoundType.NETHER_ORE), 2, 5);
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
     * @param color {@link MaterialColor The block color on maps}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerRawOreBlock(final String name, final MaterialColor color, final FeatureFlag... featureFlags) {
        return registerOreStorageBlock(name, Material.STONE, color,null, featureFlags);
    }

    /**
     * Register a {@link Block raw ore block}
     *
     * @param name {@link String The block name}
     * @param color {@link MaterialColor The block color on maps}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerMetalOreStorageBlock(final String name, MaterialColor color, final FeatureFlag... featureFlags) {
        return registerOreStorageBlock(name, Material.METAL, color, SoundType.METAL, featureFlags);
    }

    /**
     * Register a {@link Block raw ore block}
     *
     * @param name {@link String The block name}
     * @param material {@link Material The block material}
     * @param color {@link MaterialColor The block color on maps}
     * @param sound {@link SoundType The block sound}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    static RegistryObject<Block> registerOreStorageBlock(final String name, final Material material, final MaterialColor color, final SoundType sound, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> PropertyHelper.oreStorageBlockProperties(material, color,sound, featureFlags));
    }

    /**
     * Register a {@link Block fuel block}
     *
     * @param name {@link String The block name}
     * @param color {@link MaterialColor The block color on maps}
     * @param burnTime {@link Integer The fuel burn time}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerFuelBlock(final String name, final MaterialColor color, final int burnTime, final FeatureFlag... featureFlags) {
        RegistryObject<Block> block = registerBlockWithoutBlockItem(name, () -> new Block(PropertyHelper.basicBlockProperties(Material.STONE, color, 5.0F, 6.0F, true, featureFlags)));
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
        return registerBlock(name, () -> new FlowerBlock(effectSupplier, 5, PropertyHelper.copyFromBlock(Blocks.POPPY, featureFlags)) {

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
     * @param materialColor {@link MaterialColor The block color on maps}
     * @param blockSetType {@link BlockSetType The pressure plate block set type}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerPressurePlate(final String name, final boolean isWooden, final MaterialColor materialColor, final BlockSetType blockSetType, final FeatureFlag... featureFlags) {
        final BlockBehaviour.Properties properties = PropertyHelper.copyFromBlock(isWooden ? Blocks.OAK_PRESSURE_PLATE : Blocks.STONE_PRESSURE_PLATE, featureFlags).color(materialColor);
        return registerBlock(name, () -> new PressurePlateBlock(isWooden ? PressurePlateBlock.Sensitivity.EVERYTHING : PressurePlateBlock.Sensitivity.MOBS, properties, blockSetType));
    }

    /**
     * Register a {@link PressurePlateBlock pressure plate block}
     *
     * @param name {@link String The block name}
     * @param maxWeight {@link Integer The max weight the pressure plate can detect}
     * @param materialColor {@link MaterialColor The block color on maps}
     * @param blockSetType {@link BlockSetType The pressure plate block set type}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerWeightedPressurePlate(final String name, final int maxWeight, final MaterialColor materialColor, final BlockSetType blockSetType, final FeatureFlag... featureFlags) {
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
        });
    }

    /**
     * Register a {@link StairBlock stair block}
     *
     * @param name {@link String The block name}
     * @param blockSupplier {@link Supplier<Block> The supplier for block this stair is based on}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerSlab(final String name, final Supplier<? extends Block> blockSupplier, final FeatureFlag... featureFlags) {
        return registerSlab(name, () -> PropertyHelper.copyFromBlock(blockSupplier.get(), featureFlags).requiresCorrectToolForDrops(), () -> LevelHelper.getPushReaction(blockSupplier.get().defaultBlockState()));
    }

    /**
     * Register a {@link StairBlock stair block}
     *
     * @param name {@link String The block name}
     * @param propertiesSupplier {@link Supplier<BlockBehaviour.Properties> The supplier for block properties this stair is based on}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerSlab(final String name, final Supplier<BlockBehaviour.Properties> propertiesSupplier) {
        return registerSlab(name, propertiesSupplier, () -> PushReaction.NORMAL);
    }

    /**
     * Register a {@link StairBlock stair block}
     *
     * @param name {@link String The block name}
     * @param propertiesSupplier {@link Supplier<BlockBehaviour.Properties> The supplier for block properties this stair is based on}
     * @param pistonPushReaction {@link Supplier<PushReaction> The piston push reaction supplier for this block}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerSlab(final String name, final Supplier<BlockBehaviour.Properties> propertiesSupplier, final Supplier<PushReaction> pistonPushReaction) {
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
        return registerWall(name, () -> PropertyHelper.copyFromBlock(blockSupplier.get(), featureFlags).requiresCorrectToolForDrops(), () -> LevelHelper.getPushReaction(blockSupplier.get().defaultBlockState()));
    }

    /**
     * Register a {@link WallBlock wall block}
     *
     * @param name {@link String The block name}
     * @param propertiesSupplier {@link Supplier<Block> The supplier for the block properties this wall is based on}
     * @return {@link RegistryObject<Block> The registered block}
     */
    public static RegistryObject<Block> registerWall(final String name, final Supplier<BlockBehaviour.Properties> propertiesSupplier) {
        return registerWall(name, propertiesSupplier, () -> PushReaction.NORMAL);
    }

    /**
     * Register a {@link WallBlock wall block}
     *
     * @param name {@link String The block name}
     * @param propertiesSupplier {@link Supplier<BlockBehaviour.Properties> The supplier for block properties this stair is based on}
     * @param pistonPushReaction {@link Supplier<PushReaction> The piston push reaction supplier for this block}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerWall(final String name, final Supplier<BlockBehaviour.Properties> propertiesSupplier, final Supplier<PushReaction> pistonPushReaction) {
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
        return registerBlockWithoutBlockItem(name, () -> new ChestBlock(PropertyHelper.copyFromBlock(Blocks.CHEST, featureFlags), chestBlockEntitySupplier) {

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
        return registerBlockWithoutBlockItem(name, () -> new ChestBlock(PropertyHelper.copyFromBlock(Blocks.TRAPPED_CHEST, featureFlags), chestBlockEntitySupplier) {

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
        });
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
     * Register a {@link BlockItem block item}
     *
     * @param name  {@link String The block name}
     * @param block {@link RegistryObject<Block> The supplier for the block the item is referring to}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this block to be registered}
     */
    static void registerBlockItem(final String name, final RegistryObject<? extends Block> block, final FeatureFlag... featureFlags) {
        registerItem(name, () -> new BlockItem(block.get(), PropertyHelper.basicItemProperties(featureFlags)));
    }

    /**
     * Register a {@link Block block} without also registering a {@link BlockItem block item}
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
        return ENTITY_TYPES.register(name, () -> entityBuilder.build(new ResourceLocation(MineWorld.MOD_ID, name).toString()));
    }

    /**
     * Register a {@link BlockEntityType block entity type}
     *
     * @param name {@link String The block entity name}
     * @param blockEntitySupplier {@link BlockEntityType.BlockEntitySupplier<T> The supplier for the block entity}
     * @param blockSupplier {@link Supplier<Block> The supplier for the block related to the block entity}
     * @return {@link RegistryObject<BlockEntityType> The registered block entity}
     * @param <T> {@link T The block entity type}
     */
    public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerBlockEntity(final String name, final BlockEntityType.BlockEntitySupplier<T> blockEntitySupplier, final Supplier<Block> blockSupplier) {
        return BLOCK_ENTITY_TYPES.register(name, () -> BlockEntityType.Builder.of(blockEntitySupplier, blockSupplier.get()).build(null));
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
     * @param placementModifiers {@link List<PlacementModifier> The placement modifiers}
     */
    public static void registerPlacedFeature(final BootstapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> key, final Holder<ConfiguredFeature<?, ?>> configuredFeatureHolder, final List<PlacementModifier> placementModifiers) {
        PlacementUtils.register(context, key, configuredFeatureHolder, placementModifiers);
    }

    /**
     * Register a {@link TrimMaterial trim material} with darker variants
     *
     * @param context {@link BootstapContext<TrimMaterial> The bootstrap context}
     * @param resourceKey {@link ResourceKey<TrimMaterial> The trim material resource key}
     * @param material {@link Item The item used to apply the trim material}
     * @param color {@link MWColors The text color for the item tooltip}
     * @param itemModelIndex {@link Float The index value for the item model override}
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
                Component.translatable(Util.makeDescriptionId("trim_material", resourceKey.location())).withStyle(Style.EMPTY.withColor(color.toText())),
                variants);
        context.register(resourceKey, trimmaterial);
    }

    /**
     * Register a {@link CreativeModeTab creative tab}
     *
     * @param event {@link CreativeModeTabEvent.Register Creative mode tab register event}
     * @param name {@link String The tab name}
     * @param afterTab After which {@link CreativeModeTab creative tab} this tab should appear
     * @param iconSupplier {@link Supplier<ItemStack> The icon supplier}. Determines which {@link Item item} to use as tab icon
     * @return {@link CreativeModeTab The registered creative mode tab}
     */
    public static CreativeModeTab registerCreativeTab(final CreativeModeTabEvent.Register event, final String name, final CreativeModeTab afterTab, final Supplier<ItemStack> iconSupplier) {
        return event.registerCreativeModeTab(new ResourceLocation(MineWorld.MOD_ID, name),
                List.of(),
                afterTab != null ? List.of(afterTab) : List.of(),
                builder -> builder
                        .icon(iconSupplier)
                        .title(Component.translatable("itemGroup." + MineWorld.MOD_ID + "." + name))
                        .build());
    }

    /**
     * Register a {@link BlockSetType block set type}
     *
     * @param name {@link String The block set type name}
     * @param defaultSound {@link SoundType The block set type default sound}
     * @param doorCloseSound {@link SoundEvent The sound to play when a door or a trapdoor is closed}
     * @param doorOpenSound {@link SoundEvent The sound to play when a door or a trapdoor is opened}
     * @param pressurePlateClickOffSound {@link SoundEvent The sound to play when a pressure plate looses pressure}
     * @param pressurePlateClickOnSound {@link SoundEvent The sound to play when a pressure plate is pressed}
     * @param buttonClickOffSound {@link SoundEvent The sound to play when a button looses pressure}
     * @param buttonClickOnSound {@link SoundEvent The sound to play when a button is pressed}
     * @return {@link BlockSetType The registered block set type}
     */
    public static BlockSetType registerBlockSetType(final String name, final SoundType defaultSound, final SoundEvent doorCloseSound, final SoundEvent doorOpenSound, final SoundEvent pressurePlateClickOffSound, final SoundEvent pressurePlateClickOnSound, final SoundEvent buttonClickOffSound, final SoundEvent buttonClickOnSound) {
        return BlockSetType.register(new BlockSetType(new ResourceLocation(MineWorld.MOD_ID, name).toString(), defaultSound,
                doorCloseSound, doorOpenSound,
                doorCloseSound, doorOpenSound,
                pressurePlateClickOffSound, pressurePlateClickOnSound,
                buttonClickOffSound, buttonClickOnSound)
        );
    }

    /**
     * Register a {@link BlockSetType block set type}
     *
     * @param name {@link String The block set type name}
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
    public static BlockSetType registerBlockSetType(final String name, final SoundType defaultSound, final SoundEvent doorCloseSound, final SoundEvent doorOpenSound, final SoundEvent trapdoorCloseSound, final SoundEvent trapdoorOpenSound, final SoundEvent pressurePlateClickOffSound, final SoundEvent pressurePlateClickOnSound, final SoundEvent buttonClickOffSound, final SoundEvent buttonClickOnSound) {
        return BlockSetType.register(new BlockSetType(new ResourceLocation(MineWorld.MOD_ID, name).toString(), defaultSound,
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
        return BlockTags.create(new ResourceLocation(MineWorld.MOD_ID, name));
    }

    /**
     * Register a {@link TagKey<Item> item tag}
     *
     * @param name {@link String The tag name}
     */
    public static TagKey<Item> registerItemTag(final String name) {
        return ItemTags.create(new ResourceLocation(MineWorld.MOD_ID, name));
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
     * @param item {@link Supplier<ItemLike> The item to apply the dispense behavior}
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
        return STATISTICS.register(name, () -> new ResourceLocation(MineWorld.MOD_ID, name));
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
        return RECIPE_TYPES.register(name, () -> RecipeType.simple(new ResourceLocation(MineWorld.MOD_ID, name)));
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
     * Register {@link MineWorld MineWorld} {@link PoiType villager poi types}
     *
     * @param poiTypeSuppliers {@link PoiType The villager poi types to register}
     */
    @SafeVarargs
    public static void registerPOIs(final Supplier<? extends PoiType>... poiTypeSuppliers) {
        final Method registerMethod = ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockStates", PoiType.class);
        Arrays.stream(poiTypeSuppliers).forEach(poiType -> {
            try {
                registerMethod.invoke(null, poiType.get());
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
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
        return VILLGER_PROFESSIONS.register(name, () -> new VillagerProfession(
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
     * Register the {@link MineWorld MineWorld} compostables
     */
    public static void registerCompostables() {
        registerCompostable(MWBlocks.GRASS_CARPET.get(), 0.1F);
        registerCompostable(MWBlocks.OAK_LEAVES_CARPET.get(), 0.1F);
        registerCompostable(MWBlocks.SPRUCE_LEAVES_CARPET.get(), 0.1F);
        registerCompostable(MWBlocks.BIRCH_LEAVES_CARPET.get(), 0.1F);
        registerCompostable(MWBlocks.JUNGLE_LEAVES_CARPET.get(), 0.1F);
        registerCompostable(MWBlocks.ACACIA_LEAVES_CARPET.get(), 0.1F);
        registerCompostable(MWBlocks.DARK_OAK_LEAVES_CARPET.get(), 0.1F);
        registerCompostable(MWBlocks.MANGROVE_LEAVES_CARPET.get(), 0.1F);
        registerCompostable(MWBlocks.MANGROVE_ROOTS_CARPET.get(), 0.1F);
        registerCompostable(MWBlocks.CHERRY_LEAVES_CARPET.get(), 0.1F);
        registerCompostable(MWBlocks.AZALEA_LEAVES_CARPET.get(), 0.25F);
        registerCompostable(MWBlocks.FLOWERING_AZALEA_LEAVES_CARPET.get(), 0.3F);
        registerCompostable(MWBlocks.NETHER_WART_CARPET.get(), 0.3F);
        registerCompostable(MWBlocks.WARPED_WART_CARPET.get(), 0.3F);
        registerCompostable(MWBlocks.WARPED_WART.get(), 0.85F);
        registerCompostable(MWBlocks.OAK_BUSH.get(), 0.65F);
        registerCompostable(MWBlocks.SPRUCE_BUSH.get(), 0.65F);
        registerCompostable(MWBlocks.BIRCH_BUSH.get(), 0.65F);
        registerCompostable(MWBlocks.JUNGLE_BUSH.get(), 0.65F);
        registerCompostable(MWBlocks.ACACIA_BUSH.get(), 0.65F);
        registerCompostable(MWBlocks.DARK_OAK_BUSH.get(), 0.65F);
        registerCompostable(MWBlocks.MANGROVE_BUSH.get(), 0.65F);
        registerCompostable(MWBlocks.CHERRY_BUSH.get(), 0.85F);
        registerCompostable(MWBlocks.BLUE_ROSE.get(), 0.65F);
        registerCompostable(MWBlocks.BLUE_ROSE_BUSH.get(), 0.65F);
        registerCompostable(MWBlocks.WHITE_ROSE.get(), 0.65F);
        registerCompostable(MWBlocks.WHITE_ROSE_BUSH.get(), 0.65F);
        registerCompostable(MWItems.CORN_SEEDS.get(), 0.3F);
    }

    /**
     * Register a {@link ItemLike compostable item}
     *
     * @param item {@link ItemLike The item to register}
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
        VILLGER_PROFESSIONS.register(eventBus);
    }

}