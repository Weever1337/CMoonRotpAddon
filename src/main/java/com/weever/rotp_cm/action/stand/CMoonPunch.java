package com.weever.rotp_cm.action.stand;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityLightAttack;
import com.github.standobyte.jojo.action.stand.punch.StandEntityPunch;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.mc.damage.StandEntityDamageSource;
import com.weever.rotp_cm.entity.CMoonEntity;
import com.weever.rotp_cm.init.InitEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class CMoonPunch extends StandEntityLightAttack {
    public CMoonPunch (StandEntityLightAttack.Builder builder){
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
        if (user.hasEffect(InitEffects.CM_AWAKENING.get())) {
            target.hurt(dmgSource, user.getEffect(InitEffects.CM_AWAKENING.get()).getAmplifier() + 1);
        }
        return super.punchEntity(stand, target, dmgSource);
    }
}