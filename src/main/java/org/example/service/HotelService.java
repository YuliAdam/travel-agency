package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.controller.request.HotelRequest;
import org.example.controller.response.HotelResponse;
import org.example.entity.Hotel;
import org.example.exception.EntityNotExistException;
import org.example.repository.HotelRepository;
import org.example.utils.Messages;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public List<HotelResponse> getAllHotel() {
        return hotelRepository.findAllHotel().stream().map(HotelResponse::new).toList();
    }

    public HotelResponse getHotel(Long id) {
        return new HotelResponse(hotelRepository.findById(id).orElseThrow(() -> new EntityNotExistException(String.format(Messages.HOTEL_NOT_EXIST, id))));
    }

    public List<HotelResponse> findHotels(String paramtr, String sort, Integer pageNumber, Integer pageSize) {
        PageRequest page = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, sort));
        return (paramtr == null || paramtr.isBlank())
                ? (hotelRepository.findHotel(page).stream().map(HotelResponse::new).toList())
                : (hotelRepository.findHotel(paramtr.trim(), page).stream().map(HotelResponse::new).toList());
    }

    public void deleteHotel(Long id) {
        hotelRepository.findById(id).orElseThrow(() -> new EntityNotExistException(String.format(Messages.HOTEL_NOT_EXIST, id)));
        hotelRepository.deleteById(id);
    }

    public void deleteAllHotel() {
        hotelRepository.deleteAll();
    }

    public HotelResponse createHotel(HotelRequest hotelRequest) {
        Hotel hotel = new Hotel(hotelRequest.getCountry(), hotelRequest.getName(), hotelRequest.getStar(), hotelRequest.getGuest(), hotelRequest.getBreakfast(),
                hotelRequest.getLunch(), hotelRequest.getDinner(), hotelRequest.getTv(), hotelRequest.getAirConditioner(), hotelRequest.getBalcony());
        return new HotelResponse(hotelRepository.save(hotel));
    }

    public HotelResponse updateHotel(Long id, HotelRequest hotel) {
        Hotel existingHotel = hotelRepository.findById(id).orElseThrow(() -> new EntityNotExistException(String.format(Messages.HOTEL_NOT_EXIST, id)));
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
