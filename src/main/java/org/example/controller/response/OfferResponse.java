package org.example.controller.response;

import org.example.entity.Hotel;
import org.example.entity.Offer;
import org.example.entity.characteristic.Country;
import org.example.entity.characteristic.Transport;
import org.example.entity.characteristic.Type;

import java.sql.Date;

public class OfferResponse {
    private Long id;

    private Type type;

    private Country country;

    private Integer numOfTheDays;

    private Date start;

    private Transport transport;

    private Long hotelId;

    private Float price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHotelId() {
        return this.hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
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

    public Integer getNumOfTheDays() {
        return numOfTheDays;
    }

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


    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public OfferResponse(Offer offer) {
        this.id = offer.getId();
        this.type = offer.getType();
        this.country = offer.getCountry();
        this.numOfTheDays = offer.getNumOfTheDays();
        this.start = offer.getStart();
        this.transport = offer.getTransport();
        this.hotelId = offer.getHotel().getId();
        this.price = offer.getPrice();
    }

    public OfferResponse() {
    }
}
