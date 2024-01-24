package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.grower.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.block.*;
import org.mineworld.entity.MWPrimedTnt;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.helper.RegistryHelper;
import org.mineworld.helper.ResourceHelper;
import org.mineworld.item.MWFuelBlockItem;

import java.util.Locale;
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

    //#region Silver

    public static final RegistryObject<Block> SILVER_ORE = registerOverworldOre("silver_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = registerOverworldOre("deepslate_silver_ore", true);
    public static final RegistryObject<Block> RAW_SILVER_BLOCK = registerOreStorageBlock("raw_silver_block", MWColors.RAW_SILVER.toMapColor(), false);
    public static final RegistryObject<Block> SILVER_BLOCK = registerOreStorageBlock("silver_block", MWColors.SILVER.toMapColor(), true);

    //#endregion

    //#region Aluminum

    public static final RegistryObject<Block> ALUMINUM_ORE = registerOverworldOre("aluminum_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_ALUMINUM_ORE = registerOverworldOre("deepslate_aluminum_ore", true);
    public static final RegistryObject<Block> RAW_ALUMINUM_BLOCK = registerOreStorageBlock("raw_aluminum_block", MWColors.RAW_ALUMINUM.toMapColor(), false);
    public static final RegistryObject<Block> ALUMINUM_BLOCK = registerOreStorageBlock("aluminum_block", MWColors.ALUMINUM.toMapColor(), true);

    //#endregion

    //#region Ruby

    public static final RegistryObject<Block> RUBY_ORE = registerOverworldOre("ruby_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_RUBY_ORE = registerOverworldOre("deepslate_ruby_ore", true);
    public static final RegistryObject<Block> RUBY_BLOCK = registerOreStorageBlock("ruby_block", MWColors.RUBY.toMapColor(), true);

    //#endregion

    //#region Sapphire

    public static final RegistryObject<Block> SAPPHIRE_ORE = registerOverworldOre("sapphire_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = registerOverworldOre("deepslate_sapphire_ore", true);
    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerOreStorageBlock("sapphire_block", MWColors.SAPPHIRE.toMapColor(), true);

    //#endregion

    //#region Bronze

    public static final RegistryObject<Block> RAW_BRONZE_BLOCK = registerOreStorageBlock("raw_bronze_block", MWColors.RAW_BRONZE.toMapColor(), false);
    public static final RegistryObject<Block> BRONZE_BLOCK = registerBlock("bronze_block", () -> PropertyHelper.block(MWColors.BRONZE.toMapColor(),3.0F, 6.0F, true, SoundType.COPPER));

    //#endregion

    //#region Pyrite

    public static final RegistryObject<Block> PYRITE_ORE = registerNetherOre("pyrite_ore");
    public static final RegistryObject<Block> PYRITE_BLOCK = registerFuel("pyrite_block", MWColors.PYRITE.toMapColor(), 1200);

    //#endregion

    //#region Charcoal

    public static final RegistryObject<Block> CHARCOAL_BLOCK = registerFuel("charcoal_block", MWColors.CHARCOAL.toMapColor(), 800);

    //#endregion

    //#endregion

    //#region Wood Block Sets

    //#region Oak

    public static final RegistryObject<Block> HOLLOW_OAK_LOG = registerHollowLog(WoodType.OAK, false, Blocks.OAK_LOG::defaultBlockState);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_OAK_LOG = registerHollowLog(WoodType.OAK, true, Blocks.STRIPPED_OAK_LOG::defaultBlockState);
    public static final RegistryObject<Block> OAK_LEAVES_CARPET = registerLeaveCarpet(WoodType.OAK, () -> Blocks.OAK_LEAVES);
    public static final RegistryObject<Block> OAK_BUSH = registerBush(WoodType.OAK, OakTreeGrower::new);
    public static final RegistryObject<Block> OAK_BARREL = registerBarrel(WoodType.OAK);

    //#endregion

    //#region Spruce

    public static final RegistryObject<Block> HOLLOW_SPRUCE_LOG = registerHollowLog(WoodType.SPRUCE, false, Blocks.SPRUCE_LOG::defaultBlockState);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_SPRUCE_LOG = registerHollowLog(WoodType.SPRUCE, true, Blocks.STRIPPED_SPRUCE_LOG::defaultBlockState);
    public static final RegistryObject<Block> SPRUCE_LEAVES_CARPET = registerLeaveCarpet(WoodType.SPRUCE, () -> Blocks.SPRUCE_LEAVES);
    public static final RegistryObject<Block> SPRUCE_BUSH = registerBush(WoodType.SPRUCE, SpruceTreeGrower::new);
    public static final RegistryObject<Block> SPRUCE_CHEST = registerChest(() -> WoodType.SPRUCE, Suppliers.memoize(() -> MWBlockEntityTypes.SPRUCE_CHEST.get()));
    public static final RegistryObject<Block> SPRUCE_TRAPPED_CHEST = registerTrappedChest(() -> WoodType.SPRUCE, Suppliers.memoize(() -> MWBlockEntityTypes.SPRUCE_TRAPPED_CHEST.get()));
    public static final RegistryObject<Block> SPRUCE_BOOKSHELF = registerBookshelf(WoodType.SPRUCE);
    public static final RegistryObject<Block> SPRUCE_CHISELED_BOOKSHELF = registerChiseledBookshelf(WoodType.SPRUCE);
    public static final RegistryObject<Block> SPRUCE_LECTERN = registerLectern(WoodType.SPRUCE);

    //#endregion

    //#region Birch

    public static final RegistryObject<Block> HOLLOW_BIRCH_LOG = registerHollowLog(WoodType.BIRCH, false, Blocks.BIRCH_LOG::defaultBlockState);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_BIRCH_LOG = registerHollowLog(WoodType.BIRCH, true, Blocks.STRIPPED_BIRCH_LOG::defaultBlockState);
    public static final RegistryObject<Block> BIRCH_LEAVES_CARPET = registerLeaveCarpet(WoodType.BIRCH, () -> Blocks.BIRCH_LEAVES);
    public static final RegistryObject<Block> BIRCH_BUSH = registerBush(WoodType.BIRCH, BirchTreeGrower::new);
    public static final RegistryObject<Block> BIRCH_BARREL = registerBarrel(WoodType.BIRCH);
    public static final RegistryObject<Block> BIRCH_CHEST = registerChest(() -> WoodType.BIRCH, Suppliers.memoize(() -> MWBlockEntityTypes.BIRCH_CHEST.get()));
    public static final RegistryObject<Block> BIRCH_TRAPPED_CHEST = registerTrappedChest(() -> WoodType.BIRCH, Suppliers.memoize(() -> MWBlockEntityTypes.BIRCH_TRAPPED_CHEST.get()));
    public static final RegistryObject<Block> BIRCH_BOOKSHELF = registerBookshelf(WoodType.BIRCH);
    public static final RegistryObject<Block> BIRCH_CHISELED_BOOKSHELF = registerChiseledBookshelf(WoodType.BIRCH);
    public static final RegistryObject<Block> BIRCH_LECTERN = registerLectern(WoodType.BIRCH);

    //#endregion

    //#region Jungle

    public static final RegistryObject<Block> HOLLOW_JUNGLE_LOG = registerHollowLog(WoodType.JUNGLE, false, Blocks.JUNGLE_LOG::defaultBlockState);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_JUNGLE_LOG = registerHollowLog(WoodType.JUNGLE, true, Blocks.STRIPPED_JUNGLE_LOG::defaultBlockState);
    public static final RegistryObject<Block> JUNGLE_LEAVES_CARPET = registerLeaveCarpet(WoodType.JUNGLE, () -> Blocks.JUNGLE_LEAVES);
    public static final RegistryObject<Block> JUNGLE_BUSH = registerBush(WoodType.JUNGLE, JungleTreeGrower::new);
    public static final RegistryObject<Block> JUNGLE_BARREL = registerBarrel(WoodType.JUNGLE);
    public static final RegistryObject<Block> JUNGLE_CHEST = registerChest(() -> WoodType.JUNGLE, Suppliers.memoize(() -> MWBlockEntityTypes.JUNGLE_CHEST.get()));
    public static final RegistryObject<Block> JUNGLE_TRAPPED_CHEST = registerTrappedChest(() -> WoodType.JUNGLE, Suppliers.memoize(() -> MWBlockEntityTypes.JUNGLE_TRAPPED_CHEST.get()));
    public static final RegistryObject<Block> JUNGLE_BOOKSHELF = registerBookshelf(WoodType.JUNGLE);
    public static final RegistryObject<Block> JUNGLE_CHISELED_BOOKSHELF = registerChiseledBookshelf(WoodType.JUNGLE);
    public static final RegistryObject<Block> JUNGLE_LECTERN = registerLectern(WoodType.JUNGLE);

    //#endregion

    //#region Acacia

    public static final RegistryObject<Block> HOLLOW_ACACIA_LOG = registerHollowLog(WoodType.ACACIA, false, Blocks.ACACIA_LOG::defaultBlockState);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_ACACIA_LOG = registerHollowLog(WoodType.ACACIA, true, Blocks.STRIPPED_ACACIA_LOG::defaultBlockState);
    public static final RegistryObject<Block> ACACIA_LEAVES_CARPET = registerLeaveCarpet(WoodType.ACACIA, () -> Blocks.ACACIA_LEAVES);
    public static final RegistryObject<Block> ACACIA_BUSH = registerBush(WoodType.ACACIA, AcaciaTreeGrower::new);
    public static final RegistryObject<Block> ACACIA_BARREL = registerBarrel(WoodType.ACACIA);
    public static final RegistryObject<Block> ACACIA_CHEST = registerChest(() -> WoodType.ACACIA, Suppliers.memoize(() -> MWBlockEntityTypes.ACACIA_CHEST.get()));
    public static final RegistryObject<Block> ACACIA_TRAPPED_CHEST = registerTrappedChest(() -> WoodType.ACACIA, Suppliers.memoize(() -> MWBlockEntityTypes.ACACIA_TRAPPED_CHEST.get()));
    public static final RegistryObject<Block> ACACIA_BOOKSHELF = registerBookshelf(WoodType.ACACIA);
    public static final RegistryObject<Block> ACACIA_CHISELED_BOOKSHELF = registerChiseledBookshelf(WoodType.ACACIA);
    public static final RegistryObject<Block> ACACIA_LECTERN = registerLectern(WoodType.ACACIA);

    //#endregion

    //#region Dark Oak

    public static final RegistryObject<Block> HOLLOW_DARK_OAK_LOG = registerHollowLog(WoodType.DARK_OAK, false, Blocks.DARK_OAK_LOG::defaultBlockState);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_DARK_OAK_LOG = registerHollowLog(WoodType.DARK_OAK, true, Blocks.STRIPPED_DARK_OAK_LOG::defaultBlockState);
    public static final RegistryObject<Block> DARK_OAK_LEAVES_CARPET = registerLeaveCarpet(WoodType.DARK_OAK, () -> Blocks.DARK_OAK_LEAVES);
    public static final RegistryObject<Block> DARK_OAK_BUSH = registerBush(WoodType.DARK_OAK, DarkOakTreeGrower::new);
    public static final RegistryObject<Block> DARK_OAK_BARREL = registerBarrel(WoodType.DARK_OAK);
    public static final RegistryObject<Block> DARK_OAK_CHEST = registerChest(() -> WoodType.DARK_OAK, Suppliers.memoize(() -> MWBlockEntityTypes.DARK_OAK_CHEST.get()));
    public static final RegistryObject<Block> DARK_OAK_TRAPPED_CHEST = registerTrappedChest(() -> WoodType.DARK_OAK, Suppliers.memoize(() -> MWBlockEntityTypes.DARK_OAK_TRAPPED_CHEST.get()));
    public static final RegistryObject<Block> DARK_OAK_BOOKSHELF = registerBookshelf(WoodType.DARK_OAK);
    public static final RegistryObject<Block> DARK_OAK_CHISELED_BOOKSHELF = registerChiseledBookshelf(WoodType.DARK_OAK);
    public static final RegistryObject<Block> DARK_OAK_LECTERN = registerLectern(WoodType.DARK_OAK);

    //#endregion

    //#region Mangrove

    public static final RegistryObject<Block> HOLLOW_MANGROVE_LOG = registerHollowLog(WoodType.MANGROVE, false, Blocks.MANGROVE_LOG::defaultBlockState);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_MANGROVE_LOG = registerHollowLog(WoodType.MANGROVE, true, Blocks.STRIPPED_MANGROVE_LOG::defaultBlockState);
    public static final RegistryObject<Block> MANGROVE_LEAVES_CARPET = registerLeaveCarpet(WoodType.MANGROVE, () -> Blocks.MANGROVE_LEAVES);
    public static final RegistryObject<Block> MANGROVE_ROOTS_CARPET = registerLeaveCarpet(WoodType.MANGROVE, "roots", () -> Blocks.MANGROVE_LEAVES);
    public static final RegistryObject<Block> MUDDY_MANGROVE_ROOTS_CARPET = registerCarpet("muddy_mangrove_roots", () -> Blocks.MANGROVE_LEAVES);
    public static final RegistryObject<Block> MANGROVE_BUSH = registerBush(WoodType.MANGROVE, () -> new MangroveTreeGrower(0.85F));
    public static final RegistryObject<Block> MANGROVE_BARREL = registerBarrel(WoodType.MANGROVE);
    public static final RegistryObject<Block> MANGROVE_CHEST = registerChest(() -> WoodType.MANGROVE, Suppliers.memoize(() -> MWBlockEntityTypes.MANGROVE_CHEST.get()));
    public static final RegistryObject<Block> MANGROVE_TRAPPED_CHEST = registerTrappedChest(() -> WoodType.MANGROVE, Suppliers.memoize(() -> MWBlockEntityTypes.MANGROVE_TRAPPED_CHEST.get()));
    public static final RegistryObject<Block> MANGROVE_BOOKSHELF = registerBookshelf(WoodType.MANGROVE);
    public static final RegistryObject<Block> MANGROVE_CHISELED_BOOKSHELF = registerChiseledBookshelf(WoodType.MANGROVE);
    public static final RegistryObject<Block> MANGROVE_LECTERN = registerLectern(WoodType.MANGROVE);

    //#endregion

    //#region Cherry

    public static final RegistryObject<Block> HOLLOW_CHERRY_LOG = registerHollowLog(WoodType.CHERRY, false, Blocks.CHERRY_LOG::defaultBlockState);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_CHERRY_LOG = registerHollowLog(WoodType.CHERRY, true, Blocks.STRIPPED_CHERRY_LOG::defaultBlockState);
    public static final RegistryObject<Block> CHERRY_LEAVES_CARPET = registerLeaveCarpet(WoodType.CHERRY, () -> Blocks.CHERRY_LEAVES);
    public static final RegistryObject<Block> CHERRY_BUSH = registerBush(WoodType.CHERRY, CherryTreeGrower::new);
    public static final RegistryObject<Block> CHERRY_BARREL = registerBarrel(WoodType.CHERRY);
    public static final RegistryObject<Block> CHERRY_CHEST = registerChest(() -> WoodType.CHERRY, Suppliers.memoize(() -> MWBlockEntityTypes.CHERRY_CHEST.get()));
    public static final RegistryObject<Block> CHERRY_TRAPPED_CHEST = registerTrappedChest(() -> WoodType.CHERRY, Suppliers.memoize(() -> MWBlockEntityTypes.CHERRY_TRAPPED_CHEST.get()));
    public static final RegistryObject<Block> CHERRY_BOOKSHELF = registerBookshelf(WoodType.CHERRY);
    public static final RegistryObject<Block> CHERRY_CHISELED_BOOKSHELF = registerChiseledBookshelf(WoodType.CHERRY);
    public static final RegistryObject<Block> CHERRY_LECTERN = registerLectern(WoodType.CHERRY);

    //#endregion

    //#region Bamboo

    public static final RegistryObject<Block> HOLLOW_BAMBOO_BLOCK = registerHollowLog(WoodType.BAMBOO, false, "block", Blocks.BAMBOO_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_BAMBOO_BLOCK = registerHollowLog(WoodType.BAMBOO, true, "block", Blocks.STRIPPED_BAMBOO_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> BAMBOO_BARREL = registerBarrel(WoodType.BAMBOO);
    public static final RegistryObject<Block> BAMBOO_CHEST = registerChest(() -> WoodType.BAMBOO, Suppliers.memoize(() -> MWBlockEntityTypes.BAMBOO_CHEST.get()));
    public static final RegistryObject<Block> BAMBOO_TRAPPED_CHEST = registerTrappedChest(() -> WoodType.BAMBOO, Suppliers.memoize(() -> MWBlockEntityTypes.BAMBOO_TRAPPED_CHEST.get()));
    public static final RegistryObject<Block> BAMBOO_BOOKSHELF = registerBookshelf(WoodType.BAMBOO);
    public static final RegistryObject<Block> BAMBOO_CHISELED_BOOKSHELF = registerChiseledBookshelf(WoodType.BAMBOO);
    public static final RegistryObject<Block> BAMBOO_LECTERN = registerLectern(WoodType.BAMBOO);

    //#endregion

    //#region Crimson

    public static final RegistryObject<Block> HOLLOW_CRIMSON_STEM = registerHollowLog(WoodType.CRIMSON, false, "stem", Blocks.CRIMSON_STEM::defaultBlockState);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_CRIMSON_STEM = registerHollowLog(WoodType.CRIMSON, true, "stem", Blocks.STRIPPED_CRIMSON_STEM::defaultBlockState);
    public static final RegistryObject<Block> NETHER_WART_CARPET = registerCarpet("nether_wart", () -> Blocks.NETHER_WART_BLOCK);
    public static final RegistryObject<Block> CRIMSON_BARREL = registerBarrel(WoodType.CRIMSON);
    public static final RegistryObject<Block> CRIMSON_CHEST = registerChest(() -> WoodType.CRIMSON, Suppliers.memoize(() -> MWBlockEntityTypes.CRIMSON_CHEST.get()));
    public static final RegistryObject<Block> CRIMSON_TRAPPED_CHEST = registerTrappedChest(() -> WoodType.CRIMSON, Suppliers.memoize(() -> MWBlockEntityTypes.CRIMSON_TRAPPED_CHEST.get()));
    public static final RegistryObject<Block> CRIMSON_BOOKSHELF = registerBookshelf(WoodType.CRIMSON);
    public static final RegistryObject<Block> CRIMSON_CHISELED_BOOKSHELF = registerChiseledBookshelf(WoodType.CRIMSON);
    public static final RegistryObject<Block> CRIMSON_LECTERN = registerLectern(WoodType.CRIMSON);

    //#endregion

    //#region Warped

    public static final RegistryObject<Block> HOLLOW_WARPED_STEM = registerHollowLog(WoodType.WARPED, false, "stem", Blocks.WARPED_STEM::defaultBlockState);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_WARPED_STEM = registerHollowLog(WoodType.WARPED, true, "stem", Blocks.STRIPPED_WARPED_STEM::defaultBlockState);
    public static final RegistryObject<Block> WARPED_WART_CARPET = registerCarpet("warped_wart", () -> Blocks.WARPED_WART_BLOCK);
    public static final RegistryObject<Block> WARPED_BARREL = registerBarrel(WoodType.WARPED);
    public static final RegistryObject<Block> WARPED_CHEST = registerChest(() -> WoodType.WARPED, Suppliers.memoize(() -> MWBlockEntityTypes.WARPED_CHEST.get()));
    public static final RegistryObject<Block> WARPED_TRAPPED_CHEST = registerTrappedChest(() -> WoodType.WARPED, Suppliers.memoize(() -> MWBlockEntityTypes.WARPED_TRAPPED_CHEST.get()));
    public static final RegistryObject<Block> WARPED_BOOKSHELF = registerBookshelf(WoodType.WARPED);
    public static final RegistryObject<Block> WARPED_CHISELED_BOOKSHELF = registerChiseledBookshelf(WoodType.WARPED);
    public static final RegistryObject<Block> WARPED_LECTERN = registerLectern(WoodType.WARPED);

    //#endregion

    //#region Azalea

    public static final RegistryObject<Block> AZALEA_LEAVES_CARPET = registerLeaveCarpet("azalea", () -> Blocks.AZALEA_LEAVES);
    public static final RegistryObject<Block> FLOWERING_AZALEA_LEAVES_CARPET = registerLeaveCarpet("flowering_azalea", () -> Blocks.FLOWERING_AZALEA_LEAVES);

    //#endregion

    //#region Apple

    public static final RegistryObject<Block> APPLE_LOG = registerLog(MWWoodTypes.MWWoodTypeNames.APPLE, false, MWWoodTypes.APPLE, true);
    public static final RegistryObject<Block> HOLLOW_APPLE_LOG = registerHollowLog(MWWoodTypes.MWWoodTypeNames.APPLE, false,  Suppliers.memoize(() -> APPLE_LOG.get().defaultBlockState()));
    public static final RegistryObject<Block> STRIPPED_APPLE_LOG = registerLog(MWWoodTypes.MWWoodTypeNames.APPLE, true, MWWoodTypes.APPLE, true);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_APPLE_LOG = registerHollowLog(MWWoodTypes.MWWoodTypeNames.APPLE, true,  Suppliers.memoize(() -> STRIPPED_APPLE_LOG.get().defaultBlockState()));
    public static final RegistryObject<Block> APPLE_WOOD = registerLog(MWWoodTypes.MWWoodTypeNames.APPLE, false, MWWoodTypes.APPLE, false);
    public static final RegistryObject<Block> STRIPPED_APPLE_WOOD = registerLog(MWWoodTypes.MWWoodTypeNames.APPLE, true, MWWoodTypes.APPLE, false);
    public static final RegistryObject<Block> APPLE_PLANKS = registerPlanks(MWWoodTypes.MWWoodTypeNames.APPLE, MWWoodTypes.APPLE);
    public static final RegistryObject<Block> APPLE_LEAVES = registerLeaves(MWWoodTypes.MWWoodTypeNames.APPLE, MWWoodTypes.APPLE);
    public static final RegistryObject<Block> APPLE_LEAVES_CARPET = registerLeaveCarpet(MWWoodTypes.MWWoodTypeNames.APPLE, Suppliers.memoize(() -> APPLE_LEAVES.get()));
    public static final RegistryObject<Block> APPLE_SAPLING = registerSapling(MWWoodTypes.MWWoodTypeNames.APPLE, MWTreeGrowers.APPLE_TREE_GROWER);
    public static final RegistryObject<Block> APPLE_BUSH = registerBush(MWWoodTypes.MWWoodTypeNames.APPLE, MWTreeGrowers.APPLE_TREE_GROWER);
    public static final RegistryObject<Block> APPLE_STAIRS = registerStair(MWWoodTypes.MWWoodTypeNames.APPLE, Suppliers.memoize(() -> APPLE_PLANKS.get().defaultBlockState()));
    public static final RegistryObject<Block> APPLE_SLAB = registerSlab(MWWoodTypes.MWWoodTypeNames.APPLE, Suppliers.memoize(() -> APPLE_PLANKS.get().defaultBlockState()));
    public static final RegistryObject<Block> APPLE_DOOR = registerDoor(MWWoodTypes.MWWoodTypeNames.APPLE, false, () -> BlockSetType.OAK);
    public static final RegistryObject<Block> APPLE_TRAPDOOR = registerTrapdoor(MWWoodTypes.MWWoodTypeNames.APPLE, false, () -> BlockSetType.OAK);
    public static final RegistryObject<Block> APPLE_PRESSURE_PLATE = registerPressurePlate(MWWoodTypes.MWWoodTypeNames.APPLE, true, ResourceHelper.woodColor(MWWoodTypes.APPLE.get()), () -> BlockSetType.OAK);
    public static final RegistryObject<Block> APPLE_BUTTON = registerButton(MWWoodTypes.MWWoodTypeNames.APPLE, true, () -> BlockSetType.OAK);
    public static final RegistryObject<Block> APPLE_BARREL = registerBarrel(MWWoodTypes.MWWoodTypeNames.APPLE);
    public static final RegistryObject<Block> APPLE_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.APPLE, MWWoodTypes.APPLE, Suppliers.memoize(() -> MWBlockEntityTypes.APPLE_CHEST.get()));
    public static final RegistryObject<Block> APPLE_TRAPPED_CHEST = registerTrappedChest(MWWoodTypes.MWWoodTypeNames.APPLE, MWWoodTypes.APPLE, Suppliers.memoize(() -> MWBlockEntityTypes.APPLE_TRAPPED_CHEST.get()));
    public static final RegistryObject<Block> APPLE_BOOKSHELF = registerBookshelf(MWWoodTypes.MWWoodTypeNames.APPLE, MWWoodTypes.APPLE);
    public static final RegistryObject<Block> APPLE_CHISELED_BOOKSHELF = registerChiseledBookshelf(MWWoodTypes.MWWoodTypeNames.APPLE, MWWoodTypes.APPLE);
    public static final RegistryObject<Block> APPLE_LECTERN = registerLectern(MWWoodTypes.MWWoodTypeNames.APPLE, MWWoodTypes.APPLE);
    public static final RegistryObject<Block> APPLE_FENCE = registerFence(MWWoodTypes.MWWoodTypeNames.APPLE, MWWoodTypes.APPLE);
    public static final RegistryObject<Block> APPLE_FENCE_GATE = registerFenceGate(MWWoodTypes.MWWoodTypeNames.APPLE, MWWoodTypes.APPLE);
    public static final RegistryObject<Block> APPLE_SIGN = registerSign(MWWoodTypes.MWWoodTypeNames.APPLE, MWWoodTypes.APPLE);
    public static final RegistryObject<Block> APPLE_WALL_SIGN = registerWallSign(MWWoodTypes.MWWoodTypeNames.APPLE, MWWoodTypes.APPLE, Suppliers.memoize(() -> APPLE_SIGN.get()));
    public static final RegistryObject<Block> APPLE_HANGING_SIGN = registerHangingSign(MWWoodTypes.MWWoodTypeNames.APPLE, MWWoodTypes.APPLE);
    public static final RegistryObject<Block> APPLE_WALL_HANGING_SIGN = registerWallHangingSign(MWWoodTypes.MWWoodTypeNames.APPLE, MWWoodTypes.APPLE, Suppliers.memoize(() -> APPLE_HANGING_SIGN.get()));

    //#endregion

    //#region Palm

    public static final RegistryObject<Block> PALM_LOG = registerLog(MWWoodTypes.MWWoodTypeNames.PALM, false, MWWoodTypes.PALM, true);
    public static final RegistryObject<Block> HOLLOW_PALM_LOG = registerHollowLog(MWWoodTypes.MWWoodTypeNames.PALM, false,  Suppliers.memoize(() -> PALM_LOG.get().defaultBlockState()));
    public static final RegistryObject<Block> STRIPPED_PALM_LOG = registerLog(MWWoodTypes.MWWoodTypeNames.PALM, true, MWWoodTypes.PALM, true);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_PALM_LOG = registerHollowLog(MWWoodTypes.MWWoodTypeNames.PALM, true,  Suppliers.memoize(() -> STRIPPED_PALM_LOG.get().defaultBlockState()));
    public static final RegistryObject<Block> PALM_WOOD = registerLog(MWWoodTypes.MWWoodTypeNames.PALM, false, MWWoodTypes.PALM, false);
    public static final RegistryObject<Block> STRIPPED_PALM_WOOD = registerLog(MWWoodTypes.MWWoodTypeNames.PALM, true, MWWoodTypes.PALM, false);
    public static final RegistryObject<Block> PALM_PLANKS = registerPlanks(MWWoodTypes.MWWoodTypeNames.PALM, MWWoodTypes.PALM);
    public static final RegistryObject<Block> PALM_LEAVES = registerLeaves(MWWoodTypes.MWWoodTypeNames.PALM, MWWoodTypes.PALM);
    public static final RegistryObject<Block> PALM_LEAVES_CARPET = registerLeaveCarpet(MWWoodTypes.MWWoodTypeNames.PALM, Suppliers.memoize(() -> PALM_LEAVES.get()));
    public static final RegistryObject<Block> PALM_SAPLING = registerBlock(MWWoodTypes.MWWoodTypeNames.PALM + "_sapling", Suppliers.memoize(PalmSaplingBlock::new));
    public static final RegistryObject<Block> PALM_BUSH = registerBlock(MWWoodTypes.MWWoodTypeNames.PALM + "_bush", Suppliers.memoize(PalmBushBlock::new));
    public static final RegistryObject<Block> PALM_STAIRS = registerStair(MWWoodTypes.MWWoodTypeNames.PALM, Suppliers.memoize(() -> PALM_PLANKS.get().defaultBlockState()));
    public static final RegistryObject<Block> PALM_SLAB = registerSlab(MWWoodTypes.MWWoodTypeNames.PALM, Suppliers.memoize(() -> PALM_PLANKS.get().defaultBlockState()));
    public static final RegistryObject<Block> PALM_DOOR = registerDoor(MWWoodTypes.MWWoodTypeNames.PALM, false, () -> BlockSetType.JUNGLE);
    public static final RegistryObject<Block> PALM_TRAPDOOR = registerTrapdoor(MWWoodTypes.MWWoodTypeNames.PALM, false, () -> BlockSetType.JUNGLE);
    public static final RegistryObject<Block> PALM_PRESSURE_PLATE = registerPressurePlate(MWWoodTypes.MWWoodTypeNames.PALM, true, ResourceHelper.woodColor(MWWoodTypes.PALM.get()), () -> BlockSetType.JUNGLE);
    public static final RegistryObject<Block> PALM_BUTTON = registerButton(MWWoodTypes.MWWoodTypeNames.PALM, true, () -> BlockSetType.JUNGLE);
    public static final RegistryObject<Block> PALM_BARREL = registerBarrel(MWWoodTypes.MWWoodTypeNames.PALM);
    public static final RegistryObject<Block> PALM_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.PALM, MWWoodTypes.PALM, Suppliers.memoize(() -> MWBlockEntityTypes.PALM_CHEST.get()));
    public static final RegistryObject<Block> PALM_TRAPPED_CHEST = registerTrappedChest(MWWoodTypes.MWWoodTypeNames.PALM, MWWoodTypes.PALM, Suppliers.memoize(() -> MWBlockEntityTypes.PALM_TRAPPED_CHEST.get()));
    public static final RegistryObject<Block> PALM_BOOKSHELF = registerBookshelf(MWWoodTypes.MWWoodTypeNames.PALM, MWWoodTypes.PALM);
    public static final RegistryObject<Block> PALM_CHISELED_BOOKSHELF = registerChiseledBookshelf(MWWoodTypes.MWWoodTypeNames.PALM, MWWoodTypes.PALM);
    public static final RegistryObject<Block> PALM_LECTERN = registerLectern(MWWoodTypes.MWWoodTypeNames.PALM, MWWoodTypes.PALM);
    public static final RegistryObject<Block> PALM_FENCE = registerFence(MWWoodTypes.MWWoodTypeNames.PALM, MWWoodTypes.PALM);
    public static final RegistryObject<Block> PALM_FENCE_GATE = registerFenceGate(MWWoodTypes.MWWoodTypeNames.PALM, MWWoodTypes.PALM);
    public static final RegistryObject<Block> PALM_SIGN = registerSign(MWWoodTypes.MWWoodTypeNames.PALM, MWWoodTypes.PALM);
    public static final RegistryObject<Block> PALM_WALL_SIGN = registerWallSign(MWWoodTypes.MWWoodTypeNames.PALM, MWWoodTypes.PALM, Suppliers.memoize(() -> PALM_SIGN.get()));
    public static final RegistryObject<Block> PALM_HANGING_SIGN = registerHangingSign(MWWoodTypes.MWWoodTypeNames.PALM, MWWoodTypes.PALM);
    public static final RegistryObject<Block> PALM_WALL_HANGING_SIGN = registerWallHangingSign(MWWoodTypes.MWWoodTypeNames.PALM, MWWoodTypes.PALM, Suppliers.memoize(() -> PALM_HANGING_SIGN.get()));

    //#endregion

    //#region Dead

    public static final RegistryObject<Block> DEAD_LOG = registerLog(MWWoodTypes.MWWoodTypeNames.DEAD, false,  MWWoodTypes.DEAD, true);
    public static final RegistryObject<Block> HOLLOW_DEAD_LOG = registerHollowLog(MWWoodTypes.MWWoodTypeNames.DEAD, false,  Suppliers.memoize(() -> DEAD_LOG.get().defaultBlockState()));
    public static final RegistryObject<Block> STRIPPED_DEAD_LOG = registerLog(MWWoodTypes.MWWoodTypeNames.DEAD, true, MWWoodTypes.DEAD, true);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_DEAD_LOG = registerHollowLog(MWWoodTypes.MWWoodTypeNames.DEAD, true,  Suppliers.memoize(() -> STRIPPED_DEAD_LOG.get().defaultBlockState()));
    public static final RegistryObject<Block> DEAD_WOOD = registerLog(MWWoodTypes.MWWoodTypeNames.DEAD, false, MWWoodTypes.DEAD, false);
    public static final RegistryObject<Block> STRIPPED_DEAD_WOOD = registerLog(MWWoodTypes.MWWoodTypeNames.DEAD, true, MWWoodTypes.DEAD, false);
    public static final RegistryObject<Block> DEAD_PLANKS = registerPlanks(MWWoodTypes.MWWoodTypeNames.DEAD, MWWoodTypes.DEAD);
    public static final RegistryObject<Block> DEAD_STAIRS = registerStair(MWWoodTypes.MWWoodTypeNames.DEAD, Suppliers.memoize(() -> DEAD_PLANKS.get().defaultBlockState()));
    public static final RegistryObject<Block> DEAD_SLAB = registerSlab(MWWoodTypes.MWWoodTypeNames.DEAD, Suppliers.memoize(() -> DEAD_PLANKS.get().defaultBlockState()));
    public static final RegistryObject<Block> DEAD_DOOR = registerDoor(MWWoodTypes.MWWoodTypeNames.DEAD, false, () -> BlockSetType.OAK);
    public static final RegistryObject<Block> DEAD_TRAPDOOR = registerTrapdoor(MWWoodTypes.MWWoodTypeNames.DEAD, false, () -> BlockSetType.OAK);
    public static final RegistryObject<Block> DEAD_PRESSURE_PLATE = registerPressurePlate(MWWoodTypes.MWWoodTypeNames.DEAD, true, ResourceHelper.woodColor(MWWoodTypes.DEAD.get()), () -> BlockSetType.OAK);
    public static final RegistryObject<Block> DEAD_BUTTON = registerButton(MWWoodTypes.MWWoodTypeNames.DEAD, true, () -> BlockSetType.OAK);
    public static final RegistryObject<Block> DEAD_BARREL = registerBarrel(MWWoodTypes.MWWoodTypeNames.DEAD);
    public static final RegistryObject<Block> DEAD_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.DEAD, MWWoodTypes.DEAD, Suppliers.memoize(() -> MWBlockEntityTypes.DEAD_CHEST.get()));
    public static final RegistryObject<Block> DEAD_TRAPPED_CHEST = registerTrappedChest(MWWoodTypes.MWWoodTypeNames.DEAD, MWWoodTypes.DEAD, Suppliers.memoize(() -> MWBlockEntityTypes.DEAD_TRAPPED_CHEST.get()));
    public static final RegistryObject<Block> DEAD_BOOKSHELF = registerBookshelf(MWWoodTypes.MWWoodTypeNames.DEAD, MWWoodTypes.DEAD);
    public static final RegistryObject<Block> DEAD_CHISELED_BOOKSHELF = registerChiseledBookshelf(MWWoodTypes.MWWoodTypeNames.DEAD, MWWoodTypes.DEAD);
    public static final RegistryObject<Block> DEAD_LECTERN = registerLectern(MWWoodTypes.MWWoodTypeNames.DEAD, MWWoodTypes.DEAD);
    public static final RegistryObject<Block> DEAD_FENCE = registerFence(MWWoodTypes.MWWoodTypeNames.DEAD, MWWoodTypes.DEAD);
    public static final RegistryObject<Block> DEAD_FENCE_GATE = registerFenceGate(MWWoodTypes.MWWoodTypeNames.DEAD, MWWoodTypes.DEAD);
    public static final RegistryObject<Block> DEAD_SIGN = registerSign(MWWoodTypes.MWWoodTypeNames.DEAD, MWWoodTypes.DEAD);
    public static final RegistryObject<Block> DEAD_WALL_SIGN = registerWallSign(MWWoodTypes.MWWoodTypeNames.DEAD, MWWoodTypes.DEAD, Suppliers.memoize(() -> DEAD_SIGN.get()));
    public static final RegistryObject<Block> DEAD_HANGING_SIGN = registerHangingSign(MWWoodTypes.MWWoodTypeNames.DEAD, MWWoodTypes.DEAD);
    public static final RegistryObject<Block> DEAD_WALL_HANGING_SIGN = registerWallHangingSign(MWWoodTypes.MWWoodTypeNames.DEAD, MWWoodTypes.DEAD, Suppliers.memoize(() -> DEAD_HANGING_SIGN.get()));

    //#endregion

    //#region Sculk

    public static final RegistryObject<Block> SCULK_LOG = registerLog(MWWoodTypes.MWWoodTypeNames.SCULK, false, MWWoodTypes.SCULK, true);
    public static final RegistryObject<Block> HOLLOW_SCULK_LOG = registerHollowLog(MWWoodTypes.MWWoodTypeNames.SCULK, false,  Suppliers.memoize(() -> SCULK_LOG.get().defaultBlockState()));
    public static final RegistryObject<Block> STRIPPED_SCULK_LOG = registerLog(MWWoodTypes.MWWoodTypeNames.SCULK, true, MWWoodTypes.SCULK, true);
    public static final RegistryObject<Block> HOLLOW_STRIPPED_SCULK_LOG = registerHollowLog(MWWoodTypes.MWWoodTypeNames.SCULK, true,  Suppliers.memoize(() -> STRIPPED_SCULK_LOG.get().defaultBlockState()));
    public static final RegistryObject<Block> SCULK_WOOD = registerLog(MWWoodTypes.MWWoodTypeNames.SCULK, false, MWWoodTypes.SCULK, false);
    public static final RegistryObject<Block> STRIPPED_SCULK_WOOD = registerLog(MWWoodTypes.MWWoodTypeNames.SCULK, true, MWWoodTypes.SCULK, false);
    public static final RegistryObject<Block> SCULK_PLANKS = registerPlanks(MWWoodTypes.MWWoodTypeNames.SCULK, MWWoodTypes.SCULK);
    public static final RegistryObject<Block> SCULK_LEAVES = registerLeaves(MWWoodTypes.MWWoodTypeNames.SCULK, MWWoodTypes.SCULK);
    public static final RegistryObject<Block> SCULK_LEAVES_CARPET = registerLeaveCarpet(MWWoodTypes.MWWoodTypeNames.SCULK, Suppliers.memoize(() -> SCULK_LEAVES.get()));
    public static final RegistryObject<Block> SCULK_SAPLING = registerBlock(MWWoodTypes.MWWoodTypeNames.SCULK + "_sapling", Suppliers.memoize(SculkSaplingBlock::new));
    public static final RegistryObject<Block> SCULK_BUSH = registerBlock(MWWoodTypes.MWWoodTypeNames.SCULK + "_bush", Suppliers.memoize(SculkBushBlock::new));
    public static final RegistryObject<Block> SCULK_STAIRS = registerStair(MWWoodTypes.MWWoodTypeNames.SCULK, Suppliers.memoize(() -> SCULK_PLANKS.get().defaultBlockState()));
    public static final RegistryObject<Block> SCULK_SLAB = registerSlab(MWWoodTypes.MWWoodTypeNames.SCULK, Suppliers.memoize(() -> SCULK_PLANKS.get().defaultBlockState()));
    public static final RegistryObject<Block> SCULK_DOOR = registerDoor(MWWoodTypes.MWWoodTypeNames.SCULK, false, () -> BlockSetType.OAK);
    public static final RegistryObject<Block> SCULK_TRAPDOOR = registerTrapdoor(MWWoodTypes.MWWoodTypeNames.SCULK, false, () -> BlockSetType.OAK);
    public static final RegistryObject<Block> SCULK_PRESSURE_PLATE = registerPressurePlate(MWWoodTypes.MWWoodTypeNames.SCULK, true, MWColors.SCULK.toMapColor(), () -> BlockSetType.OAK);
    public static final RegistryObject<Block> SCULK_BUTTON = registerButton(MWWoodTypes.MWWoodTypeNames.SCULK, true, () -> BlockSetType.OAK);
    public static final RegistryObject<Block> SCULK_BARREL = registerBarrel(MWWoodTypes.MWWoodTypeNames.SCULK);
    public static final RegistryObject<Block> SCULK_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.SCULK, MWWoodTypes.SCULK, Suppliers.memoize(() -> MWBlockEntityTypes.SCULK_CHEST.get()));
    public static final RegistryObject<Block> SCULK_TRAPPED_CHEST = registerTrappedChest(MWWoodTypes.MWWoodTypeNames.SCULK, MWWoodTypes.SCULK, Suppliers.memoize(() -> MWBlockEntityTypes.SCULK_TRAPPED_CHEST.get()));
    public static final RegistryObject<Block> SCULK_BOOKSHELF = registerBookshelf(MWWoodTypes.MWWoodTypeNames.SCULK, MWWoodTypes.SCULK);
    public static final RegistryObject<Block> SCULK_CHISELED_BOOKSHELF = registerChiseledBookshelf(MWWoodTypes.MWWoodTypeNames.SCULK, MWWoodTypes.SCULK);
    public static final RegistryObject<Block> SCULK_LECTERN = registerLectern(MWWoodTypes.MWWoodTypeNames.SCULK, MWWoodTypes.SCULK);
    public static final RegistryObject<Block> SCULK_FENCE = registerFence(MWWoodTypes.MWWoodTypeNames.SCULK, MWWoodTypes.SCULK);
    public static final RegistryObject<Block> SCULK_FENCE_GATE = registerFenceGate(MWWoodTypes.MWWoodTypeNames.SCULK, MWWoodTypes.SCULK);
    public static final RegistryObject<Block> SCULK_SIGN = registerSign(MWWoodTypes.MWWoodTypeNames.SCULK, MWWoodTypes.SCULK);
    public static final RegistryObject<Block> SCULK_WALL_SIGN = registerWallSign(MWWoodTypes.MWWoodTypeNames.SCULK, MWWoodTypes.SCULK, Suppliers.memoize(() -> SCULK_SIGN.get()));
    public static final RegistryObject<Block> SCULK_HANGING_SIGN = registerHangingSign(MWWoodTypes.MWWoodTypeNames.SCULK, MWWoodTypes.SCULK);
    public static final RegistryObject<Block> SCULK_WALL_HANGING_SIGN = registerWallHangingSign(MWWoodTypes.MWWoodTypeNames.SCULK, MWWoodTypes.SCULK, Suppliers.memoize(() -> SCULK_HANGING_SIGN.get()));

    //#endregion

    //#endregion

    //#region Stone Block Sets

    //#region Marble

    public static final RegistryObject<Block> MARBLE = registerBlock("marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MWColors.MARBLE.toMapColor())));
    public static final RegistryObject<Block> MARBLE_STAIRS = registerStair("marble", Suppliers.memoize(() -> MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> MARBLE_SLAB = registerSlab("marble", Suppliers.memoize(() -> MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> MARBLE_WALL = registerWall("marble", Suppliers.memoize(() -> MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> MARBLE_PRESSURE_PLATE = registerPressurePlate("marble", false, MWColors.MARBLE.toMapColor(), Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MARBLE_BUTTON = registerButton("marble", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Stone, Cobblestone, Mossy Stone, Mossy Cobblestone, Smooth Stone, Stone Bricks and Mossy Stone Bricks

    public static final RegistryObject<Block> STONE_WALL = registerWall("stone", Blocks.STONE::defaultBlockState);
    public static final RegistryObject<Block> COBBLESTONE_PRESSURE_PLATE = registerPressurePlate("cobblestone", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> COBBLESTONE_BUTTON = registerButton("cobblestone", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MOSSY_STONE = registerBlock("mossy_stone", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.STONE)));
    public static final RegistryObject<Block> MOSSY_STONE_STAIRS = registerStair("mossy_stone", Suppliers.memoize(() -> MOSSY_STONE.get().defaultBlockState()));
    public static final RegistryObject<Block> MOSSY_STONE_SLAB = registerSlab("mossy_stone", Suppliers.memoize(() -> MOSSY_STONE.get().defaultBlockState()));
    public static final RegistryObject<Block> MOSSY_STONE_WALL = registerWall("mossy_stone", Suppliers.memoize(() -> MOSSY_STONE.get().defaultBlockState()));
    public static final RegistryObject<Block> MOSSY_STONE_PRESSURE_PLATE = registerPressurePlate("mossy_stone", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MOSSY_STONE_BUTTON = registerButton("mossy_stone", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MOSSY_COBBLESTONE_PRESSURE_PLATE = registerPressurePlate("mossy_cobblestone", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MOSSY_COBBLESTONE_BUTTON = registerButton("mossy_cobblestone", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_STONE_STAIRS = registerStair("smooth_stone", Blocks.SMOOTH_STONE::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_STONE_WALL = registerWall("smooth_stone", Blocks.SMOOTH_STONE::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_STONE_PRESSURE_PLATE = registerPressurePlate("smooth_stone", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_STONE_BUTTON = registerButton("smooth_stone", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> STONE_BRICKS_PRESSURE_PLATE = registerPressurePlate("stone_bricks", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> STONE_BRICKS_BUTTON = registerButton("stone_bricks", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MOSSY_STONE_BRICKS_PRESSURE_PLATE = registerPressurePlate("mossy_stone_bricks", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MOSSY_STONE_BRICKS_BUTTON = registerButton("mossy_stone_bricks", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MOSSY_CHISELED_STONE_BRICKS = registerBlock("mossy_chiseled_stone_bricks", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.CHISELED_STONE_BRICKS)));
    public static final RegistryObject<Block> MOSSY_CRACKED_STONE_BRICKS = registerBlock("mossy_cracked_stone_bricks", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.CRACKED_STONE_BRICKS)));

    //#endregion

    //#region Granite and Polished Granite

    public static final RegistryObject<Block> GRANITE_PRESSURE_PLATE = registerPressurePlate("granite", false, MapColor.DIRT, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GRANITE_BUTTON = registerButton("granite", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_GRANITE_WALL = registerWall("polished_granite", Blocks.POLISHED_GRANITE::defaultBlockState);
    public static final RegistryObject<Block> POLISHED_GRANITE_PRESSURE_PLATE = registerPressurePlate("polished_granite", false, MapColor.DIRT, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_GRANITE_BUTTON = registerButton("polished_granite", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Diorite and Polished Diorite

    public static final RegistryObject<Block> DIORITE_PRESSURE_PLATE = registerPressurePlate("diorite", false, MapColor.QUARTZ, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DIORITE_BUTTON = registerButton("diorite", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_DIORITE_WALL = registerWall("polished_diorite", Blocks.POLISHED_DIORITE::defaultBlockState);
    public static final RegistryObject<Block> POLISHED_DIORITE_PRESSURE_PLATE = registerPressurePlate("polished_diorite", false, MapColor.QUARTZ, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_DIORITE_BUTTON = registerButton("polished_diorite", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Andesite and Polished Andesite

    public static final RegistryObject<Block> ANDESITE_PRESSURE_PLATE = registerPressurePlate("andesite", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> ANDESITE_BUTTON = registerButton("andesite", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_ANDESITE_WALL = registerWall("polished_andesite", Blocks.POLISHED_ANDESITE::defaultBlockState);
    public static final RegistryObject<Block> POLISHED_ANDESITE_PRESSURE_PLATE = registerPressurePlate("polished_andesite", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_ANDESITE_BUTTON = registerButton("polished_andesite", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Deepslate, Cobbled Deepslate, Polished Deepslate, Deepslate Bricks, Deepslate Tiles and Reinforced Deepslate

    public static final RegistryObject<Block> DEEPSLATE_STAIRS = registerStair("deepslate", Blocks.DEEPSLATE::defaultBlockState);
    public static final RegistryObject<Block> DEEPSLATE_SLAB = registerSlab("deepslate", Blocks.DEEPSLATE::defaultBlockState);
    public static final RegistryObject<Block> DEEPSLATE_WALL = registerWall("deepslate", Blocks.DEEPSLATE::defaultBlockState);
    public static final RegistryObject<Block> DEEPSLATE_PRESSURE_PLATE = registerPressurePlate("deepslate", false, MapColor.DEEPSLATE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DEEPSLATE_BUTTON = registerButton("deepslate", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> COBBLED_DEEPSLATE_PRESSURE_PLATE = registerPressurePlate("cobbled_deepslate", false, MapColor.DEEPSLATE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> COBBLED_DEEPSLATE_BUTTON = registerButton("cobbled_deepslate", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_DEEPSLATE_PRESSURE_PLATE = registerPressurePlate("polished_deepslate", false, MapColor.DEEPSLATE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_DEEPSLATE_BUTTON = registerButton("polished_deepslate", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DEEPSLATE_BRICKS_PRESSURE_PLATE = registerPressurePlate("deepslate_bricks", false, MapColor.DEEPSLATE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DEEPSLATE_BRICKS_BUTTON = registerButton("deepslate_bricks", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DEEPSLATE_TILES_PRESSURE_PLATE = registerPressurePlate("deepslate_tiles", false, MapColor.DEEPSLATE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DEEPSLATE_TILES_BUTTON = registerButton("deepslate_tiles", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> REINFORCED_DEEPSLATE_STAIRS = registerStair("reinforced_deepslate", Blocks.REINFORCED_DEEPSLATE::defaultBlockState, PushReaction.BLOCK);
    public static final RegistryObject<Block> REINFORCED_DEEPSLATE_SLAB = registerSlab("reinforced_deepslate", Blocks.REINFORCED_DEEPSLATE::defaultBlockState, PushReaction.BLOCK);
    public static final RegistryObject<Block> REINFORCED_DEEPSLATE_WALL = registerWall("reinforced_deepslate", Blocks.REINFORCED_DEEPSLATE::defaultBlockState, PushReaction.BLOCK);
    public static final RegistryObject<Block> REINFORCED_DEEPSLATE_PRESSURE_PLATE = registerPressurePlate("reinforced_deepslate", false, MapColor.DEEPSLATE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> REINFORCED_DEEPSLATE_BUTTON = registerButton("reinforced_deepslate", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Bricks and Mud Bricks

    public static final RegistryObject<Block> BRICKS_PRESSURE_PLATE = registerPressurePlate("bricks", false, MapColor.COLOR_RED, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BRICKS_BUTTON = registerButton("bricks", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MUD_BRICKS_PRESSURE_PLATE = registerPressurePlate("mud_bricks", false, MapColor.TERRACOTTA_LIGHT_GRAY, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MUD_BRICKS_BUTTON = registerButton("mud_bricks", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Sandstone, Smooth Sandstone, Cut Sandstone, Red Sandstone, Smooth Red Sandstone and Cut Red Sandstone

    public static final RegistryObject<Block> SANDSTONE_PRESSURE_PLATE = registerPressurePlate("sandstone", false, MapColor.SAND, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SANDSTONE_BUTTON = registerButton("sandstone", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_SANDSTONE_WALL = registerWall("smooth_sandstone", Blocks.SANDSTONE::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_SANDSTONE_PRESSURE_PLATE = registerPressurePlate("smooth_sandstone", false, MapColor.SAND, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_SANDSTONE_BUTTON = registerButton("smooth_sandstone", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CUT_SANDSTONE_STAIRS = registerStair("cut_sandstone", Blocks.CUT_SANDSTONE::defaultBlockState);
    public static final RegistryObject<Block> CUT_SANDSTONE_WALL = registerWall("cut_sandstone", Blocks.CUT_SANDSTONE::defaultBlockState);
    public static final RegistryObject<Block> RED_SANDSTONE_PRESSURE_PLATE = registerPressurePlate("red_sandstone", false, MapColor.COLOR_ORANGE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> RED_SANDSTONE_BUTTON = registerButton("red_sandstone", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_RED_SANDSTONE_WALL = registerWall("smooth_red_sandstone", Blocks.RED_SANDSTONE::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_RED_SANDSTONE_PRESSURE_PLATE = registerPressurePlate("smooth_red_sandstone", false, MapColor.COLOR_ORANGE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_RED_SANDSTONE_BUTTON = registerButton("smooth_red_sandstone", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CUT_RED_SANDSTONE_STAIRS = registerStair("cut_red_sandstone", Blocks.CUT_RED_SANDSTONE::defaultBlockState);
    public static final RegistryObject<Block> CUT_RED_SANDSTONE_WALL = registerWall("cut_red_sandstone", Blocks.CUT_RED_SANDSTONE::defaultBlockState);

    //#endregion

    //#region Prismarine, Prismarine Bricks and Dark Prismarine

    public static final RegistryObject<Block> PRISMARINE_PRESSURE_PLATE = registerPressurePlate("prismarine", false, MapColor.COLOR_CYAN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PRISMARINE_BUTTON = registerButton("prismarine", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PRISMARINE_BRICKS_WALL = registerWall("prismarine_bricks", Blocks.PRISMARINE_BRICKS::defaultBlockState);
    public static final RegistryObject<Block> PRISMARINE_BRICKS_PRESSURE_PLATE = registerPressurePlate("prismarine_bricks", false, MapColor.DIAMOND, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PRISMARINE_BRICKS_BUTTON = registerButton("prismarine_bricks", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DARK_PRISMARINE_WALL = registerWall("dark_prismarine", Blocks.DARK_PRISMARINE::defaultBlockState);
    public static final RegistryObject<Block> DARK_PRISMARINE_PRESSURE_PLATE = registerPressurePlate("dark_prismarine", false, MapColor.DIAMOND, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DARK_PRISMARINE_BUTTON = registerButton("dark_prismarine", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Netherrack, Nether Bricks, Red Nether Bricks and Warped Nether Bricks

    public static final RegistryObject<Block> NETHERRACK_STAIRS = registerStair("netherrack", Blocks.NETHERRACK::defaultBlockState);
    public static final RegistryObject<Block> NETHERRACK_SLAB = registerSlab("netherrack", Blocks.NETHERRACK::defaultBlockState);
    public static final RegistryObject<Block> NETHERRACK_WALL = registerWall("netherrack", Blocks.NETHERRACK::defaultBlockState);
    public static final RegistryObject<Block> NETHERRACK_PRESSURE_PLATE = registerPressurePlate("netherrack", false, MapColor.NETHER, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> NETHERRACK_BUTTON = registerButton("netherrack", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> NETHER_BRICKS_PRESSURE_PLATE = registerPressurePlate("nether_bricks", false, MapColor.NETHER, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> NETHER_BRICKS_BUTTON = registerButton("nether_bricks", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> RED_NETHER_BRICKS_PRESSURE_PLATE = registerPressurePlate("red_nether_bricks", false, MapColor.NETHER, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> RED_NETHER_BRICKS_BUTTON = registerButton("red_nether_bricks", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CRACKED_RED_NETHER_BRICKS = registerBlock("cracked_red_nether_bricks", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.RED_NETHER_BRICKS)));
    public static final RegistryObject<Block> CHISELED_RED_NETHER_BRICKS = registerBlock("chiseled_red_nether_bricks", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.RED_NETHER_BRICKS)));
    public static final RegistryObject<Block> WARPED_NETHER_BRICKS = registerBlock("warped_nether_bricks", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.NETHER_BRICKS)));
    public static final RegistryObject<Block> CRACKED_WARPED_NETHER_BRICKS = registerBlock("cracked_warped_nether_bricks",  Suppliers.memoize(() -> PropertyHelper.copy(WARPED_NETHER_BRICKS.get())));
    public static final RegistryObject<Block> CHISELED_WARPED_NETHER_BRICKS = registerBlock("chiseled_warped_nether_bricks",  Suppliers.memoize(() -> PropertyHelper.copy(WARPED_NETHER_BRICKS.get())));
    public static final RegistryObject<Block> WARPED_NETHER_BRICKS_STAIRS = registerStair("warped_nether_bricks",  Suppliers.memoize(() -> WARPED_NETHER_BRICKS.get().defaultBlockState()));
    public static final RegistryObject<Block> WARPED_NETHER_BRICKS_SLAB = registerSlab("warped_nether_bricks", Suppliers.memoize(() -> WARPED_NETHER_BRICKS.get().defaultBlockState()));
    public static final RegistryObject<Block> WARPED_NETHER_BRICKS_WALL = registerWall("warped_nether_bricks", Suppliers.memoize(() -> WARPED_NETHER_BRICKS.get().defaultBlockState()));
    public static final RegistryObject<Block> WARPED_NETHER_BRICKS_PRESSURE_PLATE = registerPressurePlate("warped_nether_bricks", false, MapColor.WARPED_WART_BLOCK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> WARPED_NETHER_BRICKS_BUTTON = registerButton("warped_nether_bricks", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Basalt, Smooth Basalt and Polished Basalt

    public static final RegistryObject<Block> BASALT_STAIRS = registerStair("basalt", Blocks.BASALT::defaultBlockState);
    public static final RegistryObject<Block> BASALT_SLAB = registerSlab("basalt", Blocks.BASALT::defaultBlockState);
    public static final RegistryObject<Block> BASALT_WALL = registerWall("basalt", Blocks.BASALT::defaultBlockState);
    public static final RegistryObject<Block> BASALT_PRESSURE_PLATE = registerPressurePlate("basalt", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BASALT_BUTTON = registerButton("basalt", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_BASALT_STAIRS = registerStair("smooth_basalt", Blocks.SMOOTH_BASALT::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_BASALT_SLAB = registerSlab("smooth_basalt", Blocks.SMOOTH_BASALT::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_BASALT_WALL = registerWall("smooth_basalt", Blocks.SMOOTH_BASALT::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_BASALT_PRESSURE_PLATE = registerPressurePlate("smooth_basalt", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_BASALT_BUTTON = registerButton("smooth_basalt", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_BASALT_STAIRS = registerStair("polished_basalt", Blocks.POLISHED_BASALT::defaultBlockState);
    public static final RegistryObject<Block> POLISHED_BASALT_SLAB = registerSlab("polished_basalt", Blocks.POLISHED_BASALT::defaultBlockState);
    public static final RegistryObject<Block> POLISHED_BASALT_WALL = registerWall("polished_basalt", Blocks.POLISHED_BASALT::defaultBlockState);
    public static final RegistryObject<Block> POLISHED_BASALT_PRESSURE_PLATE = registerPressurePlate("polished_basalt", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_BASALT_BUTTON = registerButton("polished_basalt", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Blackstone, Polished Blackstone and Gilded Blackstone

    public static final RegistryObject<Block> BLACKSTONE_PRESSURE_PLATE = registerPressurePlate("blackstone", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLACKSTONE_BUTTON = registerButton("blackstone", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_BLACKSTONE_BRICKS_PRESSURE_PLATE = registerPressurePlate("polished_blackstone_bricks", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_BLACKSTONE_BRICKS_BUTTON = registerButton("polished_blackstone_bricks", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GILDED_BLACKSTONE_STAIRS = registerStair("gilded_blackstone", Blocks.GILDED_BLACKSTONE::defaultBlockState);
    public static final RegistryObject<Block> GILDED_BLACKSTONE_SLAB = registerSlab("gilded_blackstone", Blocks.GILDED_BLACKSTONE::defaultBlockState);
    public static final RegistryObject<Block> GILDED_BLACKSTONE_WALL = registerWall("gilded_blackstone", Blocks.GILDED_BLACKSTONE::defaultBlockState);
    public static final RegistryObject<Block> GILDED_BLACKSTONE_PRESSURE_PLATE = registerPressurePlate("gilded_blackstone", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GILDED_BLACKSTONE_BUTTON = registerButton("gilded_blackstone", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region End Stone and End Stone Bricks

    public static final RegistryObject<Block> END_STONE_STAIRS = registerStair("end_stone", Blocks.END_STONE::defaultBlockState);
    public static final RegistryObject<Block> END_STONE_SLAB = registerSlab("end_stone", Blocks.END_STONE::defaultBlockState);
    public static final RegistryObject<Block> END_STONE_WALL = registerWall("end_stone", Blocks.END_STONE::defaultBlockState);
    public static final RegistryObject<Block> END_STONE_PRESSURE_PLATE = registerPressurePlate("end_stone", false, MapColor.SAND, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> END_STONE_BUTTON = registerButton("end_stone", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> END_STONE_BRICKS_PRESSURE_PLATE = registerPressurePlate("end_stone_bricks", false, MapColor.SAND, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> END_STONE_BRICKS_BUTTON = registerButton("end_stone_bricks", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Purpur and Purpur Pillar

    public static final RegistryObject<Block> PURPUR_WALL = registerWall("purpur", Blocks.PURPUR_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> PURPUR_PRESSURE_PLATE = registerPressurePlate("purpur", false, MapColor.COLOR_MAGENTA, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PURPUR_BUTTON = registerButton("purpur", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PURPUR_PILLAR_STAIRS = registerStair("purpur_pillar", Blocks.PURPUR_PILLAR::defaultBlockState);
    public static final RegistryObject<Block> PURPUR_PILLAR_SLAB = registerSlab("purpur_pillar", Blocks.PURPUR_PILLAR::defaultBlockState);
    public static final RegistryObject<Block> PURPUR_PILLAR_WALL = registerWall("purpur_pillar", Blocks.PURPUR_PILLAR::defaultBlockState);
    public static final RegistryObject<Block> PURPUR_PILLAR_PRESSURE_PLATE = registerPressurePlate("purpur_pillar", false, MapColor.COLOR_MAGENTA, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PURPUR_PILLAR_BUTTON = registerButton("purpur_pillar", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Quartz, Smooth Quartz, Quartz Bricks and Quartz Pillar

    public static final RegistryObject<Block> QUARTZ_WALL = registerWall("quartz", Blocks.QUARTZ_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> QUARTZ_PRESSURE_PLATE = registerPressurePlate("quartz", false, MapColor.QUARTZ, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> QUARTZ_BUTTON = registerButton("quartz", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_QUARTZ_WALL = registerWall("smooth_quartz", Blocks.SMOOTH_QUARTZ::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_QUARTZ_PRESSURE_PLATE = registerPressurePlate("smooth_quartz", false, MapColor.QUARTZ, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_QUARTZ_BUTTON = registerButton("smooth_quartz", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> QUARTZ_BRICKS_STAIRS = registerStair("quartz_bricks", Blocks.QUARTZ_BRICKS::defaultBlockState);
    public static final RegistryObject<Block> QUARTZ_BRICKS_SLAB = registerSlab("quartz_bricks", Blocks.QUARTZ_BRICKS::defaultBlockState);
    public static final RegistryObject<Block> QUARTZ_BRICKS_WALL = registerWall("quartz_bricks", Blocks.QUARTZ_BRICKS::defaultBlockState);
    public static final RegistryObject<Block> QUARTZ_BRICKS_PRESSURE_PLATE = registerPressurePlate("quartz_bricks", false, MapColor.QUARTZ, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> QUARTZ_BRICKS_BUTTON = registerButton("quartz_bricks", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> QUARTZ_PILLAR_STAIRS = registerStair("quartz_pillar", Blocks.QUARTZ_PILLAR::defaultBlockState);
    public static final RegistryObject<Block> QUARTZ_PILLAR_SLAB = registerSlab("quartz_pillar", Blocks.QUARTZ_PILLAR::defaultBlockState);
    public static final RegistryObject<Block> QUARTZ_PILLAR_WALL = registerWall("quartz_pillar", Blocks.QUARTZ_PILLAR::defaultBlockState);
    public static final RegistryObject<Block> QUARTZ_PILLAR_PRESSURE_PLATE = registerPressurePlate("quartz_pillar", false, MapColor.QUARTZ, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> QUARTZ_PILLAR_BUTTON = registerButton("quartz_pillar", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Terracotta

    public static final RegistryObject<Block> TERRACOTTA_STAIRS = registerStair("terracotta", Blocks.TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> TERRACOTTA_SLAB = registerSlab("terracotta", Blocks.TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> TERRACOTTA_WALL = registerWall("terracotta", Blocks.TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> TERRACOTTA_PRESSURE_PLATE = registerPressurePlate("terracotta", false, MapColor.COLOR_ORANGE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> TERRACOTTA_BUTTON = registerButton("terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Calcite

    public static final RegistryObject<Block> CALCITE_STAIRS = registerStair("calcite", Blocks.CALCITE::defaultBlockState);
    public static final RegistryObject<Block> CALCITE_SLAB = registerSlab("calcite", Blocks.CALCITE::defaultBlockState);
    public static final RegistryObject<Block> CALCITE_WALL = registerWall("calcite", Blocks.CALCITE::defaultBlockState);
    public static final RegistryObject<Block> CALCITE_PRESSURE_PLATE = registerPressurePlate("calcite", false, MapColor.TERRACOTTA_WHITE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CALCITE_BUTTON = registerButton("calcite", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Tuff

    public static final RegistryObject<Block> TUFF_STAIRS = registerStair("tuff", Blocks.TUFF::defaultBlockState);
    public static final RegistryObject<Block> TUFF_SLAB = registerSlab("tuff", Blocks.TUFF::defaultBlockState);
    public static final RegistryObject<Block> TUFF_WALL = registerWall("tuff", Blocks.TUFF::defaultBlockState);
    public static final RegistryObject<Block> TUFF_PRESSURE_PLATE = registerPressurePlate("tuff", false, MapColor.TERRACOTTA_GRAY, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> TUFF_BUTTON = registerButton("tuff", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Dripstone

    public static final RegistryObject<Block> DRIPSTONE_STAIRS = registerStair("dripstone", Blocks.DRIPSTONE_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> DRIPSTONE_SLAB = registerSlab("dripstone", Blocks.DRIPSTONE_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> DRIPSTONE_WALL = registerWall("dripstone", Blocks.DRIPSTONE_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> DRIPSTONE_PRESSURE_PLATE = registerPressurePlate("dripstone", false, MapColor.TERRACOTTA_BROWN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DRIPSTONE_BUTTON = registerButton("dripstone", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Obsidian, Crying Obsidian and Glowing Obsidian

    public static final RegistryObject<Block> OBSIDIAN_STAIRS = registerStair("obsidian", Blocks.OBSIDIAN::defaultBlockState, PushReaction.BLOCK);
    public static final RegistryObject<Block> OBSIDIAN_SLAB = registerSlab("obsidian", Blocks.OBSIDIAN::defaultBlockState, PushReaction.BLOCK);
    public static final RegistryObject<Block> OBSIDIAN_WALL = registerWall("obsidian", Blocks.OBSIDIAN::defaultBlockState, PushReaction.BLOCK);
    public static final RegistryObject<Block> OBSIDIAN_PRESSURE_PLATE = registerPressurePlate("obsidian", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> OBSIDIAN_BUTTON = registerButton("obsidian", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CRYING_OBSIDIAN_STAIRS = registerBlock("crying_obsidian_stairs", CryingObsidianStairs::new);
    public static final RegistryObject<Block> CRYING_OBSIDIAN_SLAB = registerBlock("crying_obsidian_slab", CryingObsidianSlab::new);
    public static final RegistryObject<Block> CRYING_OBSIDIAN_WALL = registerBlock("crying_obsidian_wall", CryingObsidianWall::new);
    public static final RegistryObject<Block> CRYING_OBSIDIAN_PRESSURE_PLATE = registerPressurePlate("crying_obsidian", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CRYING_OBSIDIAN_BUTTON = registerButton("crying_obsidian", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GLOWING_OBSIDIAN = registerBlock("glowing_obsidian", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.OBSIDIAN).lightLevel(state -> 15).pushReaction(PushReaction.BLOCK)));
    public static final RegistryObject<Block> GLOWING_OBSIDIAN_STAIRS = registerStair("glowing_obsidian", Suppliers.memoize(() -> GLOWING_OBSIDIAN.get().defaultBlockState()));
    public static final RegistryObject<Block> GLOWING_OBSIDIAN_SLAB = registerSlab("glowing_obsidian", Suppliers.memoize(() -> GLOWING_OBSIDIAN.get().defaultBlockState()));
    public static final RegistryObject<Block> GLOWING_OBSIDIAN_WALL = registerWall("glowing_obsidian", Suppliers.memoize(() -> GLOWING_OBSIDIAN.get().defaultBlockState()));
    public static final RegistryObject<Block> GLOWING_OBSIDIAN_PRESSURE_PLATE = registerPressurePlate("glowing_obsidian", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GLOWING_OBSIDIAN_BUTTON = registerButton("glowing_obsidian", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Misc

    public static final RegistryObject<Block> LAVA_ROCK = registerBlock("lava_rock", Suppliers.memoize(() -> new MagmaBlock(PropertyHelper.copy(Blocks.MAGMA_BLOCK).lightLevel(state -> 10).mapColor(MapColor.COLOR_BLACK))));
    public static final RegistryObject<Block> PERENNIAL_ICE = registerBlock("perennial_ice", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.BLUE_ICE).strength(3.5F).friction(0.99F)));
    public static final RegistryObject<Block> END_SOIL = registerBlock("end_soil", Suppliers.memoize(() -> new MWSoilBlock(MapColor.COLOR_GREEN, MWSoundTypes.END_SOIL, Blocks.END_STONE::defaultBlockState)));
    public static final RegistryObject<Block> SCULK_SOIL = registerBlock("sculk_soil", Suppliers.memoize(SculkSoilBlock::new));

    //#endregion

    //#endregion

    //#region Metal Block Sets

    //#region Iron

    public static final RegistryObject<Block> IRON_STAIRS = registerStair("iron", Blocks.IRON_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> IRON_SLAB = registerSlab("iron", Blocks.IRON_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> IRON_CAGE = registerCage("iron", Blocks.IRON_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> IRON_GRATE = registerHorizontalPane("iron_grate", Suppliers.memoize(() -> Blocks.IRON_BARS.defaultBlockState()));
    public static final RegistryObject<Block> CUT_IRON = registerBlock("cut_iron", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> CUT_IRON_STAIRS = registerStair("cut_iron", Suppliers.memoize(() -> CUT_IRON.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_IRON_SLAB = registerSlab("cut_iron", Suppliers.memoize(() -> CUT_IRON.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_IRON_PRESSURE_PLATE = registerWeightedPressurePlate("cut_iron", 15, MapColor.METAL, Suppliers.memoize(() -> BlockSetType.IRON));
    public static final RegistryObject<Block> WALL_HANGING_LANTERN = registerWallHangingLantern("", false, () -> Blocks.LANTERN);
    public static final RegistryObject<Block> WALL_HANGING_SOUL_LANTERN = registerWallHangingLantern("", true, () -> Blocks.SOUL_LANTERN);
    public static final RegistryObject<Block> END_LANTERN = registerLantern(ResourceHelper.fireName(MWFireBlock.MWFireType.END), false, MWFireBlock.MWFireType.END.lightLevel());
    public static final RegistryObject<Block> WALL_HANGING_END_LANTERN = registerWallHangingLantern(ResourceHelper.fireName(MWFireBlock.MWFireType.END), false, Suppliers.memoize(() -> END_LANTERN.get()), MWFireBlock.MWFireType.END.lightLevel());
    public static final RegistryObject<Block> SCULK_LANTERN = registerLantern(ResourceHelper.fireName(MWFireBlock.MWFireType.SCULK), false, MWFireBlock.MWFireType.SCULK.lightLevel());
    public static final RegistryObject<Block> WALL_HANGING_SCULK_LANTERN = registerWallHangingLantern(ResourceHelper.fireName(MWFireBlock.MWFireType.SCULK), false, Suppliers.memoize(() -> SCULK_LANTERN.get()), MWFireBlock.MWFireType.SCULK.lightLevel());

    //#endregion

    //#region Gold

    public static final RegistryObject<Block> GOLDEN_STAIRS = registerStair("golden", Blocks.GOLD_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> GOLDEN_SLAB = registerSlab("golden", Blocks.GOLD_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> GOLDEN_DOOR = registerDoor("golden", true, Suppliers.memoize(() -> BlockSetType.GOLD));
    public static final RegistryObject<Block> GOLDEN_TRAPDOOR = registerTrapdoor("golden", true, Suppliers.memoize(() -> BlockSetType.GOLD));
    public static final RegistryObject<Block> GOLDEN_CHAIN = registerChain("golden");
    public static final RegistryObject<Block> GOLDEN_LANTERN = registerLantern("golden", false);
    public static final RegistryObject<Block> WALL_HANGING_GOLDEN_LANTERN = registerWallHangingLantern("golden", false, Suppliers.memoize(() -> GOLDEN_LANTERN.get()));
    public static final RegistryObject<Block> GOLDEN_SOUL_LANTERN = registerLantern("golden", true);
    public static final RegistryObject<Block> WALL_HANGING_GOLDEN_SOUL_LANTERN = registerWallHangingLantern("golden", true, Suppliers.memoize(() -> GOLDEN_SOUL_LANTERN.get()));
    public static final RegistryObject<Block> GOLDEN_END_LANTERN = registerLantern("golden_" + ResourceHelper.fireName(MWFireBlock.MWFireType.END), false, MWFireBlock.MWFireType.END.lightLevel());
    public static final RegistryObject<Block> WALL_HANGING_GOLDEN_END_LANTERN = registerWallHangingLantern("golden_" + ResourceHelper.fireName(MWFireBlock.MWFireType.END), false, Suppliers.memoize(() -> END_LANTERN.get()), MWFireBlock.MWFireType.END.lightLevel());
    public static final RegistryObject<Block> GOLDEN_SCULK_LANTERN = registerLantern("golden_" + ResourceHelper.fireName(MWFireBlock.MWFireType.SCULK), false, MWFireBlock.MWFireType.SCULK.lightLevel());
    public static final RegistryObject<Block> WALL_HANGING_GOLDEN_SCULK_LANTERN = registerWallHangingLantern("golden_" + ResourceHelper.fireName(MWFireBlock.MWFireType.SCULK), false, Suppliers.memoize(() -> SCULK_LANTERN.get()), MWFireBlock.MWFireType.SCULK.lightLevel());
    public static final RegistryObject<Block> GOLD_BARS = registerBars("gold");
    public static final RegistryObject<Block> GOLDEN_CAGE = registerCage("golden", Blocks.GOLD_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> GOLD_GRATE = registerHorizontalPane("gold_grate", Suppliers.memoize(() -> GOLD_BARS.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_GOLD = registerBlock("cut_gold", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.GOLD_BLOCK)));
    public static final RegistryObject<Block> CUT_GOLDEN_STAIRS = registerStair("cut_golden", Suppliers.memoize(() -> CUT_GOLD.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_GOLDEN_SLAB = registerSlab("cut_golden", Suppliers.memoize(() -> CUT_GOLD.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_GOLDEN_PRESSURE_PLATE = registerWeightedPressurePlate("cut_golden", 15, MapColor.GOLD, Suppliers.memoize(() -> BlockSetType.GOLD));

    //#endregion

    //#region Netherite

    public static final RegistryObject<Block> NETHERITE_STAIRS = registerStair("netherite", Blocks.NETHERITE_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> NETHERITE_SLAB = registerSlab("netherite", Blocks.NETHERITE_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> NETHERITE_DOOR = registerDoor("netherite", true, MWBlockSetTypes.NETHERITE);
    public static final RegistryObject<Block> NETHERITE_TRAPDOOR = registerTrapdoor("netherite", true, MWBlockSetTypes.NETHERITE);
    public static final RegistryObject<Block> NETHERITE_PRESSURE_PLATE = registerWeightedPressurePlate("netherite", 100, MapColor.COLOR_BLACK, MWBlockSetTypes.NETHERITE);
    public static final RegistryObject<Block> NETHERITE_CHAIN = registerChain("netherite");
    public static final RegistryObject<Block> NETHERITE_LANTERN = registerLantern("netherite", false);
    public static final RegistryObject<Block> WALL_HANGING_NETHERITE_LANTERN = registerWallHangingLantern("netherite", false, Suppliers.memoize(() -> NETHERITE_LANTERN.get()));
    public static final RegistryObject<Block> NETHERITE_SOUL_LANTERN = registerLantern("netherite", true);
    public static final RegistryObject<Block> WALL_HANGING_NETHERITE_SOUL_LANTERN = registerWallHangingLantern("netherite", true, Suppliers.memoize(() -> NETHERITE_SOUL_LANTERN.get()));
    public static final RegistryObject<Block> NETHERITE_END_LANTERN = registerLantern("netherite_" + ResourceHelper.fireName(MWFireBlock.MWFireType.END), false, MWFireBlock.MWFireType.END.lightLevel());
    public static final RegistryObject<Block> WALL_HANGING_NETHERITE_END_LANTERN = registerWallHangingLantern("netherite_" + ResourceHelper.fireName(MWFireBlock.MWFireType.END), false, Suppliers.memoize(() -> END_LANTERN.get()), MWFireBlock.MWFireType.END.lightLevel());
    public static final RegistryObject<Block> NETHERITE_SCULK_LANTERN = registerLantern("netherite_" + ResourceHelper.fireName(MWFireBlock.MWFireType.SCULK), false, MWFireBlock.MWFireType.SCULK.lightLevel());
    public static final RegistryObject<Block> WALL_HANGING_NETHERITE_SCULK_LANTERN = registerWallHangingLantern("netherite_" + ResourceHelper.fireName(MWFireBlock.MWFireType.SCULK), false, Suppliers.memoize(() -> SCULK_LANTERN.get()), MWFireBlock.MWFireType.SCULK.lightLevel());
    public static final RegistryObject<Block> NETHERITE_BARS = registerBars("netherite");
    public static final RegistryObject<Block> NETHERITE_CAGE = registerCage("netherite", Blocks.NETHERITE_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> NETHERITE_GRATE = registerHorizontalPane("netherite_grate", Suppliers.memoize(() -> NETHERITE_BARS.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_NETHERITE = registerBlock("cut_netherite", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> CUT_NETHERITE_STAIRS = registerStair("cut_netherite", Suppliers.memoize(() -> CUT_NETHERITE.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_NETHERITE_SLAB = registerSlab("cut_netherite", Suppliers.memoize(() -> CUT_NETHERITE.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_NETHERITE_PRESSURE_PLATE = registerWeightedPressurePlate("cut_netherite", 100, MapColor.COLOR_BLACK, MWBlockSetTypes.NETHERITE);

    //#endregion

    //#region Aluminum

    public static final RegistryObject<Block> ALUMINUM_STAIRS = registerStair("aluminum", Suppliers.memoize(() -> ALUMINUM_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> ALUMINUM_SLAB = registerSlab("aluminum", Suppliers.memoize(() -> ALUMINUM_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> ALUMINUM_DOOR = registerDoor("aluminum", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> ALUMINUM_TRAPDOOR = registerTrapdoor("aluminum", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> ALUMINUM_PRESSURE_PLATE = registerWeightedPressurePlate("aluminum", 15, MWColors.ALUMINUM.toMapColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> ALUMINUM_CHAIN = registerChain("aluminum");
    public static final RegistryObject<Block> ALUMINUM_LANTERN = registerLantern("aluminum", false);
    public static final RegistryObject<Block> WALL_HANGING_ALUMINUM_LANTERN = registerWallHangingLantern("aluminum", false, Suppliers.memoize(() -> ALUMINUM_LANTERN.get()));
    public static final RegistryObject<Block> ALUMINUM_SOUL_LANTERN = registerLantern("aluminum", true);
    public static final RegistryObject<Block> WALL_HANGING_ALUMINUM_SOUL_LANTERN = registerWallHangingLantern("aluminum", true, Suppliers.memoize(() -> ALUMINUM_SOUL_LANTERN.get()));
    public static final RegistryObject<Block> ALUMINUM_END_LANTERN = registerLantern("aluminum_" + ResourceHelper.fireName(MWFireBlock.MWFireType.END), false, MWFireBlock.MWFireType.END.lightLevel());
    public static final RegistryObject<Block> WALL_HANGING_ALUMINUM_END_LANTERN = registerWallHangingLantern("aluminum_" + ResourceHelper.fireName(MWFireBlock.MWFireType.END), false, Suppliers.memoize(() -> END_LANTERN.get()), MWFireBlock.MWFireType.END.lightLevel());
    public static final RegistryObject<Block> ALUMINUM_SCULK_LANTERN = registerLantern("aluminum_" + ResourceHelper.fireName(MWFireBlock.MWFireType.SCULK), false, MWFireBlock.MWFireType.SCULK.lightLevel());
    public static final RegistryObject<Block> WALL_HANGING_ALUMINUM_SCULK_LANTERN = registerWallHangingLantern("aluminum_" + ResourceHelper.fireName(MWFireBlock.MWFireType.SCULK), false, Suppliers.memoize(() -> SCULK_LANTERN.get()), MWFireBlock.MWFireType.SCULK.lightLevel());
    public static final RegistryObject<Block> ALUMINUM_BARS = registerBars("aluminum");
    public static final RegistryObject<Block> ALUMINUM_CAGE = registerCage("aluminum", Suppliers.memoize(() -> ALUMINUM_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> ALUMINUM_GRATE = registerHorizontalPane("aluminum_grate", Suppliers.memoize(() -> ALUMINUM_BARS.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_ALUMINUM = registerBlock("cut_aluminum", Suppliers.memoize(() -> PropertyHelper.copy(ALUMINUM_BLOCK.get())));
    public static final RegistryObject<Block> CUT_ALUMINUM_STAIRS = registerStair("cut_aluminum", Suppliers.memoize(() -> CUT_ALUMINUM.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_ALUMINUM_SLAB = registerSlab("cut_aluminum", Suppliers.memoize(() -> CUT_ALUMINUM.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_ALUMINUM_PRESSURE_PLATE = registerWeightedPressurePlate("cut_aluminum", 15, MWColors.ALUMINUM.toMapColor(), MWBlockSetTypes.METAL);

    //#endregion

    //#region Silver

    public static final RegistryObject<Block> SILVER_STAIRS = registerStair("silver", Suppliers.memoize(() -> SILVER_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> SILVER_SLAB = registerSlab("silver", Suppliers.memoize(() -> SILVER_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> SILVER_DOOR = registerDoor("silver", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> SILVER_TRAPDOOR = registerTrapdoor("silver", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> SILVER_PRESSURE_PLATE = registerWeightedPressurePlate("silver", 50, MWColors.SILVER.toMapColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> SILVER_CHAIN = registerChain("silver");
    public static final RegistryObject<Block> SILVER_LANTERN = registerLantern("silver", false);
    public static final RegistryObject<Block> WALL_HANGING_SILVER_LANTERN = registerWallHangingLantern("silver", false, Suppliers.memoize(() -> SILVER_LANTERN.get()));
    public static final RegistryObject<Block> SILVER_SOUL_LANTERN = registerLantern("silver", true);
    public static final RegistryObject<Block> WALL_HANGING_SILVER_SOUL_LANTERN = registerWallHangingLantern("silver", true, Suppliers.memoize(() -> SILVER_SOUL_LANTERN.get()));
    public static final RegistryObject<Block> SILVER_END_LANTERN = registerLantern("silver_" + ResourceHelper.fireName(MWFireBlock.MWFireType.END), false, MWFireBlock.MWFireType.END.lightLevel());
    public static final RegistryObject<Block> WALL_HANGING_SILVER_END_LANTERN = registerWallHangingLantern("silver_" + ResourceHelper.fireName(MWFireBlock.MWFireType.END), false, Suppliers.memoize(() -> END_LANTERN.get()), MWFireBlock.MWFireType.END.lightLevel());
    public static final RegistryObject<Block> SILVER_SCULK_LANTERN = registerLantern("silver_" + ResourceHelper.fireName(MWFireBlock.MWFireType.SCULK), false, MWFireBlock.MWFireType.SCULK.lightLevel());
    public static final RegistryObject<Block> WALL_HANGING_SILVER_SCULK_LANTERN = registerWallHangingLantern("silver_" + ResourceHelper.fireName(MWFireBlock.MWFireType.SCULK), false, Suppliers.memoize(() -> SCULK_LANTERN.get()), MWFireBlock.MWFireType.SCULK.lightLevel());
    public static final RegistryObject<Block> SILVER_BARS = registerBars("silver");
    public static final RegistryObject<Block> SILVER_CAGE = registerCage("silver", Suppliers.memoize(() -> SILVER_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> SILVER_GRATE = registerHorizontalPane("silver_grate", Suppliers.memoize(() -> SILVER_BARS.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_SILVER = registerBlock("cut_silver", Suppliers.memoize(() -> PropertyHelper.copy(SILVER_BLOCK.get())));
    public static final RegistryObject<Block> CUT_SILVER_STAIRS = registerStair("cut_silver", Suppliers.memoize(() -> CUT_SILVER.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_SILVER_SLAB = registerSlab("cut_silver", Suppliers.memoize(() -> CUT_SILVER.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_SILVER_PRESSURE_PLATE = registerWeightedPressurePlate("cut_silver", 50, MWColors.SILVER.toMapColor(), MWBlockSetTypes.METAL);

    //#endregion

    //#region Bronze

    public static final RegistryObject<Block> BRONZE_STAIRS = registerStair("bronze", Suppliers.memoize(() -> BRONZE_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> BRONZE_SLAB = registerSlab("bronze", Suppliers.memoize(() -> BRONZE_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> BRONZE_DOOR = registerDoor("bronze", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> BRONZE_TRAPDOOR = registerTrapdoor("bronze", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> BRONZE_PRESSURE_PLATE = registerWeightedPressurePlate("bronze", 15, MWColors.BRONZE.toMapColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> BRONZE_CHAIN = registerChain("bronze");
    public static final RegistryObject<Block> BRONZE_LANTERN = registerLantern("bronze", false);
    public static final RegistryObject<Block> WALL_HANGING_BRONZE_LANTERN = registerWallHangingLantern("bronze", false, Suppliers.memoize(() -> BRONZE_LANTERN.get()));
    public static final RegistryObject<Block> BRONZE_SOUL_LANTERN = registerLantern("bronze", true);
    public static final RegistryObject<Block> WALL_HANGING_BRONZE_SOUL_LANTERN = registerWallHangingLantern("bronze", true, Suppliers.memoize(() -> BRONZE_SOUL_LANTERN.get()));
    public static final RegistryObject<Block> BRONZE_END_LANTERN = registerLantern("bronze_" + ResourceHelper.fireName(MWFireBlock.MWFireType.END), false, MWFireBlock.MWFireType.END.lightLevel());
    public static final RegistryObject<Block> WALL_HANGING_BRONZE_END_LANTERN = registerWallHangingLantern("bronze_" + ResourceHelper.fireName(MWFireBlock.MWFireType.END), false, Suppliers.memoize(() -> END_LANTERN.get()), MWFireBlock.MWFireType.END.lightLevel());
    public static final RegistryObject<Block> BRONZE_SCULK_LANTERN = registerLantern("bronze_" + ResourceHelper.fireName(MWFireBlock.MWFireType.SCULK), false, MWFireBlock.MWFireType.SCULK.lightLevel());
    public static final RegistryObject<Block> WALL_HANGING_BRONZE_SCULK_LANTERN = registerWallHangingLantern("bronze_" + ResourceHelper.fireName(MWFireBlock.MWFireType.SCULK), false, Suppliers.memoize(() -> SCULK_LANTERN.get()), MWFireBlock.MWFireType.SCULK.lightLevel());
    public static final RegistryObject<Block> BRONZE_BARS = registerBars("bronze");
    public static final RegistryObject<Block> BRONZE_CAGE = registerCage("bronze", Suppliers.memoize(() -> BRONZE_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> BRONZE_GRATE = registerHorizontalPane("bronze_grate", Suppliers.memoize(() -> BRONZE_BARS.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_BRONZE = registerBlock("cut_bronze", Suppliers.memoize(() -> PropertyHelper.copy(BRONZE_BLOCK.get())));
    public static final RegistryObject<Block> CUT_BRONZE_STAIRS = registerStair("cut_bronze", Suppliers.memoize(() -> CUT_BRONZE.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_BRONZE_SLAB = registerSlab("cut_bronze", Suppliers.memoize(() -> CUT_BRONZE.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_BRONZE_PRESSURE_PLATE = registerWeightedPressurePlate("cut_bronze", 15, MWColors.BRONZE.toMapColor(), MWBlockSetTypes.METAL);

    //#endregion

    //#endregion

    //#region Glass Block Sets

    public static final RegistryObject<Block> GLASS_STAIRS = registerStair("glass", Blocks.GLASS::defaultBlockState);
    public static final RegistryObject<Block> GLASS_SLAB = registerSlab("glass", Blocks.GLASS::defaultBlockState);
    public static final RegistryObject<Block> GLASS_WALL = registerGlassWall("glass", Blocks.GLASS::defaultBlockState);

    //#endregion

    //#region Flowers, Crops and Plants

    public static final RegistryObject<Block> BLUE_ROSE = registerFlower("blue_rose", Suppliers.memoize(() -> MobEffects.SATURATION));
    public static final RegistryObject<Block> BLUE_ROSE_BUSH = registerTallFlower("blue_rose_bush");
    public static final RegistryObject<Block> WHITE_ROSE = registerFlower("white_rose", () -> MobEffects.HEAL);
    public static final RegistryObject<Block> WHITE_ROSE_BUSH = registerTallFlower("white_rose_bush");
    public static final RegistryObject<Block> CORN = registerBlockWithoutBlockItem("corn", CornBlock::new);
    public static final RegistryObject<Block> WARPED_WART = registerBlockWithoutBlockItem("warped_wart", WarpedWartBlock::new);
    public static final RegistryObject<Block> BLUEBERRY_BUSH = registerBlockWithoutBlockItem("blueberry_bush", BlueberryBushBlock::new);
    public static final RegistryObject<Block> BROWN_MUSHROOM_WALL_FAN = registerWallFan("brown_mushroom", MapColor.COLOR_BROWN, () -> Blocks.BROWN_MUSHROOM);
    public static final RegistryObject<Block> RED_MUSHROOM_WALL_FAN = registerWallFan("red_mushroom", MapColor.COLOR_RED, () -> Blocks.RED_MUSHROOM);
    public static final RegistryObject<Block> CATTAIL = registerBlock("cattail", CattailBlock::new);
    public static final RegistryObject<Block> SCULK_ROOTS = registerBlock("sculk_roots", SculkRootBlock::new);

    //#endregion

    //#region Plant Carpets

    public static final RegistryObject<Block> GRASS_CARPET = registerCarpet("grass", () -> Blocks.GRASS_BLOCK);
    public static final RegistryObject<Block> PODZOL_CARPET = registerCarpet("podzol", () -> Blocks.PODZOL);
    public static final RegistryObject<Block> MYCELIUM_CARPET = registerCarpet("mycelium", () -> Blocks.MYCELIUM);
    public static final RegistryObject<Block> DIRT_CARPET = registerCarpet("dirt", () -> Blocks.DIRT);
    public static final RegistryObject<Block> COARSE_DIRT_CARPET = registerCarpet("coarse_dirt", () -> Blocks.COARSE_DIRT);
    public static final RegistryObject<Block> ROOTED_DIRT_CARPET = registerCarpet("rooted_dirt", () -> Blocks.ROOTED_DIRT);
    public static final RegistryObject<Block> MUD_CARPET = registerCarpet("mud", () -> Blocks.MUD);
    public static final RegistryObject<Block> CLAY_CARPET = registerCarpet("clay", () -> Blocks.CLAY);
    public static final RegistryObject<Block> CRIMSON_NYLIUM_CARPET = registerCarpet("crimson_nylium", () -> Blocks.CRIMSON_NYLIUM);
    public static final RegistryObject<Block> WARPED_NYLIUM_CARPET = registerCarpet("warped_nylium", () -> Blocks.WARPED_NYLIUM);
    public static final RegistryObject<Block> GRAVEL_CARPET = registerCarpet("gravel", () -> Blocks.GRAVEL);
    public static final RegistryObject<Block> SAND_CARPET = registerCarpet("sand", () -> Blocks.SAND);
    public static final RegistryObject<Block> RED_SAND_CARPET = registerCarpet("red_sand", () -> Blocks.RED_SAND);

    //#endregion

    //#region TNT

    public static final RegistryObject<Block> DISGUISED_GRASS_TNT = registerTnt(MWPrimedTnt.Type.DISGUISED_GRASS);
    public static final RegistryObject<Block> DISGUISED_DIRT_TNT = registerTnt(MWPrimedTnt.Type.DISGUISED_DIRT);
    public static final RegistryObject<Block> DISGUISED_SAND_TNT = registerFallableTnt(MWPrimedTnt.Type.DISGUISED_SAND, 14406560);
    public static final RegistryObject<Block> DISGUISED_RED_SAND_TNT = registerFallableTnt(MWPrimedTnt.Type.DISGUISED_RED_SAND, 11098145);
    public static final RegistryObject<Block> DISGUISED_STONE_TNT = registerTnt(MWPrimedTnt.Type.DISGUISED_STONE);
    public static final RegistryObject<Block> DISGUISED_CAKE_TNT = registerTnt(MWPrimedTnt.Type.DISGUISED_CAKE);
    public static final RegistryObject<Block> MEGA_TNT = registerTnt(MWPrimedTnt.Type.MEGA);
    public static final RegistryObject<Block> SUPER_TNT = registerTnt(MWPrimedTnt.Type.SUPER);
    public static final RegistryObject<Block> HYPER_TNT = registerTnt(MWPrimedTnt.Type.HYPER);

    //#endregion

    //#region Heads

    public static final RegistryObject<Block> STRAY_SKULL = registerBlockWithoutBlockItem("stray_skull", Suppliers.memoize(() -> new MWSkullBlock(MWSkullBlock.Types.STRAY)));
    public static final RegistryObject<Block> STRAY_WALL_SKULL = registerBlockWithoutBlockItem("stray_wall_skull", Suppliers.memoize(() -> new MWWallSkullBlock(MWSkullBlock.Types.STRAY, STRAY_SKULL)));
    public static final RegistryObject<Block> HUSK_HEAD = registerBlockWithoutBlockItem("husk_head", Suppliers.memoize(() -> new MWSkullBlock(MWSkullBlock.Types.HUSK)));
    public static final RegistryObject<Block> HUSK_WALL_HEAD = registerBlockWithoutBlockItem("husk_wall_head", Suppliers.memoize(() -> new MWWallSkullBlock(MWSkullBlock.Types.HUSK, HUSK_HEAD)));
    public static final RegistryObject<Block> DROWNED_HEAD = registerBlockWithoutBlockItem("drowned_head", Suppliers.memoize(() -> new MWSkullBlock(MWSkullBlock.Types.DROWNED)));
    public static final RegistryObject<Block> DROWNED_WALL_HEAD = registerBlockWithoutBlockItem("drowned_wall_head", Suppliers.memoize(() -> new MWWallSkullBlock(MWSkullBlock.Types.DROWNED, DROWNED_HEAD)));

    //#endregion

    //#region Rods

    public static final RegistryObject<Block> BONE_ROD_BLOCK = registerRod("bone", MapColor.TERRACOTTA_WHITE, SoundType.BONE_BLOCK);
    public static final RegistryObject<Block> BLAZE_ROD_BLOCK = registerRod("blaze", MapColor.TERRACOTTA_ORANGE, SoundType.METAL);
    public static final RegistryObject<Block> STICK_ROD_BLOCK = registerRod("stick", MapColor.WOOD, SoundType.WOOD);

    //#endregion

    //#region Torches

    //#region Regular

    public static final RegistryObject<Block> UNLIT_TORCH = registerUnlitTorch("");
    public static final RegistryObject<Block> UNLIT_WALL_TORCH = registerUnlitWallTorch("", Suppliers.memoize(() -> UNLIT_TORCH.get()));

    //#endregion

    //#region Soul

    public static final RegistryObject<Block> UNLIT_SOUL_TORCH = registerUnlitTorch("soul");
    public static final RegistryObject<Block> UNLIT_SOUL_WALL_TORCH = registerUnlitWallTorch("soul", Suppliers.memoize(() -> UNLIT_SOUL_TORCH.get()));

    //#endregion

    //#region End

    public static final RegistryObject<Block> END_TORCH = registerTorch(MWFireBlock.MWFireType.END, Suppliers.memoize(() -> MWParticleTypes.END_FIRE_FLAME.get()));
    public static final RegistryObject<Block> END_WALL_TORCH = registerWallTorch(MWFireBlock.MWFireType.END, Suppliers.memoize(() -> MWParticleTypes.END_FIRE_FLAME.get()), Suppliers.memoize(() -> END_TORCH.get()));
    public static final RegistryObject<Block> UNLIT_END_TORCH = registerUnlitTorch(ResourceHelper.fireName(MWFireBlock.MWFireType.END));
    public static final RegistryObject<Block> UNLIT_END_WALL_TORCH = registerUnlitWallTorch(ResourceHelper.fireName(MWFireBlock.MWFireType.END), Suppliers.memoize(() -> UNLIT_END_TORCH.get()));

    //#endregion

    //#region Sculk

    public static final RegistryObject<Block> SCULK_TORCH = registerTorch(MWFireBlock.MWFireType.SCULK, Suppliers.memoize(() -> MWParticleTypes.SCULK_FIRE_FLAME.get()));
    public static final RegistryObject<Block> SCULK_WALL_TORCH = registerWallTorch(MWFireBlock.MWFireType.SCULK, Suppliers.memoize(() -> MWParticleTypes.SCULK_FIRE_FLAME.get()), Suppliers.memoize(() -> SCULK_TORCH.get()));
    public static final RegistryObject<Block> UNLIT_SCULK_TORCH = registerUnlitTorch(ResourceHelper.fireName(MWFireBlock.MWFireType.SCULK));
    public static final RegistryObject<Block> UNLIT_SCULK_WALL_TORCH = registerUnlitWallTorch(ResourceHelper.fireName(MWFireBlock.MWFireType.SCULK), Suppliers.memoize(() -> UNLIT_SCULK_TORCH.get()));

    //#endregion

    //#endregion

    //#region Fires and Campfires

    public static final RegistryObject<Block> END_FIRE = registerFireBlock(MWFireBlock.MWFireType.END);
    public static final RegistryObject<Block> END_CAMPFIRE = registerCampfireBlock(MWFireBlock.MWFireType.END);
    public static final RegistryObject<Block> SCULK_FIRE = registerFireBlock(MWFireBlock.MWFireType.SCULK);
    public static final RegistryObject<Block> SCULK_CAMPFIRE = registerCampfireBlock(MWFireBlock.MWFireType.SCULK);

    //#endregion

    //#region Misc

    public static final RegistryObject<Block> DAYLIGHT_LAMP = registerBlock("daylight_lamp", Suppliers.memoize(() -> new DaylightLampBlock()));
    public static final RegistryObject<Block> HORIZONTAL_GLASS_PANE = registerHorizontalPane("horizontal_glass_pane", Blocks.GLASS::defaultBlockState);
    public static final RegistryObject<Block> ROPE = registerBlock("rope", Suppliers.memoize(() -> new RopeBlock()));
    public static final RegistryObject<Block> ROPE_TAIL = registerBlockWithoutBlockItem("rope_tail", Suppliers.memoize(() -> new RopeTailBlock()));
    public static final RegistryObject<Block> ICE_CHEST = registerChest("ice", MWWoodTypes.ICE, Suppliers.memoize(() -> MWBlockEntityTypes.ICE_CHEST.get()));
    public static final RegistryObject<Block> ICE_TRAPPED_CHEST = registerTrappedChest("ice", MWWoodTypes.ICE, Suppliers.memoize(() -> MWBlockEntityTypes.ICE_TRAPPED_CHEST.get()));
    public static final RegistryObject<Block> CHRISTMAS_LIGHTS = registerBlock("christmas_lights", Suppliers.memoize(() -> new SidePanelBlock(PropertyHelper.block(0.15F, false).instabreak().sound(SoundType.GLASS))));
    public static final RegistryObject<Block> GIFT = registerBlockWithoutBlockItem("gift", Suppliers.memoize(() -> new GiftBlock()));

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
     * @param materialName {@link String The Block material name}
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State the Stair is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerStair(final String materialName, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerStair(materialName, blockStateSupplier, PushReaction.NORMAL, featureFlags);
    }

    /**
     * Register a {@link MWStairBlock Stair Block}
     *
     * @param materialName {@link String The Block material name}
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State the Stair is based on}
     * @param pushReaction {@link PushReaction The Block Push Reaction when moved by Pistons}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerStair(final String materialName, final Supplier<BlockState> blockStateSupplier, final PushReaction pushReaction, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_stairs", () -> new MWStairBlock(blockStateSupplier, pushReaction, featureFlags));
    }

    /**
     * Register a {@link MWSlabBlock Slab Block}
     *
     * @param materialName {@link String The Block material name}
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State the Slab is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerSlab(final String materialName, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerSlab(materialName, blockStateSupplier, PushReaction.NORMAL, featureFlags);
    }

    /**
     * Register a {@link MWSlabBlock Slab Block}
     *
     * @param materialName {@link String The Block material name}
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State the Slab is based on}
     * @param pushReaction {@link PushReaction The Block Push Reaction when moved by Pistons}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerSlab(final String materialName, final Supplier<BlockState> blockStateSupplier, final PushReaction pushReaction, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_slab", () -> new MWSlabBlock(blockStateSupplier, pushReaction, featureFlags));
    }

    /**
     * Register a {@link MWWallBlock Wall Block}
     *
     * @param materialName {@link String The Block material name}
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State the Wall is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerWall(final String materialName, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerWall(materialName, blockStateSupplier, PushReaction.NORMAL, featureFlags);
    }

    /**
     * Register a {@link MWWallBlock Wall Block}
     *
     * @param materialName {@link String The Block material name}
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State the Wall is based on}
     * @param pushReaction {@link PushReaction The Block Push Reaction when moved by Pistons}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerWall(final String materialName, final Supplier<BlockState> blockStateSupplier, final PushReaction pushReaction, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_wall", () -> new MWWallBlock(blockStateSupplier, pushReaction, featureFlags));
    }

    /**
     * Register a {@link GlassWallBlock Glass Wall Block}
     *
     * @param materialName {@link String The Block material name}
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State the Wall is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerGlassWall(final String materialName, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_wall", () -> new GlassWallBlock(blockStateSupplier, featureFlags));
    }

    /**
     * Register a {@link PressurePlateBlock Pressure Plate Block}
     *
     * @param materialName {@link String The Block material name}
     * @param isWooden {@link Boolean If the Pressure Plate is a Wooden Pressure Plate}
     * @param mapColor {@link MapColor The Block Map color}
     * @param blockSetTypeSupplier {@link Supplier<BlockSetType> The Block Set Type Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerPressurePlate(final String materialName, final boolean isWooden, final MapColor mapColor, final Supplier<BlockSetType> blockSetTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_pressure_plate", () -> new PressurePlateBlock(isWooden ? PressurePlateBlock.Sensitivity.EVERYTHING : PressurePlateBlock.Sensitivity.MOBS,
                PropertyHelper.copy(isWooden ? Blocks.OAK_PRESSURE_PLATE : Blocks.STONE_PRESSURE_PLATE, featureFlags).mapColor(mapColor),
                blockSetTypeSupplier.get())
        );
    }

    /**
     * Register a {@link WeightedPressurePlateBlock Weighted Pressure Plate Block}
     *
     * @param materialName {@link String The Block material name}
     * @param maxWeight {@link Integer The Weighted Pressure Plate max weight}
     * @param mapColor {@link MapColor The Block Map color}
     * @param blockSetTypeSupplier {@link Supplier<BlockSetType> The Block Set Type Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerWeightedPressurePlate(final String materialName, final int maxWeight, final MapColor mapColor, final Supplier<BlockSetType> blockSetTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_pressure_plate", () -> new WeightedPressurePlateBlock(maxWeight, PropertyHelper.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE, featureFlags).mapColor(mapColor), blockSetTypeSupplier.get()));
    }

    /**
     * Register a {@link ButtonBlock Button Block}
     *
     * @param materialName {@link String The Block material name}
     * @param isWooden {@link Boolean If the Button is a Wooden Button}
     * @param blockSetTypeSupplier {@link Supplier<BlockSetType> The Block Set Type Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerButton(final String materialName, final boolean isWooden, final Supplier<BlockSetType> blockSetTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_button", () -> new ButtonBlock(
                PropertyHelper.copy(isWooden ? Blocks.OAK_PRESSURE_PLATE : Blocks.STONE_PRESSURE_PLATE, featureFlags),
                blockSetTypeSupplier.get(),
                isWooden ? 30 : 20,
                isWooden)
        );
    }

    /**
     * Register a {@link DoorBlock Door Block}
     *
     * @param materialName {@link String The Block material name}
     * @param requiresPower {@link Boolean If the Door needs redstone to be activated}
     * @param blockSetTypeSupplier {@link BlockSetType The Door Block set type supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerDoor(final String materialName, final boolean requiresPower, final Supplier<BlockSetType> blockSetTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_door", () -> new DoorBlock(PropertyHelper.copy(requiresPower ? Blocks.IRON_DOOR : Blocks.OAK_DOOR, featureFlags), blockSetTypeSupplier.get()));
    }

    /**
     * Register a {@link TrapDoorBlock Trapdoor Block}
     *
     * @param materialName {@link String The Block material name}
     * @param requiresPower {@link Boolean If the Trapdoor needs redstone to be activated}
     * @param blockSetType {@link BlockSetType The Trapdoor Block Set Type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerTrapdoor(final String materialName, final boolean requiresPower, final Supplier<BlockSetType> blockSetType, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_trapdoor", () -> new TrapDoorBlock(PropertyHelper.copy(requiresPower ? Blocks.IRON_TRAPDOOR : Blocks.OAK_TRAPDOOR, featureFlags), blockSetType.get()));
    }

    /**
     * Register a {@link ChainBlock Chain block}
     *
     * @param materialName {@link String The Block material name}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerChain(final String materialName, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_chain", () -> new ChainBlock(PropertyHelper.copy(Blocks.CHAIN, featureFlags)));
    }

    /**
     * Register a {@link LanternBlock Lantern Block}
     *
     * @param materialName {@link String The Block material name}
     * @param isSoulLantern {@link Boolean If the Lantern is a soul lantern}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerLantern(final String materialName, final boolean isSoulLantern, final FeatureFlag... featureFlags) {
        return registerLantern(materialName, isSoulLantern, isSoulLantern ? 10 : 15, featureFlags);
    }

    /**
     * Register a {@link LanternBlock Lantern Block}
     *
     * @param materialName {@link String The Block material name}
     * @param isSoulLantern {@link Boolean If the Lantern is a soul lantern}
     * @param lightLevel {@link Integer The Lantern light level}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerLantern(final String materialName, final boolean isSoulLantern, final int lightLevel, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + (isSoulLantern ? "_soul_lantern" : "_lantern"), () -> new LanternBlock(PropertyHelper.copy(isSoulLantern ? Blocks.SOUL_LANTERN : Blocks.LANTERN, featureFlags).lightLevel(blockState -> lightLevel)));
    }

    /**
     * Register a {@link WallHangingLanternBlock Wall Hanging Lantern Block}
     *
     * @param materialName {@link String The Block material name}
     * @param isSoulLantern {@link Boolean If the Lantern is a soul lantern}
     * @param blockSupplier {@link Supplier<Block> The Supplier for the Block this lantern is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerWallHangingLantern(final String materialName, final boolean isSoulLantern, final Supplier<Block> blockSupplier, final FeatureFlag... featureFlags) {
        return registerWallHangingLantern(materialName, isSoulLantern, blockSupplier, isSoulLantern ? 10 : 15, featureFlags);
    }

    /**
     * Register a {@link WallHangingLanternBlock Wall Hanging Lantern Block}
     *
     * @param materialName {@link String The Block material name}
     * @param isSoulLantern {@link Boolean If the Lantern is a soul lantern}
     * @param blockSupplier {@link Supplier<Block> The Supplier for the Block this lantern is based on}
     * @param lightLevel {@link Integer The Lantern light level}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerWallHangingLantern(final String materialName, final boolean isSoulLantern, final Supplier<Block> blockSupplier, final int lightLevel, final FeatureFlag... featureFlags) {
        return registerBlockWithoutBlockItem("wall_hanging_" + materialName + (materialName.isEmpty() ? "" : "_") + (isSoulLantern ? "soul_lantern" : "lantern"), Suppliers.memoize(() -> new WallHangingLanternBlock(PropertyHelper.copy(blockSupplier.get(), featureFlags).lightLevel(blockState -> lightLevel))));
    }

    /**
     * Register a {@link IronBarsBlock Bar Block}
     *
     * @param materialName {@link String The Block material name}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerBars(final String materialName, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_bars", () -> new IronBarsBlock(PropertyHelper.copy(Blocks.IRON_BARS, featureFlags)));
    }

    /**
     * Register a {@link Block Cage Block}
     *
     * @param materialName {@link String The Block material name}
     * @param blockStateSupplier {@link Supplier<BlockState> The Block State Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerCage(final String materialName, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_cage", () -> new Block(PropertyHelper.makeTranslucent(blockStateSupplier.get().getBlock(), featureFlags).requiresCorrectToolForDrops()));
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
     * Register a {@link MWTntBlock TNT Block}
     *
     * @param type {@link MWPrimedTnt.Type The TNT Type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerTnt(final MWPrimedTnt.Type type, final FeatureFlag... featureFlags) {
        return registerBlock(type.name().toLowerCase(Locale.ROOT) + "_tnt", () -> new MWTntBlock(type, featureFlags));
    }

    /**
     * Register a {@link FallableTntBlock Fallable TNT Block}
     *
     * @param type {@link MWPrimedTnt.Type The TNT Type}
     * @param dustColor {@link Integer The falling block dust color}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerFallableTnt(final MWPrimedTnt.Type type, final int dustColor, final FeatureFlag... featureFlags) {
        return registerBlock(type.name().toLowerCase(Locale.ROOT) + "_tnt", () -> new FallableTntBlock(type, dustColor, featureFlags));
    }

    /**
     * Register an {@link HorizontalPaneBlock horizontal pane Block}
     *
     * @param name {@link String The Block name}
     * @param blockSupplier {@link Supplier<BlockState> The Supplier for the Block State this pane is based on}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerHorizontalPane(final String name, final Supplier<BlockState> blockSupplier) {
        return registerBlockWithoutBlockItem(name, Suppliers.memoize(() -> new HorizontalPaneBlock(PropertyHelper.copy(blockSupplier.get().getBlock()))));
    }

    /**
     * Register a {@link LeaveCarpet Leaves Carpet}
     *
     * @param woodType {@link WoodType The Wood Type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerLeaveCarpet(final WoodType woodType, final Supplier<Block> blockSupplier, final FeatureFlag... featureFlags) {
        return registerLeaveCarpet(ResourceHelper.woodName(woodType), blockSupplier, featureFlags);
    }

    /**
     * Register a {@link LeaveCarpet Leaves Carpet}
     *
     * @param woodName {@link String The Wood name}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerLeaveCarpet(final String woodName, final Supplier<Block> blockSupplier, final FeatureFlag... featureFlags) {
        return registerLeaveCarpet(woodName, "leaves", blockSupplier, featureFlags);
    }

    /**
     * Register a {@link LeaveCarpet Leaves Carpet}
     *
     * @param woodType {@link String The Wood name}
     * @param suffix {@link String The Block name suffix}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerLeaveCarpet(final WoodType woodType, final String suffix, final Supplier<Block> blockSupplier, final FeatureFlag... featureFlags) {
        return registerLeaveCarpet(ResourceHelper.woodName(woodType), suffix, blockSupplier, featureFlags);
    }

    /**
     * Register a {@link LeaveCarpet Leaves Carpet}
     *
     * @param woodName {@link String The Wood name}
     * @param suffix {@link String The Block name suffix}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerLeaveCarpet(final String woodName, final String suffix, final Supplier<Block> blockSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(woodName + "_" + suffix + "_carpet", Suppliers.memoize(() -> new LeaveCarpet(PropertyHelper.copy(blockSupplier.get(), featureFlags))));
    }

    /**
     * Register a {@link CarpetBlock Carpet}
     *
     * @param materialName {@link String The Block material name}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerCarpet(final String materialName, final Supplier<Block> blockSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_carpet", Suppliers.memoize(() -> new CarpetBlock(PropertyHelper.copy(blockSupplier.get(), featureFlags))));
    }

    /**
     * Register a {@link TreeBushBlock Bush Block}
     *
     * @param woodType {@link WoodType The Wood Type}
     * @param treeGrowerSupplier {@link Supplier<AbstractTreeGrower> The Supplier for the Tree Grower for this Bush}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerBush(final WoodType woodType, final Supplier<AbstractTreeGrower> treeGrowerSupplier, final FeatureFlag... featureFlags) {
        return registerBush(ResourceHelper.woodName(woodType), treeGrowerSupplier, featureFlags);
    }

    /**
     * Register a {@link TreeBushBlock Bush Block}
     *
     * @param woodName {@link String The Wood name}
     * @param treeGrowerSupplier {@link Supplier<AbstractTreeGrower> The Supplier for the Tree Grower for this Bush}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerBush(final String woodName, final Supplier<AbstractTreeGrower> treeGrowerSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(woodName + "_bush", Suppliers.memoize(() -> new TreeBushBlock(treeGrowerSupplier, featureFlags)));
    }

    /**
     * Register an {@link MWLogBlock Log Block}
     *
     * @param woodName {@link String The Wood name}
     * @param isStrippedLog {@link Boolean If is a stripped log}
     * @param woodTypeSupplier {@link WoodType The Supplier for the Wood Type}
     * @param isLog {@link Boolean If the Block is a Log Block}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerLog(final String woodName, final boolean isStrippedLog, final Supplier<WoodType> woodTypeSupplier, final boolean isLog) {
        return registerBlock((isStrippedLog ? "stripped_" : "") + woodName + (isLog ? "_log" : "_wood"), Suppliers.memoize(() -> new MWLogBlock(ResourceHelper.woodColor(woodTypeSupplier.get()))));
    }

    /**
     * Register an {@link HollowBlock Hollow Log}
     *
     * @param woodType {@link WoodType The Wood Type}
     * @param isStripped {@link Boolean If the Log is a Stripped Log}
     * @param blockStateSupplier {@link Supplier<Block> The Supplier for the Block State this Hollow Log is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerHollowLog(final WoodType woodType, final boolean isStripped, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerHollowLog(woodType, isStripped, "log", blockStateSupplier, featureFlags);
    }

    /**
     * Register an {@link HollowBlock Hollow Log}
     *
     * @param woodName {@link WoodType The Wood name}
     * @param isStripped {@link Boolean If the Log is a Stripped Log}
     * @param blockStateSupplier {@link Supplier<Block> The Supplier for the Block State this Hollow Log is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerHollowLog(final String woodName, final boolean isStripped, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerHollowLog(woodName, isStripped, "log", blockStateSupplier, featureFlags);
    }

    /**
     * Register an {@link HollowBlock Hollow Log}
     *
     * @param woodType {@link WoodType The Wood Type}
     * @param isStripped {@link Boolean If the Log is a Stripped Log}
     * @param suffix {@link String The block name suffix}
     * @param blockStateSupplier {@link Supplier<Block> The Supplier for the Block State this Hollow Log is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerHollowLog(final WoodType woodType, final boolean isStripped, final String suffix, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerHollowLog(woodType.name().toLowerCase(Locale.ROOT), isStripped, suffix, blockStateSupplier, featureFlags);
    }

    /**
     * Register an {@link HollowBlock Hollow Log}
     *
     * @param woodName {@link String The Wood name}
     * @param isStripped {@link Boolean If the Log is a Stripped Log}
     * @param suffix {@link String The block name suffix}
     * @param blockStateSupplier {@link Supplier<Block> The Supplier for the Block State this Hollow Log is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerHollowLog(final String woodName, final boolean isStripped, final String suffix, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerBlock("hollow_" + (isStripped ? "stripped_" : "") + woodName + "_" + suffix, Suppliers.memoize(() -> new HollowBlock(blockStateSupplier, featureFlags)));
    }

    /**
     * Register a {@link MWPlanksBlock Planks Block}
     *
     * @param woodName {@link String The Wood name}
     * @param woodTypeSupplier {@link Supplier<WoodType> The Wood Type Supplier}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerPlanks(final String woodName, final Supplier<WoodType> woodTypeSupplier) {
        return registerBlock(woodName + "_planks", Suppliers.memoize(() -> new MWPlanksBlock(woodTypeSupplier)));
    }

    /**
     * Register a {@link SaplingBlock Sapling Block}
     *
     * @param woodName {@link String The Wood name}
     * @param treeGrowerSupplier {@link Supplier<AbstractTreeGrower> The Tree Grower Supplier}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerSapling(final String woodName, final Supplier<AbstractTreeGrower> treeGrowerSupplier) {
        return registerBlock(woodName + "_sapling", Suppliers.memoize(() -> new SaplingBlock(treeGrowerSupplier.get(), PropertyHelper.copy(Blocks.OAK_SAPLING))));
    }

    /**
     * Register a {@link MWLeavesBlock Leaves Block}
     *
     * @param woodName {@link String The Wood name}
     * @param woodTypeSupplier {@link Supplier<WoodType> The Wood Type Supplier}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerLeaves(final String woodName, final Supplier<WoodType> woodTypeSupplier) {
        return registerBlock(woodName + "_leaves", Suppliers.memoize(() -> new MWLeavesBlock(woodTypeSupplier)));
    }

    /**
     * Register a {@link MWFenceBlock Fence Block}
     *
     * @param woodName {@link String The Wood name}
     * @param woodTypeSupplier {@link Supplier<WoodType> The Wood Type Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerFence(final String woodName, final Supplier<WoodType> woodTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(woodName + "_fence", Suppliers.memoize(() -> new MWFenceBlock(woodTypeSupplier, featureFlags)));
    }

    /**
     * Register a {@link MWFenceGateBlock Fence Gate Block}
     *
     * @param woodName {@link String The Wood name}
     * @param woodTypeSupplier {@link Supplier<WoodType> The Wood Type Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerFenceGate(final String woodName, final Supplier<WoodType> woodTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(woodName + "_fence_gate", Suppliers.memoize(() -> new MWFenceGateBlock(woodTypeSupplier, featureFlags)));
    }

    /**
     * Register a {@link MWStandingSignBlock Sign Block}
     *
     * @param woodName {@link String The Wood name}
     * @param woodTypeSupplier {@link Supplier<WoodType> The Wood Type Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerSign(final String woodName, final Supplier<WoodType> woodTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlockWithoutBlockItem(woodName + "_sign", Suppliers.memoize(() -> new MWStandingSignBlock(woodTypeSupplier, featureFlags)));
    }

    /**
     * Register a {@link MWWallSignBlock Wall Sign Block}
     *
     * @param woodName {@link String The Wood name}
     * @param woodTypeSupplier {@link Supplier<WoodType> The Wood Type Supplier}
     * @param signSupplier {@link Supplier<Block> The Supplier for the Standing Sign this Wall Sign is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerWallSign(final String woodName, final Supplier<WoodType> woodTypeSupplier, final Supplier<Block> signSupplier, final FeatureFlag... featureFlags) {
        return registerBlockWithoutBlockItem(woodName + "_wall_sign", Suppliers.memoize(() -> new MWWallSignBlock(woodTypeSupplier, signSupplier, featureFlags)));
    }

    /**
     * Register an {@link MWCeilingHangingSignBlock Hanging Sign Block}
     *
     * @param woodName {@link String The Wood name}
     * @param woodTypeSupplier {@link Supplier<WoodType> The Wood Type Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerHangingSign(final String woodName, final Supplier<WoodType> woodTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlockWithoutBlockItem(woodName + "_hanging_sign", Suppliers.memoize(() -> new MWCeilingHangingSignBlock(woodTypeSupplier, featureFlags)));
    }

    /**
     * Register a {@link MWWallHangingSignBlock Wall Hanging Sign Block}
     *
     * @param woodName {@link String The Wood name}
     * @param woodTypeSupplier {@link Supplier<WoodType> The Wood Type Supplier}
     * @param hangingSignSupplier {@link Supplier<Block> The Supplier for the Hanging Sign this Wall Hanging Sign is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerWallHangingSign(final String woodName, final Supplier<WoodType> woodTypeSupplier, final Supplier<Block> hangingSignSupplier, final FeatureFlag... featureFlags) {
        return registerBlockWithoutBlockItem(woodName + "_wall_hanging_sign", Suppliers.memoize(() -> new MWWallHangingSignBlock(woodTypeSupplier, hangingSignSupplier, featureFlags)));
    }

    /**
     * Register a {@link BarrelBlock Barrel Block}
     *
     * @param woodType {@link WoodType The Wood Type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerBarrel(final WoodType woodType, final FeatureFlag... featureFlags) {
        return registerBarrel(ResourceHelper.woodName(woodType), featureFlags);
    }

    /**
     * Register a {@link BarrelBlock Barrel Block}
     *
     * @param woodName {@link String The Wood name}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerBarrel(final String woodName, final FeatureFlag... featureFlags) {
        return registerBlock(woodName + "_barrel", Suppliers.memoize(() -> new BarrelBlock(PropertyHelper.copy(Blocks.BARREL, featureFlags))));
    }

    /**
     * Register a {@link MWChestBlock Chest Block}
     *
     * @param woodTypeSupplier {@link Supplier<WoodType> The Wood Type Supplier}
     * @param blockEntityTypeSupplier {@link Supplier<BlockEntityType> The Chest Block Entity Type Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerChest(final Supplier<WoodType> woodTypeSupplier, final Supplier<BlockEntityType<? extends ChestBlockEntity>> blockEntityTypeSupplier, final FeatureFlag... featureFlags) {
        return registerChest(ResourceHelper.woodName(woodTypeSupplier.get()), woodTypeSupplier, blockEntityTypeSupplier, featureFlags);
    }

    /**
     * Register a {@link MWChestBlock Chest Block}
     *
     * @param woodName {@link String The Wood name}
     * @param woodTypeSupplier {@link WoodType The Wood Type}
     * @param blockEntityTypeSupplier {@link Supplier<BlockEntityType> The Chest Block Entity Type Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerChest(final String woodName, final Supplier<WoodType> woodTypeSupplier, final Supplier<BlockEntityType<? extends ChestBlockEntity>> blockEntityTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlockWithoutBlockItem(woodName + "_chest", Suppliers.memoize(() -> new MWChestBlock(woodTypeSupplier, blockEntityTypeSupplier, featureFlags)));
    }

    /**
     * Register a {@link MWTrappedChestBlock Trapped Chest Block}
     *
     * @param woodTypeSupplier {@link Supplier<WoodType> The Wood Type Supplier}
     * @param blockEntityTypeSupplier {@link Supplier<BlockEntityType> The Chest Block Entity Type Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerTrappedChest(final Supplier<WoodType> woodTypeSupplier, final Supplier<BlockEntityType<? extends ChestBlockEntity>> blockEntityTypeSupplier, final FeatureFlag... featureFlags) {
        return registerTrappedChest(ResourceHelper.woodName(woodTypeSupplier.get()), woodTypeSupplier, blockEntityTypeSupplier, featureFlags);
    }

    /**
     * Register a {@link MWTrappedChestBlock Trapped Chest Block}
     *
     * @param woodName {@link String The Wood name}
     * @param woodTypeSupplier {@link WoodType The Wood Type}
     * @param blockEntityTypeSupplier {@link Supplier<BlockEntityType> The Chest Block Entity Type Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerTrappedChest(final String woodName, final Supplier<WoodType> woodTypeSupplier, final Supplier<BlockEntityType<? extends ChestBlockEntity>> blockEntityTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlockWithoutBlockItem(woodName + "_trapped_chest", Suppliers.memoize(() -> new MWTrappedChestBlock(woodTypeSupplier, blockEntityTypeSupplier, featureFlags)));
    }

    /**
     * Register a {@link MWBookshelfBlock Bookshelf Block}
     *
     * @param woodType {@link WoodType The Wood Type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerBookshelf(final WoodType woodType, final FeatureFlag... featureFlags) {
        return registerBookshelf(ResourceHelper.woodName(woodType), () -> woodType, featureFlags);
    }

    /**
     * Register a {@link MWBookshelfBlock Bookshelf Block}
     *
     * @param woodName {@link String The Bookshelf Wood name}
     * @param woodTypeSupplier {@link WoodType The Wood Type Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerBookshelf(final String woodName, final Supplier<WoodType> woodTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(woodName + "_bookshelf", Suppliers.memoize(() -> new MWBookshelfBlock(woodTypeSupplier, featureFlags)));
    }

    /**
     * Register a {@link ChiseledBookShelfBlock Chiseled Bookshelf Block}
     *
     * @param woodType {@link WoodType The Wood Type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerChiseledBookshelf(final WoodType woodType, final FeatureFlag... featureFlags) {
        return registerChiseledBookshelf(ResourceHelper.woodName(woodType), () -> woodType, featureFlags);
    }

    /**
     * Register a {@link ChiseledBookShelfBlock Chiseled Bookshelf Block}
     *
     * @param woodName {@link String The Bookshelf Wood name}
     * @param woodTypeSupplier {@link WoodType The Wood Type Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerChiseledBookshelf(final String woodName, final Supplier<WoodType> woodTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(woodName + "_chiseled_bookshelf", Suppliers.memoize(() -> new ChiseledBookShelfBlock(PropertyHelper.copy(Blocks.CHISELED_BOOKSHELF, featureFlags).sound(woodTypeSupplier.get().soundType()))));
    }

    /**
     * Register a {@link LecternBlock Lectern Block}
     *
     * @param woodType {@link WoodType The Wood Type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerLectern(final WoodType woodType, final FeatureFlag... featureFlags) {
        return registerLectern(ResourceHelper.woodName(woodType), () -> woodType, featureFlags);
    }

    /**
     * Register a {@link LecternBlock Lectern Block}
     *
     * @param woodName {@link String The Bookshelf Wood name}
     * @param woodTypeSupplier {@link WoodType The Wood Type Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerLectern(final String woodName, final Supplier<WoodType> woodTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(woodName + "_lectern", Suppliers.memoize(() -> new MWLecternBlock(woodTypeSupplier, featureFlags)));
    }

    /**
     * Register a {@link MWRodBlock Rod Block}
     *
     * @param materialName {@link String The Block material name}
     * @param color {@link MapColor The Block Color on maps}
     * @param sound {@link SoundType The Block Sound}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerRod(final String materialName, final MapColor color, final SoundType sound, final FeatureFlag... featureFlags) {
        return registerBlockWithoutBlockItem(materialName + "_rod_block", Suppliers.memoize(() -> new MWRodBlock(color, sound, featureFlags)));
    }

    /**
     * Register a {@link BaseCoralWallFanBlock Wall Fan Block}
     *
     * @param materialName {@link String The Block material name}
     * @param color {@link MapColor The Block Color on maps}
     * @param blockSupplier {@link Supplier<Block> The Supplier for the Block this Fan is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerWallFan(final String materialName, final MapColor color, final Supplier<Block> blockSupplier, final FeatureFlag... featureFlags) {
        return registerBlockWithoutBlockItem(materialName + "_wall_fan", Suppliers.memoize(() -> new BaseCoralWallFanBlock(PropertyHelper.block(color, 0F, 0F, true, SoundType.GRASS, featureFlags).noCollission().instabreak().lootFrom(blockSupplier))));
    }

    /**
     * Register a {@link MWTorchBlock Torch Block}
     *
     * @param fireType {@link MWFireBlock.MWFireType The Fire Type}
     * @param particleSupplier {@link Supplier<ParticleOptions> The Torch Particle Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerTorch(final MWFireBlock.MWFireType fireType, final Supplier<? extends ParticleOptions> particleSupplier, final FeatureFlag... featureFlags) {
        return registerBlockWithoutBlockItem(ResourceHelper.fireName(fireType) + "_torch", Suppliers.memoize(() -> new MWTorchBlock(fireType.lightLevel(), particleSupplier, featureFlags)));
    }

    /**
     * Register a {@link MWWallTorchBlock Wall Torch Block}
     *
     * @param fireType {@link MWFireBlock.MWFireType The Fire Type}
     * @param particleSupplier {@link Supplier<ParticleOptions> The Torch Particle Supplier}
     * {@link Supplier<Block> The Supplier for the Torch Block}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerWallTorch(final MWFireBlock.MWFireType fireType, final Supplier<? extends ParticleOptions> particleSupplier, final Supplier<Block> blockSupplier, final FeatureFlag... featureFlags) {
        return registerBlockWithoutBlockItem(ResourceHelper.fireName(fireType) + "_wall_torch", Suppliers.memoize(() -> new MWWallTorchBlock(fireType.lightLevel(), particleSupplier, blockSupplier, featureFlags)));
    }

    /**
     * Register an {@link UnlitTorchBlock Unlit Torch Block}
     *
     * @param materialName {@link String The Block material name}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerUnlitTorch(final String materialName, final FeatureFlag... featureFlags) {
        return registerBlockWithoutBlockItem("unlit_" + materialName + (materialName.isEmpty() ? "" : "_") + "torch", Suppliers.memoize(() -> new UnlitTorchBlock(featureFlags)));
    }

    /**
     * Register a {@link UnlitWallTorchBlock Unlit Wall Torch Block}
     *
     * @param materialName {@link String The Block material name}
     * @param blockSupplier {@link Supplier<Block> The Supplier for the Torch Block}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerUnlitWallTorch(final String materialName, final Supplier<Block> blockSupplier, final FeatureFlag... featureFlags) {
        return registerBlockWithoutBlockItem("unlit_" + materialName + (materialName.isEmpty() ? "" : "_") + "wall_torch", Suppliers.memoize(() -> new UnlitWallTorchBlock(blockSupplier, featureFlags)));
    }

    /**
     * Register a {@link MWFireBlock Fire Block}
     *
     * @param type {@link MWFireBlock.MWFireType The Fire Type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerFireBlock(final MWFireBlock.MWFireType type, final FeatureFlag... featureFlags) {
        return registerBlockWithoutBlockItem(type.name().toLowerCase(Locale.ROOT) + "_fire", Suppliers.memoize(() -> new MWFireBlock(type, featureFlags)));
    }

    /**
     * Register a {@link MWCampfireBlock Campfire Block}
     *
     * @param type {@link MWFireBlock.MWFireType The Fire Type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerCampfireBlock(final MWFireBlock.MWFireType type, final FeatureFlag... featureFlags) {
        return registerBlock(type.name().toLowerCase(Locale.ROOT) + "_campfire", Suppliers.memoize(() -> new MWCampfireBlock(type, featureFlags)));
    }

    /**
     * Register a {@link Block Block} given its {@link BlockBehaviour.Properties Properties}
     *
     * @param name {@link String The Block name}
     * @param propertiesSupplier {@link Supplier<BlockBehaviour.Properties> The Block Properties Supplier}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerBlock(final String name, final Supplier<BlockBehaviour.Properties> propertiesSupplier) {
        return registerBlock(name, () -> new Block(propertiesSupplier.get()));
    }

    /**
     * Register a {@link Block Block} without registering its {@link BlockItem Block Item}
     *
     * @param name {@link String The Block name}
     * @param blockSupplier {@link Supplier The Block Supplier}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerBlockWithoutBlockItem(final String name, final Supplier<? extends Block> blockSupplier) {
        return BLOCKS.register(name, blockSupplier);
    }

    /**
     * Register a {@link Block Block}
     *
     * @param name {@link String The Block name}
     * @param blockSupplier {@link Supplier The Block Supplier}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerBlock(final String name, final Supplier<? extends Block> blockSupplier, final FeatureFlag... featureFlags) {
        final RegistryObject<Block> block = registerBlockWithoutBlockItem(name, blockSupplier);
        MWItems.registerBlockItem(name, block, featureFlags);
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
        MWCopperBlocks.register();
        MWPointedDripstones.register();
        MWPebbles.PebbleBlocks.register();
        MWFlowerPots.register();
        BLOCKS.register(eventBus);
    }

    //#endregion

}