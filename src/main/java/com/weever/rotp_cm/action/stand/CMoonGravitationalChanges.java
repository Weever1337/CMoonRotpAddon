package com.weever.rotp_cm.action.stand;

import java.util.Objects;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.entity.stand.StandPose;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.mc.MCUtil;
import com.weever.rotp_cm.init.InitEffects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class CMoonGravitationalChanges extends StandEntityAction {
    public static final StandPose POSE = new StandPose("CM_GRAVITATIONAL_CHANGES");
    public CMoonGravitationalChanges(StandEntityAction.Builder builder){
        super(builder);
    }

    @Override
    protected ActionConditionResult checkStandConditions(StandEntity stand, IStandPower power, ActionTarget target) {
        if (power.getStamina() < 200) return ActionConditionResult.NEGATIVE;
        return ActionConditionResult.POSITIVE;
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
        	int duration = 50/2;
            for (LivingEntity entity : MCUtil.entitiesAround(
                    LivingEntity.class, userPower.getUser(), 10, false,
                    entity -> (!(entity instanceof StandEntity) || !userPower.getUser().is(Objects.requireNonNull(((StandEntity) entity).getUser()))))) {
            	if (!entity.hasEffect(InitEffects.CM_GRAVITY.get())) entity.addEffect(new EffectInstance(InitEffects.CM_GRAVITY.get(), duration, 3, false, false, true));
                //if (!entity.hasEffect(Effects.SLOW_FALLING)) entity.addEffect(new EffectInstance(Effects.SLOW_FALLING, duration*4, 12, false, false, false));
                if (!entity.hasEffect(InitEffects.CM_PARALYSIS.get())) entity.addEffect(new EffectInstance(InitEffects.CM_PARALYSIS.get(), duration, 1, false, false, true));
            }
        }
    }
}