package com.folksdev.jwt_token.requests;

import com.folksdev.jwt_token.enums.UserRole;

import java.util.Set;

public record CreateUserRequests (
        String name,
        String username,
        String password,
        Set<UserRole> authorities
){
}
