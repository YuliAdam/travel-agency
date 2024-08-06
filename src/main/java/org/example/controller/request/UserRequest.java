package org.example.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.characteristic.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Long id;
    @NotBlank(message = "User Name cannot be empty")
    @Size(min = 1, max = 50, message = "User Name is not valid")
    private String userName;
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotBlank(message = "Email cannot be empty")
    @Size(max = 100, message = "Login is not valid")
    private String login;
    @NotBlank(message = "Telephone cannot be empty")
    @Pattern(regexp = "[0-9+]{5,12}", message = "Telephone is not valid")
    private String tell;
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 4, max = 12, message = "Password is not valid. Min 4 chars max 12.")
    private String password;
    private Role role;
}
