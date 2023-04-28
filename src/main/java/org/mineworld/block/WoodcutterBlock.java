package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.StonecutterMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StonecutterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.core.MWStats;
import org.mineworld.helper.PropertyHelper;

/**
 * Implementation class for a woodcutter
 */
public class WoodcutterBlock extends StonecutterBlock {

    /**
     * {@link Component The woodcutter screen title}
     */
    private static final Component CONTAINER_TITLE = Component.translatable("container." + MineWorld.MOD_ID + ".woodcutter");

    /**
     * Constructor. Set the block properties
     */
    public WoodcutterBlock() {
        super(PropertyHelper.basicBlockProperties(Material.WOOD, MaterialColor.WOOD, 3.5F, 3.5F, true, SoundType.WOOD));
    }

    /**
     * Open the woodcutter screen on right click
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param player {@link Player The player reference}
     * @param hand {@link InteractionHand The hand the player is interacting with}
     * @param blockHitResult {@link BlockHitResult The block hit result}
     * @return {@link InteractionResult The itneraction result}
     */
    @Override
    public @NotNull InteractionResult use(final @NotNull BlockState blockState, final Level level, final @NotNull BlockPos blockPos, final @NotNull Player player, final @NotNull InteractionHand hand, final @NotNull BlockHitResult blockHitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        player.openMenu(blockState.getMenuProvider(level, blockPos));
        player.awardStat(MWStats.INTERACT_WITH_WOODCUTTER.get());
        return InteractionResult.CONSUME;
    }

    /**
     * Get the {@link MenuProvider woodcutter menu provider}
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @return {@link MenuProvider The woodcutter menu provider}
     */
    @Nullable
    @Override
    public MenuProvider getMenuProvider(final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos) {
        return new SimpleMenuProvider((id, inventory, player) -> new StonecutterMenu(id, inventory, ContainerLevelAccess.create(level, blockPos)), CONTAINER_TITLE);
    }

}