package org.example.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.example.entity.characteristic.Country;
import org.example.entity.characteristic.Transport;
import org.example.entity.characteristic.Type;

import java.sql.Date;

@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @Column(name = "id")
    public int id;
    @Column(name = "type", columnDefinition = "ENUM ('EXCURSION','RELAX','PILGRIMAGE','WEEKEND')")
    @Nullable
    @Enumerated(EnumType.STRING)
    public Type type;
    @Column(name = "country",columnDefinition = "ENUM('AU','EG','BY','RU','FR','IL','IT','USA')")
    @Nullable
    @Enumerated(EnumType.STRING)
    public Country country;
    @Column(name = "num_of_the_days")
    public int numOfTheDays;
    @Column(name = "start")
    @Nullable
    public Date start;
    @Column(name = "transport",columnDefinition ="ENUM('AIR','TRAIN','SHIP','BUS')" )
    @Enumerated(EnumType.STRING)
    @Nullable
    public Transport transport;
    @ManyToOne
    @JoinColumn(name ="id_hotel")
    @Nullable
    public Hotel hotel;
    @Column(name = "price$")
    public float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getNumOfTheDays() {
        return numOfTheDays;
    }

    public void setNumOfTheDays(int numOfTheDays) {
        this.numOfTheDays = numOfTheDays;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Offer(int id, Type type, Country country, int numOfTheDays, Date start, Transport transport, float price) {
        this.id = id;
        this.type = type;
        this.country = country;
        this.numOfTheDays = numOfTheDays;
        this.start = start;
        this.transport = transport;
        this.price = price;
    }

    public Offer() {
    }
}
