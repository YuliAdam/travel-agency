package org.example.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Hotel;
import org.example.entity.characteristic.Country;
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    public HotelResponse(String name) {
        this.name = name;
    }
    public HotelResponse(Long id) {
        this.id = id;
    }
}
