package org.example.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Offer;
import org.example.entity.Users;

import java.sql.Timestamp;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersRequest {
    private Long id;
    @NotNull(message = "User Id cannot be empty" )
    @Min(value = 1, message = "User Id is not valid." )
    private Long userId;
    @NotNull(message = "Offer Id cannot be empty" )
    @Min(value = 1, message = "Offer Id is not valid." )
    private Long offerId;
    private Timestamp orderDate;
}
