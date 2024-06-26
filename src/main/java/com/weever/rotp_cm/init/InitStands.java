package com.weever.rotp_cm.init;

import com.github.standobyte.jojo.JojoMod;
import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.stand.*;
import com.github.standobyte.jojo.entity.stand.StandEntityType;
import com.github.standobyte.jojo.init.ModSounds;
import com.github.standobyte.jojo.init.power.stand.EntityStandRegistryObject;
import com.github.standobyte.jojo.init.power.stand.ModStandsInit;
import com.github.standobyte.jojo.power.impl.stand.StandInstance;
import com.github.standobyte.jojo.power.impl.stand.stats.StandStats;
import com.github.standobyte.jojo.power.impl.stand.stats.TimeStopperStandStats;
import com.github.standobyte.jojo.power.impl.stand.type.EntityStandType;
import com.github.standobyte.jojo.power.impl.stand.type.StandType;
import com.weever.rotp_cm.RotpCMAddon;
import com.weever.rotp_cm.action.stand.Destroy;
import com.weever.rotp_cm.action.stand.Punch;
import com.weever.rotp_cm.action.stand.punch.DestroyBarrage;
import com.weever.rotp_cm.action.stand.GravitationalShift;
import com.weever.rotp_cm.action.stand.punch.HeartInversion;
import com.weever.rotp_cm.entity.CMEntity;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import static com.github.standobyte.jojo.init.power.ModCommonRegisters.ACTIONS;

public class InitStands {
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<Action<?>> ACTIONS = DeferredRegister.create(
            (Class<Action<?>>) ((Class<?>) Action.class), RotpCMAddon.MOD_ID);
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<StandType<?>> STANDS = DeferredRegister.create(
            (Class<StandType<?>>) ((Class<?>) StandType.class), RotpCMAddon.MOD_ID);
    
 // ======================================== C-Moon ========================================
    public static final RegistryObject<StandEntityAction> CMOON_DESTROY_BARRAGE = ACTIONS.register("cm_destroy_barrage",
         () -> new DestroyBarrage(new StandEntityMeleeBarrage.Builder()
                 .standUserWalkSpeed(1F)
                 .standSound(InitSounds.CM_BARRAGE)
         )
    );

    public static final RegistryObject<TimeStop> BUY_MY_PATREON = ACTIONS.register("buy_my_patreon", // https://patreon.com/Weever
            () -> new TimeStop(new TimeStop.Builder())
    );

    public static final RegistryObject<StandEntityHeavyAttack> CMOON_HEART_INVERSION = ACTIONS.register("cm_heart_inversion",
            () -> new HeartInversion(new StandEntityHeavyAttack.Builder()
                    .punchSound(InitSounds.CM_HEART_INVERSION)
                    .staminaCost(50)
            )
    );

    public static final RegistryObject<StandEntityAction> CMOON_PUNCH = ACTIONS.register("cm_punch",
         () -> new Punch(new StandEntityLightAttack.Builder()
                 .punchSound(InitSounds.CM_PUNCH)
         )
    );

    public static final RegistryObject<StandEntityAction> CMOON_BARRAGE = ACTIONS.register("cm_barrage",
            () -> new StandEntityMeleeBarrage(new StandEntityMeleeBarrage.Builder()
                    .standSound(InitSounds.CM_BARRAGE)
            )
    );

    public static final RegistryObject<StandEntityHeavyAttack> CMOON_HEAVY_PUNCH = ACTIONS.register("cm_heavy_punch",
            () -> new StandEntityHeavyAttack(new StandEntityHeavyAttack.Builder()
                    .shiftVariationOf(CMOON_BARRAGE)
                    .shiftVariationOf(CMOON_PUNCH)
                    .setFinisherVariation(CMOON_HEART_INVERSION)
                    .partsRequired(StandInstance.StandPart.ARMS)
                    .punchSound(InitSounds.CM_HEAVY_PUNCH)
            )
    );

    public static final RegistryObject<StandEntityAction> CMOON_BLOCK = ACTIONS.register("cm_block",
            StandEntityBlock::new
    );

    public static final RegistryObject<StandEntityAction> CMOON_GRAVITATIONAL_SHIFT = ACTIONS.register("cm_gravitational_shift",
            () -> new GravitationalShift(new StandEntityAction.Builder()
                    .standSound(InitSounds.CM_GRAVITATIONAL_SHIFT)
                    .partsRequired(StandInstance.StandPart.MAIN_BODY)
                    .holdToFire(25, false)
                    .staminaCost(200)
                    .resolveLevelToUnlock(2)
                    .cooldown(10, 5)
            )
    );

    public static final RegistryObject<StandEntityAction> CMOON_DESTROY = ACTIONS.register("cm_destroy",
            () -> new Destroy(new StandEntityAction.Builder().standUserWalkSpeed(0.25f)
                    .resolveLevelToUnlock(1)
                    .holdToFire(10, false)
                    .cooldown(10, 10)
            )
    );

    public static final EntityStandRegistryObject<EntityStandType<TimeStopperStandStats>, StandEntityType<CMEntity>> STAND_CM =
            new EntityStandRegistryObject<>("cmoon",
                    STANDS, 
                    () -> new EntityStandType.Builder<TimeStopperStandStats>()
                    .color(0x63f542)
                    .storyPartName(ModStandsInit.PART_6_NAME)
                    .leftClickHotbar(
                            CMOON_PUNCH.get(), // Shift Variation: Heavy Punch
                            CMOON_BARRAGE.get() // Shift Variation: Heavy Punch
                            // Finisher: Heart Inversion
                    ).rightClickHotbar(
                            CMOON_BLOCK.get(),
                            CMOON_DESTROY.get(),
                            CMOON_GRAVITATIONAL_SHIFT.get()
                    )
                    .defaultStats(TimeStopperStandStats.class, new TimeStopperStandStats.Builder()
                            .power(0)
                            .speed(11)
                            .range(200, 300)
                            .durability(8) // stamina
                            .precision(6) // accuracy
                            .randomWeight(0.5)
                            .timeStopMaxTicks(0, 0)
                            .timeStopLearningPerTick(0)
                            .timeStopDecayPerDay(0F)
                            .timeStopCooldownPerTick(0F)
                            .build()
                    )
                    .build(),
                    InitEntities.ENTITIES,
                    () -> new StandEntityType<>(CMEntity::new, 0.7F, 2F)
                            .summonSound(InitSounds.CM_SUMMON)
                            .unsummonSound(InitSounds.CM_UNSUMMON))
            .withDefaultStandAttributes();
}
