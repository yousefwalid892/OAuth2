package com.example.Authorization.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain AppSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> {
                    auth.anyRequest().permitAll();
                });
        http.formLogin(Customizer.withDefaults());
        http.csrf(c -> c.disable());
        return http.build();
                }
    @Bean
    public PasswordEncoder passwordEncoder() {
      //  return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance(); //just for testing (not better security for production)
    }

}
