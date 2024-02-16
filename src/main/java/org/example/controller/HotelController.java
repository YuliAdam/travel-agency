package org.example.controller;

import org.example.entity.Admin;
import org.example.entity.Hotel;
import org.example.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelService hotelService;

    @GetMapping("/all")
    public List<Hotel> getAllHotel(){
        return hotelService.getAllHotel();
    }
    @DeleteMapping("/delete/id")
    public String deleteHotel(@RequestParam int id){
        return hotelService.deleteHotel(id);
    }
    @PostMapping("/create")
    public Hotel createHotel(@RequestBody Hotel hotel){
        return hotelService.createHotel(hotel);
    }
    @PutMapping("/id")
    public Hotel updateHotel(@RequestParam int id,@RequestBody Hotel hotel){
        return hotelService.updateHotel(id,hotel);
    }
}
