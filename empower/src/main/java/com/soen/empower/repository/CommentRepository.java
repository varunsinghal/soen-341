package com.soen.empower.repository;

import org.springframework.data.repository.CrudRepository;

import com.soen.empower.entity.Comment;

public interface CommentRepository extends CrudRepository<Comment, String>{

}
