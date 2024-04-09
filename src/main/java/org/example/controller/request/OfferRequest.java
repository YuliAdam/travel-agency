package org.example.controller.request;

import jakarta.persistence.*;
import org.example.entity.Hotel;
import org.example.entity.characteristic.Country;
import org.example.entity.characteristic.Transport;
import org.example.entity.characteristic.Type;

import java.sql.Date;

public class OfferRequest {
    private Long id;

    private Type type;

    private Country country;

    private Integer numOfTheDays;

    private Date start;

    private Transport transport;

    private Hotel hotel;

    private Float price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getHotelId() {
        return this.hotel.getId();
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

    public Integer getNumOfTheDays() { return numOfTheDays; }

    public void setNumOfTheDays(Integer numOfTheDays) {
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public OfferRequest(Long id, Type type, Country country, Integer numOfTheDays, Date start, Transport transport, Hotel hotel,Float price) {
        this.id = id;
        this.type = type;
        this.country = country;
        this.numOfTheDays = numOfTheDays;
        this.start = start;
        this.transport = transport;
        this.hotel=hotel;
        this.price = price;
    }

    public OfferRequest() {}
}
