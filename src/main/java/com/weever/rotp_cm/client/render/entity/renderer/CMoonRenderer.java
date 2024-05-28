package com.weever.rotp_cm.client.render.entity.renderer;

import com.github.standobyte.jojo.client.render.entity.model.stand.StandEntityModel;
import com.github.standobyte.jojo.client.render.entity.model.stand.StandModelRegistry;
import com.github.standobyte.jojo.client.render.entity.renderer.stand.StandEntityRenderer;
import com.weever.rotp_cm.RotpCMoonAddon;
import com.weever.rotp_cm.client.render.entity.model.CMoonModel;
import com.weever.rotp_cm.entity.CMoonEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class CMoonRenderer extends StandEntityRenderer<CMoonEntity, StandEntityModel<CMoonEntity>> {
    
    public CMoonRenderer(EntityRendererManager renderManager) {
        super(renderManager, 
                StandModelRegistry.registerModel(new ResourceLocation(RotpCMoonAddon.MOD_ID, "cmoon"), CMoonModel::new),
                new ResourceLocation(RotpCMoonAddon.MOD_ID, "textures/entity/stand/cmoon.png"), 0);
    }
}
