package com.weever.rotp_cm.init;

import com.weever.rotp_cm.RotpCMAddon;
import com.weever.rotp_cm.effect.*;

import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitEffects {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(
            ForgeRegistries.POTIONS,
            RotpCMAddon.MOD_ID
    );
    public static final RegistryObject<Effect> CM_INVERSION = EFFECTS.register("cm_inversion",
            () -> new InversionEffect(0x00FF00)
    );
    public static final RegistryObject<Effect> CM_SHIFT = EFFECTS.register("cm_shift",
            () -> new InversionEffect(0x984cfc)
    );
}