package com.soen.empower.service;

import com.soen.empower.entity.Friend;
import com.soen.empower.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    public boolean areFriends(long userId, long otherUserId) {
        Friend record = friendRepository.findByUserIdAndOtherUserIdOrUserIdAndOtherUserIdAndEnabled(userId, otherUserId, otherUserId, userId, 1);
        return record != null;
    }
}
