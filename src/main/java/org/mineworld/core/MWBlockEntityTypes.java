package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.client.renderer.chest.MWChestRenderer;
import org.mineworld.client.renderer.chest.MWTrappedChestRenderer;
import org.mineworld.entity.block.DaylightLampBlockEntity;
import org.mineworld.entity.block.chest.*;
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
    public static final RegistryObject<BlockEntityType<SkullBlockEntity>> SKULL = registerBlockEntity("skull", SkullBlockEntity::new,
            Suppliers.memoize(() -> MWBlocks.STRAY_SKULL.get()),
            Suppliers.memoize(() -> MWBlocks.STRAY_WALL_SKULL.get()),
            Suppliers.memoize(() -> MWBlocks.HUSK_HEAD.get()),
            Suppliers.memoize(() -> MWBlocks.HUSK_WALL_HEAD.get()),
            Suppliers.memoize(() -> MWBlocks.DROWNED_HEAD.get()),
            Suppliers.memoize(() -> MWBlocks.DROWNED_WALL_HEAD.get())
    );
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> SPRUCE_CHEST = registerBlockEntity("spruce_chest", SpruceChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.SPRUCE_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> SPRUCE_TRAPPED_CHEST = registerBlockEntity("spruce_trapped_chest", SpruceTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.SPRUCE_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> BIRCH_CHEST = registerBlockEntity("birch_chest", BirchChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.BIRCH_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> BIRCH_TRAPPED_CHEST = registerBlockEntity("birch_trapped_chest", BirchTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.BIRCH_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> JUNGLE_CHEST = registerBlockEntity("jungle_chest", JungleChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.JUNGLE_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> JUNGLE_TRAPPED_CHEST = registerBlockEntity("jungle_trapped_chest", JungleTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.JUNGLE_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> ACACIA_CHEST = registerBlockEntity("acacia_chest", AcaciaChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.ACACIA_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> ACACIA_TRAPPED_CHEST = registerBlockEntity("acacia_trapped_chest", AcaciaTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.ACACIA_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> DARK_OAK_CHEST = registerBlockEntity("dark_oak_chest", DarkOakChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.DARK_OAK_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> DARK_OAK_TRAPPED_CHEST = registerBlockEntity("dark_oak_trapped_chest", DarkOakTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.DARK_OAK_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> MANGROVE_CHEST = registerBlockEntity("mangrove_chest", MangroveChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.MANGROVE_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> MANGROVE_TRAPPED_CHEST = registerBlockEntity("mangrove_trapped_chest", MangroveTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.MANGROVE_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> CHERRY_CHEST = registerBlockEntity("cherry_chest", CherryChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.CHERRY_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> CHERRY_TRAPPED_CHEST = registerBlockEntity("cherry_trapped_chest", CherryTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.CHERRY_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> BAMBOO_CHEST = registerBlockEntity("bamboo_chest", BambooChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.BAMBOO_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> BAMBOO_TRAPPED_CHEST = registerBlockEntity("bamboo_trapped_chest", BambooTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.BAMBOO_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> CRIMSON_CHEST = registerBlockEntity("crimson_chest", CrimsonChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.CRIMSON_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> CRIMSON_TRAPPED_CHEST = registerBlockEntity("crimson_trapped_chest", CrimsonTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.CRIMSON_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> WARPED_CHEST = registerBlockEntity("warped_chest", WarpedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.WARPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> WARPED_TRAPPED_CHEST = registerBlockEntity("warped_trapped_chest", WarpedTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.WARPED_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> APPLE_CHEST = registerBlockEntity("apple_chest", AppleChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.APPLE_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> APPLE_TRAPPED_CHEST = registerBlockEntity("apple_trapped_chest", AppleTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.APPLE_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> PALM_CHEST = registerBlockEntity("palm_chest", PalmChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.PALM_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> PALM_TRAPPED_CHEST = registerBlockEntity("palm_trapped_chest", PalmTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.PALM_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> DEAD_CHEST = registerBlockEntity("dead_chest", DeadChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.DEAD_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> DEAD_TRAPPED_CHEST = registerBlockEntity("dead_trapped_chest", DeadTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.DEAD_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> SCULK_CHEST = registerBlockEntity("sculk_chest", SculkChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.SCULK_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> SCULK_TRAPPED_CHEST = registerBlockEntity("sculk_trapped_chest", SculkTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.SCULK_TRAPPED_CHEST.get()));


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

    /**
     * Register a {@link BlockEntityRendererProvider Block Entity Renderer}
     *
     * @param blockEntity {@link BlockEntity The Block Entity}
     * @param rendererProvider {@link BlockEntityRendererProvider Block Entity Renderer}
     * @param <T> {@link T The Block Entity type}
     */
    private static <T extends BlockEntity> void registerRenderer(final RegistryObject<BlockEntityType<T>> blockEntity, final BlockEntityRendererProvider<T> rendererProvider) {
        BlockEntityRenderers.register(blockEntity.get(), rendererProvider);
    }

    /**
     * Register a {@link BlockEntityRendererProvider Block Entity Renderer}
     * for some {@link BlockEntityType Block Entities}
     *
     * @param rendererProvider {@link BlockEntityRendererProvider Block Entity Renderer}
     * @param blockEntities {@link BlockEntity The Block Entities}
     * @param <T> {@link T The Block Entity type}
     */
    @SafeVarargs
    private static <T extends BlockEntity> void registerRenderer(final BlockEntityRendererProvider<T> rendererProvider, final RegistryObject<BlockEntityType<T>>... blockEntities) {
        Arrays.stream(blockEntities).forEach(blockEntity -> registerRenderer(blockEntity, rendererProvider));
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

    /**
     * Register all {@link BlockEntityRenderer Block Entity Renderers}
     */
    public static void registerRenderers() {
        registerRenderer(MWChestRenderer::new,
                SPRUCE_CHEST,
                BIRCH_CHEST,
                JUNGLE_CHEST,
                ACACIA_CHEST,
                DARK_OAK_CHEST,
                MANGROVE_CHEST,
                CHERRY_CHEST,
                BAMBOO_CHEST,
                CRIMSON_CHEST,
                WARPED_CHEST,
                APPLE_CHEST,
                PALM_CHEST,
                DEAD_CHEST,
                SCULK_CHEST
        );
        registerRenderer(MWTrappedChestRenderer::new,
                SPRUCE_TRAPPED_CHEST,
                BIRCH_TRAPPED_CHEST,
                JUNGLE_TRAPPED_CHEST,
                ACACIA_TRAPPED_CHEST,
                DARK_OAK_TRAPPED_CHEST,
                MANGROVE_TRAPPED_CHEST,
                CHERRY_TRAPPED_CHEST,
                BAMBOO_TRAPPED_CHEST,
                CRIMSON_TRAPPED_CHEST,
                WARPED_TRAPPED_CHEST,
                APPLE_TRAPPED_CHEST,
                PALM_TRAPPED_CHEST,
                DEAD_TRAPPED_CHEST,
                SCULK_TRAPPED_CHEST
        );
    }

    //#endregion

}