package com.example.hotel.controller;

import com.example.hotel.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class ApiKeyController {

    @Autowired
    private ApiKeyService apiKeyService;

    // Test
    @GetMapping("/verify-key")
    public String verifyApiKey(@RequestHeader("X-API-KEY") String apiKey) {
        apiKeyService.validateApiKey(apiKey);
        return "API Key is valid and active!";
    }
}