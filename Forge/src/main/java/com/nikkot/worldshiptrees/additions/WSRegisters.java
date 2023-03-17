package com.nikkot.worldshiptrees.additions;

import com.nikkot.worldshiptrees.WorldShipTrees;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityType;
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
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class WSRegisters {

    public static final DeferredRegister<Item> itemRegister = DeferredRegister
            .create(ForgeRegistries.ITEMS, WorldShipTrees.MODID);
    public static final DeferredRegister<Block> blockRegister = DeferredRegister
            .create(ForgeRegistries.BLOCKS, WorldShipTrees.MODID);

    public static final DeferredRegister<FluidType> fluidTypeRegister = DeferredRegister
            .create(ForgeRegistries.Keys.FLUID_TYPES, WorldShipTrees.MODID);
    public static final DeferredRegister<Fluid> fluidRegister = DeferredRegister
            .create(ForgeRegistries.FLUIDS, WorldShipTrees.MODID);
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

    public static final DeferredRegister<TrunkPlacerType<?>> trunkPlacerRegister = DeferredRegister
            .create(Registry.TRUNK_PLACER_TYPE_REGISTRY, WorldShipTrees.MODID);
    public static final DeferredRegister<TreeDecoratorType<?>> treeDecoratorRegister = DeferredRegister
            .create(ForgeRegistries.TREE_DECORATOR_TYPES, WorldShipTrees.MODID);
    public static final DeferredRegister<RootPlacerType<?>> rootPlacerRegister = DeferredRegister
            .create(Registry.ROOT_PLACER_TYPE_REGISTRY, WorldShipTrees.MODID);
    public static final DeferredRegister<FoliagePlacerType<?>> foliagePlacerRegister = DeferredRegister
            .create(ForgeRegistries.FOLIAGE_PLACER_TYPES, WorldShipTrees.MODID);
    public static final DeferredRegister<FeatureSizeType<?>> featureSizeRegister = DeferredRegister
            .create(Registry.FEATURE_SIZE_TYPE_REGISTRY, WorldShipTrees.MODID);

    public static final DeferredRegister<Feature<?>> featureRegister = DeferredRegister
            .create(ForgeRegistries.FEATURES, WorldShipTrees.MODID);
    public static final DeferredRegister<ConfiguredFeature<?, ?>> configuredFeatureRegister = DeferredRegister
            .create(Registry.CONFIGURED_FEATURE_REGISTRY, WorldShipTrees.MODID);

    public static final DeferredRegister<EntityType<?>> entityTypeRegister = DeferredRegister
            .create(Registry.ENTITY_TYPE_REGISTRY, WorldShipTrees.MODID);

    public static final List<DeferredRegister<?>> registers = new ArrayList<>();
    public static void registerEverything(IEventBus eventBus) {

        WSTreeGenTypes.registerTrunkTypes(trunkPlacerRegister);
        trunkPlacerRegister.register(eventBus);
        registers.add(trunkPlacerRegister);

        WSTreeGenTypes.registerTreeDecorators(treeDecoratorRegister);
        treeDecoratorRegister.register(eventBus);
        registers.add(treeDecoratorRegister);

        WSTreeGenTypes.registerRootTypes(rootPlacerRegister);
        rootPlacerRegister.register(eventBus);
        registers.add(rootPlacerRegister);

        WSTreeGenTypes.registerFoliageTypes(foliagePlacerRegister);
        foliagePlacerRegister.register(eventBus);
        registers.add(foliagePlacerRegister);

        WSTreeGenTypes.registerFeatureSizes(featureSizeRegister);
        featureSizeRegister.register(eventBus);
        registers.add(featureSizeRegister);

        WSFeatures.registerFeatures(featureRegister);
        featureRegister.register(eventBus);
        registers.add(featureRegister);

        WSFluids.registerFluidTypes(fluidTypeRegister);
        fluidTypeRegister.register(eventBus);
        registers.add(fluidTypeRegister);

        WSFluids.registerFluids(fluidRegister);
        fluidRegister.register(eventBus);
        registers.add(fluidRegister);

        //Blocks need to be registered first due to use of BlockItems and usage in FeatureConfigurations
        WSBlocks.registerBlocks(blockRegister);

        WSFeatures.registerConfiguredFeatures(configuredFeatureRegister);
        configuredFeatureRegister.register(eventBus);
        registers.add(configuredFeatureRegister);

        WSBlocks.registerBlocksSecondary(blockRegister);
        blockRegister.register(eventBus);
        registers.add(blockRegister);

        WSItems.registerItems(itemRegister);
        itemRegister.register(eventBus);
        registers.add(itemRegister);

        WSEntities.registerEntities(entityTypeRegister);
        entityTypeRegister.register(eventBus);
        registers.add(entityTypeRegister);

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
