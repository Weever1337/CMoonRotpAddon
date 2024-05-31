package com.weever.rotp_cm.effects;
import com.github.standobyte.jojo.potion.UncurableEffect;
import com.weever.rotp_cm.RotpCMoonAddon;
import com.weever.rotp_cm.init.InitEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.potion.EffectType;
import net.minecraft.util.MovementInput;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class CMoonParalysis extends UncurableEffect {
    public CMoonParalysis(int color) {
        super(EffectType.HARMFUL, color);
    }

    public boolean isDurationEffectTick(int duration, int amplifier) { return true; }

    @Mod.EventBusSubscriber(modid = RotpCMoonAddon.MOD_ID, value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onMovementInput(InputUpdateEvent event) {
            LivingEntity player = (LivingEntity) event.getPlayer();

            if (player.hasEffect(InitEffects.CM_PARALYSIS.get())) {
                MovementInput input = event.getMovementInput();
                input.forwardImpulse = 0;
                input.leftImpulse = 0;
                input.jumping = false;
                input.shiftKeyDown = false;
                input.up = false;
                input.down = false;
                input.right = false;
                input.left = false;
            }
        }
    }
}
