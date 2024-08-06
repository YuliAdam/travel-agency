package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.controller.request.OfferRequest;
import org.example.controller.request.OrdersRequest;
import org.example.entity.characteristic.Country;
import org.example.entity.characteristic.Transport;
import org.example.entity.characteristic.Type;
import org.example.service.HotelService;
import org.example.service.MoneyService;
import org.example.service.OfferService;
import org.example.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@RestController
@RequestMapping("/offer")
@Validated
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    private final HotelService hotelService;

    private final UserService userService;

    private final MoneyService moneyService;

    @GetMapping("/search")
    public ModelAndView getOffers(Model model, @RequestParam(required = false) String paramtr,
                                  @RequestParam(required = false, defaultValue = "start") String sort,
                                  @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                  @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        ModelAttributes.paginationAttributes(model, paramtr, sort, pageNumber, pageSize);
        ModelAttributes.offerAttributes(model, offerService, hotelService);
        model.addAttribute("order", new OrdersRequest());
        return new ModelAndView("offers", "offers", offerService.findOffers(paramtr, sort, pageNumber, pageSize));
    }

    @GetMapping("/{id}/redirect/update")
    public ModelAndView findById(@PathVariable Long id, Model model) {
        ModelAttributes.offerAttributes(model, offerService, hotelService);
        return new ModelAndView("offer", "offer", offerService.findById(id));
    }

    @GetMapping("/redirect/create")
    public ModelAndView getOffer(Model model) {
        ModelAttributes.offerAttributes(model, offerService, hotelService);
        return new ModelAndView("addOffer");
    }

    @DeleteMapping("/{id}/delete")
    public ModelAndView deleteOffer(@PathVariable Long id, Model model,
                                    @RequestParam(required = false) String paramtr,
                                    @RequestParam(required = false, defaultValue = "start") String sort,
                                    @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                    @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        ModelAttributes.paginationAttributes(model, paramtr, sort, pageNumber, pageSize);
        offerService.deleteOffer(id);
        ModelAttributes.offerAttributes(model, offerService, hotelService);
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        return new ModelAndView("offers", "offers", offerService.findOffers(paramtr, sort, pageNumber, pageSize));
    }

    @PostMapping("/create")
    public ModelAndView createOffer(@ModelAttribute("offer") @Valid OfferRequest offerRequest, Model model,
                                    @RequestParam(required = false) String paramtr,
                                    @RequestParam(required = false, defaultValue = "start") String sort,
                                    @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                    @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        paramtr = offerRequest.getCountry().toString();
        ModelAttributes.paginationAttributes(model, paramtr, sort, pageNumber, pageSize);
        offerService.createOffer(offerRequest);
        ModelAttributes.offerAttributes(model, offerService, hotelService);
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        model.addAttribute("date", LocalDate.now());
        return new ModelAndView("offers", "offers", offerService.findOffers(paramtr, sort, pageNumber, pageSize));
    }

    @PutMapping("/update")
    public ModelAndView updateOffer(@ModelAttribute("offer") @Valid OfferRequest offerRequest, Model model,
                                    @RequestParam(required = false) String paramtr,
                                    @RequestParam(required = false, defaultValue = "start") String sort,
                                    @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                    @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        paramtr = offerRequest.getCountry().toString();
        ModelAttributes.paginationAttributes(model, paramtr, sort, pageNumber, pageSize);
        offerService.updateOffer(offerRequest.getId(), offerRequest);
        ModelAttributes.offerAttributes(model, offerService, hotelService);
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        model.addAttribute("date", LocalDate.now());
        return new ModelAndView("offers", "offers", offerService.findOffers(paramtr, sort, pageNumber, pageSize));
    }
}
