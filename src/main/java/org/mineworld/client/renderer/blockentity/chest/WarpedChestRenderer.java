package org.mineworld.client.renderer.blockentity.chest;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * {@link ChestRenderer Renderer} class for a warped chest
 */
@OnlyIn(Dist.CLIENT)
public class WarpedChestRenderer extends AbstractChestRenderer {

    /**
     * Constructor. Set up the {@link BlockEntityRendererProvider.Context render context}
     *
     * @param context {@link BlockEntityRendererProvider.Context The block render context}
     */
    public WarpedChestRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    /**
     * Get the {@link WoodType chest wood type}
     *
     * @return {@link WoodType#WARPED Warped wood type}
     */
    @Override
    protected WoodType getWoodType() {
        return WoodType.WARPED;
    }

}