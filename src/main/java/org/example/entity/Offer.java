package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.characteristic.Country;
import org.example.entity.characteristic.Transport;
import org.example.entity.characteristic.Type;

import java.sql.Date;

@Entity
@Table(name = "offer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JoinColumn(name = "id_hotel")
    private Hotel hotel;
    @Column(name = "price")
    private Float price;

    public Offer(Type type, Country country, Integer numOfTheDays, Date start, Transport transport, Hotel hotel, Float price) {
        this.type = type;
        this.country = country;
        this.numOfTheDays = numOfTheDays;
        this.start = start;
        this.transport = transport;
        this.hotel = hotel;
        this.price = price;
    }
}
