package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;
    @Column(name = "order_date")
    private Timestamp orderDate;
    public Orders(Users user, Offer offer, Timestamp orderDate) {
        this.user=user;
        this.offer=offer;
        this.orderDate=orderDate;
    }
    public Orders(Users user, Offer offer) {
        this.user=user;
        this.offer=offer;
    }
}