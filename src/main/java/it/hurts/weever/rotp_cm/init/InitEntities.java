package it.hurts.weever.rotp_cm.init;

import it.hurts.weever.rotp_cm.RotpCMAddon;

import net.minecraft.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(
            ForgeRegistries.ENTITIES, RotpCMAddon.MOD_ID);
};
