package org.example.service;

import org.example.controller.request.UserRequest;
import org.example.controller.response.AdminResponse;
import org.example.controller.response.HotelResponse;
import org.example.controller.response.UserResponse;
import org.example.entity.Admin;
import org.example.entity.Users;
import org.example.entity.characteristic.Role;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserResponse> getAllUser() {
        return userRepository.findAllUsers().stream().map(UserResponse::new).toList();
    }

    public List<UserResponse> findUsers(String paramtr, String sort, Integer pageNumber, Integer pageSize) {
        PageRequest page = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, sort));
        if (paramtr != null) {
            return userRepository.findUsers(paramtr.trim(), page).stream()
                    .map(UserResponse::new)
                    .toList();
        } else return userRepository.findUsers("", page).stream()
                .map(UserResponse::new)
                .toList();
    }

    public UserResponse getUser(Long id) {
        return new UserResponse(userRepository.findById(id).get());
    }
    public UserResponse getUserByLogin(String login) {
        return new UserResponse(userRepository.findByLogin(login));
    }

    public String deleteUser(Long id) {
        try {
            userRepository.findById(id).get();
            userRepository.deleteById(id);
            return "User deleted. ";
        } catch (NoSuchElementException e) {
            System.out.println("Error. Class UserService. User not found. " + e);
            return "User not found. " + e;
        }
    }
    public UserResponse createUser(UserRequest userRequest) {
        try {
            Users user = new Users(userRequest.getUserName(), userRequest.getLogin(), userRequest.getTell()
                    ,new BCryptPasswordEncoder(12).encode(userRequest.getPassword()), Role.USER);
            return new UserResponse(userRepository.save(user));
        } catch (DataIntegrityViolationException e) {
            System.out.println("Error. Class UserService. User_name cannot be null. " + e);
            return new UserResponse("User_name cannot be null. " + e);
        }
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        try {
            Users existingUser = userRepository.findById(id).get();
            existingUser.setUserName(userRequest.getUserName());
            existingUser.setLogin(userRequest.getLogin());
            existingUser.setTell(userRequest.getTell());
            existingUser.setPassword(userRequest.getPassword());
            existingUser.setRole(userRequest.getRole());
            return new UserResponse(userRepository.save(existingUser));
        } catch (NoSuchElementException e) {
            System.out.println("Error. Class UserService. User not found. " + e);
            return new UserResponse("User not found. " + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("Error. Class UserService. User_name cannot be null. " + e);
            return new UserResponse("User_name cannot be null. " + e);
        }
    }

}
