package org.mineworld.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.event.SwordEvents;
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.PropertyHelper;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link TorchBlock Unlit Torch Block}
 */
public class UnlitTorchBlock extends TorchBlock {

    /**
     * {@link Supplier<BiMap> Unlit Torches by Torch}
     */
    public static final Supplier<BiMap<Block, Block>> UNLIT_TORCHES = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(Blocks.TORCH, MWBlocks.UNLIT_TORCH.get())
            .put(Blocks.SOUL_TORCH, MWBlocks.UNLIT_SOUL_TORCH.get())
            .put(MWBlocks.END_TORCH.get(), MWBlocks.UNLIT_END_TORCH.get())
            .put(MWBlocks.SCULK_TORCH.get(), MWBlocks.UNLIT_SCULK_TORCH.get())
    .build());

    /**
     * {@link Supplier<BiMap> Torch by Unlit Torch}
     */
    public static final Supplier<BiMap<Block, Block>> TORCH_BY_UNLIT = Suppliers.memoize(() -> UNLIT_TORCHES.get().inverse());


    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public UnlitTorchBlock(final FeatureFlag... featureFlags) {
        super(ParticleTypes.FLAME, PropertyHelper.copy(Blocks.TORCH, featureFlags).lightLevel(blockState -> 0).noCollission().instabreak().sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY));
    }

    /**
     * Display the {@link ParticleType Block Particles}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param randomSource {@link RandomSource The random reference}
     */
    public void animateTick(final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull RandomSource randomSource) {

    }

    /**
     * Get the corresponding {@link TorchBlock Torch Block}
     *
     * @return {@link TorchBlock The Torch Block}
     */
    protected Block getTorchBlock() {
        return TORCH_BY_UNLIT.get().getOrDefault(this, Blocks.TORCH);
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
    public @NotNull InteractionResult use(final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull Player player, final @NotNull InteractionHand hand, final @NotNull BlockHitResult hitResult) {
        final ItemStack itemStack = player.getItemInHand(hand);
        if(itemStack.getItem() instanceof FlintAndSteelItem || SwordEvents.isFireAspectSword(itemStack)) {
            level.setBlock(blockPos, getTorchBlock().withPropertiesOf(blockState), 2);
            ItemHelper.hurt(itemStack, player);
            player.level().playSound(player, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    /**
     * Get an {@link UnlitTorchBlock Unlit Torch Block} for the given {@link Block Block}
     *
     * @param block {@link Block The Block to get the Unlit Torch Block for}
     * @return {@link UnlitTorchBlock The Unlit Torch Block}
     */
    public static Block getUnlitTorchBlockFor(final Block block) {
        final Optional<Block> unlitTorch = Optional.ofNullable(UNLIT_TORCHES.get().get(block));
        return unlitTorch.orElse(UnlitWallTorchBlock.getUnlitTorchBlockFor(block));
    }

}