package org.mineworld.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.mineworld.MineWorld;
import org.mineworld.helper.ResourceHelper;

/**
 * {@link MineWorld MineWorld} {@link TagKey Tags}
 */
public final class MWTags {

    //#region Tags

    /**
     * {@link TagKey<Block> Block tags}
     */
    public static class Blocks {
        public static final TagKey<Block> LECTERNS = registerBlockTag("lecterns");
        public static final TagKey<Block> ETHEREAL_PORTAL_FRAME_BLOCKS = registerBlockTag("ethereal_portal_frame_blocks");
        public static final TagKey<Block> CATTAIL_PLACEABLE = registerBlockTag("cattail_placeable");
        public static final TagKey<Block> END_FIRE_BASE_BLOCKS = registerBlockTag("end_fire_base_blocks");
        public static final TagKey<Block> SCULK_FIRE_BASE_BLOCKS = registerBlockTag("sculk_fire_base_blocks");
        public static final TagKey<Block> ANCIENT_TEMPLE_CANNOT_REPLACE = registerBlockTag("ancient_temple_cannot_replace");
    }

    /**
     * {@link TagKey<Item> Item tags}
     */
    public static class Items {

        public static final TagKey<Item> LANTERNS = registerItemTag("lanterns");
        public static final TagKey<Item> BOOKSHELVES = registerItemTag("bookshelves");

    }

    /**
     * {@link TagKey<Instrument> Instrument tags}
     */
    public static class Instruments {

        public static final TagKey<Instrument> BASS_COPPER_HORNS = registerInstrumentTag("bass_copper_horns");
        public static final TagKey<Instrument> HARMONY_COPPER_HORNS = registerInstrumentTag("harmony_copper_horns");
        public static final TagKey<Instrument> MELODY_COPPER_HORNS = registerInstrumentTag("melody_copper_horns");
        public static final TagKey<Instrument> COPPER_HORNS = registerInstrumentTag("copper_horns");

    }

    //#endregion

    //#region Methods

    /**
     * Register a {@link TagKey<Block> Block Tag}
     *
     * @param name {@link String The Tag name}
     */
    public static TagKey<Block> registerBlockTag(final String name) {
        return BlockTags.create(ResourceHelper.resourceLocation(name));
    }

    /**
     * Register an {@link TagKey<Item> Item Tag}
     *
     * @param name {@link String The Tag name}
     */
    public static TagKey<Item> registerItemTag(final String name) {
        return ItemTags.create(ResourceHelper.resourceLocation(name));
    }

    /**
     * Register an {@link TagKey<Instrument> Instrument Tag}
     *
     * @param name {@link String The Tag name}
     */
    public static TagKey<Instrument> registerInstrumentTag(final String name) {
        return TagKey.create(Registries.INSTRUMENT, ResourceHelper.resourceLocation(name));
    }

    //#endregion

}