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

    /**
     * areFriends calculate the friendship status between two users.
     * Description of status bits
     * '1' - are friends
     * '0r' - friendship request received
     * '0s' - friendship request sent
     * '-1' - friendship request NOT FOUND.
     *
     * @param userId      current logged in user driving the function
     * @param otherUserId profile viewed by the current user
     * @return the status of the frienship
     */
    public String areFriends(long userId, long otherUserId) {
        Friend friend = friendRepository.findByUserIdAndOtherUserIdAndEnabledOrUserIdAndOtherUserIdAndEnabled(userId, otherUserId, 1, otherUserId, userId, 1);
        if (friend != null) return "1";
        Friend sent = friendRepository.findByUserIdAndOtherUserIdAndEnabled(userId, otherUserId, 0);
        if (sent != null) return "0s";
        Friend received = friendRepository.findByUserIdAndOtherUserIdAndEnabled(otherUserId, userId, 0);
        if (received != null) return "0r";
        return "-1";
    }

    public void removeFriend(long userId, long otherUserId) {
        Friend record = friendRepository.findByUserIdAndOtherUserIdAndEnabledOrUserIdAndOtherUserIdAndEnabled(userId, otherUserId, 1, otherUserId, userId, 1);
        friendRepository.delete(record);
    }

    public void addFriend(Friend friend) {
        friend.setEnabled(0);
        friendRepository.save(friend);
    }

    public void accept(Friend friend) {
        friend.setEnabled(1);
        friendRepository.save(friend);
    }

    public void decline(Friend friend) {
        friendRepository.delete(friend);
    }

    public List<Friend> viewSentRequests(long userId) {
        return friendRepository.findAllByUserIdAndEnabled(userId, 0);
    }


    public List<Friend> viewReceivedRequests(long userId) {
        return friendRepository.findAllByOtherUserIdAndEnabled(userId, 0);
    }

    public List<Friend> fetchFriends(long userId) {
        return friendRepository.findByUserIdAndEnabledOrOtherUserIdAndEnabled(userId, 1, userId, 1);
    }
}
