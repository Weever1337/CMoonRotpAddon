package com.weever.rotp_cm.effect;

import com.github.standobyte.jojo.potion.UncurableEffect;
import com.weever.rotp_cm.RotpCMAddon;
import com.weever.rotp_cm.init.InitEffects;
import com.weever.rotp_cm.init.InitSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class InversionEffect extends UncurableEffect {
    public InversionEffect(int color) {
        super(EffectType.HARMFUL, color);
    }

    public boolean isDurationEffectTick(int duration, int amplifier) { return true; }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        int power = 0;
        if (amplifier == 0) power += 1;
        if (!entity.level.isClientSide()) {
            if (entity.tickCount % amplifier / 2 == 0) {
                entity.hurt(new DamageSource("cmoon").bypassArmor(), power*2);
                //entity.level.playSound(null, entity.blockPosition(), InitSounds.CM_INVERSION_HIT.get(), SoundCategory.PLAYERS, 0.7F,1);
            }
        }
    }

    @Mod.EventBusSubscriber(modid = RotpCMAddon.MOD_ID)
    public static class Events {
        @SubscribeEvent
        public static void onLivingHeal(LivingHealEvent event) {
            LivingEntity entity = (LivingEntity) event.getEntity();
            if (entity.hasEffect(InitEffects.CM_INVERSION.get()))
                event.setCanceled(true);
        }
    }
}