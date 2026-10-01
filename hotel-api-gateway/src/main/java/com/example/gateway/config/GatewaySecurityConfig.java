package com.example.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;

@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
            .csrf(csrf -> csrf.disable())
            .exceptionHandling(exception -> exception
                // AJAX / Fetch calls OAuth login එකට redirect නොවී 401 Unauthorized ලබා දීම සඳහා (CORS Error වැළැක්වීමට)
                .authenticationEntryPoint(new HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED))
            )
            .authorizeExchange(exchange -> exchange
                // Public Endpoints සහ OAuth Login Routes
                .pathMatchers("/login/**", "/oauth2/**", "/error", "/public/**").permitAll()
                // Microservice API Endpoints වෙත කෙලින්ම යොමු වීමට ඉඩ දීම (Microservice level එකේ API Key verify වේ)
                .pathMatchers("/api/**").permitAll()
                .anyExchange().authenticated()
            )
            .oauth2Login(oauth2 -> {}); // Guideline requirement: OAuth 2.0 Client Support

        return http.build();
    }
}