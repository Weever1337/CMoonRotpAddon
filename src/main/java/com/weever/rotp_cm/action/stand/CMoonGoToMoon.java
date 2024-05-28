package com.weever.rotp_cm.action.stand;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
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
import net.minecraft.potion.Effects;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class CMoonGoToMoon extends StandEntityHeavyAttack {
    public CMoonGoToMoon(StandEntityHeavyAttack.Builder builder){
        super(builder);
    }
    @Override
    protected ActionConditionResult checkStandConditions(StandEntity stand, IStandPower power, ActionTarget target) {
        CMoonEntity CMoon = (CMoonEntity) stand;
        if (CMoon.isAtt()) { return conditionMessage("cant_control_stand"); }
        return ActionConditionResult.POSITIVE;
    }

    @Override
    public StandEntityPunch punchEntity(StandEntity stand, Entity target, StandEntityDamageSource dmgSource) {
        if (target instanceof LivingEntity) {
            LivingEntity livingTarget = (LivingEntity) target;
            LivingEntity user = stand.getUser();
            int duration = 100;
            if (user.hasEffect(InitEffects.CM_AWAKENING.get())) { duration = 200; }
            ((LivingEntity) target).addEffect(new EffectInstance(Effects.LEVITATION, duration, 2));
            ((LivingEntity) target).addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, duration, 999));
        }
        return super.punchEntity(stand, target, dmgSource);
    }
}