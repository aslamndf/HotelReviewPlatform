package com.aslam.HotelService.impl;

import com.aslam.HotelService.exceptions.resourceNotFoundException;
import com.aslam.HotelService.model.Hotel;
import com.aslam.HotelService.repo.HotelRepo;
import com.aslam.HotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepo hotelRepo;

    @Override
    public Hotel createHotel(Hotel hotel) {
        return hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel getHotelById(UUID hotelId) {
        return hotelRepo.findById(hotelId)
                .orElseThrow(() -> new resourceNotFoundException("Hotel not found"));
    }

    @Override
    public void deleteHotel(UUID hotelId) {
        hotelRepo.deleteById(hotelId);
    }

    @Override
    public Hotel updateHotel(Hotel hotel, UUID hotelId) {
        return hotelRepo.save(hotel);
    }
}
