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
import org.mineworld.core.MWWoodTypes;
import org.mineworld.helper.PropertyHelper;

/**
 * {@link MineWorld MineWorld} {@link BlockEntityWithoutLevelRenderer custom id renderer}
 */
@OnlyIn(Dist.CLIENT)
public final class MWItemRenderer extends BlockEntityWithoutLevelRenderer {

    /**
     * {@link BlockEntityRenderDispatcher The block entity renderer dispatcher}
     */
    private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;
    /**
     * {@link ChestBlockEntity The spruce chest block entity}
     */
    private final ChestBlockEntity spruceChest = PropertyHelper.getChestBlockEntity(WoodType.SPRUCE, BlockPos.ZERO, MWBlocks.SPRUCE_CHEST.get().defaultBlockState(), false);
    /**
     * {@link ChestBlockEntity The spruce trapped chest block entity}
     */
    private final ChestBlockEntity trappedSpruceChest = PropertyHelper.getChestBlockEntity(WoodType.SPRUCE, BlockPos.ZERO, MWBlocks.SPRUCE_TRAPPED_CHEST.get().defaultBlockState(), true);
    /**
     * {@link ChestBlockEntity The birch chest block entity}
     */
    private final ChestBlockEntity birchChest = PropertyHelper.getChestBlockEntity(WoodType.BIRCH, BlockPos.ZERO, MWBlocks.BIRCH_CHEST.get().defaultBlockState(), false);
    /**
     * {@link ChestBlockEntity The birch trapped chest block entity}
     */
    private final ChestBlockEntity trappedBirchChest = PropertyHelper.getChestBlockEntity(WoodType.BIRCH, BlockPos.ZERO, MWBlocks.BIRCH_TRAPPED_CHEST.get().defaultBlockState(), true);
    /**
     * {@link ChestBlockEntity The jungle chest block entity}
     */
    private final ChestBlockEntity jungleChest = PropertyHelper.getChestBlockEntity(WoodType.JUNGLE, BlockPos.ZERO, MWBlocks.JUNGLE_CHEST.get().defaultBlockState(), false);
    /**
     * {@link ChestBlockEntity The jungle trapped chest block entity}
     */
    private final ChestBlockEntity trappedJungleChest = PropertyHelper.getChestBlockEntity(WoodType.JUNGLE, BlockPos.ZERO, MWBlocks.JUNGLE_TRAPPED_CHEST.get().defaultBlockState(), true);
    /**
     * {@link ChestBlockEntity The acacia chest block entity}
     */
    private final ChestBlockEntity acaciaChest = PropertyHelper.getChestBlockEntity(WoodType.ACACIA, BlockPos.ZERO, MWBlocks.ACACIA_CHEST.get().defaultBlockState(), false);
    /**
     * {@link ChestBlockEntity The acacia trapped chest block entity}
     */
    private final ChestBlockEntity trappedAcaciaChest = PropertyHelper.getChestBlockEntity(WoodType.ACACIA, BlockPos.ZERO, MWBlocks.ACACIA_TRAPPED_CHEST.get().defaultBlockState(), true);
    /**
     * {@link ChestBlockEntity The dark oak chest block entity}
     */
    private final ChestBlockEntity darkOakChest = PropertyHelper.getChestBlockEntity(WoodType.DARK_OAK, BlockPos.ZERO, MWBlocks.DARK_OAK_CHEST.get().defaultBlockState(), false);
    /**
     * {@link ChestBlockEntity The dark oak trapped chest block entity}
     */
    private final ChestBlockEntity trappedDarkOakChest = PropertyHelper.getChestBlockEntity(WoodType.DARK_OAK, BlockPos.ZERO, MWBlocks.DARK_OAK_TRAPPED_CHEST.get().defaultBlockState(), true);
    /**
     * {@link ChestBlockEntity The mangrove chest block entity}
     */
    private final ChestBlockEntity mangroveChest = PropertyHelper.getChestBlockEntity(WoodType.MANGROVE, BlockPos.ZERO, MWBlocks.MANGROVE_CHEST.get().defaultBlockState(), false);
    /**
     * {@link ChestBlockEntity The mangrove trapped chest block entity}
     */
    private final ChestBlockEntity trappedMangroveChest = PropertyHelper.getChestBlockEntity(WoodType.MANGROVE, BlockPos.ZERO, MWBlocks.MANGROVE_TRAPPED_CHEST.get().defaultBlockState(), true);
    /**
     * {@link ChestBlockEntity The cherry chest block entity}
     */
    private final ChestBlockEntity cherryChest = PropertyHelper.getChestBlockEntity(WoodType.CHERRY, BlockPos.ZERO, MWBlocks.CHERRY_CHEST.get().defaultBlockState(), false);
    /**
     * {@link ChestBlockEntity The cherry trapped chest block entity}
     */
    private final ChestBlockEntity trappedCherryChest = PropertyHelper.getChestBlockEntity(WoodType.CHERRY, BlockPos.ZERO, MWBlocks.CHERRY_TRAPPED_CHEST.get().defaultBlockState(), true);
    /**
     * {@link ChestBlockEntity The bamboo chest block entity}
     */
    private final ChestBlockEntity bambooChest = PropertyHelper.getChestBlockEntity(WoodType.BAMBOO, BlockPos.ZERO, MWBlocks.BAMBOO_CHEST.get().defaultBlockState(), false);
    /**
     * {@link ChestBlockEntity The bamboo trapped chest block entity}
     */
    private final ChestBlockEntity trappedBambooChest = PropertyHelper.getChestBlockEntity(WoodType.BAMBOO, BlockPos.ZERO, MWBlocks.BAMBOO_TRAPPED_CHEST.get().defaultBlockState(), true);
    /**
     * {@link ChestBlockEntity The crimson chest block entity}
     */
    private final ChestBlockEntity crimsonChest = PropertyHelper.getChestBlockEntity(WoodType.CRIMSON, BlockPos.ZERO, MWBlocks.CRIMSON_CHEST.get().defaultBlockState(), false);
    /**
     * {@link ChestBlockEntity The crimson trapped chest block entity}
     */
    private final ChestBlockEntity trappedCrimsonChest = PropertyHelper.getChestBlockEntity(WoodType.CRIMSON, BlockPos.ZERO, MWBlocks.CRIMSON_TRAPPED_CHEST.get().defaultBlockState(), true);
    /**
     * {@link ChestBlockEntity The warped chest block entity}
     */
    private final ChestBlockEntity warpedChest = PropertyHelper.getChestBlockEntity(WoodType.WARPED, BlockPos.ZERO, MWBlocks.WARPED_CHEST.get().defaultBlockState(), false);
    /**
     * {@link ChestBlockEntity The warped trapped chest block entity}
     */
    private final ChestBlockEntity trappedWarpedChest = PropertyHelper.getChestBlockEntity(WoodType.WARPED, BlockPos.ZERO, MWBlocks.WARPED_TRAPPED_CHEST.get().defaultBlockState(), true);
    /**
     * {@link ChestBlockEntity The apple chest block entity}
     */
    private final ChestBlockEntity appleChest = PropertyHelper.getChestBlockEntity(MWWoodTypes.APPLE, BlockPos.ZERO, MWBlocks.APPLE_CHEST.get().defaultBlockState(), false);
    /**
     * {@link ChestBlockEntity The apple trapped chest block entity}
     */
    private final ChestBlockEntity trappedAppleChest = PropertyHelper.getChestBlockEntity(MWWoodTypes.APPLE, BlockPos.ZERO, MWBlocks.APPLE_TRAPPED_CHEST.get().defaultBlockState(), true);
    /**
     * {@link ChestBlockEntity The palm chest block entity}
     */
    private final ChestBlockEntity palmChest = PropertyHelper.getChestBlockEntity(MWWoodTypes.PALM, BlockPos.ZERO, MWBlocks.PALM_CHEST.get().defaultBlockState(), false);
    /**
     * {@link ChestBlockEntity The palm trapped chest block entity}
     */
    private final ChestBlockEntity trappedPalmChest = PropertyHelper.getChestBlockEntity(MWWoodTypes.PALM, BlockPos.ZERO, MWBlocks.PALM_TRAPPED_CHEST.get().defaultBlockState(), true);
    /**
     * {@link ChestBlockEntity The dead chest block entity}
     */
    private final ChestBlockEntity deadChest = PropertyHelper.getChestBlockEntity(MWWoodTypes.DEAD, BlockPos.ZERO, MWBlocks.DEAD_CHEST.get().defaultBlockState(), false);
    /**
     * {@link ChestBlockEntity The dead trapped chest block entity}
     */
    private final ChestBlockEntity trappedDeadChest = PropertyHelper.getChestBlockEntity(MWWoodTypes.DEAD, BlockPos.ZERO, MWBlocks.DEAD_TRAPPED_CHEST.get().defaultBlockState(), true);
    /**
     * {@link ChestBlockEntity The ice chest block entity}
     */
    private final ChestBlockEntity iceChest = PropertyHelper.getChestBlockEntity(MWWoodTypes.ICE, BlockPos.ZERO, MWBlocks.ICE_CHEST.get().defaultBlockState(), false);
    /**
     * {@link ChestBlockEntity The ice trapped chest block entity}
     */
    private final ChestBlockEntity trappedIceChest = PropertyHelper.getChestBlockEntity(MWWoodTypes.ICE, BlockPos.ZERO, MWBlocks.ICE_TRAPPED_CHEST.get().defaultBlockState(), true);

