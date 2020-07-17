package org.example.chat.controller;

import org.example.chat.model.Friends;
import org.example.chat.model.RoleEnum;
import org.example.chat.model.User;
import org.example.chat.security.details.UserDetailImpl;
import org.example.chat.service.UserFriendsServiceImpl;
import org.example.chat.service.UserService;
import org.example.chat.service.friendservice.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    @Autowired
    private FriendsService friendsService;


    @GetMapping("/account")
    public String getAll(Authentication authentication,Model model) {
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        if(userDetail.getAuthorities().
                equals(Collections.singletonList(new SimpleGrantedAuthority(RoleEnum.Admin.name())))){
            return "redirect:/adminaccount";
        }
        List<User> userList = friendsService.showMyFriends(userDetail.getUserId());
        model.addAttribute("users", userList);
        return "account";
    }

    /*Admin Account*/
    @GetMapping("/adminaccount")
    public String getAllForAdmin(Authentication authentication,Model model) {
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        List<User> userList = friendsService.showMyFriends(userDetail.getUserId());
        model.addAttribute("users", userList);
        return "adminaccount";
    }

    /*Subscribe*/
    @GetMapping("/subscribe/{id}")
    public String subscribe(@PathVariable("id") Long id,Authentication authentication) {
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        if(!friendsService.friendship(new Friends(userDetail.getUserId(),id))){
            friendsService.add(new Friends(userDetail.getUserId(),id));
        }
        friendsService.showMyFriends(userDetail.getUserId());
        if(userDetail.getAuthorities().
                equals(Collections.singletonList(new SimpleGrantedAuthority(RoleEnum.Admin.name())))){
            return "redirect:/adminaccount";
        }
        return "account";
    }
        /*Unsubscribe*/
    @GetMapping("/unsubscribe/{id}")
    public String unsubscribe(@PathVariable("id") Long id,Authentication authentication) {
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        if(friendsService.friendship(new Friends(userDetail.getUserId(),id))){
            friendsService.deletedFriendships(userDetail.getUserId(),id);
        }
        if(userDetail.getAuthorities().
                equals(Collections.singletonList(new SimpleGrantedAuthority(RoleEnum.Admin.name())))){
            return "redirect:/adminaccount";
        }
        return "account";
    }


}
