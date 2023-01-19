package com.nikkot.worldshiptrees;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WorldShipColors {
    public static final TextColor orange  =  TextColor.fromRgb(0xFF7700);

    public static final TextColor red  =  TextColor.fromRgb(0xFF0000);

    public static final BlockColor rubber_leaves = new BlockColor() {
        @Override
        public int getColor(@NotNull BlockState p_92567_, @Nullable BlockAndTintGetter p_92568_, @Nullable BlockPos p_92569_, int p_92570_) {
            return 0xFF00FF00;
        }
    };

    public static final ItemColor rubber_leaves_item = new ItemColor() {
        @Override
        public int getColor(@NotNull ItemStack p_92672_, int p_92673_) {
            return 0xFF00FF00;
        }
    };
}
