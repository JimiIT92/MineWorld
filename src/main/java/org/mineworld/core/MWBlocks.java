package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.block.*;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.helper.RegistryHelper;
import org.mineworld.item.MWFuelBlockItem;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link Block Blocks}
 */
public final class MWBlocks {

    //#region Registry

    /**
     * The {@link DeferredRegister<Block> Block Registry}
     */
    private static final DeferredRegister<Block> BLOCKS = RegistryHelper.registry(ForgeRegistries.BLOCKS);

    //#endregion

    //#region Blocks

    //#region Ores, Raw Ore Blocks and Ore Blocks

    public static final RegistryObject<Block> SILVER_ORE = registerOverworldOre("silver_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = registerOverworldOre("deepslate_silver_ore", true);
    public static final RegistryObject<Block> ALUMINUM_ORE = registerOverworldOre("aluminum_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_ALUMINUM_ORE = registerOverworldOre("deepslate_aluminum_ore", true);
    public static final RegistryObject<Block> RUBY_ORE = registerOverworldOre("ruby_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_RUBY_ORE = registerOverworldOre("deepslate_ruby_ore", true);
    public static final RegistryObject<Block> SAPPHIRE_ORE = registerOverworldOre("sapphire_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = registerOverworldOre("deepslate_sapphire_ore", true);
    public static final RegistryObject<Block> PYRITE_ORE = registerNetherOre("pyrite_ore");
    public static final RegistryObject<Block> RAW_SILVER_BLOCK = registerOreStorageBlock("raw_silver_block", MWColors.RAW_SILVER.toMapColor(), false);
    public static final RegistryObject<Block> RAW_ALUMINUM_BLOCK = registerOreStorageBlock("raw_aluminum_block", MWColors.RAW_ALUMINUM.toMapColor(), false);
    public static final RegistryObject<Block> RAW_BRONZE_BLOCK = registerOreStorageBlock("raw_bronze_block", MWColors.RAW_BRONZE.toMapColor(), false);
    public static final RegistryObject<Block> SILVER_BLOCK = registerOreStorageBlock("silver_block", MWColors.SILVER.toMapColor(), true);
    public static final RegistryObject<Block> ALUMINUM_BLOCK = registerOreStorageBlock("aluminum_block", MWColors.ALUMINUM.toMapColor(), true);
    public static final RegistryObject<Block> BRONZE_BLOCK = registerBlock("bronze_block", () -> PropertyHelper.block(MWColors.BRONZE.toMapColor(),3.0F, 6.0F, true, SoundType.COPPER));
    public static final RegistryObject<Block> RUBY_BLOCK = registerOreStorageBlock("ruby_block", MWColors.RUBY.toMapColor(), true);
    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerOreStorageBlock("sapphire_block", MWColors.SAPPHIRE.toMapColor(), true);
    public static final RegistryObject<Block> PYRITE_BLOCK = registerFuel("pyrite_block", MWColors.PYRITE.toMapColor(), 1200);

    //#endregion

    //#region Stone Block Sets

    public static final RegistryObject<Block> MARBLE = registerBlock("marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MWColors.MARBLE.toMapColor())));
    public static final RegistryObject<Block> MARBLE_STAIRS = registerStair("marble_stairs", Suppliers.memoize(() -> MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> MARBLE_SLAB = registerSlab("marble_slab", Suppliers.memoize(() -> MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> MARBLE_WALL = registerWall("marble_wall", Suppliers.memoize(() -> MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> MARBLE_PRESSURE_PLATE = registerPressurePlate("marble_pressure_plate", false, MWColors.MARBLE.toMapColor(), Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MARBLE_BUTTON = registerButton("marble_button", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Flowers, Crops and Plants

    public static final RegistryObject<Block> BLUE_ROSE = registerFlower("blue_rose", Suppliers.memoize(() -> MobEffects.SATURATION));
    public static final RegistryObject<Block> BLUE_ROSE_BUSH = registerTallFlower("blue_rose_bush");
    public static final RegistryObject<Block> WHITE_ROSE = registerFlower("white_rose", () -> MobEffects.HEAL);
    public static final RegistryObject<Block> WHITE_ROSE_BUSH = registerTallFlower("white_rose_bush");

    //#endregion

    //#endregion

    //#region Methods

    /**
     * Register an {@link Block Overworld Ore Block}
     *
     * @param name {@link String The Block name}
     * @param isDeepslateOre {@link Boolean If the Ore is a Deepslate Ore}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerOverworldOre(final String name, final boolean isDeepslateOre, final FeatureFlag... featureFlags) {
        return registerOreBlock(name, PropertyHelper.ore(isDeepslateOre, featureFlags), 3, 7);
    }

    /**
     * Register a {@link Block Nether Ore Block}
     *
     * @param name {@link String The Block name}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerNetherOre(final String name, final FeatureFlag... featureFlags) {
        return registerOreBlock(name, PropertyHelper.ore(false, featureFlags).mapColor(MapColor.NETHER).sound(SoundType.NETHER_ORE), 2, 5);
    }

    /**
     * Register an {@link DropExperienceBlock Ore Block}
     *
     * @param name {@link String The Block name}
     * @param properties {@link BlockBehaviour.Properties The Block Properties}
     * @param minXp {@link Integer The minimum amount of XP dropped by the block}
     * @param maxXp {@link Integer The maximum amount of XP dropped by the block}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerOreBlock(final String name, final BlockBehaviour.Properties properties, final int minXp, final int maxXp) {
        return registerBlock(name, () -> new DropExperienceBlock(properties, UniformInt.of(minXp, maxXp)));
    }

    /**
     * Register an {@link Block Ore Storage Block}
     *
     * @param name {@link String The Block name}
     * @param mapColor {@link MapColor The Block Map color}
     * @param isMetallic {@link Boolean If the Ore Storage Block is metallic}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerOreStorageBlock(final String name, final MapColor mapColor, final boolean isMetallic, final FeatureFlag... featureFlags) {
        return registerBlock(name, Suppliers.memoize(() -> PropertyHelper.oreStorage(mapColor, isMetallic ? SoundType.METAL : SoundType.STONE, featureFlags)));
    }

    /**
     * Register a {@link Block Fuel Block}
     *
     * @param name {@link String The Block name}
     * @param mapColor {@link MapColor The Block Map color}
     * @param burnTime {@link Integer The fuel burn time}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerFuel(final String name, final MapColor mapColor, final int burnTime, final FeatureFlag...featureFlags) {
        final RegistryObject<Block> block = registerBlockWithoutBlockItem(name, () -> new Block(PropertyHelper.oreStorage(mapColor, SoundType.STONE, featureFlags)));
        MWItems.registerItem(name, () -> new MWFuelBlockItem(block, burnTime, featureFlags));
        return block;
    }

    /**
     * Register a {@link MWStairBlock Stair Block}
     *
     * @param name {@link String The Block name}
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State the Stair is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    public static RegistryObject<Block> registerStair(final String name, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new MWStairBlock(blockStateSupplier, featureFlags));
    }

    /**
     * Register a {@link MWSlabBlock Slab Block}
     *
     * @param name {@link String The Block name}
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State the Slab is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    public static RegistryObject<Block> registerSlab(final String name, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new MWSlabBlock(blockStateSupplier, featureFlags));
    }

    /**
     * Register a {@link MWWallBlock Wall Block}
     *
     * @param name {@link String The Block name}
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State the Wall is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    public static RegistryObject<Block> registerWall(final String name, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new MWWallBlock(blockStateSupplier, featureFlags));
    }

    /**
     * Register a {@link PressurePlateBlock Pressure Plate Block}
     *
     * @param name {@link String The Block name}
     * @param isWooden {@link Boolean If the Pressure Plate is a Wooden Pressure Plate}
     * @param mapColor {@link MapColor The Block Map color}
     * @param blockSetTypeSupplier {@link Supplier<BlockSetType> The Block Set Type Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    public static RegistryObject<Block> registerPressurePlate(final String name, final boolean isWooden, final MapColor mapColor, final Supplier<BlockSetType> blockSetTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new PressurePlateBlock(isWooden ? PressurePlateBlock.Sensitivity.EVERYTHING : PressurePlateBlock.Sensitivity.MOBS,
                PropertyHelper.copy(isWooden ? Blocks.OAK_PRESSURE_PLATE : Blocks.STONE_PRESSURE_PLATE, featureFlags).mapColor(mapColor),
                blockSetTypeSupplier.get())
        );
    }

    /**
     * Register a {@link ButtonBlock Button Block}
     *
     * @param name {@link String The Block name}
     * @param isWooden {@link Boolean If the Button is a Wooden Button}
     * @param blockSetTypeSupplier {@link Supplier<BlockSetType> The Block Set Type Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    public static RegistryObject<Block> registerButton(final String name, final boolean isWooden, final Supplier<BlockSetType> blockSetTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new ButtonBlock(
                PropertyHelper.copy(isWooden ? Blocks.OAK_PRESSURE_PLATE : Blocks.STONE_PRESSURE_PLATE, featureFlags),
                blockSetTypeSupplier.get(),
                isWooden ? 30 : 20,
                isWooden)
        );
    }

    /**
     * Register a {@link MWFlowerBlock Flower Block}
     *
     * @param name {@link String The Block name}
     * @param effectSupplier {@link Supplier<MobEffect> The Supplier for the flower effect when used in suspicious stews}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerFlower(final String name, final Supplier<MobEffect> effectSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new MWFlowerBlock(effectSupplier, featureFlags));
    }

    /**
     * Register a {@link MWTallFlowerBlock Tall Flower Block}
     *
     * @param name {@link String The Block name}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerTallFlower(final String name, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new MWTallFlowerBlock(featureFlags));
    }

    /**
     * Register a {@link Block Block} given its {@link BlockBehaviour.Properties Properties}
     *
     * @param name {@link String The Block name}
     * @param propertiesSupplier {@link Supplier<BlockBehaviour.Properties> The Block Properties Supplier}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    public static RegistryObject<Block> registerBlock(final String name, final Supplier<BlockBehaviour.Properties> propertiesSupplier) {
        return registerBlock(name, () -> new Block(propertiesSupplier.get()));
    }

    /**
     * Register a {@link Block Block} without registering its {@link BlockItem Block Item}
     *
     * @param name {@link String The Block name}
     * @param blockSupplier {@link Supplier The Block supplier}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    public static RegistryObject<Block> registerBlockWithoutBlockItem(final String name, final Supplier<? extends Block> blockSupplier) {
        return BLOCKS.register(name, blockSupplier);
    }

    /**
     * Register a {@link BlockItem Block Item}
     *
     * @param name {@link String The Block name}
     * @param blockSupplier {@link Supplier The Block supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    private static void registerBlockItem(final String name, final Supplier<? extends Block> blockSupplier, final FeatureFlag... featureFlags) {
        MWItems.registerItem(name, () -> new BlockItem(blockSupplier.get(), PropertyHelper.item(featureFlags)));
    }

    /**
     * Register a {@link Block Block}
     *
     * @param name {@link String The Block name}
     * @param blockSupplier {@link Supplier The Block supplier}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    public static RegistryObject<Block> registerBlock(final String name, final Supplier<? extends Block> blockSupplier, final FeatureFlag... featureFlags) {
        final RegistryObject<Block> block = registerBlockWithoutBlockItem(name, blockSupplier);
        registerBlockItem(name, block, featureFlags);
        return block;
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link Block Blocks}
     *
     * @param eventBus {@link IEventBus The mod event bus}
     */
    public static void register(final IEventBus eventBus) {
        MWColoredBlocks.register();
        MWPebbles.PebbleBlocks.register();
        MWFlowerPots.register();
        BLOCKS.register(eventBus);
    }

    //#endregion

}