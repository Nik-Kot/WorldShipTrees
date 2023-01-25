package com.nikkot.worldshiptrees.objects;

import com.mojang.math.Vector3f;
import com.nikkot.worldshiptrees.WSFluidType;
import com.nikkot.worldshiptrees.WorldShipTrees;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class WSFluids {

    public static final ResourceLocation RL_WATER_STILL = new ResourceLocation("block/water_still");
    public static final ResourceLocation RL_WATER_FLOWING = new ResourceLocation("block/water_flow");
    public static final ResourceLocation RL_WATER_OVERLAY = new ResourceLocation("block/water_overlay");
    public static final ResourceLocation RL_UNDERWATER = new ResourceLocation(WorldShipTrees.MODID,"textures/misc/underfluid_tree_sap.png");


    public static final RegistryObject<WSFluidType> FLUID_TYPE_TREE_SAP = WSRegisters.fluidTypeRegister.register("tree_sap_type", () -> new WSFluidType(FluidType.Properties.create()).stillTexture(RL_WATER_STILL).flowingTexture(RL_WATER_FLOWING).overlayTexture(RL_WATER_OVERLAY).renderOverlayTexture(RL_UNDERWATER).tintColor(WSColors.tree_sap).fogColor(new Vector3f(0x8Fp0f / 255f, 0x77p0f / 255f, 0x48p0f / 255f)));

    public static final RegistryObject<ForgeFlowingFluid.Source> FLUID_TREE_SAP_SOURCE = WSRegisters.fluidRegister.register("tree_sap_source", () -> new ForgeFlowingFluid.Source(WSFluids.FLUID_TREE_SAP_PROPERTIES));
    public static final RegistryObject<ForgeFlowingFluid.Flowing> FLUID_TREE_SAP_FLOWING = WSRegisters.fluidRegister.register("tree_sap_flowing", () -> new ForgeFlowingFluid.Flowing(WSFluids.FLUID_TREE_SAP_PROPERTIES));

    public static final ForgeFlowingFluid.Properties FLUID_TREE_SAP_PROPERTIES = new ForgeFlowingFluid.Properties(FLUID_TYPE_TREE_SAP, FLUID_TREE_SAP_SOURCE, FLUID_TREE_SAP_FLOWING).block(WSBlocks.BLOCK_LIQUID_TREE_SAP).bucket(WSItems.ITEM_BUCKET_TREE_SAP).levelDecreasePerBlock(2).slopeFindDistance(2).explosionResistance(100.0f).tickRate(1);

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
