package com.nikkot.worldshiptrees.events;

import com.nikkot.worldshiptrees.WorldShipTrees;
import com.nikkot.worldshiptrees.additions.custom.WSFluidType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WorldShipTrees.MODID, value = Dist.CLIENT)
public class WSClientEvents {

    private static volatile boolean wasInFluid = false;
    //private static volatile boolean wasInFluid2 = false;
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        boolean isInFluid = player.getEyeInFluidType() instanceof WSFluidType;

        //WorldShipTrees.LOGGER.error(Boolean.toString(isInFluid));


        if (!wasInFluid && isInFluid) {
            player.playSound(SoundEvents.AMBIENT_UNDERWATER_ENTER);
        }

        if (wasInFluid && !isInFluid) {
            player.playSound(SoundEvents.AMBIENT_UNDERWATER_EXIT);
        }

        //wasInFluid2 = wasInFluid;
        wasInFluid = isInFluid;
    }
}
