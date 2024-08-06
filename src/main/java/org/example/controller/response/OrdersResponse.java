package org.example.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Offer;
import org.example.entity.Orders;
import org.example.entity.Users;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersResponse {
    private Long id;
    private Long userId;
    private Long offerId;
    private Timestamp orderDate;

    public OrdersResponse(Orders orders) {
        this.id = orders.getId();
        this.userId=orders.getUser().getId();
        this.offerId=orders.getOffer().getId();
        this.orderDate = orders.getOrderDate();
    }
}
