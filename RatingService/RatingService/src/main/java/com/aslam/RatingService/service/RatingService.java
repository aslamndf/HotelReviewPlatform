package com.aslam.RatingService.service;

import com.aslam.RatingService.model.Rating;

import java.util.List;
import java.util.UUID;

public interface RatingService {

    Rating createRating(Rating rating);

    List<Rating> getAllRatings();

    List<Rating> getRatingsByUserId(UUID userId);

    List<Rating> getRatingsByHotelId(UUID hotelId);
}
