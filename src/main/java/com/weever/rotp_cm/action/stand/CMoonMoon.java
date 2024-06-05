package com.weever.rotp_cm.action.stand;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.entity.stand.StandPose;
import com.github.standobyte.jojo.entity.stand.StandRelativeOffset;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.weever.rotp_cm.init.InitEffects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class CMoonMoon extends StandEntityAction {
    public static final StandPose POSE = new StandPose("CM_MOON");
    public CMoonMoon(StandEntityAction.Builder builder){
        super(builder);
    }

    @Override
    protected ActionConditionResult checkStandConditions(StandEntity stand, IStandPower power, ActionTarget target) {
        if (power.getStamina() < 100) return ActionConditionResult.NEGATIVE;
        if (stand.isManuallyControlled()) return ActionConditionResult.NEGATIVE;
        return ActionConditionResult.POSITIVE;
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            LivingEntity user = userPower.getUser();
            int duration = 50/2;
            if (!user.hasEffect(InitEffects.CM_GRAVITY.get())) user.addEffect(new EffectInstance(InitEffects.CM_GRAVITY.get(), duration, 3, false, false, true)); // AHH MOMENT 💀💀💀
            if (!user.hasEffect(Effects.SLOW_FALLING)) user.addEffect(new EffectInstance(Effects.SLOW_FALLING, duration*3, 12, false, false, true));
            if (!user.hasEffect(InitEffects.CM_PARALYSIS.get())) user.addEffect(new EffectInstance(InitEffects.CM_PARALYSIS.get(), duration, 2, false, false, true));
        }
    }
}