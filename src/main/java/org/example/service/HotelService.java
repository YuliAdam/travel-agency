package org.example.service;

import org.example.entity.Hotel;
import org.example.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    HotelRepository hotelRepository;
    public List<Hotel> getAllHotel(){
        return hotelRepository.findAllHotel().stream().toList();
    }
}
