package com.soen.empower.repository;

import com.soen.empower.entity.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
/*
 * The interface CardRepository
 */

public interface CardRepository extends CrudRepository<Card, Long> {
	/* find all posts by descending order
	 * @return list of cards(post)
	 */
    List<Card> findAllByOrderByIdDesc();

    /* find all posts in descending order based on userId
     * @param Long userId
     * @return list of cards (posts)
     */
    List<Card> findByBelongsToIdOrderByIdDesc(long userId);

    Card findById(long id);
}
