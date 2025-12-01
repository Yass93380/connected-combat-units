package com.example.yass.users.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.util.Set;

@Getter
public enum UserRole {
    @JsonProperty("administrateur") ADMIN("administrateur", Set.of(Permission.READ, Permission.WRITE, Permission.CREATE, Permission.UPDATE, Permission.DELETE)),
    @JsonProperty("hiérarchie") HIERARCHY("hiérarchie", Set.of(Permission.READ, Permission.WRITE, Permission.UPDATE)),
    @JsonProperty("utilisateur") USER("utilisateur", Set.of(Permission.READ));

    private final String frenchRole;
    private final Set<Permission> permissions;

    UserRole(String frenchRole, Set<Permission> permissions) {
        this.frenchRole = frenchRole;
        this.permissions = permissions;
    }

    public static UserRole fromString(String frenchRole) {
        return switch (frenchRole) {
            case "administrateur" -> ADMIN;
            case "hiérarchie" -> HIERARCHY;
            case "utilisateur" -> USER;
            default -> throw new IllegalArgumentException("Le rang militaire " + frenchRole + "est inconnu.");
        };
    }

    public enum Permission {
        READ,
        WRITE,
        CREATE,
        UPDATE,
        DELETE
    }
}
