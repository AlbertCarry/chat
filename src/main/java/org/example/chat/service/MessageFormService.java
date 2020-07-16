package org.example.chat.service;

import org.example.chat.forms.messageform.MessageForm;
import org.example.chat.model.User;
import org.example.chat.security.details.UserDetailImpl;
import org.springframework.security.core.userdetails.UserDetails;

public interface MessageFormService {
    MessageForm buildMessageForm(UserDetailImpl userDetail, Long id);
}
