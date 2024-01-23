package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.block.CoralFlowerPotBlock;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.helper.ResourceHelper;

import java.util.HashMap;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link FlowerPotBlock Flower Pots}
 */
public final class MWFlowerPots {

    //#region Registry

    /**
     * {@link MineWorld MineWorld} {@link FlowerPotBlock Flower Pots}. The key represents the {@link Block flower block}, the value is the {@link Block potted flower block}
     */
    private static final HashMap<Supplier<? extends Block>, RegistryObject<Block>> FLOWER_POTS = new HashMap<>();

    //#endregion

    //#region Flower Pots

    public static final RegistryObject<Block> POTTED_BLUE_ROSE = registerFlowerPot("blue_rose", Suppliers.memoize(() -> MWBlocks.BLUE_ROSE.get()));
    public static final RegistryObject<Block> POTTED_BLUE_ROSE_BUSH = registerFlowerPot("blue_rose_bush", Suppliers.memoize(() -> MWBlocks.BLUE_ROSE_BUSH.get()));
    public static final RegistryObject<Block> POTTED_WHITE_ROSE = registerFlowerPot("white_rose", Suppliers.memoize(() -> MWBlocks.WHITE_ROSE.get()));
    public static final RegistryObject<Block> POTTED_WHITE_ROSE_BUSH = registerFlowerPot("white_rose_bush", Suppliers.memoize(() -> MWBlocks.WHITE_ROSE_BUSH.get()));
    public static final RegistryObject<Block> POTTED_MOSS_BLOCK = registerFlowerPot("moss_block", Suppliers.memoize(() -> Blocks.MOSS_BLOCK));
    public static final RegistryObject<Block> POTTED_OAK_LEAVES = registerFlowerPot(ResourceHelper.woodName(WoodType.OAK) + "_leaves", Suppliers.memoize(() -> Blocks.OAK_LEAVES));
    public static final RegistryObject<Block> POTTED_SPRUCE_LEAVES = registerFlowerPot(ResourceHelper.woodName(WoodType.SPRUCE) + "_leaves", Suppliers.memoize(() -> Blocks.SPRUCE_LEAVES));
    public static final RegistryObject<Block> POTTED_BIRCH_LEAVES = registerFlowerPot(ResourceHelper.woodName(WoodType.BIRCH) + "_leaves", Suppliers.memoize(() -> Blocks.BIRCH_LEAVES));
    public static final RegistryObject<Block> POTTED_JUNGLE_LEAVES = registerFlowerPot(ResourceHelper.woodName(WoodType.JUNGLE) + "_leaves", Suppliers.memoize(() -> Blocks.JUNGLE_LEAVES));
    public static final RegistryObject<Block> POTTED_ACACIA_LEAVES = registerFlowerPot(ResourceHelper.woodName(WoodType.ACACIA) + "_leaves", Suppliers.memoize(() -> Blocks.ACACIA_LEAVES));
    public static final RegistryObject<Block> POTTED_DARK_OAK_LEAVES = registerFlowerPot(ResourceHelper.woodName(WoodType.DARK_OAK) + "_leaves", Suppliers.memoize(() -> Blocks.DARK_OAK_LEAVES));
    public static final RegistryObject<Block> POTTED_MANGROVE_LEAVES = registerFlowerPot(ResourceHelper.woodName(WoodType.MANGROVE) + "_leaves", Suppliers.memoize(() -> Blocks.MANGROVE_LEAVES));
    public static final RegistryObject<Block> POTTED_MANGROVE_ROOTS = registerFlowerPot(ResourceHelper.woodName(WoodType.MANGROVE) + "_roots", Suppliers.memoize(() -> Blocks.MANGROVE_ROOTS));
    public static final RegistryObject<Block> POTTED_MUDDY_MANGROVE_ROOTS = registerFlowerPot("muddy_mangrove_roots", Suppliers.memoize(() -> Blocks.MUDDY_MANGROVE_ROOTS));
    public static final RegistryObject<Block> POTTED_CHERRY_LEAVES = registerFlowerPot(ResourceHelper.woodName(WoodType.CHERRY) + "_leaves", Suppliers.memoize(() -> Blocks.CHERRY_LEAVES));
    public static final RegistryObject<Block> POTTED_AZALEA_LEAVES = registerFlowerPot("azalea_leaves", Suppliers.memoize(() -> Blocks.AZALEA_LEAVES));
    public static final RegistryObject<Block> POTTED_FLOWERING_AZALEA_LEAVES = registerFlowerPot("flowering_azalea_leaves", Suppliers.memoize(() -> Blocks.FLOWERING_AZALEA_LEAVES));
    public static final RegistryObject<Block> POTTED_MUSHROOM_STEM = registerFlowerPot("mushroom_stem", Suppliers.memoize(() -> Blocks.MUSHROOM_STEM));
    public static final RegistryObject<Block> POTTED_BROWN_MUSHROOM_BLOCK = registerFlowerPot("brown_mushroom_block", Suppliers.memoize(() -> Blocks.BROWN_MUSHROOM_BLOCK));
    public static final RegistryObject<Block> POTTED_RED_MUSHROOM_BLOCK = registerFlowerPot("red_mushroom_block", Suppliers.memoize(() -> Blocks.RED_MUSHROOM_BLOCK));
    public static final RegistryObject<Block> POTTED_NETHER_WART_BLOCK = registerFlowerPot("nether_wart_block", Suppliers.memoize(() -> Blocks.NETHER_WART_BLOCK));
    public static final RegistryObject<Block> POTTED_WARPED_WART_BLOCK = registerFlowerPot("warped_wart_block", Suppliers.memoize(() -> Blocks.WARPED_WART_BLOCK));
    public static final RegistryObject<Block> POTTED_SHROOMLIGHT = registerLitFlowerPot("shroomlight", Suppliers.memoize(() -> Blocks.SHROOMLIGHT), 5);
    public static final RegistryObject<Block> POTTED_GRASS = registerFlowerPot("grass", Suppliers.memoize(() -> Blocks.GRASS));
    public static final RegistryObject<Block> POTTED_SUGAR_CANE = registerFlowerPot("sugar_cane", Suppliers.memoize(() -> Blocks.SUGAR_CANE));
    public static final RegistryObject<Block> POTTED_SEAGRASS = registerFlowerPot("seagrass", Suppliers.memoize(() -> Blocks.SEAGRASS));
    public static final RegistryObject<Block> POTTED_TALL_GRASS = registerFlowerPot("tall_grass", Suppliers.memoize(() -> Blocks.TALL_GRASS));
    public static final RegistryObject<Block> POTTED_LARGE_FERN = registerFlowerPot("large_fern", Suppliers.memoize(() -> Blocks.LARGE_FERN));
    public static final RegistryObject<Block> POTTED_SUNFLOWER = registerFlowerPot("sunflower", Suppliers.memoize(() -> Blocks.SUNFLOWER));
    public static final RegistryObject<Block> POTTED_LILAC = registerFlowerPot("lilac", Suppliers.memoize(() -> Blocks.LILAC));
    public static final RegistryObject<Block> POTTED_ROSE_BUSH = registerFlowerPot("rose_bush", Suppliers.memoize(() -> Blocks.ROSE_BUSH));
    public static final RegistryObject<Block> POTTED_PEONY = registerFlowerPot("peony", Suppliers.memoize(() -> Blocks.PEONY));
    public static final RegistryObject<Block> POTTED_KELP = registerFlowerPot("kelp", Suppliers.memoize(() -> Blocks.KELP));
    public static final RegistryObject<Block> POTTED_BIG_DRIPLEAF = registerFlowerPot("big_dripleaf", Suppliers.memoize(() -> Blocks.BIG_DRIPLEAF));
    public static final RegistryObject<Block> POTTED_SMALL_DRIPLEAF = registerFlowerPot("small_dripleaf", Suppliers.memoize(() -> Blocks.SMALL_DRIPLEAF));
    public static final RegistryObject<Block> POTTED_CHORUS_PLANT = registerFlowerPot("chorus_plant", Suppliers.memoize(() -> Blocks.CHORUS_PLANT));
    public static final RegistryObject<Block> POTTED_CHORUS_FLOWER = registerFlowerPot("chorus_flower", Suppliers.memoize(() -> Blocks.CHORUS_FLOWER));
    public static final RegistryObject<Block> POTTED_SWEET_BERRY_BUSH = registerFlowerPot("sweet_berry_bush", Suppliers.memoize(() -> Blocks.SWEET_BERRY_BUSH));
    public static final RegistryObject<Block> POTTED_CAVE_VINES = registerLitFlowerPot("cave_vines", Suppliers.memoize(() -> Blocks.CAVE_VINES), 4);
    public static final RegistryObject<Block> POTTED_OCHRE_FROGLIGHT = registerLitFlowerPot("ochre_froglight", Suppliers.memoize(() -> Blocks.OCHRE_FROGLIGHT), 5);
    public static final RegistryObject<Block> POTTED_VERDANT_FROGLIGHT = registerLitFlowerPot("verdant_froglight", Suppliers.memoize(() -> Blocks.VERDANT_FROGLIGHT), 5);
    public static final RegistryObject<Block> POTTED_PEARLESCENT_FROGLIGHT = registerLitFlowerPot("pearlescent_froglight", Suppliers.memoize(() -> Blocks.PEARLESCENT_FROGLIGHT), 5);
    public static final RegistryObject<Block> POTTED_DEAD_TUBE_CORAL_BLOCK = registerFlowerPot("dead_tube_coral_block", Suppliers.memoize(() -> Blocks.DEAD_TUBE_CORAL_BLOCK));
    public static final RegistryObject<Block> POTTED_TUBE_CORAL_BLOCK = registerCoralFlowerPot("tube_coral_block", Suppliers.memoize(() -> POTTED_DEAD_TUBE_CORAL_BLOCK.get()), Suppliers.memoize(() -> Blocks.TUBE_CORAL_BLOCK));
    public static final RegistryObject<Block> POTTED_DEAD_BRAIN_CORAL_BLOCK = registerFlowerPot("dead_brain_coral_block", Suppliers.memoize(() -> Blocks.DEAD_BRAIN_CORAL_BLOCK));
    public static final RegistryObject<Block> POTTED_BRAIN_CORAL_BLOCK = registerCoralFlowerPot("brain_coral_block", Suppliers.memoize(() -> POTTED_DEAD_BRAIN_CORAL_BLOCK.get()), Suppliers.memoize(() -> Blocks.BRAIN_CORAL_BLOCK));
    public static final RegistryObject<Block> POTTED_DEAD_BUBBLE_CORAL_BLOCK = registerFlowerPot("dead_bubble_coral_block", Suppliers.memoize(() -> Blocks.DEAD_BUBBLE_CORAL_BLOCK));
    public static final RegistryObject<Block> POTTED_BUBBLE_CORAL_BLOCK = registerCoralFlowerPot("bubble_coral_block", Suppliers.memoize(() -> POTTED_DEAD_BUBBLE_CORAL_BLOCK.get()), Suppliers.memoize(() -> Blocks.BUBBLE_CORAL_BLOCK));
    public static final RegistryObject<Block> POTTED_DEAD_FIRE_CORAL_BLOCK = registerFlowerPot("dead_fire_coral_block", Suppliers.memoize(() -> Blocks.DEAD_FIRE_CORAL_BLOCK));
    public static final RegistryObject<Block> POTTED_FIRE_CORAL_BLOCK = registerCoralFlowerPot("fire_coral_block", Suppliers.memoize(() -> POTTED_DEAD_FIRE_CORAL_BLOCK.get()), Suppliers.memoize(() -> Blocks.FIRE_CORAL_BLOCK));
    public static final RegistryObject<Block> POTTED_DEAD_HORN_CORAL_BLOCK = registerFlowerPot("dead_horn_coral_block", Suppliers.memoize(() -> Blocks.DEAD_HORN_CORAL_BLOCK));
    public static final RegistryObject<Block> POTTED_HORN_CORAL_BLOCK = registerCoralFlowerPot("horn_coral_block", Suppliers.memoize(() -> POTTED_DEAD_HORN_CORAL_BLOCK.get()), Suppliers.memoize(() -> Blocks.HORN_CORAL_BLOCK));
    public static final RegistryObject<Block> POTTED_DEAD_TUBE_CORAL = registerFlowerPot("dead_tube_coral", Suppliers.memoize(() -> Blocks.DEAD_TUBE_CORAL));
    public static final RegistryObject<Block> POTTED_TUBE_CORAL = registerCoralFlowerPot("tube_coral", Suppliers.memoize(() -> POTTED_DEAD_TUBE_CORAL.get()), Suppliers.memoize(() -> Blocks.TUBE_CORAL));
    public static final RegistryObject<Block> POTTED_DEAD_BRAIN_CORAL = registerFlowerPot("dead_brain_coral", Suppliers.memoize(() -> Blocks.DEAD_BRAIN_CORAL));
    public static final RegistryObject<Block> POTTED_BRAIN_CORAL = registerCoralFlowerPot("brain_coral", Suppliers.memoize(() -> POTTED_DEAD_BRAIN_CORAL.get()), Suppliers.memoize(() -> Blocks.BRAIN_CORAL));
    public static final RegistryObject<Block> POTTED_DEAD_BUBBLE_CORAL = registerFlowerPot("dead_bubble_coral", Suppliers.memoize(() -> Blocks.DEAD_BUBBLE_CORAL));
    public static final RegistryObject<Block> POTTED_BUBBLE_CORAL = registerCoralFlowerPot("bubble_coral", Suppliers.memoize(() -> POTTED_DEAD_BUBBLE_CORAL.get()), Suppliers.memoize(() -> Blocks.BUBBLE_CORAL));
    public static final RegistryObject<Block> POTTED_DEAD_FIRE_CORAL = registerFlowerPot("dead_fire_coral", Suppliers.memoize(() -> Blocks.DEAD_FIRE_CORAL));
    public static final RegistryObject<Block> POTTED_FIRE_CORAL = registerCoralFlowerPot("fire_coral", Suppliers.memoize(() -> POTTED_DEAD_FIRE_CORAL.get()), Suppliers.memoize(() -> Blocks.FIRE_CORAL));
    public static final RegistryObject<Block> POTTED_DEAD_HORN_CORAL = registerFlowerPot("dead_horn_coral", Suppliers.memoize(() -> Blocks.DEAD_HORN_CORAL));
    public static final RegistryObject<Block> POTTED_HORN_CORAL = registerCoralFlowerPot("horn_coral", Suppliers.memoize(() -> POTTED_DEAD_HORN_CORAL.get()), Suppliers.memoize(() -> Blocks.HORN_CORAL));
    public static final RegistryObject<Block> POTTED_DEAD_TUBE_CORAL_FAN = registerFlowerPot("dead_tube_coral_fan", Suppliers.memoize(() -> Blocks.DEAD_TUBE_CORAL_FAN));
    public static final RegistryObject<Block> POTTED_TUBE_CORAL_FAN = registerCoralFlowerPot("tube_coral_fan", Suppliers.memoize(() -> POTTED_DEAD_TUBE_CORAL_FAN.get()), Suppliers.memoize(() -> Blocks.TUBE_CORAL_FAN));
    public static final RegistryObject<Block> POTTED_DEAD_BRAIN_CORAL_FAN = registerFlowerPot("dead_brain_coral_fan", Suppliers.memoize(() -> Blocks.DEAD_BRAIN_CORAL_FAN));
    public static final RegistryObject<Block> POTTED_BRAIN_CORAL_FAN = registerCoralFlowerPot("brain_coral_fan", Suppliers.memoize(() -> POTTED_DEAD_BRAIN_CORAL_FAN.get()), Suppliers.memoize(() -> Blocks.BRAIN_CORAL_FAN));
    public static final RegistryObject<Block> POTTED_DEAD_BUBBLE_CORAL_FAN = registerFlowerPot("dead_bubble_coral_fan", Suppliers.memoize(() -> Blocks.DEAD_BUBBLE_CORAL_FAN));
    public static final RegistryObject<Block> POTTED_BUBBLE_CORAL_FAN = registerCoralFlowerPot("bubble_coral_fan", Suppliers.memoize(() -> POTTED_DEAD_BUBBLE_CORAL_FAN.get()), Suppliers.memoize(() -> Blocks.BUBBLE_CORAL_FAN));
    public static final RegistryObject<Block> POTTED_DEAD_FIRE_CORAL_FAN = registerFlowerPot("dead_fire_coral_fan", Suppliers.memoize(() -> Blocks.DEAD_FIRE_CORAL_FAN));
    public static final RegistryObject<Block> POTTED_FIRE_CORAL_FAN = registerCoralFlowerPot("fire_coral_fan", Suppliers.memoize(() -> POTTED_DEAD_FIRE_CORAL_FAN.get()), Suppliers.memoize(() -> Blocks.FIRE_CORAL_FAN));
    public static final RegistryObject<Block> POTTED_DEAD_HORN_CORAL_FAN = registerFlowerPot("dead_horn_coral_fan", Suppliers.memoize(() -> Blocks.DEAD_HORN_CORAL_FAN));
    public static final RegistryObject<Block> POTTED_HORN_CORAL_FAN = registerCoralFlowerPot("horn_coral_fan", Suppliers.memoize(() -> POTTED_DEAD_HORN_CORAL_FAN.get()), Suppliers.memoize(() -> Blocks.HORN_CORAL_FAN));
    public static final RegistryObject<Block> POTTED_BAMBOO_BLOCK = registerFlowerPot(ResourceHelper.woodName(WoodType.BAMBOO) + "_block", Suppliers.memoize(() -> Blocks.BAMBOO_BLOCK));
    public static final RegistryObject<Block> POTTED_STRIPPED_BAMBOO_BLOCK = registerFlowerPot("stripped_"+ ResourceHelper.woodName(WoodType.BAMBOO) + "_block", Suppliers.memoize(() -> Blocks.STRIPPED_BAMBOO_BLOCK));
    public static final RegistryObject<Block> POTTED_MUD = registerFlowerPot("mud", Suppliers.memoize(() -> Blocks.MUD));
    public static final RegistryObject<Block> POTTED_CLAY = registerFlowerPot("clay", Suppliers.memoize(() -> Blocks.CLAY));
    public static final RegistryObject<Block> POTTED_NETHER_WART = registerFlowerPot("nether_wart", Suppliers.memoize(() -> Blocks.NETHER_WART));
    public static final RegistryObject<Block> POTTED_SEA_PICKLE = registerLitFlowerPot("sea_pickle", Suppliers.memoize(() -> Blocks.SEA_PICKLE), 1);
    public static final RegistryObject<Block> POTTED_DRIED_KELP_BLOCK = registerFlowerPot("dried_kelp_block", Suppliers.memoize(() -> Blocks.DRIED_KELP_BLOCK));
    public static final RegistryObject<Block> POTTED_SPONGE = registerFlowerPot("sponge", Suppliers.memoize(() -> Blocks.SPONGE));
    public static final RegistryObject<Block> POTTED_WET_SPONGE = registerFlowerPot("wet_sponge", Suppliers.memoize(() -> Blocks.WET_SPONGE));
    public static final RegistryObject<Block> POTTED_MELON = registerFlowerPot("melon", Suppliers.memoize(() -> Blocks.MELON));
    public static final RegistryObject<Block> POTTED_PUMPKIN = registerFlowerPot("pumpkin", Suppliers.memoize(() -> Blocks.PUMPKIN));
    public static final RegistryObject<Block> POTTED_HAY_BLOCK = registerFlowerPot("hay_block", Suppliers.memoize(() -> Blocks.HAY_BLOCK));
    public static final RegistryObject<Block> POTTED_HONEYCOMB_BLOCK = registerFlowerPot("honeycomb_block", Suppliers.memoize(() -> Blocks.HONEYCOMB_BLOCK));
    public static final RegistryObject<Block> POTTED_HONEY_BLOCK = registerFlowerPot("honey_block", Suppliers.memoize(() -> Blocks.HONEY_BLOCK));
    public static final RegistryObject<Block> POTTED_SLIME_BLOCK = registerFlowerPot("slime_block", Suppliers.memoize(() -> Blocks.SLIME_BLOCK));
    public static final RegistryObject<Block> POTTED_SCULK = registerFlowerPot("sculk", Suppliers.memoize(() -> Blocks.SCULK));
    public static final RegistryObject<Block> POTTED_SNOW_BLOCK = registerFlowerPot("snow_block", Suppliers.memoize(() -> Blocks.SNOW_BLOCK));
    public static final RegistryObject<Block> POTTED_WARPED_WART = registerFlowerPot("warped_wart", Suppliers.memoize(() -> MWBlocks.WARPED_WART.get()));
    public static final RegistryObject<Block> POTTED_OAK_BUSH = registerFlowerPot(ResourceHelper.woodName(WoodType.OAK) + "_bush", Suppliers.memoize(() -> MWBlocks.OAK_BUSH.get()));
    public static final RegistryObject<Block> POTTED_SPRUCE_BUSH = registerFlowerPot(ResourceHelper.woodName(WoodType.SPRUCE) + "_bush", Suppliers.memoize(() -> MWBlocks.SPRUCE_BUSH.get()));
    public static final RegistryObject<Block> POTTED_BIRCH_BUSH = registerFlowerPot(ResourceHelper.woodName(WoodType.BIRCH) + "_bush", Suppliers.memoize(() -> MWBlocks.BIRCH_BUSH.get()));
    public static final RegistryObject<Block> POTTED_JUNGLE_BUSH = registerFlowerPot(ResourceHelper.woodName(WoodType.JUNGLE) + "_bush", Suppliers.memoize(() -> MWBlocks.JUNGLE_BUSH.get()));
    public static final RegistryObject<Block> POTTED_ACACIA_BUSH = registerFlowerPot(ResourceHelper.woodName(WoodType.ACACIA) + "_bush", Suppliers.memoize(() -> MWBlocks.ACACIA_BUSH.get()));
    public static final RegistryObject<Block> POTTED_DARK_OAK_BUSH = registerFlowerPot(ResourceHelper.woodName(WoodType.DARK_OAK) + "_bush", Suppliers.memoize(() -> MWBlocks.DARK_OAK_BUSH.get()));
    public static final RegistryObject<Block> POTTED_MANGROVE_BUSH = registerFlowerPot(ResourceHelper.woodName(WoodType.MANGROVE) + "_bush", Suppliers.memoize(() -> MWBlocks.MANGROVE_BUSH.get()));
    public static final RegistryObject<Block> POTTED_CHERRY_BUSH = registerFlowerPot(ResourceHelper.woodName(WoodType.CHERRY) + "_bush", Suppliers.memoize(() -> MWBlocks.CHERRY_BUSH.get()));
    public static final RegistryObject<Block> POTTED_APPLE_SAPLING = registerFlowerPot(MWWoodTypes.MWWoodTypeNames.APPLE + "_sapling", Suppliers.memoize(() -> MWBlocks.APPLE_SAPLING.get()));
    public static final RegistryObject<Block> POTTED_APPLE_LEAVES = registerFlowerPot(MWWoodTypes.MWWoodTypeNames.APPLE + "_leaves", Suppliers.memoize(() -> MWBlocks.APPLE_LEAVES.get()));
    public static final RegistryObject<Block> POTTED_APPLE_BUSH = registerFlowerPot(MWWoodTypes.MWWoodTypeNames.APPLE + "_bush", Suppliers.memoize(() -> MWBlocks.APPLE_BUSH.get()));
    public static final RegistryObject<Block> POTTED_PALM_SAPLING = registerFlowerPot(MWWoodTypes.MWWoodTypeNames.PALM + "_sapling", Suppliers.memoize(() -> MWBlocks.PALM_SAPLING.get()));
    public static final RegistryObject<Block> POTTED_PALM_LEAVES = registerFlowerPot(MWWoodTypes.MWWoodTypeNames.PALM + "_leaves", Suppliers.memoize(() -> MWBlocks.PALM_LEAVES.get()));
    public static final RegistryObject<Block> POTTED_PALM_BUSH = registerFlowerPot(MWWoodTypes.MWWoodTypeNames.PALM + "_bush", Suppliers.memoize(() -> MWBlocks.PALM_BUSH.get()));
    public static final RegistryObject<Block> POTTED_SCULK_SAPLING = registerFlowerPot(MWWoodTypes.MWWoodTypeNames.SCULK + "_sapling", Suppliers.memoize(() -> MWBlocks.SCULK_SAPLING.get()));
    public static final RegistryObject<Block> POTTED_SCULK_LEAVES = registerFlowerPot(MWWoodTypes.MWWoodTypeNames.SCULK + "_leaves", Suppliers.memoize(() -> MWBlocks.SCULK_LEAVES.get()));
    public static final RegistryObject<Block> POTTED_SCULK_BUSH = registerFlowerPot(MWWoodTypes.MWWoodTypeNames.SCULK + "_bush", Suppliers.memoize(() -> MWBlocks.SCULK_BUSH.get()));
    public static final RegistryObject<Block> POTTED_CATTAIL = registerFlowerPot("cattail", Suppliers.memoize(() -> MWBlocks.CATTAIL.get()));
    public static final RegistryObject<Block> POTTED_SCULK_ROOTS = registerFlowerPot("sculk_roots", Suppliers.memoize(() -> MWBlocks.SCULK_ROOTS.get()));

