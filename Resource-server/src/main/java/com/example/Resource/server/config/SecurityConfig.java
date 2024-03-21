package com.example.Resource.server.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .oauth2ResourceServer(
                        rs -> rs.jwt(Customizer.withDefaults())
                );

        http
                .authorizeHttpRequests(
                        auth -> auth.anyRequest().authenticated()
                );

        http
                .csrf(c -> c.disable()
                );
        return http.build();
    }
}
