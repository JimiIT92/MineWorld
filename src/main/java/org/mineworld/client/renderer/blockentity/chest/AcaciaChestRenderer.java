package org.mineworld.client.renderer.blockentity.chest;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * {@link ChestRenderer Renderer} class for an acacia chest
 */
@OnlyIn(Dist.CLIENT)
public class AcaciaChestRenderer extends AbstractChestRenderer {

    /**
     * Constructor. Set up the {@link BlockEntityRendererProvider.Context render context}
     *
     * @param context {@link BlockEntityRendererProvider.Context The block render context}
     */
    public AcaciaChestRenderer(final BlockEntityRendererProvider.Context context) {
        super(context);
    }

    /**
     * Get the {@link WoodType chest wood type}
     *
     * @return {@link WoodType#ACACIA Acacia wood type}
     */
    @Override
    protected WoodType getWoodType() {
        return WoodType.ACACIA;
    }

}