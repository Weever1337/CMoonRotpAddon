package com.weever.rotp_cm.action.stand;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.mc.MCUtil;
import com.weever.rotp_cm.entity.CMoonEntity;
import com.weever.rotp_cm.init.InitEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import com.github.standobyte.jojo.action.ActionTarget;

import java.util.Objects;

public class CMoonGravitationalChanges extends StandEntityAction {
    public CMoonGravitationalChanges(StandEntityAction.Builder builder){
        super(builder);
    }

    @Override
    protected ActionConditionResult checkStandConditions(StandEntity stand, IStandPower power, ActionTarget target) {
        CMoonEntity CMoon = (CMoonEntity) stand;
        if (CMoon.isAtt()) { return conditionMessage("cant_control_stand"); }
        if (power.getStamina() < 200) return ActionConditionResult.NEGATIVE;
        return ActionConditionResult.POSITIVE;
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
        	int duration = 100;
            for (LivingEntity entity : MCUtil.entitiesAround(
                    LivingEntity.class, userPower.getUser(), 10, false,
                    entity -> (!(entity instanceof StandEntity) || !userPower.getUser().is(Objects.requireNonNull(((StandEntity) entity).getUser()))))) {
            	if (!entity.hasEffect(Effects.LEVITATION)) entity.addEffect(new EffectInstance(Effects.LEVITATION, duration, 2, false, false, true)); // AHH MOMENT 💀💀💀
                //if (!entity.hasEffect(Effects.SLOW_FALLING)) entity.addEffect(new EffectInstance(Effects.SLOW_FALLING, duration*2, 2, false, false, true));
                if (!entity.hasEffect(InitEffects.CM_PARALYSIS.get())) entity.addEffect(new EffectInstance(InitEffects.CM_PARALYSIS.get(), duration, 1, false, false, true));
            }
        }
    }
}