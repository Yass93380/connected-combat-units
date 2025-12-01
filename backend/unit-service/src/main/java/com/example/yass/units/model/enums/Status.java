package com.example.yass.units.model.enums;

import lombok.Getter;

@Getter
public enum Status {

    ENABLED("Prêt au combat, moral haut"),  ENGAGED("Combat en cours, moral haut"),
    DISABLED("Unité détruite"), DEGRADED("Combat en cours, moral bas");

    private final String status;

    Status(String status){
        this.status = status;
    }
}
