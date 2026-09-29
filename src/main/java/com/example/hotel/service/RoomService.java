package com.example.hotel.service;

import com.example.hotel.model.Room;
import com.example.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

   
    public String checkCapacitySuitability(int guests, int requestedCapacity) {
        if (guests > requestedCapacity) {
            return "Warning: " + guests + " guests exceed room capacity of " + requestedCapacity + "! Please book extra rooms.";
        } else if (guests <= 0) {
            return "Invalid request: Guest count must be at least 1.";
        } else {
            return "Success: Room capacity of " + requestedCapacity + " is suitable for " + guests + " guests.";
        }
    }

 
    public List<Room> getRoomsByStatus(String status) {
        return roomRepository.findByStatusIgnoreCase(status.trim());
    }

    
    public List<Room> getRoomsByMaxPrice(double maxPrice) {
        return roomRepository.findByPricePerNightLessThanEqual(maxPrice);
    }
}