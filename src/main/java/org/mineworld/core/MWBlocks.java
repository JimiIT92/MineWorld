package org.mineworld.core;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.block.CoralFlowerPotBlock;
import org.mineworld.block.CornBlock;

import java.util.HashMap;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link Block blocks}
 */
public final class MWBlocks {

    /**
     * {@link MineWorld MineWorld} flower pots. The key represents the {@link Block flower block}, the value is the {@link Block potted flower block}
     */
    private static final HashMap<Supplier<? extends Block>, RegistryObject<Block>> flowerPots = new HashMap<>();
    /**
     * {@link DeferredRegister<Block> The block registry}
     */
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MineWorld.MODID);

    //#region Blocks

    public static final RegistryObject<Block> SILVER_ORE = registerOverworldOreBlock("silver_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = registerOverworldOreBlock("deepslate_silver_ore", true);
    public static final RegistryObject<Block> ALUMINUM_ORE = registerOverworldOreBlock("aluminum_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_ALUMINUM_ORE = registerOverworldOreBlock("deepslate_aluminum_ore", true);
    public static final RegistryObject<Block> RUBY_ORE = registerOverworldOreBlock("ruby_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_RUBY_ORE = registerOverworldOreBlock("deepslate_ruby_ore", true);
    public static final RegistryObject<Block> SAPPHIRE_ORE = registerOverworldOreBlock("sapphire_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = registerOverworldOreBlock("deepslate_sapphire_ore", true);
    public static final RegistryObject<Block> PYRITE_ORE = registerNetherOreBlock("pyrite_ore");
    public static final RegistryObject<Block> RAW_SILVER_BLOCK = registerRawOreBlock("raw_silver_block", MWColors.RAW_SILVER.toMaterialColor());
    public static final RegistryObject<Block> RAW_ALUMINUM_BLOCK = registerRawOreBlock("raw_aluminum_block", MWColors.RAW_ALUMINUM.toMaterialColor());
    public static final RegistryObject<Block> RAW_BRONZE_BLOCK = registerRawOreBlock("raw_bronze_block", MWColors.RAW_BRONZE.toMaterialColor());
    public static final RegistryObject<Block> SILVER_BLOCK = registerMetalOreStorageBlock("silver_block", MWColors.SILVER.toMaterialColor());
    public static final RegistryObject<Block> ALUMINUM_BLOCK = registerMetalOreStorageBlock("aluminum_block", MWColors.ALUMINUM.toMaterialColor());
    public static final RegistryObject<Block> BRONZE_BLOCK = registerSimpleBlock("bronze_block", basicBlockProperties(Material.METAL, MWColors.BRONZE.toMaterialColor(),3.0F, 6.0F, true, SoundType.COPPER));
    public static final RegistryObject<Block> RUBY_BLOCK = registerMetalOreStorageBlock("ruby_block", MWColors.RUBY.toMaterialColor());
    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerMetalOreStorageBlock("sapphire_block", MWColors.SAPPHIRE.toMaterialColor());
    public static final RegistryObject<Block> PYRITE_BLOCK = registerFuelBlock("pyrite_block", MWColors.PYRITE.toMaterialColor(), 1200);
    public static final RegistryObject<Block> CHARCOAL_BLOCK = registerFuelBlock("charcoal_block", MWColors.CHARCOAL.toMaterialColor(), 800);
    public static final RegistryObject<Block> MARBLE = registerBlock("marble", () -> new Block(basicBlockProperties(Material.STONE, MWColors.MARBLE.toMaterialColor(), 1.5F, 6.0F, true, SoundType.CALCITE)));
    public static final RegistryObject<Block> WHITE_MARBLE = registerBlock("white_marble", () -> new Block(BlockBehaviour.Properties.copy(MARBLE.get())));
    public static final RegistryObject<Block> ORANGE_MARBLE = registerBlock("orange_marble", () -> new Block(BlockBehaviour.Properties.copy(MARBLE.get())));
    public static final RegistryObject<Block> MAGENTA_MARBLE = registerBlock("magenta_marble", () -> new Block(BlockBehaviour.Properties.copy(MARBLE.get())));
    public static final RegistryObject<Block> LIGHT_BLUE_MARBLE = registerBlock("light_blue_marble", () -> new Block(BlockBehaviour.Properties.copy(MARBLE.get())));
    public static final RegistryObject<Block> YELLOW_MARBLE = registerBlock("yellow_marble", () -> new Block(BlockBehaviour.Properties.copy(MARBLE.get())));
    public static final RegistryObject<Block> LIME_MARBLE = registerBlock("lime_marble", () -> new Block(BlockBehaviour.Properties.copy(MARBLE.get())));
    public static final RegistryObject<Block> PINK_MARBLE = registerBlock("pink_marble", () -> new Block(BlockBehaviour.Properties.copy(MARBLE.get())));
    public static final RegistryObject<Block> GRAY_MARBLE = registerBlock("gray_marble", () -> new Block(BlockBehaviour.Properties.copy(MARBLE.get())));
    public static final RegistryObject<Block> LIGHT_GRAY_MARBLE = registerBlock("light_gray_marble", () -> new Block(BlockBehaviour.Properties.copy(MARBLE.get())));
    public static final RegistryObject<Block> CYAN_MARBLE = registerBlock("cyan_marble", () -> new Block(BlockBehaviour.Properties.copy(MARBLE.get())));
    public static final RegistryObject<Block> PURPLE_MARBLE = registerBlock("purple_marble", () -> new Block(BlockBehaviour.Properties.copy(MARBLE.get())));
    public static final RegistryObject<Block> BLUE_MARBLE = registerBlock("blue_marble", () -> new Block(BlockBehaviour.Properties.copy(MARBLE.get())));
    public static final RegistryObject<Block> BROWN_MARBLE = registerBlock("brown_marble", () -> new Block(BlockBehaviour.Properties.copy(MARBLE.get())));
    public static final RegistryObject<Block> GREEN_MARBLE = registerBlock("green_marble", () -> new Block(BlockBehaviour.Properties.copy(MARBLE.get())));
    public static final RegistryObject<Block> RED_MARBLE = registerBlock("red_marble", () -> new Block(BlockBehaviour.Properties.copy(MARBLE.get())));
    public static final RegistryObject<Block> BLACK_MARBLE = registerBlock("black_marble", () -> new Block(BlockBehaviour.Properties.copy(MARBLE.get())));
    public static final RegistryObject<Block> BLUE_ROSE = registerFlower("blue_rose", () -> MobEffects.SATURATION);
    public static final RegistryObject<Block> POTTED_BLUE_ROSE = registerFlowerPot("potted_blue_rose", BLUE_ROSE);
    public static final RegistryObject<Block> BLUE_ROSE_BUSH = registerTallFlower("blue_rose_bush");
    public static final RegistryObject<Block> POTTED_BLUE_ROSE_BUSH = registerFlowerPot("potted_blue_rose_bush", BLUE_ROSE_BUSH);
    public static final RegistryObject<Block> WHITE_ROSE = registerFlower("white_rose", () -> MobEffects.HEAL);
    public static final RegistryObject<Block> POTTED_WHITE_ROSE = registerFlowerPot("potted_white_rose", WHITE_ROSE);
    public static final RegistryObject<Block> WHITE_ROSE_BUSH = registerTallFlower("white_rose_bush");
    public static final RegistryObject<Block> POTTED_WHITE_ROSE_BUSH = registerFlowerPot("potted_white_rose_bush", WHITE_ROSE_BUSH);
    public static final RegistryObject<Block> POTTED_OAK_LEAVES = registerFlowerPot("potted_oak_leaves", () -> Blocks.OAK_LEAVES);
    public static final RegistryObject<Block> POTTED_SPRUCE_LEAVES = registerFlowerPot("potted_spruce_leaves", () -> Blocks.SPRUCE_LEAVES);
    public static final RegistryObject<Block> POTTED_BIRCH_LEAVES = registerFlowerPot("potted_birch_leaves", () -> Blocks.BIRCH_LEAVES);
    public static final RegistryObject<Block> POTTED_JUNGLE_LEAVES = registerFlowerPot("potted_jungle_leaves", () -> Blocks.JUNGLE_LEAVES);
    public static final RegistryObject<Block> POTTED_ACACIA_LEAVES = registerFlowerPot("potted_acacia_leaves", () -> Blocks.ACACIA_LEAVES);
    public static final RegistryObject<Block> POTTED_CHERRY_LEAVES = registerFlowerPot("potted_cherry_leaves", () -> Blocks.CHERRY_LEAVES, FeatureFlags.UPDATE_1_20);
    public static final RegistryObject<Block> POTTED_DARK_OAK_LEAVES = registerFlowerPot("potted_dark_oak_leaves", () -> Blocks.DARK_OAK_LEAVES);
    public static final RegistryObject<Block> POTTED_MANGROVE_LEAVES = registerFlowerPot("potted_mangrove_leaves", () -> Blocks.MANGROVE_LEAVES);
    public static final RegistryObject<Block> POTTED_AZALEA_LEAVES = registerFlowerPot("potted_azalea_leaves", () -> Blocks.AZALEA_LEAVES);
    public static final RegistryObject<Block> POTTED_FLOWERING_AZALEA_LEAVES = registerFlowerPot("potted_flowering_azalea_leaves", () -> Blocks.FLOWERING_AZALEA_LEAVES);
    public static final RegistryObject<Block> POTTED_MANGROVE_ROOTS = registerFlowerPot("potted_mangrove_roots", () -> Blocks.MANGROVE_ROOTS);
    public static final RegistryObject<Block> POTTED_MUDDY_MANGROVE_ROOTS = registerFlowerPot("potted_muddy_mangrove_roots", () -> Blocks.MUDDY_MANGROVE_ROOTS);
    public static final RegistryObject<Block> POTTED_BROWN_MUSHROOM_BLOCK = registerFlowerPot("potted_brown_mushroom_block", () -> Blocks.BROWN_MUSHROOM_BLOCK);
    public static final RegistryObject<Block> POTTED_RED_MUSHROOM_BLOCK = registerFlowerPot("potted_red_mushroom_block", () -> Blocks.RED_MUSHROOM_BLOCK);
    public static final RegistryObject<Block> POTTED_NETHER_WART_BLOCK = registerFlowerPot("potted_nether_wart_block", () -> Blocks.NETHER_WART_BLOCK);
    public static final RegistryObject<Block> POTTED_WARPED_WART_BLOCK = registerFlowerPot("potted_warped_wart_block", () -> Blocks.WARPED_WART_BLOCK);
    public static final RegistryObject<Block> POTTED_SHROOMLIGHT = registerLitFlowerPot("potted_shroomlight", () -> Blocks.SHROOMLIGHT, 5);
    public static final RegistryObject<Block> POTTED_GRASS = registerFlowerPot("potted_grass", () -> Blocks.GRASS);
    public static final RegistryObject<Block> POTTED_SUGAR_CANE = registerFlowerPot("potted_sugar_cane", () -> Blocks.SUGAR_CANE);
    public static final RegistryObject<Block> POTTED_SEAGRASS = registerFlowerPot("potted_seagrass", () -> Blocks.SEAGRASS);
    public static final RegistryObject<Block> POTTED_TALL_GRASS = registerFlowerPot("potted_tall_grass", () -> Blocks.TALL_GRASS);
    public static final RegistryObject<Block> POTTED_LARGE_FERN = registerFlowerPot("potted_large_fern", () -> Blocks.LARGE_FERN);
    public static final RegistryObject<Block> POTTED_SUNFLOWER = registerFlowerPot("potted_sunflower", () -> Blocks.SUNFLOWER);
    public static final RegistryObject<Block> POTTED_LILAC = registerFlowerPot("potted_lilac", () -> Blocks.LILAC);
    public static final RegistryObject<Block> POTTED_ROSE_BUSH = registerFlowerPot("potted_rose_bush", () -> Blocks.ROSE_BUSH);
    public static final RegistryObject<Block> POTTED_PEONY = registerFlowerPot("potted_peony", () -> Blocks.PEONY);
    public static final RegistryObject<Block> POTTED_KELP = registerFlowerPot("potted_kelp", () -> Blocks.KELP);
    public static final RegistryObject<Block> POTTED_BIG_DRIPLEAF = registerFlowerPot("potted_big_dripleaf", () -> Blocks.BIG_DRIPLEAF);
    public static final RegistryObject<Block> POTTED_SMALL_DRIPLEAF = registerFlowerPot("potted_small_dripleaf", () -> Blocks.SMALL_DRIPLEAF);
    public static final RegistryObject<Block> POTTED_CHORUS_PLANT = registerFlowerPot("potted_chorus_plant", () -> Blocks.CHORUS_PLANT);
    public static final RegistryObject<Block> POTTED_CHORUS_FLOWER = registerFlowerPot("potted_chorus_flower", () -> Blocks.CHORUS_FLOWER);
    public static final RegistryObject<Block> POTTED_SWEET_BERRY_BUSH = registerFlowerPot("potted_sweet_berry_bush", () -> Blocks.SWEET_BERRY_BUSH);
    public static final RegistryObject<Block> POTTED_CAVE_VINES = registerLitFlowerPot("potted_cave_vines", () -> Blocks.CAVE_VINES, 4);
    public static final RegistryObject<Block> POTTED_OCHRE_FROGLIGHT = registerLitFlowerPot("potted_ochre_froglight", () -> Blocks.OCHRE_FROGLIGHT, 5);
    public static final RegistryObject<Block> POTTED_VERDANT_FROGLIGHT = registerLitFlowerPot("potted_verdant_froglight", () -> Blocks.VERDANT_FROGLIGHT, 5);
    public static final RegistryObject<Block> POTTED_PEARLESCENT_FROGLIGHT = registerLitFlowerPot("potted_pearlescent_froglight", () -> Blocks.PEARLESCENT_FROGLIGHT, 5);
    public static final RegistryObject<Block> POTTED_DEAD_TUBE_CORAL_BLOCK = registerFlowerPot("potted_dead_tube_coral_block", () -> Blocks.DEAD_TUBE_CORAL_BLOCK);
    public static final RegistryObject<Block> POTTED_TUBE_CORAL_BLOCK = registerCoralFlowerPot("potted_tube_coral_block", POTTED_DEAD_TUBE_CORAL_BLOCK, () -> Blocks.TUBE_CORAL_BLOCK);
    public static final RegistryObject<Block> POTTED_DEAD_BRAIN_CORAL_BLOCK = registerFlowerPot("potted_dead_brain_coral_block", () -> Blocks.DEAD_BRAIN_CORAL_BLOCK);
    public static final RegistryObject<Block> POTTED_BRAIN_CORAL_BLOCK = registerCoralFlowerPot("potted_brain_coral_block", POTTED_DEAD_BRAIN_CORAL_BLOCK, () -> Blocks.BRAIN_CORAL_BLOCK);
    public static final RegistryObject<Block> POTTED_DEAD_BUBBLE_CORAL_BLOCK = registerFlowerPot("potted_dead_bubble_coral_block", () -> Blocks.DEAD_BUBBLE_CORAL_BLOCK);
    public static final RegistryObject<Block> POTTED_BUBBLE_CORAL_BLOCK = registerCoralFlowerPot("potted_bubble_coral_block", POTTED_DEAD_BUBBLE_CORAL_BLOCK, () -> Blocks.BUBBLE_CORAL_BLOCK);
    public static final RegistryObject<Block> POTTED_DEAD_FIRE_CORAL_BLOCK = registerFlowerPot("potted_dead_fire_coral_block", () -> Blocks.DEAD_FIRE_CORAL_BLOCK);
    public static final RegistryObject<Block> POTTED_FIRE_CORAL_BLOCK = registerCoralFlowerPot("potted_fire_coral_block", POTTED_DEAD_FIRE_CORAL_BLOCK, () -> Blocks.FIRE_CORAL_BLOCK);
    public static final RegistryObject<Block> POTTED_DEAD_HORN_CORAL_BLOCK = registerFlowerPot("potted_dead_horn_coral_block", () -> Blocks.DEAD_HORN_CORAL_BLOCK);
    public static final RegistryObject<Block> POTTED_HORN_CORAL_BLOCK = registerCoralFlowerPot("potted_horn_coral_block", POTTED_DEAD_HORN_CORAL_BLOCK, () -> Blocks.HORN_CORAL_BLOCK);
    public static final RegistryObject<Block> POTTED_DEAD_TUBE_CORAL = registerFlowerPot("potted_dead_tube_coral", () -> Blocks.DEAD_TUBE_CORAL);
    public static final RegistryObject<Block> POTTED_TUBE_CORAL = registerCoralFlowerPot("potted_tube_coral", POTTED_DEAD_TUBE_CORAL, () -> Blocks.TUBE_CORAL);
    public static final RegistryObject<Block> POTTED_DEAD_BRAIN_CORAL = registerFlowerPot("potted_dead_brain_coral", () -> Blocks.DEAD_BRAIN_CORAL);
    public static final RegistryObject<Block> POTTED_BRAIN_CORAL = registerCoralFlowerPot("potted_brain_coral", POTTED_DEAD_BRAIN_CORAL, () -> Blocks.BRAIN_CORAL);
    public static final RegistryObject<Block> POTTED_DEAD_BUBBLE_CORAL = registerFlowerPot("potted_dead_bubble_coral", () -> Blocks.DEAD_BUBBLE_CORAL);
    public static final RegistryObject<Block> POTTED_BUBBLE_CORAL = registerCoralFlowerPot("potted_bubble_coral", POTTED_DEAD_BUBBLE_CORAL, () -> Blocks.BUBBLE_CORAL);
    public static final RegistryObject<Block> POTTED_DEAD_FIRE_CORAL = registerFlowerPot("potted_dead_fire_coral", () -> Blocks.DEAD_FIRE_CORAL);
    public static final RegistryObject<Block> POTTED_FIRE_CORAL = registerCoralFlowerPot("potted_fire_coral", POTTED_DEAD_FIRE_CORAL, () -> Blocks.FIRE_CORAL);
    public static final RegistryObject<Block> POTTED_DEAD_HORN_CORAL = registerFlowerPot("potted_dead_horn_coral", () -> Blocks.DEAD_HORN_CORAL);
    public static final RegistryObject<Block> POTTED_HORN_CORAL = registerCoralFlowerPot("potted_horn_coral", POTTED_DEAD_HORN_CORAL, () -> Blocks.HORN_CORAL);
    public static final RegistryObject<Block> POTTED_DEAD_TUBE_CORAL_FAN = registerFlowerPot("potted_dead_tube_coral_fan", () -> Blocks.DEAD_TUBE_CORAL_FAN);
    public static final RegistryObject<Block> POTTED_TUBE_CORAL_FAN = registerCoralFlowerPot("potted_tube_coral_fan", POTTED_DEAD_TUBE_CORAL_FAN, () -> Blocks.TUBE_CORAL_FAN);
    public static final RegistryObject<Block> POTTED_DEAD_BRAIN_CORAL_FAN = registerFlowerPot("potted_dead_brain_coral_fan", () -> Blocks.DEAD_BRAIN_CORAL_FAN);
    public static final RegistryObject<Block> POTTED_BRAIN_CORAL_FAN = registerCoralFlowerPot("potted_brain_coral_fan", POTTED_DEAD_BRAIN_CORAL_FAN, () -> Blocks.BRAIN_CORAL_FAN);
    public static final RegistryObject<Block> POTTED_DEAD_BUBBLE_CORAL_FAN = registerFlowerPot("potted_dead_bubble_coral_fan", () -> Blocks.DEAD_BUBBLE_CORAL_FAN);
    public static final RegistryObject<Block> POTTED_BUBBLE_CORAL_FAN = registerCoralFlowerPot("potted_bubble_coral_fan", POTTED_DEAD_BUBBLE_CORAL_FAN, () -> Blocks.BUBBLE_CORAL_FAN);
    public static final RegistryObject<Block> POTTED_DEAD_FIRE_CORAL_FAN = registerFlowerPot("potted_dead_fire_coral_fan", () -> Blocks.DEAD_FIRE_CORAL_FAN);
    public static final RegistryObject<Block> POTTED_FIRE_CORAL_FAN = registerCoralFlowerPot("potted_fire_coral_fan", POTTED_DEAD_FIRE_CORAL_FAN, () -> Blocks.FIRE_CORAL_FAN);
    public static final RegistryObject<Block> POTTED_DEAD_HORN_CORAL_FAN = registerFlowerPot("potted_dead_horn_coral_fan", () -> Blocks.DEAD_HORN_CORAL_FAN);
    public static final RegistryObject<Block> POTTED_HORN_CORAL_FAN = registerCoralFlowerPot("potted_horn_coral_fan", POTTED_DEAD_HORN_CORAL_FAN, () -> Blocks.HORN_CORAL_FAN);
    public static final RegistryObject<Block> CORN = registerBlockWithoutBlockItem("corn", CornBlock::new);

    //#endregion

    /**
     * Create some basic {@link BlockBehaviour.Properties block properties}
     * using the {@link Material material color} and the base {@link SoundType block sound}
     *
     * @param material {@link Material The block material}
     * @param hardness {@link Float The block hardness}
     * @param blastResistance {@link Float The block blast restitance}
     * @param requiresTool {@link Boolean If the block requires the correct tool to drop}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    private static BlockBehaviour.Properties basicBlockProperties(final Material material, final float hardness, final float blastResistance, final boolean requiresTool, final FeatureFlag... featureFlags) {
        return basicBlockProperties(material, material.getColor(), hardness, blastResistance, requiresTool, featureFlags);
    }

    /**
     * Create some basic {@link BlockBehaviour.Properties block properties}
     * using the provided {@link MaterialColor color} and the base {@link SoundType block sound}
     *
     * @param material {@link Material The block material}
     * @param color {@link MaterialColor The block color on maps}
     * @param hardness {@link Float The block hardness}
     * @param blastResistance {@link Float The block blast restitance}
     * @param requiresTool {@link Boolean If the block requires the correct tool to drop}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    private static BlockBehaviour.Properties basicBlockProperties(final Material material, final MaterialColor color, final float hardness, final float blastResistance, final boolean requiresTool, final FeatureFlag... featureFlags) {
        return basicBlockProperties(material, color, hardness, blastResistance, requiresTool, null, featureFlags);
    }

    /**
     * Create some basic {@link BlockBehaviour.Properties block properties}
     *
     * @param material {@link Material The block material}
     * @param color {@link MaterialColor The block color on maps}
     * @param hardness {@link Float The block hardness}
     * @param blastResistance {@link Float The block blast restitance}
     * @param requiresTool {@link Boolean If the block requires the correct tool to drop}
     * @param sound {@link SoundType The block sound}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    private static BlockBehaviour.Properties basicBlockProperties(final Material material, final MaterialColor color, final float hardness, final float blastResistance, final boolean requiresTool, final SoundType sound, final FeatureFlag... featureFlags) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of(material, color).strength(hardness, blastResistance);
        if(requiresTool) {
            properties = properties.requiresCorrectToolForDrops();
        }
        if(sound != null) {
            properties = properties.sound(sound);
        }
        if(featureFlags != null) {
            properties = properties.requiredFeatures(featureFlags);
        }
        return properties;
    }

    /**
     * Create the {@link BlockBehaviour.Properties block properties} for an {@link Block ore block}
     *
     * @param isDeepslateOre {@link Boolean Whether the ore is a deepslate ore}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The ore block properties}
     */
    private static BlockBehaviour.Properties oreBlockProperties(final boolean isDeepslateOre, final FeatureFlag... featureFlags) {
        return basicBlockProperties(Material.STONE,
                isDeepslateOre ? MaterialColor.DEEPSLATE : Material.STONE.getColor(),
                isDeepslateOre ? 4.5F : 3.0F, 3.0F,
                true,
                isDeepslateOre ? SoundType.DEEPSLATE : null,
                featureFlags);
    }

    /**
     * Register an {@link Block ore block}
     *
     * @param name {@link String The block Name}
     * @param isDeepslateOre {@link Boolean Whether the ore is a deepslate ore}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerOverworldOreBlock(final String name, final boolean isDeepslateOre, final FeatureFlag... featureFlags) {
        return registerOreBlock(name, oreBlockProperties(isDeepslateOre, featureFlags), 3, 7);
    }

    /**
     * Register a Nether {@link Block ore block}
     *
     * @param name {@link String The block Name}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerNetherOreBlock(final String name, final FeatureFlag... featureFlags) {
        return registerOreBlock(name, oreBlockProperties(false, featureFlags).color(MaterialColor.NETHER).sound(SoundType.NETHER_ORE), 2, 5);
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
    private static RegistryObject<Block> registerOreBlock(final String name, final BlockBehaviour.Properties blockProperties, final int minXp, final int maxXp) {
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
    private static RegistryObject<Block> registerRawOreBlock(final String name, final MaterialColor color, final FeatureFlag... featureFlags) {
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
    private static RegistryObject<Block> registerMetalOreStorageBlock(final String name, MaterialColor color, final FeatureFlag... featureFlags) {
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
    private static RegistryObject<Block> registerOreStorageBlock(final String name, final Material material, final MaterialColor color, final SoundType sound, final FeatureFlag... featureFlags) {
        return registerSimpleBlock(name, basicBlockProperties(Material.STONE, color,5.0F, 6.0F, true, sound, featureFlags));
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
    private static RegistryObject<Block> registerFuelBlock(final String name, final MaterialColor color, final int burnTime, final FeatureFlag... featureFlags) {
        RegistryObject<Block> block = registerBlockWithoutBlockItem(name, () -> new Block(basicBlockProperties(Material.STONE, color, 5.0F, 6.0F, true, featureFlags)));
        MWItems.registerFuelBlockItem(name, block, burnTime, featureFlags);
        return block;
    }

    /**
     * Copy the {@link BlockBehaviour.Properties block properties} of an existing block
     * and makes them required by the provided {@link FeatureFlag feature flags}
     *
     * @param block {@link Block The block to copy the properties from}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    public static BlockBehaviour.Properties copyFrom(final Block block, final FeatureFlag... featureFlags) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.copy(block);
        if(featureFlags != null) {
            properties = properties.requiredFeatures(featureFlags);
        }
        return properties;
    }

    /**
     * Register a {@link FlowerBlock flower block}
     *
     * @param name {@link String The block name}
     * @param effectSupplier {@link Supplier<MobEffect> The flower effect when used in suspicious stews}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerFlower(final String name, final Supplier<MobEffect> effectSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new FlowerBlock(effectSupplier, 5, copyFrom(Blocks.POPPY, featureFlags)));
    }

    /**
     * Register a {@link TallFlowerBlock tall flower block}
     *
     * @param name {@link String The block name}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerTallFlower(final String name, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new TallFlowerBlock(copyFrom(Blocks.ROSE_BUSH, featureFlags)));
    }

    /**
     * Register a {@link FlowerPotBlock flower pot block}
     * that can emit light
     *
     * @param name {@link String The block name}
     * @param flowerSupplier {@link Supplier<Block> The flower this pot is referring to}
     * @param light {@link Integer The block light level}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this block to be registered}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerLitFlowerPot(final String name, final Supplier<? extends Block> flowerSupplier, final int light, final FeatureFlag... featureFlags) {
        return registerFlowerPot(name, flowerSupplier, copyFrom(Blocks.FLOWER_POT, featureFlags).lightLevel(state -> light));
    }

    /**
     * Register a {@link FlowerPotBlock flower pot block}
     *
     * @param name {@link String The block name}
     * @param flowerSupplier {@link Supplier<Block> The flower this pot is referring to}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this block to be registered}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerFlowerPot(final String name, final Supplier<? extends Block> flowerSupplier, final FeatureFlag... featureFlags) {
        return registerFlowerPot(name, flowerSupplier, copyFrom(Blocks.FLOWER_POT, featureFlags));
    }

    /**
     * Register a {@link FlowerPotBlock flower pot block}
     *
     * @param name {@link String The block name}
     * @param flowerSupplier {@link Supplier<Block> The flower this pot is referring to}
     * @param properties {@link BlockBehaviour.Properties The block properties}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerFlowerPot(final String name, final Supplier<? extends Block> flowerSupplier, final BlockBehaviour.Properties properties) {
        final RegistryObject<Block> block = registerBlockWithoutBlockItem(name, () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, flowerSupplier, properties));
        flowerPots.put(flowerSupplier, block);
        return block;
    }

    /**
     * Register a {@link CoralFlowerPotBlock coral flower pot block}
     *
     * @param name {@link String The block name}
     * @param deadCoralFlowerPot {@link Supplier<Block> The dead coral flower pot variant}
     * @param coralSupplier {@link Supplier<Block> The coral this pot is referring to}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this block to be registered}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerCoralFlowerPot(final String name, final Supplier<? extends Block> deadCoralFlowerPot, final Supplier<? extends Block> coralSupplier, final FeatureFlag... featureFlags) {
        final RegistryObject<Block> block = registerBlockWithoutBlockItem(name, () -> new CoralFlowerPotBlock(deadCoralFlowerPot, coralSupplier, featureFlags));
        flowerPots.put(coralSupplier, block);
        return block;
    }

    /**
     * Register a {@link Block block} using the provided {@link Material color} and {@link SoundType block sound}
     *
     * @param name {@link String The block name}
     * @param properties {@link BlockBehaviour.Properties The block properties}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerSimpleBlock(final String name, final BlockBehaviour.Properties properties) {
        return registerBlock(name, () -> new Block(properties));
    }

    /**
     * Register a {@link Block block}
     *
     * @param name {@link String The block name}
     * @param blockSupplier {@link Supplier<Block> The block supplier}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this block to be registered}
     * @param <T> The block type
     * @return {@link RegistryObject<T> The registered block}
     */
    private static <T extends Block> RegistryObject<T> registerBlock(final String name, final Supplier<T> blockSupplier, final FeatureFlag... featureFlags) {
        RegistryObject<T> block = registerBlockWithoutBlockItem(name, blockSupplier);
        registerBlockItem(name, block, featureFlags);
        return block;
    }

    /**
     * Register a {@link Block block} without also registering a {@link BlockItem block item}
     *
     * @param name {@link String The block name}
     * @param blockSupplier {@link Supplier<Block> The block supplier}
     * @param <T> The block type
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(final String name, final Supplier<T> blockSupplier) {
        return BLOCKS.register(name, blockSupplier);
    }

    /**
     * Register a {@link BlockItem block item}
     *
     * @param name  {@link String The block name}
     * @param block {@link RegistryObject<T> The block}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this block to be registered}
     * @param <T> The block type
     */
    private static <T extends Block> void registerBlockItem(final String name, final RegistryObject<T> block, final FeatureFlag... featureFlags) {
        MWItems.registerItem(name, () -> new BlockItem(block.get(), MWItems.basicProperties(featureFlags)));
    }

    /**
     * Register the {@link MineWorld MineWorld} flower pots
     */
    public static void registerFlowerPots() {
        final FlowerPotBlock flowerPot = (FlowerPotBlock) Blocks.FLOWER_POT;
        flowerPots.forEach((flower, pot) -> flowerPot.addPlant(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(flower.get())), pot));
    }

    /**
     * Register the {@link MineWorld MineWorld} {@link Block blocks}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(final IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
