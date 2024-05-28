package com.weever.rotp_cm.action.stand;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.action.stand.StandEntityMeleeBarrage;
import com.github.standobyte.jojo.action.stand.punch.StandEntityPunch;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.mc.damage.StandEntityDamageSource;
import com.weever.rotp_cm.entity.CMoonEntity;
import net.minecraft.entity.Entity;

public class CMoonBarrage extends StandEntityMeleeBarrage {
    public CMoonBarrage(StandEntityMeleeBarrage.Builder builder) {
        super(builder);
    }

    @Override
    protected ActionConditionResult checkStandConditions(StandEntity stand, IStandPower power, ActionTarget target) {
        CMoonEntity cMoon = (CMoonEntity) stand;
        if (cMoon.isAtt()) {
            return conditionMessage("cant_control_stand");
        }
        return ActionConditionResult.POSITIVE;
    }
}