package az.candyshop.CandyShop.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation
        .authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomFilter customFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(permitSwagger).permitAll()
                    .requestMatchers(permitAll).permitAll()
                    .requestMatchers(permitUser).hasRole("USER")
                    .requestMatchers(permitAdmin).hasRole("ADMIN")
                    .requestMatchers(permitEmployee).hasAnyRole("EMPLOYEE", "ADMIN")
                    .anyRequest().authenticated()
            );
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private final String[] permitSwagger = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui/index.html",
            "/swagger-ui.html"
    };

    private final String[] permitAll = {
            "/v1/auth/otp_change_password",
            "/v1/auth/forget_password",
            "/v1/auth/register",
            "/v1/auth/login",
            "/v1/products/all_products",
            "/a_products_image/**",
            "/a_users_image/**"
    };

    private final String[] permitUser = {
            "/v1/users/upload_image",
            "/v1/orders/create_order",
            "/v1/orders/some_order_by_user_id"
    };

    private final String[] permitAdmin = {
            "/v1/users/upload_image",
            "/v1/users/create_user",
            "/v1/users/{userEmail}",
            "/v1/users/{page}/{size}",
            "/v1/users/find_users/{userRole}",
            "/v1/users/delete_user/{id}",
            "/v1/products/delete_products"
    };

    private final String[] permitEmployee = {
            "/v1/products/uploadFiles",
            "/v1/products/add_products",
            "/v1/products/createProducts",
            "/v1/products/{name}",
            "/v1/products/all_products",
            "/v1/orders/update_order_status",
            "/v1/orders/cancel_order",
            "/v1/orders/some_order_by_user_id",
            "/v1/orders/one_order_by_order_id",
            "/v1/orderItems/get_order_items_by_order_id"
    };
}
