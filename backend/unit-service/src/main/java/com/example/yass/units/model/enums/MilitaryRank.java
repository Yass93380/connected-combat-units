package com.example.yass.units.model.enums;

import lombok.Getter;

@Getter
public enum MilitaryRank {

    SOLDIER("soldat"), UNDER_OFFICER("sous-officier"), OFFICER("officier");

    private final String frenchRank;

    MilitaryRank(String frenchRank) {
        this.frenchRank = frenchRank;
    }
}
