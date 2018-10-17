package com.soen.empower.repository;


import com.soen.empower.entity.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The Interface MessageRepository.
 */
public interface MessageRepository extends CrudRepository<Message, String> {

    /**
     * Find all by conversation id.
     *
     * @param conversationId the conversation id
     * @return the list
     */
    List<Message> findAllByConversationId(Long conversationId);
}
