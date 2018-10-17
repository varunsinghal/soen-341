package com.soen.empower.repository;

import com.soen.empower.entity.Conversation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The Interface ConversationRepository.
 */
public interface ConversationRepository extends CrudRepository<Conversation, String> {

    /**
     * Find all by user id or other user id order by updated desc.
     *
     * @param currentUserId the current user id
     * @param otherUserId the other user id
     * @return the list
     */
    List<Conversation> findAllByUserIdOrOtherUserIdOrderByUpdatedDesc(Long currentUserId, Long otherUserId);

    /**
     * Find by user id and other user id.
     *
     * @param userId the user id
     * @param otherUserId the other user id
     * @return the conversation
     */
    Conversation findByUserIdAndOtherUserId(Long userId, Long otherUserId);

    /**
     * Find by id.
     *
     * @param id the id
     * @return the conversation
     */
    Conversation findById(Long id);

}
