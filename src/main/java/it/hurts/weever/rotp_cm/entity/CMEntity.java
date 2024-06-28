package it.hurts.weever.rotp_cm.entity;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.client.particle.custom.CustomParticlesHelper;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.entity.stand.StandEntityType;

import com.github.standobyte.jojo.init.ModStatusEffects;
import com.github.standobyte.jojo.init.power.stand.ModStandsInit;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.power.impl.stand.StandUtil;
import com.github.standobyte.jojo.util.general.GeneralUtil;
import com.github.standobyte.jojo.util.mc.MCUtil;
import com.github.standobyte.jojo.util.mod.JojoModUtil;
import it.hurts.weever.rotp_cm.init.InitEffects;
import it.hurts.weever.rotp_cm.init.InitParticles;
import it.hurts.weever.rotp_cm.init.InitSounds;
import it.hurts.weever.rotp_cm.init.InitStands;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class CMEntity extends StandEntity {
    private static final DataParameter<Boolean> ATTACK_HAS_TARGET = EntityDataManager.defineId(CMEntity.class, DataSerializers.BOOLEAN);
    public CMEntity(StandEntityType<CMEntity> type, World world) {
        super(type, world);
    }
    static boolean Buffs = false;
    private LivingEntity autoAttackTarget = null;
    public static boolean isBuff() { return Buffs; }
    public boolean isAtt() { return autoAttackTarget != null; }
    public static void setBuffOrNot(boolean set) { Buffs = set; }

    @SuppressWarnings("deprecation")
    @Override
    public void tick() {
        super.tick();
        handleAutoAttackTarget();
        handleBuffs();
    }

    private void handleAutoAttackTarget() {
        LivingEntity user = this.getUser();
        if (autoAttackTarget == null) {
            return;
        }

        if (autoAttackTarget.removed) {
            clearAutoAttackTarget();
        } else if (isManuallyControlled()) {
            autoAttackTarget = null;
            this.retractWhenOver();
            StandUtil.setManualControl((PlayerEntity) user, false, false);
        } else if (autoAttackTarget.getHealth() > 0) {
            performAttack();
        } else if (autoAttackTarget.isDeadOrDying() && autoAttackTarget.getMaxHealth() >= 20) {
            user.addEffect(new EffectInstance(ModStatusEffects.STAMINA_REGEN.get(), 100, 4, false, false, false));
        }
    }

    private void performAttack() {
        ActionTarget actionTarget = new ActionTarget(autoAttackTarget);
        StandEntityAction punch = InitStands.CMOON_PUNCH.get();
        StandEntityAction barrage = InitStands.CMOON_DESTROY_BARRAGE.get();
        moveToTarget(autoAttackTarget);

        if (this.getStaminaCondition() > 0.25) {
            if (this.getFinisherMeter() > 0.3 && !this.isBeingRetracted() && !autoAttackTarget.isDeadOrDying()) {
                if (this.getCurrentTaskAction() != barrage) {
                    this.setTask(barrage, 60, StandEntityAction.Phase.PERFORM, actionTarget);
                }
            } else {
                this.setTask(punch, 10, StandEntityAction.Phase.PERFORM, actionTarget);
            }
        }

        setTaskTarget(actionTarget);
    }

    private void clearAutoAttackTarget() {
        autoAttackTarget = null;
        this.stopTask();
        this.retractWhenOver();
    }

    private void handleBuffs() {
        if (!isBuff()) {
            return;
        }
        LivingEntity user = this.getUser();
        IStandPower power = this.getUserPower();

        if (user == null || power == null) return;

        if (this.getCurrentTaskAction() == ModStandsInit.UNSUMMON_STAND_ENTITY.get() && user instanceof PlayerEntity){
            setBuffOrNot(false);
            return;
        }
        if (power.getStamina() < 10) {
            level.playSound(null, this.blockPosition(), InitSounds.CM_UNSUMMON.get(), SoundCategory.PLAYERS, 1, 1);
            power.toggleSummon();
            return;
        }

        float energyRatio = power.getStamina() / power.getMaxStamina();
        IParticleData particleType = InitParticles.HAMON_AURA_PURPLE.get();

        user.addEffect(new EffectInstance(InitEffects.CM_SHIFT.get(), 100, 2, false, false, true));
        user.addEffect(new EffectInstance(Effects.JUMP, 100, 1, false, false, false)); // i cant change jump height
        if (user.level.isClientSide()) {
            GeneralUtil.doFractionTimes(() -> {
                CustomParticlesHelper.createHamonAuraParticle(particleType, user,
                        user.getX() + (random.nextDouble() - 0.5) * (user.getBbWidth() + 0.5F),
                        user.getY() + random.nextDouble() * (user.getBbHeight() * 0.5F),
                        user.getZ() + (random.nextDouble() - 0.5) * (user.getBbWidth() + 0.5F));
            }, energyRatio);
        }

        for (Entity entity : MCUtil.entitiesAround(
                Entity.class, user, 3, false,
                entity -> (!(entity instanceof ItemEntity)
                        && !(entity instanceof ExperienceOrbEntity)
                        && !(entity instanceof FallingBlockEntity)
                        && !(entity instanceof LivingEntity))
        )) {
            if ((entity instanceof ProjectileEntity)) {
                ProjectileEntity projectile = (ProjectileEntity) entity;
                if (projectile.getOwner() == user) return;
                if (projectile.fallDistance > 0) return;
                JojoModUtil.deflectProjectile(projectile, projectile.getDeltaMovement().reverse());
                level.addParticle(InitParticles.HAMON_SPARK_PURPLE.get(), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
                power.consumeStamina(30);
            }
        }
    }

    public void retractWhenOver() {
        if (!this.isFollowingUser()) {
            entityData.set(ATTACK_HAS_TARGET, false);
            this.retractStand(false);
        } if (this.getCurrentTaskAction() == InitStands.CMOON_DESTROY_BARRAGE.get()) {
            this.stopTask();
        }
    }

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
        if (!level.isClientSide() && (task == null || task.getAction() == InitStands.CMOON_DESTROY.get())) {
            setAutoAttackTarget(null);
        }
        return super.setTask(task);
    }

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
}
