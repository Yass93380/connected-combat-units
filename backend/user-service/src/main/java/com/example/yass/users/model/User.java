package com.example.yass.users.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="USERS")
public class User {

    @Id
    @Column(name = "ID", nullable = false, updatable = false)
    public UUID id;

    @Column(nullable = false, name = "MILITARY_RANK")
    @Enumerated(EnumType.STRING)
    private MilitaryRank militaryRank;

    @Column(nullable = false, name = "NAME")
    public String name;

    @Column(nullable = false, name = "ROLE")
    @Enumerated(EnumType.STRING)
    public UserRole role;

    @PrePersist
    public void generateId() {
        if(id == null)
            id = UUID.randomUUID();
    }
}
