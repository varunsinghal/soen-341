package com.soen.empower.controller;

import com.soen.empower.fixture.Factory;
import com.soen.empower.service.ConversationService;
import com.soen.empower.service.MessageService;
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

    @Test
    public void getIndex_ReturnsConversation() throws Exception {
        when(conversationService.fetchAll((long) 1)).thenReturn(Arrays.asList(Factory.conversation1));
        mockMvc.perform(MockMvcRequestBuilders.get("/message").sessionAttr("user_id", (long) 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("message/index"));
    }

    @Test
    public void getConversationId_ReturnsMessageThread() throws Exception {
        when(conversationService.fetchAll((long) 1)).thenReturn(Arrays.asList(Factory.conversation1));
        when(conversationService.fetchById((long) 1)).thenReturn(Factory.conversation1);
        when(messageService.fetch((long) 1)).thenReturn(Arrays.asList(Factory.message1, Factory.message2));

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
