package com.nikkot.worldshiptrees.additions;

import com.nikkot.worldshiptrees.additions.custom.WSBucketItem;
import com.nikkot.worldshiptrees.WorldShipTrees;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class WSItems {



    public static final RegistryObject<SimpleFoiledItem> ITEM_CREATIVE_TAB = WSRegisters
            .itemRegister.register("creative_tab_item", () ->
                    new SimpleFoiledItem (
                            new Item.Properties()));
    public static final CreativeModeTab CREATIVE_MODE_TAB = new CreativeModeTab(WorldShipTrees.MODID) {
        @Override
        @NotNull
        public ItemStack makeIcon() {
            return ITEM_CREATIVE_TAB.get().getDefaultInstance();
        }

        /*@Override
        @NotNull
        public Component getDisplayName() {
            Style style = Style.EMPTY.withColor(WSColors.orange);
            return Component.translatable("itemGroup." + WorldShipTrees.MODID).withStyle(style);
        }*/
    };

    public static final Rarity RARITY_LEGENDARY = Rarity.create("legendary",
            (style) -> style.withColor(WSColors.orange));
    public static final Rarity RARITY_MYTHIC = Rarity.create("mythic",
            (style) -> style.withColor(WSColors.red));

    public static final RegistryObject<BlockItem> ITEM_RUBBER_WOOD_LOG = WSRegisters
            .itemRegister.register("rubber_wood_log", () ->
                    new BlockItem (
                            WSBlocks.BLOCK_RUBBER_WOOD_LOG.get(),
                            new Item.Properties()
                                    .tab(CREATIVE_MODE_TAB)));

    public static final RegistryObject<BlockItem> ITEM_HOLLOW_RUBBER_WOOD_LOG = WSRegisters
            .itemRegister.register("hollow_rubber_wood_log", () ->
                    new BlockItem (
                            WSBlocks.BLOCK_HOLLOW_RUBBER_WOOD_LOG.get(),
                            new Item.Properties()
                                    .tab(CREATIVE_MODE_TAB)));
    public static final RegistryObject<BlockItem> ITEM_RUBBER_WOOD_LEAVES = WSRegisters
            .itemRegister.register("rubber_wood_leaves", () ->
                    new BlockItem (
                            WSBlocks.BLOCK_RUBBER_WOOD_LEAVES.get(),
                            new Item.Properties()
                                    .tab(CREATIVE_MODE_TAB)));
    public static final RegistryObject<BlockItem> ITEM_RUBBER_WOOD_SAPLING = WSRegisters
            .itemRegister.register("rubber_wood_sapling", () ->
                    new BlockItem (
                            WSBlocks.BLOCK_RUBBER_WOOD_SAPLING.get(),
                            new Item.Properties()
                                    .tab(CREATIVE_MODE_TAB)));
    public static final RegistryObject<BlockItem> ITEM_SACRED_RUBBER_WOOD_SAPLING = WSRegisters
            .itemRegister.register("sacred_rubber_wood_sapling", () ->
                    new BlockItem (
                            WSBlocks.BLOCK_SACRED_RUBBER_WOOD_SAPLING.get(),
                            new Item.Properties()
                                    .tab(CREATIVE_MODE_TAB)
                                    .rarity(RARITY_LEGENDARY)) {
                        @Override
                        public boolean isFoil(@NotNull ItemStack stack) {
                            return true;
                        }
                    });

    public static final RegistryObject<WSBucketItem> ITEM_BUCKET_TREE_SAP = WSRegisters
            .itemRegister.register("tree_sap_bucket", () ->
                    new WSBucketItem(
                            WSFluids.FLUID_TREE_SAP_SOURCE,
                            new Item.Properties()
                                    .craftRemainder(Items.BUCKET)
                                    .stacksTo(1)
                                    .tab(CREATIVE_MODE_TAB),
                            WSColors.tree_sap));


    public static final RegistryObject<BlockItem> ITEM_INFESTED_RUBBER_WOOD_LOG = WSRegisters
            .itemRegister.register("infested_rubber_wood_log", () ->
                    new BlockItem (
                            WSBlocks.BLOCK_INFESTED_RUBBER_WOOD_LOG.get(),
                            new Item.Properties()
                                    .tab(CREATIVE_MODE_TAB)));

    public static final RegistryObject<BlockItem> ITEM_INFESTED_HOLLOW_RUBBER_WOOD_LOG = WSRegisters
            .itemRegister.register("infested_hollow_rubber_wood_log", () ->
                    new BlockItem (
                            WSBlocks.BLOCK_INFESTED_HOLLOW_RUBBER_WOOD_LOG.get(),
                            new Item.Properties()
                                    .tab(CREATIVE_MODE_TAB)));


    public static final RegistryObject<ForgeSpawnEggItem> ITEM_SPAWN_EGG_WS_ENTITY = WSRegisters
            .itemRegister.register("ws_entity_spawn_egg", () ->
                    new ForgeSpawnEggItem(WSEntities.ENTITY_WS_ENTITY,
                            WSColors.tree_sap,
                            WSColors.podzol,
                            new Item.Properties()
                                    .tab(CREATIVE_MODE_TAB)));
    public static final List<CreativeModeTab> tabs = new ArrayList<>();
    public static final List<RegistryObject<? extends Item>> items = new ArrayList<>();

    public static List<RegistryObject<? extends Item>> registerItems (DeferredRegister<Item> itemRegister) {

        tabs.add(CREATIVE_MODE_TAB);

        items.add(ITEM_RUBBER_WOOD_LOG);
        items.add(ITEM_RUBBER_WOOD_LEAVES);
        items.add(ITEM_RUBBER_WOOD_SAPLING);
        items.add(ITEM_SACRED_RUBBER_WOOD_SAPLING);

        items.add(ITEM_INFESTED_RUBBER_WOOD_LOG);
        items.add(ITEM_INFESTED_HOLLOW_RUBBER_WOOD_LOG);

        items.add(ITEM_SPAWN_EGG_WS_ENTITY);

        return items;
    }
}
