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
 * Dislike service.
 */
@Service
public class DislikeService {

    @Autowired
    private DislikeRepository dislikeRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;


    /**
     * It will try to delete the like by searching for it in the likeRepository. Then, it will
     * add a dislike to the system.
     *
     * @param dislike entity
     */
    public void add(Dislike dislike) {
        Dislike dislikeRecord = dislikeRepository.findByUserIdAndCardId(dislike.getUser().getId(), dislike.getCard().getId());
        Like likeRecord = likeRepository.findByUserIdAndCardId(dislike.getUser().getId(), dislike.getCard().getId());
        if (likeRecord != null) likeRepository.delete(likeRecord);
        if (dislikeRecord == null) dislikeRepository.save(dislike);
    }

    /**
     * To get neutral for the wall posts.
     *
     * @param dislike entity
     */
    public void remove(Dislike dislike) {
        Dislike dislikeRecord = dislikeRepository.findByUserIdAndCardId(dislike.getUser().getId(), dislike.getCard().getId());
        dislikeRepository.delete(dislikeRecord);
    }

    /**
     * Fetch cards which have been disliked by the user. Hence, using it to highlight the disliked button.
     *
     * @param loggedInUserId logged in user id
     * @return list of card ids liked by user id
     */
    public List<Long> findCardsFor(long loggedInUserId) {
        ArrayList<Long> result = new ArrayList<>();
        for (Dislike dislike : userRepository.findById(loggedInUserId).getDislikes())
            result.add(dislike.getCard().getId());
        return result;
    }
}
