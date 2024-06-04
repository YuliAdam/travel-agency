package org.example.service;

import org.example.controller.request.OfferRequest;
import org.example.controller.response.HotelResponse;
import org.example.controller.response.OfferResponse;
import org.example.controller.response.OrdersResponse;
import org.example.entity.Offer;
import org.example.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OfferService {
    @Autowired
    OfferRepository offerRepository;

    public List<OfferResponse> getAllOffer() {
        return offerRepository.findAllOffer().stream().map(OfferResponse::new).toList();
    }
    public List<Long> getAllHotelId() {
        return offerRepository.findAllHotelId();
    }
    public List<HotelResponse> getAllHotel() {
        return offerRepository.findAllHotel().stream().map(HotelResponse::new).toList();
    }
    public HotelResponse getHotelById(Long hotelId){
        return new HotelResponse( offerRepository.findHotelById(hotelId));
    }
    public List<OfferResponse> findOffers(String paramtr, String sort, Integer pageNumber, Integer pageSize) {
        PageRequest page = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, sort));
        if (paramtr == null || paramtr.equals("")) {
            return offerRepository.findOffer(page).stream()
                    .map(OfferResponse::new)
                    .toList();
        } else return offerRepository.findOffer(paramtr.trim(), page).stream()
                .map(OfferResponse::new)
                .toList();
    }
    public OfferResponse findById(Long id) {
        return new OfferResponse(offerRepository.findById(id).get());
    }
    public String deleteOffer(Long id){
            offerRepository.findById(id).get();
            offerRepository.deleteById(id);
            return "Offer deleted";
    }
    public OfferResponse createOffer(OfferRequest offerRequest){
            Offer offer=new Offer(offerRequest.getType(),offerRequest.getCountry(),offerRequest.getNumOfTheDays(),offerRequest.getStart(),offerRequest.getTransport(),
                    offerRepository.findHotelById(offerRequest.getHotelId()),offerRequest.getPrice());
        return  new OfferResponse(offerRepository.save(offer));
    }
    public OfferResponse updateOffer(Long id, OfferRequest offerRequest){
            Offer existingOffer = offerRepository.findById(id).get();
        existingOffer.setType(offerRequest.getType());
        existingOffer.setCountry(offerRequest.getCountry());
        existingOffer.setNumOfTheDays(offerRequest.getNumOfTheDays());
        existingOffer.setStart(offerRequest.getStart());
        existingOffer.setTransport(offerRequest.getTransport());
        existingOffer.setHotel(offerRepository.findHotelById(offerRequest.getHotelId()));
        existingOffer.setPrice(offerRequest.getPrice());
        return new OfferResponse(offerRepository.save(existingOffer));
    }
}
