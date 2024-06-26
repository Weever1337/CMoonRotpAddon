package com.weever.rotp_cm.init;

import com.weever.rotp_cm.RotpCMAddon;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(
            ForgeRegistries.SOUND_EVENTS, RotpCMAddon.MOD_ID);

    public static final RegistryObject<SoundEvent> CM_SUMMON = SOUNDS.register("cm_summon",
            () -> new SoundEvent(new ResourceLocation(RotpCMAddon.MOD_ID, "cm_summon"))
    );

    public static final RegistryObject<SoundEvent> CM_UNSUMMON = SOUNDS.register("cm_unsummon",
            () -> new SoundEvent(new ResourceLocation(RotpCMAddon.MOD_ID, "cm_unsummon"))
    );

    public static final RegistryObject<SoundEvent> CM_PUNCH = SOUNDS.register("cm_punch",
            () -> new SoundEvent(new ResourceLocation(RotpCMAddon.MOD_ID, "cm_punch"))
    );

    public static final RegistryObject<SoundEvent> CM_HEAVY_PUNCH = SOUNDS.register("cm_heavy_punch",
            () -> new SoundEvent(new ResourceLocation(RotpCMAddon.MOD_ID, "cm_heavy_punch"))
    );

    public static final RegistryObject<SoundEvent> CM_BARRAGE = SOUNDS.register("cm_barrage",
            () -> new SoundEvent(new ResourceLocation(RotpCMAddon.MOD_ID, "cm_barrage"))
    );

    public static final RegistryObject<SoundEvent> CM_HEART_INVERSION = SOUNDS.register("cm_heart_inversion",
            () -> new SoundEvent(new ResourceLocation(RotpCMAddon.MOD_ID, "cm_heart_inversion"))
    );

//    public static final RegistryObject<SoundEvent> CM_DESTROY = SOUNDS.register("cm_destroy",
//            () -> new SoundEvent(new ResourceLocation(RotpCMAddon.MOD_ID, "cm_destroy"))
//    );

    public static final RegistryObject<SoundEvent> CM_GRAVITATIONAL_SHIFT = SOUNDS.register("cm_gravitational_shift",
            () -> new SoundEvent(new ResourceLocation(RotpCMAddon.MOD_ID, "cm_gravitational_shift"))
    );
}
