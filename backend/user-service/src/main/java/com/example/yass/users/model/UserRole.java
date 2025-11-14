package com.example.yass.users.model;

import lombok.Getter;
import java.util.Set;

@Getter
public enum UserRole {
    ADMIN(Set.of(Permission.READ, Permission.WRITE, Permission.CREATE, Permission.UPDATE, Permission.DELETE)),
    HIERARCHY(Set.of(Permission.READ, Permission.WRITE, Permission.UPDATE)),
    USER(Set.of(Permission.READ));

    private final Set<Permission> permissions;

    UserRole(Set<Permission> permissions) {
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
