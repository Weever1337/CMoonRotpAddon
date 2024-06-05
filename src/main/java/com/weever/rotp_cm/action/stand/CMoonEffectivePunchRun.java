package com.weever.rotp_cm.action.stand;

import java.util.Random;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.weever.rotp_cm.init.InitEffects;
import com.weever.rotp_cm.network.AddonPackets;
import com.weever.rotp_cm.network.server.RemoveTagPacket;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.world.World;


public class CMoonEffectivePunchRun extends StandEntityAction {
    public CMoonEffectivePunchRun(StandEntityAction.Builder builder) {
        super(builder);
    }
    
    @Override
    protected ActionConditionResult checkStandConditions(StandEntity stand, IStandPower power, ActionTarget target) {
        if (power.getStamina() < 50) return ActionConditionResult.NEGATIVE;
        return ActionConditionResult.POSITIVE;
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task){
        if (!world.isClientSide){
            double range = 30;
            LivingEntity user = userPower.getUser();
            LivingEntity entity = livingEntityRange(userPower, range);

			if (entity != null){
				Random random = new Random();
		        int number = random.nextInt(3);
		        switch (number) {
		        	case 0: // Gravity Effect
		        		if (!entity.hasEffect(InitEffects.CM_GRAVITY.get())) entity.addEffect(new EffectInstance(InitEffects.CM_GRAVITY.get(), 15, 1, false, false, true));
		        		if (!entity.hasEffect(Effects.SLOW_FALLING)) entity.addEffect(new EffectInstance(Effects.SLOW_FALLING, 15*2, 12, false, false, false));
                        break;
		        	case 1: // Paralysis effect
		        		if (!entity.hasEffect(InitEffects.CM_PARALYSIS.get())) entity.addEffect(new EffectInstance(InitEffects.CM_PARALYSIS.get(), 50, 2, false, false, true));
		        		break;
		        	case 2: // Inversion Effect
		        		if (!entity.hasEffect(InitEffects.CM_INVERSION.get())) entity.addEffect(new EffectInstance(InitEffects.CM_INVERSION.get(), 50, 1, false, false, true));
		        		break;
		        }
		        String s_id = String.valueOf(user.getUUID());
	            entity.removeTag(s_id);
	            if (user instanceof ServerPlayerEntity) {
	                AddonPackets.sendToClient(new RemoveTagPacket(entity.getId(), s_id), (ServerPlayerEntity) user);
	            }
			}
        }
    }


    public static LivingEntity livingEntityRange(IStandPower standuser, Double range){
        LivingEntity user = standuser.getUser();
        World world = user.level;
        String s_id = String.valueOf(user.getUUID());
        LivingEntity entidad = world.getEntitiesOfClass(LivingEntity.class, user.getBoundingBox().inflate(range),
                EntityPredicates.ENTITY_STILL_ALIVE).stream().filter(entity -> entity.getTags().contains(s_id)).findFirst().orElse(null);
        return entidad;

    }




}