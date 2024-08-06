package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.controller.request.*;
import org.example.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final HotelService hotelService;

    private final UserService userService;

    private final OfferService offerService;

    private final OrderService orderService;

    private final MoneyService moneyService;

    @GetMapping("/hotels")
    public ModelAndView viewHomePageHotel(Model model) {
        ModelAttributes.hotelAttributes(model);
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
        ModelAttributes.offerAttributes(model, offerService, hotelService);
        return new ModelAndView("offers", "offers", offerService.getAllOffer());
    }

    @GetMapping("/orders")
    public ModelAndView viewHomePageOrders(Model model) {
        ModelAttributes.orderAttributes(model, orderService, userService);
        ModelAttributes.offerAttributes(model, offerService, hotelService);
        return new ModelAndView("orders", "orders", orderService.getAllOrder());
    }

    @GetMapping("/moneys")
    public ModelAndView viewHomePageMoney(Model model) {
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        return new ModelAndView("money");
    }

    @GetMapping("/login")
    public ModelAndView viewHomePageLogin(Model model) {
        model.addAttribute("user", new UserRequest());
        return new ModelAndView("login");
    }

    @GetMapping("/menu")
    public ModelAndView viewHomePageMenu(Model model) {
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        return new ModelAndView("menu");
    }

    @GetMapping("/exception")
    public ModelAndView viewHomePageException(Model model) {
        return new ModelAndView("exceptionPage");
    }

}
