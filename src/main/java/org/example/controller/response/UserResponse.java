package org.example.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Users;
import org.example.entity.characteristic.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String userName;
    private String login;
    private String tell;
    private String password;
    private Role role;

    public String getPassword() {
        StringBuilder pass = new StringBuilder();
        for (int i = 0; i < password.length(); i++) pass = pass.append("*");
        return pass.toString();
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
