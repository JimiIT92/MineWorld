package org.mineworld.core;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.InstrumentItem;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.ResourceHelper;
import org.mineworld.item.CopperHornItem;

/**
 * {@link MineWorld MineWorld} {@link Instrument Instruments}
 */
public final class MWInstruments {

    //#region Instruments

    public static final ResourceKey<Instrument> FLY_GOAT_HORN = registerGoatHornKey("fly");
    public static final ResourceKey<Instrument> RESIST_GOAT_HORN = registerGoatHornKey("resist");
    public static final ResourceKey<Instrument> GREAT_SKY_FALLING_COPPER_HORN = registerCopperHornKey("great_sky_falling");
    public static final ResourceKey<Instrument> GREAT_SKY_FALLING_COPPER_HORN_BASS = registerCopperHornKey("great_sky_falling", CopperHornItem.CopperHornVariant.BASS);
    public static final ResourceKey<Instrument> GREAT_SKY_FALLING_COPPER_HORN_HARMONY = registerCopperHornKey("great_sky_falling", CopperHornItem.CopperHornVariant.HARMONY);
    public static final ResourceKey<Instrument> OLD_HYMN_RESTING_COPPER_HORN = registerCopperHornKey("old_hymn_resting");
    public static final ResourceKey<Instrument> OLD_HYMN_RESTING_COPPER_HORN_BASS = registerCopperHornKey("old_hymn_resting", CopperHornItem.CopperHornVariant.BASS);
    public static final ResourceKey<Instrument> OLD_HYMN_RESTING_COPPER_HORN_HARMONY = registerCopperHornKey("old_hymn_resting", CopperHornItem.CopperHornVariant.HARMONY);
    public static final ResourceKey<Instrument> PURE_WATER_DESIRE_COPPER_HORN = registerCopperHornKey("pure_water_desire");
    public static final ResourceKey<Instrument> PURE_WATER_DESIRE_COPPER_HORN_BASS = registerCopperHornKey("pure_water_desire", CopperHornItem.CopperHornVariant.BASS);
    public static final ResourceKey<Instrument> PURE_WATER_DESIRE_COPPER_HORN_HARMONY = registerCopperHornKey("pure_water_desire", CopperHornItem.CopperHornVariant.HARMONY);
    public static final ResourceKey<Instrument> MUMBLE_FIRE_MEMORY_COPPER_HORN = registerCopperHornKey("mumble_fire_memory");
    public static final ResourceKey<Instrument> MUMBLE_FIRE_MEMORY_COPPER_HORN_BASS = registerCopperHornKey("mumble_fire_memory", CopperHornItem.CopperHornVariant.BASS);
    public static final ResourceKey<Instrument> MUMBLE_FIRE_MEMORY_COPPER_HORN_HARMONY = registerCopperHornKey("mumble_fire_memory", CopperHornItem.CopperHornVariant.HARMONY);
    public static final ResourceKey<Instrument> DRY_URGE_ANGER_COPPER_HORN = registerCopperHornKey("dry_urge_anger");
    public static final ResourceKey<Instrument> DRY_URGE_ANGER_COPPER_HORN_BASS = registerCopperHornKey("dry_urge_anger", CopperHornItem.CopperHornVariant.BASS);
    public static final ResourceKey<Instrument> DRY_URGE_ANGER_COPPER_HORN_HARMONY = registerCopperHornKey("dry_urge_anger", CopperHornItem.CopperHornVariant.HARMONY);
    public static final ResourceKey<Instrument> CLEAR_TEMPER_JOURNEY_COPPER_HORN = registerCopperHornKey("clear_temper_journey");
    public static final ResourceKey<Instrument> CLEAR_TEMPER_JOURNEY_COPPER_HORN_BASS = registerCopperHornKey("clear_temper_journey", CopperHornItem.CopperHornVariant.BASS);
    public static final ResourceKey<Instrument> CLEAR_TEMPER_JOURNEY_COPPER_HORN_HARMONY = registerCopperHornKey("clear_temper_journey", CopperHornItem.CopperHornVariant.HARMONY);
    public static final ResourceKey<Instrument> FRESH_NEST_THOUGHT_COPPER_HORN = registerCopperHornKey("fresh_nest_thought");
    public static final ResourceKey<Instrument> FRESH_NEST_THOUGHT_COPPER_HORN_BASS = registerCopperHornKey("fresh_nest_thought", CopperHornItem.CopperHornVariant.BASS);
    public static final ResourceKey<Instrument> FRESH_NEST_THOUGHT_COPPER_HORN_HARMONY = registerCopperHornKey("fresh_nest_thought", CopperHornItem.CopperHornVariant.HARMONY);
    public static final ResourceKey<Instrument> SECRET_LAKE_TEAR_COPPER_HORN = registerCopperHornKey("secret_lake_tear");
    public static final ResourceKey<Instrument> SECRET_LAKE_TEAR_COPPER_HORN_BASS = registerCopperHornKey("secret_lake_tear", CopperHornItem.CopperHornVariant.BASS);
    public static final ResourceKey<Instrument> SECRET_LAKE_TEAR_COPPER_HORN_HARMONY = registerCopperHornKey("secret_lake_tear", CopperHornItem.CopperHornVariant.HARMONY);
    public static final ResourceKey<Instrument> FEARLESS_RIVER_GIFT_COPPER_HORN = registerCopperHornKey("fearless_river_gift");
    public static final ResourceKey<Instrument> FEARLESS_RIVER_GIFT_COPPER_HORN_BASS = registerCopperHornKey("fearless_river_gift", CopperHornItem.CopperHornVariant.BASS);
    public static final ResourceKey<Instrument> FEARLESS_RIVER_GIFT_COPPER_HORN_HARMONY = registerCopperHornKey("fearless_river_gift", CopperHornItem.CopperHornVariant.HARMONY);
    public static final ResourceKey<Instrument> SWEET_MOON_LOVE_COPPER_HORN = registerCopperHornKey("sweet_moon_love");
    public static final ResourceKey<Instrument> SWEET_MOON_LOVE_COPPER_HORN_BASS = registerCopperHornKey("sweet_moon_love", CopperHornItem.CopperHornVariant.BASS);
    public static final ResourceKey<Instrument> SWEET_MOON_LOVE_COPPER_HORN_HARMONY = registerCopperHornKey("sweet_moon_love", CopperHornItem.CopperHornVariant.HARMONY);

