package com.soen.empower.repository;

import com.soen.empower.entity.Dislike;
import org.springframework.data.repository.CrudRepository;
/*
 * The interface Dislikerepository
 */
public interface DislikeRepository extends CrudRepository<Dislike, Long> {
	
    /**
     * Find dislike by userId and cardId
     *
     * @param two arguments i.e. userId(Long), cardId(Long)
     * @return Dislike object
     */
    Dislike findByUserIdAndCardId(Long id, Long id1);
}
