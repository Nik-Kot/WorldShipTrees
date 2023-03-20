package com.nikkot.worldshiptrees.additions.custom;

import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Supplier;

public class WSInfestedBlock extends Block {
    private final Block hostBlock;
    private final EntityType<? extends Mob> infestEntity;
    private static final Map<Block, WSInfestedBlock> BLOCK_BY_HOST_BLOCK = Maps.newIdentityHashMap();
    private static final Map<Block, EntityType<? extends Mob>> MOB_BY_HOST_BLOCK = Maps.newIdentityHashMap();
    private static final Map<BlockState, BlockState> HOST_TO_INFESTED_STATES = Maps.newIdentityHashMap();
    private static final Map<BlockState, BlockState> INFESTED_TO_HOST_STATES = Maps.newIdentityHashMap();

    public WSInfestedBlock(Block block, EntityType<? extends Mob> entity, BlockBehaviour.Properties properties) {
        super(properties.destroyTime(block.defaultDestroyTime() / 2.0F).explosionResistance(0.75F));
        this.hostBlock = block;
        this.infestEntity = entity;
        BLOCK_BY_HOST_BLOCK.put(block, this);
        MOB_BY_HOST_BLOCK.put(block, entity);
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 5;
    }

    public Block getHostBlock() {
        return this.hostBlock;
    }

    public static boolean isCompatibleHostBlock(BlockState blockState, EntityType<?> entityType) {
        return BLOCK_BY_HOST_BLOCK.containsKey(blockState.getBlock()) && MOB_BY_HOST_BLOCK.containsValue(entityType);
    }

    private void spawnInfestation(ServerLevel level, BlockPos blockPos) {
        Mob entity = infestEntity.create(level);
        if (entity != null) {
            entity.moveTo((double) blockPos.getX() + 0.5d, (double) blockPos.getY(), (double) blockPos.getZ() + 0.5d, 0.0f, 0.0f);
            level.addFreshEntity(entity);
            entity.spawnAnim();
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void spawnAfterBreak(@NotNull BlockState blockState, @NotNull ServerLevel level, @NotNull BlockPos blockPos, @NotNull ItemStack itemStack, boolean condition) {
        super.spawnAfterBreak(blockState, level, blockPos, itemStack, condition);
        if (level.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS) && itemStack.getEnchantmentLevel(Enchantments.SILK_TOUCH) == 0) {
            this.spawnInfestation(level, blockPos);
        }

    }

    public static BlockState infestedStateByHost(BlockState blockState) {
        return getNewStateWithProperties(HOST_TO_INFESTED_STATES, blockState, () ->
                BLOCK_BY_HOST_BLOCK.get(blockState.getBlock()).defaultBlockState());
    }

    public BlockState hostStateByInfested(BlockState blockState) {
        return getNewStateWithProperties(INFESTED_TO_HOST_STATES, blockState, () ->
                this.getHostBlock().defaultBlockState());
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static BlockState getNewStateWithProperties(Map<BlockState, BlockState> stateMap, BlockState blockState, Supplier<BlockState> blockStateSupplier) {
        return stateMap.computeIfAbsent(blockState, (blockState1) -> {
            BlockState blockstate = blockStateSupplier.get();

            for(Property property : blockState1.getProperties()) {
                blockstate = blockstate.hasProperty(property) ? blockstate.setValue(property, blockState1.getValue(property)) : blockstate;
            }

            return blockstate;
        });
    }

    public boolean isCorrectEntity(EntityType<?> entityType) {
        return entityType == infestEntity;
    }
}
