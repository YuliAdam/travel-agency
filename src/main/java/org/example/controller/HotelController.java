package org.example.controller;

import org.example.controller.request.HotelRequest;
import org.example.controller.response.HotelResponse;
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

    @GetMapping
    public List<HotelResponse> getAllHotel(){
        return hotelService.getAllHotel();
    }
    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable Long id){
        return hotelService.deleteHotel(id);
    }
    @PostMapping
    public HotelResponse createHotel(@RequestBody HotelRequest hotel){
        return hotelService.createHotel(hotel);
    }
    @PutMapping("/{id}")
    public HotelResponse updateHotel(@PathVariable Long id,@RequestBody HotelRequest hotel){
        return hotelService.updateHotel(id,hotel);
    }
}
