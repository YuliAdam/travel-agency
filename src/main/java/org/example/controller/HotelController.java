package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.client.ExternalClient;
import org.example.controller.request.HotelRequest;
import org.example.entity.characteristic.Country;
import org.example.service.FileStorageService;
import org.example.service.HotelService;
import org.example.service.MoneyService;
import org.example.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/hotel")
@Validated
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    private final UserService userService;

    private final MoneyService moneyService;

    private final ExternalClient externalClient;

    private final FileStorageService fileStorageService;


    @GetMapping("/search")
    public ModelAndView getHotels(Model model,
                                  @RequestParam(required = false) String paramtr,
                                  @RequestParam(required = false, defaultValue = "id") String sort,
                                  @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                  @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        ModelAttributes.paginationAttributes(model, paramtr, sort, pageNumber, pageSize);
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        ModelAttributes.hotelAttributes(model);
        //model.addAttribute("temp",externalClient.madeCall(countryNow));
        //model.addAttribute("tempC", externalClient.weatherNow(countryNow).get(0));
        //model.addAttribute("weatherDescription", externalClient.weatherNow(countryNow).get(1));
        return new ModelAndView("hotels", "hotels", hotelService.findHotels(paramtr, sort, pageNumber, pageSize));
    }

    @GetMapping("/redirect/create")
    public ModelAndView getHotel(Model model) {
        ModelAttributes.hotelAttributes(model);
        return new ModelAndView("addHotel");
    }

    @GetMapping(value = "/{id}/redirect/update")
    public ModelAndView getHotel(@PathVariable Long id, Model model) {
        ModelAttributes.hotelAttributes(model);
        return new ModelAndView("hotel", "hotel", hotelService.getHotel(id));
    }

    @DeleteMapping("/{id}/delete")
    public ModelAndView deleteHotel(@PathVariable Long id, Model model,
                                    @RequestParam(required = false) String paramtr,
                                    @RequestParam(required = false, defaultValue = "name") String sort,
                                    @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                    @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        hotelService.deleteHotel(id);
        ModelAttributes.paginationAttributes(model, paramtr, sort, pageNumber, pageSize);
        ModelAttributes.hotelAttributes(model);
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        return new ModelAndView("hotels", "hotels", hotelService.findHotels(paramtr, sort, pageNumber, pageSize));
    }

    @PostMapping("/create")
    public ModelAndView createHotel(@ModelAttribute("hotel") @Valid HotelRequest hotel, Model model,
                                    @RequestParam(required = false) String paramtr,
                                    @RequestParam(required = false, defaultValue = "name") String sort,
                                    @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                    @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        hotelService.createHotel(hotel);
        paramtr = hotel.getName();
        ModelAttributes.paginationAttributes(model, paramtr, sort, pageNumber, pageSize);
        ModelAttributes.hotelAttributes(model);
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        return new ModelAndView("hotels", "hotels", hotelService.findHotels(paramtr, sort, pageNumber, pageSize));
    }

    @PutMapping("/update")
    public ModelAndView updateHotel(@ModelAttribute("hotel") @Valid HotelRequest hotel, Model model,
                                    @RequestParam(required = false) String paramtr,
                                    @RequestParam(required = false, defaultValue = "name") String sort,
                                    @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                    @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        hotelService.updateHotel(hotel.getId(), hotel);
        paramtr = hotel.getName();
        ModelAttributes.paginationAttributes(model, paramtr, sort, pageNumber, pageSize);
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        ModelAttributes.hotelAttributes(model);
        return new ModelAndView("hotels", "hotels", hotelService.findHotels(paramtr, sort, pageNumber, pageSize));
    }
// method getResponse

    @GetMapping("/{id}/redirect/uploadimage")
    public ModelAndView displayUploadForm(@PathVariable Long id, Model model) {
        model.addAttribute("hotelId", id);
        return new ModelAndView("uploadFile");
    }

    @PostMapping("/{id}/upload")
    public ModelAndView uploadImage(@PathVariable("id") Long id, Model model,
                                    @RequestParam("image") MultipartFile file,
                                    @RequestParam(required = false) String paramtr,
                                    @RequestParam(required = false, defaultValue = "id") String sort,
                                    @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                    @RequestParam(required = false, defaultValue = "5") Integer pageSize) throws IOException {
        fileStorageService.storeFile(file, id);
        model.addAttribute("msg", "Uploaded images: " + file.getOriginalFilename().toString());
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        ModelAttributes.paginationAttributes(model, paramtr, sort, pageNumber, pageSize);
        ModelAttributes.hotelAttributes(model);
        return new ModelAndView("hotels", "hotels", hotelService.findHotels(paramtr, sort, pageNumber, pageSize));
    }

    @GetMapping("/{id}/getImage")
    public ModelAndView getImage(@PathVariable Long id, Model model) {
        fileStorageService.getImage(id);
        model.addAttribute("images", fileStorageService.getImage(id));
        return new ModelAndView("images", "images", fileStorageService.getImage(id));
    }
}
