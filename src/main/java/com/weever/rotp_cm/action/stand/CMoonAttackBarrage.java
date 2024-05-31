package com.weever.rotp_cm.action.stand;

import com.github.standobyte.jojo.action.stand.StandEntityMeleeBarrage;

public class CMoonAttackBarrage extends StandEntityMeleeBarrage {
    public CMoonAttackBarrage(Builder builder) {
        super(builder.heldWalkSpeed(1).standUserWalkSpeed(1));
    }
}