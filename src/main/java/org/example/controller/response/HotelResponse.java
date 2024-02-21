package org.example.controller.response;

import org.example.entity.Hotel;
import org.example.entity.characteristic.Country;

public class HotelResponse {
    private Long id;
    private Country country;

    private String name;

    private Integer star;

    private Integer guest;

    private Boolean breakfast;

    private Boolean lunch;

    private Boolean dinner;

    private Boolean tv;

    private Boolean airConditioner;

    private Boolean balcony;

    public Long getId() {
        return id;
    }

    public Country getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public Integer getStar() {
        return star;
    }

    public Integer getGuest() {
        return guest;
    }

    public Boolean isBreakfast() {
        return breakfast;
    }

    public Boolean isLunch() {
        return lunch;
    }

    public Boolean isDinner() {
        return dinner;
    }

    public Boolean isTv() {
        return tv;
    }

    public Boolean isAirConditioner() {
        return airConditioner;
    }
    public Boolean isBalcony() {
        return balcony;
    }

    public HotelResponse(Hotel hotel) {
        this.id = hotel.getId();
        this.country = hotel.getCountry();
        this.name = hotel.getName();
        this.star = hotel.getStar();
        this.guest = hotel.getGuest();
        this.breakfast = hotel.isBreakfast();
        this.lunch = hotel.isLunch();
        this.dinner = hotel.isDinner();
        this.tv = hotel.isTv();
        this.airConditioner = hotel.isAirConditioner();
        this.balcony = hotel.isBalcony();
    }

    public HotelResponse(){}
    public HotelResponse(String name) {
        this.name = name;
    }
}
