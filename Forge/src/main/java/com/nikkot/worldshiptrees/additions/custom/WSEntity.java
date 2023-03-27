package com.nikkot.worldshiptrees.additions.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.AgeableMob;
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

    private static final EntityDataAccessor<Boolean> DATA_BABY_ID = SynchedEntityData.defineId(WSEntity.class, EntityDataSerializers.BOOLEAN);
    public static final int BABY_START_AGE = -24000;
    private static final int FORCED_AGE_PARTICLE_TICKS = 40;
    protected int age;
    protected int forcedAge;
    protected int forcedAgeTimer;

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


    @NotNull
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 8.0d).add(Attributes.MOVEMENT_SPEED, 0.25d).add(Attributes.ATTACK_DAMAGE, 1.0d);
    }

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
                    BlockPos blockpos = (new BlockPos(this.mob.getX(), this.mob.getY() + 0.5d, this.mob.getZ())).relative(this.selectedDirection);
                    BlockState blockstate = this.mob.level.getBlockState(blockpos);
                    if (WSInfestation.isCompatibleHostBlock(blockstate.getBlock(), this.mob.getType())) {
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
                if (WSInfestation.isCompatibleHostBlock(blockstate.getBlock(), this.mob.getType())) {
                    levelaccessor.setBlock(blockpos, WSInfestation.infestedStateByHost(blockstate), 3);
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
                            if (WSInfestation.isCompatibleInfestedBlock(block, this.wsEntity.getType())) {
                                if (ForgeEventFactory.getMobGriefingEvent(level, this.wsEntity)) {
                                    level.destroyBlock(blockpos1, true, this.wsEntity);
                                } else {
                                    level.setBlock(blockpos1, WSInfestation.hostStateByInfested(level.getBlockState(blockpos1)), 3);
                                    WSEntity entity2 = (WSEntity) wsEntity.getType().create(level);
                                    if (entity2 != null) {
                                        entity2.moveTo(blockpos, 0.0f, 0.0f);
                                        level.addFreshEntity(entity2);
                                        entity2.spawnAnim();
                                    }
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

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_BABY_ID, false);
    }

    public int getAge() {
        if (this.level.isClientSide) {
            return this.entityData.get(DATA_BABY_ID) ? -1 : 1;
        } else {
            return this.age;
        }
    }

    public void ageUp(int p_146741_, boolean p_146742_) {
        int i = this.getAge();
        i += p_146741_ * 20;
        if (i > 0) {
            i = 0;
        }

        int j = i - i;
        this.setAge(i);
        if (p_146742_) {
            this.forcedAge += j;
            if (this.forcedAgeTimer == 0) {
                this.forcedAgeTimer = 40;
            }
        }

        if (this.getAge() == 0) {
            this.setAge(this.forcedAge);
        }

    }

    public void ageUp(int p_146759_) {
        this.ageUp(p_146759_, false);
    }

    public void setAge(int p_146763_) {
        int i = this.getAge();
        this.age = p_146763_;
        if (i < 0 && p_146763_ >= 0 || i >= 0 && p_146763_ < 0) {
            this.entityData.set(DATA_BABY_ID, p_146763_ < 0);
            this.ageBoundaryReached();
        }

    }

    protected void ageBoundaryReached() {
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("Age", this.getAge());
        compoundTag.putInt("ForcedAge", this.forcedAge);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.setAge(compoundTag.getInt("Age"));
        this.forcedAge = compoundTag.getInt("ForcedAge");
    }

    @Override
    public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> accessor) {
        if (DATA_BABY_ID.equals(accessor)) {
            this.refreshDimensions();
        }

        super.onSyncedDataUpdated(accessor);
    }

    @Override
    public boolean isBaby() {
        return this.getAge() < 0;
    }

    @Override
    public void setBaby(boolean p_146756_) {
        this.setAge(p_146756_ ? BABY_START_AGE : 0);
    }

    public static int getSpeedUpSecondsWhenFeeding(int p_216968_) {
        return (int)((float)(p_216968_ / 20) * 0.1F);
    }

}
