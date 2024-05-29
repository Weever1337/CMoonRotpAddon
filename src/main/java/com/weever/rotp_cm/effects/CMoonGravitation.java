package com.weever.rotp_cm.effects;

import com.github.standobyte.jojo.potion.UncurableEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

import java.util.UUID;

public class CMoonGravitation extends UncurableEffect {
    public CMoonGravitation(int color) {
        super(EffectType.HARMFUL, color);
    }

    public boolean isDurationEffectTick(int duration, int amplifier) { return true; }

    @Override
    public void applyEffectTick(LivingEntity Entity, int amplifier) {
        // no code lol :D
    }
    public static final AttributeModifier SLOW_FALLING = new AttributeModifier(
            UUID.fromString("e00c3955-0634-44a4-9bfa-071fb74914e3"), "Gravity", -0.875, AttributeModifier.Operation.MULTIPLY_TOTAL);

}
