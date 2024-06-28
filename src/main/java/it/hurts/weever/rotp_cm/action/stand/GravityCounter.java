package it.hurts.weever.rotp_cm.action.stand;

import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.init.ModStatusEffects;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import it.hurts.weever.rotp_cm.RotpCMAddon;
import it.hurts.weever.rotp_cm.init.InitSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

public class GravityCounter extends StandEntityAction {
    public GravityCounter(Builder builder) {
        super(builder);
    }

    @Override
    protected ActionConditionResult checkStandConditions(StandEntity stand, IStandPower power, ActionTarget target) {
        super.checkStandConditions(stand, power, target);
        if (power.getStamina() < 75) return ActionConditionResult.NEGATIVE;
        return ActionConditionResult.POSITIVE;
    }

    @Override
    public void onClick(World world, LivingEntity user, IStandPower power) {
        if (!world.isClientSide) {
            if (user != null) {
                user.addTag("counter");
                System.out.println("[ACTION] Added tag counter");
            }
        }
    }

    @Override
    public void onTaskStopped(World world, StandEntity standEntity, IStandPower standPower, StandEntityTask task, @Nullable StandEntityAction newAction) {
        if(!world.isClientSide) {
            LivingEntity User = standPower.getUser();
            if (User != null && User.getTags().contains("counter")) {
                User.removeTag("counter");
                System.out.println("[ACTION] Removed tag counter");
            }
        }
    }

    @Mod.EventBusSubscriber(modid = RotpCMAddon.MOD_ID)
    public static class EventForAbility {
        @SubscribeEvent(priority = EventPriority.LOW)
        public static void onLivingHurtEvent(LivingHurtEvent event) {
            LivingEntity entity = event.getEntityLiving();
            Entity dmgEntity = event.getSource().getEntity();
            if (entity == null || !entity.getTags().contains("counter")) return;
            boolean isPlayer = entity instanceof PlayerEntity;
            System.out.println(String.format("[EVENT] LivingHurtEvent. Entity: {}", entity.getDisplayName()));
            if (isPlayer) {
                entity.removeTag("counter");
                ((LivingEntity) dmgEntity).addEffect(new EffectInstance(ModStatusEffects.STUN.get(), 75, 1, false, false, true));
                entity.level.playSound(null, entity.blockPosition(), InitSounds.CM_GRAVITY_COUNTER.get(), SoundCategory.PLAYERS, 1, 1);
                System.out.println("[EVENT] Removed Tag Counter");
                event.setAmount(0);
            }
        }
    }
}