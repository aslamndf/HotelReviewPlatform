package com.aslam.HotelService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {
    @GetMapping
    public ResponseEntity<List<String>> getAllStaffs() {
        List<String> list = List.of("Alice", "Bob", "Charlie");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
