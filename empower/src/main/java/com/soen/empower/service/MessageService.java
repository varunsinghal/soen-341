package com.soen.empower.service;

import com.soen.empower.entity.Message;
import com.soen.empower.entity.User;
import com.soen.empower.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class MessageService.
 */
@Service
public class MessageService {

    /** The message repository. */
    @Autowired
    private MessageRepository messageRepository;

    /**
     * Adds the.
     *
     * @param message the message
     * @return the message
     */
    public Message add(Message message) {
        return messageRepository.save(message);
    }

    /**
     * Fetch.
     *
     * @param conversationId the conversation id
     * @return the list
     */
    public List<Message> fetch(Long conversationId){
        return messageRepository.findAllByConversationId(conversationId);
    }
}
