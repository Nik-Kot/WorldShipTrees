package com.nikkot.worldshiptrees.additions.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class WSEntity extends Silverfish {

    @Nullable
    public WSEntityWakeUpFriendsGoal friendsGoal;

    public WSEntity(EntityType<? extends WSEntity> entityType, Level level) {
        super(entityType, level);
    }



    @Override
    protected void registerGoals() {
        this.friendsGoal = new WSEntityWakeUpFriendsGoal(this);
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new ClimbOnTopOfPowderSnowGoal(this, this.level));
        this.goalSelector.addGoal(3, this.friendsGoal);
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new WSEntityMergeWithWoodGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public boolean hurt(@NotNull DamageSource damageSource, float amount) {
        if (this.isInvulnerableTo(damageSource)) {
            return false;
        } else {
            if ((damageSource instanceof EntityDamageSource || damageSource == DamageSource.MAGIC) && this.friendsGoal != null) {
                this.friendsGoal.notifyHurt();
            }

            return super.hurt(damageSource, amount);
        }
    }

    /*
    @NotNull
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 8.0d).add(Attributes.MOVEMENT_SPEED, 0.25d).add(Attributes.ATTACK_DAMAGE, 1.0d);
    }*/

    public static class WSEntityMergeWithWoodGoal extends RandomStrollGoal {
        @Nullable
        public Direction selectedDirection;
        public boolean doMerge;

        public WSEntityMergeWithWoodGoal(WSEntity entity) {
            super(entity, 1.0d, 10);
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            if (this.mob.getTarget() != null) {
                return false;
            } else if (!this.mob.getNavigation().isDone()) {
                return false;
            } else {
                RandomSource randomsource = this.mob.getRandom();
                if (ForgeEventFactory.getMobGriefingEvent(this.mob.level, this.mob) && randomsource.nextInt(reducedTickDelay(10)) == 0) {
                    this.selectedDirection = Direction.getRandom(randomsource);
                    BlockPos blockpos = (new BlockPos(this.mob.getX(), this.mob.getY() + 0.5D, this.mob.getZ())).relative(this.selectedDirection);
                    BlockState blockstate = this.mob.level.getBlockState(blockpos);
                    if (WSInfestedBlock.isCompatibleHostBlock(blockstate)) {
                        this.doMerge = true;
                        return true;
                    }
                }

                this.doMerge = false;
                return super.canUse();
            }
        }

        @Override
        public boolean canContinueToUse() {
            return !this.doMerge && super.canContinueToUse();
        }

        @Override
        public void start() {
            if (!this.doMerge || selectedDirection == null) {
                super.start();
            } else {
                LevelAccessor levelaccessor = this.mob.level;
                BlockPos blockpos = (new BlockPos(this.mob.getX(), this.mob.getY() + 0.5d, this.mob.getZ())).relative(this.selectedDirection);
                BlockState blockstate = levelaccessor.getBlockState(blockpos);
                if (WSInfestedBlock.isCompatibleHostBlock(blockstate)) {
                    levelaccessor.setBlock(blockpos, WSInfestedBlock.infestedStateByHost(blockstate), 3);
                    this.mob.spawnAnim();
                    this.mob.discard();
                }

            }
        }
    }

    public static class WSEntityWakeUpFriendsGoal extends Goal {
        public final WSEntity wsEntity;
        public int lookForFriends;

        public WSEntityWakeUpFriendsGoal(WSEntity entity) {
            this.wsEntity = entity;
        }

        public void notifyHurt() {
            if (this.lookForFriends == 0) {
                this.lookForFriends = this.adjustedTickDelay(20);
            }

        }

        @Override
        public boolean canUse() {
            return this.lookForFriends > 0;
        }

        @Override
        public void tick() {
            --this.lookForFriends;
            if (this.lookForFriends <= 0) {
                Level level = this.wsEntity.level;
                RandomSource randomsource = this.wsEntity.getRandom();
                BlockPos blockpos = this.wsEntity.blockPosition();

                for (int i = 0; i <= 5 && i >= -5; i = (i <= 0 ? 1 : 0) - i) {
                    for (int j = 0; j <= 10 && j >= -10; j = (j <= 0 ? 1 : 0) - j) {
                        for (int k = 0; k <= 10 && k >= -10; k = (k <= 0 ? 1 : 0) - k) {
                            BlockPos blockpos1 = blockpos.offset(j, i, k);
                            BlockState blockstate = level.getBlockState(blockpos1);
                            Block block = blockstate.getBlock();
                            if (block instanceof WSInfestedBlock) {
                                if (ForgeEventFactory.getMobGriefingEvent(level, this.wsEntity)) {
                                    level.destroyBlock(blockpos1, true, this.wsEntity);
                                } else {
                                    level.setBlock(blockpos1, ((WSInfestedBlock) block).hostStateByInfested(level.getBlockState(blockpos1)), 3);
                                }

                                if (randomsource.nextBoolean()) {
                                    return;
                                }
                            }
                        }
                    }
                }
            }

        }
    }




}
