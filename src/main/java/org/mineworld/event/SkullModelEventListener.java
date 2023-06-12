package org.mineworld.event;

import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.block.MWSkullBlock;
import org.mineworld.core.MWBlocks;

/**
 * Register the custom skull block types
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD,  value = Dist.CLIENT)
public final class SkullModelEventListener {

    /**
     * {@link ModelLayerLocation The model layer location for the stray skull}
     */
    private static final ModelLayerLocation STRAY_SKULL_LAYER = new ModelLayerLocation(MWBlocks.STRAY_SKULL.getId(), "main");
    /**
     * {@link ModelLayerLocation The model layer location for the husk head}
     */
    private static final ModelLayerLocation HUSK_HEAD_LAYER = new ModelLayerLocation(MWBlocks.HUSK_HEAD.getId(), "main");
    /**
     * {@link ModelLayerLocation The model layer location for the drowned head}
     */
    private static final ModelLayerLocation DROWNED_HEAD_LAYER = new ModelLayerLocation(MWBlocks.DROWNED_HEAD.getId(), "main");

    /**
     * Register the custom model layers
     *
      * @param event {@link EntityRenderersEvent.RegisterLayerDefinitions The register layer definitions event}
     */
    @SubscribeEvent
    public static void registerLayerDefinitions(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(STRAY_SKULL_LAYER, Lazy.of(SkullModel::createHumanoidHeadLayer));
        event.registerLayerDefinition(HUSK_HEAD_LAYER, Lazy.of(SkullModel::createHumanoidHeadLayer));
        event.registerLayerDefinition(DROWNED_HEAD_LAYER, Lazy.of(SkullModel::createHumanoidHeadLayer));
    }

    /**
     * Register the custom skull models
     *
     * @param event {@link EntityRenderersEvent.CreateSkullModels The create skull models event}
     */
    @SubscribeEvent
    public static void onSkullModelCreate(final EntityRenderersEvent.CreateSkullModels event) {
        event.registerSkullModel(MWSkullBlock.Types.STRAY, new SkullModel(event.getEntityModelSet().bakeLayer(STRAY_SKULL_LAYER)));
        event.registerSkullModel(MWSkullBlock.Types.HUSK, new SkullModel(event.getEntityModelSet().bakeLayer(HUSK_HEAD_LAYER)));
        event.registerSkullModel(MWSkullBlock.Types.DROWNED, new SkullModel(event.getEntityModelSet().bakeLayer(DROWNED_HEAD_LAYER)));
        SkullBlockRenderer.SKIN_BY_TYPE.put(MWSkullBlock.Types.STRAY, new ResourceLocation(MineWorld.MOD_ID, "textures/entity/skeleton/stray_head_full.png"));
        SkullBlockRenderer.SKIN_BY_TYPE.put(MWSkullBlock.Types.HUSK, new ResourceLocation("textures/entity/zombie/husk.png"));
        SkullBlockRenderer.SKIN_BY_TYPE.put(MWSkullBlock.Types.DROWNED, new ResourceLocation(MineWorld.MOD_ID, "textures/entity/zombie/drowned_head_full.png"));
    }

}