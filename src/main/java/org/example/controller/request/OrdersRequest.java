package org.example.controller.request;

import org.example.entity.Offer;
import org.example.entity.Users;

import java.sql.Timestamp;
import java.util.Date;

public class OrdersRequest {

    private Long id;

    private Long userId;

    private Long offerId;

    private Timestamp orderDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }
    public void setOrderDate( Timestamp orderDate) {
        this.orderDate = orderDate;
    }
    public OrdersRequest(Long id, Long userId, Long offerId) {
        this.id = id;
        this.userId=userId;
        this.offerId=offerId;
        this.orderDate = new Timestamp(new Date().getTime());
    }

    public OrdersRequest() {}
}
