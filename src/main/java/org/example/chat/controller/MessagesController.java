package org.example.chat.controller;

import org.example.chat.forms.messageform.MessageForm;
import org.example.chat.model.Messages;
import org.example.chat.model.User;
import org.example.chat.repository.MessagesRepository;
import org.example.chat.security.details.UserDetailImpl;
import org.example.chat.service.MessageFormService;
import org.example.chat.service.MessagesService;
import org.example.chat.service.MessagesServiceImpl;
import org.example.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class MessagesController {
    @Autowired
    private UserService userService;
    @Autowired
    private MessagesRepository messagesRepository;
    @Autowired
    private MessagesService messagesService;
    @Autowired
    private MessageFormService messageFormService;
    /*--------------------Messages--------------------------------------------*/
    @GetMapping("/messages/{id}")
    public String messagesContr(@PathVariable("id") Long id,Authentication authentication,Model model,
                                 Model modelMes){
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
        /* */
       List<Messages> newListMess = messagesService.showMyDialogWith(userDetail.getUserId(),id);
        /* */
        modelMes.addAttribute("messages",newListMess);
        MessageForm messageForm = messageFormService.buildMessageForm(userDetail,id);
        model.addAttribute("messageform",messageForm);
        return "messages";
    }

    @PostMapping("/messages")
    public void messagesContrPost(MessageForm messageForm, Authentication authentication,Model model,Model modelMes) {
        Messages messages = messagesService.buildMessage(messageForm);
        messagesRepository.save(messages);
        messagesContr(messageForm.getIdTo(),authentication,model,modelMes);
    }
}
