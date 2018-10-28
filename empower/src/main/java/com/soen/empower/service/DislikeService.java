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

@Service
public class DislikeService {

    @Autowired
    private DislikeRepository dislikeRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;


    public void add(Dislike dislike) {
        Dislike dislikeRecord = dislikeRepository.findByUserIdAndCardId(dislike.getUser().getId(), dislike.getCard().getId());
        Like likeRecord = likeRepository.findByUserIdAndCardId(dislike.getUser().getId(), dislike.getCard().getId());
        if (likeRecord != null) likeRepository.delete(likeRecord);
        if (dislikeRecord == null) dislikeRepository.save(dislike);
    }

    public void remove(Dislike dislike) {
        Dislike dislikeRecord = dislikeRepository.findByUserIdAndCardId(dislike.getUser().getId(), dislike.getCard().getId());
        dislikeRepository.delete(dislikeRecord);
    }

    public List<Long> findCardsFor(long loggedInUserId) {
        ArrayList<Long> result = new ArrayList<>();
        for (Dislike dislike : userRepository.findById(loggedInUserId).getDislikes())
            result.add(dislike.getCard().getId());
        return result;
    }
}
