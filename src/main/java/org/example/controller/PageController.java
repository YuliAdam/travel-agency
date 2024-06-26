package org.example.controller;

import org.example.controller.request.*;
import org.example.controller.response.HotelResponse;
import org.example.entity.characteristic.Country;
import org.example.entity.characteristic.Transport;
import org.example.entity.characteristic.Type;
import org.example.service.*;
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
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private MoneyService moneyService;

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
    @GetMapping("/orders")
    public ModelAndView viewHomePageOrders(Model model) {
        model.addAttribute("types", Type.values());
        model.addAttribute("transports", Transport.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("offers", offerService.getAllOffer());
        model.addAttribute("hotels", hotelService.getAllHotel());
        model.addAttribute("orders", ordersService.getAllOrder());
        model.addAttribute("order", new OrdersRequest());
        return new ModelAndView("orders", "orders", ordersService.getAllOrder());
    }
    @GetMapping("/moneys")
    public ModelAndView viewHomePageMoney(Model model) {
        model.addAttribute("money", new MoneyRequest());
        model.addAttribute("currentUserId",userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        model.addAttribute("moneyByCurrentUserId", moneyService.findByUserId(userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        return new ModelAndView("money");
    }
    @GetMapping("/login")
    public ModelAndView viewHomePageLogin(Model model) {
        model.addAttribute("user", new UserRequest());
        return new ModelAndView("login");
    }
    @GetMapping("/menu")
    public ModelAndView viewHomePageMenu(Model model) {
        model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        model.addAttribute("currentUserId",userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        model.addAttribute("moneyByCurrentUserId", moneyService.findByUserId(userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        return new ModelAndView("menu");
    }
    @GetMapping("/exception")
    public ModelAndView viewHomePageException(Model model) {
        return new ModelAndView("exceptionPage");
    }

}
