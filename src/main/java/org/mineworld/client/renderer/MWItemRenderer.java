package org.mineworld.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.helper.PropertyHelper;

/**
 * {@link MineWorld MineWorld} {@link BlockEntityWithoutLevelRenderer custom item renderer}
 */
@OnlyIn(Dist.CLIENT)
public final class MWItemRenderer extends BlockEntityWithoutLevelRenderer {

    /**
     * {@link BlockEntityRenderDispatcher The block entity renderer dispatcher}
     */
    private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;
    /**
     * {@link EntityModelSet The entity model set}
     */
    private final EntityModelSet entityModelSet;
    /**
     * {@link ChestBlockEntity The spruce chest block entity}
     */
    private final ChestBlockEntity spruceChest = PropertyHelper.getChestBlockEntity(WoodType.SPRUCE, BlockPos.ZERO, MWBlocks.SPRUCE_CHEST.get().defaultBlockState(), false);
    /**
     * {@link ChestBlockEntity The spruce trapped chest block entity}
     */
    private final ChestBlockEntity trappedSpruceChest = PropertyHelper.getChestBlockEntity(WoodType.SPRUCE, BlockPos.ZERO, MWBlocks.SPRUCE_TRAPPED_CHEST.get().defaultBlockState(), true);

    /**
     * Constructor. Set the {@link EntityModelSet entity model set}
     */
    public MWItemRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        this.blockEntityRenderDispatcher = Minecraft.getInstance().getBlockEntityRenderDispatcher();
        this.entityModelSet = Minecraft.getInstance().getEntityModels();
        initModels();
    }

    /**
     * Reinitialize the {@link MineWorld Mineworld} models
     * on resource reloads
     *
     * @param resourceManager {@link ResourceManager Resource Manager}
     */
    public void onResourceManagerReload(@NotNull ResourceManager resourceManager) {
        initModels();
    }

    /**
     * Initialize the {@link MineWorld MineWorld} models
     */
    private void initModels() {

    }

    /**
     * Render custom models for some {@link ItemStack item stacks}
     *
     * @param stack {@link ItemStack The item stack to render}
     * @param displayContext {@link ItemDisplayContext The item display context}
     * @param pose {@link PoseStack The pose stack}
     * @param buffer {@link MultiBufferSource The multi buffer renderer source}
     * @param packedLight {@link Integer The client packed light}
     * @param packedOverlay {@link Integer The client packed overlay}
     */
    @Override
    public void renderByItem(final ItemStack stack, final @NotNull ItemDisplayContext displayContext, final @NotNull PoseStack pose, final @NotNull MultiBufferSource buffer, final int packedLight, final int packedOverlay) {
        final Item item = stack.getItem();
        if(item instanceof BlockItem blockItem) {
            final Block block = blockItem.getBlock();
            final BlockState blockState = block.defaultBlockState();
            BlockEntity blockentity = null;
            if(blockState.is(MWBlocks.SPRUCE_CHEST.get())) {
                blockentity = this.spruceChest;
            }
            else if(blockState.is(MWBlocks.SPRUCE_TRAPPED_CHEST.get())) {
                blockentity = this.trappedSpruceChest;
            }
            if(blockentity != null) {
                this.blockEntityRenderDispatcher.renderItem(blockentity, pose, buffer, packedLight, packedOverlay);
            }
        }
    }

}