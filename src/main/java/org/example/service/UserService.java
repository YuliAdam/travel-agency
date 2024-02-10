package org.example.service;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
@Autowired
    UserRepository userRepository;
    public List<User> getAllUser() {
        return userRepository.findAll().stream().collect(Collectors.toList());
    }

}
