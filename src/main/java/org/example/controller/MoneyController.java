package org.example.controller;

import jakarta.validation.Valid;
import org.example.controller.request.MoneyRequest;
import org.example.service.MoneyService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/money")
@Validated
public class MoneyController {
    @Autowired
    private MoneyService moneyService;
    @Autowired
    private UserService userService;
    @GetMapping("/{userId}/redirect/update")
    public ModelAndView findById(@PathVariable Long userId, Model model) {
        model.addAttribute("money",new MoneyRequest());
        model.addAttribute("currentUserId",userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        model.addAttribute("moneyByCurrentUserId", moneyService.findByUserId(userId));
        return new ModelAndView("money", "money", moneyService.findByUserId(userId));
    }
    @PostMapping("/{userId}/create")
    public ModelAndView createMoney( @PathVariable Long userId,Model model) {
        moneyService.createMoney(userId);
        model.addAttribute("currentUserId",userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        model.addAttribute("money", new MoneyRequest());
        return new ModelAndView("menu");
    }

    @DeleteMapping("/{userId}/delete")
    public ModelAndView deleteMoney(@PathVariable Long userId, Model model) {
        moneyService.deleteMoney(userId);
        model.addAttribute("money", new MoneyRequest());
        model.addAttribute("currentUserId",userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        model.addAttribute("moneyByCurrentUserId",moneyService.findByUserId(userId));
        return new ModelAndView("menu");
    }
    @PutMapping("/update")
    public ModelAndView updateMoney(@ModelAttribute("money") @Valid MoneyRequest moneyRequest, Model model) {
        moneyService.updateMoney(moneyRequest.getUserId(),moneyRequest.getTransfer());
        model.addAttribute("money",new MoneyRequest());
        model.addAttribute("currentUserId",userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        model.addAttribute("moneyByCurrentUserId", moneyService.findByUserId(moneyRequest.getUserId()));
        return new ModelAndView("menu");
    }

}
