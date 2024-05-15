package org.example.controller;

import jakarta.validation.Valid;
import org.example.controller.request.OfferRequest;
import org.example.controller.request.OrdersRequest;
import org.example.controller.response.HotelResponse;
import org.example.controller.response.OfferResponse;
import org.example.entity.characteristic.Country;
import org.example.entity.characteristic.Transport;
import org.example.entity.characteristic.Type;
import org.example.repository.HotelRepository;
import org.example.service.HotelService;
import org.example.service.OfferService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/offer")
@Validated
public class OfferController {
    @Autowired
    private OfferService offerService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ModelAndView getOffers(Model model, @RequestParam(required = false, defaultValue = "") String paramtr,
                                  @RequestParam(required = false, defaultValue = "start") String sort,
                                  @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                  @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        model.addAttribute("role",SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        model.addAttribute("currentUserId",userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());

        model.addAttribute("paramtr", paramtr);
        model.addAttribute("sort", sort);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("types", Type.values());
        model.addAttribute("transports", Transport.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("offer", new OfferRequest());
        model.addAttribute("hotelId", offerService.getAllHotelId());
        model.addAttribute("hotels", hotelService.getAllHotel());

        model.addAttribute("order", new OrdersRequest());
        return new ModelAndView("offers", "offers", offerService.findOffers(paramtr, sort, pageNumber, pageSize));
    }

    @GetMapping("/{id}/redirect/update")
    public ModelAndView findById(@PathVariable Long id, Model model) {
        model.addAttribute("types", Type.values());
        model.addAttribute("transports", Transport.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("offer", new OfferRequest());
        model.addAttribute("hotelId", offerService.getAllHotelId());
        return new ModelAndView("offer", "offer", offerService.findById(id));
    }

    @GetMapping("/redirect/create")
    public ModelAndView getOffer(Model model) {
        model.addAttribute("types", Type.values());
        model.addAttribute("transports", Transport.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("hotelId", offerService.getAllHotelId());
        model.addAttribute("offer", new OfferRequest());
        return new ModelAndView("addOffer");
    }

    @DeleteMapping("/{id}/delete")
    public ModelAndView deleteOffer(@PathVariable Long id, Model model,
                                    @RequestParam(required = false, defaultValue = "") String paramtr,
                                    @RequestParam(required = false, defaultValue = "start") String sort,
                                    @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                    @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        model.addAttribute("paramtr", paramtr);
        model.addAttribute("sort", sort);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        offerService.deleteOffer(id);
        model.addAttribute("types", Type.values());
        model.addAttribute("transports", Transport.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("offer", new OfferRequest());

        model.addAttribute("role",SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        model.addAttribute("currentUserId",userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        model.addAttribute("hotels", hotelService.getAllHotel());
        return new ModelAndView("offers", "offers", offerService.findOffers(paramtr, sort, pageNumber, pageSize));
    }

    @PostMapping("/create")
    public ModelAndView createOffer(@ModelAttribute("offer") @Valid OfferRequest offerRequest, Model model,
                                    @RequestParam(required = false, defaultValue = "") String paramtr,
                                    @RequestParam(required = false, defaultValue = "start") String sort,
                                    @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                    @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        paramtr=offerRequest.getCountry().toString();
        model.addAttribute("paramtr", paramtr);
        model.addAttribute("sort", sort);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        offerService.createOffer(offerRequest);
        model.addAttribute("types", Type.values());
        model.addAttribute("transports", Transport.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("hotels", hotelService.getAllHotel());
        model.addAttribute("offer", new OfferRequest());

        model.addAttribute("date", LocalDate.now());
        model.addAttribute("role",SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        model.addAttribute("currentUserId",userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        return new ModelAndView("offers", "offers", offerService.findOffers(paramtr, sort, pageNumber, pageSize));
    }

    @PutMapping("/update")
    public ModelAndView updateOffer(@ModelAttribute("offer") @Valid OfferRequest offerRequest, Model model,
                                    @RequestParam(required = false, defaultValue = "") String paramtr,
                                    @RequestParam(required = false, defaultValue = "start") String sort,
                                    @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                    @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        paramtr=offerRequest.getCountry().toString();
        model.addAttribute("paramtr", paramtr);
        model.addAttribute("sort", sort);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        offerService.updateOffer(offerRequest.getId(), offerRequest);
        model.addAttribute("types", Type.values());
        model.addAttribute("transports", Transport.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("hotelId", offerService.getAllHotelId());
        model.addAttribute("hotels", hotelService.getAllHotel());
        model.addAttribute("offer", new OfferRequest());

        model.addAttribute("date", LocalDate.now());
        model.addAttribute("role",SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        model.addAttribute("currentUserId",userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        return new ModelAndView("offers", "offers", offerService.findOffers(paramtr, sort, pageNumber, pageSize));
    }
}
