package com.example.hotel.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "api_keys")
public class ApiKey {

    @Id
    private String id;
    private String keyValue; // e.g. "SUPER-SECRET-DEV-KEY-123"
    private String clientName; // e.g. "Hotel Mobile App"
    private boolean active; // true / false

    public ApiKey() {}

    public ApiKey(String keyValue, String clientName, boolean active) {
        this.keyValue = keyValue;
        this.clientName = clientName;
        this.active = active;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getKeyValue() { return keyValue; }
    public void setKeyValue(String keyValue) { this.keyValue = keyValue; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
