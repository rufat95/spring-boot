package com.folksdev.jwt_token.requests;

public record AuthRequest(
        String username,
        String password
) {
}
