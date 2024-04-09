package org.example.controller;

import org.example.controller.request.OfferRequest;
import org.example.controller.response.HotelResponse;
import org.example.controller.response.OfferResponse;
import org.example.entity.characteristic.Country;
import org.example.entity.characteristic.Transport;
import org.example.entity.characteristic.Type;
import org.example.repository.HotelRepository;
import org.example.service.HotelService;
import org.example.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/offer")
public class OfferController {
    @Autowired
    OfferService offerService;

    @GetMapping
    public List<OfferResponse> getAllOffer() {
        return offerService.getAllOffer();
    }

    @GetMapping("/{id}/redirect/update")
    public ModelAndView findById(@PathVariable Long id, Model model) {
        model.addAttribute("types", Type.values());
        model.addAttribute("transports", Transport.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("hotels",offerService.getAllHotel());
        model.addAttribute("hotelsName", offerService.getAllHotelName());
        model.addAttribute("offer", new OfferRequest());
        return new ModelAndView("offer", "offer", offerService.findById(id));
    }

    @GetMapping("/redirect/create")
    public ModelAndView getOffer(Model model) {
        model.addAttribute("types", Type.values());
        model.addAttribute("transports", Transport.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("offer", new OfferRequest());
        return new ModelAndView("addOffer");
    }

    @DeleteMapping("/{id}/delete")
    public ModelAndView deleteOffer(@PathVariable Long id, Model model) {
        offerService.deleteOffer(id);
        model.addAttribute("types", Type.values());
        model.addAttribute("transports", Transport.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("offer", new OfferRequest());
        return new ModelAndView("offers", "offers", offerService.getAllOffer());
    }

    @PostMapping("/create")
    public ModelAndView createOffer(@ModelAttribute("offer") OfferRequest offerRequest, Model model) {
        offerService.createOffer(offerRequest);
        model.addAttribute("types", Type.values());
        model.addAttribute("transports", Transport.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("offer", new OfferRequest());
        return new ModelAndView("offer", "offer", offerService.getAllOffer());
    }

    @PutMapping("/update")
    public ModelAndView updateOffer(@ModelAttribute("offer") OfferRequest offerRequest, Model model) {
        offerService.updateOffer(offerRequest.getId(), offerRequest);
        model.addAttribute("types", Type.values());
        model.addAttribute("transports", Transport.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("hotels",offerService.getAllHotel());
        model.addAttribute("hotelsName", offerService.getAllHotelName());
        model.addAttribute("offer", new OfferRequest());
        return new ModelAndView("offer", "offer", offerService.getAllOffer());
    }
}
