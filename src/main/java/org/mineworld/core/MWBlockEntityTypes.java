package org.mineworld.core;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.entity.block.DaylightLampBlockEntity;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link BlockEntityType block entity types}
 */
public final class MWBlockEntityTypes {

    /**
     * {@link DeferredRegister<BlockEntityType> The block entity types registry}
     */
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MineWorld.MODID);

    //#region Block entity types

    public static final RegistryObject<BlockEntityType<DaylightLampBlockEntity>> DAYLIGHT_LAMP = registerBlockEntity("daylight_lamp", DaylightLampBlockEntity::new, MWBlocks.DAYLIGHT_LAMP);

    //#endregion

    /**
     * Register a {@link BlockEntityType block entity type}
     *
     * @param name {@link String The block entity name}
     * @param blockEntitySupplier {@link BlockEntityType.BlockEntitySupplier The block entity supplier}
     * @param blockSupplier {@link Supplier<Block> The supplier for the block related to the block entity}
     * @return {@link RegistryObject<BlockEntityType> The registered block entity}
     * @param <T> {@link T The block entity type}
     */
    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerBlockEntity(final String name, final BlockEntityType.BlockEntitySupplier<T> blockEntitySupplier, final Supplier<Block> blockSupplier) {
        return BLOCK_ENTITY_TYPES.register(name, () -> BlockEntityType.Builder.of(blockEntitySupplier, blockSupplier.get()).build(null));
    }

    /**
     * Register the {@link MineWorld MineWorld} {@link BlockEntityType block entity types}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(final IEventBus eventBus) {
        BLOCK_ENTITY_TYPES.register(eventBus);
    }
}