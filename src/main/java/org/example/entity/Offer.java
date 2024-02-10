package org.example.entity;

import jakarta.persistence.*;
import org.example.entity.characteristic.Country;
import org.example.entity.characteristic.Transport;
import org.example.entity.characteristic.Type;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "offer")
public class Offer {
    @Id
    public int id;
    @Column(name = "type", columnDefinition = "ENUM('EXCURSION','RELAX','PILGRIMAGE','WEEKEND')")
    @Enumerated(EnumType.STRING)
    public Type type;
    @Column(name = "country", columnDefinition = "ENUM('AU','EG','BY','RU','FR','IL','IT')")
    @Enumerated(EnumType.STRING)
    public Country country;
    @Column(name = "num_of_the_days")
    public int numOfTheDays;
    @Column(name = "start")
    public Date start;
    @Column(name = "transport", columnDefinition = "ENUM('AIR','TRAIN','SHIP','BUS')")
    @Enumerated(EnumType.STRING)
    public Transport transport;
    @ManyToOne
    @JoinColumn(name ="id_hotel")
    public Hotel hotel;
    @Column(name = "price$")
    public float price;
   /* @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order",joinColumns = @JoinColumn(name = "offer_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"))
    public Set<User> users=new HashSet<User>();

    public Set<User> getUsers() {
        return users;
    }
    public void setUsers(Set<User> users) {
        this.users = users;
    }
    public void addUser(User user) {
        users.add(user);
    }*/

    public Offer(int id, Type type, Country country, int numOfTheDays, Date start, Transport transport,float price) {
        this.id = id;
        this.type = type;
        this.country = country;
        this.numOfTheDays = numOfTheDays;
        this.start = start;
        this.transport = transport;
        this.price = price;
    }

    public Offer() {
    }
}
