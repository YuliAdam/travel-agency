package org.example.controller.request;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.example.entity.Offer;
import org.example.entity.User;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.Date;

public class OrdersRequest {

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
    public OrdersRequest(Long id, User user, Offer offer) {
        this.id = id;
        this.user=user;
        this.offer=offer;
        this.orderDate = new Timestamp(new Date().getTime());
    }

    public OrdersRequest() {}
}
