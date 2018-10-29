package com.soen.empower.controller;

import com.soen.empower.fixture.Factory;
import com.soen.empower.service.FriendService;
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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FriendController.class, secure = false)
public class FriendControllerTest {

    @MockBean
    private FriendService friendService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getIndex_ReturnFriendList() throws Exception {
        when(friendService.fetchFriends(1L)).thenReturn(Arrays.asList(Factory.friend1));
        mockMvc.perform(MockMvcRequestBuilders.get("/friend").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("friend/index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("friends"))
                .andExpect(MockMvcResultMatchers.model().attribute("friends", Arrays.asList(Factory.friend1)));
    }

    @Test
    public void addFriend_RedirectToTheirProfile() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/friend/add", Factory.friend1))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/profile/2"));
    }

    @Test
    public void removeFriend_RedirectToTheirProfile() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/friend/remove?id=2").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/profile/2"));
    }

    @Test
    public void acceptFriend_ReturnReceivedRequestsPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/friend/accept", Factory.friend1))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/friend/received"));
    }

    @Test
    public void declineFriend_ReturnReceivedRequestsPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/friend/decline", Factory.friend1))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/friend/received"));
    }

    @Test
    public void getSent_ReturnsTemplate() throws Exception {
        when(friendService.viewSentRequests(1L)).thenReturn(Arrays.asList(Factory.friend1));

        mockMvc.perform(MockMvcRequestBuilders.get("/friend/sent").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("friend/sent"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("requests"))
                .andExpect(MockMvcResultMatchers.model().attribute("requests", Arrays.asList(Factory.friend1)));
    }

    @Test
    public void getReceived_ReturnsTemplate() throws Exception {
        when(friendService.viewReceivedRequests(1L)).thenReturn(Arrays.asList(Factory.friend1));

        mockMvc.perform(MockMvcRequestBuilders.get("/friend/received").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("friend/received"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("requests"))
                .andExpect(MockMvcResultMatchers.model().attribute("requests", Arrays.asList(Factory.friend1)));

    }
}
