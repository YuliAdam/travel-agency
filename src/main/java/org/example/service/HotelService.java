package org.example.service;

import org.example.controller.request.HotelRequest;
import org.example.controller.response.HotelResponse;
import org.example.entity.Hotel;
import org.example.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class HotelService {
    @Autowired
    HotelRepository hotelRepository;
    public List<HotelResponse> getAllHotel(){
        return hotelRepository.findAllHotel().stream().map(HotelResponse::new).toList();
    }
    public String deleteHotel(Long id){
        try {
            hotelRepository.findById(id).get();
            hotelRepository.deleteById(id);
            return "Hotel deleted";
        } catch (NoSuchElementException e){
            System.out.println("Error. Class HotelService. Hotel not found. "+ e);
            return "Hotel not found."+e;
        }
    }
    public HotelResponse createHotel(HotelRequest hotelRequest){
        try {
        Hotel hotel= new Hotel(hotelRequest.getCountry(),hotelRequest.getName(),hotelRequest.getStar(),hotelRequest.getGuest(),hotelRequest.isBreakfast(),
                hotelRequest.isLunch(),hotelRequest.isDinner(),hotelRequest.isTv(),hotelRequest.isAirConditioner(),hotelRequest.isBalcony());
        return  new HotelResponse(hotelRepository.save(hotel));
        }catch (DataIntegrityViolationException e){
            System.out.println("Error. Class HotelService. The hotel Name cannot be null. "+e);
            return new HotelResponse("The hotel's Name cannot be null. "+e);
        }
    }
    public HotelResponse updateHotel(Long id, HotelRequest hotel){
        try {
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
        existingHotel.setBalcony(hotel.isBalcony());
        return new HotelResponse(hotelRepository.save(existingHotel));
        }catch (NoSuchElementException e){
            System.out.println("Error. Class HotelService. Hotel not found. "+e);
            return new HotelResponse("Hotel not found. "+e);
        }catch (DataIntegrityViolationException e){
            System.out.println("Error. Class HotelService. The hotel Name cannot be null. "+e);
            return new HotelResponse("The hotel's Name cannot be null. "+e);
        }
    }

}
