package org.example.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    public int id;
    @Column(name = "user_name")
    public String user_name;
    @Column(name = "email")
    public String email;
    @Column(name = "tell")
    public long tell;
    /*@ManyToMany
    @JoinTable(name = "order",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "offer_id",referencedColumnName = "id"))
    public Set<Offer> offers = new HashSet<Offer>();

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }
    public void addOffer(Offer offer){
        offers.add(offer);
    }*/

    public User() {
    }
    public User(int id,String user_name,String email,long tell){
        this.id=id;
        this.user_name=user_name;
        this.email=email;
        this.tell=tell;
    }
}
