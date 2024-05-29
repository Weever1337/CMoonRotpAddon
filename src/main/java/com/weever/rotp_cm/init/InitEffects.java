package com.weever.rotp_cm.init;

import com.weever.rotp_cm.RotpCMoonAddon;
import com.weever.rotp_cm.effects.*;

import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitEffects {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(
            ForgeRegistries.POTIONS,
            RotpCMoonAddon.MOD_ID
    );
    public static final RegistryObject<Effect> CM_INVERSION = EFFECTS.register("cm_inversion",
            () -> new CMoonInversion(0x00FF00)
    );
    public static final RegistryObject<Effect> CM_AWAKENING = EFFECTS.register("cm_awakening",
            () -> new CMoonAwakening(0xff0000)
    );
    public static final RegistryObject<Effect> CM_PARALYSIS = EFFECTS.register("cm_paralysis",
            () -> new CMoonAwakening(0x0f0f0f)
    );
}