    //#endregion

    //#region Methods

    /**
     * Register a {@link FlowerPotBlock Flower Pot} that emits light
     *
     * @param plantName {@link String The Plant name}
     * @param flowerSupplier {@link Supplier<Block> The Supplier for the Flower this Pot is referring to}
     * @param light {@link Integer The light value emitted by the block}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Flower Pot}
     */
    public static RegistryObject<Block> registerLitFlowerPot(final String plantName, final Supplier<? extends Block> flowerSupplier, final int light, final FeatureFlag... featureFlags) {
        return registerFlowerPot(plantName, flowerSupplier, Suppliers.memoize(() -> PropertyHelper.copy(Blocks.FLOWER_POT, featureFlags).lightLevel(state -> light)));
    }

    /**
     * Register a {@link FlowerPotBlock Flower Pot}
     *
     * @param plantName {@link String The Plant name}
     * @param flowerSupplier {@link Supplier<Block> The Supplier for the Flower this Pot is referring to}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Flower Pot}
     */
    public static RegistryObject<Block> registerFlowerPot(final String plantName, final Supplier<? extends Block> flowerSupplier, final FeatureFlag... featureFlags) {
        return registerFlowerPot(plantName, flowerSupplier, Suppliers.memoize(() -> PropertyHelper.copy(Blocks.FLOWER_POT, featureFlags)));
    }

