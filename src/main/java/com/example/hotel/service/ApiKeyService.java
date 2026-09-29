package com.example.hotel.service;

import com.example.hotel.exception.UnauthorizedException;
import com.example.hotel.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiKeyService {

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    public void validateApiKey(String apiKey) {
        // Debug Log
        System.out.println("Received API Key from Postman: [" + apiKey + "]");

        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new UnauthorizedException("Unauthorized: Missing X-API-KEY header!");
        }

        apiKeyRepository.findByKeyValueAndActiveTrue(apiKey.trim())
                .orElseThrow(() -> new UnauthorizedException("Unauthorized: Invalid or Expired API Key!"));
    }
}

