package com.taguhyung.workhub.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/projects", "/api/projects/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/projects").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/projects/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/projects/**").permitAll()
                        .anyRequest().authenticated())
                .build();
    }
}