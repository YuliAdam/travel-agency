package org.example.controller;

import org.example.controller.request.HotelRequest;
import org.example.controller.request.OfferRequest;
import org.example.controller.request.UserRequest;
import org.example.controller.response.HotelResponse;
import org.example.entity.characteristic.Country;
import org.example.entity.characteristic.Transport;
import org.example.entity.characteristic.Type;
import org.example.service.CustomUserDetailsService;
import org.example.service.HotelService;
import org.example.service.OfferService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Map;

@Controller
public class PageController {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private UserService userService;
    @Autowired
    private OfferService offerService;

    @GetMapping("/hotels")
    public ModelAndView viewHomePageHotel(Model model) {
        model.addAttribute("countries", Country.values());
        model.addAttribute("hotel", new HotelRequest());
        model.addAttribute("hotels", hotelService.getAllHotel());
        return new ModelAndView("hotels", "hotels", hotelService.getAllHotel());
    }

    @GetMapping("/users")
    public ModelAndView viewHomePageUser(Model model) {
        model.addAttribute("user", new UserRequest());
        model.addAttribute("users", userService.getAllUser());
        return new ModelAndView("users", "users", userService.getAllUser());
    }

    @GetMapping("/offers")
    public ModelAndView viewHomePageOffer(Model model) {
        model.addAttribute("types", Type.values());
        model.addAttribute("transports", Transport.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("offer", new OfferRequest());
        model.addAttribute("offers", offerService.getAllOffer());
        model.addAttribute("hotels", hotelService.getAllHotel());
        return new ModelAndView("offers", "offers", offerService.getAllOffer());
    }
    @GetMapping("/login")
    public ModelAndView viewHomePageLogin(Model model) {
        model.addAttribute("user", new UserRequest());
        return new ModelAndView("login");
    }
    @GetMapping("/menu")
    public ModelAndView viewHomePageMenu(Model model) {
        return new ModelAndView("menu");
    }


}
