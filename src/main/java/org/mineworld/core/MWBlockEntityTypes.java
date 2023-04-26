package org.mineworld.core;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.LecternRenderer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.entity.block.DaylightLampBlockEntity;
import org.mineworld.helper.RegisterHelper;

/**
 * {@link MineWorld MineWorld} {@link BlockEntityType block entity types}
 */
public final class MWBlockEntityTypes {

    public static final RegistryObject<BlockEntityType<DaylightLampBlockEntity>> DAYLIGHT_LAMP = RegisterHelper.registerBlockEntity("daylight_lamp", DaylightLampBlockEntity::new, MWBlocks.DAYLIGHT_LAMP);
    public static final RegistryObject<BlockEntityType<LecternBlockEntity>> SPRUCE_LECTERN = RegisterHelper.registerBlockEntity("spruce_lectern", LecternBlockEntity::new, MWBlocks.SPRUCE_LECTERN);

    /**
     * Register the {@link MineWorld MineWorld} {@link BlockEntityType block entity types}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(final IEventBus eventBus) {
        RegisterHelper.registerBlockEntityTypes(eventBus);
    }

    /**
     * Register the block entity renderings
     */
    public static void registerRenderers() {
        BlockEntityRenderers.register(SPRUCE_LECTERN.get(), LecternRenderer::new);
    }
}