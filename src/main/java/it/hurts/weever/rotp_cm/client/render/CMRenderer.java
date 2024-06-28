package it.hurts.weever.rotp_cm.client.render;

import com.github.standobyte.jojo.client.render.entity.model.stand.StandEntityModel;
import com.github.standobyte.jojo.client.render.entity.model.stand.StandModelRegistry;
import com.github.standobyte.jojo.client.render.entity.renderer.stand.StandEntityRenderer;
import it.hurts.weever.rotp_cm.RotpCMAddon;
import it.hurts.weever.rotp_cm.entity.CMEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class CMRenderer extends StandEntityRenderer<CMEntity, StandEntityModel<CMEntity>> {
    public CMRenderer(EntityRendererManager renderManager) {
        super(renderManager, 
                StandModelRegistry.registerModel(new ResourceLocation(RotpCMAddon.MOD_ID, "cmoon"), CMModel::new),
                new ResourceLocation(RotpCMAddon.MOD_ID, "textures/entity/stand/cmoon.png"), 0);
    }
}
