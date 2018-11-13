package com.soen.empower.service;

import com.soen.empower.entity.Card;
import com.soen.empower.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Feed story is referred as cards.
 */
@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    /**
     * Added placeholder for dependency injection to support test cases.
     *
     * @param cardRepository repository access.
     */
    CardService(CardRepository cardRepository) {
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
     * Adds the card to repository.
     *
     * @param card the card
     */
    public void add(Card card) {
        cardRepository.save(card);
    }


    /**
     * Fetch cards for a given user's wall.
     *
     * @param userId user id
     * @return list of cards
     */
    public List<Card> fetchCardsFor(long userId) {
        return cardRepository.findByBelongsToIdOrderByIdDesc(userId);
    }

    /**
     * Fetch cards for a given group's wall.
     *
     * @param groupId group id
     * @return list of cards
     */
    public List<Card> fetchCardsForGroup(long groupId) {
        return cardRepository.findByBelongsToGroupIdOrderByIdDesc(groupId);
    }
}
