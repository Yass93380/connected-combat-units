package com.example.yass.units.model.entity;

import com.example.yass.units.model.enums.CavalryType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "CAVALRY")
@Getter
@Setter
@Builder
public class Cavalry extends Unit {

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private CavalryType type;

    @Column(name = "OPERATOR_NUMBER")
    private int operator_number;

    @Column(name = "CALIBRE")
    private int calibre;

    @Column(name = "ARMOUR") // blindage.
    private int armour;

    @OneToMany
    @JoinColumn(name = "SUPERIOR_ID")
    List<Cavalry> subordinates;

}
