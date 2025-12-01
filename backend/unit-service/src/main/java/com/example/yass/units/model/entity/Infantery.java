package com.example.yass.units.model.entity;

import com.example.yass.units.model.enums.InfanteryWeapon;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "INFANTERY")
@Getter
@Setter
@Builder
public class Infantery extends Unit {

    @Column(name = "NAME")
    private String name;

    @Column(name = "WEAPON")
    @Enumerated(EnumType.STRING)
    private InfanteryWeapon weapon;

    @Column(name = "MUNITIONS")
    private Long munitions;
}
