package org.example.entity;

import jakarta.persistence.*;
import org.example.entity.characteristic.Country;

@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "country")
    @Enumerated(EnumType.STRING)
    private Country country;
    @Column(name = "name")
    private String name;
    @Column(name = "star")
    private Integer star;
    @Column(name = "guests")
    private Integer guest;
    @Column(name = "breakfast")
    private Boolean breakfast;
    @Column(name = "lunch")
    private Boolean lunch;
    @Column(name = "dinner")
    private Boolean dinner;
    @Column(name = "tv")
    private Boolean tv;
    @Column(name = "air_conditioner")
    private Boolean airConditioner;
    @Column(name = "balcony")
    private Boolean balcony;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getGuest() {
        return guest;
    }

    public void setGuest(Integer guest) {
        this.guest = guest;
    }

    public Boolean getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Boolean breakfast) {
        this.breakfast = breakfast;
    }

    public Boolean getLunch() {
        return lunch;
    }

    public void setLunch(Boolean lunch) {
        this.lunch = lunch;
    }

    public Boolean getDinner() {
        return dinner;
    }

    public void setDinner(Boolean dinner) {
        this.dinner = dinner;
    }

    public Boolean getTv() {
        return tv;
    }

    public void setTv(Boolean tv) {
        this.tv = tv;
    }

    public Boolean getAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(Boolean airConditioner) {
        this.airConditioner = airConditioner;
    }

    public Boolean getBalcony() {
        return balcony;
    }

    public void setBalcony(Boolean balcony) {
        this.balcony = balcony;
    }

    public Hotel(Long id, Country country, String name, Integer star, Integer guest, Boolean breakfast, Boolean lunch, Boolean dinner, Boolean tv, Boolean airConditioner, Boolean balcony) {
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
    public Hotel(Country country, String name, Integer star, Integer guest, Boolean breakfast, Boolean lunch, Boolean dinner, Boolean tv, Boolean airConditioner, Boolean balcony) {
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
    public Hotel(){}
    public Hotel(Long id) { this.id=id; }
}
