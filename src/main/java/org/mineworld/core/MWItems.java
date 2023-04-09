package org.mineworld.core;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;

import java.util.List;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link Item items}
 */
public final class MWItems {

    /**
     * {@link DeferredRegister<Item> The item registry}
     */
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MineWorld.MODID);

    //#region Items

    public static final RegistryObject<Item> RUBY = registerSimpleItem("ruby");
    public static final RegistryObject<Item> SAPPHIRE = registerSimpleItem("sapphire");
    public static final RegistryObject<Item> PYRITE = registerFuel("pyrite", 120);
    public static final RegistryObject<Item> RAW_SILVER = registerSimpleItem("raw_silver");
    public static final RegistryObject<Item> SILVER_INGOT = registerSimpleItem("silver_ingot");
    public static final RegistryObject<Item> SILVER_NUGGET = registerSimpleItem("silver_nugget");
    public static final RegistryObject<Item> RAW_ALUMINUM = registerSimpleItem("raw_aluminum");
    public static final RegistryObject<Item> ALUMINUM_INGOT = registerSimpleItem("aluminum_ingot");
    public static final RegistryObject<Item> ALUMINUM_NUGGET = registerSimpleItem("aluminum_nugget");
    public static final RegistryObject<Item> RAW_BRONZE = registerSimpleItem("raw_bronze");
    public static final RegistryObject<Item> BRONZE_INGOT = registerSimpleItem("bronze_ingot");
    public static final RegistryObject<Item> BRONZE_NUGGET = registerSimpleItem("bronze_nugget");
    public static final RegistryObject<Item> COPPER_NUGGET = registerSimpleItem("copper_nugget");
    public static final RegistryObject<Item> RAW_BRONZE_SMITHING_TEMPLATE = registerItem("raw_bronze_smithing_template",
            () -> createBasicSmithingTemplate("raw_bronze", "raw_copper", "raw_aluminum"));

    //#endregion

    /**
     * Create a basic {@link SmithingTemplateItem smithing template}
     *
     * @param name {@link String The smithing template name}
     * @param baseSlotIcon {@link ResourceLocation Icon to show inside the base slot}
     * @param additionSlotIcon {@link ResourceLocation Icon to show inside the additions slot}
     * @return {@link SmithingTemplateItem The smithing template}
     */
    private static SmithingTemplateItem createBasicSmithingTemplate(final String name, final String baseSlotIcon, final String additionSlotIcon) {
        return createBasicSmithingTemplate(name,
                List.of(new ResourceLocation(MineWorld.MODID, "item/empty_slot_" + baseSlotIcon)),
                List.of(new ResourceLocation(MineWorld.MODID, "item/empty_slot_" + additionSlotIcon)));
    }

    /**
     * Create a basic {@link SmithingTemplateItem smithing template}
     *
     * @param name {@link String The smithing template name}
     * @param baseSlotIcons {@link List<ResourceLocation> List of icons to show inside the base slot}
     * @param additionSlotIcons {@link List<ResourceLocation> List of icons to show inside the additions slot}
     * @return {@link SmithingTemplateItem The smithing template}
     */
    private static SmithingTemplateItem createBasicSmithingTemplate(final String name, final List<ResourceLocation> baseSlotIcons, final List<ResourceLocation> additionSlotIcons) {
        return new SmithingTemplateItem(
                Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(MineWorld.MODID, "smithing_template." + name + ".applies_to"))).withStyle(ChatFormatting.BLUE),
                Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(MineWorld.MODID, "smithing_template." + name + ".ingredients"))).withStyle(ChatFormatting.BLUE),
                Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation(MineWorld.MODID, name))).withStyle(ChatFormatting.GRAY),
                Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(MineWorld.MODID, "smithing_template." + name + ".base_slot_description"))),
                Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(MineWorld.MODID, "smithing_template." + name + ".additions_slot_description"))),
                baseSlotIcons,
                additionSlotIcons
        );
    }

    /**
     * Get the basic {@link Item.Properties item properties}
     *
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link Item.Properties item properties}
     */
    public static Item.Properties basicProperties(final FeatureFlag... featureFlags) {
        Item.Properties properties = new Item.Properties();
        if(featureFlags != null && featureFlags.length > 0) {
            properties = properties.requiredFeatures(featureFlags);
        }
        return properties;
    }

    /**
     * Register a basic {@link Item item}
     *
     * @param name {@link String The item name}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    private static RegistryObject<Item> registerSimpleItem(final String name, final FeatureFlag... featureFlags) {
        return registerItem(name, () -> new Item(basicProperties(featureFlags)));
    }

    /**
     * Register a {@link Item fuel item}
     *
     * @param name {@link String The item name}
     * @param burnTime {@link Integer The fuel burn time} in seconds
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    private static RegistryObject<Item> registerFuel(final String name, final int burnTime, final FeatureFlag... featureFlags) {
        return registerItem(name, () -> new Item(basicProperties(featureFlags)) {
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
     * Register a {@link Block block fuel item}
     *
     * @param name          {@link String The item name}
     * @param blockSupplier {@link Block The block referred from this item}
     * @param burnTime      {@link Integer The fuel burn time} in seconds
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     */
    public static void registerFuelBlockItem(final String name, final Supplier<? extends Block> blockSupplier, final int burnTime, final FeatureFlag... featureFlags) {
        registerItem(name, () -> new BlockItem(blockSupplier.get(), basicProperties(featureFlags)) {
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
     * Register a {@link Item fuel block item}
     *
     * @param name {@link String The item name}
     * @param block {@link Block The block referred from this item}
     * @param burnTime {@link Integer The fuel burn time} in seconds
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link RegistryObject<Item> The registered item}
     */
    private static RegistryObject<Item> registerFuelBlockItem(final String name, final Block block, final int burnTime, final FeatureFlag... featureFlags) {
        return registerItem(name, () -> new BlockItem(block, basicProperties(featureFlags)) {
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
     * Get a default {@link ItemStack Item Stack} from a {@link RegistryObject<Block> block registry object}
     *
     * @param registryObject {@link RegistryObject<Block> The block registry object}
     * @return {@link ItemStack The default Item Stack}
     */
    public static ItemStack getDefaultStack(final RegistryObject<? extends ItemLike> registryObject) {
        return getDefaultStack(registryObject.get().asItem());
    }

    /**
     * Get a default {@link ItemStack Item Stack} from an {@link ItemLike Item Like} object
     * (namely blocks and items)
     *
     * @param object {@link ItemLike The object}
     * @return {@link ItemStack The default Item Stack}
     */
    public static ItemStack getDefaultStack(final ItemLike object) {
        return getDefaultStack(object.asItem());
    }

    /**
     * Get a default {@link ItemStack Item Stack} from an {@link Item item}
     *
     * @param item {@link Item The item}
     * @return {@link ItemStack The default Item Stack}
     */
    public static ItemStack getDefaultStack(final Item item) {
        return item.getDefaultInstance();
    }

    /**
     * Register the {@link MineWorld MineWorld} {@link Item items}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(final IEventBus eventBus) {
        MWFoods.register(eventBus);
        MWTools.register(eventBus);
        MWArmors.register(eventBus);
        ITEMS.register(eventBus);
    }
}
