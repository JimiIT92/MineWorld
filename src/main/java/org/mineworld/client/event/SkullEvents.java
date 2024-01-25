package org.mineworld.client.event;

import com.google.common.base.Suppliers;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.block.MWSkullBlock;
import org.mineworld.core.MWBlocks;
import org.mineworld.helper.ResourceHelper;
import org.mineworld.helper.TextureHelper;

/**
 * Handle all events for {@link MineWorld MineWorld} {@link SkullBlock Skulls}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class SkullEvents {

    /**
     * {@link ModelLayerLocation The Model Layer Location for the Stray Skull}
     */
    private static final ModelLayerLocation STRAY_SKULL_LAYER = new ModelLayerLocation(MWBlocks.STRAY_SKULL.getId(), "main");
    /**
     * {@link ModelLayerLocation The Model Layer Location for the Husk Head}
     */
    private static final ModelLayerLocation HUSK_HEAD_LAYER = new ModelLayerLocation(MWBlocks.HUSK_HEAD.getId(), "main");
    /**
     * {@link ModelLayerLocation The Model Layer Location for the Drowned Head}
     */
    private static final ModelLayerLocation DROWNED_HEAD_LAYER = new ModelLayerLocation(MWBlocks.DROWNED_HEAD.getId(), "main");

    /**
     * Register all {@link MineWorld MineWorld} {@link LayerDefinition Layer Definitions}
     *
     * @param event {@link EntityRenderersEvent.RegisterLayerDefinitions The Register Layers Definition Event}
     */
    @SubscribeEvent
    public static void onRegisterLayers(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(STRAY_SKULL_LAYER,Suppliers.memoize(SkullModel::createHumanoidHeadLayer));
        event.registerLayerDefinition(HUSK_HEAD_LAYER,Suppliers.memoize(SkullModel::createHumanoidHeadLayer));
        event.registerLayerDefinition(DROWNED_HEAD_LAYER,Suppliers.memoize(SkullModel::createHumanoidHeadLayer));
    }

    /**
     * Register all {@link MineWorld MineWorld} {@link SkullModel Skull Models}
     *
     * @param event {@link EntityRenderersEvent.CreateSkullModels The Create Skull Models Event}
     */
    @SubscribeEvent
    public static void onCreateSkullModels(final EntityRenderersEvent.CreateSkullModels event) {
        registerSkullModel(event, MWSkullBlock.Types.STRAY, STRAY_SKULL_LAYER, TextureHelper.entity("skeleton/stray_head_full"));
        registerSkullModel(event, MWSkullBlock.Types.HUSK, HUSK_HEAD_LAYER, new ResourceLocation(ResourceHelper.path(TextureHelper.entity("zombie/husk"))));
        registerSkullModel(event, MWSkullBlock.Types.DROWNED, DROWNED_HEAD_LAYER, TextureHelper.entity("zombie/drowned_head_full"));
    }

    /**
     * Register a {@link SkullModel Skull Model}
     *
     * @param event {@link EntityRenderersEvent.CreateSkullModels The Create Skull Models Event}
     * @param type {@link MWSkullBlock.Types The Skull Type}
     * @param layerLocation {@link ModelLayerLocation The Skull Layer Location}
     */
    private static void registerSkullModel(final EntityRenderersEvent.CreateSkullModels event, final MWSkullBlock.Types type, final ModelLayerLocation layerLocation, final ResourceLocation texture) {
        event.registerSkullModel(type, new SkullModel(event.getEntityModelSet().bakeLayer(layerLocation)));
        SkullBlockRenderer.SKIN_BY_TYPE.put(type, texture);
    }

}