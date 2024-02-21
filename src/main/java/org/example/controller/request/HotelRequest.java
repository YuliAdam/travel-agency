package org.example.controller.request;

import org.example.entity.characteristic.Country;

public class HotelRequest {
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
    public HotelRequest(Long id, Country country, String name, Integer star, Integer guest, Boolean breakfast, Boolean lunch, Boolean dinner, Boolean tv, Boolean airConditioner, Boolean balcony) {
        this.id = id;
        this.country = country;
        this.name = name;
        this.star = star;
        this.guest = guest;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.tv = tv;
        this.airConditioner = airConditioner;
        this.balcony = balcony;
    }
    public HotelRequest(){}
}
