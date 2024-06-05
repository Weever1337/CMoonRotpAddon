package com.weever.rotp_cm.action.stand;

import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.action.stand.StandEntityHeavyAttack;
import com.github.standobyte.jojo.action.stand.punch.StandEntityPunch;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.mc.damage.StandEntityDamageSource;
import com.weever.rotp_cm.entity.CMoonEntity;
import com.weever.rotp_cm.init.InitStands;
import com.weever.rotp_cm.network.AddonPackets;
import com.weever.rotp_cm.network.server.AddTagPacket;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;

public class CMoonEffectivePunch extends StandEntityHeavyAttack {
    public CMoonEffectivePunch (StandEntityHeavyAttack.Builder builder){
        super(builder);
    }

    @Override
    protected Action<IStandPower> replaceAction(IStandPower power, ActionTarget target) {
        String s_id = String.valueOf(power.getUser().getUUID());
        Entity exist = EntityRange(power,16,s_id);
        if (exist != null){
            return InitStands.CMOON_EFFECTIVE_PUNCH_RUN.get();
        }
        return super.replaceAction(power, target);
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
        IStandPower power = stand.getUserPower();
		LivingEntity user = power.getUser();
        String s_id = String.valueOf(user.getUUID());
		//Entity exist = EntityRange(power,2*10,s_id);
		target.addTag(s_id);
		if (user instanceof ServerPlayerEntity) {
			AddonPackets.sendToClient(new AddTagPacket(target.getId(), s_id), (ServerPlayerEntity) user);
		}
        return super.punchEntity(stand, target, dmgSource)
                .addKnockback(0.0F)
                .disableBlocking(0.0F);
    }


//    @Override
//    public void standTickPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task){
//        LivingEntity user = userPower.getUser();
//        String s_id = String.valueOf(user.getUUID());
//        double range = 10;
//
//        Entity exist = EntityRange(userPower,2*range,s_id);
//
//        if (exist != null){
//            LivingEntity entity = standEntity.isManuallyControlled() ? standEntity:user;
//
//            RayTraceResult ray = JojoModUtil.rayTrace(entity.getEyePosition(1.0F),entity.getLookAngle(),
//                    range,world,entity,e->!(e.is(standEntity) || e.is(user)),0,0);
//            if(ray.getType() == RayTraceResult.Type.ENTITY) {
//                Entity target = ((EntityRayTraceResult) ray).getEntity();
//                standEntity.moveTo(target.position());
//            }
//        }
//    }
//
//    @Override
//    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task){
//       if(!world.isClientSide){
//           LivingEntity user = userPower.getUser();
//           String s_id = String.valueOf(user.getUUID());
//           double range = 10;
//
//           Entity exist = EntityRange(userPower,2*range,s_id);
//
//           if(exist == null){
//               LivingEntity entity = standEntity.isManuallyControlled() ? standEntity:user;
//
//               RayTraceResult ray = JojoModUtil.rayTrace(entity.getEyePosition(1.0F),entity.getLookAngle(),
//                       range,world,entity,e->!(e.is(standEntity) || e.is(user)),0,0);
//               if(ray.getType() == RayTraceResult.Type.ENTITY){
//                   Entity target =  ((EntityRayTraceResult) ray).getEntity();
//                   standEntity.moveTo(target.position());
//                   target.addTag(s_id);
//                   if (user instanceof ServerPlayerEntity) {
//                       AddonPackets.sendToClient(new AddTagPacket(target.getId(), s_id), (ServerPlayerEntity) user);
//                   }
//               }
//           }
//       }
    //}

   public static Entity EntityRange(IStandPower userPower, double range, String id){
	   LivingEntity user = userPower.getUser();
        World world = user.level;
        Entity entidad = world.getEntities(null, user.getBoundingBox().inflate(range)).stream().filter(entity -> entity.getTags().contains(id)).findFirst().orElse(null);
        return entidad;
   }


    @Override
    public StandAction[] getExtraUnlockable() {
        return new StandAction[] { InitStands.CMOON_EFFECTIVE_PUNCH_RUN.get(), InitStands.CMOON_EFFECTIVE_PUNCH_QUIT.get()};
    }
}