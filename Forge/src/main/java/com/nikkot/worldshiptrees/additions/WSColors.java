package com.nikkot.worldshiptrees.additions;

import com.nikkot.worldshiptrees.additions.custom.WSBucketItem;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WSColors {
    public static final TextColor orange  =  TextColor.fromRgb(0xFF7700);

    public static final TextColor red  =  TextColor.fromRgb(0xFF0000);

    public static final BlockColor block_rubber_leaves = new BlockColor() {
        @Override
        public int getColor(@NotNull BlockState blockState, @Nullable BlockAndTintGetter blockAndTintGetter, @Nullable BlockPos blockPos, int tintIndex) {
            return 0xFF00FF00;
        }
    };

    public static final ItemColor item_rubber_leaves = new ItemColor() {
        @Override
        public int getColor(@NotNull ItemStack itemStack, int tintIndex) {
            return 0xFF00FF00;
        }
    };

    public static final ItemColor item_bucket = new ItemColor() {
        @Override
        public int getColor(@NotNull ItemStack itemStack, int tintIndex) {
            WSBucketItem wsBucketItem = (WSBucketItem) itemStack.getItem();
            return wsBucketItem.getColor(tintIndex);
        }
    };

    public static final int tree_sap = MaterialColor.WOOD.col + 0xFF000000;

    public static final int podzol = MaterialColor.PODZOL.col + 0xFF000000;
}
