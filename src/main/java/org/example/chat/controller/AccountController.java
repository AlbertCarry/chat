package org.example.chat.controller;

import org.example.chat.model.RoleEnum;
import org.example.chat.model.User;
import org.example.chat.security.details.UserDetailImpl;
import org.example.chat.service.UserFriendsServiceImpl;
import org.example.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class AccountController {
    @Autowired
    private UserFriendsServiceImpl userFriendsService;
    @Autowired
    private UserService userService;


    @GetMapping("/account")
    public String getAll(Authentication authentication,Model model) {
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        if(userDetail.getAuthorities().
                equals(Collections.singletonList(new SimpleGrantedAuthority(RoleEnum.Admin.name())))){
            return "redirect:/adminaccount";
        }
        List<Optional<User>> user = userFriendsService.findAllBesidesMe(userDetail.getUserId());
        List<User> userList = new ArrayList<>();
        for (Optional<User> u : user){
            userList.add(u.get());
        }
        model.addAttribute("users", userList);
        return "account";
    }

    /*Admin Account*/
    @GetMapping("/adminaccount")
    public String getAllForAdmin(Authentication authentication,Model model) {
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        List<Optional<User>> user = userFriendsService.findAllBesidesMe(userDetail.getUserId());
        List<User> userList = new ArrayList<>();
        for (Optional<User> u : user){
            userList.add(u.get());
        }
        model.addAttribute("users", userList);
        return "adminaccount";
    }

}
