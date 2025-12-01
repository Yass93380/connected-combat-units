package com.example.yass.units.model.utils;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
public class GpsCoordinates {

    @Column(name = "LONGITUDE", nullable = false)
    private Double longitude;

    @Column(name = "LATITUDE", nullable = false)
    private Double latitude;

    @Column(name = "ALTITUDE")
    private Double altitude; //null ou 0, dans le cas d'unité terrestre ou maritime.

}
