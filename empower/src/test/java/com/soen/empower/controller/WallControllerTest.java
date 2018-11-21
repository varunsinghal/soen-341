package com.soen.empower.controller;

import com.soen.empower.entity.*;
import com.soen.empower.service.*;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = WallController.class, secure = false)
public class WallControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    @MockBean
    private UserService userService;

    @MockBean
    private FriendService friendService;

    @MockBean
    private CommentService commentService;

    @MockBean
    private LikeService likeService;

    @MockBean
    private DislikeService dislikeService;

    @MockBean
    private StorageService storageService;

    @MockBean
    private NotificationService notificationService;
    private Card card;
    private Comment comment;
    private User user1;
    private User user2;
    private Like like;
    private Dislike dislike;

    @Before
    public void setUp(){
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
    public void getIndex_ReturnsRedirectURL() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/wall").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/wall/1"));
    }


    @Test
    public void addPost_ReturnsWall() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/wall/addPost/1", card))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void addComment_ReturnsHome() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/wall/addComment/1", comment))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void friendVisitsWall_ReturnWall() throws Exception {
        // service mock
        when(friendService.areFriends(2L, 1L)).thenReturn("1");
        when(cardService.fetchCardsFor(1L)).thenReturn(Collections.singletonList(card));
        when(userService.findById(1L)).thenReturn(user1);
        when(likeService.findCardsFor(2L)).thenReturn(Collections.emptyList());
        when(dislikeService.findCardsFor(2L)).thenReturn(Collections.singletonList(2L));
        // perform
        mockMvc.perform(MockMvcRequestBuilders.get("/wall/1").sessionAttr("user_id", 2L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("wall/index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("cards", "owner", "likedCards", "dislikedCards"))
        ;
    }

    @Test
    public void AnyUserVisitsWall_ReturnsAccessDenied() {
        when(friendService.areFriends(1L, 2L)).thenReturn("-1");
        Assertions.assertThatThrownBy(() -> mockMvc.perform(MockMvcRequestBuilders.get("/wall/2").sessionAttr("user_id", 1L)))
                .hasCause(new AccessDeniedException("403 returned"));

    }

    @Test
    public void addLike_ReturnsWall() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/wall/addLike/1", like))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/wall/1"));
    }

    @Test
    public void addDislike_ReturnsWall() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/wall/addDislike/1", dislike))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/wall/1"));
    }

    @Test
    public void removeLike_ReturnsWall() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/wall/removeLike/1", like))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/wall/1"));
    }

    @Test
    public void removeDislike_ReturnsWall() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/wall/removeDislike/1", dislike))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/wall/1"));
    }
}
