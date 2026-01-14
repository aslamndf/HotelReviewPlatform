package com.aslam.userService.exceptions;

public class resourceNotFoundException extends RuntimeException{
    public resourceNotFoundException() {
        super("Resource Not Found on Server");
    }

    public resourceNotFoundException(String message) {
        super(message);
    }
}
