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
    Stack<Integer> pageNumbers = new Stack<>();
    Stack<Integer> pageSizes = new Stack<>();
    Stack<String> sorts = new Stack<>();
    Stack<String> names = new Stack<>();

    @GetMapping("/search")
    public ModelAndView getHotelByName(Model model, @RequestParam(required = false) String name,
                                       @RequestParam(required = false) String sort,
                                       @RequestParam(required = false) Integer pageNumber,
                                       @RequestParam(required = false) Integer pageSize) {
        if (pageNumbers.isEmpty()) pageNumbers.push(0);
        if (pageNumber != null) pageNumbers.push(pageNumber);
        if (pageSizes.isEmpty()) pageSizes.push(5);
        if (pageSize != null) pageSizes.push(pageSize);
        if (sorts.isEmpty()) sorts.push("id");
        if (sort != null) sorts.push(sort);
        if (names.isEmpty()) names.push("");
        if (name != null) names.push(name);
        int pages = hotelService.getAllHotel().size() / pageSizes.peek();
        if (hotelService.countHotel() % pageSizes.peek() != 0) pages++;
        model.addAttribute("pages", pages);
        model.addAttribute("countries", Country.values());
        return new ModelAndView("hotels", "hotels", hotelService.getHotelByName(names.peek(), sorts.peek(), pageNumbers.peek(), pageSizes.peek()));
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
    public ModelAndView deleteHotel(@PathVariable Long id, Model model) {
        hotelService.deleteHotel(id);
        model.addAttribute("countries", Country.values());
        model.addAttribute("hotel", new HotelRequest());
        return new ModelAndView("hotels", "hotels", hotelService.getAllHotel());
    }

    @PostMapping("/create")
    public ModelAndView createHotel(@ModelAttribute("hotel") HotelRequest hotel, Model model) {
        hotelService.createHotel(hotel);
        model.addAttribute("countries", Country.values());
        model.addAttribute("hotel", new HotelRequest());
        return new ModelAndView("hotels", "hotels", hotelService.getAllHotel());
    }

    @PutMapping("/update")
    public ModelAndView updateHotel(@ModelAttribute("hotel") HotelRequest hotel, Model model) {
        hotelService.updateHotel(hotel.getId(), hotel);
        model.addAttribute("countries", Country.values());
        model.addAttribute("hotel", new HotelRequest());
        return new ModelAndView("hotels", "hotels", hotelService.getAllHotel());
    }
}
