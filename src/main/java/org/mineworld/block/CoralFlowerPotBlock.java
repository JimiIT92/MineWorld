package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.helper.PropertyHelper;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link FlowerPotBlock Coral Flower Pot}.
 * The coral inside the pot needs water to survive, otherwise it will turn into the dead variant
 */
public class CoralFlowerPotBlock extends FlowerPotBlock {

    /**
     * {@link BooleanProperty Whether this Coral Pot is dried}
     */
    public static final BooleanProperty DRIED = BooleanProperty.create("dried");
    /**
     * {@link Supplier<Block> The Supplier for the Dead Coral Pot variant}
     */
    private final Supplier<? extends Block> deadCoralFlowerPotSupplier;

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param deadCoralFlowerPotSupplier {@link Supplier<Block> The Supplier for the Dead Coral Pot variant}
     * @param coralSupplier {@link Supplier<Block> The Coral this Pot is referring to}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public CoralFlowerPotBlock(final Supplier<? extends Block> deadCoralFlowerPotSupplier, final Supplier<? extends Block> coralSupplier, final FeatureFlag... featureFlags) {
        super(() -> (FlowerPotBlock) Blocks.FLOWER_POT, coralSupplier, PropertyHelper.copy(Blocks.FLOWER_POT, featureFlags));
        this.registerDefaultState(this.stateDefinition.any().setValue(DRIED, Boolean.FALSE));
        this.deadCoralFlowerPotSupplier = deadCoralFlowerPotSupplier;
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
    public @NotNull InteractionResult use(final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final Player player, final @NotNull InteractionHand hand, final @NotNull BlockHitResult hitResult) {
        final ItemStack heldItem = player.getItemInHand(hand);
        if(heldItem.is(Items.POTION) && PotionUtils.getPotion(heldItem).equals(Potions.WATER) && !blockState.getValue(DRIED)) {
            final Item item = heldItem.getItem();
            player.setItemInHand(hand, ItemUtils.createFilledResult(heldItem, player, new ItemStack(Items.GLASS_BOTTLE)));
            player.awardStat(Stats.ITEM_USED.get(item));
            level.setBlockAndUpdate(blockPos, blockState.setValue(DRIED, Boolean.TRUE));
            level.playSound(null, blockPos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(null, GameEvent.FLUID_PLACE, blockPos);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return super.use(blockState, level, blockPos, player, hand, hitResult);
    }

    /**
     * Checks if the Pot is dried. If not schedule the next tick to see
     * if the Dead Coral Pot should be placed
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link ServerLevel The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param neighborBlockState {@link BlockState The neighbor Block State}
     * @param isClient {@link Boolean If the Block has been placed only on the Client}
     */
    @Override
    public void onPlace(final BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull BlockState neighborBlockState, final boolean isClient) {
        if (!blockState.getValue(DRIED)) {
            level.scheduleTick(blockPos, this, 60 + level.getRandom().nextInt(40));
        }
    }

    /**
     * Ticks the Block
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link ServerLevel The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param randomSource {@link RandomSource The random reference}
     */
    @Override
    public void tick(final BlockState blockState, final @NotNull ServerLevel level, final @NotNull BlockPos blockPos, final @NotNull RandomSource randomSource) {
        if (!blockState.getValue(DRIED)) {
            level.setBlock(blockPos, this.deadCoralFlowerPotSupplier.get().defaultBlockState(), 2);
        }
    }

    /**
     * Check if the Block should randomly ticking
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean True if is not dried}
     */
    @Override
    public boolean isRandomlyTicking(final BlockState blockState) {
        return !blockState.getValue(DRIED);
    }

    /**
     * Create the {@link StateDefinition Block State definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The Block State builder}
     */
    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(DRIED);
    }

}