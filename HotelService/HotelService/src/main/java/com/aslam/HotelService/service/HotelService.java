package com.aslam.HotelService.service;

import com.aslam.HotelService.model.Hotel;

import java.util.List;
import java.util.UUID;

public interface HotelService {

    //create hotel
    Hotel createHotel(Hotel hotel);

    //get all hotels
    List<Hotel> getAllHotels();

    //get single hotel
    Hotel getHotelById(UUID hotelId);

    //delete hotel
    void deleteHotel(UUID hotelId);

    //update hotel
    Hotel updateHotel(Hotel hotel, UUID hotelId);
}
