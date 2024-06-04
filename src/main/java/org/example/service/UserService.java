package org.example.service;

import org.example.controller.request.UserRequest;
import org.example.controller.response.AdminResponse;
import org.example.controller.response.HotelResponse;
import org.example.controller.response.OrdersResponse;
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
        if (paramtr == null || paramtr.equals("")) {
            return userRepository.findUsers(page).stream()
                    .map(UserResponse::new)
                    .toList();
        } else return userRepository.findUsers(paramtr.trim(), page).stream()
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
        userRepository.findById(id).get();
        userRepository.deleteById(id);
        return "User deleted. ";
    }

    public UserResponse createUser(UserRequest userRequest) {
        Users user = new Users(userRequest.getUserName(), userRequest.getLogin(), userRequest.getTell()
                , new BCryptPasswordEncoder(12).encode(userRequest.getPassword()), Role.USER);
        return new UserResponse(userRepository.save(user));
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        Users existingUser = userRepository.findById(id).get();
        existingUser.setUserName(userRequest.getUserName());
        existingUser.setLogin(userRequest.getLogin());
        existingUser.setTell(userRequest.getTell());
        if (!existingUser.getPassword().equals(userRequest.getPassword())) {
            existingUser.setPassword(new BCryptPasswordEncoder(12).encode(userRequest.getPassword()));
        }
        existingUser.setRole(userRequest.getRole());
        return new UserResponse(userRepository.save(existingUser));
    }

}
