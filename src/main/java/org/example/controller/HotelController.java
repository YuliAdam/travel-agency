package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.example.controller.request.HotelRequest;
import org.example.controller.response.UploadFileResponse;
import org.example.entity.Hotel;
import org.example.entity.characteristic.Country;
import org.example.service.FileStorageService;
import org.example.service.HotelService;
import org.example.service.MoneyService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/hotel")
@Validated
public class HotelController {
    @Value("${spring.datasource.url}")
    private String url;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private UserService userService;
    @Autowired
    private MoneyService moneyService;


    @GetMapping("/search")
    public ModelAndView getHotels(Model model,
                                  @RequestParam(required = false) String paramtr,
                                  @RequestParam(required = false, defaultValue = "name") String sort,
                                  @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                  @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        model.addAttribute("currentUserId", userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        model.addAttribute("moneyByCurrentUserId", moneyService.findByUserId(userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));

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
                                    @RequestParam(required = false) String paramtr,
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
        model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        model.addAttribute("moneyByCurrentUserId", moneyService.findByUserId(userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        model.addAttribute("currentUserId", userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
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
        model.addAttribute("paramtr", paramtr);
        model.addAttribute("sort", sort);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("countries", Country.values());
        model.addAttribute("hotel", new HotelRequest());
        model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        model.addAttribute("moneyByCurrentUserId", moneyService.findByUserId(userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        model.addAttribute("currentUserId", userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
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
        model.addAttribute("paramtr", paramtr);
        model.addAttribute("sort", sort);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("countries", Country.values());
        model.addAttribute("hotel", new HotelRequest());
        model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        model.addAttribute("moneyByCurrentUserId", moneyService.findByUserId(userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        model.addAttribute("currentUserId", userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        return new ModelAndView("hotels", "hotels", hotelService.findHotels(paramtr, sort, pageNumber, pageSize));
    }

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/{id}/redirect/uploadimage")
    public ModelAndView displayUploadForm(@PathVariable Long id, Model model) {
        model.addAttribute("hotelId", id);
        return new ModelAndView("uploadFile");
    }

    @PostMapping("/{id}/upload")
    public ModelAndView uploadImage(@PathVariable("id") Long id, Model model, @RequestParam("image") MultipartFile file) throws IOException {
        String fileName = fileStorageService.storeFile(file, id);
        model.addAttribute("msg", "Uploaded images: " + file.getOriginalFilename().toString());
        model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        model.addAttribute("currentUserId", userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        model.addAttribute("moneyByCurrentUserId", moneyService.findByUserId(userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        String paramtr = "";
        String sort = "country";
        int pageNumber = 0;
        int pageSize = 5;
        model.addAttribute("paramtr", paramtr);
        model.addAttribute("sort", sort);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("countries", Country.values());
        return new ModelAndView("hotels", "hotels", hotelService.findHotels(paramtr, sort, pageNumber, pageSize));
    }

    @GetMapping("/{id}/getImage")
    public ModelAndView getImage(@PathVariable Long id, Model model) {
        fileStorageService.getImage(id);
        model.addAttribute("images", fileStorageService.getImage(id));
        return new ModelAndView("images", "images", fileStorageService.getImage(id));
    }
}
