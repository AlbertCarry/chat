package org.example.chat.service;

import org.example.chat.forms.messageform.MessageForm;
import org.example.chat.model.User;
import org.example.chat.repository.UserRepository;
import org.example.chat.security.details.UserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class MessageFormServiceImpl implements MessageFormService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public MessageForm buildMessageForm(UserDetailImpl userDetail, Long id) {
        MessageForm messageForm = new MessageForm();
        User user = userRepository.getOne(id);
        messageForm.setIdFrom(userDetail.getUserId());
        messageForm.setFromName(userDetail.getFirstName() + " " +userDetail.getLastName());
        messageForm.setIdTo(id);
        messageForm.setToName(user.getFirstName() + " " +user.getLastName());
        messageForm.setData(new Date().toString());
        return messageForm;
    }
}
