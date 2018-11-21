package com.soen.empower.controller;

import com.soen.empower.entity.User;
import com.soen.empower.service.GroupService;
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

import java.util.Collections;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SearchController.class, secure = false)
public class SearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private GroupService groupService;
    private User user;

    @Before
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setFullName("varun singhal");
    }

    @Test
    public void getSearch_ReturnSearchTemplate() throws Exception {
        when(userService.findByPartialName("varun")).thenReturn(Collections.singletonList(user));
        mockMvc.perform(MockMvcRequestBuilders.get("/search?name=varun"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("search/index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("users"))
                .andExpect(MockMvcResultMatchers.model().attribute("users", Collections.singletonList(user)));

    }
}
