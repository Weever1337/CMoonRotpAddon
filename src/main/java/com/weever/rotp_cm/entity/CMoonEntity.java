package com.weever.rotp_cm.entity;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.entity.stand.StandEntityType;
import com.github.standobyte.jojo.init.ModParticles;
import com.github.standobyte.jojo.init.ModStatusEffects;
import com.github.standobyte.jojo.init.power.stand.ModStandsInit;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.mc.MCUtil;
import com.weever.rotp_cm.init.InitEffects;
import com.weever.rotp_cm.init.InitSounds;
import com.weever.rotp_cm.init.InitStands;

import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class CMoonEntity extends StandEntity {
    private static final DataParameter<Boolean> ATTACK_HAS_TARGET = EntityDataManager.defineId(CMoonEntity.class, DataSerializers.BOOLEAN);
    public CMoonEntity(StandEntityType<CMoonEntity> type, World world) {
        super(type, world);
    } // Tysm Purple Haze Addon! :D

    private LivingEntity autoAttackTarget = null;
    boolean barrier;

    public void retractWhenOver() {
        if (!this.isFollowingUser()) {
            //this.setManualControl(false, false);
            entityData.set(ATTACK_HAS_TARGET, false);
            this.retractStand(false);
        }
    }

    float attackBarrageTime = 0;
    
    public void setAutoAttackTarget(LivingEntity entity) {
        this.autoAttackTarget = entity;
        if (entity == null) {
            if (entityData.get(ATTACK_HAS_TARGET)) {
                this.retractStand(false);
            }
            entityData.set(ATTACK_HAS_TARGET, false);
        }
    }

    @Override
    protected boolean setTask(StandEntityTask task) {
        if (!level.isClientSide() && (task == null || task.getAction() == InitStands.CMOON_AUTO_ATTACK.get())) {
            setAutoAttackTarget(null);
        }
        return super.setTask(task);
    }
    
    public void setBarrOrNot(boolean set) { this.barrier = set; }
    
    public boolean isBarr() { return barrier; }

    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(ATTACK_HAS_TARGET, false);
    }

    @Override
    public void onSyncedDataUpdated(DataParameter<?> dataParameter) {
        super.onSyncedDataUpdated(dataParameter);
    }

    @Override
    public boolean isFollowingUser() {
        return !entityData.get(ATTACK_HAS_TARGET) && super.isFollowingUser();
    }

    private void moveToTarget(LivingEntity target) {
        entityData.set(ATTACK_HAS_TARGET, true);
        setStandFlag(StandFlag.BEING_RETRACTED, false);
        Vector3d standPos = this.position();
        Vector3d targetPos = target.position();
        Vector3d vecToTarget = targetPos.subtract(standPos);
        double distanceSqr = vecToTarget.lengthSqr();
        double minDistance = target.getBbWidth() + this.getBbWidth() + 1;
        if (distanceSqr < minDistance * minDistance) {
            targetPos = targetPos.subtract(vecToTarget.normalize().scale(minDistance));
            vecToTarget = targetPos.subtract(standPos);
        }
        this.setDeltaMovement(vecToTarget.x / 10, vecToTarget.y / 16, vecToTarget.z / 10);
        this.lookAt(EntityAnchorArgument.Type.EYES, target.getEyePosition(1));
    }

    @SuppressWarnings("deprecation")
    @Override
    public void tick() {
        super.tick();
        LivingEntity user = this.getUser();
        
        if (autoAttackTarget != null && autoAttackTarget.removed) {
            autoAttackTarget = null;
            this.stopTask();
            this.retractWhenOver();
        }

        if (autoAttackTarget != null) {
            if (autoAttackTarget.getHealth() > 0) {
                ActionTarget actionTarget = new ActionTarget(autoAttackTarget);
                StandEntityAction punch = InitStands.CMOON_PUNCH.get();
                StandEntityAction barrage = InitStands.CMOON_ATTACK_BARRAGE.get();
                StandEntityAction inversionPunch = InitStands.CMOON_INVERSION_PUNCH.get();
                this.moveToTarget(autoAttackTarget);

                if (this.getStaminaCondition() > 0.25) {
                    if (this.getFinisherMeter() > 0.3 && !this.isBeingRetracted() && !autoAttackTarget.isDeadOrDying()) {
                        if (this.getCurrentTaskAction() != barrage) {
                            this.setTask(barrage, 60, StandEntityAction.Phase.PERFORM, actionTarget);
                        }
                        if (this.getFinisherMeter() > 0.7 && (autoAttackTarget.getHealth() / autoAttackTarget.getMaxHealth()) < 0.15) {
                            this.stopTask();
                            this.setTask(inversionPunch, inversionPunch.getStandActionTicks(this.getUserPower(), this), StandEntityAction.Phase.WINDUP, actionTarget);
                            attackBarrageTime = 0;
                        }
                    }
                    else {
                        this.setTask(punch, 10, StandEntityAction.Phase.PERFORM, actionTarget);
                    }
                }
                
                setTaskTarget(actionTarget);
            }
            else if (autoAttackTarget.isDeadOrDying() && autoAttackTarget.getMaxHealth() >= 20) {
                this.getUser().addEffect(new EffectInstance(ModStatusEffects.STAMINA_REGEN.get(), 100, 1));
            }
        } else if (this.isBarr()) {
            PlayerEntity player = (PlayerEntity) this.getUser();
            IStandPower power = this.getUserPower();
            if (this.getCurrentTaskAction() == ModStandsInit.UNSUMMON_STAND_ENTITY.get() && user instanceof PlayerEntity){
                this.setBarrOrNot(false);
                return;
            }
            if (power.getStamina() < 10) {
                level.playSound(null, this.blockPosition(), InitSounds.CMOON_UNSUMMON_SOUND.get(), SoundCategory.PLAYERS,1,1);
                power.toggleSummon();
                return;
            }
            for (Entity entity : MCUtil.entitiesAround(
                    Entity.class, player, 5, false,
                    entity -> (!(entity instanceof StandEntity)))
            ) {
                if (entity instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    if (!livingEntity.hasEffect(Effects.LEVITATION)) {
                        livingEntity.addEffect(new EffectInstance(Effects.LEVITATION, 100, 2, false, false, true));
                        livingEntity.addEffect(new EffectInstance(InitEffects.CM_PARALYSIS.get(), 100, 1, false, false, true));
                        power.consumeStamina(100);
                    }
                } else if (
                		entity instanceof ItemEntity 
                		|| entity instanceof ExperienceOrbEntity 
                		|| entity instanceof FallingBlockEntity
                ) {
                	continue;
                } else {
                    double x = entity.getX() ;
                    double y = entity.getY();
                    double z = entity.getZ();
                    //entity.level.addParticle(ParticleTypes.CRIT, x, y, z, 0, 0, 0);
                    entity.level.addParticle(ModParticles.CD_RESTORATION.get(), x, y, z, 0, 0, 0);
                    entity.remove();
                    power.consumeStamina(25);
                }
            }
        }
    }

    @Override
    public ActionTarget aimWithThisOrUser(double reachDistance, ActionTarget currentTarget) {
        ActionTarget target;
        if (currentTarget.getType() == ActionTarget.TargetType.ENTITY && isTargetInReach(currentTarget)) {
            target = currentTarget;
        }
        else {
            RayTraceResult aim = null;
            if (!isManuallyControlled()) {
                LivingEntity user = getUser();
                if (user != null) {
                    aim = precisionRayTrace(user, reachDistance);
                }
            }
            if (aim == null || autoAttackTarget != null) {
                aim = precisionRayTrace(this, reachDistance);
            }

            target = ActionTarget.fromRayTraceResult(aim);
        }

        if (target.getEntity() != this) {
            Vector3d targetPos = target.getTargetPos(true);
            if (targetPos != null) {
                MCUtil.rotateTowards(this, targetPos, (float) getAttackSpeed() / 16F * 18F);
            }
        }

        return target;
    }

    @Override
    public void defaultRotation() {
        if (entityData.get(ATTACK_HAS_TARGET)) {
            setYHeadRot(this.yRot);
        }
        else {
            super.defaultRotation();
        }
    }

    public boolean checkTargets(Entity entity){
        return entity != this.getUser() && !entity.isAlliedTo(this.getUser());
    }
}
