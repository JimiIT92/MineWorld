package org.mineworld.item;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.helper.PropertyHelper;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link ItemNameBlockItem Item Name Block Item}
 */
public class MWItemNameBlockItem extends ItemNameBlockItem {

    /**
     * Constructor. Set the {@link Properties Item Properties}
     *
     * @param blockSupplier {@link Supplier<Block> The Supplier for the Block that will be placed by this Item}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     */
    public MWItemNameBlockItem(final Supplier<? extends Block> blockSupplier, final FeatureFlag... featureFlags) {
        super(blockSupplier.get(), PropertyHelper.item(featureFlags));
    }

    /**
     * Assign the special rendering to this {@link Item Item}
     *
     * @param consumer {@link Consumer<IClientItemExtensions> The Client Item Rendered Consumer}
     */
    @Override
    public void initializeClient(final @NotNull Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            /**
             * Get the {@link BlockEntityWithoutLevelRenderer Item Renderer}
             *
             * @return The {@link MineWorld MineWorld} {@link MineWorld#getItemsRenderer() Item Renderer}
             */
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return MineWorld.getItemsRenderer();
            }
        });
    }

}