package com.nikkot.worldshiptrees.events;

import com.nikkot.worldshiptrees.WorldShipTrees;
import com.nikkot.worldshiptrees.additions.custom.WSHollowPillarBlock;
import com.nikkot.worldshiptrees.triggers.WSTrigger;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WorldShipTrees.MODID)
public class WSEvents {
    public static boolean enableAutoCrawl = true;


    //public static WSTrigger crawlTrigger;
    private static final String TAG_TRYING_TO_CRAWL = WorldShipTrees.MODID + ":trying_crawl";
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        //WorldShipTrees.LOGGER.error("q1");
        if(enableAutoCrawl && event.phase == TickEvent.Phase.START) {
            Player player = event.player;
            BlockPos playerPos = player.blockPosition();
            boolean isTrying = player.isCrouching() && !player.isSwimming() &&
                    player.level.getBlockState(playerPos).getCollisionShape(player.level, playerPos).isEmpty();
            boolean wasTrying = player.getPersistentData().getBoolean(TAG_TRYING_TO_CRAWL);

            if(isTrying && !wasTrying) {
                Direction dir = player.getDirection();
                BlockPos pos = playerPos.relative(dir);

                if(!tryClimb(player, dir, pos))
                    tryClimb(player, dir, pos.above());
            }

            if(isTrying != wasTrying)
                player.getPersistentData().putBoolean(TAG_TRYING_TO_CRAWL, isTrying);
        }
    }
    private static boolean tryClimb(Player player, Direction dir, BlockPos pos) {
        //WorldShipTrees.LOGGER.error("q2");
        BlockState state = player.level.getBlockState(pos);
        Block block = state.getBlock();

        if(block instanceof WSHollowPillarBlock) {
            Direction.Axis axis = state.getValue(WSHollowPillarBlock.AXIS);
            if(axis != Direction.Axis.Y && axis == dir.getAxis()) {
                player.setPose(Pose.SWIMMING);
                player.setSwimming(true);

                double x = pos.getX() + 0.5 - (dir.getStepX() * 0.4);
                double y = pos.getY() + 0.13;
                double z = pos.getZ() + 0.5 - (dir.getStepZ() * 0.4);

                player.setPos(x, y, z);

                /*
                if(player instanceof ServerPlayer sp) {
                    crawlTrigger.trigger(sp);
                }*/

                return true;
            }
        }

        return false;
    }
}
