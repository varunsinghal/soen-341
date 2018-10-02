package com.soen.empower.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soen.empower.entity.Comment;
import com.soen.empower.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	public void add(Comment comment) {
		commentRepository.save(comment);
	}

}
