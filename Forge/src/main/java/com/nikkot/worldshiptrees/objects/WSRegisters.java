package com.nikkot.worldshiptrees.objects;

import com.nikkot.worldshiptrees.WorldShipTrees;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSizeType;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacerType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WSRegisters {

    public static final DeferredRegister<Item> itemRegister = DeferredRegister.create(ForgeRegistries.ITEMS, WorldShipTrees.MODID);
    public static final DeferredRegister<Block> blockRegister = DeferredRegister.create(ForgeRegistries.BLOCKS, WorldShipTrees.MODID);
    public static final DeferredRegister<Fluid> fluidRegister = DeferredRegister.create(ForgeRegistries.FLUIDS, WorldShipTrees.MODID);
/*
    public static final DeferredRegister<WorldShipTrunkPlacerType<?>> trunkPlacerRegister = DeferredRegister.create(new ResourceLocation(WorldShipTrees.MODID, "trunk_placer_registry"), WorldShipTrees.MODID);
    public static final Supplier<IForgeRegistry<WorldShipTrunkPlacerType<?>>> trunkPlacerRegistry = trunkPlacerRegister.makeRegistry(RegistryBuilder::new);

    public static final DeferredRegister<WorldShipTreeDecoratorType<?>> treeDecoratorRegister = DeferredRegister.create(new ResourceLocation(WorldShipTrees.MODID, "tree_decorator_registry"), WorldShipTrees.MODID);
    public static final Supplier<IForgeRegistry<WorldShipTreeDecoratorType<?>>> treeDecoratorRegistry = treeDecoratorRegister.makeRegistry(RegistryBuilder::new);

    public static final DeferredRegister<WorldShipRootPlacerType<?>> rootPlacerRegister = DeferredRegister.create(new ResourceLocation(WorldShipTrees.MODID, "root_placer_registry"), WorldShipTrees.MODID);
    public static final Supplier<IForgeRegistry<WorldShipRootPlacerType<?>>> rootPlacerRegistry = rootPlacerRegister.makeRegistry(RegistryBuilder::new);

    public static final DeferredRegister<WorldShipFoliagePlacerType<?>> foliagePlacerRegister = DeferredRegister.create(new ResourceLocation(WorldShipTrees.MODID, "foliage_placer_registry"), WorldShipTrees.MODID);
    public static final Supplier<IForgeRegistry<WorldShipFoliagePlacerType<?>>> foliagePlacerRegistry = foliagePlacerRegister.makeRegistry(RegistryBuilder::new);
*/

    //public static final List<Supplier<IForgeRegistry>> registries = new ArrayList<>();

    public static final DeferredRegister<TrunkPlacerType<?>> trunkPlacerRegister = DeferredRegister.create(Registry.TRUNK_PLACER_TYPE_REGISTRY, WorldShipTrees.MODID);
    public static final DeferredRegister<TreeDecoratorType<?>> treeDecoratorRegister = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, WorldShipTrees.MODID);
    public static final DeferredRegister<RootPlacerType<?>> rootPlacerRegister = DeferredRegister.create(Registry.ROOT_PLACER_TYPE_REGISTRY, WorldShipTrees.MODID);
    public static final DeferredRegister<FoliagePlacerType<?>> foliagePlacerRegister = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, WorldShipTrees.MODID);
    public static final DeferredRegister<FeatureSizeType<?>> featureSizeRegister = DeferredRegister.create(Registry.FEATURE_SIZE_TYPE_REGISTRY, WorldShipTrees.MODID);

    public static final DeferredRegister<Feature<?>> featureRegister = DeferredRegister.create(ForgeRegistries.FEATURES, WorldShipTrees.MODID);
    public static final DeferredRegister<ConfiguredFeature<?, ?>> configuredFeatureRegister = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, WorldShipTrees.MODID);
    public static void registerEverything(IEventBus eventBus) {

        WSTreeGenTypes.registerTrunkTypes(trunkPlacerRegister);
        trunkPlacerRegister.register(eventBus);

        WSTreeGenTypes.registerTreeDecorators(treeDecoratorRegister);
        trunkPlacerRegister.register(eventBus);

        WSTreeGenTypes.registerRootTypes(rootPlacerRegister);
        rootPlacerRegister.register(eventBus);

        WSTreeGenTypes.registerFoliageTypes(foliagePlacerRegister);
        foliagePlacerRegister.register(eventBus);

        WSTreeGenTypes.registerFeatureSizes(featureSizeRegister);
        featureSizeRegister.register(eventBus);

        WSFeatures.registerFeatures(featureRegister);
        featureRegister.register(eventBus);

        //Blocks need to be registered first due to use of BlockItems
        WSBlocks.registerBlocks(blockRegister);

        WSFeatures.registerConfiguredFeatures(configuredFeatureRegister);
        configuredFeatureRegister.register(eventBus);

        WSBlocks.registerBlocksSecondary(blockRegister);
        blockRegister.register(eventBus);

        //WorldShipFluids.registerFluids(fluidRegister, eventBus);


        WSItems.registerItems(itemRegister);
        itemRegister.register(eventBus);

    }


    /*public static void registerRegistries() {
        Supplier<IForgeRegistry<WorldShipTrunkPlacerType<?>>> trunkPlacerTmp = trunkPlacerRegistry;
        Supplier<IForgeRegistry<WorldShipTreeDecoratorType<?>>> treeDecoratorTmp = treeDecoratorRegistry;
        Supplier<IForgeRegistry<WorldShipRootPlacerType<?>>> rootPlacerTmp = rootPlacerRegistry;
        Supplier<IForgeRegistry<WorldShipFoliagePlacerType<?>>> foliagePlacerTmp = foliagePlacerRegistry;
    }*/

    /*public static void registerFluids (IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }*/

}