    /**
     * Register a {@link FlowerPotBlock Flower Pot}
     *
     * @param plantName {@link String The Plant name}
     * @param flowerSupplier {@link Supplier<Block> The Supplier for the Flower this Pot is referring to}
     * @param propertiesSupplier {@link Supplier<BlockBehaviour.Properties> The Block properties Supplier}
     * @return {@link RegistryObject<Block> The registered Flower Pot}
     */
    private static RegistryObject<Block> registerFlowerPot(final String plantName, final Supplier<? extends Block> flowerSupplier, final Supplier<BlockBehaviour.Properties> propertiesSupplier) {
        final RegistryObject<Block> block = MWBlocks.registerBlockWithoutBlockItem(pottedName(plantName), () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, flowerSupplier, propertiesSupplier.get()));
        FLOWER_POTS.put(flowerSupplier, block);
        return block;
    }

    /**
     * Register a {@link FlowerPotBlock Flower Pot}
     *
     * @param plantName {@link String The Plant name}
     * @param deadCoralFlowerPotSupplier {@link Supplier<Block> The Supplier for the Dead Coral Flower Pot this Pot is referring to}
     * @param coralSupplier {@link Supplier<Block> The Supplier for the Coral this Pot is referring to}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Flower Pot}
     */
    private static RegistryObject<Block> registerCoralFlowerPot(final String plantName, final Supplier<? extends Block> deadCoralFlowerPotSupplier, final Supplier<? extends Block> coralSupplier, final FeatureFlag... featureFlags) {
        final RegistryObject<Block> block = MWBlocks.registerBlockWithoutBlockItem(pottedName(plantName), () -> new CoralFlowerPotBlock(deadCoralFlowerPotSupplier, coralSupplier, featureFlags));
        FLOWER_POTS.put(coralSupplier, block);
        return block;
    }

    /**
     * Get the {@link String Potted Plant name}
     *
     * @param plantName {@link String The Plant name}
     * @return The {@link String Potted Plant name}
     */
    private static String pottedName(final String plantName) {
        return "potted_" + plantName;
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link FlowerPotBlock Flower Pots}
     */
    public static void register() { }

    /**
     * Register all {@link FlowerPotBlock Flower Pot} plants
     */
    public static void registerFlowerPotPlants() {
        final FlowerPotBlock flowerPot = (FlowerPotBlock) Blocks.FLOWER_POT;
        FLOWER_POTS.forEach((flower, pot) -> flowerPot.addPlant(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(flower.get())), pot));
    }

    //#endregion

}