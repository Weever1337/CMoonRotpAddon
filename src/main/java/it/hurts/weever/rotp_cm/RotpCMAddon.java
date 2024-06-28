package it.hurts.weever.rotp_cm;

import it.hurts.weever.rotp_cm.init.*;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RotpCMAddon.MOD_ID)
public class RotpCMAddon {
    public static final String MOD_ID = "rotp_cm";
    public static final Logger LOGGER = LogManager.getLogger();

    public RotpCMAddon() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RotpCMConfig.commonSpec);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        InitEntities.ENTITIES.register(modEventBus);
        InitSounds.SOUNDS.register(modEventBus);
        InitStands.ACTIONS.register(modEventBus);
        InitStands.STANDS.register(modEventBus);
        InitEffects.EFFECTS.register(modEventBus);
        InitParticles.PARTICLES.register(modEventBus);

        //AddonPackets.init();
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
