package com.example.hotel.repository;

import com.example.hotel.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {

    // Custom Query 1: Room status එක අනුව filter කිරීම (e.g., AVAILABLE)
    List<Room> findByStatusIgnoreCase(String status);

    // Custom Query 2: Maximum price එකට වඩා අඩු හෝ සමාන කාමර සොයා ගැනීම
    List<Room> findByPricePerNightLessThanEqual(double maxPrice);
}