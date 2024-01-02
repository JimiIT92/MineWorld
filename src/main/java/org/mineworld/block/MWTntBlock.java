package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.core.MWCriteriaTriggers;
import org.mineworld.entity.MWPrimedTnt;
import org.mineworld.helper.PropertyHelper;

import java.util.List;

/**
 * {@link MineWorld MineWorld} {@link TntBlock TNT Block}
 */
public class MWTntBlock extends TntBlock {

    /**
     * {@link MWPrimedTnt.Type The TNT Type}
     */
    private final MWPrimedTnt.Type type;

    /**
     * Constructor. Set the Block properties
     *
     * @param type {@link MWPrimedTnt.Type The TNT Type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public MWTntBlock(final MWPrimedTnt.Type type, final FeatureFlag... featureFlags) {
        super(PropertyHelper.copy(Blocks.TNT, featureFlags).noOcclusion());
        this.type = type;
    }

    /**
     * Fuse the TNT
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param explosion {@link Explosion The explosion data}
     */
    @Override
    public void wasExploded(final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull Explosion explosion) {
        if (!level.isClientSide) {
            final MWPrimedTnt primedTnt = getPrimedTnt(level, blockPos, explosion.getIndirectSourceEntity());
            final int fuse = primedTnt.getFuse();
            primedTnt.setFuse((short)(level.random.nextInt(fuse / 4) + fuse / 8));
            level.addFreshEntity(primedTnt);
        }
    }

    /**
     * Prime the TNT when it catches fire
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @param owner {@link LivingEntity The entity that ignited the TNT}
     */
    @Override
    public void onCaughtFire(final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final @Nullable Direction direction, final @Nullable LivingEntity owner) {
        if (!level.isClientSide) {
            final MWPrimedTnt primedTnt = getPrimedTnt(level, blockPos, owner);
            level.addFreshEntity(primedTnt);
            level.playSound(null, primedTnt.getX(), primedTnt.getY(), primedTnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(owner, GameEvent.PRIME_FUSE, blockPos);
        }
    }

    /**
     * Get a {@link PrimedTnt Primed TNT Entity} instance
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param owner {@link LivingEntity The entity that ignited the tnt}
     * @return {@link PrimedTnt The Primed TNT Entity}
     */
    private MWPrimedTnt getPrimedTnt(final Level level, final BlockPos blockPos, final LivingEntity owner) {
        return new MWPrimedTnt(level, (double)blockPos.getX() + 0.5D, blockPos.getY(), (double)blockPos.getZ() + 0.5D, owner, this.type);
    }

    /**
     * Add tooltips to a disguised TNT
     *
     * @param itemStack {@link ItemStack The current Item Stack}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param tooltips {@link List<Component> The current tooltips}
     * @param tooltipFlag {@link TooltipFlag The tooltip flag}
     */
    @Override
    public void appendHoverText(final @NotNull ItemStack itemStack, final @Nullable BlockGetter blockGetter, final @NotNull List<Component> tooltips, final @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, blockGetter, tooltips, tooltipFlag);
        appendTntHoverText(tooltips, this.type);
    }

    /**
     * Add tooltips to a disguised TNT
     *
     * @param tooltips {@link List<Component> The current tooltips}
     * @param type {@link MWPrimedTnt.Type The TNT Type}
     */
    public static void appendTntHoverText(final @NotNull List<Component> tooltips, final MWPrimedTnt.Type type) {
        if(type.isDisguised()) {
            tooltips.add(type.getDisguisedBlockName().withStyle(Style.EMPTY.withColor(type.getTextColor())));
        }
    }

    /**
     * Makes the block able to catch fire
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Boolean#TRUE True}
     */
    @Override
    public boolean isFlammable(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return true;
    }

    /**
     * Get the block {@link Integer flammability value}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Integer 20}
     */
    @Override
    public int getFlammability(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return 20;
    }

    /**
     * Get the block {@link Integer fire spread speed value}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Integer 5}
     */
    @Override
    public int getFireSpreadSpeed(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return 5;
    }

    /**
     * Get the {@link VoxelShape block shape}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param collisionContext {@link CollisionContext The collision context}
     * @return {@link VoxelShape The block shape}
     */
    public @NotNull VoxelShape getShape(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull CollisionContext collisionContext) {
        return this.type.equals(MWPrimedTnt.Type.DISGUISED_CAKE) ? Block.box(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D): Shapes.block();
    }

    /**
     * Ignite the TNT Block
     *
     * @param state {@link BlockState The current Block State}
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current Block Pos}
     * @param player {@link Player The player that interacted with the Block}
     * @param hand {@link InteractionHand The hand used to interact with the Block}
     * @param hitResult {@link BlockHitResult The Block hit result}
     * @return {@link InteractionResult The interaction result}
     */
    @Override
    public @NotNull InteractionResult use(final @NotNull BlockState state, final @NotNull Level level, final @NotNull BlockPos pos, final @NotNull Player player, final @NotNull InteractionHand hand, final @NotNull BlockHitResult hitResult) {
        if(this.type.equals(MWPrimedTnt.Type.DISGUISED_CAKE) && !level.isClientSide) {
            MWCriteriaTriggers.IGNITE_CAKE_TNT.trigger((ServerPlayer) player);
        }
        return super.use(state, level, pos, player, hand, hitResult);
    }

}