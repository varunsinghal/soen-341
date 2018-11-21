package com.soen.empower.controller;

import com.soen.empower.entity.User;
import com.soen.empower.service.CardService;
import com.soen.empower.service.CommentService;
import com.soen.empower.service.FriendService;
import com.soen.empower.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
    private User user;
    private User friend;


    @Before
    public void setUp(){
        user = new User();
        user.setId(1L);

        friend = new User();
        friend.setId(2L);

    }

    @Test
    public void getIndex_ReturnsRedirectURL() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/wall"));
    }

    @Test
    public void getHome_ReturnsToWallUrl() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/home"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/wall"));
    }


    @Test
    public void viewProfile_ReturnsProfilePage() throws Exception {
        when(userService.findById((long) 1)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/profile").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/profile"));
    }

    @Test
    public void viewOtherProfile_ReturnsProfilePage() throws Exception {
        when(userService.findById(2L)).thenReturn(friend);
        when(friendService.areFriends(1L, 2L)).thenReturn("1");

        mockMvc.perform(MockMvcRequestBuilders.get("/user/profile/2").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/profile"));
    }

    @Test
    public void editProfile_ReturnsProfilePage() throws Exception {
        when(userService.findById((long) 1)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/edit").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/profileEdit"));
    }

    @Test
    public void saveProfile_ReturnsProfilePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/save", 1L))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

}
