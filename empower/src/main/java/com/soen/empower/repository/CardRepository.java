package com.soen.empower.repository;

import com.soen.empower.entity.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CardRepository extends CrudRepository<Card, String> {

    public List<Card> findAllByOrderByIdDesc();

}
