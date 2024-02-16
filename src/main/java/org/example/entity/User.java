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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer id;
    @Column(name = "user_name")
    @Nullable
    public String userName;
    @Column(name = "email")
    @Nullable
    public String email;
    @Nullable
    @Column(name = "tell")
    public Long tell;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }


    public void setTell(long tell) {
        this.tell = tell;
    }

    public User() {
    }
    public User(Integer id,String userName,String email,long tell){
        this.id=id;
        this.userName=userName;
        this.email=email;
        this.tell=tell;
    }

    @Nullable
    public Long getTell() {
        return tell;
    }
}
