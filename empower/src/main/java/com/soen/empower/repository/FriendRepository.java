package com.soen.empower.repository;

import com.soen.empower.entity.Friend;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/*
 * The interface FriendRepository
 */
public interface FriendRepository extends CrudRepository<Friend, String> {
    /**
     * Find friend by userId and OtheruserId of the users
     *
     * @param six arguments i.e. userId(Long), otherUserId(Long), enabled(int), userId1(Long), otherUserId1(Long), enabled1(int)
     * @return friend object
     */
    Friend findByUserIdAndOtherUserIdAndEnabledOrUserIdAndOtherUserIdAndEnabled(Long userId, Long otherUserId, int enabled, Long userId1, Long otherUserId1, int enabled1);

    /**
     * Find list of friend by userId and enabled
     *
     * @param two arguments i.e. userId(Long), enabled(int)
     * @return list of friends
     */

    List<Friend> findAllByUserIdAndEnabled(long userId, int enabled);

    /**
     * Find list of friend by otherUserId and enabled
     *
     * @param two arguments i.e. UserId(Long), enabled(int)
     * @return list of friends
     */

    List<Friend> findAllByOtherUserIdAndEnabled(long userId, int enabled);

    /**
     * Find friend by userId, otherUserId and enabled
     *
     * @param three arguments i.e. UserId(Long), otherUserId(Long), enabled(int)
     * @return friend object
     */
    Friend findByUserIdAndOtherUserIdAndEnabled(long userId, long otherUserId, int enabled);

    /**
     * Find list of friends by userId or otherUserId
     *
     * @param four arguments i.e. UserId(Long), enabled(int), userId1(Long), enabled1(int)
     * @return list of friends
     */
    List<Friend> findByUserIdAndEnabledOrOtherUserIdAndEnabled(long userId, int enabled, long userId1, int enabled1);
}
