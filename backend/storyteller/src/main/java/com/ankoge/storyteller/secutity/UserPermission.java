package com.ankoge.storyteller.secutity;

import lombok.Getter;

@Getter
public enum UserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }
}