package org.example.service;

import org.example.entity.Offer;
import org.example.entity.User;
import org.example.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {
    @Autowired
    OfferRepository offerRepository;

    public List<Offer> getAllOffer() {
        return offerRepository.findAllOffer().stream().collect(Collectors.toList());
    }
    public Offer findById(int id) {
        return offerRepository.findById(id).get();
    }
    public String deleteOffer(int id){
        try {
            offerRepository.findById(id).get();
            offerRepository.deleteById(id);
            return "Offer deleted";
        } catch (Exception e){
            return "Offer not found";
        }
    }
    public Offer createOffer(Offer offer){
        return  offerRepository.save(offer);
    }
    public Offer updateOffer(int id, Offer offer){
        Offer existingOffer = offerRepository.findById(id).get();
        existingOffer.setType(offer.getType());
        existingOffer.setCountry(offer.getCountry());
        existingOffer.setNumOfTheDays(offer.getNumOfTheDays());
        existingOffer.setStart(offer.getStart());
        existingOffer.setTransport(offer.getTransport());
        existingOffer.setPrice(offer.getPrice());
        return offerRepository.save(existingOffer);
    }
}
