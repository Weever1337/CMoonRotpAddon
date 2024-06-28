package it.hurts.weever.rotp_cm;

import com.github.standobyte.jojo.init.ModStatusEffects;
import it.hurts.weever.rotp_cm.entity.CMEntity;
import it.hurts.weever.rotp_cm.init.InitEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;
import java.util.Random;

@Mod.EventBusSubscriber(modid = RotpCMAddon.MOD_ID)
public class GameplayHandler {
    static int duration = 75;

    @SubscribeEvent
    public static void onLivingHurtEvent(LivingHurtEvent event) {
        LivingEntity entity = event.getEntityLiving();
        DamageSource dmgSource = event.getSource();
        Entity dmgEntity = dmgSource.getEntity();
        if (dmgEntity instanceof CMEntity) {
            CMEntity cmEntity = (CMEntity) Objects.requireNonNull(dmgEntity);
            boolean isBuff = cmEntity.isBuff();
            if (isBuff) { duration += 45; }
            System.out.println("[C-Moon Rewrite] " + entity.getDisplayName().getString() + " was hit by " + dmgEntity.getDisplayName().getString());
            entity.addEffect(new EffectInstance(InitEffects.CM_INVERSION.get(), duration, getInversionDamage(), false, false, true));
            Random random = new Random();
            int randomInt = random.nextInt(3);
            switch (randomInt) {
                case 0:
                    entity.addEffect(new EffectInstance(ModStatusEffects.MISSHAPEN_ARMS.get(), duration, 1, false, false, true));
                    break;
                case 1:
                    entity.addEffect(new EffectInstance(ModStatusEffects.MISSHAPEN_LEGS.get(), duration, 1, false, false, true));
                    break;
                case 2:
                    entity.addEffect(new EffectInstance(ModStatusEffects.MISSHAPEN_FACE.get(), duration, 1, false, false, true));
                    break;
            }
        }
    }

    public static int getInversionDamage() {
        return RotpCMConfig.getCommonConfigInstance(false).DefaultInversionDamage.get();
    }

    public static int getHeartInversionDamage() {
        return RotpCMConfig.getCommonConfigInstance(false).DefaultHeartInversionDamage.get();
    }
}
