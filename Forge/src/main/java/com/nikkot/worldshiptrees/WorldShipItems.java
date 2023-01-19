package com.nikkot.worldshiptrees;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.UnaryOperator;

public class WorldShipItems {



    public static RegistryObject<Item> ITEM_CREATIVE_TAB;
    public static RegistryObject<Item> ITEM_RUBBER_WOOD_LOG;
    public static RegistryObject<Item> ITEM_RUBBER_WOOD_LEAVES;

    public static RegistryObject<Item> ITEM_RUBBER_SAPLING;

    public static RegistryObject<Item> ITEM_SACRED_RUBBER_SAPLING;

    public static CreativeModeTab CREATIVE_MODE_TAB;

    public static void registerItems (DeferredRegister<Item> itemRegister, IEventBus eventBus) {

        ITEM_CREATIVE_TAB = itemRegister.register("creative_tab_item", () -> new Item(new Item.Properties()) {
            @Override
            public boolean isFoil(@NotNull ItemStack stack) {
                return true;
            }
        });

        CREATIVE_MODE_TAB = registerCreativeTab(ITEM_CREATIVE_TAB);

        ITEM_RUBBER_WOOD_LOG = itemRegister.register("rubber_wood_log", () -> new BlockItem(WorldShipBlocks.BLOCK_RUBBER_WOOD_LOG.get(), new Item.Properties().tab(CREATIVE_MODE_TAB)));
        ITEM_RUBBER_WOOD_LEAVES = itemRegister.register("rubber_wood_leaves", () -> new BlockItem(WorldShipBlocks.BLOCK_RUBBER_WOOD_LEAVES.get(), new Item.Properties().tab(CREATIVE_MODE_TAB)));

        ITEM_RUBBER_SAPLING = itemRegister.register("rubber_sapling", () -> new Item(new Item.Properties().tab(CREATIVE_MODE_TAB)));

        ITEM_SACRED_RUBBER_SAPLING = itemRegister.register("sacred_rubber_sapling", () -> new Item(new Item.Properties().tab(CREATIVE_MODE_TAB).rarity(RARITY_LEGENDARY)) {
            @Override
            public boolean isFoil(@NotNull ItemStack stack) {
                return true;
            }
        });

        itemRegister.register(eventBus);
    }

    public static CreativeModeTab registerCreativeTab(RegistryObject<Item> creativeTabItem) {
        return new CreativeModeTab(WorldShipTrees.MODID) {
            @Override
            @NotNull
            public ItemStack makeIcon() {
                return creativeTabItem.get().getDefaultInstance();
            }
            /*@Override
            @NotNull
            public Component getDisplayName() {
                Style style = Style.EMPTY.withColor(WorldShipColors.orange);
                return Component.translatable("itemGroup." + WorldShipTrees.MODID).withStyle(style);
            }*/
        };
    }
    public static final Rarity RARITY_LEGENDARY = Rarity.create("legendary", style -> {
        return style.withColor(WorldShipColors.orange);
    });
    public static final Rarity RARITY_MYTHIC = Rarity.create("mythic", style -> {
        return style.withColor(WorldShipColors.red);
    });
}
