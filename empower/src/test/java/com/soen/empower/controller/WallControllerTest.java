package com.soen.empower.controller;

import com.soen.empower.entity.Card;
import com.soen.empower.fixture.Factory;
import com.soen.empower.service.*;
import org.assertj.core.api.Assertions;
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


    @Test
    public void getIndex_ReturnsRedirectURL() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/wall").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/wall/1"));
    }


    @Test
    public void addPost_ReturnsWall() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/wall/addPost/1", Factory.card1))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void addPostImage_ReturnsWall() {
        // TODO : File based test cases.
    }

    @Test
    public void addComment_ReturnsHome() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/wall/addComment/1", Factory.comment1))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void friendVisitsWall_ReturnWall() throws Exception {
        // arrange
        Card card1 = Factory.card1;
        card1.setLikes(Arrays.asList(Factory.like1));
        card1.setDislikes(Arrays.asList());
        Card card2 = Factory.card2;
        card2.setLikes(Arrays.asList(Factory.like2));
        card2.setDislikes(Arrays.asList());
        // service mock
        when(friendService.areFriends(2L, 1L)).thenReturn("1");
        when(cardService.fetchCardsFor(1L)).thenReturn(Arrays.asList(card1, card2));
        when(userService.findById(1L)).thenReturn(Factory.user1);
        when(likeService.findCardsFor(2L)).thenReturn(Arrays.asList(Factory.card1.getId(), Factory.card2.getId()));
        when(dislikeService.findCardsFor(2L)).thenReturn(Arrays.asList());
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
        mockMvc.perform(MockMvcRequestBuilders.post("/wall/addLike/1", Factory.like1))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/wall/1"));
    }

    @Test
    public void addDislike_ReturnsWall() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/wall/addDislike/1", Factory.dislike1))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/wall/1"));
    }

    @Test
    public void removeLike_ReturnsWall() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/wall/removeLike/1", Factory.like1))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/wall/1"));
    }

    @Test
    public void removeDislike_ReturnsWall() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/wall/removeDislike/1", Factory.dislike1))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/wall/1"));
    }
}
