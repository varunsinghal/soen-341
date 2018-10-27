package com.soen.empower.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soen.empower.entity.Card;
import com.soen.empower.repository.CardRepository;

/**
 *
 */
@Service
public class CardService {
	
	/** The card repository. */
	@Autowired
	private CardRepository cardRepository;
	
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
		return cardRepository.findByBelongsToId(userId);
	}
}
