package org.mineworld.client.renderer;

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
import org.mineworld.entity.vehicle.MWMinecartTNT;

/**
 * Renderer class for a {@link MWMinecartTNT MineWorld tnt minecart}
 */
@OnlyIn(Dist.CLIENT)
public class MWTntMinecartRenderer extends MinecartRenderer<MWMinecartTNT> {

    /**
     * {@link BlockRenderDispatcher The block renderer reference}
     */
    private final BlockRenderDispatcher blockRenderer;

    /**
     * Constructor. Set the {@link BlockRenderDispatcher block renderer reference}
     *
     * @param context {@link EntityRendererProvider.Context The entity render provider context}
     */
    public MWTntMinecartRenderer(EntityRendererProvider.Context context) {
        super(context, ModelLayers.TNT_MINECART);
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    /**
     * Render the {@link MWMinecartTNT tnt minecart}
     *
     * @param minecart {@link MWMinecartTNT The tnt minecart}
     * @param partialTicks {@link Float The tnt minecart partial ticks}
     * @param tntBlockState {@link BlockState The tnt minecart block state}
     * @param poseStack {@link PoseStack The tnt minecart pose stack}
     * @param multiBufferSource {@link MultiBufferSource The rendering multi buffer source}
     * @param packedLight {@link Integer The world packed light}
     */
    @Override
    protected void renderMinecartContents(final MWMinecartTNT minecart, final float partialTicks, final @NotNull BlockState tntBlockState, final @NotNull PoseStack poseStack, final @NotNull MultiBufferSource multiBufferSource, final int packedLight) {
        int fuse = minecart.getFuse();
        if (fuse > -1 && (float)fuse - partialTicks + 1.0F < 10.0F) {
            float fuseFactor = 1.0F - ((float)fuse - partialTicks + 1.0F) / 10.0F;
            fuseFactor = Mth.clamp(fuseFactor, 0.0F, 1.0F);
            fuseFactor *= fuseFactor;
            fuseFactor *= fuseFactor;
            final float fuseFactorScale = 1.0F + fuseFactor * 0.3F;
            poseStack.scale(fuseFactorScale, fuseFactorScale, fuseFactorScale);
        }
        renderWhiteSolidBlock(this.blockRenderer, tntBlockState, poseStack, multiBufferSource, packedLight, fuse > -1 && fuse / 5 % 2 == 0);
    }

    /**
     * Render the {@link MWMinecartTNT tnt minecart primed tnt}
     *
     * @param blockRendererDispatcher {@link BlockRenderDispatcher The block rendered dispatcher}
     * @param tntBlockState {@link BlockState The tnt block state}
     * @param poseStack {@link PoseStack The tnt minecart pose stack}
     * @param multiBufferSource {@link MultiBufferSource The rendering multi buffer source}
     * @param packedLight {@link Integer The world packed light}
     * @param isPrimed {@link Boolean If the tnt is primed}
     */
    public static void renderWhiteSolidBlock(BlockRenderDispatcher blockRendererDispatcher, BlockState tntBlockState, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, boolean isPrimed) {
        blockRendererDispatcher.renderSingleBlock(tntBlockState, poseStack, multiBufferSource, packedLight, isPrimed ? OverlayTexture.pack(OverlayTexture.u(1.0F), 10) : OverlayTexture.NO_OVERLAY);
    }

}