package org.mineworld.core;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegisterHelper;

/**
 * {@link MineWorld MineWorld} tags
 */
public final class MWTags {

    /**
     * {@link TagKey<Block> Block tags}
     */
    public static class Blocks {

        public static final TagKey<Block> LECTERNS = RegisterHelper.registerBlockTag("lecterns");
        public static final TagKey<Block> ETHEREAL_PORTAL_FRAME_BLOCKS = RegisterHelper.registerBlockTag("ethereal_portal_frame_blocks");
        public static final TagKey<Block> CATTAIL_PLACEABLE = RegisterHelper.registerBlockTag("cattail_placeable");
        public static final TagKey<Block> END_FIRE_BASE_BLOCKS = RegisterHelper.registerBlockTag("end_fire_base_blocks");
        public static final TagKey<Block> SCULK_FIRE_BASE_BLOCKS = RegisterHelper.registerBlockTag("sculk_fire_base_blocks");
        public static final TagKey<Block> ANCIENT_TEMPLE_CANNOT_REPLACE = RegisterHelper.registerBlockTag("ancient_temple_cannot_replace");
    }

    /**
     * {@link TagKey<Item> Item tags}
     */
    public static class Items {

        public static final TagKey<Item> LANTERNS = RegisterHelper.registerItemTag("lanterns");
        public static final TagKey<Item> BOOKSHELVES = RegisterHelper.registerItemTag("bookshelves");

    }

}