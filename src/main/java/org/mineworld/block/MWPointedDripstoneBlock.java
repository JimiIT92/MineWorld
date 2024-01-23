package org.mineworld.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWPointedDripstones;
import org.mineworld.helper.PropertyHelper;

import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link PointedDripstoneBlock Pointed Dripstone Block}
 */
public class MWPointedDripstoneBlock extends Block implements Fallable, SimpleWaterloggedBlock {

    /**
     * {@link Supplier<BiMap> Pointed Dripstone Blocks by Block}
     */
    private static final Supplier<BiMap<Block, Block>> DRIPSTONES = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(Blocks.STONE, MWPointedDripstones.STONE_POINTED_DRIPSTONE.get())
            .put(Blocks.SANDSTONE, MWPointedDripstones.SANDSTONE_POINTED_DRIPSTONE.get())
            .put(Blocks.RED_SANDSTONE, MWPointedDripstones.RED_SANDSTONE_POINTED_DRIPSTONE.get())
            .put(Blocks.ICE, MWPointedDripstones.ICE_POINTED_DRIPSTONE.get())
            .put(Blocks.PACKED_ICE, MWPointedDripstones.PACKED_ICE_POINTED_DRIPSTONE.get())
            .put(Blocks.BLUE_ICE, MWPointedDripstones.BLUE_ICE_POINTED_DRIPSTONE.get())
            .put(Blocks.DEEPSLATE, MWPointedDripstones.DEEPSLATE_POINTED_DRIPSTONE.get())
            .put(Blocks.GRANITE, MWPointedDripstones.GRANITE_POINTED_DRIPSTONE.get())
            .put(Blocks.DIORITE, MWPointedDripstones.DIORITE_POINTED_DRIPSTONE.get())
            .put(Blocks.ANDESITE, MWPointedDripstones.ANDESITE_POINTED_DRIPSTONE.get())
            .put(Blocks.CALCITE, MWPointedDripstones.CALCITE_POINTED_DRIPSTONE.get())
            .put(Blocks.TUFF, MWPointedDripstones.TUFF_POINTED_DRIPSTONE.get())
            .put(Blocks.PRISMARINE, MWPointedDripstones.PRISMARINE_POINTED_DRIPSTONE.get())
            .put(Blocks.NETHERRACK, MWPointedDripstones.NETHERRACK_POINTED_DRIPSTONE.get())
            .put(Blocks.CRIMSON_NYLIUM, MWPointedDripstones.CRIMSON_NYLIUM_POINTED_DRIPSTONE.get())
            .put(Blocks.WARPED_NYLIUM, MWPointedDripstones.WARPED_NYLIUM_POINTED_DRIPSTONE.get())
            .put(Blocks.SOUL_SOIL, MWPointedDripstones.SOUL_SOIL_POINTED_DRIPSTONE.get())
            .put(Blocks.BLACKSTONE, MWPointedDripstones.BLACKSTONE_POINTED_DRIPSTONE.get())
            .put(Blocks.BASALT, MWPointedDripstones.BASALT_POINTED_DRIPSTONE.get())
            .put(Blocks.END_STONE, MWPointedDripstones.END_STONE_POINTED_DRIPSTONE.get())
            .put(MWBlocks.PERENNIAL_ICE.get(), MWPointedDripstones.PERENNIAL_ICE_POINTED_DRIPSTONE.get())
            .put(MWBlocks.MARBLE.get(), MWPointedDripstones.MARBLE_POINTED_DRIPSTONE.get())
    .build());

    /**
     * {@link Supplier<BiMap> Blocks by Pointed Dripstone Blocks}
     */
    Supplier<BiMap<Block, Block>> SOURCE_BY_DRIPSTONE = Suppliers.memoize(() -> DRIPSTONES.get().inverse());

    /**
     * {@link DirectionProperty The Pointed Dripstone Tip Direction property}
     */
    public static final DirectionProperty TIP_DIRECTION = BlockStateProperties.VERTICAL_DIRECTION;
    /**
     * {@link DripstoneThickness The Pointed Dripstone Thickness property}
     */
    public static final EnumProperty<DripstoneThickness> THICKNESS = BlockStateProperties.DRIPSTONE_THICKNESS;
    /**
     * {@link BooleanProperty The Block Waterlogged Property}
     */
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    /**
     * {@link Supplier<Block> The Supplier for the Block this Pointed Dripstone is based on}
     */
    private final Supplier<Block> blockSupplier;

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param blockSupplier {@link Supplier<Block> The Supplier for the Block this Pointed Dripstone is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public MWPointedDripstoneBlock(final Supplier<Block> blockSupplier, final FeatureFlag... featureFlags) {
        super(PropertyHelper.copy(blockSupplier.get(), featureFlags).noOcclusion().randomTicks().strength(0.5F).dynamicShape().offsetType(OffsetType.XZ).pushReaction(PushReaction.DESTROY));
        this.registerDefaultState(this.stateDefinition.any().setValue(TIP_DIRECTION, Direction.UP).setValue(THICKNESS, DripstoneThickness.TIP).setValue(WATERLOGGED, Boolean.FALSE));
        this.blockSupplier = blockSupplier;
    }

    /**
     * Create the {@link StateDefinition Block State definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The Block State builder}
     */
    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(TIP_DIRECTION, THICKNESS, WATERLOGGED);
    }

    /**
     * Check if the Block can stay at the given {@link BlockPos location}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param levelReader {@link LevelReader The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Boolean True if the block can survive}
     */
    @Override
    public boolean canSurvive(final BlockState blockState, final @NotNull LevelReader levelReader, final @NotNull BlockPos blockPos) {
        return isValidPointedDripstonePlacement(levelReader, blockPos, blockState.getValue(TIP_DIRECTION));
    }

    /**
     * Update the {@link BlockState Block State} on neighbor changes
     *
     * @param blockState {@link BlockState The current Block State}
     * @param direction {@link Direction The direction the changes are coming}
     * @param neighborBlockState {@link BlockState The neighbor Block State}
     * @param levelAccessor {@link LevelAccessor The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param neighborBlockPos {@link BlockPos The neighbor Block Pos}
     * @return {@link BlockState The updated Block State}
     */
    @Override
    public @NotNull BlockState updateShape(final BlockState blockState, final @NotNull Direction direction, final @NotNull BlockState neighborBlockState, final @NotNull LevelAccessor levelAccessor, final @NotNull BlockPos blockPos, final @NotNull BlockPos neighborBlockPos) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }

        if (!direction.equals(Direction.UP) && !direction.equals(Direction.DOWN)) {
            return blockState;
        }
        final Direction tipDirection = blockState.getValue(TIP_DIRECTION);
        if (tipDirection.equals(Direction.DOWN) && levelAccessor.getBlockTicks().hasScheduledTick(blockPos, this)) {
            return blockState;
        }
        if (direction.equals(tipDirection.getOpposite()) && !this.canSurvive(blockState, levelAccessor, blockPos)) {
            levelAccessor.scheduleTick(blockPos, this, tipDirection.equals(Direction.DOWN) ? 2 : 1);
            return blockState;
        }
        final DripstoneThickness thickness = calculateDripstoneThickness(levelAccessor, blockPos, tipDirection, blockState.getValue(THICKNESS).equals(DripstoneThickness.TIP_MERGE));
        return blockState.setValue(THICKNESS, thickness);
    }

    /**
     * Destroy the {@link Block Block} when hit by a {@link Projectile Projectile}
     *
     * @param level {@link Level The level reference}
     * @param blockState {@link BlockState The current Block State}
     * @param hitResult {@link BlockHitResult The Block Hit Result}
     * @param projectile {@link Projectile The Projectile that hit the Block}
     */
    @Override
    public void onProjectileHit(final Level level, final @NotNull BlockState blockState, final BlockHitResult hitResult, final @NotNull Projectile projectile) {
        final BlockPos hitPos = hitResult.getBlockPos();
        if (!level.isClientSide && projectile.mayInteract(level, hitPos) && projectile instanceof ThrownTrident && projectile.getDeltaMovement().length() > 0.6D) {
            level.destroyBlock(hitPos, true);
        }
    }

    /**
     * Damage an {@link Entity Entity} when it falls on the {@link Block Block}
     *
     * @param level {@link Level The level reference}
     * @param blockState {@link BlockState The current Block State}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param entity {@link Entity The Entity that fell onto the Block}
     * @param fallDistance {@link Float The fall distance}
     */
    @Override
    public void fallOn(final @NotNull Level level, final BlockState blockState, final @NotNull BlockPos blockPos, final @NotNull Entity entity, final float fallDistance) {
        if (blockState.getValue(TIP_DIRECTION).equals(Direction.UP) && blockState.getValue(THICKNESS).equals(DripstoneThickness.TIP)) {
            entity.causeFallDamage(fallDistance + 2.0F, 2.0F, level.damageSources().stalagmite());
        } else {
            super.fallOn(level, blockState, blockPos, entity, fallDistance);
        }
    }

    /**
     * Display the {@link ParticleType Block Particles}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param randomSource {@link RandomSource The random reference}
     */
    @Override
    public void animateTick(final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull RandomSource randomSource) {
        if (canDrip(blockState)) {
            final float chance = randomSource.nextFloat();
            if (chance <= 0.12F) {
                getFluidAboveStalactite(level, blockPos, blockState).filter(fluidInfo -> chance < 0.02F || canFillCauldron(fluidInfo.fluid)).ifPresent(fluidInfo -> spawnDripParticle(level, blockPos, blockState, fluidInfo.fluid));
            }
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
    public void tick(final @NotNull BlockState blockState, final @NotNull ServerLevel level, final @NotNull BlockPos blockPos, final @NotNull RandomSource randomSource) {
        if (isStalagmite(blockState) && !this.canSurvive(blockState, level, blockPos)) {
            level.destroyBlock(blockPos, true);
        } else {
            spawnFallingStalactite(blockState, level, blockPos);
        }
    }

    /**
     * Randomly ticks the Block
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link ServerLevel The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param randomSource {@link RandomSource The random reference}
     */
    @Override
    public void randomTick(final @NotNull BlockState blockState, final @NotNull ServerLevel level, final @NotNull BlockPos blockPos, final RandomSource randomSource) {
        maybeFillCauldron(blockState, level, blockPos, randomSource.nextFloat());
        if (randomSource.nextFloat() < 0.011377778F && isStalactiteStartPos(blockState, level, blockPos)) {
            growStalactiteOrStalagmiteIfPossible(blockState, level, blockPos);
        }
    }

    /**
     * Try to fill a {@link CauldronBlock Cauldron} when randomly ticking
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link ServerLevel The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param distance {@link Float The distance from the Cauldron}
     */
    @VisibleForTesting
    public void maybeFillCauldron(final BlockState blockState, final ServerLevel level, final BlockPos blockPos, final float distance) {
        if (!(distance > 0.17578125F)) {
            if (isStalactiteStartPos(blockState, level, blockPos)) {
                final Optional<FluidInfo> optionalFluid = getFluidAboveStalactite(level, blockPos, blockState);
                if(optionalFluid.isPresent()) {
                    final FluidInfo fluidInfo = optionalFluid.get();
                    final Fluid fluid = fluidInfo.fluid;
                    float cauldronDistance;
                    if (fluid.isSame(Fluids.WATER)) {
                        cauldronDistance = 0.17578125F;
                    } else {
                        if (!fluid.isSame(Fluids.LAVA)) {
                            return;
                        }
                        cauldronDistance = 0.05859375F;
                    }

                    if (distance < cauldronDistance) {
                        final BlockPos tipPos = findTip(blockState, level, blockPos, 11, false);
                        if (tipPos != null) {
                            if (fluidInfo.blockState.is(Blocks.MUD) && fluid.isSame(Fluids.WATER)) {
                                BlockState clayBlockState = Blocks.CLAY.defaultBlockState();
                                level.setBlockAndUpdate(fluidInfo.blockPos, clayBlockState);
                                Block.pushEntitiesUp(fluidInfo.blockState, clayBlockState, level, fluidInfo.blockPos);
                                level.gameEvent(GameEvent.BLOCK_CHANGE, fluidInfo.blockPos, GameEvent.Context.of(clayBlockState));
                                level.levelEvent(1504, tipPos, 0);
                            }
                            final BlockPos cauldronPos = findFillableCauldronBelowStalactiteTip(level, tipPos);
                            if (cauldronPos != null) {
                                final BlockState cauldronBlockState = level.getBlockState(cauldronPos);
                                final Block cauldron = cauldronBlockState.getBlock();
                                if(cauldron instanceof AbstractCauldronBlock && !((AbstractCauldronBlock)cauldron).isFull(cauldronBlockState))  {
                                    if(cauldronBlockState.is(Blocks.CAULDRON)) {
                                        if(Fluids.LAVA.equals(fluid)) {
                                            level.setBlock(cauldronPos, Blocks.LAVA_CAULDRON.defaultBlockState(), 11);
                                        } else {
                                            level.setBlock(cauldronPos, Blocks.WATER_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 1), 11);
                                        }
                                    } else if(cauldronBlockState.is(Blocks.WATER_CAULDRON)) {
                                        level.setBlockAndUpdate(cauldronPos, cauldronBlockState.setValue(LayeredCauldronBlock.LEVEL, Math.max(cauldronBlockState.getValue(LayeredCauldronBlock.LEVEL) + 1, 3)));
                                    }
                                }
                                level.scheduleTick(cauldronPos, cauldron, 50 + (tipPos.getY() - cauldronPos.getY()));
                            }
                        }
                    }
                }

            }
        }
    }

    /**
     * Get the {@link BlockState Block State} after the block has been placed
     *
     * @param placeContext {@link BlockPlaceContext The block place context}
     * @return {@link BlockState The placed Block State}
     */
    @Override
    @Nullable
    public BlockState getStateForPlacement(final BlockPlaceContext placeContext) {
        final LevelAccessor level = placeContext.getLevel();
        final BlockPos pos = placeContext.getClickedPos();
        final Direction tipDirection = calculateTipDirection(level, pos, placeContext.getNearestLookingVerticalDirection().getOpposite());
        if (tipDirection == null) {
            return null;
        }
        final DripstoneThickness thickness = calculateDripstoneThickness(level, pos, tipDirection, !placeContext.isSecondaryUseActive());
        return thickness == null ? null : this.defaultBlockState().setValue(TIP_DIRECTION, tipDirection).setValue(THICKNESS, thickness).setValue(WATERLOGGED, level.getFluidState(pos).isSourceOfType(Fluids.WATER));
    }

    /**
     * Get the {@link FluidState Block Fluid State}
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Fluids#WATER Water if is Waterlogged}
     */
    @Override
    public @NotNull FluidState getFluidState(final BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    /**
     * Get the {@link VoxelShape Block Occlusion Shape}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Shapes#empty() Empty shape}
     */
    @Override
    public @NotNull VoxelShape getOcclusionShape(final @NotNull BlockState blockState, final @NotNull BlockGetter level, final @NotNull BlockPos blockPos) {
        return Shapes.empty();
    }

    /**
     * Get the {@link VoxelShape Block Shape}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param collisionContext {@link CollisionContext The collision context}
     * @return {@link VoxelShape The Block Shape}
     */
    @Override
    public @NotNull VoxelShape getShape(final BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull CollisionContext collisionContext) {
        final DripstoneThickness thickness = blockState.getValue(THICKNESS);
        VoxelShape voxelshape = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
        switch (thickness) {
            case TIP -> {
                if (blockState.getValue(TIP_DIRECTION).equals(Direction.DOWN)) {
                    voxelshape = Block.box(5.0D, 5.0D, 5.0D, 11.0D, 16.0D, 11.0D);
                } else {
                    voxelshape = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 11.0D, 11.0D);
                }
            }
            case TIP_MERGE -> voxelshape = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
            case FRUSTUM -> voxelshape = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
            case MIDDLE -> voxelshape = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
        }
        final Vec3 offset = blockState.getOffset(blockGetter, blockPos);
        return voxelshape.move(offset.x, 0.0D, offset.z);
    }

    /**
     * Check if the {@link VoxelShape Block Shape} represents a full Block
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Boolean#FALSE False}
     */
    @Override
    public boolean isCollisionShapeFullBlock(final @NotNull BlockState blockState, final @NotNull BlockGetter level, final @NotNull BlockPos blockPos) {
        return false;
    }

    /**
     * Get the {@link Float Block Maximum Horizontal Offset}
     *
     * @return {@link Float 0.125F}
     */
    @Override
    public float getMaxHorizontalOffset() {
        return 0.125F;
    }

    /**
     * Get the {@link DamageSource Block Fall Damage Source}
     *
     * @param entity {@link Entity The entity hit by the Block}
     * @return {@link DamageSource The Block Fall Damage Source}
     */
    @Override
    public @NotNull DamageSource getFallDamageSource(final Entity entity) {
        return entity.damageSources().fallingStalactite(entity);
    }

    /**
     * Spawn the {@link FallingBlockEntity Falling Stalactite}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link ServerLevel The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     */
    private void spawnFallingStalactite(final BlockState blockState, final ServerLevel level, final BlockPos blockPos) {
        final BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();
        for(BlockState dripstoneState = blockState; isStalactite(dripstoneState); dripstoneState = level.getBlockState(mutableBlockPos)) {
            final FallingBlockEntity fallingDripstone = fall(level, mutableBlockPos, dripstoneState);
            if (isTip(dripstoneState, true)) {
                final float damage = Math.max(1 + blockPos.getY() - mutableBlockPos.getY(), 6);
                fallingDripstone.setHurtsEntities(damage, 40);
                break;
            }
            mutableBlockPos.move(Direction.DOWN);
        }
    }

    /**
     * Spawn a {@link FallingBlockEntity Falling Stalactite} when the supporting Block has been broken
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @return {@link FallingBlockEntity The Falling Stalactite Block Entity}
     */
    public FallingBlockEntity fall(final Level level, final BlockPos blockPos, final BlockState blockState) {
        final FallingBlockEntity fallingDripstone = getFallingBlockEntity(level, blockPos, blockState);
        level.setBlock(blockPos, blockState.getFluidState().createLegacyBlock(), 3);
        level.addFreshEntity(fallingDripstone);
        return fallingDripstone;
    }

    /**
     * Grow the {@link Block Stalactite} or the {@link Block Stalagmite} when randomly ticking
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link ServerLevel The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     */
    @VisibleForTesting
    public void growStalactiteOrStalagmiteIfPossible(final BlockState blockState, final ServerLevel level, final BlockPos blockPos) {
        BlockState aboveBlockState = level.getBlockState(blockPos.above(1));
        BlockState above2BlockState = level.getBlockState(blockPos.above(2));
        if (canGrow(aboveBlockState, above2BlockState)) {
            final BlockPos tipPos = findTip(blockState, level, blockPos, 7, false);
            if (tipPos != null) {
                final BlockState tipBlockState = level.getBlockState(tipPos);
                if (canDrip(tipBlockState) && canTipGrow(tipBlockState, level, tipPos)) {
                    if (level.getRandom().nextBoolean()) {
                        grow(level, tipPos, Direction.DOWN);
                    } else {
                        growStalagmiteBelow(level, tipPos);
                    }
                }
            }
        }
    }

    /**
     * Grow a {@link Block Stalagmite} below the current {@link Block Block}
     *
     * @param level {@link ServerLevel The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     */
    private void growStalagmiteBelow(final ServerLevel level, final BlockPos blockPos) {
        final BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();
        for(int i = 0; i < 10; ++i) {
            mutableBlockPos.move(Direction.DOWN);
            BlockState blockstate = level.getBlockState(mutableBlockPos);
            if (!blockstate.getFluidState().isEmpty() || !canDripThrough(level, mutableBlockPos, blockstate)) {
                return;
            }
            if (isUnmergedTipWithDirection(blockstate, Direction.UP) && canTipGrow(blockstate, level, mutableBlockPos)) {
                grow(level, mutableBlockPos, Direction.UP);
                return;
            }
            if (isValidPointedDripstonePlacement(level, mutableBlockPos, Direction.UP) && !level.isWaterAt(mutableBlockPos.below())) {
                grow(level, mutableBlockPos.below(), Direction.UP);
                return;
            }
        }
    }

    /**
     * Grow the {@link Block Pointed Dripstone}
     *
     * @param level {@link ServerLevel The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The growing direction}
     */
    private void grow(final ServerLevel level, final BlockPos blockPos, final Direction direction) {
        final BlockPos relativeBlockPos = blockPos.relative(direction);
        final BlockState relativeState = level.getBlockState(relativeBlockPos);
        if (isUnmergedTipWithDirection(relativeState, direction.getOpposite())) {
            createMergedTips(relativeState, level, relativeBlockPos);
        } else if (relativeState.isAir() || relativeState.is(Blocks.WATER)) {
            createDripstone(level, relativeBlockPos, direction, DripstoneThickness.TIP);
        }
    }

    /**
     * Create a {@link Block Pointed Dripstone} at the given {@link BlockPos Location}
     *
     * @param level {@link LevelAccessor The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The Pointed Dripstone growing direction}
     * @param thickness {@link DripstoneThickness The Pointed Dripstone thickness}
     */
    private void createDripstone(final LevelAccessor level, final BlockPos blockPos, final Direction direction, final DripstoneThickness thickness) {
        level.setBlock(blockPos, getDripstone().defaultBlockState().setValue(TIP_DIRECTION, direction).setValue(THICKNESS, thickness).setValue(WATERLOGGED, level.getFluidState(blockPos).isSourceOfType(Fluids.WATER)), 3);
    }

    /**
     * Merge the tips of two {@link Block Pointed Dripstones}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link LevelAccessor The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     */
    private void createMergedTips(final BlockState blockState, final LevelAccessor level, final BlockPos blockPos) {
        BlockPos mergePos;
        BlockPos tipPos;
        if (blockState.getValue(TIP_DIRECTION).equals(Direction.UP)) {
            tipPos = blockPos;
            mergePos = blockPos.above();
        } else {
            mergePos = blockPos;
            tipPos = blockPos.below();
        }

        createDripstone(level, mergePos, Direction.DOWN, DripstoneThickness.TIP_MERGE);
        createDripstone(level, tipPos, Direction.UP, DripstoneThickness.TIP_MERGE);
    }

    /**
     * Spawn the fluid dripping particles
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @param fluid {@link Fluid The Fluid to drip}
     */
    private static void spawnDripParticle(final Level level, final BlockPos blockPos, final BlockState blockState, final Fluid fluid) {
        final Vec3 offset = blockState.getOffset(level, blockPos);
        double x = (double)blockPos.getX() + 0.5D + offset.x;
        double y = (double)((float)(blockPos.getY() + 1) - 0.6875F) - 0.0625D;
        double z = (double)blockPos.getZ() + 0.5D + offset.z;
        final Fluid dripFluid = getDripFluid(level, fluid);
        final ParticleOptions particle = dripFluid.is(FluidTags.LAVA) ? ParticleTypes.DRIPPING_DRIPSTONE_LAVA : ParticleTypes.DRIPPING_DRIPSTONE_WATER;
        level.addParticle(particle, x, y, z, 0.0D, 0.0D, 0.0D);
    }

    /**
     * Find the {@link Block Pointed Dripstone Tip}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link LevelAccessor The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param distance {@link Integer The search distance}
     * @param shouldMerge {@link Boolean If the Pointed Dripstone tip should merge with another one}
     * @return {@link BlockPos The Pointed Dripstone Tip Location}
     */
    @Nullable
    private BlockPos findTip(final BlockState blockState, final LevelAccessor level, final BlockPos blockPos, final int distance, final boolean shouldMerge) {
        if (isTip(blockState, shouldMerge)) {
            return blockPos;
        }
        final Direction tipDirection = blockState.getValue(TIP_DIRECTION);
        return findBlockVertical(level, blockPos, tipDirection.getAxisDirection(), (tipPos, state) -> state.is(getDripstone()) && state.getValue(TIP_DIRECTION).equals(tipDirection), state -> isTip(state, shouldMerge), distance).orElse(null);
    }

    /**
     * Calculate the {@link Direction Pointed Dripstone Tip Direction}
     *
     * @param level {@link LevelReader The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The search direction}
     * @return {@link Direction The Pointed Dripstone Tip Direction}
     */
    @Nullable
    private Direction calculateTipDirection(final LevelReader level, final BlockPos blockPos, final Direction direction) {
        return isValidPointedDripstonePlacement(level, blockPos, direction) ? direction :
                !isValidPointedDripstonePlacement(level, blockPos, direction.getOpposite()) ? null : direction.getOpposite();
    }

    /**
     * Calculate the {@link DripstoneThickness Pointed Dripstone Thickness}
     *
     * @param level {@link LevelReader The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The Pointed Dripstone Direction}
     * @param shouldMerge {@link Boolean If the Pointed Dripstone tip should merge with another one}
     * @return {@link DripstoneThickness The Pointed Dripstone Thickness}
     */
    private DripstoneThickness calculateDripstoneThickness(final LevelReader level, final BlockPos blockPos, final Direction direction, final boolean shouldMerge) {
        final Direction opposite = direction.getOpposite();
        final BlockState dripstoneBlockState = level.getBlockState(blockPos.relative(direction));
        if (isPointedDripstoneWithDirection(dripstoneBlockState, opposite)) {
            return !shouldMerge && !dripstoneBlockState.getValue(THICKNESS).equals(DripstoneThickness.TIP_MERGE) ? DripstoneThickness.TIP : DripstoneThickness.TIP_MERGE;
        }
        if (!isPointedDripstoneWithDirection(dripstoneBlockState, direction)) {
            return DripstoneThickness.TIP;
        }
        final DripstoneThickness thickness = dripstoneBlockState.getValue(THICKNESS);
        if (!thickness.equals(DripstoneThickness.TIP) && !thickness.equals(DripstoneThickness.TIP_MERGE)) {
            return !isPointedDripstoneWithDirection(level.getBlockState(blockPos.relative(opposite)), direction) ? DripstoneThickness.BASE : DripstoneThickness.MIDDLE;
        }
        return DripstoneThickness.FRUSTUM;
    }

    /**
     * Check if the {@link Block Pointed Dripstone} should drip a {@link Fluid Fluid}
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean True if the Pointed Dripstone should drip}
     */
    public boolean canDrip(final BlockState blockState) {
        return isStalactite(blockState) && blockState.getValue(THICKNESS).equals(DripstoneThickness.TIP) && !blockState.getValue(WATERLOGGED);
    }

    /**
     * Check if the {@link Block Pointed Dripstone Tip} can grow
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link ServerLevel The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Boolean True if the Pointed Dripstone Tip can grow}
     */
    private boolean canTipGrow(final BlockState blockState, final ServerLevel level, final BlockPos blockPos) {
        final Direction tipDirection = blockState.getValue(TIP_DIRECTION);
        final BlockState tipState = level.getBlockState(blockPos.relative(tipDirection));
        return tipState.getFluidState().isEmpty() && (tipState.isAir() || isUnmergedTipWithDirection(tipState, tipDirection.getOpposite()));
    }

    /**
     * Get the {@link Optional<BlockPos> Pointed Dripstone Root Location}
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @param distance {@link Integer The search distance}
     * @return {@link Optional<BlockPos> The Pointed Dripstone Root Location}
     */
    private Optional<BlockPos> findRootBlock(final Level level, final BlockPos blockPos, final BlockState blockState, final int distance) {
        final Direction tipDirection = blockState.getValue(TIP_DIRECTION);
        return findBlockVertical(level, blockPos, tipDirection.getOpposite().getAxisDirection(), (tipPos, state) -> state.is(getDripstone()) && state.getValue(TIP_DIRECTION).equals(tipDirection), state -> !state.is(getDripstone()), distance);
    }

    /**
     * Check if the {@link BlockPos current Location} is valid for the {@link Block Pointed Dripstone} placement
     *
     * @param level {@link LevelReader The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The placing direction}
     * @return {@link Boolean True if the location is valid for the Pointed Dripstone placement}
     */
    private boolean isValidPointedDripstonePlacement(final LevelReader level, final BlockPos blockPos, final Direction direction) {
        final BlockPos placingPos = blockPos.relative(direction.getOpposite());
        final BlockState placedAgainstState = level.getBlockState(placingPos);
        return placedAgainstState.isFaceSturdy(level, placingPos, direction) || isPointedDripstoneWithDirection(placedAgainstState, direction);
    }

    /**
     * Check if the {@link BlockState current Block State} is a {@link Block Pointed Dripstone Tip}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param shouldMerge {@link Boolean If the Pointed Dripstone tip should merge with another one}
     * @return {@link Boolean True if the current Block State is a Pointed Dripstone Tip}
     */
    private boolean isTip(final BlockState blockState, final boolean shouldMerge) {
        if (!blockState.is(getDripstone())) {
            return false;
        }
        final DripstoneThickness thickness = blockState.getValue(THICKNESS);
        return thickness.equals(DripstoneThickness.TIP) || shouldMerge && thickness.equals(DripstoneThickness.TIP_MERGE);
    }

    /**
     * Check if the {@link BlockState current Block State} is an {@link Block Unmerged Pointed Dripstone Tip}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param direction {@link Direction The Pointed Dripstone Tip direction}
     * @return {@link Boolean True if the current Block State is an Unmerged Pointed Dripstone Tip}
     */
    private boolean isUnmergedTipWithDirection(final BlockState blockState, final Direction direction) {
        return isTip(blockState, false) && blockState.getValue(TIP_DIRECTION).equals(direction);
    }

    /**
     * Check if the {@link BlockState current Block State} is a {@link Block Stalactite}
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean True if the current Block State is a Stalactite}
     */
    private boolean isStalactite(final BlockState blockState) {
        return isPointedDripstoneWithDirection(blockState, Direction.DOWN);
    }

    /**
     * Check if the {@link BlockState current Block State} is a {@link Block Stalagmite}
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean True if the current Block State is a Stalagmite}
     */
    private boolean isStalagmite(final BlockState blockState) {
        return isPointedDripstoneWithDirection(blockState, Direction.UP);
    }

    /**
     * Check if the {@link BlockPos current Location} is a {@link Block Stalactite} {@link BlockPos starting Location}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link LevelReader The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Boolean True if the current Location is a Stalactite starting Location}
     */
    private boolean isStalactiteStartPos(final BlockState blockState, final LevelReader level, final BlockPos blockPos) {
        return isStalactite(blockState) && !level.getBlockState(blockPos.above()).is(getDripstone());
    }

    /**
     * Check if the Block is pathfindable
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param pathComputationType {@link PathComputationType The path computation type}
     * @return {@link Boolean#FALSE False}
     */
    @Override
    public boolean isPathfindable(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull PathComputationType pathComputationType) {
        return false;
    }

    /**
     * Check if the {@link BlockState current Block State} is a {@link Block Pointed Dripstone}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param direction {@link Direction The direction to check}
     * @return {@link Boolean True if the current Block State is a Pointed Dripstone}
     */
    private boolean isPointedDripstoneWithDirection(final BlockState blockState, final Direction direction) {
        return blockState.is(getDripstone()) && blockState.getValue(TIP_DIRECTION).equals(direction);
    }

    /**
     * Find a {@link AbstractCauldronBlock Cauldron} that can be filled nearby
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link BlockPos The Cauldron Location}
     */
    @Nullable
    private static BlockPos findFillableCauldronBelowStalactiteTip(final Level level, final BlockPos blockPos) {
        return findBlockVertical(level, blockPos, Direction.DOWN.getAxisDirection(), (tipPos, blockState) -> canDripThrough(level, tipPos, blockState), blockState -> blockState.getBlock() instanceof AbstractCauldronBlock, 11).orElse(null);
    }

    /**
     * Find the {@link BlockPos Stalactite Tip Location} above a {@link AbstractCauldronBlock Cauldron} to fill it
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link BlockPos The Stalactite Tip Location}
     */
    @Nullable
    public static BlockPos findStalactiteTipAboveCauldron(final Level level, final BlockPos blockPos) {
        return findBlockVertical(level, blockPos, Direction.UP.getAxisDirection(), (tipPos, blockState) -> canDripThrough(level, tipPos, blockState), (blockState) -> ((MWPointedDripstoneBlock)blockState.getBlock()).canDrip(blockState), 11).orElse(null);
    }

    /**
     * Get the {@link Fluid Cauldron Fill Fluid}
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Fluid The Cauldron Fill Fluid}
     */
    public Fluid getCauldronFillFluidType(final Level level, final BlockPos blockPos) {
        return getFluidAboveStalactite(level, blockPos, level.getBlockState(blockPos)).map(fluidInfo -> fluidInfo.fluid).filter(this::canFillCauldron).orElse(Fluids.EMPTY);
    }

    /**
     * Get the {@link Optional<Fluid> Fluid} above the {@link Block Stalactite Block}
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Optional<Fluid> The Fluid above the Stalactite}
     */
    private Optional<FluidInfo> getFluidAboveStalactite(final Level level, final BlockPos blockPos, final BlockState blockState) {
        return !isStalactite(blockState) ? Optional.empty() : findRootBlock(level, blockPos, blockState, 11).map(dripstonePos -> {
            final BlockPos abovePos = dripstonePos.above();
            final BlockState aboveBlockState = level.getBlockState(abovePos);
            Fluid fluid;
            if(aboveBlockState.is(Blocks.MUD) && !level.dimensionType().ultraWarm()) {
                fluid = Fluids.WATER;
            } else {
                fluid = level.getFluidState(dripstonePos.above()).getType();
            }
            return new MWPointedDripstoneBlock.FluidInfo(abovePos, fluid, aboveBlockState);
        });
    }

    /**
     * Check if the {@link Block Pointed Dripstone} can fill a {@link AbstractCauldronBlock Cauldron}
     *
     * @param fluid {@link Fluid The Cauldron Fill Fluid}
     * @return {@link Boolean True if the Pointed Dripstone can fill a Cauldron}
     */
    private boolean canFillCauldron(final Fluid fluid) {
        return fluid.isSame(Fluids.LAVA) || fluid.isSame(Fluids.WATER);
    }

    /**
     * Check if the {@link Block Pointed Dripstone} can grow
     *
     * @param blockState {@link BlockState The current Block State}
     * @param neighborBlockState {@link BlockState The neighbor Block State}
     * @return {@link Boolean True if the Pointed Dripstone can grow}
     */
    private boolean canGrow(final BlockState blockState, final BlockState neighborBlockState) {
        return blockState.is(getDripstoneSourceBlock()) && neighborBlockState.is(Blocks.WATER) && neighborBlockState.getFluidState().isSource();
    }

    /**
     * Get the {@link Fluid Drip Fluid}
     *
     * @param level {@link Level The level reference}
     * @param fluid {@link Fluid The Drip Fluid}
     * @return {@link Fluid The Drip Fluid}
     */
    private static Fluid getDripFluid(final Level level, final Fluid fluid) {
        return fluid.isSame(Fluids.EMPTY) ? level.dimensionType().ultraWarm() ? Fluids.LAVA : Fluids.WATER : fluid;
    }

    /**
     * Find another {@link Block Pointed Dripstone} above or below
     *
     * @param levelAccessor {@link LevelAccessor The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction.AxisDirection The axis direction to check}
     * @param blockPosBlockStateBiPredicate {@link BiPredicate The Block Pos/Block State bi predicate}
     * @param blockStatePredicate {@link Predicate<BlockState> The Block State predicate}
     * @param distance {@link Integer The search distance}
     * @return {@link Optional<BlockPos> The Pointed Dripstone Location}
     */
    private static Optional<BlockPos> findBlockVertical(final LevelAccessor levelAccessor, final BlockPos blockPos, final Direction.AxisDirection direction, final BiPredicate<BlockPos, BlockState> blockPosBlockStateBiPredicate, final Predicate<BlockState> blockStatePredicate, final int distance) {
        final BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();

        for(int i = 1; i < distance; ++i) {
            mutableBlockPos.move(Direction.get(direction, Direction.Axis.Y));
            BlockState blockState = levelAccessor.getBlockState(mutableBlockPos);
            if (blockStatePredicate.test(blockState)) {
                return Optional.of(mutableBlockPos.immutable());
            }

            if (levelAccessor.isOutsideBuildHeight(mutableBlockPos.getY()) || !blockPosBlockStateBiPredicate.test(mutableBlockPos, blockState)) {
                return Optional.empty();
            }
        }

        return Optional.empty();
    }

    /**
     * Check if the {@link Block Pointed Dripstone} can drip through another {@link Block Block}
     *
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean True if the Pointed Dripstone can drip through another Block}
     */
    private static boolean canDripThrough(final BlockGetter blockGetter, final BlockPos blockPos, final BlockState blockState) {
        return blockState.isAir() || (!blockState.isSolidRender(blockGetter, blockPos) && blockState.getFluidState().isEmpty() && !Shapes.joinIsNotEmpty(Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D), blockState.getCollisionShape(blockGetter, blockPos), BooleanOp.AND));
    }

    /**
     * Get the {@link Block Pointed Dripstone source Block}
     *
     * @return {@link Block Pointed Dripstone source Block}
     */
    public Block getDripstone() {
        return getDripstoneFor(this.blockSupplier.get());
    }

    /**
     * Get a {@link Block Pointed Dripstone Block} for the given {@link Block Block}
     *
     * @param block {@link Block The Block to get the Pointed Dripstone for}
     * @return {@link Block The Pointed Dripstone Block}
     */
    public static Block getDripstoneFor(final Block block) {
        final Optional<Block> dripstone = Optional.ofNullable(DRIPSTONES.get().get(block));
        return dripstone.orElse(Blocks.POINTED_DRIPSTONE);
    }

    /**
     * Get the {@link Block source Block} for the given {@link Block Pointed Dripstone Block}
     *
     * @return {@link Block The Pointed Dripstone source Block}
     */
    private Block getDripstoneSourceBlock() {
        final Optional<Block> source = Optional.ofNullable(SOURCE_BY_DRIPSTONE.get().get(this));
        return source.orElse(Blocks.DRIPSTONE_BLOCK);
    }

    /**
     * Drop the {@link Block Falling Dripstones} after it falls
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param fallingBlockEntity {@link FallingBlockEntity The falling Pointed Dripstone Entity}
     */
    @Override
    public void onBrokenAfterFall(final @NotNull Level level, final @NotNull BlockPos blockPos, final FallingBlockEntity fallingBlockEntity) {
        if (!fallingBlockEntity.isSilent()) {
            level.levelEvent(1045, blockPos, 0);
        }
    }

    /**
     * Get the {@link FallingBlockEntity falling Pointed Dripstone Entity}
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @return {@link FallingBlockEntity The Pointed Dripstone Entity}
     */
    public FallingBlockEntity getFallingBlockEntity(final Level level, final BlockPos blockPos, final BlockState blockState) {
        return new FallingBlockEntity(level, (double)blockPos.getX() + 0.5D, blockPos.getY(), (double)blockPos.getZ() + 0.5D, blockState.hasProperty(BlockStateProperties.WATERLOGGED) ? blockState.setValue(BlockStateProperties.WATERLOGGED, Boolean.FALSE) : blockState);
    }

    /**
     * Record for a {@link FluidInfo Pointed Dripstone Fluid Info}
     *
     * @param blockPos {@link BlockPos The Fluid Block Pos}
     * @param fluid {@link Fluid The Fluid}
     * @param blockState {@link BlockState The Fluid Block State}
     */
    record FluidInfo(BlockPos blockPos, Fluid fluid, BlockState blockState) { }

}