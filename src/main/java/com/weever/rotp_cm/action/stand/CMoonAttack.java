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
import com.weever.rotp_cm.network.AddonPackets;
import com.weever.rotp_cm.network.server.AddTagPacket;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class CMoonAttack extends StandEntityAction {
    public CMoonAttack(StandEntityAction.Builder builder){
        super(builder);
    }

    @Override
    public ActionConditionResult checkTarget(ActionTarget target, LivingEntity user, IStandPower power) {
        Entity targetEntity = target.getEntity();
        if (targetEntity.is(power.getUser())) {
            return conditionMessage("cant_attack_self");
        }
        return ActionConditionResult.POSITIVE;
    }

    @Override
    public TargetRequirement getTargetRequirement() {
        return TargetRequirement.ENTITY;
    }

    @Override
    public boolean standCanTickPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        return task.getTarget().getType() == ActionTarget.TargetType.ENTITY;
    }
    
    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            CMoonEntity cMoon = (CMoonEntity) standEntity;
            Entity targetEntity = task.getTarget().getEntity();
            cMoon.setAutoAttackTarget((LivingEntity) targetEntity);
        }
    }
}