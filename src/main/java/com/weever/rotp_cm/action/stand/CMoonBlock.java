package com.weever.rotp_cm.action.stand;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityBlock;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.weever.rotp_cm.entity.CMoonEntity;

public class CMoonBlock extends StandEntityBlock {

    @Override
    protected ActionConditionResult checkStandConditions(StandEntity stand, IStandPower power, ActionTarget target) {
        CMoonEntity CMoon = (CMoonEntity) stand;
        if (CMoon.isAtt()) {
            return conditionMessage("cant_control_stand");
        }
        return ActionConditionResult.POSITIVE;
    }
}