package org.mineworld.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.block.MWChestBlock;
import org.mineworld.block.MWTrappedChestBlock;

/**
 * {@link MineWorld MineWorld} {@link BlockEntityWithoutLevelRenderer Custom Item Renderer}
 */
@OnlyIn(Dist.CLIENT)
public final class MWItemRenderer extends BlockEntityWithoutLevelRenderer {

    /**
     * Constructor. Set the Item Renderer Properties
     */
    public MWItemRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    /**
     * Render a special for some {@link ItemStack Items}
     *
     * @param itemStack {@link ItemStack The current Item Stack}
     * @param displayContext {@link ItemDisplayContext The Item Display Context}
     * @param poseStack {@link PoseStack The Item Pose Stack}
     * @param buffer {@link MultiBufferSource The Item Buffer}
     * @param packedLight {@link Integer The level packed light}
     * @param packedOverlay {@link Integer The client packed overlay}
     */
    @Override
    public void renderByItem(final @NotNull ItemStack itemStack, final @NotNull ItemDisplayContext displayContext, final @NotNull PoseStack poseStack, final @NotNull MultiBufferSource buffer, final int packedLight, final int packedOverlay) {
        final Item item = itemStack.getItem();
        if(item instanceof BlockItem blockItem) {
            final Block block = blockItem.getBlock();
            final BlockState blockState = block.defaultBlockState();
            if(block instanceof MWChestBlock chestBlock) {
                renderBlockEntity(MWChestBlock.getBlockEntity(chestBlock.getWoodType(), BlockPos.ZERO, blockState), poseStack, buffer, packedLight, packedOverlay);
                return;
            }
            if(block instanceof MWTrappedChestBlock trappedChestBlock) {
                renderBlockEntity(MWTrappedChestBlock.getBlockEntity(trappedChestBlock.getWoodType(), BlockPos.ZERO, blockState), poseStack, buffer, packedLight, packedOverlay);
            }
        }
    }

    /**
     * Render a {@link BlockEntity Block Entity}
     *
     * @param blockEntity {@link BlockEntity The Block Entity to render}
     * @param poseStack {@link PoseStack The Item Pose Stack}
     * @param buffer {@link MultiBufferSource The Item Buffer}
     * @param packedLight {@link Integer The level packed light}
     * @param packedOverlay {@link Integer The client packed overlay}
     */
    private void renderBlockEntity(final BlockEntity blockEntity, final @NotNull PoseStack poseStack, final @NotNull MultiBufferSource buffer, final int packedLight, final int packedOverlay) {
        this.blockEntityRenderDispatcher.renderItem(blockEntity, poseStack, buffer, packedLight, packedOverlay);
    }

}