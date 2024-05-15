package org.example.controller.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.characteristic.Role;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotBlank(message = "User Name cannot be empty")
    @Size(min = 1,max=50, message = "User Name is not valid" )
    private String userName;
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotBlank(message = "Email cannot be empty")
    @Size(max=100, message = "Login is not valid")
    private String login;
    @NotBlank(message = "Telephone cannot be empty")
    @Pattern(regexp = "[0-9+]{5,12}", message = "Telephone is not valid")
    private String tell;
    private Long id;
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 4,max = 12, message = "Password is not valid. Min 4 chars max 12.")
    private String password;
    private Role role;

    public UserRequest(String userName, String login, String tell, String password, Role role) {
        this.userName = userName;
        this.login = login;
        this.tell = tell;
        this.password = password;
        this.role = role;
    }
}
