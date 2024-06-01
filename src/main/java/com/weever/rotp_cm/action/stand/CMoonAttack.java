package com.weever.rotp_cm.action.stand;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.weever.rotp_cm.entity.CMoonEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class CMoonAttack extends StandEntityAction {
    public CMoonAttack(StandEntityAction.Builder builder){
        super(builder);
    }

    @Override
    protected ActionConditionResult checkStandConditions(StandEntity stand, IStandPower power, ActionTarget target) {
        return ActionConditionResult.POSITIVE;
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            CMoonEntity cMoon = (CMoonEntity) standEntity;
            Entity cameraEntity = standEntity.isManuallyControlled() ? standEntity : userPower.getUser();
            Vector3d eyePos = cameraEntity.getEyePosition(1);
            AxisAlignedBB targetsBox = new AxisAlignedBB(eyePos, eyePos).inflate(32, 32, 32);
            List<Entity> potentialTargets = world.getEntities(cMoon, targetsBox,
                    entity -> (entity instanceof LivingEntity && cMoon.checkTargets(entity)));
            Vector3d lookVec = cameraEntity.getLookAngle();
            Optional<Entity> entityLookedAt = potentialTargets.stream().max(Comparator.comparingDouble(
                    entity -> {
                        Vector3d targetPos = entity.getBoundingBox().getCenter();
                        Vector3d vecToTarget = targetPos.subtract(eyePos).normalize();
                        double angleCos = lookVec.dot(vecToTarget);
                        return angleCos;
                    }));
            entityLookedAt.ifPresent(target -> cMoon.setAutoAttackTarget((LivingEntity) target));
        }
    }
}