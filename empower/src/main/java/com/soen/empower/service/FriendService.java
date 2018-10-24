package com.soen.empower.service;

import com.soen.empower.entity.Friend;
import com.soen.empower.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    public boolean areFriends(long userId, long otherUserId) {
        Friend record = friendRepository.findByUserIdAndOtherUserIdOrUserIdAndOtherUserIdAndEnabled(userId, otherUserId, otherUserId, userId, 1);
        return record != null;
    }

    public void addRequest(Friend friend) {
        friend.setEnabled(0);
        friendRepository.save(friend);
    }

    public List<Friend> viewRequests(long userId) {
        return friendRepository.findAllByUserIdAndEnabled(userId, 0);
    }
}