    /**
     * Constructor. Set the {@link EntityModelSet entity model set}
     */
    public MWItemRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        this.blockEntityRenderDispatcher = Minecraft.getInstance().getBlockEntityRenderDispatcher();
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
     * Render custom models for some {@link ItemStack id stacks}
     *
     * @param stack {@link ItemStack The id stack to render}
     * @param displayContext {@link ItemDisplayContext The id display context}
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
            if(blockState.is(MWBlocks.BIRCH_CHEST.get())) {
                blockentity = this.birchChest;
            }
            else if(blockState.is(MWBlocks.BIRCH_TRAPPED_CHEST.get())) {
                blockentity = this.trappedBirchChest;
            }
            if(blockState.is(MWBlocks.JUNGLE_CHEST.get())) {
                blockentity = this.jungleChest;
            }
            else if(blockState.is(MWBlocks.JUNGLE_TRAPPED_CHEST.get())) {
                blockentity = this.trappedJungleChest;
            }
            if(blockState.is(MWBlocks.ACACIA_CHEST.get())) {
                blockentity = this.acaciaChest;
            }
            else if(blockState.is(MWBlocks.ACACIA_TRAPPED_CHEST.get())) {
                blockentity = this.trappedAcaciaChest;
            }
            if(blockState.is(MWBlocks.DARK_OAK_CHEST.get())) {
                blockentity = this.darkOakChest;
            }
            else if(blockState.is(MWBlocks.DARK_OAK_TRAPPED_CHEST.get())) {
                blockentity = this.trappedDarkOakChest;
            }
            if(blockState.is(MWBlocks.MANGROVE_CHEST.get())) {
                blockentity = this.mangroveChest;
            }
            else if(blockState.is(MWBlocks.MANGROVE_TRAPPED_CHEST.get())) {
                blockentity = this.trappedMangroveChest;
            }
            if(blockState.is(MWBlocks.CHERRY_CHEST.get())) {
                blockentity = this.cherryChest;
            }
            else if(blockState.is(MWBlocks.CHERRY_TRAPPED_CHEST.get())) {
                blockentity = this.trappedCherryChest;
            }
            if(blockState.is(MWBlocks.BAMBOO_CHEST.get())) {
                blockentity = this.bambooChest;
            }
            else if(blockState.is(MWBlocks.BAMBOO_TRAPPED_CHEST.get())) {
                blockentity = this.trappedBambooChest;
            }
            if(blockState.is(MWBlocks.CRIMSON_CHEST.get())) {
                blockentity = this.crimsonChest;
            }
            else if(blockState.is(MWBlocks.CRIMSON_TRAPPED_CHEST.get())) {
                blockentity = this.trappedCrimsonChest;
            }
            if(blockState.is(MWBlocks.WARPED_CHEST.get())) {
                blockentity = this.warpedChest;
            }
            else if(blockState.is(MWBlocks.WARPED_TRAPPED_CHEST.get())) {
                blockentity = this.trappedWarpedChest;
            }
            if(blockState.is(MWBlocks.APPLE_CHEST.get())) {
                blockentity = this.appleChest;
            }
            else if(blockState.is(MWBlocks.APPLE_TRAPPED_CHEST.get())) {
                blockentity = this.trappedAppleChest;
            }
            if(blockState.is(MWBlocks.PALM_CHEST.get())) {
                blockentity = this.palmChest;
            }
            else if(blockState.is(MWBlocks.PALM_TRAPPED_CHEST.get())) {
                blockentity = this.trappedPalmChest;
            }
            if(blockState.is(MWBlocks.DEAD_CHEST.get())) {
                blockentity = this.deadChest;
            }
            else if(blockState.is(MWBlocks.DEAD_TRAPPED_CHEST.get())) {
                blockentity = this.trappedDeadChest;
            }
            if(blockState.is(MWBlocks.ICE_CHEST.get())) {
                blockentity = this.iceChest;
            }
            else if(blockState.is(MWBlocks.ICE_TRAPPED_CHEST.get())) {
                blockentity = this.trappedIceChest;
            }
            if(blockentity != null) {
                this.blockEntityRenderDispatcher.renderItem(blockentity, pose, buffer, packedLight, packedOverlay);
            }
        }
    }

}