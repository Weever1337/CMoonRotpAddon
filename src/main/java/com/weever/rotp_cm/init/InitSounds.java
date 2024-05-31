package com.weever.rotp_cm.init;

import java.util.function.Supplier;

import com.github.standobyte.jojo.init.ModSounds;
import com.github.standobyte.jojo.util.mc.OstSoundList;
import com.weever.rotp_cm.RotpCMoonAddon;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(
            ForgeRegistries.SOUND_EVENTS, RotpCMoonAddon.MOD_ID);
    
    public static final RegistryObject<SoundEvent> CMOON_SUMMON_VOICELINE = SOUNDS.register("cm_summon",
            () -> new SoundEvent(new ResourceLocation(RotpCMoonAddon.MOD_ID, "cm_summon")));

    public static final Supplier<SoundEvent> CMOON_SUMMON_SOUND = ModSounds.STAND_SUMMON_DEFAULT;
    
    public static final Supplier<SoundEvent> CMOON_UNSUMMON_SOUND = ModSounds.STAND_UNSUMMON_DEFAULT;

    public static final Supplier<SoundEvent> CMOON_PUNCH_SOUND =  ModSounds.STAND_PUNCH_LIGHT;
    public static final Supplier<SoundEvent> CMOON_HEAVY_PUNCH_SOUND =  ModSounds.STAND_PUNCH_HEAVY;

    public static final Supplier<SoundEvent> CMOON_PUNCH_LIGHT = SOUNDS.register("cm_light_punch",
            () -> new SoundEvent(new ResourceLocation(RotpCMoonAddon.MOD_ID, "cm_light_punch")));

    public static final Supplier<SoundEvent> CMOON_PUNCH_HEAVY = SOUNDS.register("cm_heavy_punch",
            () -> new SoundEvent(new ResourceLocation(RotpCMoonAddon.MOD_ID, "cm_heavy_punch")));

    public static final Supplier<SoundEvent> CMOON_PUNCH_BARRAGE = SOUNDS.register("cm_barrage",
            () -> new SoundEvent(new ResourceLocation(RotpCMoonAddon.MOD_ID, "cm_barrage")));

    public static final Supplier<SoundEvent> CMOON_EFFECTIVE_PUNCH = SOUNDS.register("cm_effective_punch",
            () -> new SoundEvent(new ResourceLocation(RotpCMoonAddon.MOD_ID, "cm_effective_punch")));

    public static final Supplier<SoundEvent> CMOON_INVERSION_PUNCH = SOUNDS.register("cm_inversion_punch",
            () -> new SoundEvent(new ResourceLocation(RotpCMoonAddon.MOD_ID, "cm_inversion_punch")));

    public static final Supplier<SoundEvent> CMOON_MOON = SOUNDS.register("cm_moon",
            () -> new SoundEvent(new ResourceLocation(RotpCMoonAddon.MOD_ID, "cm_moon")));

    public static final Supplier<SoundEvent> CMOON_ATTACK = SOUNDS.register("cm_attack",
            () -> new SoundEvent(new ResourceLocation(RotpCMoonAddon.MOD_ID, "cm_attack")));

    public static final Supplier<SoundEvent> CMOON_AWAKENING = SOUNDS.register("cm_awakening",
            () -> new SoundEvent(new ResourceLocation(RotpCMoonAddon.MOD_ID, "cm_awakening")));
    
	public static final Supplier<SoundEvent> CMOON_SHOT = SOUNDS.register("cm_shot",
			() -> new SoundEvent(new ResourceLocation(RotpCMoonAddon.MOD_ID, "cm_shot")));
    
    public static final OstSoundList CMOON_OST = new OstSoundList(
            new ResourceLocation(RotpCMoonAddon.MOD_ID, "cm_ost"),
            SOUNDS
    );
}
