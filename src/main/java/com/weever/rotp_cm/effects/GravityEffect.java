package com.weever.rotp_cm.effects;

import com.github.standobyte.jojo.potion.IApplicableEffect;
import com.github.standobyte.jojo.potion.UncurableEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.EffectType;
import net.minecraftforge.common.ForgeMod;

public class GravityEffect extends UncurableEffect implements IApplicableEffect {
    public GravityEffect(int color) {
        super(EffectType.HARMFUL, color);
        this.addAttributeModifier(
                ForgeMod.ENTITY_GRAVITY.get(),
                "40e1e5e0-fbce-4438-bebf-99a38729c172",
                -0.5D,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }

    public boolean isDurationEffectTick(int duration, int amplifier) { return true; }

    @Override
    public boolean isApplicable(LivingEntity entity) {
        return true;
    }
}
