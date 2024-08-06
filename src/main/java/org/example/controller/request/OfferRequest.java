package org.example.controller.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.characteristic.Country;
import org.example.entity.characteristic.Transport;
import org.example.entity.characteristic.Type;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferRequest {
    private Long id;
    @NotNull(message = "Type cannot be empty")
    private Type type;
    @NotNull(message = "Country cannot be empty")
    private Country country;
    @Max(value = 130, message = "Number of the days is not valid. Max number of the days is 130")
    @Min(value = 1, message = "Number of the days is not valid.")
    private Integer numOfTheDays;
    @FutureOrPresent(message = "Start is not valid.")
    private Date start;
    private Transport transport;
    @Positive(message = "Hotel id is not valid.")
    private Long hotelId;
    @Min(value = 0, message = "Price is not valid")
    @Max(value = 1000000, message = "Price is not valid. Max is 1000000$")
    private Float price;
}
