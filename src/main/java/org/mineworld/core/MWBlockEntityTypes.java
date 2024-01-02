package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.entity.block.DaylightLampBlockEntity;
import org.mineworld.helper.RegistryHelper;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link BlockEntityType Block Entity Types}
 */
public final class MWBlockEntityTypes {

    //#region Registry

    /**
     * The {@link DeferredRegister<BlockEntityType> Block Entities Registry}
     */
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = RegistryHelper.registry(ForgeRegistries.BLOCK_ENTITY_TYPES);

    //#endregion

    //#region Block Entities

    public static final RegistryObject<BlockEntityType<DaylightLampBlockEntity>> DAYLIGHT_LAMP = registerBlockEntity("daylight_lamp", DaylightLampBlockEntity::new, Suppliers.memoize(() -> MWBlocks.DAYLIGHT_LAMP.get()));

    //#endregion

    //#region Methods

    /**
     * Register a {@link BlockEntityType Block Entity Type}
     *
     * @param name {@link String The Block Entity name}
     * @param blockEntitySupplier {@link BlockEntityType.BlockEntitySupplier<T> The supplier for the Block Entity}
     * @param blockSuppliers {@link Supplier<Block> The supplier for the blocks related to the Block Entity}
     * @return {@link RegistryObject<BlockEntityType> The registered Block Entity}
     * @param <T> {@link T The Block Entity type}
     */
    @SafeVarargs
    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerBlockEntity(final String name, final BlockEntityType.BlockEntitySupplier<T> blockEntitySupplier, final Supplier<Block>... blockSuppliers) {
        return BLOCK_ENTITY_TYPES.register(name, () -> BlockEntityType.Builder.of(blockEntitySupplier, Arrays.stream(blockSuppliers).map(Supplier::get).toList().toArray(new Block[0])).build(null));
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link BlockEntityType Block Entity Types}
     *
     * @param eventBus {@link IEventBus The mod event bus}
     */
    public static void register(final IEventBus eventBus) {
        BLOCK_ENTITY_TYPES.register(eventBus);
    }

    //#endregion

}