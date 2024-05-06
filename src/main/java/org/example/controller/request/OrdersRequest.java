package org.example.controller.request;

import org.example.entity.Offer;
import org.example.entity.Users;

import java.sql.Timestamp;
import java.util.Date;

public class OrdersRequest {

    private Long id;

    private Users user;

    public Users getUser() {
        return user;
    }

    public void setUser( Users user) {
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
    public OrdersRequest(Long id, Users user, Offer offer) {
        this.id = id;
        this.user=user;
        this.offer=offer;
        this.orderDate = new Timestamp(new Date().getTime());
    }

    public OrdersRequest() {}
}
