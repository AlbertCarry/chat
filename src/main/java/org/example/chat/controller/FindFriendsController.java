package org.example.chat.controller;

import org.example.chat.forms.findfriendform.FindFriendForm;
import org.example.chat.model.RoleEnum;
import org.example.chat.model.User;
import org.example.chat.security.details.UserDetailImpl;
import org.example.chat.service.friendservice.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class FindFriendsController {
    @Autowired
    private FriendsService friendsService;


    @GetMapping("/findfriends")
    public String findAll(Model model) {
        FindFriendForm findFriendForm = new FindFriendForm();
        model.addAttribute("findFriendForm", findFriendForm);
//
        return "findfriends";
    }

    @PostMapping("/findfriends")
    public String findAllPost(FindFriendForm findFriendForm,Model model){
        if(findFriendForm.getValue().length() < 0){
            return "findfriends";
        }
        String name = findFriendForm.getValue().substring(0,1).toUpperCase() +
                findFriendForm.getValue().substring(1).toLowerCase();
        List<User> userList = friendsService.findFriend(name);
        model.addAttribute("users",userList );
        return "findfriends";
    }


}
