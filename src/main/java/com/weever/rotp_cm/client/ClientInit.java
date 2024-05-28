package com.weever.rotp_cm.client;

import com.github.standobyte.jojo.client.particle.MeteoriteVirusParticle;
import com.github.standobyte.jojo.client.particle.custom.CustomParticlesHelper;
import com.github.standobyte.jojo.client.resources.CustomResources;
import com.weever.rotp_cm.RotpCMoonAddon;
import com.weever.rotp_cm.client.render.entity.renderer.CMoonRenderer;
import com.weever.rotp_cm.init.InitStands;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;

@EventBusSubscriber(modid = RotpCMoonAddon.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientInit {
    
    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(
                InitStands.STAND_CMOON.getEntityType(), CMoonRenderer::new);
    }
}
