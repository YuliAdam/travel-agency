package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.controller.request.UserRequest;
import org.example.entity.characteristic.Role;
import org.example.service.MoneyService;
import org.example.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final MoneyService moneyService;

    @GetMapping("/search")
    public ModelAndView findUsers(Model model, @RequestParam(required = false) String paramtr,
                                  @RequestParam(required = false, defaultValue = "user_name") String sort,
                                  @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                  @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        ModelAttributes.paginationAttributes(model, paramtr, sort, pageNumber, pageSize);
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        return new ModelAndView("users", "users", userService.findUsers(paramtr, sort, pageNumber, pageSize));
    }

    @GetMapping("/redirect/create")
    public ModelAndView getUser(Model model) {
        model.addAttribute("user", new UserRequest());
        return new ModelAndView("addUser");
    }

    @GetMapping(value = "/{id}/redirect/update")
    public ModelAndView getUser(@PathVariable Long id, Model model) {
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        model.addAttribute("allRoles", Role.values());
        return new ModelAndView("user", "user", userService.getUser(id));
    }

    @DeleteMapping("/{id}/delete")
    public ModelAndView deleteUser(@PathVariable Long id, Model model,
                                   @RequestParam(required = false) String paramtr,
                                   @RequestParam(required = false, defaultValue = "user_name") String sort,
                                   @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                   @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        ModelAttributes.paginationAttributes(model, paramtr, sort, pageNumber, pageSize);
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        userService.deleteUser(id);
        model.addAttribute("user", new UserRequest());
        return new ModelAndView("users", "users", userService.findUsers(paramtr, sort, pageNumber, pageSize));
    }

    @PostMapping("/create")
    public ModelAndView createUser(@ModelAttribute("user") @Valid UserRequest userRequest, Model model,
                                   @RequestParam(required = false) String paramtr,
                                   @RequestParam(required = false, defaultValue = "user_name") String sort,
                                   @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                   @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        paramtr = userRequest.getUserName();
        ModelAttributes.paginationAttributes(model, paramtr, sort, pageNumber, pageSize);
        userService.createUser(userRequest);
        model.addAttribute("user", new UserRequest());
        return new ModelAndView("/login");
    }

    @PutMapping("/update")
    public ModelAndView updateUser(@ModelAttribute("user") @Valid UserRequest userRequest, Model model,
                                   @RequestParam(required = false) String paramtr,
                                   @RequestParam(required = false, defaultValue = "user_name") String sort,
                                   @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                   @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        paramtr = userRequest.getUserName();
        ModelAttributes.paginationAttributes(model, paramtr, sort, pageNumber, pageSize);
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        userService.updateUser(userRequest.getId(), userRequest);
        model.addAttribute("user", new UserRequest());
        model.addAttribute("allRoles", Role.values());
        return new ModelAndView("users", "users", userService.findUsers(paramtr, sort, pageNumber, pageSize));
    }
}
