package com.example.yass.units.model.entity;

import com.example.yass.units.model.enums.AirType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "AIR")
@Getter
@Setter
@Builder
public class Air {

    @Column(name = "TYPE")
    private AirType airType;

    @Column(name = "AIRCRAFT_NAME")
    private String name;

    @Column(name = "SPEED")
    private int speed;

    @OneToMany
    @JoinColumn(name = "SUPERIOR_ID")
    List<Air> subordinates;

}
