package com.nikkot.worldshiptrees.additions;

import com.nikkot.worldshiptrees.treegen2.*;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSizeType;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacerType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class WSTreeGenTypes {
    public static final RegistryObject<TrunkPlacerType<?>> WS_TRUNK_PLACER = WSRegisters.trunkPlacerRegister.register("worldship_trunk_placer", () -> new TrunkPlacerType<>(WSTrunkPlacer.CODEC));

    public static final RegistryObject<TreeDecoratorType<?>> WS_TREE_DECORATOR = WSRegisters.treeDecoratorRegister.register("worldship_tree_decorator", () -> new TreeDecoratorType<>(WSTreeDecorator.CODEC));

    public static final RegistryObject<RootPlacerType<?>> WS_ROOT_PLACER = WSRegisters.rootPlacerRegister.register("worldship_root_placer", () -> new RootPlacerType<>(WSRootPlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<?>> WS_FOLIAGE_PLACER = WSRegisters.foliagePlacerRegister.register("worldship_foliage_placer", () -> new FoliagePlacerType<>(WSFoliagePlacer.CODEC));

    public static final RegistryObject<FeatureSizeType<?>> WS_FEATURE_SIZE = WSRegisters.featureSizeRegister.register("worldship_feature_size", () -> new FeatureSizeType<>(WSFeatureSize.CODEC));


    public static final List<RegistryObject<TrunkPlacerType<?>>> trunk_placers = new ArrayList<>();
    public static final List<RegistryObject<TreeDecoratorType<?>>> tree_decorators = new ArrayList<>();
    public static final List<RegistryObject<RootPlacerType<?>>> root_placers = new ArrayList<>();
    public static final List<RegistryObject<FoliagePlacerType<?>>> foliage_placers = new ArrayList<>();
    public static final List<RegistryObject<FeatureSizeType<?>>> feature_sizes = new ArrayList<>();

    public static List<RegistryObject<TrunkPlacerType<?>>> registerTrunkTypes (DeferredRegister<TrunkPlacerType<?>> trunkPlacerRegister) {
        trunk_placers.add(WS_TRUNK_PLACER);
        return trunk_placers;
    }

    public static List<RegistryObject<TreeDecoratorType<?>>> registerTreeDecorators (DeferredRegister<TreeDecoratorType<?>> treeDecoratorRegister) {
        tree_decorators.add(WS_TREE_DECORATOR);
        return tree_decorators;
    }

    public static List<RegistryObject<RootPlacerType<?>>> registerRootTypes (DeferredRegister<RootPlacerType<?>> rootPlacerRegister) {
        root_placers.add(WS_ROOT_PLACER);
        return root_placers;
    }

    public static List<RegistryObject<FoliagePlacerType<?>>> registerFoliageTypes (DeferredRegister<FoliagePlacerType<?>> foliagePlacerRegister) {
        foliage_placers.add(WS_FOLIAGE_PLACER);
        return foliage_placers;
    }
    public static List<RegistryObject<FeatureSizeType<?>>> registerFeatureSizes (DeferredRegister<FeatureSizeType<?>> featureSizeRegister) {
        feature_sizes.add(WS_FEATURE_SIZE);
        return feature_sizes;
    }





}
