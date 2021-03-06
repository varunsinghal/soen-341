package com.soen.empower.service;

import com.soen.empower.entity.Comment;
import com.soen.empower.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Class CommentService.
 */
@Service
public class CommentService {

    /**
     * The comment repository.
     */
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private TagService tagService;

    /**
     * Adds the.
     *
     * @param comment the comment
     */
    public void add(Comment comment) {
        comment = tagService.process(comment);
        commentRepository.save(comment);
    }

}
