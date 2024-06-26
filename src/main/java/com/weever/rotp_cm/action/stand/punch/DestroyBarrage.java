package com.weever.rotp_cm.action.stand.punch;

import com.github.standobyte.jojo.action.stand.StandEntityMeleeBarrage;

public class DestroyBarrage extends StandEntityMeleeBarrage {
    public DestroyBarrage(Builder builder) {
        super(builder.heldWalkSpeed(1).standUserWalkSpeed(1));
    }
}