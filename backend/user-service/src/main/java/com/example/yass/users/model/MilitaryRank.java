package com.example.yass.users.model;

import lombok.Getter;

@Getter
public enum MilitaryRank {

    SOLDIER("soldat", 1), OFFICER("officier", 2),
            SENIOR_OFFICER("état-major", 3);

    private final String frenchRank;
    private final int numericRank;

    MilitaryRank(String frenchRank, int numericRank) {
        this.frenchRank = frenchRank;
        this.numericRank = numericRank;
    }
}
