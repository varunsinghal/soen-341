package com.soen.empower.repository;

import com.soen.empower.entity.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The Interface CardRepository.
 */
public interface CardRepository extends CrudRepository<Card, Long> {

    /**
     * Find all by order by id desc.
     *
     * @return the list
     */
    List<Card> findAllByOrderByIdDesc();


    /**
     * Find by belongs to id order by id desc.
     *
     * @param userId the user id
     * @return the list
     */
    List<Card> findByBelongsToIdOrderByIdDesc(long userId);

    /**
     * Find by id.
     *
     * @param id the id
     * @return the card
     */
    Card findById(long id);

    /**
     * Find by belongs to group id order by id desc.
     *
     * @param groupId the group id
     * @return the list
     */
    List<Card> findByBelongsToGroupIdOrderByIdDesc(long groupId);
}
