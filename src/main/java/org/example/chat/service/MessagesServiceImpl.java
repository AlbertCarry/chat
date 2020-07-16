package org.example.chat.service;

import org.example.chat.forms.messageform.MessageForm;
import org.example.chat.model.Messages;
import org.example.chat.repository.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessagesServiceImpl implements MessagesService {
    @Autowired
    private MessagesRepository messagesRepository;

    @Override
    public List<Messages> showMyDialogWith(Long meId, Long friendId) {
        List<Optional<Messages>> messages = messagesRepository.getAllById(meId,friendId);
        List<Messages> messagesList = new ArrayList<>();
        for (Optional<Messages> m : messages){
            messagesList.add(m.get());
        }
        return messagesList;
    }

    @Override
    public Messages buildMessage(MessageForm messageForm) {
        Messages messages = new Messages();
        messages.setFrom(messageForm.getIdFrom());
        messages.setFromName(messageForm.getFromName());
        messages.setTo(messageForm.getIdTo());
        messages.setToName(messageForm.getToName());
        messages.setMessage(messageForm.getMessage());
        messages.setDate(messageForm.getData());
        return messages;
    }
}
