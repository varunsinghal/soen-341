package com.soen.empower.service;

import com.soen.empower.entity.Message;
import com.soen.empower.entity.User;
import com.soen.empower.repository.MessageRepository;
import com.soen.empower.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The Class MessageService.
 */
@Service
public class MessageService {

    /** The message repository. */
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

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

    public List<Object> findUsersForNewMessage(String search){
        List<Object> users = new ArrayList<>();
        for (User user : userRepository.findByFullNameContainingIgnoreCase(search)) {
            HashMap<String, String> hm = new HashMap<>();
            hm.put("label", user.getFullName());
            hm.put("value", String.valueOf(user.getId()));
            users.add(hm);
        }
        return users;
    }
}
