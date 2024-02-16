package org.example.controller;

import org.example.controller.request.UserRequest;
import org.example.controller.response.UserResponse;
import org.example.entity.Admin;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;
    @GetMapping
    public List<UserResponse> getAllUser(){
    return userService.getAllUser();
    }
    @GetMapping("/name")
    public List<User> findUserByName(@RequestParam String name){
        return userService.findUserByName(name);
    }
    @DeleteMapping("/id")
    public String deleteUser(@RequestParam int id){
        return userService.deleteUser(id);
    }
    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest){
        return userService.createUser(userRequest);
    }
    @PutMapping("/id")
    public User updateUser(@RequestParam int id,@RequestBody UserRequest userRequest){
        return userService.updateUser(id,userRequest);
    }
}
