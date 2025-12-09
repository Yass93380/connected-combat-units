package com.example.yass.units.dto;

import com.example.yass.units.model.entity.Air;
import com.example.yass.units.model.enums.AirType;
import com.example.yass.units.model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record AirDto(

        UUID id,

        @NotBlank
        String name,

        @NotNull
        AirType type,

        @NotNull
        Integer speed,

        @NotNull(message = "Status obligatoire")
        Status status,

        @NotNull(message = "Munitions manquantes")
        Long munitions,

        @NotNull(message = "précisez la latitude")
        Double latitude,

        @NotNull(message = "précisez la longitude")
        Double longitude,

        @NotNull(message = "Zéro pour les unités de surface(Terre & Mer), supérieur à zéro sinon")
        Integer altitude,
        
        List<Air> subordinates
) {
}
