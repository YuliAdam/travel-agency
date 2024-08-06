package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.controller.request.OfferRequest;
import org.example.controller.response.HotelResponse;
import org.example.controller.response.OfferResponse;
import org.example.entity.Offer;
import org.example.exception.EntityNotExistException;
import org.example.repository.OfferRepository;
import org.example.utils.Messages;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;

    public List<OfferResponse> getAllOffer() {
        return offerRepository.findAllOffer().stream().map(OfferResponse::new).toList();
    }

    public List<Long> getAllHotelId() {
        return offerRepository.findAllHotelId();
    }

    public List<OfferResponse> findOffers(String paramtr, String sort, Integer pageNumber, Integer pageSize) {
        PageRequest page = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, sort));
        return (paramtr == null || paramtr.isBlank())
                ? offerRepository.findOffer(page).stream().map(OfferResponse::new).toList()
                : offerRepository.findOffer(paramtr.trim(), page).stream().map(OfferResponse::new).toList();
    }

    public OfferResponse findById(Long id) {
        return new OfferResponse(offerRepository.findById(id).orElseThrow(() -> new EntityNotExistException(String.format(Messages.OFFER_NOT_EXIST, id))));
    }

    public void deleteOffer(Long id) {
        offerRepository.findById(id).orElseThrow(() -> new EntityNotExistException(String.format(Messages.OFFER_NOT_EXIST, id)));
        offerRepository.deleteById(id);
    }

    public OfferResponse createOffer(OfferRequest offerRequest) {
        Offer offer = new Offer(offerRequest.getType(), offerRequest.getCountry(), offerRequest.getNumOfTheDays(), offerRequest.getStart(), offerRequest.getTransport(),
                offerRepository.findHotelById(offerRequest.getHotelId()), offerRequest.getPrice());
        return new OfferResponse(offerRepository.save(offer));
    }

    public OfferResponse updateOffer(Long id, OfferRequest offerRequest) {
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
