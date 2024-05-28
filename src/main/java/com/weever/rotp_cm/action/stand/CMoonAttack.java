package com.weever.rotp_cm.action.stand;

import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.weever.rotp_cm.entity.CMoonEntity;
import net.minecraft.world.World;

public class CMoonAttack extends StandEntityAction {
    public CMoonAttack(StandEntityAction.Builder builder){
        super(builder);
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        CMoonEntity CMoon = (CMoonEntity) standEntity;
        CMoon.setAttOrNotWithAbility(!CMoon.isAttCauseOfAbility());
    }
}