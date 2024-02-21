package org.example.controller.response;

import org.example.entity.Offer;
import org.example.entity.Orders;
import org.example.entity.User;

import java.sql.Timestamp;

public class OrdersResponse {
    private Long id;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser( User user) {
        this.user = user;
    }

    private Offer offer;

    private Timestamp orderDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer( Offer offer) {
        this.offer = offer;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }
    public void setOrderDate( Timestamp orderDate) {
        this.orderDate = orderDate;
    }
    public OrdersResponse(Orders orders) {
        this.id = orders.getId();
        this.user=orders.getUser();
        this.offer=orders.getOffer();
        this.orderDate = orders.getOrderDate();
    }
    public OrdersResponse(User user) {
        this.user=user;
    }

    public OrdersResponse() {}
}
