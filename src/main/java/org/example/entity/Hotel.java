package org.example.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.example.entity.characteristic.Country;

@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    public int id;
    @Column(name = "country",columnDefinition = "ENUM('AU','EG','BY','RU','FR','IL','IT','USA')")
    @Enumerated(EnumType.STRING)
    @Nullable
    public Country country;
    @Column(name = "name")
    @Nullable
    public String name;
    @Column(name = "star")
    public int star;
    @Column(name = "guests")
    public int guest;
    @Column(name = "breakfast")
    public boolean breakfast;
    @Column(name = "lunch")
    public boolean lunch;
    @Column(name = "dinner")
    public boolean dinner;
    @Column(name = "tv")
    public boolean tv;
    @Column(name = "air_conditioner")
    public boolean airConditioner;
    @Column(name = "balcony")
    public boolean balcony;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getGuest() {
        return guest;
    }

    public void setGuest(int guest) {
        this.guest = guest;
    }

    public boolean isBreakfast() {
        return breakfast;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

    public boolean isLunch() {
        return lunch;
    }

    public void setLunch(boolean lunch) {
        this.lunch = lunch;
    }

    public boolean isDinner() {
        return dinner;
    }

    public void setDinner(boolean dinner) {
        this.dinner = dinner;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(boolean airConditioner) {
        this.airConditioner = airConditioner;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public Hotel(int id, Country country, String name, int star, int guest, boolean breakfast, boolean lunch, boolean dinner, boolean tv, boolean airConditioner, boolean balcony) {
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
    public Hotel(){

    }
}
