package org.example.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Hotel;
import org.example.entity.Offer;
import org.example.entity.characteristic.Country;
import org.example.entity.characteristic.Transport;
import org.example.entity.characteristic.Type;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferResponse {
    private Long id;
    private Type type;
    private Country country;
    private Integer numOfTheDays;
    private Date start;
    private Transport transport;
    private Long hotelId;
    private Float price;

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
}
