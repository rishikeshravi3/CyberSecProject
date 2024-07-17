package com.xssdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    @Order(1)
    public SecurityFilterChain unsecureFilterChain(HttpSecurity http) throws Exception {
        http
            .requestMatchers().antMatchers("/searchUnsecure")
            .and()
            .headers()
                .contentSecurityPolicy("default-src 'self'; script-src 'self' 'unsafe-inline' https://cdnjs.cloudflare.com; style-src 'self' 'unsafe-inline' https://fonts.googleapis.com https://cdnjs.cloudflare.com; font-src 'self' https://fonts.gstatic.com https://cdnjs.cloudflare.com; img-src 'self' https://picsum.photos https://fastly.picsum.photos/; connect-src 'self' http://localhost:8081")
            .and()
            .xssProtection().block(true)
            .and()
            .contentTypeOptions()
            .and()
            .and()
            .csrf().disable();

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain secureFilterChain(HttpSecurity http) throws Exception {
        http
            .requestMatchers().antMatchers("/**")
            .and()
            .headers()
                .contentSecurityPolicy("default-src 'self'; script-src 'self' https://cdnjs.cloudflare.com; style-src 'self' https://fonts.googleapis.com https://cdnjs.cloudflare.com; font-src 'self' https://fonts.gstatic.com https://cdnjs.cloudflare.com; img-src 'self' https://picsum.photos https://fastly.picsum.photos/;")
            .and()
            .xssProtection().block(true)
            .and()
            .contentTypeOptions()
            .and()
            .and()
            .csrf();

        return http.build();
    }
}