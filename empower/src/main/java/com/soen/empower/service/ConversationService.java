package com.soen.empower.service;

import com.soen.empower.entity.Conversation;
import com.soen.empower.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Class ConversationService.
 */
@Service
public class ConversationService {

    /** The conversation repository. */
    @Autowired
    private ConversationRepository conversationRepository;

    /**
     * Fetch all.
     *
     * @param currentUserId the current user id
     * @return the list
     */
    public List<Conversation> fetchAll(Long currentUserId) {
        return conversationRepository.findAllByUserIdOrOtherUserIdOrderByUpdatedDesc(currentUserId, currentUserId);
    }

    /**
     * Resolve.
     *
     * @param conversation the conversation
     * @return the conversation
     */
    public Conversation resolve(Conversation conversation) {
        Conversation resolvedConversation = conversationRepository.findByUserIdAndOtherUserId(
                conversation.getUser().getId(), conversation.getOtherUser().getId());
        if (resolvedConversation == null) resolvedConversation = conversationRepository.findByUserIdAndOtherUserId(
                conversation.getOtherUser().getId(), conversation.getUser().getId());
        if(resolvedConversation == null) resolvedConversation = conversationRepository.save(conversation);
        return resolvedConversation;
    }

    /**
     * Fetch by id.
     *
     * @param id the id
     * @return the conversation
     */
    public Conversation fetchById(Long id){
        return conversationRepository.findById(id);
    }

    /**
     * Update.
     *
     * @param resolvedConversation the resolved conversation
     */
    public void update(Conversation resolvedConversation) {
        conversationRepository.save(resolvedConversation);
    }
}