    //#endregion

    //#region Methods

    /**
     * Register all {@link MineWorld MineWorld} {@link Instrument Instruments}
     */
    public static void registerInstruments() {
        registerInstrument(FLY_GOAT_HORN, MWSounds.GOAT_HORN_FLY);
        registerInstrument(RESIST_GOAT_HORN, MWSounds.GOAT_HORN_RESIST);
        registerInstrument(GREAT_SKY_FALLING_COPPER_HORN, MWSounds.COPPER_HORN_GREAT_SKY_FALLING);
        registerInstrument(GREAT_SKY_FALLING_COPPER_HORN_BASS, MWSounds.COPPER_HORN_GREAT_SKY_FALLING_BASS);
        registerInstrument(GREAT_SKY_FALLING_COPPER_HORN_HARMONY, MWSounds.COPPER_HORN_GREAT_SKY_FALLING_HARMONY);
        registerInstrument(OLD_HYMN_RESTING_COPPER_HORN, MWSounds.COPPER_HORN_OLD_HYMN_RESTING);
        registerInstrument(OLD_HYMN_RESTING_COPPER_HORN_BASS, MWSounds.COPPER_HORN_OLD_HYMN_RESTING_BASS);
        registerInstrument(OLD_HYMN_RESTING_COPPER_HORN_HARMONY, MWSounds.COPPER_HORN_OLD_HYMN_RESTING_HARMONY);
        registerInstrument(PURE_WATER_DESIRE_COPPER_HORN, MWSounds.COPPER_HORN_PURE_WATER_DESIRE);
        registerInstrument(PURE_WATER_DESIRE_COPPER_HORN_BASS, MWSounds.COPPER_HORN_PURE_WATER_DESIRE_BASS);
        registerInstrument(PURE_WATER_DESIRE_COPPER_HORN_HARMONY, MWSounds.COPPER_HORN_PURE_WATER_DESIRE_HARMONY);
        registerInstrument(MUMBLE_FIRE_MEMORY_COPPER_HORN, MWSounds.COPPER_HORN_MUMBLE_FIRE_MEMORY);
        registerInstrument(MUMBLE_FIRE_MEMORY_COPPER_HORN_BASS, MWSounds.COPPER_HORN_MUMBLE_FIRE_MEMORY_BASS);
        registerInstrument(MUMBLE_FIRE_MEMORY_COPPER_HORN_HARMONY, MWSounds.COPPER_HORN_MUMBLE_FIRE_MEMORY_HARMONY);
        registerInstrument(DRY_URGE_ANGER_COPPER_HORN, MWSounds.COPPER_HORN_DRY_URGE_ANGER);
        registerInstrument(DRY_URGE_ANGER_COPPER_HORN_BASS, MWSounds.COPPER_HORN_DRY_URGE_ANGER_BASS);
        registerInstrument(DRY_URGE_ANGER_COPPER_HORN_HARMONY, MWSounds.COPPER_HORN_DRY_URGE_ANGER_HARMONY);
        registerInstrument(CLEAR_TEMPER_JOURNEY_COPPER_HORN, MWSounds.COPPER_HORN_CLEAR_TEMPER_JOURNEY);
        registerInstrument(CLEAR_TEMPER_JOURNEY_COPPER_HORN_BASS, MWSounds.COPPER_HORN_CLEAR_TEMPER_JOURNEY_BASS);
        registerInstrument(CLEAR_TEMPER_JOURNEY_COPPER_HORN_HARMONY, MWSounds.COPPER_HORN_CLEAR_TEMPER_JOURNEY_HARMONY);
        registerInstrument(FRESH_NEST_THOUGHT_COPPER_HORN, MWSounds.COPPER_HORN_FRESH_NEST_THOUGHT);
        registerInstrument(FRESH_NEST_THOUGHT_COPPER_HORN_BASS, MWSounds.COPPER_HORN_FRESH_NEST_THOUGHT_BASS);
        registerInstrument(FRESH_NEST_THOUGHT_COPPER_HORN_HARMONY, MWSounds.COPPER_HORN_FRESH_NEST_THOUGHT_HARMONY);
        registerInstrument(SECRET_LAKE_TEAR_COPPER_HORN, MWSounds.COPPER_HORN_SECRET_LAKE_TEAR);
        registerInstrument(SECRET_LAKE_TEAR_COPPER_HORN_BASS, MWSounds.COPPER_HORN_SECRET_LAKE_TEAR_BASS);
        registerInstrument(SECRET_LAKE_TEAR_COPPER_HORN_HARMONY, MWSounds.COPPER_HORN_SECRET_LAKE_TEAR_HARMONY);
        registerInstrument(FEARLESS_RIVER_GIFT_COPPER_HORN, MWSounds.COPPER_HORN_FEARLESS_RIVER_GIFT);
        registerInstrument(FEARLESS_RIVER_GIFT_COPPER_HORN_BASS, MWSounds.COPPER_HORN_FEARLESS_RIVER_GIFT_BASS);
        registerInstrument(FEARLESS_RIVER_GIFT_COPPER_HORN_HARMONY, MWSounds.COPPER_HORN_FEARLESS_RIVER_GIFT_HARMONY);
        registerInstrument(SWEET_MOON_LOVE_COPPER_HORN, MWSounds.COPPER_HORN_SWEET_MOON_LOVE);
        registerInstrument(SWEET_MOON_LOVE_COPPER_HORN_BASS, MWSounds.COPPER_HORN_SWEET_MOON_LOVE_BASS);
        registerInstrument(SWEET_MOON_LOVE_COPPER_HORN_HARMONY, MWSounds.COPPER_HORN_SWEET_MOON_LOVE_HARMONY);
    }

