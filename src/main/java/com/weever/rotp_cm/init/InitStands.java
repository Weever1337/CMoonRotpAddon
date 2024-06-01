package com.weever.rotp_cm.init;

import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.action.stand.StandEntityHeavyAttack;
import com.github.standobyte.jojo.action.stand.StandEntityLightAttack;
import com.github.standobyte.jojo.action.stand.StandEntityMeleeBarrage;
import com.github.standobyte.jojo.entity.stand.StandEntityType;
import com.github.standobyte.jojo.init.power.stand.EntityStandRegistryObject;
import com.github.standobyte.jojo.init.power.stand.ModStandsInit;
import com.github.standobyte.jojo.power.impl.stand.StandInstance.StandPart;
import com.github.standobyte.jojo.power.impl.stand.stats.StandStats;
import com.github.standobyte.jojo.power.impl.stand.type.EntityStandType;
import com.github.standobyte.jojo.power.impl.stand.type.StandType;
import com.weever.rotp_cm.RotpCMoonAddon;
import com.weever.rotp_cm.action.stand.*;
import com.weever.rotp_cm.entity.CMoonEntity;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class InitStands {
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<Action<?>> ACTIONS = DeferredRegister.create(
            (Class<Action<?>>) ((Class<?>) Action.class), RotpCMoonAddon.MOD_ID);
    @SuppressWarnings("unchecked")
    public static final DeferredRegister<StandType<?>> STANDS = DeferredRegister.create(
            (Class<StandType<?>>) ((Class<?>) StandType.class), RotpCMoonAddon.MOD_ID);
    
 // ======================================== CMOON Stand ========================================


    public static final RegistryObject<StandEntityAction> CMOON_PUNCH = ACTIONS.register("cm_punch",
            () -> new CMoonPunch(new StandEntityLightAttack.Builder()
                    .punchSound(InitSounds.CMOON_PUNCH_SOUND)
                    .standSound(InitSounds.CMOON_PUNCH_LIGHT)));
    
    public static final RegistryObject<StandEntityAction> CMOON_BARRAGE = ACTIONS.register("cm_barrage", 
            () -> new CMoonBarrage(new StandEntityMeleeBarrage.Builder()
                    .barrageHitSound(InitSounds.CMOON_PUNCH_SOUND)
                    .standSound(InitSounds.CMOON_PUNCH_BARRAGE)));

    public static final RegistryObject<StandEntityHeavyAttack> CMOON_INVERSION_PUNCH = ACTIONS.register("cm_inversion_punch",
            () -> new CMoonInversionPunch(new StandEntityHeavyAttack.Builder().cooldown(120)
                    .partsRequired(StandPart.ARMS)
                    .punchSound(InitSounds.CMOON_HEAVY_PUNCH_SOUND)
                    .shout(InitSounds.CMOON_INVERSION_PUNCH)
                    .staminaCostTick(50F)));

    public static final RegistryObject<StandEntityHeavyAttack> CMOON_HEAVY_PUNCH = ACTIONS.register("cm_heavy_punch",
            () -> new CMoonHeavyPunch(new StandEntityHeavyAttack.Builder()
                    .shiftVariationOf(CMOON_PUNCH).shiftVariationOf(CMOON_BARRAGE)
                    .setFinisherVariation(CMOON_INVERSION_PUNCH)
                    .punchSound(InitSounds.CMOON_HEAVY_PUNCH_SOUND)
                    .shout (InitSounds.CMOON_PUNCH_HEAVY)
                    .partsRequired(StandPart.ARMS)));
    
    public static final RegistryObject<StandEntityHeavyAttack> CMOON_EFFECTIVE_PUNCH = ACTIONS.register("cm_effective_punch",
            () -> new CMoonEffectivePunch(new StandEntityHeavyAttack.Builder().cooldown(120)
                    .partsRequired(StandPart.ARMS)
                    .standSound(InitSounds.CMOON_EFFECTIVE_PUNCH)
                    .staminaCostTick(50F)));
    
    public static final RegistryObject<StandEntityAction> CMOON_EFFECTIVE_PUNCH_RUN = ACTIONS.register("cm_effective_punch_run",
            () -> new CMoonEffectivePunchRun(new StandEntityAction.Builder().standWindupDuration(5)
                    .staminaCost(50F)));
    
    public static final RegistryObject<StandEntityAction> CMOON_EFFECTIVE_PUNCH_QUIT = ACTIONS.register("cm_effective_punch_quit",
            () -> new CMoonEffectivePunchQuit(new StandEntityAction.Builder()
            		.standWindupDuration(5)
                    .staminaCost(50F)
                    .shiftVariationOf(CMOON_EFFECTIVE_PUNCH)
                    .shiftVariationOf(CMOON_EFFECTIVE_PUNCH_RUN)));
    
    public static final RegistryObject<StandEntityAction> CMOON_BLOCK = ACTIONS.register("cm_block",
            () -> new CMoonBlock ());

    public static final RegistryObject<StandEntityAction> CMOON_ATTACK_BARRAGE = ACTIONS.register("cm_attack_barrage",
            () -> new CMoonAttackBarrage(new StandEntityMeleeBarrage.Builder()
                    .standUserWalkSpeed(1F)
                    .barrageHitSound(InitSounds.CMOON_PUNCH_SOUND)
                    .standSound(InitSounds.CMOON_PUNCH_BARRAGE)));
    
    public static final RegistryObject<StandEntityAction> CMOON_MOON = ACTIONS.register("cm_moon",
            () -> new CMoonMoon(new StandEntityAction.Builder().cooldown(250)
                    .holdToFire(20, false)
                    .staminaCostTick(100F)
                    .partsRequired(StandPart.MAIN_BODY)
                    .shout(InitSounds.CMOON_MOON)
            ));

    public static final RegistryObject<StandEntityHeavyAttack> CMOON_GO_TO_MOON = ACTIONS.register("cm_go_to_moon",
            () -> new CMoonGoToMoon(new StandEntityHeavyAttack.Builder().cooldown(250)
                    .partsRequired(StandPart.MAIN_BODY)
                    .shout(InitSounds.CMOON_MOON)
                    .staminaCostTick(100F)
                    .shiftVariationOf(CMOON_MOON)
            ));

    public static final RegistryObject<StandEntityAction> CMOON_GRAVITATION = ACTIONS.register("cm_gravitational_changes",
            () -> new CMoonGravitationalChanges(new StandEntityAction.Builder().cooldown(220).standUserWalkSpeed(0.25f)
                    .shout(InitSounds.CMOON_MOON)
                    .resolveLevelToUnlock(3)
                    .holdToFire(20, false)
                    .staminaCostTick(200F)
                    .partsRequired(StandPart.MAIN_BODY)
            ));

    public static final RegistryObject<StandEntityAction> CMOON_AWAKENING = ACTIONS.register("cm_awakening",
            () -> new CMoonAwakening(new StandEntityAction.Builder().cooldown(2500).standUserWalkSpeed(0.25f)
                    .resolveLevelToUnlock(3)
                    .partsRequired(StandPart.MAIN_BODY)
                    .holdToFire(40, false)
                    .staminaCostTick(400F)
                    .shout(InitSounds.CMOON_AWAKENING)
            ));

    public static final RegistryObject<StandEntityAction> CMOON_AUTO_ATTACK = ACTIONS.register("cm_attack",
            () -> new CMoonAttack(new StandEntityAction.Builder().standUserWalkSpeed(0.25f)
                    .holdToFire(20, false)
                    .resolveLevelToUnlock(3)
                    .cooldown(100)
                    .standSound(InitSounds.CMOON_ATTACK)));
    
    public static final RegistryObject<StandEntityAction> CMOON_GRAVITATIONAL_BARRIER = ACTIONS.register("cm_gravitational_barrier",
            () -> new CMoonGravitationalBarrier(new StandEntityAction.Builder().cooldown(30).standUserWalkSpeed(0.25f)
                    .resolveLevelToUnlock(3)
                    .partsRequired(StandPart.MAIN_BODY)
                    .staminaCostTick(300F)
                    .holdToFire(20, false)
                    .shout(InitSounds.CMOON_SUMMON_VOICELINE)
            ));

    public static final EntityStandRegistryObject<EntityStandType<StandStats>, StandEntityType<CMoonEntity>> STAND_CMOON =
            new EntityStandRegistryObject<>("cmoon",
                    STANDS, 
                    () -> new EntityStandType.Builder<StandStats>()
                    .color(0x248f2d)
                    .storyPartName(ModStandsInit.PART_6_NAME)
                    .leftClickHotbar(
                            CMOON_PUNCH.get(),
                            CMOON_BARRAGE.get(),
                            CMOON_EFFECTIVE_PUNCH.get(),
                            CMOON_GRAVITATIONAL_BARRIER.get()
                    )
                    .rightClickHotbar(
                            CMOON_BLOCK.get(),
                            CMOON_MOON.get(),
                            CMOON_GRAVITATION.get(),
                            CMOON_AWAKENING.get(),
                            CMOON_AUTO_ATTACK.get()
                    )
                    .defaultStats(StandStats.class, new StandStats.Builder()
                            .power(7)
                            .speed(13)
                            .range(250, 300)
                            .durability(8)
                            .precision(6)
                            .build())
                    .addSummonShout(InitSounds.CMOON_SUMMON_VOICELINE)
                    .addOst(InitSounds.CMOON_OST)
                    .build(),
                    
                    InitEntities.ENTITIES,
                    () -> new StandEntityType<CMoonEntity>(CMoonEntity::new, 0.7F, 2.0F)
                    .summonSound(InitSounds.CMOON_SUMMON_SOUND)
                    .unsummonSound(InitSounds.CMOON_UNSUMMON_SOUND))
            .withDefaultStandAttributes();
}
