package com.weever.rotp_cm.action.stand;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
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

public class CMoonInversionPunch extends StandEntityHeavyAttack {
    public CMoonInversionPunch (StandEntityHeavyAttack.Builder builder){
        super(builder);
    }

    @Override
    protected ActionConditionResult checkStandConditions(StandEntity stand, IStandPower power, ActionTarget target) {
        CMoonEntity CMoon = (CMoonEntity) stand;
        if (CMoon.isAtt()) {
            return conditionMessage("cant_control_stand");
        }
        return ActionConditionResult.POSITIVE;
    }

    @Override
    public StandEntityPunch punchEntity(StandEntity stand, Entity target, StandEntityDamageSource dmgSource) {
        LivingEntity user = stand.getUser();
        int amplifier;
        if (!user.hasEffect(InitEffects.CM_AWAKENING.get())) { amplifier = 0; }
        else amplifier = user.getEffect(InitEffects.CM_AWAKENING.get()).getAmplifier();

        if (target instanceof LivingEntity) {
            LivingEntity livingTarget = (LivingEntity) target;
            ((LivingEntity) target).addEffect(new EffectInstance(InitEffects.CM_INVERSION.get(), 50, amplifier+3));
        }
        if (user.hasEffect(InitEffects.CM_AWAKENING.get())) {
            target.hurt(dmgSource, amplifier+1);
        }
        return super.punchEntity(stand, target, dmgSource)
                .addKnockback(0.5F + stand.getLastHeavyFinisherValue())
                .disableBlocking((float) stand.getProximityRatio(target) - 0.25F);
    }
}