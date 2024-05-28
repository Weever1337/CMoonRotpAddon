package com.weever.rotp_cm.effects;

import com.github.standobyte.jojo.potion.UncurableEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

public class CMoonAwakening extends UncurableEffect {
    public CMoonAwakening(int color) {
        super(EffectType.HARMFUL, color);
    }

    public boolean isDurationEffectTick(int duration, int amplifier) { return true; }

    @Override
    public void applyEffectTick(LivingEntity Entity, int amplifier) {
        // no code lol :D
    }
}
