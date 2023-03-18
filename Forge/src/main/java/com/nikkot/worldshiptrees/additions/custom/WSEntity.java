package com.nikkot.worldshiptrees.additions.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class WSEntity extends Silverfish {


    public WSEntity(EntityType<? extends Silverfish> entityType, Level level) {
        super(entityType, level);
    }
}
