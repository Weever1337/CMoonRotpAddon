package it.hurts.weever.rotp_cm.action.stand;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import it.hurts.weever.rotp_cm.entity.CMEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class Destroy extends StandEntityAction {
    public Destroy(StandEntityAction.Builder builder){
        super(builder);
    }

    @Override
    public ActionConditionResult checkTarget(ActionTarget target, LivingEntity user, IStandPower power) {
        Entity targetEntity = target.getEntity();
        if (targetEntity.is(power.getUser())) {
            return conditionMessage("cant_attack_self");
        } else if (targetEntity instanceof StandEntity) {
            return conditionMessage("cant_attack_stand");
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
            CMEntity cMoon = (CMEntity) standEntity;
            Entity targetEntity = task.getTarget().getEntity();
            cMoon.setAutoAttackTarget((LivingEntity) targetEntity);
        }
    }
}