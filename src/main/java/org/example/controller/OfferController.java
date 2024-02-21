package org.example.controller;

import org.example.controller.request.OfferRequest;
import org.example.controller.response.OfferResponse;
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

    @GetMapping
    public List<OfferResponse> getAllOffer(){
        return offerService.getAllOffer();
    }
    @GetMapping("/{id}")
    public OfferResponse findById(@PathVariable Long id){
        return offerService.findById(id);
    }
    @DeleteMapping("/{id}")
    public String deleteOffer(@PathVariable Long id){
        return offerService.deleteOffer(id);
    }
    @PostMapping
    public OfferResponse createOffer(@RequestBody OfferRequest offerRequest){
        return offerService.createOffer(offerRequest);
    }
    @PutMapping("/{id}")
    public OfferResponse updateOffer(@PathVariable Long id,@RequestBody OfferRequest offerRequest){
        return offerService.updateOffer(id,offerRequest);
    }

}
