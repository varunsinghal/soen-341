package com.soen.empower.repository;

import com.soen.empower.entity.Conversation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConversationRepository extends CrudRepository<Conversation, String> {

    List<Conversation> findAllByUserIdOrOtherUserIdOrderByUpdatedDesc(Long currentUserId, Long otherUserId);

    Conversation findByUserIdAndOtherUserId(Long userId, Long otherUserId);

    Conversation findById(Long id);

}
