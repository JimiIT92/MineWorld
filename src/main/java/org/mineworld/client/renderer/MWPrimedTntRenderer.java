package org.mineworld.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWBlocks;
import org.mineworld.entity.MWPrimedTnt;

/**
 * Renderer class for a {@link MWPrimedTnt MineWorld primed tnt}
 */
@OnlyIn(Dist.CLIENT)
public class MWPrimedTntRenderer extends EntityRenderer<MWPrimedTnt> {

    /**
     * {@link BlockRenderDispatcher The block renderer reference}
     */
    private final BlockRenderDispatcher blockRenderer;

    /**
     * Constructor. Set the {@link BlockRenderDispatcher block renderer reference}
     * and the {@link Float shadow radius}
     *
     * @param context {@link EntityRendererProvider.Context The entity render provider context}
     */
    public MWPrimedTntRenderer(final EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.5F;
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    /**
     * Render the {@link MWPrimedTnt primed tnt}
     *
     * @param tnt {@link MWPrimedTnt The primed tnt to render}
     * @param yaw {@link Float The primed tnt yaw}
     * @param partialTicks {@link Float The primed tnt partial ticks}
     * @param poseStack {@link PoseStack The primed tnt pose stack}
     * @param multiBufferSource {@link MultiBufferSource The rendering multi buffer source}
     * @param packedLight {@link Integer The world packed light}
     */
    @Override
    public void render(final MWPrimedTnt tnt, final float yaw, final float partialTicks, final PoseStack poseStack, final @NotNull MultiBufferSource multiBufferSource, final int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.5F, 0.0F);
        final int fuse = tnt.getFuse();
        if ((float)fuse - partialTicks + 1.0F < 10.0F) {
            float fuseFactor = 1.0F - ((float)fuse - partialTicks + 1.0F) / 10.0F;
            fuseFactor = Mth.clamp(fuseFactor, 0.0F, 1.0F);
            fuseFactor *= fuseFactor;
            fuseFactor *= fuseFactor;
            final float fuseScale = 1.0F + fuseFactor * 0.3F;
            poseStack.scale(fuseScale, fuseScale, fuseScale);
        }

        poseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
        poseStack.translate(-0.5F, -0.5F, 0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        TntMinecartRenderer.renderWhiteSolidBlock(this.blockRenderer, getTntBlockState(tnt), poseStack, multiBufferSource, packedLight, fuse / 5 % 2 == 0);
        poseStack.popPose();
        super.render(tnt, yaw, partialTicks, poseStack, multiBufferSource, packedLight);
    }

    /**
     * Get the {@link BlockState tnt block state to render}
     * based on the {@link MWPrimedTnt primed tnt}
     *
     * @param tnt {@link MWPrimedTnt The primed tnt}
     * @return {@link BlockState The tnt block state to render}
     */
    private BlockState getTntBlockState(final MWPrimedTnt tnt) {
        return switch (tnt.getTntType()) {
            case MEGA -> MWBlocks.MEGA_TNT.get().defaultBlockState();
            case SUPER -> MWBlocks.SUPER_TNT.get().defaultBlockState();
            case HYPER -> MWBlocks.HYPER_TNT.get().defaultBlockState();
            case DISGUISED_GRASS -> MWBlocks.DISGUISED_GRASS_TNT.get().defaultBlockState();
            case DISGUISED_DIRT -> MWBlocks.DISGUISED_DIRT_TNT.get().defaultBlockState();
            case DISGUISED_SAND -> MWBlocks.DISGUISED_SAND_TNT.get().defaultBlockState();
            case DISGUISED_RED_SAND -> MWBlocks.DISGUISED_RED_SAND_TNT.get().defaultBlockState();
            case DISGUISED_STONE -> MWBlocks.DISGUISED_STONE_TNT.get().defaultBlockState();
            case DISGUISED_CAKE -> MWBlocks.DISGUISED_CAKE_TNT.get().defaultBlockState();
        };
    }

    /**
     * Get the {@link MWPrimedTnt primed tnt} {@link ResourceLocation texture location}
     *
     * @param tnt {@link MWPrimedTnt The primed tnt}
     * @return {@link TextureAtlas#LOCATION_BLOCKS The blocks texture atlas location}
     */
    @Override
    public @NotNull ResourceLocation getTextureLocation(final @NotNull MWPrimedTnt tnt) {
        return TextureAtlas.LOCATION_BLOCKS;
    }

}