package com.soen.empower.service;

import com.soen.empower.entity.Card;
import com.soen.empower.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    CardService(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }
    /**
     * Fetch all.
     *
     * @return the list
     */
    public List<Card> fetchAll() {
        return new ArrayList<>(cardRepository.findAllByOrderByIdDesc());
    }

    /**
     * Adds the.
     *
     * @param card the card
     */
    public void add(Card card) {
        cardRepository.save(card);
    }


    public List<Card> fetchCardsFor(long userId) {
        return cardRepository.findByBelongsToIdOrderByIdDesc(userId);
    }
}
