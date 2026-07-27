package com.zomato.config;

import com.zomato.security.JwtAuthenticationEntryPoint;
import com.zomato.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final JwtAuthenticationEntryPoint entryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .exceptionHandling(ex ->
                        ex.authenticationEntryPoint(entryPoint))

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth

                        // Public APIs
                        .requestMatchers(
                                "/api/auth/**"
                        ).permitAll()

                        // Admin APIs
                        .requestMatchers("/api/admin/**")
                        .hasRole("ADMIN")

                        // Restaurant Owner + Admin
                        .requestMatchers("/api/restaurants/**")
                        .hasAnyRole("ADMIN", "RESTAURANT_OWNER")

                        // Customer + Admin
                        .requestMatchers(
                                "/api/orders/**",
                                "/api/cart/**",
                                "/api/payments/**",
                                "/api/addresses/**",
                                "/api/reviews/**"
                        )
                        .hasAnyRole("CUSTOMER", "ADMIN")

                        // Delivery Partner
                        .requestMatchers("/api/delivery/**")
                        .hasAnyRole("DELIVERY_PARTNER", "ADMIN")

                        // Everything else
                        .anyRequest()
                        .authenticated()
                )

                .httpBasic(Customizer.withDefaults());

        http.addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }
}

