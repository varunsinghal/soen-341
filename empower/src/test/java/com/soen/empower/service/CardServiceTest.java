package com.soen.empower.service;

import com.soen.empower.entity.*;
import com.soen.empower.repository.CardRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    private CardService cardService;
    private Card card;
    private Comment comment;
    private User user1;
    private User user2;
    private Like like;
    private Dislike dislike;

    @Before
    public void setUp() {
        cardService = new CardService(cardRepository);


        user1 = new User();
        user1.setId(1L);

        user2 = new User();
        user2.setId(2L);

        card = new Card();
        card.setId(1L);
        card.setBelongsTo(user1);
        card.setUser(user2);

        comment = new Comment();
        comment.setId(1L);
        comment.setUser(user1);
        comment.setCard(card);

        like = new Like();
        like.setCard(card);
        like.setId(1L);
        like.setUser(user1);

        dislike = new Dislike();
        dislike.setId(1L);
        dislike.setCard(card);
        dislike.setUser(user2);


        card.setLikes(Collections.singletonList(like));
        card.setDislikes(Collections.emptyList());
    }


    @Test
    public void fetchAll_ReturnListOfCards() {
        when(cardRepository.findAllByOrderByIdDesc()).thenReturn(Collections.singletonList(card));
        List<Card> cards = cardService.fetchAll();
        Assert.assertEquals(cards.size(), 1);
    }

    @Test
    public void fetchCardsFor() {
        when(cardRepository.findByBelongsToIdOrderByIdDesc(1L)).thenReturn(Collections.singletonList(card));
        List<Card> cards = cardService.fetchCardsFor(1L);
        Assert.assertEquals(cards.size(), 1);
    }
}