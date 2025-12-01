package com.example.yass.units.model.entity;

import com.example.yass.units.model.enums.Status;
import com.example.yass.units.model.utils.GpsCoordinates;
import jakarta.persistence.*;

import java.util.UUID;

@MappedSuperclass
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    private GpsCoordinates gpsCoordinates;

}
