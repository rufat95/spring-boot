package com.folksdev.security.basic_auth.requests;

import com.folksdev.security.basic_auth.enums.UserRole;

import java.util.Set;

public record CreateUserRequest(
        String name,
        String username,
        String password,
        Set<UserRole> authorities
) {
}
