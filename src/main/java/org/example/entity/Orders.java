package org.example.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @Column(name = "id")
    public int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Nullable
    public User user;

    @Nullable
    public User getUser() {
        return user;
    }

    public void setUser(@Nullable User user) {
        this.user = user;
    }
    @ManyToOne
    @JoinColumn(name = "offer_id")
    @Nullable
    public Offer offer;
    @Column(name = "order_date")
    @Nullable
    public Timestamp orderDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nullable
    public Offer getOffer() {
        return offer;
    }

    public void setOffer(@Nullable Offer offer) {
        this.offer = offer;
    }

    @Nullable
    public Timestamp getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(@Nullable Timestamp orderDate) {
        this.orderDate = orderDate;
    }
    public Orders(int id, @Nullable User user, @Nullable Offer offer, @Nullable Timestamp orderDate) {
        this.id = id;
        this.user=user;
        this.offer=offer;
        this.orderDate = orderDate;
    }

    public Orders() {
    }
}