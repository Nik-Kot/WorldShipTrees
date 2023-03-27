package com.nikkot.worldshiptrees.additions.custom;

import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Supplier;

public interface WSInfestation {
    Map<Block, Block> INFESTED_BY_HOST_BLOCK = Maps.newIdentityHashMap();
    Map<Block, Block> HOST_BY_INFESTED_BLOCK = Maps.newIdentityHashMap();

    Map<Block, Supplier<? extends EntityType<? extends Mob>>> MOB_BY_INFESTED_BLOCK = Maps.newIdentityHashMap();
    Map<Block, Supplier<? extends EntityType<? extends Mob>>> MOB_BY_HOST_BLOCK = Maps.newIdentityHashMap();

    Map<BlockState, BlockState> HOST_TO_INFESTED_STATES = Maps.newIdentityHashMap();
    Map<BlockState, BlockState> INFESTED_TO_HOST_STATES = Maps.newIdentityHashMap();
    Map<BlockState, BlockState> INFESTED_TO_HOLLOW_STATES = Maps.newIdentityHashMap();

    Map<Block, Block> HOLLOW_BY_INFESTED_BLOCK = Maps.newIdentityHashMap();
    Map<Block, Block> HOLLOW_BY_HOST_BLOCK = Maps.newIdentityHashMap();

    default void registerInfestation(Block infestedBlock, Block hostBlock, Supplier<? extends EntityType<? extends Mob>> entitySupplier) {
        INFESTED_BY_HOST_BLOCK.put(hostBlock, infestedBlock);
        HOST_BY_INFESTED_BLOCK.put(infestedBlock, hostBlock);
        MOB_BY_INFESTED_BLOCK.put(infestedBlock, entitySupplier);
        MOB_BY_HOST_BLOCK.put(hostBlock, entitySupplier);
    }

    default void registerHollow( Block infestedBlock, Block hostBlock, Block hollowBlock) {
        HOLLOW_BY_HOST_BLOCK.put(hostBlock, hollowBlock);
        HOLLOW_BY_INFESTED_BLOCK.put(infestedBlock, hollowBlock);
    }

    static boolean isCompatibleHostBlock(Block hostBlock, EntityType<?> entityType) {
        Supplier<? extends EntityType<? extends Mob>> entitySupplier = MOB_BY_HOST_BLOCK.get(hostBlock);
        if (entitySupplier != null) {
            return entityType == entitySupplier.get();
        }
        return false;
    }

    static boolean isCompatibleInfestedBlock(Block infestedBlock, EntityType<?> entityType) {
        Supplier<? extends EntityType<? extends Mob>> entitySupplier =  MOB_BY_INFESTED_BLOCK.get(infestedBlock);
        if (entitySupplier != null) {
            return entityType == entitySupplier.get();
        }
        return false;
    }

    default void spawnInfestation(@NotNull BlockState blockState, @NotNull ServerLevel level, @NotNull BlockPos blockPos, @NotNull ItemStack itemStack) {
        EntityType<? extends Mob> infestEntity = MOB_BY_INFESTED_BLOCK.get(blockState.getBlock()).get();
        if (infestEntity != null && level.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS) && itemStack.getEnchantmentLevel(Enchantments.SILK_TOUCH) == 0) {
            Mob entity = infestEntity.create(level);
            if (entity != null) {
                entity.moveTo((double) blockPos.getX() + 0.5d, (double) blockPos.getY(), (double) blockPos.getZ() + 0.5d, 0.0f, 0.0f);
                level.addFreshEntity(entity);
                entity.spawnAnim();
            }
        }
    }

    static BlockState infestedStateByHost(BlockState blockState) {
        Block infestedBlock = INFESTED_BY_HOST_BLOCK.get(blockState.getBlock());
        if (infestedBlock != null) {
            return getNewStateWithProperties(HOST_TO_INFESTED_STATES, blockState, infestedBlock::defaultBlockState);
        } else {
            return blockState;
        }
    }

    static BlockState hostStateByInfested(BlockState blockState) {
        Block hostBlock = HOST_BY_INFESTED_BLOCK.get(blockState.getBlock());
        if (hostBlock != null) {
            return getNewStateWithProperties(INFESTED_TO_HOST_STATES, blockState, hostBlock::defaultBlockState);
        } else {
            return blockState;
        }
    }

    static BlockState hollowStateByInfested(BlockState blockState) {
        Block hollowBlock = HOLLOW_BY_INFESTED_BLOCK.get(blockState.getBlock());
        if (hollowBlock != null) {
            return getNewStateWithProperties(INFESTED_TO_HOLLOW_STATES, blockState, hollowBlock::defaultBlockState);
        } else {
            return blockState;
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    static BlockState getNewStateWithProperties(Map<BlockState, BlockState> stateMap, BlockState blockState, Supplier<BlockState> blockStateSupplier) {
        return stateMap.computeIfAbsent(blockState, (blockState1) -> {
            BlockState blockstate = blockStateSupplier.get();

            for(Property property : blockState1.getProperties()) {
                blockstate = blockstate.hasProperty(property) ? blockstate.setValue(property, blockState1.getValue(property)) : blockstate;
            }

            return blockstate;
        });
    }
}
