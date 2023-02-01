package com.nikkot.worldshiptrees.additions;

import com.nikkot.worldshiptrees.additions.custom.WSFluidType;
import com.nikkot.worldshiptrees.WorldShipTrees;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class WSFluids {

    public static final ResourceLocation RL_WATER_STILL = new ResourceLocation(
            "block/water_still");
    public static final ResourceLocation RL_WATER_FLOWING = new ResourceLocation(
            "block/water_flow");
    public static final ResourceLocation RL_WATER_OVERLAY = new ResourceLocation(
            "block/water_overlay");
    public static final ResourceLocation RL_UNDERWATER = new ResourceLocation(
            WorldShipTrees.MODID,"textures/misc/underfluid_tree_sap.png");


    public static final RegistryObject<WSFluidType> FLUID_TYPE_TREE_SAP = WSRegisters
            .fluidTypeRegister.register("tree_sap_type", () ->
                    new WSFluidType (
                            FluidType.Properties.create()
                                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                                    .canSwim(true)
                                    .canDrown(true)
                                    .canExtinguish(true)
                                    .canConvertToSource(false)
                                    .canHydrate(true)
                                    .canPushEntity(true)
                                    .fallDistanceModifier(0)
                                    .lightLevel(0)
                                    .rarity(Rarity.COMMON)
                                    .temperature(300)
                                    .viscosity(1000)
                                    .density(1000)
                                    .motionScale(0.014d)
                                    .supportsBoating(true)
                                    .pathType(BlockPathTypes.WATER)
                                    .adjacentPathType(BlockPathTypes.WATER_BORDER))
                            .stillTexture(RL_WATER_STILL)
                            .flowingTexture(RL_WATER_FLOWING)
                            .overlayTexture(RL_WATER_OVERLAY)
                            .renderOverlayTexture(RL_UNDERWATER)
                            .tintColor(WSColors.tree_sap)
                            .fogColor(WSColors.podzol)
                            .fogStart(1f)
                            .fogEnd(4f));

    public static final RegistryObject<ForgeFlowingFluid.Source> FLUID_TREE_SAP_SOURCE = WSRegisters
            .fluidRegister.register("tree_sap_source", () ->
                    new ForgeFlowingFluid.Source (
                            WSFluids.FLUID_TREE_SAP_PROPERTIES));
    public static final RegistryObject<ForgeFlowingFluid.Flowing> FLUID_TREE_SAP_FLOWING = WSRegisters
            .fluidRegister.register("tree_sap_flowing", () ->
                    new ForgeFlowingFluid.Flowing (
                            WSFluids.FLUID_TREE_SAP_PROPERTIES));

    public static final ForgeFlowingFluid.Properties FLUID_TREE_SAP_PROPERTIES = new ForgeFlowingFluid
            .Properties(FLUID_TYPE_TREE_SAP, FLUID_TREE_SAP_SOURCE, FLUID_TREE_SAP_FLOWING)
            .block(WSBlocks.BLOCK_LIQUID_TREE_SAP)
            .bucket(WSItems.ITEM_BUCKET_TREE_SAP)
            .levelDecreasePerBlock(2)
            .slopeFindDistance(2)
            .explosionResistance(100.0f)
            .tickRate(1);

    public static final List<RegistryObject<? extends FluidType>> fluid_types = new ArrayList<>();
    public static final List<RegistryObject<? extends Fluid>> fluids = new ArrayList<>();

    public static List<RegistryObject<? extends FluidType>> registerFluidTypes (DeferredRegister<FluidType> fluidTypeRegister) {
        fluid_types.add(FLUID_TYPE_TREE_SAP);

        return fluid_types;
    }

    public static List<RegistryObject<? extends Fluid>> registerFluids (DeferredRegister<Fluid> fluidRegister) {
        fluids.add(FLUID_TREE_SAP_SOURCE);
        fluids.add(FLUID_TREE_SAP_FLOWING);

        return fluids;
    }







}
