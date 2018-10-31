package com.soen.empower.repository;

import com.soen.empower.entity.Like;
import org.springframework.data.repository.CrudRepository;

/*
 * The interface LikeRepository
 */

public interface LikeRepository extends CrudRepository<Like, String> {
	/*	Find by like by userId and cardId
	 * 
	 * @param two arguments of Long, id and id1
	 * @return Like object
	 */
    Like findByUserIdAndCardId(Long id, Long id1);
}
