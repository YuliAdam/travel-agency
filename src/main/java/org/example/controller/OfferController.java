package org.example.controller;

import org.example.entity.Offer;
import org.example.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OfferController {
    @Autowired
    OfferService offerService;

    @GetMapping("/allOffer")
    public List<Offer> getAllOffer(){
        return offerService.getAllOffer();
    }
}
