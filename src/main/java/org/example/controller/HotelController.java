package org.example.controller;

import org.example.entity.Hotel;
import org.example.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {
    @Autowired
    HotelService hotelService;

    @GetMapping("/allHotel")
    public List<Hotel> getAllHotel(){
        return hotelService.getAllHotel();
    }
}
