package com.example.hotel.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rooms")
public class Room {
    
    @Id
    private String id;
    private String roomNumber;
    private String roomType; // SINGLE, DOUBLE, DELUXE
    private double pricePerNight;
    private String status; // AVAILABLE, BOOKED, MAINTENANCE
    private int capacity;

    public Room() {}

    public Room(String roomNumber, String roomType, double pricePerNight, String status, int capacity) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.status = status;
        this.capacity = capacity;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }

    public double getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(double pricePerNight) { this.pricePerNight = pricePerNight; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
}