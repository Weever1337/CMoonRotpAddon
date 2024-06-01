package com.weever.rotp_cm.action.stand;

import java.util.Random;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.init.ModStatusEffects;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.weever.rotp_cm.init.InitEffects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class CMoonAwakening extends StandEntityAction {
    public CMoonAwakening(StandEntityAction.Builder builder){
        super(builder);
    }

    @Override
    protected ActionConditionResult checkStandConditions(StandEntity stand, IStandPower power, ActionTarget target) {
        LivingEntity user = power.getUser();
        if (user.hasEffect(InitEffects.CM_AWAKENING.get())) return ActionConditionResult.NEGATIVE;
        if (power.getStamina() < 400) return ActionConditionResult.NEGATIVE;
        return ActionConditionResult.POSITIVE;
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            Random rand = new Random();
            LivingEntity user = userPower.getUser();
            user.addEffect(new EffectInstance(ModStatusEffects.STAMINA_REGEN.get(), 100, 1));
            user.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 30, 255));
            user.addEffect(new EffectInstance(Effects.BLINDNESS, 30, 255));
            user.hurt(DamageSource.MAGIC, 5.5F);
            user.addEffect(new EffectInstance(InitEffects.CM_AWAKENING.get(), 5000, rand.nextInt(4), false, false, true));
        }
    }
}