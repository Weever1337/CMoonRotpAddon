package com.weever.rotp_cm.effects;

import com.github.standobyte.jojo.potion.IApplicableEffect;
import com.github.standobyte.jojo.potion.UncurableEffect;
import com.weever.rotp_cm.init.InitEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ForgeMod;

import java.util.UUID;

public class ShiftEffect extends UncurableEffect implements IApplicableEffect {
    public ShiftEffect(int color) {
        super(EffectType.BENEFICIAL, color);
        this.addAttributeModifier(
                Attributes.MAX_HEALTH,
                "37e11769-5f6d-49b2-aaf8-7c330b9b61b8",
                0.2D,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }

    public boolean isDurationEffectTick(int duration, int amplifier) { return true; }

    @Override
    public boolean isApplicable(LivingEntity entity) {
        return true;
    }
}