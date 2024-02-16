package org.example.controller;

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
    @GetMapping("/all")
    public List<User> getAllUser(){
    return userService.getAllUser();
    }
    @GetMapping("/name")
    public List<User> findUserByName(@RequestParam String name){
        return userService.findUserByName(name);
    }
    @DeleteMapping("/delete/id")
    public String deleteUser(@RequestParam int id){
        return userService.deleteUser(id);
    }
    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    @PutMapping("/id")
    public User updateUser(@RequestParam int id,@RequestBody User user){
        return userService.updateUser(id,user);
    }
}
