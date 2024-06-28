package it.hurts.weever.rotp_cm.client;

import com.github.standobyte.jojo.client.particle.HamonAuraParticle;
import com.github.standobyte.jojo.client.particle.HamonSparkParticle;
import it.hurts.weever.rotp_cm.RotpCMAddon;
import it.hurts.weever.rotp_cm.client.render.CMRenderer;
import it.hurts.weever.rotp_cm.init.InitParticles;
import it.hurts.weever.rotp_cm.init.InitStands;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = RotpCMAddon.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientInit {
    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event) {
        Minecraft mc = event.getMinecraftSupplier().get();
        RenderingRegistry.registerEntityRenderingHandler(
                InitStands.STAND_CM.getEntityType(), CMRenderer::new);
    }

    @SubscribeEvent
    public static void onMcConstructor(ParticleFactoryRegisterEvent event) {
        Minecraft mc = Minecraft.getInstance();
        mc.particleEngine.register(InitParticles.HAMON_SPARK_PURPLE.get(), HamonSparkParticle.HamonParticleFactory::new);
        mc.particleEngine.register(InitParticles.HAMON_AURA_PURPLE.get(), HamonAuraParticle.Factory::new);
    }
}
