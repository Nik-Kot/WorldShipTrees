package com.nikkot.worldshiptrees.additions.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class WSInfestedWoodBlock extends WSInfestedPillarBlock {

    public WSInfestedWoodBlock(Block hostBlock, Supplier<? extends EntityType<? extends Mob>> entitySupplier, Properties properties) {
        super(hostBlock, entitySupplier, properties);
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState blockState) {
        return false;
    }
}
