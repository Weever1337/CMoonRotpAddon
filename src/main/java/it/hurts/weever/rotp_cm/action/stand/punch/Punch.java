package it.hurts.weever.rotp_cm.action.stand.punch;

import com.github.standobyte.jojo.action.stand.StandEntityLightAttack;
import com.github.standobyte.jojo.action.stand.punch.StandEntityPunch;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.util.mc.damage.StandEntityDamageSource;
import net.minecraft.entity.Entity;

public class Punch extends StandEntityLightAttack {
    public Punch (StandEntityLightAttack.Builder builder){
        super(builder);
    }

    @Override
    public StandEntityPunch punchEntity(StandEntity stand, Entity target, StandEntityDamageSource dmgSource) {
        return super.punchEntity(stand, target, dmgSource)
                .damage(1.0F);
    }
}