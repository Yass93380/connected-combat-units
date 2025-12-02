package com.example.yass.units.model.enums;

import lombok.Getter;

@Getter
public enum AirType {

    AIRCRAFT("avion"), HELICOPTER("hélicoptère"), AWACS("Avion Radar"), BOMBER("Bombardier"), DRONE("drône");

    private final String frenchType;

    AirType(String type) {
        this.frenchType = type;
    }
}
