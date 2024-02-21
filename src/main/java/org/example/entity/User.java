package org.example.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "email")
    private String email;
    @Column(name = "tell")
    private Long tell;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email) {
        this.email = email;
    }


    public void setTell(Long tell) {
        this.tell = tell;
    }

    public User() {}
    public User(Long id,String userName,String email,long tell){
        this.id=id;
        this.userName=userName;
        this.email=email;
        this.tell=tell;
    }
    public User(String userName,String email,long tell){
        this.userName=userName;
        this.email=email;
        this.tell=tell;
    }

    public Long getTell() { return tell; }
}
