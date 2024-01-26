package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StonecutterBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.core.MWStats;
import org.mineworld.helper.ComponentHelper;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.inventory.WoodcutterMenu;

/**
 * {@link MineWorld MineWorld} {@link Block Woodcutter Block}
 */
public class WoodcutterBlock extends StonecutterBlock {

    /**
     * {@link Component The Container Title}
     */
    public static final Component CONTAINER_TITLE = ComponentHelper.container("woodcutter");

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     */
    public WoodcutterBlock() {
        super(PropertyHelper.block(MapColor.WOOD, 3.5F, 3.5F, true, SoundType.WOOD));
    }

    /**
     * Interact with the Block
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link ServerLevel The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param player {@link Player The player who interacted with the Block}
     * @param hand {@link InteractionHand The hand the player has interacted with}
     * @param hitResult {@link BlockHitResult The hit result for the block interaction}
     * @return {@link InteractionResult The interaction result based on the Player's held Item}
     */
    @Override
    public @NotNull InteractionResult use(final @NotNull BlockState blockState, final Level level, final @NotNull BlockPos blockPos, final @NotNull Player player, final @NotNull InteractionHand hand, final @NotNull BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        player.openMenu(blockState.getMenuProvider(level, blockPos));
        player.awardStat(MWStats.INTERACT_WITH_WOODCUTTER.get());
        return InteractionResult.CONSUME;
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
        return new SimpleMenuProvider((id, inventory, player) -> new WoodcutterMenu(id, inventory, ContainerLevelAccess.create(level, blockPos)), CONTAINER_TITLE);
    }

}