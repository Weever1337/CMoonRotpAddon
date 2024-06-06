package com.weever.rotp_cm.action.stand;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityHeavyAttack;
import com.github.standobyte.jojo.action.stand.punch.StandEntityPunch;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.mc.damage.StandEntityDamageSource;
import com.weever.rotp_cm.entity.CMoonEntity;
import com.weever.rotp_cm.init.InitEffects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class CMoonInversionPunch extends StandEntityHeavyAttack {
    public CMoonInversionPunch (StandEntityHeavyAttack.Builder builder){
        super(builder);
    }

    @Override
    protected ActionConditionResult checkStandConditions(StandEntity stand, IStandPower power, ActionTarget target) {
    	CMoonEntity CMoon = (CMoonEntity) stand;
    	if (power.getStamina() < 50) return ActionConditionResult.NEGATIVE;
        else if (CMoon.isAtt()) return ActionConditionResult.NEGATIVE;
        return ActionConditionResult.POSITIVE;
    }

    @Override
    public StandEntityPunch punchEntity(StandEntity stand, Entity target, StandEntityDamageSource dmgSource) {
        LivingEntity user = stand.getUser();
        LivingEntity livingEntity = (LivingEntity) target;
        if (!livingEntity.hasEffect(InitEffects.CM_INVERSION.get()))
            livingEntity.addEffect(new EffectInstance(InitEffects.CM_INVERSION.get(), 150, 1, false, false, true));

        return super.punchEntity(stand, target, dmgSource)
                .addKnockback(10F)
                .disableBlocking((float) stand.getProximityRatio(target) - 0.25F);
    }
}