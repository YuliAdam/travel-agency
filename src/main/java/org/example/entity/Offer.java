package org.example.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.example.entity.characteristic.Country;
import org.example.entity.characteristic.Transport;
import org.example.entity.characteristic.Type;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(name = "country")
    @Enumerated(EnumType.STRING)
    private Country country;
    @Column(name = "num_of_the_days")
    private Integer numOfTheDays;
    @Column(name = "start")
    private Date start;
    @Column(name = "transport")
    @Enumerated(EnumType.STRING)
    private Transport transport;
    @ManyToOne
    @JoinColumn(name ="id_hotel")
    private Hotel hotel;

    public Long getHotelId () {
        return hotel.getId();
    }
    @Column(name = "price")
    private Float price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Country getCountry() { return country; }

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

    public Offer(Long id, Type type, Country country, Integer numOfTheDays, Date start, Transport transport,Hotel hotel, Float price) {
        this.id = id;
        this.type = type;
        this.country = country;
        this.numOfTheDays = numOfTheDays;
        this.start = start;
        this.transport = transport;
        this.hotel=hotel;
        this.price = price;
    }
    public Offer(Type type, Country country, Integer numOfTheDays, Date start, Transport transport,Hotel hotel, Float price) {
        this.type = type;
        this.country = country;
        this.numOfTheDays = numOfTheDays;
        this.start = start;
        this.transport = transport;
        this.hotel=hotel;
        this.price = price;
    }

    public Offer() {}
}
