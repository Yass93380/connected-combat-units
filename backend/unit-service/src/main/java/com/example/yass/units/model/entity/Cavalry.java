package com.example.yass.units.model.entity;

import com.example.yass.units.model.enums.CavalryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "CAVALRY")
@Getter
@Setter
@SuperBuilder
public class Cavalry extends Unit {

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private CavalryType type;

    @Column(name = "OPERATOR_NUMBER", nullable = false)
    private int operator_number;

    @Column(name = "CALIBRE", nullable = false)
    private int calibre;

    @Column(name = "ARMOUR", nullable = false) // blindage.
    private int armour;

    @OneToMany
    @JoinColumn(name = "SUPERIOR_ID")
    List<Cavalry> subordinates;

}
