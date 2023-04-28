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

    }

    /**
     * {@link TagKey<Item> Item tags}
     */
    public static class Items {

        public static final TagKey<Item> LANTERNS = RegisterHelper.registerItemTag("lanterns");

    }

}