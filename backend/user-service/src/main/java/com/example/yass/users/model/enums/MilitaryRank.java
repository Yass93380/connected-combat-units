package com.example.yass.users.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public enum MilitaryRank {

    @JsonProperty("soldat") SOLDIER("soldat", 1), @JsonProperty("officier") OFFICER("officier", 2),
    @JsonProperty("état-major") SENIOR_OFFICER("état-major", 3);

    private final String frenchRank;
    private final int numericRank;

    MilitaryRank(String frenchRank, int numericRank) {
        this.frenchRank = frenchRank;
        this.numericRank = numericRank;
    }
}
