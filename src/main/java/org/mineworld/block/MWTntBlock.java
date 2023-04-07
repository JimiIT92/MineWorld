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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.entity.MWPrimedTnt;

import java.util.List;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link TntBlock tnt block}
 */
public class MWTntBlock extends TntBlock {

    /**
     * {@link Float tnt explosion power}
     */
    private final float explosionPower;
    /**
     * {@link MWPrimedTnt.Type The tnt type}
     */
    private final MWPrimedTnt.Type type;

    /**
     * Constructor. Set the {@link Float tnt explosion power}
     *
     * @param explosionPower {@link Float The tnt explosion power}
     * @param type {@link MWPrimedTnt.Type The tnt type}
     */
    public MWTntBlock(final float explosionPower, final MWPrimedTnt.Type type) {
        super(MWBlocks.copyFrom(Blocks.TNT));
        this.explosionPower = explosionPower;
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
        return new MWPrimedTnt(level, (double)blockPos.getX() + 0.5D, blockPos.getY(), (double)blockPos.getZ() + 0.5D, igniter, this.explosionPower, this.type);
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
        if(this.type.isDisguised()) {
            MutableComponent blockName = Blocks.GRASS_BLOCK.getName();
            TextColor color = TextColor.fromLegacyFormat(ChatFormatting.DARK_GREEN);
            switch (this.type) {
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
            }
            tooltips.add(blockName.withStyle(Style.EMPTY.withColor(color)));
        }
    }
}
