package org.example.controller.response;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.example.entity.User;

public class UserResponse {

    private Integer id;

    private String userName;

    private String email;

    private Long tell;

    public Long getTell() {
        return tell;
    }

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

    public UserResponse() {
    }
    public UserResponse(User user) {
        this.id= user.getId();
        this.userName=user.getUserName();
        this.email=user.getEmail();
        this.tell=user.getTell();
    }
    public UserResponse(Integer id,String userName,String email,long tell){
        this.id=id;
        this.userName=userName;
        this.email=email;
        this.tell=tell;
    }

}
