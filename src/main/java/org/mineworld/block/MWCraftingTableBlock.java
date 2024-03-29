package org.mineworld.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWWoodTypes;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.helper.ResourceHelper;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link CraftingTableBlock Crafting Table Block}
 */
public class MWCraftingTableBlock extends CraftingTableBlock {

    /**
     * The {@link WoodType Crafting Table Wood Type Supplier}
     */
    private final Supplier<WoodType> woodTypeSupplier;
    /**
     * {@link Supplier<Map> Crafting Tables by Wood Type}
     */
    public static final Supplier<Map<WoodType, Block>> CRAFTING_TABLES = Suppliers.memoize(() -> ImmutableMap.<WoodType, Block>builder()
            .put(WoodType.OAK, Blocks.CRAFTING_TABLE)
            .put(WoodType.SPRUCE, MWBlocks.SPRUCE_CRAFTING_TABLE.get())
            .put(WoodType.BIRCH, MWBlocks.BIRCH_CRAFTING_TABLE.get())
            .put(WoodType.JUNGLE, MWBlocks.JUNGLE_CRAFTING_TABLE.get())
            .put(WoodType.ACACIA, MWBlocks.ACACIA_CRAFTING_TABLE.get())
            .put(WoodType.DARK_OAK, MWBlocks.DARK_OAK_CRAFTING_TABLE.get())
            .put(WoodType.MANGROVE, MWBlocks.MANGROVE_CRAFTING_TABLE.get())
            .put(WoodType.CHERRY, MWBlocks.CHERRY_CRAFTING_TABLE.get())
            .put(WoodType.BAMBOO, MWBlocks.BAMBOO_CRAFTING_TABLE.get())
            .put(WoodType.CRIMSON, MWBlocks.CRIMSON_CRAFTING_TABLE.get())
            .put(WoodType.WARPED, MWBlocks.WARPED_CRAFTING_TABLE.get())
            .put(MWWoodTypes.APPLE.get(), MWBlocks.APPLE_CRAFTING_TABLE.get())
            .put(MWWoodTypes.PALM.get(), MWBlocks.PALM_CRAFTING_TABLE.get())
            .put(MWWoodTypes.DEAD.get(), MWBlocks.DEAD_CRAFTING_TABLE.get())
            .put(MWWoodTypes.SCULK.get(), MWBlocks.SCULK_CRAFTING_TABLE.get())
    .build());

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param woodTypeSupplier {@link Supplier<WoodType> The Supplier for the Wood Type this Crafting Table is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public MWCraftingTableBlock(final Supplier<WoodType> woodTypeSupplier, final FeatureFlag... featureFlags) {
        super(PropertyHelper.copy(Blocks.CRAFTING_TABLE, featureFlags).mapColor(ResourceHelper.woodColor(woodTypeSupplier.get()))
                .instrument(NoteBlockInstrument.BASS).strength(2.5F)
                .sound(woodTypeSupplier.get().soundType()).ignitedByLava()
        );
        this.woodTypeSupplier = woodTypeSupplier;
    }

    /**
     * Get the {@link MenuProvider Block Menu Provider}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link MenuProvider The Woodcutter Menu Provider}
     */
    @Nullable
    @Override
    public MenuProvider getMenuProvider(final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos) {
        return new SimpleMenuProvider((id, inventory, player) -> new CraftingMenu(id, inventory, ContainerLevelAccess.create(level, blockPos)) {
            /**
             * Check if a {@link Player Player} can still interact with the {@link BlockEntity Block Entity}
             *
             * @param player {@link Player The Player interacting with the Block Entity}
             * @return {@link Boolean True if the Block Entity can still be interacted}
             */
            @Override
            public boolean stillValid(final @NotNull Player player) {
                return stillValid(this.access, player, getCraftingTable());
            }
        }, CraftingTableBlock.CONTAINER_TITLE);
    }

    /**
     * Get the {@link Block Crafting Table Block}
     * based on the {@link WoodType current Wood Type}
     *
     * @return {@link Block The Crafting Table Block}
     */
    private Block getCraftingTable() {
        return Optional.ofNullable(CRAFTING_TABLES.get().get(this.woodTypeSupplier.get())).orElse(Blocks.CRAFTING_TABLE);
    }

}