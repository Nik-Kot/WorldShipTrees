package com.nikkot.worldshiptrees.objects;

import com.nikkot.worldshiptrees.treegen2.WSTreeConfiguration;
import com.nikkot.worldshiptrees.treegen2.WSTreeFeature;
import com.nikkot.worldshiptrees.trees.WSTreeConfigurations;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class WSFeatures {

    public static final RegistryObject<ConfiguredFeature<?, ?>> CONF_FEATURE_TREE_RUBBER = WSRegisters.configuredFeatureRegister.register("rubber_tree", () -> new ConfiguredFeature<>(Feature.TREE, WSTreeConfigurations.TREE_RUBBER));

    public static final RegistryObject<WSTreeFeature> FEATURE_WORLDSHIP_TREE = WSRegisters.featureRegister.register("worldship_tree", () -> new WSTreeFeature(WSTreeConfiguration.CODEC));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CONF_FEATURE_TREE_SACRED_RUBBER = WSRegisters.configuredFeatureRegister.register("sacred_rubber_tree", () -> new ConfiguredFeature<>(FEATURE_WORLDSHIP_TREE.get(), WSTreeConfigurations.TREE_SACRED_RUBBER));

    public static final List<RegistryObject<? extends Feature<?>>> features_trees = new ArrayList<>();
    public static final List<RegistryObject<? extends ConfiguredFeature<?, ?>>> configuredFeatures = new ArrayList<>();

    public static List<RegistryObject<? extends Feature<?>>> registerFeatures(DeferredRegister<Feature<?>> featureRegister) {

        features_trees.add(FEATURE_WORLDSHIP_TREE);

        return features_trees;
    }

    public static List<RegistryObject<? extends ConfiguredFeature<?, ?>>> registerConfiguredFeatures (DeferredRegister<ConfiguredFeature<?, ?>> configuredFeatureRegister) {

        configuredFeatures.add(CONF_FEATURE_TREE_RUBBER);
        configuredFeatures.add(CONF_FEATURE_TREE_SACRED_RUBBER);

        return configuredFeatures;
    }
}
