package org.mineworld.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.entity.MWPrimedTnt;
import org.mineworld.helper.PropertyHelper;

import java.util.List;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link TntBlock tnt block}
 */
public class MWTntBlock extends TntBlock {

    /**
     * {@link MWPrimedTnt.Type The tnt type}
     */
    private final MWPrimedTnt.Type type;

    /**
     * Constructor. Set the {@link Float tnt explosion power}
     *
     * @param type {@link MWPrimedTnt.Type The tnt type}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     */
    public MWTntBlock(final MWPrimedTnt.Type type, final FeatureFlag... featureFlags) {
        super(PropertyHelper.copyFromBlock(Blocks.TNT, featureFlags).noOcclusion());
        this.type = type;
    }

    /**
     * Fuse the tnt
     *
     * @param level {@link Level The world reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param explosion {@link Explosion The explosion data}
     */
    @Override
    public void wasExploded(final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull Explosion explosion) {
        if (!level.isClientSide) {
            final MWPrimedTnt primedtnt = getPrimedTnt(level, blockPos, explosion.getIndirectSourceEntity());
            final int fuse = primedtnt.getFuse();
            primedtnt.setFuse((short)(level.random.nextInt(fuse / 4) + fuse / 8));
            level.addFreshEntity(primedtnt);
        }
    }

    /**
     * Prime the tnt when it catches fire
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link Level The world reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param direction {@link Direction The direction that the fire is coming from}
     * @param igniter {@link LivingEntity The entity that ignited the tnt}
     */
    @Override
    public void onCaughtFire(final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final @Nullable Direction direction, final @Nullable LivingEntity igniter) {
        if (!level.isClientSide) {
            final MWPrimedTnt primedtnt = getPrimedTnt(level, blockPos, igniter);
            level.addFreshEntity(primedtnt);
            level.playSound(null, primedtnt.getX(), primedtnt.getY(), primedtnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(igniter, GameEvent.PRIME_FUSE, blockPos);
        }
    }

    /**
     * Get the {@link PrimedTnt primed tnt entity} based on the {@link Integer tnt explosion power}
     *
     * @param level {@link Level The world reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param igniter {@link LivingEntity The entity that ignited the tnt}
     * @return {@link PrimedTnt The tnt entity}
     */
    private MWPrimedTnt getPrimedTnt(final Level level, final BlockPos blockPos, final LivingEntity igniter) {
        return new MWPrimedTnt(level, (double)blockPos.getX() + 0.5D, blockPos.getY(), (double)blockPos.getZ() + 0.5D, igniter, this.type);
    }

    /**
     * Add tooltips to the disguised tnts
     *
     * @param itemStack {@link ItemStack The current item stack}
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param tooltips {@link List<Component> The current tooltips}
     * @param tooltipFlag {@link TooltipFlag The tooltip flag}
     */
    @Override
    public void appendHoverText(final @NotNull ItemStack itemStack, final @Nullable BlockGetter blockGetter, @NotNull List<Component> tooltips, final @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, blockGetter, tooltips, tooltipFlag);
        appendTntHoverText(tooltips, this.type);
    }

    /**
     * Add tooltips to the disguised tnts
     *
     * @param tooltips {@link List<Component> The current tooltips}
     * @param type {@link MWPrimedTnt.Type The tnt type}
     */
    public static void appendTntHoverText(@NotNull List<Component> tooltips, final MWPrimedTnt.Type type) {
        if(type.isDisguised()) {
            MutableComponent blockName = Blocks.GRASS_BLOCK.getName();
            TextColor color = TextColor.fromLegacyFormat(ChatFormatting.DARK_GREEN);
            switch (type) {
                case DISGUISED_DIRT -> {
                    blockName = Blocks.DIRT.getName();
                    color = TextColor.fromRgb(0x7B553D);
                }
                case DISGUISED_SAND -> {
                    blockName = Blocks.SAND.getName();
                    color = TextColor.fromLegacyFormat(ChatFormatting.YELLOW);
                }
                case DISGUISED_RED_SAND -> {
                    blockName = Blocks.RED_SAND.getName();
                    color = TextColor.fromRgb(0xB8602C);
                }
                case DISGUISED_STONE -> {
                    blockName = Blocks.STONE.getName();
                    color = TextColor.fromLegacyFormat(ChatFormatting.GRAY);
                }
                case DISGUISED_CAKE -> {
                    blockName = Blocks.CAKE.getName();
                    color = TextColor.fromRgb(0xF6E8CB);
                }
            }
            tooltips.add(blockName.withStyle(Style.EMPTY.withColor(color)));
        }
    }

    /**
     * Makes the block able to catch fire
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link Level The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Boolean True}
     */
    @Override
    public boolean isFlammable(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return true;
    }

    /**
     * Get the block {@link Integer flammability value}
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link Level The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Integer 100}
     */
    @Override
    public int getFlammability(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return 100;
    }

    /**
     * Get the block {@link Integer fire spread speed value}
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link Level The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Integer 15}
     */
    @Override
    public int getFireSpreadSpeed(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return 15;
    }

    /**
     * Get the {@link VoxelShape block shape}
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link Level The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param collisionContext {@link CollisionContext The collision context}
     * @return {@link VoxelShape The block shape}
     */
    public @NotNull VoxelShape getShape(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull CollisionContext collisionContext) {
        return this.type.equals(MWPrimedTnt.Type.DISGUISED_CAKE) ? Block.box(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D): Shapes.block();
    }

}