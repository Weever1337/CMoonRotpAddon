package com.weever.rotp_cm.action.stand.projectile;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.init.ModSounds;
import com.github.standobyte.jojo.init.ModStatusEffects;
import com.github.standobyte.jojo.init.power.stand.ModStandsInit;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.mc.damage.DamageUtil;
import com.weever.rotp_cm.init.InitSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.StrayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class CMoonBarrier extends StandEntityAction {
    public CMoonBarrier(StandEntityAction.Builder builder) {
        super(builder);
    }

//    @Override
//    public void standTickPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task){
//        if(!world.isClientSide){
//            PlayerEntity player = (PlayerEntity) userPower.getUser ();
//            IStandPower power = userPower;
//            for (Entity entity : player.level.getEntities(this, this.getBoundingBox().inflate(10), entity -> (entity instanceof LivingEntity && this.checkTargets(entity)))){
//                LOGGER .info(entity.toString());
//                if (entity instanceof LivingEntity) {
//                    LivingEntity livingEntity = (LivingEntity) entity;
//                    if (!livingEntity.hasEffect(Effects.LEVITATION)) {
//                        livingEntity.addEffect(new EffectInstance(Effects.LEVITATION, 100, 2));
//                    }
//                } else {
//                    entity.hasImpulse = false;
//                }
//                power.consumeStamina(100);
//                if (power.getStamina() < 10) {
//                    level.playSound(null, this.blockPosition(), InitSounds.CMOON_UNSUMMON_SOUND.get(), SoundCategory.PLAYERS,1,1);
//                    power.toggleSummon();
//                    break;
//                }
//            }
//        }
//    }
}