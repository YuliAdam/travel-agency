package org.example.controller.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Hotel;
import org.example.entity.characteristic.Country;
import org.example.entity.characteristic.Transport;
import org.example.entity.characteristic.Type;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferRequest {
    private Long id;
    private Type type;
    private Country country;
    private Integer numOfTheDays;
    private Date start;
    private Transport transport;
    private Long hotelId;
    private Float price;
}
