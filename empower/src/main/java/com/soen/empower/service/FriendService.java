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

    public List<Friend> viewSentRequests(long userId) {
        return friendRepository.findAllByUserIdAndEnabled(userId, 0);
    }


    public List<Friend> viewReceivedRequests(long userId) {
        return friendRepository.findAllByOtherUserIdAndEnabled(userId, 0);
    }

    public void accept(Friend friend) {
        friend.setEnabled(1);
        friendRepository.save(friend);
    }

    public void decline(Friend friend) {
        friendRepository.delete(friend);
    }
}
