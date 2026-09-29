package com.example.hotel.repository;

import com.example.hotel.model.ApiKey;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiKeyRepository extends MongoRepository<ApiKey, String> {
    
    // Key string එකෙන් සොයා ගෙන active ද යන්න බලන Query Method එක
    Optional<ApiKey> findByKeyValueAndActiveTrue(String keyValue);
}
