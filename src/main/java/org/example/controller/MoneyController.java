package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.controller.request.MoneyRequest;
import org.example.service.MoneyService;
import org.example.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/money")
@Validated
@RequiredArgsConstructor
public class MoneyController {

    private final MoneyService moneyService;

    private final UserService userService;

    @GetMapping("/{userId}/redirect/update")
    public ModelAndView findById(@PathVariable Long userId, Model model) {
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        return new ModelAndView("money", "money", moneyService.findByUserId(userId));
    }

    @PostMapping("/{userId}/create")
    public ModelAndView createMoney(@PathVariable Long userId, Model model) {
        moneyService.createMoney(userId);
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        return new ModelAndView("menu");
    }

    @DeleteMapping("/{userId}/delete")
    public ModelAndView deleteMoney(@PathVariable Long userId, Model model) {
        moneyService.deleteMoney(userId);
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        return new ModelAndView("menu");
    }

    @PutMapping("/update")
    public ModelAndView updateMoney(@ModelAttribute("money") @Valid MoneyRequest moneyRequest, Model model) {
        moneyService.updateMoney(moneyRequest.getUserId(), moneyRequest.getTransfer());
        ModelAttributes.moneyAttributes(model, moneyService, userService);
        return new ModelAndView("menu");
    }

}
