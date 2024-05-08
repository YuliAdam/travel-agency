package org.example.controller.response;

import org.example.entity.Offer;
import org.example.entity.Orders;
import org.example.entity.Users;

import java.sql.Timestamp;

public class OrdersResponse {
    private Long id;

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private Long offerId;

    private Timestamp orderDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }
    public void setOrderDate( Timestamp orderDate) {
        this.orderDate = orderDate;
    }
    public OrdersResponse(Orders orders) {
        this.id = orders.getId();
        this.userId=orders.getUser().getId();
        this.offerId=orders.getOffer().getId();
        this.orderDate = orders.getOrderDate();
    }

    public OrdersResponse() {}
}
