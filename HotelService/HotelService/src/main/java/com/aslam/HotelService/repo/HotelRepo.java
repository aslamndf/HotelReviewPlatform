package com.aslam.HotelService.repo;

import com.aslam.HotelService.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HotelRepo extends JpaRepository<Hotel, UUID> {
}
