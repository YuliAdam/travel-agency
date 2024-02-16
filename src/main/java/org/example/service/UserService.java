package org.example.service;

import org.example.entity.Admin;
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
    public List<User> findUserByName(String name) {
        return userRepository.findAll().stream().filter(user -> user.userName.replaceAll(" ","").equals(name)).collect(Collectors.toList());
    }
    public String deleteUser(int id){
        try {
            userRepository.findById(id).get();
            userRepository.deleteById(id);
            return "User deleted";
        } catch (Exception e){
            return "User not found";
        }
    }
    public User createUser(User user){
        return  userRepository.save(user);
    }
    public User updateUser(int id, User user){
        User existingUser = userRepository.findById(id).get();
        existingUser.setUserName(user.getUserName());
        existingUser.setEmail(user.getEmail());
        existingUser.setTell(user.getTell());
        return userRepository.save(existingUser);
    }

}
