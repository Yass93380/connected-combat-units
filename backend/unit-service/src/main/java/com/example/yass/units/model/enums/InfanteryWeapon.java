package com.example.yass.units.model.enums;

import lombok.Getter;

@Getter
public enum InfanteryWeapon {
    ASSAULT_RIFLE("FAMAS"), SNIPER("SVD_DRAGUNOV"),
    ANTI_TANK("JAVELIN"), ANTI_AIR("STINGER");

    private final String weaponName;

    InfanteryWeapon(String weaponName) {
        this.weaponName = weaponName;
    }

}
