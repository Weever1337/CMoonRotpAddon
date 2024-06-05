package com.weever.rotp_cm.client.ui.marker;

import com.github.standobyte.jojo.client.ui.marker.MarkerRenderer;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.power.impl.stand.type.StandType;
import com.weever.rotp_cm.RotpCMoonAddon;
import com.weever.rotp_cm.init.InitStands;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;


import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;


public class EntityMark extends MarkerRenderer {

    public EntityMark (Minecraft mc){
        super(new ResourceLocation(RotpCMoonAddon.MOD_ID,"textures/action/cm_effective_punch.png"), mc);
    }

    @Override
    protected boolean shouldRender() {
        AtomicBoolean render = new AtomicBoolean(false);

        IStandPower.getStandPowerOptional(this.mc.player).ifPresent(power -> {
            StandType<?> CM = InitStands.STAND_CMOON.getStandType();
            render.set(power.getType() == CM);
        });


        return render.get();
    }

    protected static class Marker extends MarkerRenderer.MarkerInstance {
        public Marker(Vector3d pos, boolean outlined) {
            super(pos, outlined);
        }
    }

    @Override
    protected void updatePositions(List<MarkerRenderer.MarkerInstance> list, float partialTick) {
        IStandPower.getStandPowerOptional(this.mc.player).ifPresent((stand) ->{

            Targets(this.mc.player).forEach((livingEntity -> {
                list.add(new Marker(livingEntity.getPosition(partialTick).add(0,livingEntity.getBbHeight()*1.1,0),true));

            }));
        });
    }


    public static Stream<LivingEntity> Targets(LivingEntity user){
        World world = user.level;
        String s_id = String.valueOf(user.getUUID());
        Stream<LivingEntity> entidades = world.getEntitiesOfClass(LivingEntity.class,user.getBoundingBox().inflate(100),
                EntityPredicates.ENTITY_STILL_ALIVE).stream().filter(entity -> entity.getTags().contains(s_id));
        return entidades;
    }



}