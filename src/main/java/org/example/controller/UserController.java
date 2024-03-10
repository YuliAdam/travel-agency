package org.example.controller;

import org.example.controller.request.UserRequest;
import org.example.controller.response.UserResponse;
import org.example.entity.characteristic.Country;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;

    @GetMapping
    public List<UserResponse> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/redirect/create")
    public ModelAndView getUser(Model model) {
        model.addAttribute("user", new UserRequest());
        return new ModelAndView("addUser");
    }

    @GetMapping("/{name}/redirect/search")
    public  ModelAndView findUserByName(@PathVariable String name,Model model) {
        model.addAttribute("users", userService.getAllUser());
        return new ModelAndView("searchUser","users",userService.findUserByName(name));
    }
    @GetMapping(value = "/{id}/redirect/update")
    public ModelAndView getUser(@PathVariable Long id, Model model) {
        return new ModelAndView("user", "user", userService.getUser(id));
    }

    @DeleteMapping("/{id}/delete")
    public ModelAndView deleteUser(@PathVariable Long id,Model model) {
        userService.deleteUser(id);
        model.addAttribute("user", new UserRequest());
        return new ModelAndView("users","users",userService.getAllUser());
    }

    @PostMapping("/create")
    public ModelAndView createUser(@ModelAttribute("user") UserRequest userRequest,Model model) {
        userService.createUser(userRequest);
        model.addAttribute("user", new UserRequest());
        return new ModelAndView("users","users",userService.getAllUser());
    }

    @PutMapping("/update")
    public ModelAndView updateUser(@ModelAttribute("user") UserRequest userRequest,Model model) {
        userService.updateUser(userRequest.getId(),userRequest);
        model.addAttribute("user", new UserRequest());
        return new ModelAndView("users","users",userService.getAllUser());
    }
}
