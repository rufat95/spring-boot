package com.folksdev.security.basic_auth.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_MOD,
    ROLE_FSK;


    @Override
    public String getAuthority() {
        return name();
    }

}
