package org.example.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser( User user) {
        this.user = user;
    }
    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;
    @Column(name = "order_date")
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
    public Orders(Long id, User user, Offer offer, Timestamp orderDate) {
        this.id = id;
        this.user=user;
        this.offer=offer;
        this.orderDate = orderDate;
    }
    public Orders(User user, Offer offer,Timestamp orderDate) {
        this.user=user;
        this.offer=offer;
        this.orderDate=orderDate;
    }
    public Orders(User user, Offer offer) {
        this.user=user;
        this.offer=offer;
    }

    public Orders() {}
}