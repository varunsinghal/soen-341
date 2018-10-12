package com.soen.empower.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soen.empower.entity.Card;
import com.soen.empower.repository.CardRepository;

@Service
public class CardService {
	
	@Autowired
	private CardRepository cardRepository;
	
	public List<Card> fetchAll() {
		return new ArrayList<>(cardRepository.findAllByOrderByIdDesc());
	}
	
	public void add(Card card) {
		cardRepository.save(card);
	}

}
