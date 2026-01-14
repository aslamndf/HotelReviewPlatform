package com.aslam.RatingService.repo;

import com.aslam.RatingService.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RatingRepo extends JpaRepository<Rating, UUID> {

    List<Rating> findByUserId(UUID userId);

    List<Rating> findByHotelId(UUID hotelId);
}
