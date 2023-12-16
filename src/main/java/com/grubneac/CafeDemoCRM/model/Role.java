package com.grubneac.CafeDemoCRM.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.grubneac.CafeDemoCRM.model.Permission.*;

public enum Role {
    USER(Set.of(PERSON_READ)),
    ADMIN(Set.of(PERSON_READ, PERSON_WRITE, PERSON_UPDATE, PERSON_DELETE));

    private final Set<Permission> permissionSet;

    Role(Set<Permission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    private Set<Permission> getPermissionSet() {
        return permissionSet;
    }

    public Set<SimpleGrantedAuthority> getAuthoritiesSet(){
        return getPermissionSet().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
