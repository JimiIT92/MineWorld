package org.mineworld.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
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
import org.mineworld.helper.KeyHelper;

/**
 * {@link ChestRenderer Renderer} class for a {@link MineWorld MineWorld} gift
 */
@OnlyIn(Dist.CLIENT)
public class GiftRenderer extends ChestRenderer<GiftBlockEntity> {

    /**
     * Constructor. Set up the {@link BlockEntityRendererProvider.Context render context}
     *
     * @param context {@link BlockEntityRendererProvider.Context The block render context}
     */

    public GiftRenderer(final BlockEntityRendererProvider.Context context) {
        super(context);
    }

    /**
     * Get the {@link Material chest material}
     *
     * @param blockEntity {@link GiftBlockEntity The chest block entity}
     * @param chestType {@link ChestType The chest type}
     * @return {@link Material The chest material}
     */
    @Override
    protected @NotNull Material getMaterial(final @NotNull GiftBlockEntity blockEntity, final @NotNull ChestType chestType) {
        return new Material(Sheets.CHEST_SHEET, KeyHelper.entity("chest/gift"));
    }

    /**
     * Render the {@link GiftBlockEntity gift}
     *
     * @param gift {@link GiftBlockEntity The gift block entity}
     * @param yaw {@link Float The primed tnt yaw}
     * @param poseStack {@link PoseStack The primed tnt pose stack}
     * @param multiBufferSource {@link MultiBufferSource The rendering multi buffer source}
     * @param packedLight {@link Integer The world packed light}
     * @param partialTicks {@link Float The primed tnt partial ticks}
     */
    @Override
    public void render(final GiftBlockEntity gift, final float yaw, final PoseStack poseStack, final @NotNull MultiBufferSource multiBufferSource, final int packedLight, final int partialTicks) {
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
        VertexConsumer vertexconsumer = material.buffer(multiBufferSource, RenderType::entityCutout);
        this.render(poseStack, vertexconsumer, this.lid, this.lock, this.bottom, packedLight, partialTicks);
        poseStack.popPose();
    }

    /**
     * Render the {@link GiftBlockEntity gift}
     *
     * @param poseStack {@link PoseStack The primed tnt pose stack}
     * @param vertexConsumer {@link VertexConsumer The vertex consumer}
     * @param lid {@link ModelPart The lid model part}
     * @param lock {@link ModelPart The lock model part}
     * @param bottom {@link ModelPart The bottom model part}
     * @param packedLight {@link Integer The world packed light}
     * @param partialTicks {@link Float The primed tnt partial ticks}
     */
    private void render(final PoseStack poseStack, final VertexConsumer vertexConsumer, final ModelPart lid, final ModelPart lock, final ModelPart bottom, final int packedLight, final int partialTicks) {
        lid.xRot = 0;
        lock.xRot = lid.xRot;
        lid.render(poseStack, vertexConsumer, packedLight, partialTicks);
        lock.render(poseStack, vertexConsumer, packedLight, partialTicks);
        bottom.render(poseStack, vertexConsumer, packedLight, partialTicks);
    }

}