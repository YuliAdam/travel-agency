package org.example.entity;

import jakarta.persistence.*;
import org.example.entity.characteristic.Country;

@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    public int id;
    @Column(name = "country", columnDefinition = "ENUM('AU','EG','BY','RU','FR','IL','IT')")
    @Enumerated(EnumType.STRING)
    public Country country;
    @Column(name = "name")
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
