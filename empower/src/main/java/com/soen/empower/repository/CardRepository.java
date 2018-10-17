package com.soen.empower.repository;

import org.springframework.data.repository.CrudRepository;

import com.soen.empower.entity.Card;

import java.util.List;

/**
 * The Interface CardRepository.
 */
public interface CardRepository extends CrudRepository<Card, String>{
    
    /**
     * Find all by order by id desc.
     *
     * @return the list
     */
    public List<Card> findAllByOrderByIdDesc();

}
