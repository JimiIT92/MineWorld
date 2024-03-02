package org.mineworld.client.renderer.vehicle;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MinecartRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.entity.vehicle.MWMinecartTnt;

/**
 * Renderer class for a {@link MineWorld MineWorld} {@link MWMinecartTnt TNT Minecart}
 */
@OnlyIn(Dist.CLIENT)
public class MWTntMinecartRenderer extends MinecartRenderer<MWMinecartTnt> {

    /**
     * {@link BlockRenderDispatcher The Block Renderer reference}
     */
    private final BlockRenderDispatcher blockRenderer;

    /**
     * Constructor. Set the renderer properties
     *
     * @param context {@link EntityRendererProvider.Context The Render Context}
     */
    public MWTntMinecartRenderer(final EntityRendererProvider.Context context) {
        super(context, ModelLayers.TNT_MINECART);
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    /**
     * Render the {@link MWMinecartTnt TNT Minecart}
     *
     * @param minecart {@link MWMinecartTnt The TNT Minecart}
     * @param partialTicks {@link Float The Entity partial ticks}
     * @param tntBlockState {@link BlockState The TNT Block State}
     * @param poseStack {@link PoseStack The Entity pose}
     * @param buffer {@link MultiBufferSource The Entity buffer}
     * @param packedLight {@link Integer The level packed light}
     */
    @Override
    protected void renderMinecartContents(final MWMinecartTnt minecart, final float partialTicks, final @NotNull BlockState tntBlockState, final @NotNull PoseStack poseStack, final @NotNull MultiBufferSource buffer, final int packedLight) {
        final int fuse = minecart.getFuse();
        if (fuse > -1 && (float)fuse - partialTicks + 1.0F < 10.0F) {
            float fuseFactor = 1.0F - ((float)fuse - partialTicks + 1.0F) / 10.0F;
            fuseFactor = Mth.clamp(fuseFactor, 0.0F, 1.0F);
            fuseFactor *= fuseFactor;
            fuseFactor *= fuseFactor;
            final float fuseFactorScale = 1.0F + fuseFactor * 0.3F;
            poseStack.scale(fuseFactorScale, fuseFactorScale, fuseFactorScale);
        }
        renderTntBlock(tntBlockState, poseStack, buffer, packedLight, fuse > -1 && fuse / 5 % 2 == 0);
    }

    /**
     * Render the {@link MWMinecartTnt TNT Minecart}
     *
     * @param tntBlockState {@link BlockState The TNT Block State}
     * @param poseStack {@link PoseStack The Entity pose}
     * @param buffer {@link MultiBufferSource The Entity buffer}
     * @param packedLight {@link Integer The level packed light}
     * @param isPrimed {@link Boolean If the TNT is primed}
     */
    private void renderTntBlock(final BlockState tntBlockState, final PoseStack poseStack, final MultiBufferSource buffer, final int packedLight, final boolean isPrimed) {
        this.blockRenderer.renderSingleBlock(tntBlockState, poseStack, buffer, packedLight, isPrimed ? OverlayTexture.pack(OverlayTexture.u(1.0F), 10) : OverlayTexture.NO_OVERLAY);
    }

}