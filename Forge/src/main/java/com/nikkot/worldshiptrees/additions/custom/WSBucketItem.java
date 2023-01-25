package com.nikkot.worldshiptrees.additions.custom;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Supplier;

public class WSBucketItem extends BucketItem {

    private final int fluidColor;

    @Deprecated
    public WSBucketItem(Fluid fluid, Item.Properties builder, int fluidColor) {
        super(fluid, builder);
        this.fluidColor = fluidColor;
    }
    public WSBucketItem(Supplier<? extends Fluid> supplier, Item.Properties builder, int fluidColor) {
        super(supplier, builder);
        this.fluidColor = fluidColor;
    }

    public int getColor(int layer) {
        return layer == 0 ? 0xFFFFFFFF : this.fluidColor;
    }


}
