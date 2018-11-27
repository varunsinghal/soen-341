package com.soen.empower.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen.empower.entity.Friend;
import com.soen.empower.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Friend service.
 */
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

    /**
     * remove friend from repository, working of AND/OR - ( _ and _ and _) OR (_ and _ and _ and _ )
     *
     * @param userId      current logged in user driving the function
     * @param otherUserId profile viewed by the current user
     */
    public void removeFriend(long userId, long otherUserId) {
        Friend record = friendRepository.findByUserIdAndOtherUserIdAndEnabledOrUserIdAndOtherUserIdAndEnabled(userId, otherUserId, 1, otherUserId, userId, 1);
        friendRepository.delete(record);
    }

    /**
     * Add friend.
     *
     * @param friend entity
     */
    public void addFriend(Friend friend) {
        friend.setEnabled(0);
        friendRepository.save(friend);
    }

    /**
     * Accept friend request.
     *
     * @param friend entity
     */
    public void accept(Friend friend) {
        friend.setEnabled(1);
        friendRepository.save(friend);
    }

    /**
     * Decline friend request.
     *
     * @param friend entity
     */
    public void decline(Friend friend) {
        friendRepository.delete(friend);
    }

    /**
     * Fetch those records which have user id as current logged in user and enabled flag = 0.
     *
     * @param userId current logged in user
     * @return list of friend records
     */
    public List<Friend> viewSentRequests(long userId) {
        return friendRepository.findAllByUserIdAndEnabled(userId, 0);
    }


    /**
     * Fetch those records in which otherUserId is current logged in user and enabled flag is 0.
     *
     * @param userId current logged in user
     * @return list of friend
     */
    public List<Friend> viewReceivedRequests(long userId) {
        return friendRepository.findAllByOtherUserIdAndEnabled(userId, 0);
    }

    /**
     * Fetch all records where userId or otherUserId is of current logged in user and enabled
     * flag is 1.
     *
     * @param userId current logged in user
     * @return list of friend entity
     */
    public List<Friend> fetchFriends(long userId) {
        return friendRepository.findByUserIdAndEnabledOrOtherUserIdAndEnabled(userId, 1, userId, 1);
    }

    public String fetchFriendsForTag(long userId) {
        List<Object> users = new ArrayList<>();
        for (Friend friend : friendRepository.findByUserIdAndEnabledOrOtherUserIdAndEnabled(userId, 1, userId, 1)) {
            HashMap<String, String> hm = new HashMap<>();
            if (friend.getUser().getId() == userId) {
                hm.put("name", friend.getOtherUser().getFullName());
                hm.put("username", friend.getOtherUser().getUsername());
                hm.put("id", String.valueOf(friend.getOtherUser().getId()));
            } else {
                hm.put("name", friend.getUser().getFullName());
                hm.put("username", friend.getUser().getUsername());
                hm.put("id", String.valueOf(friend.getUser().getId()));
            }
            users.add(hm);
        }
        try {
            return new ObjectMapper().writeValueAsString(users);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
