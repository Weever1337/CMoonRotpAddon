package com.weever.rotp_cm.entity;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.entity.stand.StandEntityType;
import com.github.standobyte.jojo.init.ModStatusEffects;
import com.github.standobyte.jojo.init.power.stand.ModStandsInit;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.power.impl.stand.StandUtil;
import com.github.standobyte.jojo.util.mc.MCUtil;
import com.weever.rotp_cm.init.InitEffects;
import com.weever.rotp_cm.init.InitSounds;
import com.weever.rotp_cm.init.InitStands;

import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class CMoonEntity extends StandEntity {
    private static final DataParameter<Boolean> ATTACK_HAS_TARGET = EntityDataManager.defineId(CMoonEntity.class, DataSerializers.BOOLEAN);
    public CMoonEntity(StandEntityType<CMoonEntity> type, World world) {
        super(type, world);
    } // Tysm Purple Haze Addon! :D

    boolean attack;
    boolean attackfromability;
    boolean barrier;
    int range = 10;

    public void setAttOrNot(boolean set) {
        this.attack = set;
    }
    public void setBarrOrNot(boolean set) { this.barrier = set; }
    public void setAttOrNotWithAbility (boolean set) {
        this.attackfromability = set;
        this.attack = set;
    }

    public void retractWhenOver(){
        if (!this.isFollowingUser()) {
            //this.setManualControl(false, false);
            entityData.set(ATTACK_HAS_TARGET, false);
            this.retractStand(false);
        }
    }

    public boolean isAtt() {
        return attack;
    }
    public boolean isBarr() { return barrier; }
    public boolean isAttCauseOfAbility() {
        return attackfromability && attack;
    }
    float attackBarrageTime = 0;

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

    @Override
    public void tick() {
        super.tick();
        LivingEntity user = this.getUser();
        if (user.isAlive() && this.isAttCauseOfAbility()){
            this.setAttOrNotWithAbility(true);
        }
        else if ((user.isAlive() && this.getUserPower().getResolveLevel() == 0)
                || (user.isAlive() && user.getHealth() <= 0.5 * user.getMaxHealth () && this.getUserPower().getResolveLevel() == 1)
                || (user.isAlive() && user.getHealth() <= 0.25 * user.getMaxHealth () && this.getUserPower().getResolveLevel() == 2)
                || (user.isAlive() && user.getHealth() <= 0.1 * user.getMaxHealth () && this.getUserPower().getResolveLevel() >= 3)) {
            this.setAttOrNot(true);
        }
        else {
            this.setAttOrNotWithAbility(false);
        }
        if (this.isAtt() && this.isBarr()) {
            PlayerEntity player = (PlayerEntity) this.getUser ();
            this.setBarrOrNot(false);
            this.setAttOrNot(false);
            player.displayClientMessage(new TranslationTextComponent("jojo.message.action_condition.cant_choose_abilities"), true);
            IStandPower userPower = this.getUserPower();
            userPower.toggleSummon();
        }
        if (this.isAtt()) {
            if (this.getCurrentTaskAction() == ModStandsInit.UNSUMMON_STAND_ENTITY.get() && user instanceof PlayerEntity){
                this.setAttOrNot(false);
            }
            LivingEntity livingTarget = null;

            Entity curTarget = getCurrentTask().map(StandEntityTask::getTarget).orElse(ActionTarget.EMPTY).getEntity();
            if (curTarget instanceof LivingEntity && curTarget.isAlive() && curTarget.distanceToSqr(user) < range * range) {
                livingTarget = (LivingEntity) curTarget;
            }
            else {
                List<Entity> entitiesAround = this.level.getEntities(this, user.getBoundingBox().inflate(range),
                        entity -> (entity instanceof LivingEntity && this.checkTargets(entity)));
                if (!entitiesAround.isEmpty()) {
                    Entity closestEntity = entitiesAround.stream()
                            .min(Comparator.comparingDouble(
                                    target -> target.distanceToSqr(this)))
                            .get();
                    livingTarget = (LivingEntity) closestEntity;
                }
            }

            if (livingTarget != null) {
                if (livingTarget.getHealth() > 0) {
                    ActionTarget actionTarget = new ActionTarget(livingTarget);
                    StandEntityAction punch = InitStands.CMOON_PUNCH.get();
                    StandEntityAction barrage = InitStands.CMOON_ATTACK_BARRAGE.get();
                    StandEntityAction inversionPunch = InitStands.CMOON_INVERSION_PUNCH.get();
                    this.moveToTarget(livingTarget);

                    if (this.getStaminaCondition() > 0.25) {
                        if (this.getFinisherMeter() > 0.3 && !this.isBeingRetracted() && !livingTarget.isDeadOrDying ()) {
                            if (this.getCurrentTaskAction() != barrage) {
                                this.setTask(barrage, 60, StandEntityAction.Phase.PERFORM, actionTarget);
                            }
                            if (this.getFinisherMeter() > 0.7 && (livingTarget.getHealth() / livingTarget.getMaxHealth())<  0.15){
                                this.stopTask();
                                this.setTask(inversionPunch, inversionPunch.getStandActionTicks(this.getUserPower(), this), StandEntityAction.Phase.WINDUP, actionTarget);
                                attackBarrageTime = 0;
                            }
                        }
                        else {
                            this.setTask(punch, 10, StandEntityAction.Phase.PERFORM, actionTarget);
                        }
                    }
                }
                else if (livingTarget.isDeadOrDying () && livingTarget.getMaxHealth () >= 20){
                    this.getUser ().addEffect (new EffectInstance (ModStatusEffects.STAMINA_REGEN.get (), 100, 1));
                }
            }
            else {
                if (this.getCurrentTaskAction () != InitStands.CMOON_ATTACK_BARRAGE.get()){
                    this.stopTask ();
                    this.retractWhenOver();
                }
            }

            if (isManuallyControlled()) {
                if (user instanceof PlayerEntity) {
                    PlayerEntity player = (PlayerEntity) getUser();
                    StandUtil.setManualControl(player, false, false);
                    player.displayClientMessage(new TranslationTextComponent("jojo.message.action_condition.cant_control_stand"), true);
                }
            }
        }
        if (this.isBarr()) {
            PlayerEntity player = (PlayerEntity) this.getUser ();
            IStandPower power = this.getUserPower();
            if (this.getCurrentTaskAction() == ModStandsInit.UNSUMMON_STAND_ENTITY.get() && user instanceof PlayerEntity){
                this.setBarrOrNot(false);
                return;
            }
            for (LivingEntity entity : MCUtil.entitiesAround(
                    LivingEntity.class, player, 10, false,
                    entity -> (!(entity instanceof StandEntity) || !player.is(Objects.requireNonNull(((StandEntity) entity).getUser()))))
            ) {
                if (power.getStamina() < 5) {
                    level.playSound(null, this.blockPosition(), InitSounds.CMOON_UNSUMMON_SOUND.get(), SoundCategory.PLAYERS,1,1);
                    power.toggleSummon();
                    break;
                } else if (!entity.hasEffect(Effects.LEVITATION)) {
                    entity.addEffect(new EffectInstance(Effects.LEVITATION, 100, 2));
                    power.consumeStamina(200);
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
            if (aim == null || this.isAtt()) {
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

    private boolean checkTargets(Entity entity){
        return entity != this.getUser() && !entity.isAlliedTo(this.getUser());
    }
}
