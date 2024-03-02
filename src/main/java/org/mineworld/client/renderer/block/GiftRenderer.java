package org.mineworld.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.entity.block.GiftBlockEntity;
import org.mineworld.helper.TextureHelper;

/**
 * {@link MineWorld MineWorld} {@link ChestRenderer Gift Block Renderer}
 */
@OnlyIn(Dist.CLIENT)
public class GiftRenderer extends ChestRenderer<GiftBlockEntity> {

    /**
     * Constructor. Set the renderer properties
     *
     * @param context {@link EntityRendererProvider.Context The Render Context}
     */
    public GiftRenderer(final BlockEntityRendererProvider.Context context) {
        super(context);
    }

    /**
     * Get the {@link Material Gift Material}
     *
     * @param gift {@link GiftBlockEntity The Gift Block Entity}
     * @param chestType {@link ChestType The Gift Type}
     * @return {@link Material The Gift Material}
     */
    @Override
    protected @NotNull Material getMaterial(final @NotNull GiftBlockEntity gift, final @NotNull ChestType chestType) {
        return new Material(Sheets.CHEST_SHEET, TextureHelper.chest("gift"));
    }

    /**
     * Render the {@link GiftBlockEntity Gift}
     *
     * @param gift {@link GiftBlockEntity The Gift}
     * @param yaw {@link Float The Entity yaw}
     * @param partialTicks {@link Float The Entity partial ticks}
     * @param poseStack {@link PoseStack The Entity pose}
     * @param buffer {@link MultiBufferSource The Entity buffer}
     * @param packedLight {@link Integer The level packed light}
     */
    @Override
    public void render(final GiftBlockEntity gift, final float yaw, final PoseStack poseStack, final @NotNull MultiBufferSource buffer, final int packedLight, final int partialTicks) {
        Level level = gift.getLevel();
        boolean flag = level != null;
        BlockState blockstate = flag ? gift.getBlockState() : MWBlocks.GIFT.get().defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
        Block block = blockstate.getBlock();
        poseStack.pushPose();
        float yRotation = blockstate.getValue(ChestBlock.FACING).toYRot();
        poseStack.translate(0.5F, 0.5F, 0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(-yRotation));
        poseStack.translate(-0.5F, -0.5F, -0.5F);
        Material material = this.getMaterial(gift, ChestType.SINGLE);
        VertexConsumer vertexconsumer = material.buffer(buffer, RenderType::entityCutout);
        this.render(poseStack, vertexconsumer, this.lid, this.lock, this.bottom, packedLight, partialTicks);
        poseStack.popPose();
    }

    /**
     * Render the {@link GiftBlockEntity Gift}
     *
     * @param poseStack {@link PoseStack The Gift Pose}
     * @param vertexConsumer {@link VertexConsumer The Vertex Consumer reference}
     * @param lid {@link ModelPart The Gift Lid Model Part}
     * @param lock {@link ModelPart The Gift Lock Model Part}
     * @param bottom {@link ModelPart The Gift Bottom Model Part}
     * @param packedLight {@link Integer The level packed light}
     * @param partialTicks {@link Float The Entity partial ticks}
     */
    private void render(final PoseStack poseStack, final VertexConsumer vertexConsumer, final ModelPart lid, final ModelPart lock, final ModelPart bottom, final int packedLight, final int partialTicks) {
        lid.xRot = 0;
        lock.xRot = lid.xRot;
        lid.render(poseStack, vertexConsumer, packedLight, partialTicks);
        lock.render(poseStack, vertexConsumer, packedLight, partialTicks);
        bottom.render(poseStack, vertexConsumer, packedLight, partialTicks);
    }

}