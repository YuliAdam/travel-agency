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

    @GetMapping("/search")
    public  ModelAndView findUsers(Model model, @RequestParam(required = false,defaultValue = "") String paramtr,
                                        @RequestParam(required = false,defaultValue = "user_name") String sort,
                                        @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                        @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        model.addAttribute("paramtr",paramtr );
        model.addAttribute("sort",sort );
        model.addAttribute("pageNumber",pageNumber );
        model.addAttribute("pageSize",pageSize );
        return new ModelAndView("users","users",userService.findUsers(paramtr, sort, pageNumber, pageSize));
    }
    @GetMapping("/redirect/create")
    public ModelAndView getUser(Model model) {
        model.addAttribute("user", new UserRequest());
        return new ModelAndView("addUser");
    }
    @GetMapping(value = "/{id}/redirect/update")
    public ModelAndView getUser(@PathVariable Long id, Model model) {
        return new ModelAndView("user", "user", userService.getUser(id));
    }

    @DeleteMapping("/{id}/delete")
    public ModelAndView deleteUser(@PathVariable Long id,Model model,
                                   @RequestParam(required = false,defaultValue = "") String paramtr,
                                   @RequestParam(required = false,defaultValue = "user_name") String sort,
                                   @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                   @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        model.addAttribute("paramtr",paramtr );
        model.addAttribute("sort",sort );
        model.addAttribute("pageNumber",pageNumber );
        model.addAttribute("pageSize",pageSize );
        userService.deleteUser(id);
        model.addAttribute("user", new UserRequest());
        return new ModelAndView("users","users",userService.findUsers(paramtr, sort, pageNumber, pageSize));
    }

    @PostMapping("/create")
    public ModelAndView createUser(@ModelAttribute("user") UserRequest userRequest,Model model,
                                   @RequestParam(required = false,defaultValue = "") String paramtr,
                                   @RequestParam(required = false,defaultValue = "user_name") String sort,
                                   @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                   @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        paramtr= userRequest.getUserName();
        model.addAttribute("paramtr",paramtr );
        model.addAttribute("sort",sort );
        model.addAttribute("pageNumber",pageNumber );
        model.addAttribute("pageSize",pageSize );
        userService.createUser(userRequest);
        model.addAttribute("user", new UserRequest());
        return new ModelAndView("users","users",userService.findUsers(paramtr, sort, pageNumber, pageSize));
    }

    @PutMapping("/update")
    public ModelAndView updateUser(@ModelAttribute("user") UserRequest userRequest,Model model,
                                   @RequestParam(required = false,defaultValue = "") String paramtr,
                                   @RequestParam(required = false,defaultValue = "user_name") String sort,
                                   @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                   @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        paramtr= userRequest.getUserName();
        model.addAttribute("paramtr",paramtr );
        model.addAttribute("sort",sort );
        model.addAttribute("pageNumber",pageNumber );
        model.addAttribute("pageSize",pageSize );
        userService.updateUser(userRequest.getId(),userRequest);
        model.addAttribute("user", new UserRequest());
        return new ModelAndView("users","users",userService.findUsers(paramtr, sort, pageNumber, pageSize));
    }
}
