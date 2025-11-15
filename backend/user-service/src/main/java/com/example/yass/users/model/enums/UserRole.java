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

    private enum Permission {
        READ,
        WRITE,
        CREATE,
        UPDATE,
        DELETE
    }
}
