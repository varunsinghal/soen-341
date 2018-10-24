package com.soen.empower.repository;

import com.soen.empower.entity.Friend;
import org.springframework.data.repository.CrudRepository;

public interface FriendRepository extends CrudRepository<Friend, String> {
    Friend findByUserIdAndOtherUserIdOrUserIdAndOtherUserIdAndEnabled(Long userId, Long otherUserId, Long userId1, Long otherUserId1, int enabled);
}
