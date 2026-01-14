package com.aslam.HotelService.exceptions;

public class resourceNotFoundException extends RuntimeException {
    public resourceNotFoundException(String hotelNotFound) {
        super(hotelNotFound);
    }

    public  resourceNotFoundException() {
        super("Resource Not Found on Server");
    }
}
