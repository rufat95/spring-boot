package com.rufat.spring_security_jwt_example.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Component
@AllArgsConstructor
public class JwtService {

    private final String SECRET_KEY =
            "rufatrufatrufatrufatrufatrufatrufatrufatrufatrufatrufatrufatrufa";

    public String findUsername(String token) {
        return exportToken(token, Claims::getSubject);
    }

    private <T> T exportToken(java.lang.String token,
                              Function<Claims, T> claimsTFunction) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build().parseClaimsJws(token).getBody();
        return claimsTFunction.apply(claims);
    }

    private Key getKey(){
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }

    public boolean tokenControl(String jwt, UserDetails userDetails) {
        final String username = findUsername(jwt);
        return (username.equals(userDetails.getUsername()) &&
                !exportToken(jwt, Claims::getExpiration).before(new Date()));
    }

    public String generateToken(UserDetails user) {
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() * 1000 * 60 * 24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
