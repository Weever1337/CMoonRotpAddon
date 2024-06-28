package it.hurts.weever.rotp_cm.action.stand;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.SoulEntity;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.init.ModStatusEffects;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.mc.MCUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.EntityPredicates;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class CenterOfGravity extends StandEntityAction {
    public CenterOfGravity(Builder builder) {
        super(builder);
    }

    @Override
    protected ActionConditionResult checkStandConditions(StandEntity stand, IStandPower power, ActionTarget target) {
        super.checkStandConditions(stand, power, target);
        if (power.getStamina() < 100) return ActionConditionResult.NEGATIVE;
        return ActionConditionResult.POSITIVE;
    }

    @Override
    public void standTickPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if(!world.isClientSide) {
            LivingEntity user = userPower.getUser();
            if(user != null) {
                world.getEntitiesOfClass(LivingEntity.class, user.getBoundingBox().inflate(8), EntityPredicates.ENTITY_STILL_ALIVE).forEach(
                entity -> {
                    if (entity == user || entity instanceof StandEntity) return;
                    double x = entity.getX();
                    double y = entity.getY() + .3;
                    double z = entity.getZ();
                    MCUtil.runCommand(user, "particle rotp_cm:hamon_spark_purple "+x+" "+y+" "+z);
                    entity.setDeltaMovement((x - entity.getX()) / 10,(y - entity.getY()) / 10,(z - entity.getZ()) / 10);
                    entity.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 20, 25, false, false, true));
                });
//                List<Entity> entitiesAround = new ArrayList<>();
//                entitiesAround.addAll(MCUtil.entitiesAround(LivingEntity.class, standEntity,
//                        7, false,
//                        entity -> entity != userPower.getUser() && !entity.isDeadOrDying()));
//                entitiesAround.forEach(entity -> {
//                    if (entity instanceof StandEntity) return;
//                    double x = entity.getX();
//                    double y = entity.getY() + .3;
//                    double z = entity.getZ();
//                    MCUtil.runCommand(userPower.getUser(), "particle rotp_cm:hamon_spark_purple "+x+" "+y+" "+z);
//                    entity.setDeltaMovement((x - entity.getX()) / 10,(y - entity.getY()) / 10,(z - entity.getZ()) / 10);
//                    ((LivingEntity) entity).addEffect(new EffectInstance(ModStatusEffects.STUN.get(), 20, 1, false, false, true));
//                });
            }
        }
    }


    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        // nuh uh
    }
}
