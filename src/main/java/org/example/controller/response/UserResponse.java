package org.example.controller.response;
import org.example.entity.User;

public class UserResponse {

    private Long id;

    private String userName;

    private String email;

    private Long tell;
    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setTell(Long tell) {
        this.tell = tell;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public Long getTell() {
        return tell;
    }

    public UserResponse() {
    }
    public UserResponse(User user) {
        this.id= user.getId();
        this.userName=user.getUserName();
        this.email=user.getEmail();
        this.tell=user.getTell();
    }
    public UserResponse(String userName){
        this.userName=userName;
    }

}
