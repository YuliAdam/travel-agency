package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.characteristic.Country;

@Entity
@Table(name = "hotel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "gallery")
    private String gallery;

    public Hotel(Country country, String name, Integer star, Integer guest, Boolean breakfast, Boolean lunch, Boolean dinner, Boolean tv, Boolean airConditioner, Boolean balcony, String gallery) {
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
        this.gallery = gallery;
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

    public Hotel(Long id) {
        this.id = id;
    }

    public Hotel(String gallery) {
        this.gallery = gallery;
    }
}
