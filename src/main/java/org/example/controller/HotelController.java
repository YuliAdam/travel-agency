package org.example.controller;

import org.example.controller.request.HotelRequest;
import org.example.entity.Hotel;
import org.example.entity.characteristic.Country;
import org.example.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Stack;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelService hotelService;

    @GetMapping("/search")
    public ModelAndView getHotels(Model model, @RequestParam(required = false, defaultValue = "") String paramtr,
                                  @RequestParam(required = false, defaultValue = "name") String sort,
                                  @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                  @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        model.addAttribute("paramtr", paramtr);
        model.addAttribute("sort", sort);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("countries", Country.values());
        return new ModelAndView("hotels", "hotels", hotelService.findHotels(paramtr, sort, pageNumber, pageSize));
    }

    @GetMapping("/get")
    public ModelAndView getAllHotel(Model model) {
        model.addAttribute("countries", Country.values());
        return new ModelAndView("hotels", "hotels", hotelService.getAllHotel());
    }

    @GetMapping("/redirect/create")
    public ModelAndView getHotel(Model model) {
        model.addAttribute("countries", Country.values());
        model.addAttribute("hotel", new HotelRequest());
        return new ModelAndView("addHotel");
    }

    @GetMapping(value = "/{id}/redirect/update")
    public ModelAndView getHotel(@PathVariable Long id, Model model) {
        model.addAttribute("countries", Country.values());
        return new ModelAndView("hotel", "hotel", hotelService.getHotel(id));
    }

    @DeleteMapping("/{id}/delete")
    public ModelAndView deleteHotel(@PathVariable Long id, Model model,
                                    @RequestParam(required = false, defaultValue = "") String paramtr,
                                    @RequestParam(required = false, defaultValue = "name") String sort,
                                    @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                    @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        hotelService.deleteHotel(id);
        model.addAttribute("paramtr", paramtr);
        model.addAttribute("sort", sort);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("countries", Country.values());
        model.addAttribute("hotel", new HotelRequest());
        return new ModelAndView("hotels", "hotels", hotelService.findHotels(paramtr, sort, pageNumber, pageSize));
    }

    @PostMapping("/create")
    public ModelAndView createHotel(@ModelAttribute("hotel") HotelRequest hotel, Model model,
                                    @RequestParam(required = false, defaultValue = "") String paramtr,
                                    @RequestParam(required = false, defaultValue = "name") String sort,
                                    @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                    @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        hotelService.createHotel(hotel);
        paramtr = hotel.getName();
        model.addAttribute("paramtr", paramtr);
        model.addAttribute("sort", sort);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("countries", Country.values());
        model.addAttribute("hotel", new HotelRequest());
        return new ModelAndView("hotels", "hotels", hotelService.findHotels(paramtr, sort, pageNumber, pageSize));
    }

    @PutMapping("/update")
    public ModelAndView updateHotel(@ModelAttribute("hotel") HotelRequest hotel, Model model,
                                    @RequestParam(required = false, defaultValue = "") String paramtr,
                                    @RequestParam(required = false, defaultValue = "name") String sort,
                                    @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                    @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        hotelService.updateHotel(hotel.getId(), hotel);
        paramtr = hotel.getName();
        model.addAttribute("paramtr", paramtr);
        model.addAttribute("sort", sort);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("countries", Country.values());
        model.addAttribute("hotel", new HotelRequest());
        return new ModelAndView("hotels", "hotels", hotelService.findHotels(paramtr, sort, pageNumber, pageSize));
    }
}
