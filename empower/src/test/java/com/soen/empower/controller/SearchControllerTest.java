package com.soen.empower.controller;

import com.soen.empower.fixture.Factory;
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
@WebMvcTest(value = SearchController.class, secure = false)
public class SearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void getSearch_ReturnSearchTemplate() throws Exception {
        when(userService.findByPartialName("varun")).thenReturn(Arrays.asList(Factory.user1, Factory.user2));
        mockMvc.perform(MockMvcRequestBuilders.get("/search?name=varun"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("search/index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("results"))
                .andExpect(MockMvcResultMatchers.model().attribute("results", Arrays.asList(Factory.user1, Factory.user2)));

    }
}
