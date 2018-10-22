package com.soen.empower.repository;

import com.soen.empower.entity.Comment;
import org.springframework.data.repository.CrudRepository;


public interface CommentRepository extends CrudRepository<Comment, String> {

}
