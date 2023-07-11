package org.mineworld.core;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegisterHelper;

/**
 * {@link MineWorld MineWorld} tags
 */
public class MWTags {

    /**
     * {@link TagKey<Block> Block tags}
     */
    public static class Blocks {

        public static TagKey<Block> LECTERNS = RegisterHelper.registerBlockTag("lecterns");

    }

    /**
     * {@link TagKey<Item> Item tags}
     */
    public static class Items {

        public static TagKey<Item> LANTERNS = RegisterHelper.registerItemTag("lanterns");
        public static TagKey<Item> BOOKSHELVES = RegisterHelper.registerItemTag("bookshelves");

    }

}