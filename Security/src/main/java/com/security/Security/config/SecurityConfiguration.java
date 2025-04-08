package com.security.Security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final CustomFilterChain customFilterChain;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // CSRF müdafiəsi aktiv edilir və CSRF token-ları cookie-də saxlanılır
            .csrf(AbstractHttpConfigurer::disable)
            .cors(Customizer.withDefaults())
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
            .authorizeHttpRequests(auth -> auth // HTTP əsaslı təhlükəsizlik tədbirləri
                .requestMatchers("/public/**", "/auth/login", "/auth/register")
                        .permitAll()
                .requestMatchers(permitSwagger).permitAll()
                // Public endpointlər
                .requestMatchers("/admin/**").hasRole("ADMIN") // Admin endpointləri
                .requestMatchers("/employees/**").hasRole("EMPLOYEE")// User endpointləri
                .requestMatchers("/users/**").hasRole("USER")
                .requestMatchers(permitAdmin).hasRole("ADMIN")// User endpointləri
                .anyRequest().authenticated() // Qalan bütün sorğular üçün
                // autentifikasiya tələb olunur
            );

        return http.build();
    }
    private final String[] permitAdmin = {
        "/employee/index",
        "/employee/dashboard"
    };

    private final String[] permitSwagger = {
            "v3/api-docs/",
            "v3/api-docs.yanl",
            "swagger-ui/",
            "swagger-ui.html"
    };

}
