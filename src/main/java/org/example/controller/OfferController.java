package org.example.controller;

import org.example.entity.Hotel;
import org.example.entity.Offer;
import org.example.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offer")
public class OfferController {
    @Autowired
    OfferService offerService;

    @GetMapping("/all")
    public List<Offer> getAllOffer(){
        return offerService.getAllOffer();
    }
    @GetMapping("/id")
    public Offer findById(int id){
        return offerService.findById(id);
    }
    @DeleteMapping("/delete/id")
    public String deleteOffer(@RequestParam int id){
        return offerService.deleteOffer(id);
    }
    @PostMapping("/create")
    public Offer createOffer(@RequestBody Offer offer){
        return offerService.createOffer(offer);
    }
    @PutMapping("/id")
    public Offer updateOffer(@RequestParam int id,@RequestBody Offer offer){
        return offerService.updateOffer(id,offer);
    }

}
