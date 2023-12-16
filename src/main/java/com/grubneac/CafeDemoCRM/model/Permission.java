package com.grubneac.CafeDemoCRM.model;

public enum Permission {
    PERSON_READ("person:read"),
    PERSON_WRITE("person:write"),
    PERSON_UPDATE("person:update"),
    PERSON_DELETE("person:delete");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
