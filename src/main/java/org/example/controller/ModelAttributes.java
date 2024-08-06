package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.controller.request.HotelRequest;
import org.example.controller.request.MoneyRequest;
import org.example.controller.request.OfferRequest;
import org.example.controller.request.OrdersRequest;
import org.example.entity.characteristic.Country;
import org.example.entity.characteristic.Transport;
import org.example.entity.characteristic.Type;
import org.example.service.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

@RequiredArgsConstructor
public class ModelAttributes {

    // constructor x service
    // folder attributes

    public static void paginationAttributes(Model model, String paramtr, String sort, Integer pageNumber, Integer pageSize) {
        model.addAttribute("paramtr", paramtr);
        model.addAttribute("sort", sort);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
    }

    public static void moneyAttributes(Model model, MoneyService moneyService, UserService userService) {
        model.addAttribute("moneyByCurrentUserId", moneyService.findByUserId(userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        model.addAttribute("currentUserId", userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        model.addAttribute("money", new MoneyRequest());
    }

    public static void hotelAttributes(Model model) {
        model.addAttribute("countries", Country.values());
        model.addAttribute("hotel", new HotelRequest());
    }

    public static void offerAttributes(Model model, OfferService offerService, HotelService hotelService) {
        model.addAttribute("types", Type.values());
        model.addAttribute("transports", Transport.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("offer", new OfferRequest());
        model.addAttribute("hotelId", offerService.getAllHotelId());
        model.addAttribute("hotels", hotelService.getAllHotel());
        model.addAttribute("offers", offerService.getAllOffer());
    }

    public static void orderAttributes(Model model, OrderService orderService, UserService userService) {
        model.addAttribute("orderByCurrentUserId", orderService.findOrderByUserId(userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("orders", orderService.getAllOrder());
        model.addAttribute("order", new OrdersRequest());
    }
}
