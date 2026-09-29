package com.example.hotel.config;

import com.example.hotel.service.ApiKeyService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    @Autowired
    private ApiKeyService apiKeyService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Swagger documentation සදහා API Key එක පරීක්ෂා නොකර Access ලබා දීම
        if (path.contains("/swagger-ui") || path.contains("/v3/api-docs")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Request Header එකෙන් API Key එක ලබා ගැනීම
        String apiKey = request.getHeader("X-API-KEY");

        try {
            // ApiKeyService එක මගින් MongoDB හි Key එක Check කිරීම
            apiKeyService.validateApiKey(apiKey);
            filterChain.doFilter(request, response); // Valid නම් request එක controller එකට යැවීම
        } catch (Exception e) {
            // Invalid නම් 401 Unauthorized Response එකක් යැවීම
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}