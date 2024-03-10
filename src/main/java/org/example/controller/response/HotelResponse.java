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

    public Boolean getBreakfast() {
        return breakfast;
    }

    public Boolean getLunch() {
        return lunch;
    }

    public Boolean getDinner() {
        return dinner;
    }

    public Boolean getTv() {
        return tv;
    }

    public Boolean getAirConditioner() {
        return airConditioner;
    }
    public Boolean getBalcony() {
        return balcony;
    }

    public HotelResponse(Hotel hotel) {
        this.id = hotel.getId();
        this.country = hotel.getCountry();
        this.name = hotel.getName();
        this.star = hotel.getStar();
        this.guest = hotel.getGuest();
        this.breakfast = hotel.getBreakfast();
        this.lunch = hotel.getLunch();
        this.dinner = hotel.getDinner();
        this.tv = hotel.getTv();
        this.airConditioner = hotel.getAirConditioner();
        this.balcony = hotel.getBalcony();
    }

    public HotelResponse(){}
    public HotelResponse(String name) {
        this.name = name;
    }
}