    /**
     * Register an {@link Instrument Instrument}
     *
     * @param instrumentKey {@link ResourceKey<Instrument> The Instrument Resource Key}
     * @param soundSupplier {@link RegistryObject<SoundEvent> The Sound Event Registry Object}
     */
    private static void registerInstrument(final ResourceKey<Instrument> instrumentKey, final RegistryObject<SoundEvent> soundSupplier) {
        soundSupplier.getHolder().ifPresent(soundHolder -> Registry.register(BuiltInRegistries.INSTRUMENT, instrumentKey, new Instrument(soundHolder, 140, 256.0F)));
    }

    /**
     * Register an {@link Instrument Instrument} {@link ResourceKey<Instrument> Resource Key}
     * for a {@link InstrumentItem Goat Horn Item}
     *
     * @param name {@link String The Instrument name}
     * @return {@link ResourceKey<Instrument> The Instrument Resource Key}
     */
    private static ResourceKey<Instrument> registerGoatHornKey(final String name) {
        return registerInstrumentKey(name + "_goat_horn");
    }

    /**
     * Register an {@link Instrument Instrument} {@link ResourceKey<Instrument> Resource Key}
     * for a {@link CopperHornItem Copper Horn}
     *
     * @param name {@link String The Instrument name}
     * @return {@link ResourceKey<Instrument> The Instrument Resource Key}
     */
    private static ResourceKey<Instrument> registerCopperHornKey(final String name) {
        return registerCopperHornKey(name, CopperHornItem.CopperHornVariant.REGULAR);
    }

    /**
     * Register an {@link Instrument Instrument} {@link ResourceKey<Instrument> Resource Key}
     * for a {@link CopperHornItem Copper Horn}
     *
     * @param name {@link String The Instrument name}
     * @param variant {@link CopperHornItem.CopperHornVariant The Copper Horn Variant}
     * @return {@link ResourceKey<Instrument> The Instrument Resource Key}
     */
    private static ResourceKey<Instrument> registerCopperHornKey(final String name, final CopperHornItem.CopperHornVariant variant) {
        return registerInstrumentKey(name + "_copper_horn" + variant.suffix());
    }

    /**
     * Register an {@link Instrument Instrument} {@link ResourceKey<Instrument> Resource Key}
     *
     * @param name {@link String The Instrument name}
     * @return {@link ResourceKey<Instrument> The Instrument Resource Key}
     */
    private static ResourceKey<Instrument> registerInstrumentKey(final String name) {
        return ResourceKey.create(Registries.INSTRUMENT, ResourceHelper.resourceLocation(name));
    }

    //#endregion
}