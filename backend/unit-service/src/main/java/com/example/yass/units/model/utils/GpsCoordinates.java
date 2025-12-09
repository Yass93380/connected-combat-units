package com.example.yass.units.model.utils;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GpsCoordinates {

    @Column(name = "LATITUDE", nullable = false)
    private double latitude;

    @Column(name = "LONGITUDE", nullable = false)
    private double longitude;

    @Column(name = "ALTITUDE")
    private int altitude; //null dans le cas d'unité terrestre ou maritime (alt == 0).

}
