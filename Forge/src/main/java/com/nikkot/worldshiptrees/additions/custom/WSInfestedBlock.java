package com.nikkot.worldshiptrees.additions.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class WSInfestedBlock extends Block implements WSInfestation {

    public WSInfestedBlock(Supplier<? extends Block> hostBlock, Supplier<? extends EntityType<? extends Mob>> entitySupplier, BlockBehaviour.Properties properties) {
        super(properties.destroyTime(hostBlock.get().defaultDestroyTime() / 2.0F).explosionResistance(0.75F));
        registerInfestation(this, hostBlock, entitySupplier);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void spawnAfterBreak(@NotNull BlockState blockState, @NotNull ServerLevel level, @NotNull BlockPos blockPos, @NotNull ItemStack itemStack, boolean condition) {
        super.spawnAfterBreak(blockState, level, blockPos, itemStack, condition);
        spawnInfestation(blockState, level, blockPos, itemStack, false);
    }
}
