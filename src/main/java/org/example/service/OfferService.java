package org.example.service;

import org.example.entity.Offer;
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
        return offerRepository.findAllOffer1().stream().collect(Collectors.toList());
    }
}
