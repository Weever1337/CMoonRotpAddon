package it.hurts.weever.rotp_cm.init;

import it.hurts.weever.rotp_cm.RotpCMAddon;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InitParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, RotpCMAddon.MOD_ID);

    public static final RegistryObject<BasicParticleType> HAMON_SPARK_PURPLE = PARTICLES.register("hamon_spark_purple", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> HAMON_AURA_PURPLE = PARTICLES.register("hamon_aura_purple", () -> new BasicParticleType(false));
}