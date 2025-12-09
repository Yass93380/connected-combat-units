package com.example.yass.units.model.entity;

import com.example.yass.units.model.enums.Status;
import com.example.yass.units.model.utils.GpsCoordinates;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "MUNITIONS", nullable = false)
    private long munitions;

    @Embedded
    private GpsCoordinates gpsCoordinates;

    @PrePersist
    public void generateId() {
        if (id == null)
            id = UUID.randomUUID();
    }
}
