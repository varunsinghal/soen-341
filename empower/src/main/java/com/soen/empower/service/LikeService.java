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
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private DislikeRepository dislikeRepository;

    @Autowired
    private UserRepository userRepository;


    public void add(Like like) {
        Dislike dislikeRecord = dislikeRepository.findByUserIdAndCardId(like.getUser().getId(), like.getCard().getId());
        Like likeRecord = likeRepository.findByUserIdAndCardId(like.getUser().getId(), like.getCard().getId());
        if (dislikeRecord != null) dislikeRepository.delete(dislikeRecord);
        if (likeRecord == null) likeRepository.save(like);
    }

    public void remove(Like like) {
        Like likeRecord = likeRepository.findByUserIdAndCardId(like.getUser().getId(), like.getCard().getId());
        likeRepository.delete(likeRecord);
    }

    public List<Long> findCardsFor(long loggedInUserId) {
        ArrayList<Long> result = new ArrayList<>();
        for (Like like : userRepository.findById(loggedInUserId).getLikes())
            result.add(like.getCard().getId());
        return result;
    }
}
