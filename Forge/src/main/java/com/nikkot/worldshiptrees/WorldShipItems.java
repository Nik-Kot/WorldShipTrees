package com.nikkot.worldshiptrees;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class WorldShipItems {



    public static final RegistryObject<Item> ITEM_CREATIVE_TAB = WorldShipRegisters.itemRegister.register("creative_tab_item", () -> new SimpleFoiledItem(new Item.Properties()));
    public static final CreativeModeTab CREATIVE_MODE_TAB = new CreativeModeTab(WorldShipTrees.MODID) {
        @Override
        @NotNull
        public ItemStack makeIcon() {
            return ITEM_CREATIVE_TAB.get().getDefaultInstance();
        }

        /*@Override
        @NotNull
        public Component getDisplayName() {
            Style style = Style.EMPTY.withColor(WorldShipColors.orange);
            return Component.translatable("itemGroup." + WorldShipTrees.MODID).withStyle(style);
        }*/
    };

    public static final Rarity RARITY_LEGENDARY = Rarity.create("legendary", style -> {
        return style.withColor(WorldShipColors.orange);
    });
    public static final Rarity RARITY_MYTHIC = Rarity.create("mythic", style -> {
        return style.withColor(WorldShipColors.red);
    });

    public static final RegistryObject<Item> ITEM_RUBBER_WOOD_LOG = WorldShipRegisters.itemRegister.register("rubber_wood_log", () -> new BlockItem(WorldShipBlocks.BLOCK_RUBBER_WOOD_LOG.get(), new Item.Properties().tab(CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> ITEM_RUBBER_WOOD_LEAVES = WorldShipRegisters.itemRegister.register("rubber_wood_leaves", () -> new BlockItem(WorldShipBlocks.BLOCK_RUBBER_WOOD_LEAVES.get(), new Item.Properties().tab(CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> ITEM_RUBBER_WOOD_SAPLING = WorldShipRegisters.itemRegister.register("rubber_wood_sapling", () -> new BlockItem(WorldShipBlocks.BLOCK_RUBBER_WOOD_SAPLING.get(), new Item.Properties().tab(CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> ITEM_SACRED_RUBBER_WOOD_SAPLING = WorldShipRegisters.itemRegister.register("sacred_rubber_wood_sapling", () -> new BlockItem(WorldShipBlocks.BLOCK_SACRED_RUBBER_WOOD_SAPLING.get() ,new Item.Properties().tab(CREATIVE_MODE_TAB).rarity(RARITY_LEGENDARY)) {
        @Override
        public boolean isFoil(@NotNull ItemStack stack) {
            return true;
        }
    });


    public static final List<CreativeModeTab> tabs = new ArrayList<>();
    public static final List<RegistryObject<Item>> items = new ArrayList<>();

    public static List<RegistryObject<Item>> registerItems (DeferredRegister<Item> itemRegister, IEventBus eventBus) {

        tabs.add(CREATIVE_MODE_TAB);

        items.add(ITEM_RUBBER_WOOD_LOG);
        items.add(ITEM_RUBBER_WOOD_LEAVES);
        items.add(ITEM_RUBBER_WOOD_SAPLING);
        items.add(ITEM_SACRED_RUBBER_WOOD_SAPLING);

        itemRegister.register(eventBus);

        return items;
    }
}
