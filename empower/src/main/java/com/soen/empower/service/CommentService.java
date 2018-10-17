package com.soen.empower.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soen.empower.entity.Comment;
import com.soen.empower.repository.CommentRepository;

/**
 * The Class CommentService.
 */
@Service
public class CommentService {
	
	/** The comment repository. */
	@Autowired
	private CommentRepository commentRepository;
	
	/**
	 * Adds the.
	 *
	 * @param comment the comment
	 */
	public void add(Comment comment) {
		commentRepository.save(comment);
	}

}
