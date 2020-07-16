package org.example.chat.controller;

import org.example.chat.model.User;
import org.example.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountUpDateController {
    @Autowired
    private UserService userService;
    @GetMapping("/account-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "account-update";
    }
    @PostMapping("/account-update")
    public String updateUser(User user){
        userService.upDate(user);
//        userService.upDate(user);
        return "redirect:/adminaccount";
    }
}
