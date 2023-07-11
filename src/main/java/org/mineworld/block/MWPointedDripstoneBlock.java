package org.mineworld.block;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
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
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.helper.RandomHelper;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link Block Pointed Dripstone Block}
 */
public class MWPointedDripstoneBlock extends Block implements Fallable, SimpleWaterloggedBlock {

    /**
     * Dripstone Blocks from {@link Block source blocks}
     */
    private static Supplier<BiMap<Block, Block>> DRIPSTONES = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(Blocks.STONE, MWBlocks.STONE_POINTED_DRIPSTONE.get())
            .put(Blocks.SANDSTONE, MWBlocks.SANDSTONE_POINTED_DRIPSTONE.get())
            .put(Blocks.RED_SANDSTONE, MWBlocks.RED_SANDSTONE_POINTED_DRIPSTONE.get())
            .put(Blocks.ICE, MWBlocks.ICE_POINTED_DRIPSTONE.get())
            .put(Blocks.PACKED_ICE, MWBlocks.PACKED_ICE_POINTED_DRIPSTONE.get())
            .put(Blocks.BLUE_ICE, MWBlocks.BLUE_ICE_POINTED_DRIPSTONE.get())
            .put(Blocks.DEEPSLATE, MWBlocks.DEEPSLATE_POINTED_DRIPSTONE.get())
            .put(Blocks.GRANITE, MWBlocks.GRANITE_POINTED_DRIPSTONE.get())
            .put(Blocks.DIORITE, MWBlocks.DIORITE_POINTED_DRIPSTONE.get())
            .put(Blocks.ANDESITE, MWBlocks.ANDESITE_POINTED_DRIPSTONE.get())
            .put(Blocks.CALCITE, MWBlocks.CALCITE_POINTED_DRIPSTONE.get())
            .put(Blocks.TUFF, MWBlocks.TUFF_POINTED_DRIPSTONE.get())
            .put(Blocks.PRISMARINE, MWBlocks.PRISMARINE_POINTED_DRIPSTONE.get())
            .put(Blocks.NETHERRACK, MWBlocks.NETHERRACK_POINTED_DRIPSTONE.get())
            .put(Blocks.CRIMSON_NYLIUM, MWBlocks.CRIMSON_NYLIUM_POINTED_DRIPSTONE.get())
            .put(Blocks.WARPED_NYLIUM, MWBlocks.WARPED_NYLIUM_POINTED_DRIPSTONE.get())
            .put(Blocks.SOUL_SOIL, MWBlocks.SOUL_SOIL_POINTED_DRIPSTONE.get())
            .put(Blocks.BLACKSTONE, MWBlocks.BLACKSTONE_POINTED_DRIPSTONE.get())
            .put(Blocks.BASALT, MWBlocks.BASALT_POINTED_DRIPSTONE.get())
            .put(Blocks.END_STONE, MWBlocks.END_STONE_POINTED_DRIPSTONE.get())
            .put(MWBlocks.PERENNIAL_ICE.get(), MWBlocks.PERENNIAL_ICE_POINTED_DRIPSTONE.get())
            .put(MWBlocks.MARBLE.get(), MWBlocks.MARBLE_POINTED_DRIPSTONE.get())
        .build()
    );

    Supplier<BiMap<Block, Block>> SOURCE_BY_DRIPSTONE = Suppliers.memoize(() -> DRIPSTONES.get().inverse());

    /**
     * {@link DirectionProperty The dripstone tip direction}
     */
    public static DirectionProperty TIP_DIRECTION = BlockStateProperties.VERTICAL_DIRECTION;
    /**
     * {@link DripstoneThickness The dripstone thickness value}
     */
    public static EnumProperty<DripstoneThickness> THICKNESS = BlockStateProperties.DRIPSTONE_THICKNESS;
    /**
     * {@link BooleanProperty The dripstone waterlogged property}
     */
    public static BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    /**
     * The {@link Block block} the dripstone is based on
     */
    private Block SOURCE_BLOCK;

    /**
     * Supplier constructor. Sets the {@link Block pointed dripstone} properties
     *
     * @param blockSupplier {@link Supplier<Block> The supplier for the dripstone source block}
     */
    public MWPointedDripstoneBlock(Supplier<Block> blockSupplier) {
        this(blockSupplier.get());
    }

    /**
     * Constructor. Sets the {@link Block pointed dripstone} properties
     *
     * @param block {@link Block The dripstone source block}
     */
    public MWPointedDripstoneBlock(Block block) {
        super(PropertyHelper.copyFromBlock(block).noOcclusion().randomTicks().strength(0.5F).dynamicShape().offsetType(OffsetType.XZ));
        this.registerDefaultState(this.stateDefinition.any().setValue(TIP_DIRECTION, Direction.UP).setValue(THICKNESS, DripstoneThickness.TIP).setValue(WATERLOGGED, Boolean.FALSE));
        this.SOURCE_BLOCK = block;
    }

    /**
     * Create the {@link BlockState block state definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The block state builder}
     */
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(TIP_DIRECTION, THICKNESS, WATERLOGGED);
    }

    /**
     * Check if the {@link Block dripstone} can survive at the given {@link BlockPos location}
     *
     * @param state {@link BlockState The current block state}
     * @param level {@link LevelReader The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @return {@link Boolean True if the dripstone can survive}
     */
    @Override
    public boolean canSurvive(BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
        return isValidPointedDripstonePlacement(level, pos, state.getValue(TIP_DIRECTION));
    }

    /**
     * Update the {@link BlockState block state} if the
     * {@link Block dripstone} is waterlogged or the neighbor change
     *
     * @param state {@link BlockState The current block state}
     * @param direction {@link Direction The update direction}
     * @param neighborState {@link BlockState The neighbor block state}
     * @param level {@link LevelAccessor The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param neighborPos {@link BlockPos The neighbor block state}
     * @return {@link BlockState The updated block state}
     */
    @Override
    public @NotNull BlockState updateShape(BlockState state, @NotNull Direction direction, @NotNull BlockState neighborState, @NotNull LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        if (!direction.equals(Direction.UP) && !direction.equals(Direction.DOWN)) {
            return state;
        }
        Direction tipDirection = state.getValue(TIP_DIRECTION);
        if (tipDirection.equals(Direction.DOWN) && level.getBlockTicks().hasScheduledTick(pos, this)) {
            return state;
        }
        if (direction.equals(tipDirection.getOpposite()) && !this.canSurvive(state, level, pos)) {
            level.scheduleTick(pos, this, tipDirection.equals(Direction.DOWN) ? 2 : 1);
            return state;
        }
        DripstoneThickness thickness = calculateDripstoneThickness(level, pos, tipDirection, state.getValue(THICKNESS).equals(DripstoneThickness.TIP_MERGE));
        return state.setValue(THICKNESS, thickness);
    }

    /**
     * Destroy the {@link Block dripstone} when hit by a {@link Projectile projectile}
     *
     * @param level {@link Level The level reference}
     * @param state {@link BlockState The current block state}
     * @param hitResult {@link BlockHitResult The block hit result}
     * @param projectile {@link Projectile The projectile that hit the block}
     */
    @Override
    public void onProjectileHit(Level level, @NotNull BlockState state, BlockHitResult hitResult, @NotNull Projectile projectile) {
        BlockPos hitPos = hitResult.getBlockPos();
        if (!level.isClientSide && projectile.mayInteract(level, hitPos) && projectile instanceof ThrownTrident && projectile.getDeltaMovement().length() > 0.6D) {
            level.destroyBlock(hitPos, true);
        }
    }

    /**
     * Apply fall damage to an entity when the {@link Block dripstone} falls
     *
     * @param level {@link Level The level reference}
     * @param state {@link BlockState The current block state}
     * @param pos {@link BlockPos The current block pos}
     * @param entity {@link Entity The entity that has been hit}
     * @param fallDistance {@link Float The fall distance}
     */
    @Override
    public void fallOn(@NotNull Level level, BlockState state, @NotNull BlockPos pos, @NotNull Entity entity, float fallDistance) {
        if (state.getValue(TIP_DIRECTION).equals(Direction.UP) && state.getValue(THICKNESS).equals(DripstoneThickness.TIP)) {
            entity.causeFallDamage(fallDistance + 2.0F, 2.0F, level.damageSources().stalagmite());
        } else {
            super.fallOn(level, state, pos, entity, fallDistance);
        }
    }

    /**
     * Show the drip particles
     *
     * @param state {@link BlockState The current block state}
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param random {@link RandomSource The random reference}
     */
    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (canDrip(state)) {
            float chance = random.nextFloat();
            if (chance <= 0.12F) {
                getFluidAboveStalactite(level, pos, state).filter(fluidInfo -> chance < 0.02F || canFillCauldron(fluidInfo.fluid)).ifPresent(fluidInfo -> spawnDripParticle(level, pos, state, fluidInfo.fluid));
            }
        }
    }

    /**
     * Tick the {@link Block dripstone block}
     *
     * @param state {@link BlockState The current block state}
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param random {@link RandomSource The random reference}
     */
    @Override
    public void tick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (isStalagmite(state) && !this.canSurvive(state, level, pos)) {
            level.destroyBlock(pos, true);
        } else {
            spawnFallingStalactite(state, level, pos);
        }
    }

    /**
     * Tick the {@link Block dripstone block} randomly
     *
     * @param state {@link BlockState The current block state}
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param random {@link RandomSource The random reference}
     */
    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, RandomSource random) {
        maybeFillCauldron(state, level, pos, random.nextFloat());
        if (random.nextFloat() < 0.011377778F && isStalactiteStartPos(state, level, pos)) {
            growStalactiteOrStalagmiteIfPossible(state, level, pos);
        }
    }

    /**
     * Check if the {@link Block dripstone} can fill the cauldron
     *
     * @param state {@link BlockState The current block state}
     * @param level {@link ServerLevel The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param distance {@link Float The distance from the cauldron}
     */
    @VisibleForTesting
    public void maybeFillCauldron(BlockState state, ServerLevel level, BlockPos pos, float distance) {
        if (!(distance > 0.17578125F)) {
            if (isStalactiteStartPos(state, level, pos)) {
                Optional<FluidInfo> optionalFluid = getFluidAboveStalactite(level, pos, state);
                if(optionalFluid.isPresent()) {
                    FluidInfo fluidInfo = optionalFluid.get();
                    Fluid fluid = fluidInfo.fluid;
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
                        BlockPos tipPos = findTip(state, level, pos, 11, false);
                        if (tipPos != null) {
                            if (fluidInfo.sourceState.is(Blocks.MUD) && fluid.isSame(Fluids.WATER)) {
                                BlockState clayBlockState = Blocks.CLAY.defaultBlockState();
                                level.setBlockAndUpdate(fluidInfo.pos, clayBlockState);
                                Block.pushEntitiesUp(fluidInfo.sourceState, clayBlockState, level, fluidInfo.pos);
                                level.gameEvent(GameEvent.BLOCK_CHANGE, fluidInfo.pos, GameEvent.Context.of(clayBlockState));
                                level.levelEvent(1504, tipPos, 0);
                            }
                            BlockPos cauldronPos = findFillableCauldronBelowStalactiteTip(level, tipPos);
                            if (cauldronPos != null) {
                                BlockState cauldronBlockState = level.getBlockState(cauldronPos);
                                Block cauldron = cauldronBlockState.getBlock();
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
     * Destroy the {@link Block dripstone} on push by pistons
     *
     * @param state {@link BlockState The current block state}
     * @return {@link PushReaction#DESTROY The destroy piston push reaction}
     */
    @Override
    public @NotNull PushReaction getPistonPushReaction(@NotNull BlockState state) {
        return PushReaction.DESTROY;
    }

    /**
     * Get the {@link BlockState dripstone block state} when the block is placed
     *
     * @param context {@link BlockPlaceContext The block place context}
     * @return Placed {@link BlockState The palced block state}
     */
    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelAccessor level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction tipDirection = calculateTipDirection(level, pos, context.getNearestLookingVerticalDirection().getOpposite());
        if (tipDirection == null) {
            return null;
        }
        DripstoneThickness thickness = calculateDripstoneThickness(level, pos, tipDirection, !context.isSecondaryUseActive());
        return thickness == null ? null : this.defaultBlockState().setValue(TIP_DIRECTION, tipDirection).setValue(THICKNESS, thickness).setValue(WATERLOGGED, level.getFluidState(pos).isSourceOfType(Fluids.WATER));
    }

    /**
     * Get the {@link FluidState fluid state}
     *
     * @param state {@link BlockState The current block state}
     * @return {@link FluidState The dripstone fluid state}
     */
    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    /**
     * Get the {@link VoxelShape dripstone shape}
     *
     * @param state {@link BlockState The current block state}
     * @param level {@link BlockGetter The block getter reference}
     * @param pos {@link BlockPos The current block pos}
     * @return {@link VoxelShape The dripstone shape}
     */
    @Override
    public @NotNull VoxelShape getOcclusionShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
        return Shapes.empty();
    }

    /**
     * Get the {@link VoxelShape dripstone shape}
     * based on its properties
     *
     * @param state {@link BlockState The current block properties}
     * @param level {@link BlockGetter The block getter reference}
     * @param pos {@link BlockPos The current block pos}
     * @param context {@link CollisionContext The collision context}
     * @return {@link VoxelShape The dripstone shape}
     */
    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        DripstoneThickness thickness = state.getValue(THICKNESS);
        VoxelShape voxelshape = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
        switch (thickness) {
            case TIP -> {
                if (state.getValue(TIP_DIRECTION).equals(Direction.DOWN)) {
                    voxelshape = Block.box(5.0D, 5.0D, 5.0D, 11.0D, 16.0D, 11.0D);
                } else {
                    voxelshape = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 11.0D, 11.0D);
                }
            }
            case TIP_MERGE -> voxelshape = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
            case FRUSTUM -> voxelshape = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
            case MIDDLE -> voxelshape = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
        }
        Vec3 offset = state.getOffset(level, pos);
        return voxelshape.move(offset.x, 0.0D, offset.z);
    }

    /**
     * Check if the {@link VoxelShape dripstone shape} represents a full block
     *
     * @param state {@link BlockState The current block state}
     * @param level {@link BlockGetter The block getter reference}
     * @param pos {@link BlockPos The current block pos}
     * @return {@link Boolean False}
     */
    @Override
    public boolean isCollisionShapeFullBlock(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
        return false;
    }

    /**
     * Get the {@link Float dripstone maximum horizontal offset}
     *
     * @return {@link Float The dripstone maximum horizontal offset}
     */
    @Override
    public float getMaxHorizontalOffset() {
        return 0.125F;
    }

    /**
     * Get the {@link DamageSource falling dripstone damage source}
     *
     * @param entity {@link Entity The entity that has been hit by the falling dripstone}
     * @return {@link DamageSource The falling dripstone damage source}
     */
    @Override
    public @NotNull DamageSource getFallDamageSource(Entity entity) {
        return entity.damageSources().fallingStalactite(entity);
    }

    /**
     * Get the {@link Predicate<Entity> entity predicate} for the damage functions
     *
     * @return {@link Predicate<Entity> The entity predicate} for the damage functions
     */
    @Override
    public @NotNull Predicate<Entity> getHurtsEntitySelector() {
        return EntitySelector.NO_CREATIVE_OR_SPECTATOR.and(EntitySelector.LIVING_ENTITY_STILL_ALIVE);
    }

    /**
     * Spawn the {@link FallingBlockEntity falling dripstone}
     *
     * @param state {@link BlockState The current block state}
     * @param level {@link ServerLevel The level reference}
     * @param pos {@link BlockPos The current block pos}
     */
    private void spawnFallingStalactite(BlockState state, ServerLevel level, BlockPos pos) {
        BlockPos.MutableBlockPos mutableBlockPos = pos.mutable();
        for(BlockState dripstoneState = state; isStalactite(dripstoneState); dripstoneState = level.getBlockState(mutableBlockPos)) {
            FallingBlockEntity fallingDripstone = fall(level, mutableBlockPos, dripstoneState);
            if (isTip(dripstoneState, true)) {
                float damage = Math.max(1 + pos.getY() - mutableBlockPos.getY(), 6);
                fallingDripstone.setHurtsEntities(damage, 40);
                break;
            }
            mutableBlockPos.move(Direction.DOWN);
        }
    }

    /**
     * Spawn a {@link FallingBlockEntity falling dripstone} when the supporting block has been broken
     *
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param state {@link BlockState The current block state}
     * @return {@link FallingBlockEntity The falling dripstone}
     */
    public FallingBlockEntity fall(Level level, BlockPos pos, BlockState state) {
        FallingBlockEntity fallingDripstone = getFallingBlockEntity(level, pos, state);
        level.setBlock(pos, state.getFluidState().createLegacyBlock(), 3);
        level.addFreshEntity(fallingDripstone);
        return fallingDripstone;
    }

    /**
     * Grow the {@link Block dripstone} if possible
     *
     * @param state {@link BlockState The current block state}
     * @param level {@link ServerLevel The level reference}
     * @param pos {@link BlockPos The current block pos}
     */
    @VisibleForTesting
    public void growStalactiteOrStalagmiteIfPossible(BlockState state, ServerLevel level, BlockPos pos) {
        BlockState aboveBlockState = level.getBlockState(pos.above(1));
        BlockState above2BlockState = level.getBlockState(pos.above(2));
        if (canGrow(aboveBlockState, above2BlockState)) {
            BlockPos tipPos = findTip(state, level, pos, 7, false);
            if (tipPos != null) {
                BlockState tipBlockState = level.getBlockState(tipPos);
                if (canDrip(tipBlockState) && canTipGrow(tipBlockState, level, tipPos)) {
                    if (RandomHelper.choose()) {
                        grow(level, tipPos, Direction.DOWN);
                    } else {
                        growStalagmiteBelow(level, tipPos);
                    }
                }
            }
        }
    }

    /**
     * Grow a {@link Block dripstone} below
     *
     * @param level {@link ServerLevel The level reference}
     * @param pos {@link BlockPos The current block pos}
     */
    private void growStalagmiteBelow(ServerLevel level, BlockPos pos) {
        BlockPos.MutableBlockPos mutableBlockPos = pos.mutable();

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
     * Grow a {@link Block dripstone}
     *
     * @param level {@link ServerLevel The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param direction {@link Direction The growing direction}
     */
    private void grow(ServerLevel level, BlockPos pos, Direction direction) {
        BlockPos relativeBlockPos = pos.relative(direction);
        BlockState relativeState = level.getBlockState(relativeBlockPos);
        if (isUnmergedTipWithDirection(relativeState, direction.getOpposite())) {
            createMergedTips(relativeState, level, relativeBlockPos);
        } else if (relativeState.isAir() || relativeState.is(Blocks.WATER)) {
            createDripstone(level, relativeBlockPos, direction, DripstoneThickness.TIP);
        }
    }

    /**
     * Create a {@link Block dripstone} at the given {@link BlockPos location}
     *
     * @param level {@link LevelAccessor The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param direction {@link Direction The dripstone growing direction}
     * @param thickness {@link DripstoneThickness The dripstone thickness}
     */
    private void createDripstone(LevelAccessor level, BlockPos pos, Direction direction, DripstoneThickness thickness) {
        level.setBlock(pos, getDripstone().defaultBlockState().setValue(TIP_DIRECTION, direction).setValue(THICKNESS, thickness).setValue(WATERLOGGED, level.getFluidState(pos).isSourceOfType(Fluids.WATER)), 3);
    }

    /**
     * Create a {@link Block merged dripstone}
     *
     * @param state {@link BlockState The current block state}
     * @param level {@link LevelAccessor The level reference}
     * @param pos {@link BlockPos The current block pos}
     */
    private void createMergedTips(BlockState state, LevelAccessor level, BlockPos pos) {
        BlockPos mergePos;
        BlockPos tipPos;
        if (state.getValue(TIP_DIRECTION).equals(Direction.UP)) {
            tipPos = pos;
            mergePos = pos.above();
        } else {
            mergePos = pos;
            tipPos = pos.below();
        }

        createDripstone(level, mergePos, Direction.DOWN, DripstoneThickness.TIP_MERGE);
        createDripstone(level, tipPos, Direction.UP, DripstoneThickness.TIP_MERGE);
    }

    /**
     * Spawn the drip particles
     *
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The currnet block pos}
     * @param state {@link BlockState The current block state}
     * @param fluid {@link Fluid The fluid to drip}
     */
    private static void spawnDripParticle(Level level, BlockPos pos, BlockState state, Fluid fluid) {
        Vec3 offset = state.getOffset(level, pos);
        double x = (double)pos.getX() + 0.5D + offset.x;
        double y = (double)((float)(pos.getY() + 1) - 0.6875F) - 0.0625D;
        double z = (double)pos.getZ() + 0.5D + offset.z;
        Fluid dripFluid = getDripFluid(level, fluid);
        ParticleOptions particle = dripFluid.is(FluidTags.LAVA) ? ParticleTypes.DRIPPING_DRIPSTONE_LAVA : ParticleTypes.DRIPPING_DRIPSTONE_WATER;
        level.addParticle(particle, x, y, z, 0.0D, 0.0D, 0.0D);
    }

    /**
     * Find the {@link Block dripstone tip}
     *
     * @param state {@link BlockState The current block state}
     * @param level {@link LevelAccessor The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param distance {@link Integer The search distance}
     * @param shouldMerge {@link Boolean If the dripstone should merge}
     * @return {@link BlockPos The tip block pos}, if any
     */
    @Nullable
    private BlockPos findTip(BlockState state, LevelAccessor level, BlockPos pos, int distance, boolean shouldMerge) {
        if (isTip(state, shouldMerge)) {
            return pos;
        }
        Direction tipDirection = state.getValue(TIP_DIRECTION);
        return findBlockVertical(level, pos, tipDirection.getAxisDirection(), (tipPos, blockState) -> blockState.is(getDripstone()) && blockState.getValue(TIP_DIRECTION).equals(tipDirection), blockState -> isTip(blockState, shouldMerge), distance).orElse(null);
    }

    /**
     * Calculate the {@link Direction tip direction}
     *
     * @param level {@link LevelReader The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param direction {@link Direction The search direction}
     * @return {@link Direction The tip direction}
     */
    @Nullable
    private Direction calculateTipDirection(LevelReader level, BlockPos pos, Direction direction) {
        return isValidPointedDripstonePlacement(level, pos, direction) ? direction :
                !isValidPointedDripstonePlacement(level, pos, direction.getOpposite()) ? null : direction.getOpposite();
    }

    /**
     * Calculate the {@link DripstoneThickness dripstone thickness}
     *
     * @param level {@link LevelReader The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param direction {@link Direction The dripstone direction}
     * @param shouldMerge {@link Boolean If the dripstone should merge}
     * @return {@link DripstoneThickness The dripstone thickness}
     */
    private DripstoneThickness calculateDripstoneThickness(LevelReader level, BlockPos pos, Direction direction, boolean shouldMerge) {
        Direction opposite = direction.getOpposite();
        BlockState dripstoneBlockState = level.getBlockState(pos.relative(direction));
        if (isPointedDripstoneWithDirection(dripstoneBlockState, opposite)) {
            return !shouldMerge && !dripstoneBlockState.getValue(THICKNESS).equals(DripstoneThickness.TIP_MERGE) ? DripstoneThickness.TIP : DripstoneThickness.TIP_MERGE;
        }
        if (!isPointedDripstoneWithDirection(dripstoneBlockState, direction)) {
            return DripstoneThickness.TIP;
        }
        DripstoneThickness thickness = dripstoneBlockState.getValue(THICKNESS);
        if (!thickness.equals(DripstoneThickness.TIP) && !thickness.equals(DripstoneThickness.TIP_MERGE)) {
            return !isPointedDripstoneWithDirection(level.getBlockState(pos.relative(opposite)), direction) ? DripstoneThickness.BASE : DripstoneThickness.MIDDLE;
        }
        return DripstoneThickness.FRUSTUM;
    }

    /**
     * Check if the {@link Block dripstone} should drip
     *
     * @param state {@link BlockState The current block state}
     * @return {@link Boolean True if the dripstone should drip}
     */
    public boolean canDrip(BlockState state) {
        return isStalactite(state) && state.getValue(THICKNESS).equals(DripstoneThickness.TIP) && !state.getValue(WATERLOGGED);
    }

    /**
     * Check if the {@link Block dripstone tip} can grow
     *
     * @param state {@link BlockState The current block state}
     * @param level {@link ServerLevel The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @return {@link Boolean True if the dripstone tip can grow}
     */
    private boolean canTipGrow(BlockState state, ServerLevel level, BlockPos pos) {
        Direction tipDirection = state.getValue(TIP_DIRECTION);
        BlockState tipState = level.getBlockState(pos.relative(tipDirection));
        return tipState.getFluidState().isEmpty() && (tipState.isAir() || isUnmergedTipWithDirection(tipState, tipDirection.getOpposite()));
    }

    /**
     * Get the {@link Optional<BlockPos> dripstone root block pos}
     *
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param state {@link BlockState The current block state}
     * @param distance {@link Integer The search distance}
     * @return {@link Optional<BlockPos> The dripstone root block pos}
     */
    private Optional<BlockPos> findRootBlock(Level level, BlockPos pos, BlockState state, int distance) {
        Direction tipDirection = state.getValue(TIP_DIRECTION);
        return findBlockVertical(level, pos, tipDirection.getOpposite().getAxisDirection(), (tipPos, blockState) -> blockState.is(getDripstone()) && blockState.getValue(TIP_DIRECTION).equals(tipDirection), blockState -> !blockState.is(getDripstone()), distance);
    }

    /**
     * Check if the {@link BlockPos current location} is valid for the {@link Block dripstone} placement
     *
     * @param level {@link LevelReader The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param direction {@link Direction The placing direction}
     * @return {@link Boolean True} if the location is valid for the dripstone placement}
     */
    private boolean isValidPointedDripstonePlacement(LevelReader level, BlockPos pos, Direction direction) {
        BlockPos placingPos = pos.relative(direction.getOpposite());
        BlockState placedAgainstState = level.getBlockState(placingPos);
        return placedAgainstState.isFaceSturdy(level, placingPos, direction) || isPointedDripstoneWithDirection(placedAgainstState, direction);
    }

    /**
     * Check if the {@link BlockState current block state} is a tip
     *
     * @param state {@link BlockState The current block state}
     * @param shouldMerge {@link Boolean If the dripstone should merge}
     * @return {@link Boolean True if the current block state is a tip}
     */
    private boolean isTip(BlockState state, boolean shouldMerge) {
        if (!state.is(getDripstone())) {
            return false;
        }
        DripstoneThickness thickness = state.getValue(THICKNESS);
        return thickness.equals(DripstoneThickness.TIP) || shouldMerge && thickness.equals(DripstoneThickness.TIP_MERGE);
    }

    /**
     * Check if the {@link BlockState current block state} is an unmerged tip
     *
     * @param state {@link BlockState The current block state}
     * @param direction {@link Direction The tip direction}
     * @return {@link Boolean True if the current block state is an unmerged tip}
     */
    private boolean isUnmergedTipWithDirection(BlockState state, Direction direction) {
        return isTip(state, false) && state.getValue(TIP_DIRECTION).equals(direction);
    }

    /**
     * Check if the {@link BlockState current block state} is a stalactite
     *
     * @param state {@link BlockState The current block state}
     * @return {@link Boolean True if the current block state is a stalactite}
     */
    private boolean isStalactite(BlockState state) {
        return isPointedDripstoneWithDirection(state, Direction.DOWN);
    }

    /**
     * Check if the {@link BlockState current block state} is a stalagmite
     *
     * @param state {@link BlockState The current block state}
     * @return {@link Boolean True if the current block state is a stalagmite}
     */
    private boolean isStalagmite(BlockState state) {
        return isPointedDripstoneWithDirection(state, Direction.UP);
    }

    /**
     * Check if the {@link BlockPos current location} is a stalactite starting location
     *
     * @param state {@link BlockState The current block state}
     * @param level {@link LevelReader The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @return {@link Boolean True if the current location is a stalactite starting location}
     */
    private boolean isStalactiteStartPos(BlockState state, LevelReader level, BlockPos pos) {
        return isStalactite(state) && !level.getBlockState(pos.above()).is(getDripstone());
    }

    /**
     * Check if the {@link Block dripstone} is pathfindable by mobs
     *
     * @param state {@link BlockState The current block state}
     * @param level {@link BlockGetter The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param pathComputationType {@link PathComputationType The path computation type}
     * @return {@link Boolean False}
     */
    @Override
    public boolean isPathfindable(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull PathComputationType pathComputationType) {
        return false;
    }

    /**
     * Check if the {@link BlockState current block state} is a {@link Block pointed dripstone}
     *
     * @param state {@link BlockState The current block state}
     * @param direction {@link Direction The direction to check}
     * @return {@link Boolean True if the current block state is a pointed dripstone}
     */
    private boolean isPointedDripstoneWithDirection(BlockState state, Direction direction) {
        return state.is(getDripstone()) && state.getValue(TIP_DIRECTION).equals(direction);
    }

    /**
     * Get the {@link BlockPos block pos} for a {@link AbstractCauldronBlock cauldron} to fill
     *
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @return {@link BlockPos The cauldron block pos}, if any
     */
    @Nullable
    private static BlockPos findFillableCauldronBelowStalactiteTip(Level level, BlockPos pos) {
        return findBlockVertical(level, pos, Direction.DOWN.getAxisDirection(), (tipPos, blockState) -> canDripThrough(level, tipPos, blockState), blockState -> blockState.getBlock() instanceof AbstractCauldronBlock, 11).orElse(null);
    }

    /**
     * Find a stalactite tip above a {@link AbstractCauldronBlock cauldron} to fill it
     *
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @return {@link BlockPos The stalactite tip pos}, if any
     */
    @Nullable
    public static BlockPos findStalactiteTipAboveCauldron(Level level, BlockPos pos) {
        return findBlockVertical(level, pos, Direction.UP.getAxisDirection(), (tipPos, blockState) -> canDripThrough(level, tipPos, blockState), (blockState) -> ((MWPointedDripstoneBlock)blockState.getBlock()).canDrip(blockState), 11).orElse(null);
    }

    /**
     * Get the {@link Fluid cauldron fill fluid}
     *
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @return {@link Fluid The cauldron fill fluid}
     */
    public Fluid getCauldronFillFluidType(Level level, BlockPos pos) {
        return getFluidAboveStalactite(level, pos, level.getBlockState(pos)).map(fluidInfo -> fluidInfo.fluid).filter(this::canFillCauldron).orElse(Fluids.EMPTY);
    }

    /**
     * Get the {@link Optional<Fluid> fluid} above the stalactite
     *
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param state {@link BlockState The current block state}
     * @return {@link Optional<Fluid> The fluid above the stalactite}, if any
     */
    private Optional<FluidInfo> getFluidAboveStalactite(Level level, BlockPos pos, BlockState state) {
        return !isStalactite(state) ? Optional.empty() : findRootBlock(level, pos, state, 11).map(dripstonePos -> {
            BlockPos abovePos = dripstonePos.above();
            BlockState aboveBlockState = level.getBlockState(abovePos);
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
     * Check if the {@link Block dripstone} can fill a {@link AbstractCauldronBlock cauldron}
     *
     * @param fluid {@link Fluid The cauldron fill fluid}
     * @return {@link Boolean True if the dripstone can fill a cauldron}
     */
    private boolean canFillCauldron(Fluid fluid) {
        return fluid.isSame(Fluids.LAVA) || fluid.isSame(Fluids.WATER);
    }

    /**
     * Check if the {@link Block dripstone} can grow
     *
     * @param state {@link BlockState The current block state}
     * @param neighborState {@link BlockState The neighbor block state}
     * @return {@link Boolean True if the dripstone can grow}
     */
    private boolean canGrow(BlockState state, BlockState neighborState) {
        return state.is(getDripstoneSourceBlock()) && neighborState.is(Blocks.WATER) && neighborState.getFluidState().isSource();
    }

    /**
     * Get the {@link Fluid drip fluid}
     *
     * @param level {@link Level The level reference}
     * @param fluid {@link Fluid The drip fluid}
     * @return {@link Fluid The drip fluid}, if any
     */
    private static Fluid getDripFluid(Level level, Fluid fluid) {
        return fluid.isSame(Fluids.EMPTY) ? level.dimensionType().ultraWarm() ? Fluids.LAVA : Fluids.WATER : fluid;
    }

    /**
     * Find another {@link Block dripstone} above or below
     *
     * @param level {@link LevelAccessor The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param direction {@link Direction.AxisDirection The axis direction to check}
     * @param posBlockStateBiPredicate {@link BiPredicate The pos/state bi predicate}
     * @param statePredicate {@link Predicate<BlockState> The block state predicate}
     * @param distance {@link Integer The search distance}
     * @return {@link Optional<BlockPos> The dripstone block pos}, if any
     */
    private static Optional<BlockPos> findBlockVertical(LevelAccessor level, BlockPos pos, Direction.AxisDirection direction, BiPredicate<BlockPos, BlockState> posBlockStateBiPredicate, Predicate<BlockState> statePredicate, int distance) {
        BlockPos.MutableBlockPos mutableBlockPos = pos.mutable();

        for(int i = 1; i < distance; ++i) {
            mutableBlockPos.move(Direction.get(direction, Direction.Axis.Y));
            BlockState blockState = level.getBlockState(mutableBlockPos);
            if (statePredicate.test(blockState)) {
                return Optional.of(mutableBlockPos.immutable());
            }

            if (level.isOutsideBuildHeight(mutableBlockPos.getY()) || !posBlockStateBiPredicate.test(mutableBlockPos, blockState)) {
                return Optional.empty();
            }
        }

        return Optional.empty();
    }

    /**
     * Check if the {@link Block dripstone} can drip through another {@link Block block}
     *
     * @param level {@link BlockGetter The block getter reference}
     * @param pos {@link BlockPos The current block pos}
     * @param state {@link BlockState The current block state}
     * @return {@link Boolean True if the dripstone can drip through another block}
     */
    private static boolean canDripThrough(BlockGetter level, BlockPos pos, BlockState state) {
        return state.isAir() || (!state.isSolidRender(level, pos) && state.getFluidState().isEmpty() && !Shapes.joinIsNotEmpty(Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D), state.getCollisionShape(level, pos), BooleanOp.AND));
    }

    /**
     * Get the {@link Block pointed dripstone source block}
     *
     * @return {@link Block Pointed Dripstone Block}
     */
    public Block getDripstone() {
        return getDripstoneFor(this.SOURCE_BLOCK);
    }

    /**
     * Get a {@link Block pointed dripstone block} for the given {@link Block block}
     *
     * @param block {@link Block The block to get the dripstone for}
     * @return {@link Block The pointed dripstone block}, if any
     */
    public static Block getDripstoneFor(Block block) {
        Optional<Block> dripstone = Optional.ofNullable(DRIPSTONES.get().get(block));
        return dripstone.orElse(Blocks.POINTED_DRIPSTONE);
    }

    /**
     * Get a {@link Block pointed dripstone source block} for the current dripstone
     *
     * @return {@link Block Pointed Dripstone Block}
     */
    private Block getDripstoneSourceBlock() {
        Optional<Block> source = Optional.ofNullable(SOURCE_BY_DRIPSTONE.get().get(this));
        return source.orElse(Blocks.DRIPSTONE_BLOCK);
    }

    /**
     * Drop the {@link Block falling dripstones} after it falls
     *
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param fallingBlockEntity {@link FallingBlockEntity The falling dripstone entity}
     */
    @Override
    public void onBrokenAfterFall(@NotNull Level level, @NotNull BlockPos pos, FallingBlockEntity fallingBlockEntity) {
        if (!fallingBlockEntity.isSilent()) {
            level.levelEvent(1045, pos, 0);
        }
    }

    /**
     * Get the {@link FallingBlockEntity falling dripstone entity}
     *
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param state {@link BlockState The current block state}
     * @return {@link FallingBlockEntity The falling dripstone entity}
     */
    public FallingBlockEntity getFallingBlockEntity(Level level, BlockPos pos, BlockState state) {
        return new FallingBlockEntity(level, (double)pos.getX() + 0.5D, pos.getY(), (double)pos.getZ() + 0.5D, state.hasProperty(BlockStateProperties.WATERLOGGED) ? state.setValue(BlockStateProperties.WATERLOGGED, Boolean.FALSE) : state);
    }

    /**
     * Record for a {@link FluidInfo pointed dripstone fluid info}
     *
     * @param pos {@link BlockPos The fluid block pos}
     * @param fluid {@link Fluid The fluid}
     * @param sourceState {@link BlockState The fluid source block state}
     */
    record FluidInfo(BlockPos pos, Fluid fluid, BlockState sourceState) { }
}