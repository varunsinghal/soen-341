package com.soen.empower.service;

import com.soen.empower.entity.Card;
import com.soen.empower.fixture.Factory;
import com.soen.empower.repository.CardRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    private CardService cardService;

    @Before
    public void setUp() {
        cardService = new CardService(cardRepository);
    }

    @Test
    public void fetchAll_ReturnListOfCards() {
        when(cardRepository.findAllByOrderByIdDesc()).thenReturn(Arrays.asList(Factory.card1, Factory.card2));
        List<Card> cards = cardService.fetchAll();
        Assert.assertEquals(cards.size(), 2);
    }

    @Test
    public void fetchCardsFor() {
        when(cardRepository.findByBelongsToIdOrderByIdDesc(1L)).thenReturn(Arrays.asList(Factory.card1, Factory.card2));
        List<Card> cards = cardService.fetchCardsFor(1L);
        Assert.assertEquals(cards.size(), 2);
    }
}