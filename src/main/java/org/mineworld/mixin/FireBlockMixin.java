package org.mineworld.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.mineworld.core.MWBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin for the {@link FireBlock Fire Block}
 */
@Mixin(FireBlock.class)
public final class FireBlockMixin {

    /**
     * Place the appropriate {@link FireBlock Fire Block} based on the block below
     *
     * @param level {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param infoReturnable {@link CallbackInfoReturnable<BlockState> The info returnable}
     */
    @Inject(method = "getStateForPlacement(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;", at = @At("HEAD"), cancellable = true)
    public void getStateForPlacement(final BlockGetter level, final BlockPos blockPos, final CallbackInfoReturnable<BlockState> infoReturnable) {
        final BlockState terrain = level.getBlockState(blockPos.below());
        if(terrain.is(MWBlocks.END_SOIL.get())) {
            infoReturnable.setReturnValue(MWBlocks.END_FIRE.get().defaultBlockState());
        }
        else if(terrain.is(MWBlocks.SCULK_SOIL.get())) {
            infoReturnable.setReturnValue(MWBlocks.SCULK_FIRE.get().defaultBlockState());
        }
    }

}