package com.weever.rotp_cm.action.stand;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityHeavyAttack;
import com.github.standobyte.jojo.action.stand.punch.StandEntityPunch;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.mc.damage.StandEntityDamageSource;
import com.weever.rotp_cm.entity.CMoonEntity;
import com.weever.rotp_cm.init.InitEffects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class CMoonGoToMoon extends StandEntityHeavyAttack {
    public CMoonGoToMoon(StandEntityHeavyAttack.Builder builder){
        super(builder);
    }
    @Override
    protected ActionConditionResult checkStandConditions(StandEntity stand, IStandPower power, ActionTarget target) {
        CMoonEntity CMoon = (CMoonEntity) stand;
    	if (power.getStamina() < 100) return ActionConditionResult.NEGATIVE;
        else if (CMoon.isAtt()) return ActionConditionResult.NEGATIVE;
        return ActionConditionResult.POSITIVE;
    }

    @Override
    public StandEntityPunch punchEntity(StandEntity stand, Entity target, StandEntityDamageSource dmgSource) {
        if (target instanceof LivingEntity) {
            LivingEntity livingTarget = (LivingEntity) target;
            LivingEntity user = stand.getUser();
            int duration = 100/2;
            if (user.hasEffect(InitEffects.CM_AWAKENING.get())) { duration = 200/2; }
            if (!livingTarget.hasEffect(InitEffects.CM_GRAVITY.get())) livingTarget.addEffect(new EffectInstance(InitEffects.CM_GRAVITY.get(), duration, 2, false, false, true));
            if (!livingTarget.hasEffect(Effects.SLOW_FALLING)) livingTarget.addEffect(new EffectInstance(Effects.SLOW_FALLING, duration*2, 12, false, false, true));
            if (!livingTarget.hasEffect(InitEffects.CM_PARALYSIS.get())) livingTarget.addEffect(new EffectInstance(InitEffects.CM_PARALYSIS.get(), duration, 1, false, false, true));
        }
        return super.punchEntity(stand, target, dmgSource);
    }
}