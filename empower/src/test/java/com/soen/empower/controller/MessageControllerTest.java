package com.soen.empower.controller;

import com.soen.empower.entity.Conversation;
import com.soen.empower.entity.Message;
import com.soen.empower.entity.User;
import com.soen.empower.fixture.Factory;
import com.soen.empower.service.ConversationService;
import com.soen.empower.service.MessageService;
import com.soen.empower.service.NotificationService;
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

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MessageController.class, secure = false)
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @MockBean
    private ConversationService conversationService;

    @MockBean
    private UserService userService;

    @MockBean
    private NotificationService notificationService;
    private Conversation conversation;
    private Message message;

    @Before
    public void setUp(){
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);

        message = new Message();
        message.setId(1L);
        message.setFrom(user1);
        message.setTo(user2);

        conversation = new Conversation();
        conversation.setId(1L);
        conversation.setLastMessageId(1L);
        conversation.setUser(user1);
        conversation.setOtherUser(user2);

    }

    @Test
    public void getIndex_ReturnsConversation() throws Exception {
        when(conversationService.fetchAll((long) 1)).thenReturn(Collections.singletonList(conversation));
        mockMvc.perform(MockMvcRequestBuilders.get("/message").sessionAttr("user_id", (long) 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("message/index"));
    }

    @Test
    public void getConversationId_ReturnsMessageThread() throws Exception {
        when(conversationService.fetchAll((long) 1)).thenReturn(Collections.singletonList(conversation));
        when(conversationService.fetchById((long) 1)).thenReturn(conversation);
        when(messageService.fetch((long) 1)).thenReturn(Collections.singletonList(message));

        mockMvc.perform(MockMvcRequestBuilders.get("/message/1").sessionAttr("user_id", (long) 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("message/chat"));
    }

    @Test
    public void getNew_ReturnsComposeTemplate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/message/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("message/new"));
    }

}
