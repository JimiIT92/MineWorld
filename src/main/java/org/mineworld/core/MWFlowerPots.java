package org.mineworld.core;

import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.block.CoralFlowerPotBlock;

import java.util.HashMap;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link FlowerPotBlock flower pots}
 */
public final class MWFlowerPots {

    /**
     * {@link MineWorld MineWorld} flower pots. The key represents the {@link Block flower block}, the value is the {@link Block potted flower block}
     */
    private static final HashMap<Supplier<? extends Block>, RegistryObject<Block>> flowerPots = new HashMap<>();

    //#region Flower pots

    public static final RegistryObject<Block> POTTED_BLUE_ROSE = registerFlowerPot("potted_blue_rose", MWBlocks.BLUE_ROSE);
    public static final RegistryObject<Block> POTTED_BLUE_ROSE_BUSH = registerFlowerPot("potted_blue_rose_bush", MWBlocks.BLUE_ROSE_BUSH);
    public static final RegistryObject<Block> POTTED_WHITE_ROSE = registerFlowerPot("potted_white_rose", MWBlocks.WHITE_ROSE);
    public static final RegistryObject<Block> POTTED_WHITE_ROSE_BUSH = registerFlowerPot("potted_white_rose_bush", MWBlocks.WHITE_ROSE_BUSH);
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

    //#endregion

    /**
     * Register a {@link FlowerPotBlock flower pot block}
     * that can emit light
     *
     * @param name {@link String The block name}
     * @param flowerSupplier {@link Supplier <Block> The flower this pot is referring to}
     * @param light {@link Integer The block light level}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this block to be registered}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerLitFlowerPot(final String name, final Supplier<? extends Block> flowerSupplier, final int light, final FeatureFlag... featureFlags) {
        return registerFlowerPot(name, flowerSupplier, MWBlocks.copyFrom(Blocks.FLOWER_POT, featureFlags).lightLevel(state -> light));
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
        return registerFlowerPot(name, flowerSupplier, MWBlocks.copyFrom(Blocks.FLOWER_POT, featureFlags));
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
        final RegistryObject<Block> block = MWBlocks.registerBlockWithoutBlockItem(name, () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, flowerSupplier, properties));
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
        final RegistryObject<Block> block = MWBlocks.registerBlockWithoutBlockItem(name, () -> new CoralFlowerPotBlock(deadCoralFlowerPot, coralSupplier, featureFlags));
        flowerPots.put(coralSupplier, block);
        return block;
    }

    /**
     * Register the {@link MineWorld MineWorld} flower pots
     */
    public static void registerFlowerPots() {
        final FlowerPotBlock flowerPot = (FlowerPotBlock) Blocks.FLOWER_POT;
        flowerPots.forEach((flower, pot) -> flowerPot.addPlant(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(flower.get())), pot));
    }

    /**
     * Register the {@link MineWorld MineWorld} {@link Block flower pots}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(final IEventBus eventBus) { }
}
