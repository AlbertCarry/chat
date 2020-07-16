package org.example.chat.service;

import org.example.chat.forms.messageform.MessageForm;
import org.example.chat.model.Messages;

import java.util.List;

public interface MessagesService {
    List<Messages> showMyDialogWith(Long meId, Long friendId);
    Messages buildMessage(MessageForm messageForm);

}
