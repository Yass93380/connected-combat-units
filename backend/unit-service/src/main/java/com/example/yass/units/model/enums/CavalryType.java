package com.example.yass.units.model.enums;

import lombok.Getter;

@Getter
public enum CavalryType {

    TANK("Char de combat"), INFANTERY_FIGHTING_VEHICLE("Véhicule de combat d'infanterie"),
    ARMOURED_RECO("Véhicule de reconnaissance blindé"),
    PERSONNEL_CARRIER_VEHICLE("Véhicule de transport de troupe blindé");

    private final String descriptionFr;

    CavalryType(String descriptionFr) {
        this.descriptionFr = descriptionFr;
    }
}
