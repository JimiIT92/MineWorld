package org.mineworld.client.renderer.block;

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
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.entity.MWPrimedTnt;

/**
 * Renderer class for a {@link MineWorld MineWorld} {@link MWPrimedTnt Primed TNT}
 */
@OnlyIn(Dist.CLIENT)
public class MWPrimedTntRenderer extends EntityRenderer<MWPrimedTnt> {

    /**
     * {@link BlockRenderDispatcher The Block Renderer reference}
     */
    private final BlockRenderDispatcher blockRenderer;

    /**
     * Constructor. Set the renderer properties
     *
     * @param context {@link EntityRendererProvider.Context The Render Context}
     */
    public MWPrimedTntRenderer(final EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.5F;
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    /**
     * Render the {@link MWPrimedTnt Primed TNT}
     *
     * @param tnt {@link MWPrimedTnt The Primed TNT}
     * @param yaw {@link Float The Entity yaw}
     * @param partialTicks {@link Float The Entity partial ticks}
     * @param poseStack {@link PoseStack The Entity pose}
     * @param buffer {@link MultiBufferSource The Entity buffer}
     * @param packedLight {@link Integer The level packed light}
     */
    @Override
    public void render(final MWPrimedTnt tnt, final float yaw, final float partialTicks, final PoseStack poseStack, final @NotNull MultiBufferSource buffer, final int packedLight) {
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
        TntMinecartRenderer.renderWhiteSolidBlock(this.blockRenderer, getTntBlockState(tnt), poseStack, buffer, packedLight, fuse / 5 % 2 == 0);
        poseStack.popPose();
        super.render(tnt, yaw, partialTicks, poseStack, buffer, packedLight);
    }

    /**
     * Get the {@link BlockState Block State to render}
     * based on the {@link MWPrimedTnt.Type Primed TNT Type}
     *
     * @param tnt {@link MWPrimedTnt The Primed TNT}
     * @return {@link BlockState The Block State to render}
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
     * Get the {@link ResourceLocation Entity texture}
     *
     * @param tnt {@link MWPrimedTnt The Primed TNT}
     * @return {@link TextureAtlas#LOCATION_BLOCKS The Blocks Texture Atlas Location}
     */
    @Override
    public @NotNull ResourceLocation getTextureLocation(final @NotNull MWPrimedTnt tnt) {
        return TextureAtlas.LOCATION_BLOCKS;
    }

}