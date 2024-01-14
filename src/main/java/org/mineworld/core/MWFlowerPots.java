package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.block.CoralFlowerPotBlock;
import org.mineworld.helper.PropertyHelper;

import java.util.HashMap;
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

    public static final RegistryObject<Block> POTTED_BLUE_ROSE = registerFlowerPot("potted_blue_rose", Suppliers.memoize(() -> MWBlocks.BLUE_ROSE.get()));
    public static final RegistryObject<Block> POTTED_BLUE_ROSE_BUSH = registerFlowerPot("potted_blue_rose_bush", Suppliers.memoize(() -> MWBlocks.BLUE_ROSE_BUSH.get()));
    public static final RegistryObject<Block> POTTED_WHITE_ROSE = registerFlowerPot("potted_white_rose", Suppliers.memoize(() -> MWBlocks.WHITE_ROSE.get()));
    public static final RegistryObject<Block> POTTED_WHITE_ROSE_BUSH = registerFlowerPot("potted_white_rose_bush", Suppliers.memoize(() -> MWBlocks.WHITE_ROSE_BUSH.get()));
    public static final RegistryObject<Block> POTTED_MOSS_BLOCK = registerFlowerPot("potted_moss_block", Suppliers.memoize(() -> Blocks.MOSS_BLOCK));
    public static final RegistryObject<Block> POTTED_OAK_LEAVES = registerFlowerPot("potted_oak_leaves", Suppliers.memoize(() -> Blocks.OAK_LEAVES));
    public static final RegistryObject<Block> POTTED_SPRUCE_LEAVES = registerFlowerPot("potted_spruce_leaves", Suppliers.memoize(() -> Blocks.SPRUCE_LEAVES));
    public static final RegistryObject<Block> POTTED_BIRCH_LEAVES = registerFlowerPot("potted_birch_leaves", Suppliers.memoize(() -> Blocks.BIRCH_LEAVES));
    public static final RegistryObject<Block> POTTED_JUNGLE_LEAVES = registerFlowerPot("potted_jungle_leaves", Suppliers.memoize(() -> Blocks.JUNGLE_LEAVES));
    public static final RegistryObject<Block> POTTED_ACACIA_LEAVES = registerFlowerPot("potted_acacia_leaves", Suppliers.memoize(() -> Blocks.ACACIA_LEAVES));
    public static final RegistryObject<Block> POTTED_CHERRY_LEAVES = registerFlowerPot("potted_cherry_leaves", Suppliers.memoize(() -> Blocks.CHERRY_LEAVES));
    public static final RegistryObject<Block> POTTED_DARK_OAK_LEAVES = registerFlowerPot("potted_dark_oak_leaves", Suppliers.memoize(() -> Blocks.DARK_OAK_LEAVES));
    public static final RegistryObject<Block> POTTED_MANGROVE_LEAVES = registerFlowerPot("potted_mangrove_leaves", Suppliers.memoize(() -> Blocks.MANGROVE_LEAVES));
    public static final RegistryObject<Block> POTTED_AZALEA_LEAVES = registerFlowerPot("potted_azalea_leaves", Suppliers.memoize(() -> Blocks.AZALEA_LEAVES));
    public static final RegistryObject<Block> POTTED_FLOWERING_AZALEA_LEAVES = registerFlowerPot("potted_flowering_azalea_leaves", Suppliers.memoize(() -> Blocks.FLOWERING_AZALEA_LEAVES));
    public static final RegistryObject<Block> POTTED_MANGROVE_ROOTS = registerFlowerPot("potted_mangrove_roots", Suppliers.memoize(() -> Blocks.MANGROVE_ROOTS));
    public static final RegistryObject<Block> POTTED_MUDDY_MANGROVE_ROOTS = registerFlowerPot("potted_muddy_mangrove_roots", Suppliers.memoize(() -> Blocks.MUDDY_MANGROVE_ROOTS));
    public static final RegistryObject<Block> POTTED_MUSHROOM_STEM = registerFlowerPot("potted_mushroom_stem", Suppliers.memoize(() -> Blocks.MUSHROOM_STEM));
    public static final RegistryObject<Block> POTTED_BROWN_MUSHROOM_BLOCK = registerFlowerPot("potted_brown_mushroom_block", Suppliers.memoize(() -> Blocks.BROWN_MUSHROOM_BLOCK));
    public static final RegistryObject<Block> POTTED_RED_MUSHROOM_BLOCK = registerFlowerPot("potted_red_mushroom_block", Suppliers.memoize(() -> Blocks.RED_MUSHROOM_BLOCK));
    public static final RegistryObject<Block> POTTED_NETHER_WART_BLOCK = registerFlowerPot("potted_nether_wart_block", Suppliers.memoize(() -> Blocks.NETHER_WART_BLOCK));
    public static final RegistryObject<Block> POTTED_WARPED_WART_BLOCK = registerFlowerPot("potted_warped_wart_block", Suppliers.memoize(() -> Blocks.WARPED_WART_BLOCK));
    public static final RegistryObject<Block> POTTED_SHROOMLIGHT = registerLitFlowerPot("potted_shroomlight", Suppliers.memoize(() -> Blocks.SHROOMLIGHT), 5);
    public static final RegistryObject<Block> POTTED_GRASS = registerFlowerPot("potted_grass", Suppliers.memoize(() -> Blocks.GRASS));
    public static final RegistryObject<Block> POTTED_SUGAR_CANE = registerFlowerPot("potted_sugar_cane", Suppliers.memoize(() -> Blocks.SUGAR_CANE));
    public static final RegistryObject<Block> POTTED_SEAGRASS = registerFlowerPot("potted_seagrass", Suppliers.memoize(() -> Blocks.SEAGRASS));
    public static final RegistryObject<Block> POTTED_TALL_GRASS = registerFlowerPot("potted_tall_grass", Suppliers.memoize(() -> Blocks.TALL_GRASS));
    public static final RegistryObject<Block> POTTED_LARGE_FERN = registerFlowerPot("potted_large_fern", Suppliers.memoize(() -> Blocks.LARGE_FERN));
    public static final RegistryObject<Block> POTTED_SUNFLOWER = registerFlowerPot("potted_sunflower", Suppliers.memoize(() -> Blocks.SUNFLOWER));
    public static final RegistryObject<Block> POTTED_LILAC = registerFlowerPot("potted_lilac", Suppliers.memoize(() -> Blocks.LILAC));
    public static final RegistryObject<Block> POTTED_ROSE_BUSH = registerFlowerPot("potted_rose_bush", Suppliers.memoize(() -> Blocks.ROSE_BUSH));
    public static final RegistryObject<Block> POTTED_PEONY = registerFlowerPot("potted_peony", Suppliers.memoize(() -> Blocks.PEONY));
    public static final RegistryObject<Block> POTTED_KELP = registerFlowerPot("potted_kelp", Suppliers.memoize(() -> Blocks.KELP));
    public static final RegistryObject<Block> POTTED_BIG_DRIPLEAF = registerFlowerPot("potted_big_dripleaf", Suppliers.memoize(() -> Blocks.BIG_DRIPLEAF));
    public static final RegistryObject<Block> POTTED_SMALL_DRIPLEAF = registerFlowerPot("potted_small_dripleaf", Suppliers.memoize(() -> Blocks.SMALL_DRIPLEAF));
    public static final RegistryObject<Block> POTTED_CHORUS_PLANT = registerFlowerPot("potted_chorus_plant", Suppliers.memoize(() -> Blocks.CHORUS_PLANT));
    public static final RegistryObject<Block> POTTED_CHORUS_FLOWER = registerFlowerPot("potted_chorus_flower", Suppliers.memoize(() -> Blocks.CHORUS_FLOWER));
    public static final RegistryObject<Block> POTTED_SWEET_BERRY_BUSH = registerFlowerPot("potted_sweet_berry_bush", Suppliers.memoize(() -> Blocks.SWEET_BERRY_BUSH));
    public static final RegistryObject<Block> POTTED_CAVE_VINES = registerLitFlowerPot("potted_cave_vines", Suppliers.memoize(() -> Blocks.CAVE_VINES), 4);
    public static final RegistryObject<Block> POTTED_OCHRE_FROGLIGHT = registerLitFlowerPot("potted_ochre_froglight", Suppliers.memoize(() -> Blocks.OCHRE_FROGLIGHT), 5);
    public static final RegistryObject<Block> POTTED_VERDANT_FROGLIGHT = registerLitFlowerPot("potted_verdant_froglight", Suppliers.memoize(() -> Blocks.VERDANT_FROGLIGHT), 5);
    public static final RegistryObject<Block> POTTED_PEARLESCENT_FROGLIGHT = registerLitFlowerPot("potted_pearlescent_froglight", Suppliers.memoize(() -> Blocks.PEARLESCENT_FROGLIGHT), 5);
    public static final RegistryObject<Block> POTTED_DEAD_TUBE_CORAL_BLOCK = registerFlowerPot("potted_dead_tube_coral_block", Suppliers.memoize(() -> Blocks.DEAD_TUBE_CORAL_BLOCK));
    public static final RegistryObject<Block> POTTED_TUBE_CORAL_BLOCK = registerCoralFlowerPot("potted_tube_coral_block", Suppliers.memoize(() -> POTTED_DEAD_TUBE_CORAL_BLOCK.get()), Suppliers.memoize(() -> Blocks.TUBE_CORAL_BLOCK));
    public static final RegistryObject<Block> POTTED_DEAD_BRAIN_CORAL_BLOCK = registerFlowerPot("potted_dead_brain_coral_block", Suppliers.memoize(() -> Blocks.DEAD_BRAIN_CORAL_BLOCK));
    public static final RegistryObject<Block> POTTED_BRAIN_CORAL_BLOCK = registerCoralFlowerPot("potted_brain_coral_block", Suppliers.memoize(() -> POTTED_DEAD_BRAIN_CORAL_BLOCK.get()), Suppliers.memoize(() -> Blocks.BRAIN_CORAL_BLOCK));
    public static final RegistryObject<Block> POTTED_DEAD_BUBBLE_CORAL_BLOCK = registerFlowerPot("potted_dead_bubble_coral_block", Suppliers.memoize(() -> Blocks.DEAD_BUBBLE_CORAL_BLOCK));
    public static final RegistryObject<Block> POTTED_BUBBLE_CORAL_BLOCK = registerCoralFlowerPot("potted_bubble_coral_block", Suppliers.memoize(() -> POTTED_DEAD_BUBBLE_CORAL_BLOCK.get()), Suppliers.memoize(() -> Blocks.BUBBLE_CORAL_BLOCK));
    public static final RegistryObject<Block> POTTED_DEAD_FIRE_CORAL_BLOCK = registerFlowerPot("potted_dead_fire_coral_block", Suppliers.memoize(() -> Blocks.DEAD_FIRE_CORAL_BLOCK));
    public static final RegistryObject<Block> POTTED_FIRE_CORAL_BLOCK = registerCoralFlowerPot("potted_fire_coral_block", Suppliers.memoize(() -> POTTED_DEAD_FIRE_CORAL_BLOCK.get()), Suppliers.memoize(() -> Blocks.FIRE_CORAL_BLOCK));
    public static final RegistryObject<Block> POTTED_DEAD_HORN_CORAL_BLOCK = registerFlowerPot("potted_dead_horn_coral_block", Suppliers.memoize(() -> Blocks.DEAD_HORN_CORAL_BLOCK));
    public static final RegistryObject<Block> POTTED_HORN_CORAL_BLOCK = registerCoralFlowerPot("potted_horn_coral_block", Suppliers.memoize(() -> POTTED_DEAD_HORN_CORAL_BLOCK.get()), Suppliers.memoize(() -> Blocks.HORN_CORAL_BLOCK));
    public static final RegistryObject<Block> POTTED_DEAD_TUBE_CORAL = registerFlowerPot("potted_dead_tube_coral", Suppliers.memoize(() -> Blocks.DEAD_TUBE_CORAL));
    public static final RegistryObject<Block> POTTED_TUBE_CORAL = registerCoralFlowerPot("potted_tube_coral", Suppliers.memoize(() -> POTTED_DEAD_TUBE_CORAL.get()), Suppliers.memoize(() -> Blocks.TUBE_CORAL));
    public static final RegistryObject<Block> POTTED_DEAD_BRAIN_CORAL = registerFlowerPot("potted_dead_brain_coral", Suppliers.memoize(() -> Blocks.DEAD_BRAIN_CORAL));
    public static final RegistryObject<Block> POTTED_BRAIN_CORAL = registerCoralFlowerPot("potted_brain_coral", Suppliers.memoize(() -> POTTED_DEAD_BRAIN_CORAL.get()), Suppliers.memoize(() -> Blocks.BRAIN_CORAL));
    public static final RegistryObject<Block> POTTED_DEAD_BUBBLE_CORAL = registerFlowerPot("potted_dead_bubble_coral", Suppliers.memoize(() -> Blocks.DEAD_BUBBLE_CORAL));
    public static final RegistryObject<Block> POTTED_BUBBLE_CORAL = registerCoralFlowerPot("potted_bubble_coral", Suppliers.memoize(() -> POTTED_DEAD_BUBBLE_CORAL.get()), Suppliers.memoize(() -> Blocks.BUBBLE_CORAL));
    public static final RegistryObject<Block> POTTED_DEAD_FIRE_CORAL = registerFlowerPot("potted_dead_fire_coral", Suppliers.memoize(() -> Blocks.DEAD_FIRE_CORAL));
    public static final RegistryObject<Block> POTTED_FIRE_CORAL = registerCoralFlowerPot("potted_fire_coral", Suppliers.memoize(() -> POTTED_DEAD_FIRE_CORAL.get()), Suppliers.memoize(() -> Blocks.FIRE_CORAL));
    public static final RegistryObject<Block> POTTED_DEAD_HORN_CORAL = registerFlowerPot("potted_dead_horn_coral", Suppliers.memoize(() -> Blocks.DEAD_HORN_CORAL));
    public static final RegistryObject<Block> POTTED_HORN_CORAL = registerCoralFlowerPot("potted_horn_coral", Suppliers.memoize(() -> POTTED_DEAD_HORN_CORAL.get()), Suppliers.memoize(() -> Blocks.HORN_CORAL));
    public static final RegistryObject<Block> POTTED_DEAD_TUBE_CORAL_FAN = registerFlowerPot("potted_dead_tube_coral_fan", Suppliers.memoize(() -> Blocks.DEAD_TUBE_CORAL_FAN));
    public static final RegistryObject<Block> POTTED_TUBE_CORAL_FAN = registerCoralFlowerPot("potted_tube_coral_fan", Suppliers.memoize(() -> POTTED_DEAD_TUBE_CORAL_FAN.get()), Suppliers.memoize(() -> Blocks.TUBE_CORAL_FAN));
    public static final RegistryObject<Block> POTTED_DEAD_BRAIN_CORAL_FAN = registerFlowerPot("potted_dead_brain_coral_fan", Suppliers.memoize(() -> Blocks.DEAD_BRAIN_CORAL_FAN));
    public static final RegistryObject<Block> POTTED_BRAIN_CORAL_FAN = registerCoralFlowerPot("potted_brain_coral_fan", Suppliers.memoize(() -> POTTED_DEAD_BRAIN_CORAL_FAN.get()), Suppliers.memoize(() -> Blocks.BRAIN_CORAL_FAN));
    public static final RegistryObject<Block> POTTED_DEAD_BUBBLE_CORAL_FAN = registerFlowerPot("potted_dead_bubble_coral_fan", Suppliers.memoize(() -> Blocks.DEAD_BUBBLE_CORAL_FAN));
    public static final RegistryObject<Block> POTTED_BUBBLE_CORAL_FAN = registerCoralFlowerPot("potted_bubble_coral_fan", Suppliers.memoize(() -> POTTED_DEAD_BUBBLE_CORAL_FAN.get()), Suppliers.memoize(() -> Blocks.BUBBLE_CORAL_FAN));
    public static final RegistryObject<Block> POTTED_DEAD_FIRE_CORAL_FAN = registerFlowerPot("potted_dead_fire_coral_fan", Suppliers.memoize(() -> Blocks.DEAD_FIRE_CORAL_FAN));
    public static final RegistryObject<Block> POTTED_FIRE_CORAL_FAN = registerCoralFlowerPot("potted_fire_coral_fan", Suppliers.memoize(() -> POTTED_DEAD_FIRE_CORAL_FAN.get()), Suppliers.memoize(() -> Blocks.FIRE_CORAL_FAN));
    public static final RegistryObject<Block> POTTED_DEAD_HORN_CORAL_FAN = registerFlowerPot("potted_dead_horn_coral_fan", Suppliers.memoize(() -> Blocks.DEAD_HORN_CORAL_FAN));
    public static final RegistryObject<Block> POTTED_HORN_CORAL_FAN = registerCoralFlowerPot("potted_horn_coral_fan", Suppliers.memoize(() -> POTTED_DEAD_HORN_CORAL_FAN.get()), Suppliers.memoize(() -> Blocks.HORN_CORAL_FAN));
    public static final RegistryObject<Block> POTTED_BAMBOO_BLOCK = registerFlowerPot("potted_bamboo_block", Suppliers.memoize(() -> Blocks.BAMBOO_BLOCK));
    public static final RegistryObject<Block> POTTED_STRIPPED_BAMBOO_BLOCK = registerFlowerPot("potted_stripped_bamboo_block", Suppliers.memoize(() -> Blocks.STRIPPED_BAMBOO_BLOCK));
    public static final RegistryObject<Block> POTTED_MUD = registerFlowerPot("potted_mud", Suppliers.memoize(() -> Blocks.MUD));
    public static final RegistryObject<Block> POTTED_CLAY = registerFlowerPot("potted_clay", Suppliers.memoize(() -> Blocks.CLAY));
    public static final RegistryObject<Block> POTTED_NETHER_WART = registerFlowerPot("potted_nether_wart", Suppliers.memoize(() -> Blocks.NETHER_WART));
    public static final RegistryObject<Block> POTTED_SEA_PICKLE = registerLitFlowerPot("potted_sea_pickle", Suppliers.memoize(() -> Blocks.SEA_PICKLE), 1);
    public static final RegistryObject<Block> POTTED_DRIED_KELP_BLOCK = registerFlowerPot("potted_dried_kelp_block", Suppliers.memoize(() -> Blocks.DRIED_KELP_BLOCK));
    public static final RegistryObject<Block> POTTED_SPONGE = registerFlowerPot("potted_sponge", Suppliers.memoize(() -> Blocks.SPONGE));
    public static final RegistryObject<Block> POTTED_WET_SPONGE = registerFlowerPot("potted_wet_sponge", Suppliers.memoize(() -> Blocks.WET_SPONGE));
    public static final RegistryObject<Block> POTTED_MELON = registerFlowerPot("potted_melon", Suppliers.memoize(() -> Blocks.MELON));
    public static final RegistryObject<Block> POTTED_PUMPKIN = registerFlowerPot("potted_pumpkin", Suppliers.memoize(() -> Blocks.PUMPKIN));
    public static final RegistryObject<Block> POTTED_HAY_BLOCK = registerFlowerPot("potted_hay_block", Suppliers.memoize(() -> Blocks.HAY_BLOCK));
    public static final RegistryObject<Block> POTTED_HONEYCOMB_BLOCK = registerFlowerPot("potted_honeycomb_block", Suppliers.memoize(() -> Blocks.HONEYCOMB_BLOCK));
    public static final RegistryObject<Block> POTTED_HONEY_BLOCK = registerFlowerPot("potted_honey_block", Suppliers.memoize(() -> Blocks.HONEY_BLOCK));
    public static final RegistryObject<Block> POTTED_SLIME_BLOCK = registerFlowerPot("potted_slime_block", Suppliers.memoize(() -> Blocks.SLIME_BLOCK));
    public static final RegistryObject<Block> POTTED_SCULK = registerFlowerPot("potted_sculk", Suppliers.memoize(() -> Blocks.SCULK));
    public static final RegistryObject<Block> POTTED_SNOW_BLOCK = registerFlowerPot("potted_snow_block", Suppliers.memoize(() -> Blocks.SNOW_BLOCK));
    public static final RegistryObject<Block> POTTED_WARPED_WART = registerFlowerPot("potted_warped_wart", Suppliers.memoize(() -> MWBlocks.WARPED_WART.get()));

    //#endregion

    //#region Methods

    /**
     * Register a {@link FlowerPotBlock Flower Pot} that emits light
     *
     * @param name {@link String The Block name}
     * @param flowerSupplier {@link Supplier<Block> The Supplier for the Flower this Pot is referring to}
     * @param light {@link Integer The light value emitted by the block}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Flower Pot}
     */
    public static RegistryObject<Block> registerLitFlowerPot(final String name, final Supplier<? extends Block> flowerSupplier, final int light, final FeatureFlag... featureFlags) {
        return registerFlowerPot(name, flowerSupplier, Suppliers.memoize(() -> PropertyHelper.copy(Blocks.FLOWER_POT, featureFlags).lightLevel(state -> light)));
    }

    /**
     * Register a {@link FlowerPotBlock Flower Pot}
     *
     * @param name {@link String The Block name}
     * @param flowerSupplier {@link Supplier<Block> The Supplier for the Flower this Pot is referring to}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Flower Pot}
     */
    public static RegistryObject<Block> registerFlowerPot(final String name, final Supplier<? extends Block> flowerSupplier, final FeatureFlag... featureFlags) {
        return registerFlowerPot(name, flowerSupplier, Suppliers.memoize(() -> PropertyHelper.copy(Blocks.FLOWER_POT, featureFlags)));
    }

    /**
     * Register a {@link FlowerPotBlock Flower Pot}
     *
     * @param name {@link String The Block name}
     * @param flowerSupplier {@link Supplier<Block> The Supplier for the Flower this Pot is referring to}
     * @param propertiesSupplier {@link Supplier<BlockBehaviour.Properties> The Block properties Supplier}
     * @return {@link RegistryObject<Block> The registered Flower Pot}
     */
    private static RegistryObject<Block> registerFlowerPot(final String name, final Supplier<? extends Block> flowerSupplier, final Supplier<BlockBehaviour.Properties> propertiesSupplier) {
        final RegistryObject<Block> block = MWBlocks.registerBlockWithoutBlockItem(name, () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, flowerSupplier, propertiesSupplier.get()));
        FLOWER_POTS.put(flowerSupplier, block);
        return block;
    }

    /**
     * Register a {@link FlowerPotBlock Flower Pot}
     *
     * @param name {@link String The Block name}
     * @param deadCoralFlowerPotSupplier {@link Supplier<Block> The Supplier for the Dead Coral Flower Pot this Pot is referring to}
     * @param coralSupplier {@link Supplier<Block> The Supplier for the Coral this Pot is referring to}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Flower Pot}
     */
    private static RegistryObject<Block> registerCoralFlowerPot(final String name, final Supplier<? extends Block> deadCoralFlowerPotSupplier, final Supplier<? extends Block> coralSupplier, final FeatureFlag... featureFlags) {
        final RegistryObject<Block> block = MWBlocks.registerBlockWithoutBlockItem(name, () -> new CoralFlowerPotBlock(deadCoralFlowerPotSupplier, coralSupplier, featureFlags));
        FLOWER_POTS.put(coralSupplier, block);
        return block;
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link FlowerPotBlock Flower Pots}
     */
    public static void register() { }

    //#endregion

}