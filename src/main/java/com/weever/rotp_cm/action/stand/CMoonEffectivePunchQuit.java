package com.weever.rotp_cm.action.stand;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.weever.rotp_cm.entity.CMoonEntity;
import com.weever.rotp_cm.network.AddonPackets;
import com.weever.rotp_cm.network.server.RemoveTagPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;

public class CMoonEffectivePunchQuit extends StandEntityAction {
    public CMoonEffectivePunchQuit(StandEntityAction.Builder builder) {
        super(builder);
    }
    
    @Override
    protected ActionConditionResult checkStandConditions(StandEntity stand, IStandPower power, ActionTarget target) {
        CMoonEntity CMoon = (CMoonEntity) stand;
        if (CMoon.isAtt()) return conditionMessage("cant_control_stand");
        if (power.getStamina() < 50) return ActionConditionResult.NEGATIVE;
        return ActionConditionResult.POSITIVE;
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task){
        if(!world.isClientSide){
            Double range = 3*standEntity.getMaxRange();
            LivingEntity sigma = userPower.getUser();
            String s_id = String.valueOf(sigma.getUUID());
            Entity entity = CMoonEffectivePunch.EntityRange(userPower,range*3,s_id);
            if(entity!= null){
                entity.removeTag(s_id);

                if (sigma instanceof ServerPlayerEntity) {
                    AddonPackets.sendToClient(new RemoveTagPacket(entity.getId(), s_id), (ServerPlayerEntity) sigma);
                }
            }
        }
    }
}