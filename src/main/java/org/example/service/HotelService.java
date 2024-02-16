package org.example.service;

import org.example.entity.Hotel;
import org.example.entity.User;
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
    public String deleteHotel(int id){
        try {
            hotelRepository.findById(id).get();
            hotelRepository.deleteById(id);
            return "Hotel deleted";
        } catch (Exception e){
            return "Hotel not found";
        }
    }
    public Hotel createHotel(Hotel hotel){
        return  hotelRepository.save(hotel);
    }
    public Hotel updateHotel(int id, Hotel hotel){
        Hotel existingHotel = hotelRepository.findById(id).get();
        existingHotel.setCountry(hotel.getCountry());
        existingHotel.setName(hotel.getName());
        existingHotel.setStar(hotel.getStar());
        existingHotel.setGuest(hotel.getGuest());
        existingHotel.setBreakfast(hotel.isBreakfast());
        existingHotel.setLunch(hotel.isLunch());
        existingHotel.setDinner(hotel.isDinner());
        existingHotel.setTv(hotel.isTv());
        existingHotel.setAirConditioner(hotel.isAirConditioner());
        existingHotel.setBalcony(hotel.isTv());
        return hotelRepository.save(existingHotel);
    }

}
