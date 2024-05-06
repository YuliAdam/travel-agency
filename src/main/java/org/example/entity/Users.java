package org.example.entity;

import jakarta.persistence.*;
import org.example.entity.characteristic.Role;

@Entity
@Table(name = "user")
public class Users {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "login",unique = true)
    private String login;
    @Column(name = "tell")
    private Long tell;
    @Column(name = "password")
    private String password;
    //@Transient
    //private String passwordConfirm;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setTell(Long tell) {
        this.tell = tell;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Users() {
    }

    public Users(Long id, String userName, String login, long tell, String password, Role role) {
        this.id = id;
        this.userName = userName;
        this.login = login;
        this.tell = tell;
        this.password = password;
        this.role = role;
    }

    public Users(String userName, String login, long tell, String password, Role role) {
        this.userName = userName;
        this.login = login;
        this.tell = tell;
        this.password = password;
        this.role = role;
    }

    public Long getTell() {
        return tell;
    }

}
