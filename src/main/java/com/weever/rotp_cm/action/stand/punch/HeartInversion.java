package com.weever.rotp_cm.action.stand.punch;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.*;
import com.github.standobyte.jojo.action.stand.punch.StandEntityPunch;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.mc.damage.StandEntityDamageSource;

import com.weever.rotp_cm.entity.CMEntity;
import com.weever.rotp_cm.init.InitEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;

public class HeartInversion extends StandEntityHeavyAttack {
    public HeartInversion (StandEntityHeavyAttack.Builder builder){
        super(builder);
    }

    @Override
    protected ActionConditionResult checkStandConditions(StandEntity stand, IStandPower power, ActionTarget target) {
        CMEntity CMoon = (CMEntity) stand;
        if (power.getStamina() < 50) return ActionConditionResult.NEGATIVE;
        else if (CMoon.isAtt()) return ActionConditionResult.NEGATIVE;
        return ActionConditionResult.POSITIVE;
    }

    @Override
    public StandEntityPunch punchEntity(StandEntity stand, Entity target, StandEntityDamageSource dmgSource) {
        if (target != null && target instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) target;
            if (!livingEntity.hasEffect(InitEffects.CM_INVERSION.get()))
                livingEntity.addEffect(new EffectInstance(InitEffects.CM_INVERSION.get(), 200, 5, false, false, true));
        }
        return super.punchEntity(stand, target, dmgSource)
                .addKnockback(5F)
                .disableBlocking((float) stand.getProximityRatio(target) - 0.25F);
    }
}