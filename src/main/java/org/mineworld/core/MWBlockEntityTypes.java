package org.mineworld.core;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.LecternRenderer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.client.renderer.blockentity.chest.*;
import org.mineworld.entity.block.DaylightLampBlockEntity;
import org.mineworld.entity.block.chest.*;
import org.mineworld.helper.RegisterHelper;

/**
 * {@link MineWorld MineWorld} {@link BlockEntityType block entity types}
 */
public final class MWBlockEntityTypes {

    public static final RegistryObject<BlockEntityType<DaylightLampBlockEntity>> DAYLIGHT_LAMP = RegisterHelper.registerBlockEntity("daylight_lamp", DaylightLampBlockEntity::new, MWBlocks.DAYLIGHT_LAMP);
    public static final RegistryObject<BlockEntityType<LecternBlockEntity>> SPRUCE_LECTERN = RegisterHelper.registerBlockEntity("spruce_lectern", LecternBlockEntity::new, MWBlocks.SPRUCE_LECTERN);
    public static final RegistryObject<BlockEntityType<SpruceChestBlockEntity>> SPRUCE_CHEST = RegisterHelper.registerBlockEntity("spruce_chest", SpruceChestBlockEntity::new, MWBlocks.SPRUCE_CHEST);
    public static final RegistryObject<BlockEntityType<SpruceTrappedChestBlockEntity>> SPRUCE_TRAPPED_CHEST = RegisterHelper.registerBlockEntity("spruce_trapped_chest", SpruceTrappedChestBlockEntity::new, MWBlocks.SPRUCE_TRAPPED_CHEST);
    public static final RegistryObject<BlockEntityType<LecternBlockEntity>> BIRCH_LECTERN = RegisterHelper.registerBlockEntity("birch_lectern", LecternBlockEntity::new, MWBlocks.BIRCH_LECTERN);
    public static final RegistryObject<BlockEntityType<BirchChestBlockEntity>> BIRCH_CHEST = RegisterHelper.registerBlockEntity("birch_chest", BirchChestBlockEntity::new, MWBlocks.BIRCH_CHEST);
    public static final RegistryObject<BlockEntityType<BirchTrappedChestBlockEntity>> BIRCH_TRAPPED_CHEST = RegisterHelper.registerBlockEntity("birch_trapped_chest", BirchTrappedChestBlockEntity::new, MWBlocks.BIRCH_TRAPPED_CHEST);
    public static final RegistryObject<BlockEntityType<LecternBlockEntity>> JUNGLE_LECTERN = RegisterHelper.registerBlockEntity("jungle_lectern", LecternBlockEntity::new, MWBlocks.JUNGLE_LECTERN);
    public static final RegistryObject<BlockEntityType<JungleChestBlockEntity>> JUNGLE_CHEST = RegisterHelper.registerBlockEntity("jungle_chest", JungleChestBlockEntity::new, MWBlocks.JUNGLE_CHEST);
    public static final RegistryObject<BlockEntityType<JungleTrappedChestBlockEntity>> JUNGLE_TRAPPED_CHEST = RegisterHelper.registerBlockEntity("jungle_trapped_chest", JungleTrappedChestBlockEntity::new, MWBlocks.JUNGLE_TRAPPED_CHEST);
    public static final RegistryObject<BlockEntityType<LecternBlockEntity>> ACACIA_LECTERN = RegisterHelper.registerBlockEntity("acacia_lectern", LecternBlockEntity::new, MWBlocks.ACACIA_LECTERN);
    public static final RegistryObject<BlockEntityType<AcaciaChestBlockEntity>> ACACIA_CHEST = RegisterHelper.registerBlockEntity("acacia_chest", AcaciaChestBlockEntity::new, MWBlocks.ACACIA_CHEST);
    public static final RegistryObject<BlockEntityType<AcaciaTrappedChestBlockEntity>> ACACIA_TRAPPED_CHEST = RegisterHelper.registerBlockEntity("acacia_trapped_chest", AcaciaTrappedChestBlockEntity::new, MWBlocks.ACACIA_TRAPPED_CHEST);
    public static final RegistryObject<BlockEntityType<LecternBlockEntity>> DARK_OAK_LECTERN = RegisterHelper.registerBlockEntity("dark_oak_lectern", LecternBlockEntity::new, MWBlocks.DARK_OAK_LECTERN);
    public static final RegistryObject<BlockEntityType<DarkOakChestBlockEntity>> DARK_OAK_CHEST = RegisterHelper.registerBlockEntity("dark_oak_chest", DarkOakChestBlockEntity::new, MWBlocks.DARK_OAK_CHEST);
    public static final RegistryObject<BlockEntityType<DarkOakTrappedChestBlockEntity>> DARK_OAK_TRAPPED_CHEST = RegisterHelper.registerBlockEntity("dark_oak_trapped_chest", DarkOakTrappedChestBlockEntity::new, MWBlocks.DARK_OAK_TRAPPED_CHEST);
    public static final RegistryObject<BlockEntityType<LecternBlockEntity>> MANGROVE_LECTERN = RegisterHelper.registerBlockEntity("mangrove_lectern", LecternBlockEntity::new, MWBlocks.MANGROVE_LECTERN);
    public static final RegistryObject<BlockEntityType<MangroveChestBlockEntity>> MANGROVE_CHEST = RegisterHelper.registerBlockEntity("mangrove_chest", MangroveChestBlockEntity::new, MWBlocks.MANGROVE_CHEST);
    public static final RegistryObject<BlockEntityType<MangroveTrappedChestBlockEntity>> MANGROVE_TRAPPED_CHEST = RegisterHelper.registerBlockEntity("mangrove_trapped_chest", MangroveTrappedChestBlockEntity::new, MWBlocks.MANGROVE_TRAPPED_CHEST);
    public static final RegistryObject<BlockEntityType<LecternBlockEntity>> CHERRY_LECTERN = RegisterHelper.registerBlockEntity("cherry_lectern", LecternBlockEntity::new, MWBlocks.CHERRY_LECTERN);
    public static final RegistryObject<BlockEntityType<CherryChestBlockEntity>> CHERRY_CHEST = RegisterHelper.registerBlockEntity("cherry_chest", CherryChestBlockEntity::new, MWBlocks.CHERRY_CHEST);
    public static final RegistryObject<BlockEntityType<CherryTrappedChestBlockEntity>> CHERRY_TRAPPED_CHEST = RegisterHelper.registerBlockEntity("cherry_trapped_chest", CherryTrappedChestBlockEntity::new, MWBlocks.CHERRY_TRAPPED_CHEST);
    public static final RegistryObject<BlockEntityType<LecternBlockEntity>> BAMBOO_LECTERN = RegisterHelper.registerBlockEntity("bamboo_lectern", LecternBlockEntity::new, MWBlocks.BAMBOO_LECTERN);
    public static final RegistryObject<BlockEntityType<BambooChestBlockEntity>> BAMBOO_CHEST = RegisterHelper.registerBlockEntity("bamboo_chest", BambooChestBlockEntity::new, MWBlocks.BAMBOO_CHEST);
    public static final RegistryObject<BlockEntityType<BambooTrappedChestBlockEntity>> BAMBOO_TRAPPED_CHEST = RegisterHelper.registerBlockEntity("bamboo_trapped_chest", BambooTrappedChestBlockEntity::new, MWBlocks.BAMBOO_TRAPPED_CHEST);
    public static final RegistryObject<BlockEntityType<LecternBlockEntity>> CRIMSON_LECTERN = RegisterHelper.registerBlockEntity("crimson_lectern", LecternBlockEntity::new, MWBlocks.CRIMSON_LECTERN);
    public static final RegistryObject<BlockEntityType<CrimsonChestBlockEntity>> CRIMSON_CHEST = RegisterHelper.registerBlockEntity("crimson_chest", CrimsonChestBlockEntity::new, MWBlocks.CRIMSON_CHEST);
    public static final RegistryObject<BlockEntityType<CrimsonTrappedChestBlockEntity>> CRIMSON_TRAPPED_CHEST = RegisterHelper.registerBlockEntity("crimson_trapped_chest", CrimsonTrappedChestBlockEntity::new, MWBlocks.CRIMSON_TRAPPED_CHEST);
    public static final RegistryObject<BlockEntityType<LecternBlockEntity>> WARPED_LECTERN = RegisterHelper.registerBlockEntity("warped_lectern", LecternBlockEntity::new, MWBlocks.WARPED_LECTERN);
    public static final RegistryObject<BlockEntityType<WarpedChestBlockEntity>> WARPED_CHEST = RegisterHelper.registerBlockEntity("warped_chest", WarpedChestBlockEntity::new, MWBlocks.WARPED_CHEST);
    public static final RegistryObject<BlockEntityType<WarpedTrappedChestBlockEntity>> WARPED_TRAPPED_CHEST = RegisterHelper.registerBlockEntity("warped_trapped_chest", WarpedTrappedChestBlockEntity::new, MWBlocks.WARPED_TRAPPED_CHEST);

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
        BlockEntityRenderers.register(SPRUCE_CHEST.get(), SpruceChestRenderer::new);
        BlockEntityRenderers.register(SPRUCE_TRAPPED_CHEST.get(), SpruceTrappedChestRenderer::new);
        BlockEntityRenderers.register(BIRCH_LECTERN.get(), LecternRenderer::new);
        BlockEntityRenderers.register(BIRCH_CHEST.get(), BirchChestRenderer::new);
        BlockEntityRenderers.register(BIRCH_TRAPPED_CHEST.get(), BirchTrappedChestRenderer::new);
        BlockEntityRenderers.register(JUNGLE_LECTERN.get(), LecternRenderer::new);
        BlockEntityRenderers.register(JUNGLE_CHEST.get(), JungleChestRenderer::new);
        BlockEntityRenderers.register(JUNGLE_TRAPPED_CHEST.get(), JungleTrappedChestRenderer::new);
        BlockEntityRenderers.register(ACACIA_LECTERN.get(), LecternRenderer::new);
        BlockEntityRenderers.register(ACACIA_CHEST.get(), AcaciaChestRenderer::new);
        BlockEntityRenderers.register(ACACIA_TRAPPED_CHEST.get(), AcaciaTrappedChestRenderer::new);
        BlockEntityRenderers.register(DARK_OAK_LECTERN.get(), LecternRenderer::new);
        BlockEntityRenderers.register(DARK_OAK_CHEST.get(), DarkOakChestRenderer::new);
        BlockEntityRenderers.register(DARK_OAK_TRAPPED_CHEST.get(), DarkOakTrappedChestRenderer::new);
        BlockEntityRenderers.register(MANGROVE_LECTERN.get(), LecternRenderer::new);
        BlockEntityRenderers.register(MANGROVE_CHEST.get(), MangroveChestRenderer::new);
        BlockEntityRenderers.register(MANGROVE_TRAPPED_CHEST.get(), MangroveTrappedChestRenderer::new);
        BlockEntityRenderers.register(CHERRY_LECTERN.get(), LecternRenderer::new);
        BlockEntityRenderers.register(CHERRY_CHEST.get(), CherryChestRenderer::new);
        BlockEntityRenderers.register(CHERRY_TRAPPED_CHEST.get(), CherryTrappedChestRenderer::new);
        BlockEntityRenderers.register(BAMBOO_LECTERN.get(), LecternRenderer::new);
        BlockEntityRenderers.register(BAMBOO_CHEST.get(), BambooChestRenderer::new);
        BlockEntityRenderers.register(BAMBOO_TRAPPED_CHEST.get(), BambooTrappedChestRenderer::new);
        BlockEntityRenderers.register(CRIMSON_LECTERN.get(), LecternRenderer::new);
        BlockEntityRenderers.register(CRIMSON_CHEST.get(), CrimsonChestRenderer::new);
        BlockEntityRenderers.register(CRIMSON_TRAPPED_CHEST.get(), CrimsonTrappedChestRenderer::new);
        BlockEntityRenderers.register(WARPED_LECTERN.get(), LecternRenderer::new);
        BlockEntityRenderers.register(WARPED_CHEST.get(), WarpedChestRenderer::new);
        BlockEntityRenderers.register(WARPED_TRAPPED_CHEST.get(), WarpedTrappedChestRenderer::new);
    }
}