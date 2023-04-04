package org.mineworld.core;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link Block blocks}
 */
public final class MWBlocks {

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
    public static final RegistryObject<Block> BRONZE_BLOCK = registerSimpleBlock("bronze_block", Material.METAL, MWColors.BRONZE.toMaterialColor(),3.0F, 6.0F, true, SoundType.COPPER);
    public static final RegistryObject<Block> RUBY_BLOCK = registerMetalOreStorageBlock("ruby_block", MWColors.RUBY.toMaterialColor());
    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerMetalOreStorageBlock("sapphire_block", MWColors.SAPPHIRE.toMaterialColor());
    public static final RegistryObject<Block> PYRITE_BLOCK = registerFuelBlock("pyrite_block", MWColors.PYRITE.toMaterialColor(), 1200);
    public static final RegistryObject<Block> CHARCOAL_BLOCK = registerFuelBlock("charcoal_block", MWColors.CHARCOAL.toMaterialColor(), 800);

    //#endregion

    /**
     * Create some basic {@link BlockBehaviour.Properties block properties}
     * using the {@link Material material color} and the base {@link SoundType block sound}
     *
     * @param material {@link Material The block material}
     * @param hardness {@link Float The block hardness}
     * @param blastResistance {@link Float The block blast restitance}
     * @param requiresTool {@link Boolean If the block requires the correct tool to drop}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    private static BlockBehaviour.Properties basicBlockProperties(final Material material, final float hardness, final float blastResistance, final boolean requiresTool) {
        return basicBlockProperties(material, material.getColor(), hardness, blastResistance, requiresTool);
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
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    private static BlockBehaviour.Properties basicBlockProperties(final Material material, final MaterialColor color, final float hardness, final float blastResistance, final boolean requiresTool) {
        return basicBlockProperties(material, color, hardness, blastResistance, requiresTool, null);
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
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    private static BlockBehaviour.Properties basicBlockProperties(final Material material, final MaterialColor color, final float hardness, final float blastResistance, final boolean requiresTool, final SoundType sound) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of(material, color).strength(hardness, blastResistance);
        if(requiresTool) {
            properties = properties.requiresCorrectToolForDrops();
        }
        if(sound != null) {
            properties = properties.sound(sound);
        }
        return properties;
    }

    /**
     * Create the {@link BlockBehaviour.Properties block properties} for an {@link Block ore block}
     *
     * @param isDeepslateOre {@link Boolean Whether the ore is a deepslate ore}
     * @return {@link BlockBehaviour.Properties The ore block properties}
     */
    private static BlockBehaviour.Properties oreBlockProperties(final boolean isDeepslateOre) {
        return basicBlockProperties(Material.STONE,
                isDeepslateOre ? MaterialColor.DEEPSLATE : Material.STONE.getColor(),
                isDeepslateOre ? 4.5F : 3.0F, 3.0F,
                true,
                isDeepslateOre ? SoundType.DEEPSLATE : null);
    }

    /**
     * Register an {@link Block ore block}
     *
     * @param name {@link String The block Name}
     * @param isDeepslateOre {@link Boolean Whether the ore is a deepslate ore}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerOverworldOreBlock(final String name, final boolean isDeepslateOre) {
        return registerOreBlock(name, oreBlockProperties(isDeepslateOre), 3, 7);
    }

    /**
     * Register a Nether {@link Block ore block}
     *
     * @param name {@link String The block Name}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerNetherOreBlock(final String name) {
        return registerOreBlock(name, oreBlockProperties(false).color(MaterialColor.NETHER).sound(SoundType.NETHER_ORE), 2, 5);
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
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerRawOreBlock(final String name, final MaterialColor color) {
        return registerOreStorageBlock(name, Material.STONE, color,null);
    }

    /**
     * Register a {@link Block raw ore block}
     *
     * @param name {@link String The block name}
     * @param color {@link MaterialColor The block color on maps}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerMetalOreStorageBlock(final String name, MaterialColor color) {
        return registerOreStorageBlock(name, Material.METAL, color, SoundType.METAL);
    }

    /**
     * Register a {@link Block raw ore block}
     *
     * @param name {@link String The block name}
     * @param color {@link MaterialColor The block color on maps}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerOreStorageBlock(final String name, final Material material, final MaterialColor color, final SoundType sound) {
        return registerSimpleBlock(name, Material.STONE, color,5.0F, 6.0F, true, sound);
    }

    /**
     * Register a {@link Block fuel block}
     *
     * @param name {@link String The block name}
     * @param color {@link MaterialColor The block color on maps}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerFuelBlock(final String name, final MaterialColor color, final int burnTime) {
        RegistryObject<Block> block = registerBlockWithoutBlockItem(name, () -> new Block(basicBlockProperties(Material.STONE, color, 5.0F, 6.0F, true)));
        MWItems.registerFuelBlockItem(name, block, burnTime);
        return block;
    }

    /**
     * Register a {@link Block block} using the {@link Material material color} and the base {@link SoundType block sound}
     *
     * @param name {@link String The block name}
     * @param material {@link Material The block material}
     * @param hardness {@link Float The block hardness}
     * @param blastResistance {@link Float The block blast resistance}
     * @param requiresTool {@link Boolean If the block requires the correct tool to drop}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerSimpleBlock(final String name, final Material material, final float hardness, final float blastResistance, final boolean requiresTool) {
        return registerSimpleBlock(name, material, material.getColor(), hardness, blastResistance, requiresTool);
    }

    /**
     * Register a {@link Block block} using the provided {@link Material color} and the base {@link SoundType block sound}
     *
     * @param name {@link String The block name}
     * @param material {@link Material The block material}
     * @param color {@link MaterialColor The block color}
     * @param hardness {@link Float The block hardness}
     * @param blastResistance {@link Float The block blast resistance}
     * @param requiresTool {@link Boolean If the block requires the correct tool to drop}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerSimpleBlock(final String name, final Material material, final MaterialColor color, final float hardness, final float blastResistance, final boolean requiresTool) {
        return registerSimpleBlock(name, material, color, hardness, blastResistance, requiresTool, null);
    }

    /**
     * Register a {@link Block block} using the provided {@link Material color} and {@link SoundType block sound}
     *
     * @param name {@link String The block name}
     * @param material {@link Material The block material}
     * @param color {@link MaterialColor The block color}
     * @param hardness {@link Float The block hardness}
     * @param blastResistance {@link Float The block blast resistance}
     * @param requiresTool {@link Boolean If the block requires the correct tool to drop}
     * @param sound {@link SoundType The block sound}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerSimpleBlock(final String name, final Material material, final MaterialColor color, final float hardness, final float blastResistance, final boolean requiresTool, final SoundType sound) {
        return registerBlock(name, () -> new Block(basicBlockProperties(material, color, hardness, blastResistance, requiresTool, sound)));
    }

    /**
     * Register a {@link Block block}
     *
     * @param name {@link String The block name}
     * @param blockSupplier {@link Supplier<Block> The block supplier}
     * @param <T> The block type
     * @return {@link RegistryObject<T> The registered block}
     */
    private static <T extends Block> RegistryObject<T> registerBlock(final String name, final Supplier<T> blockSupplier) {
        RegistryObject<T> block = registerBlockWithoutBlockItem(name, blockSupplier);
        registerBlockItem(name, block);
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
     * @param <T> The block type
     */
    private static <T extends Block> void registerBlockItem(final String name, final RegistryObject<T> block) {
        MWItems.registerItem(name, () -> new BlockItem(block.get(), MWItems.basicProperties()));
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
