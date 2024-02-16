package org.example.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.example.entity.characteristic.Type;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.NumberFormat;


@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    public int id;
    @Column(name = "user_name")
    @Nullable
    public String userName;
    @Column(name = "email")
    @Nullable
    public String email;
    @Column(name = "tell")
    public long tell;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public void setEmail(String email) {
        this.email = email;
    }

    public long getTell() {
        try {
            return tell;
        }catch (NullPointerException  e){
            return tell=000000;
        }
    }

    public void setTell(long tell) {
        this.tell = tell;
    }

    public User() {
    }
    public User(int id,String userName,String email,long tell){
        this.id=id;
        this.userName=userName;
        this.email=email;
        this.tell=tell;
    }
    public User(int id,String userName,String email){
        this.id=id;
        this.userName=userName;
        this.email=email;
    }

}
