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
import net.minecraft.world.level.block.CoralBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.mineworld.helper.PropertyHelper;

import java.util.function.Supplier;

/**
 * Implementation class for a {@link CoralBlock coral block} {@link FlowerPotBlock flower pot}.
 * The coral inside the pot needs water to survive, otherwise it will turn into the dead variant
 */
public class CoralFlowerPotBlock extends FlowerPotBlock {

    /**
     * {@link BooleanProperty Whether this coral flower pot is dried}
     */
    public static final BooleanProperty DRIED = BooleanProperty.create("dried");
    /**
     * {@link Supplier<Block> The dead coral flower pot variant}
     */
    private final Supplier<? extends Block> deadCoralFlowerPot;

    /**
     * Constructor. Set the {@link Supplier<Block> dead coral variant}
     *
     * @param deadCoralFlowerPot {@link Supplier<Block> The dead coral flower pot variant}
     * @param coral {@link Supplier<Block> The coral for this flower pot}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     */
    public CoralFlowerPotBlock(final Supplier<? extends Block> deadCoralFlowerPot, final Supplier<? extends Block> coral, final FeatureFlag... featureFlags) {
        super(() -> (FlowerPotBlock) Blocks.FLOWER_POT, coral, PropertyHelper.copyFromBlock(Blocks.FLOWER_POT, featureFlags));
        this.registerDefaultState(this.stateDefinition.any().setValue(DRIED, Boolean.FALSE));
        this.deadCoralFlowerPot = deadCoralFlowerPot;
    }

    /**
     * Make the {@link CoralFlowerPotBlock coral flower pot} dried if interacted
     * with a water bottle
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link ServerLevel The world reference}
     * @param blockPos {@link BlockPos The flower pot block pos}
     * @param player {@link Player The player who interacted with the flower pot}
     * @param hand {@link InteractionHand The hand the player has interacted with}
     * @param hitResult {@link BlockHitResult The hit result for the block interaction}
     * @return {@link InteractionResult The interaction result based on the id in the player's hand}
     */
    @Override
    public @NotNull InteractionResult use(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(itemstack.is(Items.POTION) && PotionUtils.getPotion(itemstack).equals(Potions.WATER) && !blockState.getValue(DRIED)) {
            final Item item = itemstack.getItem();
            player.setItemInHand(hand, ItemUtils.createFilledResult(itemstack, player, new ItemStack(Items.GLASS_BOTTLE)));
            player.awardStat(Stats.ITEM_USED.get(item));
            level.setBlockAndUpdate(blockPos, blockState.setValue(DRIED, Boolean.TRUE));
            level.playSound(null, blockPos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(null, GameEvent.FLUID_PLACE, blockPos);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return super.use(blockState, level, blockPos, player, hand, hitResult);
    }

    /**
     * Checks if the flower pot is dried. If not schedule the next tick to see
     * if the dead coral flwoer pot should be placed
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link ServerLevel The world reference}
     * @param blockPos {@link BlockPos The flower pot block pos}
     * @param neighborBlockState {@link BlockState The neighbor block state}
     * @param par5 {@link Boolean Unused boolean value}
     */
    @Override
    public void onPlace(BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState neighborBlockState, boolean par5) {
        if (!blockState.getValue(DRIED)) {
            level.scheduleTick(blockPos, this, 60 + level.getRandom().nextInt(40));
        }
    }

    /**
     * Ticks randomly and if the {@link CoralFlowerPotBlock coral flower pot}
     * isn't dried, the dead coral flower pot gets placed
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link ServerLevel The world reference}
     * @param blockPos {@link BlockPos The flower pot block pos}
     * @param random {@link RandomSource The random source reference}
     */
    @Override
    public void tick(BlockState blockState, @NotNull ServerLevel level, @NotNull BlockPos blockPos, @NotNull RandomSource random) {
        if (!blockState.getValue(DRIED)) {
            level.setBlock(blockPos, this.deadCoralFlowerPot.get().defaultBlockState(), 2);
        }
    }

    /**
     * Makes the block randomly ticking if is not dried
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link Boolean True if is not dried}
     */
    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return !blockState.getValue(DRIED);
    }

    /**
     * Create the {@link StateDefinition block state definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The block state builder}
     */
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(DRIED);
    }

}