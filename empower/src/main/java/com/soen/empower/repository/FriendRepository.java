package com.soen.empower.repository;

import com.soen.empower.entity.Friend;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendRepository extends CrudRepository<Friend, String> {
    Friend findByUserIdAndOtherUserIdOrUserIdAndOtherUserIdAndEnabled(Long userId, Long otherUserId, Long userId1, Long otherUserId1, int enabled);

    List<Friend> findAllByUserIdAndEnabled(long userId, int enabled);
}
