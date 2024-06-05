package com.weever.rotp_cm.effects;

import com.github.standobyte.jojo.potion.IApplicableEffect;
import com.github.standobyte.jojo.potion.UncurableEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ForgeMod;

import java.util.UUID;

public class AwakeningEffect extends UncurableEffect implements IApplicableEffect {
    public AwakeningEffect(int color) {
        super(EffectType.HARMFUL, color);
        this.addAttributeModifier(
                Attributes.MAX_HEALTH,
                "37e11769-5f6d-49b2-aaf8-7c330b9b61b8",
                0.2D,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        )
        .addAttributeModifier(
                ForgeMod.ENTITY_GRAVITY.get(),
                "903bd50b-f186-412f-aa8c-077fb196441e",
                -0.2D,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }

    public boolean isDurationEffectTick(int duration, int amplifier) { return true; }

    @Override
    public boolean isApplicable(LivingEntity entity) {
        return true;
    }
}
