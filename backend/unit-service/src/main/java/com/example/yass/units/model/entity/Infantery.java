package com.example.yass.units.model.entity;

import com.example.yass.units.model.enums.InfanteryWeapon;
import com.example.yass.units.model.enums.MilitaryRank;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "INFANTERY")
@Getter
@Setter
@Builder
public class Infantery extends Unit {

    @Column(name = "NAME")
    private String name;

    @Column(name = "RANK")
    @Enumerated(EnumType.STRING)
    private MilitaryRank rank;

    @Column(name = "WEAPON")
    @Enumerated(EnumType.STRING)
    private InfanteryWeapon weapon;

    @OneToMany
    @JoinColumn(name = "SUPERIOR_ID")
    List<Infantery> subordinates;
}
