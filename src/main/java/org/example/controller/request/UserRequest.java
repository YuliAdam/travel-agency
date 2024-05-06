package org.example.controller.request;


import org.example.entity.characteristic.Role;

public class UserRequest {

    private String userName;

    private String login;

    private Long tell;
    private Long id;
    private String password;
    private Role role;

    public UserRequest(String userName, String login, Long tell,String password,Role role) {
        this.userName = userName;
        this.login = login;
        this.tell = tell;
        this.password=password;
        this.role=role;
    }
    public UserRequest(Long id,String userName, String login, Long tell,String password,Role role) {
        this.id=id;
        this.userName = userName;
        this.login = login;
        this.tell = tell;
        this.password=password;
        this.role=role;
    }

    public UserRequest() {}

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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setTell(Long tell) {
        this.tell = tell;
    }
}
