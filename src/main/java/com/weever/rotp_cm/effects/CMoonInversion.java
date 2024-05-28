package com.weever.rotp_cm.effects;

import com.github.standobyte.jojo.potion.UncurableEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

public class CMoonInversion extends UncurableEffect {
    public CMoonInversion(int color) {
        super(EffectType.HARMFUL, color);
    }

    public boolean isDurationEffectTick(int duration, int amplifier) { return true; }

    @Override
    public void applyEffectTick(LivingEntity Entity, int amplifier) {
        if (!Entity.level.isClientSide()) {
            Entity.hurt(DamageSource.MAGIC, amplifier);
        }
    }
}
