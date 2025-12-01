package com.example.yass.users.model.entity;

import com.example.yass.users.model.enums.MilitaryRank;
import com.example.yass.users.model.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name="USERS")
public class User implements UserDetails {

    @Id
    @Column(name = "ID", nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, name = "MILITARY_RANK")
    @Enumerated(EnumType.STRING)
    private MilitaryRank militaryRank;

    @Column(nullable = false, name = "NAME")
    private String name;

    @Column(name = "PASSWORD", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false, name = "ROLE")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @PrePersist
    private void generateId() {
        if(id == null)
            id = UUID.randomUUID();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authorities = this.role.getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.role.name()));

        return authorities;
    }

    @Override
    public String getUsername() {
        return this.name;
    }
}
