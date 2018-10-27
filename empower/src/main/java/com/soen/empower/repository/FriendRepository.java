package com.soen.empower.repository;

import com.soen.empower.entity.Friend;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendRepository extends CrudRepository<Friend, String> {
    Friend findByUserIdAndOtherUserIdAndEnabledOrUserIdAndOtherUserIdAndEnabled(Long userId, Long otherUserId, int enabled, Long userId1, Long otherUserId1, int enabled1);

    List<Friend> findAllByUserIdAndEnabled(long userId, int enabled);

    List<Friend> findAllByOtherUserIdAndEnabled(long userId, int enabled);

    Friend findByUserIdAndOtherUserIdAndEnabled(long userId, long otherUserId, int enabled);

    List<Friend> findByUserIdAndEnabledOrOtherUserIdAndEnabled(long userId, int enabled, long userId1, int enabled1);
}
