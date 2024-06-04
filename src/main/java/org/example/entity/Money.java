package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "money")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Money {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", referencedColumnName = "id",unique = true)
    private Users user;
    @Column(name = "balance")
    private Float balance;
    @Column(name = "last_change_date")
    private Timestamp lastChangeDate;
    @Column(name="last_transfer")
    private Float transfer;
    @Column (name = "version")
    private long version;
    public Money(Float balance,long version){
        this.balance=balance;
        this.version=version;
    }
    public Money(Users user, Float balance,Float transfer){
        this.user=user;
        this.balance=balance;
        this.transfer=transfer;
    }

}
