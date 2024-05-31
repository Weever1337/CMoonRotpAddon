package com.weever.rotp_cm.action.stand;

import com.github.standobyte.jojo.action.stand.StandEntityHeavyAttack;
import com.github.standobyte.jojo.action.stand.punch.StandEntityPunch;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.util.mc.damage.StandEntityDamageSource;
import com.weever.rotp_cm.init.InitEffects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class CMoonHeavyPunch extends StandEntityHeavyAttack {
    public CMoonHeavyPunch (StandEntityHeavyAttack.Builder builder){
        super(builder);
    }

    @Override
    public StandEntityPunch punchEntity(StandEntity stand, Entity target, StandEntityDamageSource dmgSource) {
        LivingEntity user = stand.getUser();
        if (user.hasEffect(InitEffects.CM_AWAKENING.get())) {
            target.hurt(dmgSource, user.getEffect(InitEffects.CM_AWAKENING.get()).getAmplifier() + 1);
        }
        return super.punchEntity(stand, target, dmgSource)
                .addKnockback(0.5F + stand.getLastHeavyFinisherValue())
                .disableBlocking((float) stand.getProximityRatio(target) - 0.25F);
    }
}