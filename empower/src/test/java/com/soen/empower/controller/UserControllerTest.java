package com.soen.empower.controller;

import com.soen.empower.fixture.Factory;
import com.soen.empower.service.CardService;
import com.soen.empower.service.CommentService;
import com.soen.empower.service.FriendService;
import com.soen.empower.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @MockBean
    private CardService cardService;

    @MockBean
    private CommentService commentService;

    @MockBean
    private FriendService friendService;

    @Test
    public void getIndex_ReturnsRedirectURL() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/wall"));
    }

    @Test
    public void getHome_ReturnsRedirectURL() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/home"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/wall"));
//        when(cardService.fetchAll()).thenReturn(Arrays.asList(Factory.card1, Factory.card2));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/user/home"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("user/home"));
    }

    @Test
    public void addPost_ReturnsHome() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/addPost", Factory.card1))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void addComment_ReturnsHome() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/addComment", Factory.comment1))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void viewProfile_ReturnsProfilePage() throws Exception {
        when(userService.findById((long) 1)).thenReturn(Factory.user1);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/profile").sessionAttr("user_id", (long) 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/profile"));
    }

    @Test
    public void viewOtherProfile_ReturnsProfilePage() throws Exception {
        when(userService.findById((long) 2)).thenReturn(Factory.user2);
        when(friendService.areFriends((long) 1, (long) 2)).thenReturn("1");

        mockMvc.perform(MockMvcRequestBuilders.get("/user/profile/2").sessionAttr("user_id", (long) 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/profile"));
    }

    @Test
    public void editProfile_ReturnsProfilePage() throws Exception {
        when(userService.findById((long) 1)).thenReturn(Factory.user1);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/edit").sessionAttr("user_id", (long) 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/profileEdit"));
    }

    @Test
    public void saveProfile_ReturnsProfilePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/save", Factory.user1))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

}
