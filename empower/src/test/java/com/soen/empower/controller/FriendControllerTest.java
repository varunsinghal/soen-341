package com.soen.empower.controller;

import com.soen.empower.entity.Friend;
import com.soen.empower.entity.User;
import com.soen.empower.service.FriendService;
import com.soen.empower.service.NotificationService;
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

import java.util.Collections;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FriendController.class, secure = false)
public class FriendControllerTest {

    @MockBean
    private FriendService friendService;
    @MockBean
    private NotificationService notificationService;

    @Autowired
    private MockMvc mockMvc;

    private Friend friend;
    private Friend request;

    @Before
    public void setUp() {
        User user1 = new User();
        user1.setId(1L);

        User user2 = new User();
        user2.setId(2L);

        User user3 = new User();
        user3.setId(3L);

        friend = new Friend();
        friend.setId(1L);
        friend.setUser(user1);
        friend.setOtherUser(user2);
        friend.setEnabled(1);

        request = new Friend();
        request.setId(2L);
        request.setUser(user1);
        request.setOtherUser(user3);
    }

    @Test
    public void getIndex_ReturnFriendList() throws Exception {
        when(friendService.fetchFriends(1L)).thenReturn(Collections.singletonList(friend));
        mockMvc.perform(MockMvcRequestBuilders.get("/friend").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("friend/index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("friends"))
                .andExpect(MockMvcResultMatchers.model().attribute("friends", Collections.singletonList(friend)));
    }

//    @Test
//    public void addFriend() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/friend/add", request).sessionAttr("user_id", "1L"))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//               .andExpect(MockMvcResultMatchers.redirectedUrl("/user/profile/3"));
//    }

    @Test
    public void removeFriend_RedirectToTheirProfile() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/friend/remove?id=2").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/profile/2"));
    }

    @Test
    public void acceptFriend_ReturnReceivedRequestsPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/friend/accept", friend))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/friend/received"));
    }

    @Test
    public void declineFriend_ReturnReceivedRequestsPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/friend/decline", friend))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/friend/received"));
    }

    @Test
    public void getSent_ReturnsTemplate() throws Exception {
        when(friendService.viewSentRequests(1L)).thenReturn(Collections.singletonList(friend));

        mockMvc.perform(MockMvcRequestBuilders.get("/friend/sent").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("friend/sent"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("requests"))
                .andExpect(MockMvcResultMatchers.model().attribute("requests", Collections.singletonList(friend)));
    }

    @Test
    public void getReceived_ReturnsTemplate() throws Exception {
        when(friendService.viewReceivedRequests(1L)).thenReturn(Collections.singletonList(friend));

        mockMvc.perform(MockMvcRequestBuilders.get("/friend/received").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("friend/received"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("requests"))
                .andExpect(MockMvcResultMatchers.model().attribute("requests", Collections.singletonList(friend)));

    }
}
