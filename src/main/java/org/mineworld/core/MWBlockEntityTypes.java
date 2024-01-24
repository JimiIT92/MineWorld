package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.client.renderer.GiftRenderer;
import org.mineworld.client.renderer.MWChestRenderer;
import org.mineworld.client.renderer.MWTrappedChestRenderer;
import org.mineworld.entity.block.*;
import org.mineworld.entity.block.chest.*;
import org.mineworld.helper.RegistryHelper;
import org.mineworld.helper.ResourceHelper;

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
    public static final RegistryObject<BlockEntityType<LecternBlockEntity>> LECTERN = registerBlockEntity("lectern", MWLecternBlockEntity::new,
            Suppliers.memoize(() -> MWBlocks.SPRUCE_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.BIRCH_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.JUNGLE_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.ACACIA_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.DARK_OAK_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.MANGROVE_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.CHERRY_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.BAMBOO_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.CRIMSON_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.WARPED_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.APPLE_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.PALM_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.DEAD_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.SCULK_LECTERN.get())
    );
    public static final RegistryObject<BlockEntityType<SignBlockEntity>> SIGN = registerBlockEntity("sign", MWSignBlockEntity::new,
            Suppliers.memoize(() -> MWBlocks.APPLE_SIGN.get()), Suppliers.memoize(() -> MWBlocks.APPLE_WALL_SIGN.get()),
            Suppliers.memoize(() -> MWBlocks.PALM_SIGN.get()), Suppliers.memoize(() -> MWBlocks.PALM_WALL_SIGN.get()),
            Suppliers.memoize(() -> MWBlocks.DEAD_SIGN.get()), Suppliers.memoize(() -> MWBlocks.DEAD_WALL_SIGN.get()),
            Suppliers.memoize(() -> MWBlocks.SCULK_SIGN.get()), Suppliers.memoize(() -> MWBlocks.SCULK_WALL_SIGN.get())
    );
    public static final RegistryObject<BlockEntityType<SignBlockEntity>> HANGING_SIGN = registerBlockEntity("hanging_sign", MWHangingSignBlockEntity::new,
            Suppliers.memoize(() -> MWBlocks.APPLE_HANGING_SIGN.get()), Suppliers.memoize(() -> MWBlocks.APPLE_WALL_HANGING_SIGN.get()),
            Suppliers.memoize(() -> MWBlocks.PALM_HANGING_SIGN.get()), Suppliers.memoize(() -> MWBlocks.PALM_WALL_HANGING_SIGN.get()),
            Suppliers.memoize(() -> MWBlocks.DEAD_HANGING_SIGN.get()), Suppliers.memoize(() -> MWBlocks.DEAD_WALL_HANGING_SIGN.get()),
            Suppliers.memoize(() -> MWBlocks.SCULK_HANGING_SIGN.get()), Suppliers.memoize(() -> MWBlocks.SCULK_WALL_HANGING_SIGN.get())
    );
    public static final RegistryObject<BlockEntityType<CampfireBlockEntity>> CAMPFIRE = registerBlockEntity("campfire", MWCampfireBlockEntity::new,
            Suppliers.memoize(() -> MWBlocks.END_CAMPFIRE.get()),
            Suppliers.memoize(() -> MWBlocks.SCULK_CAMPFIRE.get())
    );
    public static final RegistryObject<BlockEntityType<GiftBlockEntity>> GIFT = registerBlockEntity("gift", GiftBlockEntity::new,
            Suppliers.memoize(() -> MWBlocks.GIFT.get())
    );
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> SPRUCE_CHEST = registerChest(WoodType.SPRUCE, false, SpruceChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.SPRUCE_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> SPRUCE_TRAPPED_CHEST = registerChest(WoodType.SPRUCE, true, SpruceTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.SPRUCE_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> BIRCH_CHEST = registerChest(WoodType.BIRCH, false, BirchChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.BIRCH_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> BIRCH_TRAPPED_CHEST = registerChest(WoodType.BIRCH, true, BirchTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.BIRCH_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> JUNGLE_CHEST = registerChest(WoodType.JUNGLE, false, JungleChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.JUNGLE_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> JUNGLE_TRAPPED_CHEST = registerChest(WoodType.JUNGLE, true, JungleTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.JUNGLE_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> ACACIA_CHEST = registerChest(WoodType.ACACIA, false, AcaciaChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.ACACIA_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> ACACIA_TRAPPED_CHEST = registerChest(WoodType.ACACIA, true, AcaciaTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.ACACIA_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> DARK_OAK_CHEST = registerChest(WoodType.DARK_OAK, false, DarkOakChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.DARK_OAK_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> DARK_OAK_TRAPPED_CHEST = registerChest(WoodType.DARK_OAK, true, DarkOakTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.DARK_OAK_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> MANGROVE_CHEST = registerChest(WoodType.MANGROVE, false, MangroveChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.MANGROVE_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> MANGROVE_TRAPPED_CHEST = registerChest(WoodType.MANGROVE, true, MangroveTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.MANGROVE_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> CHERRY_CHEST = registerChest(WoodType.CHERRY, false, CherryChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.CHERRY_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> CHERRY_TRAPPED_CHEST = registerChest(WoodType.CHERRY, true, CherryTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.CHERRY_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> BAMBOO_CHEST = registerChest(WoodType.BAMBOO, false, BambooChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.BAMBOO_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> BAMBOO_TRAPPED_CHEST = registerChest(WoodType.BAMBOO, true, BambooTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.BAMBOO_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> CRIMSON_CHEST = registerChest(WoodType.CRIMSON, false, CrimsonChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.CRIMSON_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> CRIMSON_TRAPPED_CHEST = registerChest(WoodType.CRIMSON, true, CrimsonTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.CRIMSON_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> WARPED_CHEST = registerChest(WoodType.WARPED, false, WarpedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.WARPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> WARPED_TRAPPED_CHEST = registerChest(WoodType.WARPED, true, WarpedTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.WARPED_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> APPLE_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.APPLE, false, AppleChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.APPLE_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> APPLE_TRAPPED_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.APPLE, true, AppleTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.APPLE_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> PALM_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.PALM, false, PalmChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.PALM_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> PALM_TRAPPED_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.PALM, true, PalmTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.PALM_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> DEAD_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.DEAD, false, DeadChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.DEAD_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> DEAD_TRAPPED_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.DEAD, true, DeadTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.DEAD_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> SCULK_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.SCULK, false, SculkChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.SCULK_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> SCULK_TRAPPED_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.SCULK, true, SculkTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.SCULK_TRAPPED_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWChestBlockEntity>> ICE_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.ICE, false, IceChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.ICE_CHEST.get()));
    public static final RegistryObject<BlockEntityType<MWTrappedChestBlockEntity>> ICE_TRAPPED_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.ICE, true, IceTrappedChestBlockEntity::new, Suppliers.memoize(() -> MWBlocks.ICE_TRAPPED_CHEST.get()));

    //#endregion

    //#region Methods

    /**
     * Register a {@link ChestBlockEntity Chest Block Entity Type}
     *
     * @param woodType {@link WoodType The Chest Wood Type}
     * @param isTrappedChest {@link Boolean If the Chest is a Trapped Chest}
     * @param blockEntitySupplier {@link BlockEntityType.BlockEntitySupplier<T> The supplier for the Block Entity}
     * @param blockSuppliers {@link Supplier<Block> The supplier for the blocks related to the Block Entity}
     * @return {@link RegistryObject<BlockEntityType> The registered Block Entity}
     * @param <T> {@link T The Block Entity type}
     */
    @SafeVarargs
    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerChest(final WoodType woodType, final boolean isTrappedChest, final BlockEntityType.BlockEntitySupplier<T> blockEntitySupplier, final Supplier<Block>... blockSuppliers) {
        return registerChest(ResourceHelper.woodName(woodType), isTrappedChest, blockEntitySupplier, blockSuppliers);
    }

    /**
     * Register a {@link ChestBlockEntity Chest Block Entity Type}
     *
     * @param woodName {@link String The Chest Wood name}
     * @param isTrappedChest {@link Boolean If the Chest is a Trapped Chest}
     * @param blockEntitySupplier {@link BlockEntityType.BlockEntitySupplier<T> The supplier for the Block Entity}
     * @param blockSuppliers {@link Supplier<Block> The supplier for the blocks related to the Block Entity}
     * @return {@link RegistryObject<BlockEntityType> The registered Block Entity}
     * @param <T> {@link T The Block Entity type}
     */
    @SafeVarargs
    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerChest(final String woodName, final boolean isTrappedChest, final BlockEntityType.BlockEntitySupplier<T> blockEntitySupplier, final Supplier<Block>... blockSuppliers) {
        return registerBlockEntity(woodName + "_" + (isTrappedChest ? "trapped_" : "") + "chest", blockEntitySupplier, blockSuppliers);
    }

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
        registerRenderer(LECTERN, LecternRenderer::new);
        registerRenderer(SIGN, SignRenderer::new);
        registerRenderer(HANGING_SIGN, HangingSignRenderer::new);
        registerRenderer(SKULL, SkullBlockRenderer::new);
        registerRenderer(CAMPFIRE, CampfireRenderer::new);
        registerRenderer(GIFT, GiftRenderer::new);
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
                SCULK_CHEST,
                ICE_CHEST
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
                SCULK_TRAPPED_CHEST,
                ICE_TRAPPED_CHEST
        );
    }

    //#endregion

}