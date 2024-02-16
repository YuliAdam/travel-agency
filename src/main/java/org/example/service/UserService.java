package org.example.service;

import org.example.controller.request.UserRequest;
import org.example.controller.response.UserResponse;
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
    public List<UserResponse> getAllUser() {
        return userRepository.findAll().stream().map(UserResponse::new).toList();
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
    public UserResponse createUser(UserRequest userRequest){
        User user= new User(null, userRequest.userName, userRequest.getEmail(),userRequest.getTell());
        return  new UserResponse(userRepository.save(user));
    }
    public User updateUser(int id, UserRequest userRequest){
        User existingUser = userRepository.findById(id).get();
        existingUser.setUserName(userRequest.getUserName());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setTell(userRequest.getTell());
        return userRepository.save(existingUser);
    }

}
