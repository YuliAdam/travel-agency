package org.example.controller.request;

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
    private Long userId;
    private Long offerId;
    private Timestamp orderDate;
}
