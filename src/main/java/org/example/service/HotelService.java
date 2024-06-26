package org.example.service;

import org.example.controller.request.HotelRequest;
import org.example.controller.response.HotelResponse;
import org.example.entity.Hotel;
import org.example.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class HotelService {
    @Autowired
    HotelRepository hotelRepository;

    public List<HotelResponse> getAllHotel() {
        return hotelRepository.findAllHotel().stream().map(HotelResponse::new).toList();
    }

    public long countHotel() {
        return hotelRepository.countHotel();
    }


    public HotelResponse getHotel(Long id) {
        return new HotelResponse(hotelRepository.findById(id).get());
    }

    public List<HotelResponse> findHotels(String paramtr, String sort, Integer pageNumber, Integer pageSize) {
        PageRequest page = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, sort));
        if (paramtr == null || paramtr.equals("")) {
            return hotelRepository.findHotels(page).stream()
                    .map(HotelResponse::new)
                    .toList();
        } else {
            return hotelRepository.findHotels(paramtr.trim(), page).stream()
                    .map(HotelResponse::new)
                    .toList();
        }
    }

    public String deleteHotel(Long id) {
            hotelRepository.findById(id).get();
            hotelRepository.deleteById(id);
            return "Hotel deleted";
    }

    public HotelResponse createHotel(HotelRequest hotelRequest) {
            Hotel hotel = new Hotel(hotelRequest.getCountry(), hotelRequest.getName(), hotelRequest.getStar(), hotelRequest.getGuest(), hotelRequest.getBreakfast(),
                    hotelRequest.getLunch(), hotelRequest.getDinner(), hotelRequest.getTv(), hotelRequest.getAirConditioner(), hotelRequest.getBalcony());
            return new HotelResponse(hotelRepository.save(hotel));
    }

    public HotelResponse updateHotel(Long id, HotelRequest hotel) {
            Hotel existingHotel = hotelRepository.findById(id).get();
            existingHotel.setCountry(hotel.getCountry());
            existingHotel.setName(hotel.getName());
            existingHotel.setStar(hotel.getStar());
            existingHotel.setGuest(hotel.getGuest());
            existingHotel.setBreakfast(hotel.getBreakfast());
            existingHotel.setLunch(hotel.getLunch());
            existingHotel.setDinner(hotel.getDinner());
            existingHotel.setTv(hotel.getTv());
            existingHotel.setAirConditioner(hotel.getAirConditioner());
            existingHotel.setBalcony(hotel.getBalcony());
            return new HotelResponse(hotelRepository.save(existingHotel));
    }

}
