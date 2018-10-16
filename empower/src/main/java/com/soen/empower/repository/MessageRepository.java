package com.soen.empower.repository;


import com.soen.empower.entity.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, String> {

    List<Message> findAllByConversationId(Long conversationId);
}
