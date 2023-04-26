package org.mineworld.client.renderer.blockentity.chest;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * {@link ChestRenderer Renderer} class for a spruce trapped chest
 */
@OnlyIn(Dist.CLIENT)
public class SpruceTrappedChestRenderer extends SpruceChestRenderer {

    /**
     * Constructor. Set up the {@link BlockEntityRendererProvider.Context render context}
     *
     * @param context {@link BlockEntityRendererProvider.Context The block render context}
     */
    public SpruceTrappedChestRenderer(final BlockEntityRendererProvider.Context context) {
        super(context);
    }

    /**
     * Check if the chest is a trapped chest
     *
     * @return {@link Boolean True}
     */
    @Override
    protected boolean isTrappedChest() {
        return true;
    }

}