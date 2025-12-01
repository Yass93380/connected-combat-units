package com.example.yass.units.model.entity;

import com.example.yass.units.model.enums.CavalryType;
import jakarta.persistence.*;

@Entity
@Table(name = "CAVALRY")
public class Cavalry {

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private CavalryType type;


}
