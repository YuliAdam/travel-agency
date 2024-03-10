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

    public void setId(Long id) {
        this.id = id;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public void setGuest(Integer guest) {
        this.guest = guest;
    }

    public void setBreakfast(Boolean breakfast) {
        this.breakfast = breakfast;
    }

    public void setLunch(Boolean lunch) {
        this.lunch = lunch;
    }

    public void setDinner(Boolean dinner) {
        this.dinner = dinner;
    }

    public void setTv(Boolean tv) {
        this.tv = tv;
    }

    public void setAirConditioner(Boolean airConditioner) {
        this.airConditioner = airConditioner;
    }

    public void setBalcony(Boolean balcony) {
        this.balcony = balcony;
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
