package com.soen.empower.service;

import com.soen.empower.entity.Conversation;
import com.soen.empower.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    public List<Conversation> fetchAll(Long currentUserId) {
        return conversationRepository.findAllByUserIdOrOtherUserId(currentUserId, currentUserId);
    }

    public Conversation resolve(Conversation conversation) {
        Conversation resolvedConversation = conversationRepository.findByUserIdAndOtherUserId(conversation.getUser().getId(), conversation.getOtherUser().getId());
        if (resolvedConversation == null) resolvedConversation = conversationRepository.findByUserIdAndOtherUserId(conversation.getOtherUser().getId(), conversation.getUser().getId());
        if(resolvedConversation == null) resolvedConversation = conversationRepository.save(conversation);
        return resolvedConversation;
    }

    public Conversation fetchById(Long id){
        return conversationRepository.findById(id);
    }

    public void update(Conversation resolvedConversation) {
        conversationRepository.save(resolvedConversation);
    }
}
