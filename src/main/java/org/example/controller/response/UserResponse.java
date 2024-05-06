package org.example.controller.response;

import org.example.entity.Users;
import org.example.entity.characteristic.Role;

public class UserResponse {

    private Long id;

    private String userName;

    private String login;

    private Long tell;
    private String password;
    private Role role;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getLogin() {
        return login;
    }

    public Long getTell() {
        return tell;
    }

    public UserResponse() {
    }

    public UserResponse(Users user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.login = user.getLogin();
        this.tell = user.getTell();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

    public UserResponse(String userName) {
        this.userName = userName;
    }

}
