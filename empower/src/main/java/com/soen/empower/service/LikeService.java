package com.soen.empower.service;

import com.soen.empower.entity.Dislike;
import com.soen.empower.entity.Like;
import com.soen.empower.repository.DislikeRepository;
import com.soen.empower.repository.LikeRepository;
import com.soen.empower.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Like service.
 */
@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private DislikeRepository dislikeRepository;

    @Autowired
    private UserRepository userRepository;


    /**
     * Add like to the system by first deleting its dislike.
     *
     * @param like entity object received as like
     */
    public void add(Like like) {
        Dislike dislikeRecord = dislikeRepository.findByUserIdAndCardId(like.getUser().getId(), like.getCard().getId());
        Like likeRecord = likeRepository.findByUserIdAndCardId(like.getUser().getId(), like.getCard().getId());
        if (dislikeRecord != null) dislikeRepository.delete(dislikeRecord);
        if (likeRecord == null) likeRepository.save(like);
    }

    /**
     * Find the like record and remove it. This is done to make the post neutral.
     *
     * @param like entity object
     */
    public void remove(Like like) {
        Like likeRecord = likeRepository.findByUserIdAndCardId(like.getUser().getId(), like.getCard().getId());
        likeRepository.delete(likeRecord);
    }

    /**
     * To highlight the like button, we need the list of likes for the given user.
     *
     * @param loggedInUserId user id
     * @return list of long card ids.
     */
    public List<Long> findCardsFor(long loggedInUserId) {
        ArrayList<Long> result = new ArrayList<>();
        for (Like like : userRepository.findById(loggedInUserId).getLikes())
            result.add(like.getCard().getId());
        return result;
    }
}
