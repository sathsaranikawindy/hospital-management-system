package com.example.hotel.controller;

import com.example.hotel.model.Room;
import com.example.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // API 1: Business Logic Endpoint
    @GetMapping("/capacity-check")
    public String checkCapacity(
            @RequestParam int guests,
            @RequestParam int capacity
    ) {
        return roomService.checkCapacitySuitability(guests, capacity);
    }

    // API 2: Filter Rooms by Status
    @GetMapping("/filter/status")
    public List<Room> getRoomsByStatus(@RequestParam String status) {
        return roomService.getRoomsByStatus(status);
    }

    // API 3: Filter Rooms by Max Price
    @GetMapping("/filter/price")
    public List<Room> getRoomsByPrice(@RequestParam double maxPrice) {
        return roomService.getRoomsByMaxPrice(maxPrice);
    }
}