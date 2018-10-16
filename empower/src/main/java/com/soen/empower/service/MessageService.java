package com.soen.empower.service;

import com.soen.empower.entity.Message;
import com.soen.empower.entity.User;
import com.soen.empower.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message add(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> fetch(Long conversationId){
        return messageRepository.findAllByConversationId(conversationId);
    }
}
