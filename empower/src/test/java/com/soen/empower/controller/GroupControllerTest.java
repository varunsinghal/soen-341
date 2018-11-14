package com.soen.empower.controller;

import com.soen.empower.entity.*;
import com.soen.empower.service.*;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = GroupController.class, secure = false)
public class GroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;
    @MockBean
    private UserService userService;
    @MockBean
    private LikeService likeService;
    @MockBean
    private DislikeService dislikeService;
    @MockBean
    private StorageService storageService;
    @MockBean
    private CommentService commentService;


    @MockBean
    private GroupService groupService;
    private Group group;
    private User owner;
    private User admin;
    private User member;
    private User guest;
    private Card card;
    private Like like;
    private Dislike dislike;
    private Comment comment;

    @Before
    public void setUp() {
        owner = new User();
        owner.setId(1L);

        admin = new User();
        admin.setId(2L);

        member = new User();
        member.setId(3L);

        guest = new User();
        guest.setId(4L);

        group = new Group();
        group.setId(1L);
        group.setName("group 1");
        group.setOwner(owner);
        group.setMembers(Arrays.asList(owner, admin, member));
        group.setAdmins(Arrays.asList(owner, admin));

        card = new Card();
        card.setId(1L);
        card.setBelongsToGroup(group);
        card.setUser(admin);

        like = new Like();
        like.setId(1L);
        like.setUser(admin);
        like.setCard(card);

        dislike = new Dislike();
        dislike.setId(1L);
        dislike.setUser(member);
        dislike.setCard(card);

        card.setLikes(Collections.singletonList(like));
        card.setDislikes(Collections.singletonList(dislike));

        comment = new Comment();
        comment.setCard(card);
        comment.setId(1L);
        comment.setUser(admin);
    }

    @Test
    public void indexGroup() throws Exception {
        when(groupService.fetchJoinedGroups(1L)).thenReturn(Collections.singletonList(group));
        mockMvc.perform(MockMvcRequestBuilders.get("/group").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("group/index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("activeTab", "groups"))
                .andExpect(MockMvcResultMatchers.model().attribute("groups", Collections.singletonList(group)));
    }

    @Test
    public void allGroup() throws Exception {
        when(groupService.fetchAll()).thenReturn(Collections.singletonList(group));
        mockMvc.perform(MockMvcRequestBuilders.get("/group/all").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("group/index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("activeTab", "groups"))
                .andExpect(MockMvcResultMatchers.model().attribute("groups", Collections.singletonList(group)));

    }

    @Test
    public void searchGroup() throws Exception {
        when(groupService.findByPartialName("1")).thenReturn(Collections.singletonList(group));
        mockMvc.perform(MockMvcRequestBuilders.get("/group/search?name=1").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("group/index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("activeTab", "groups", "searchString"))
                .andExpect(MockMvcResultMatchers.model().attribute("groups", Collections.singletonList(group)));

    }

    @Test
    public void createGroup() throws Exception {
         mockMvc.perform(MockMvcRequestBuilders.get("/group/create").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("group/create"));
    }

    @Test
    public void createGroupSubmit() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/group/create", group).sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void viewGroupPage() throws Exception {
        when(groupService.findById(1L)).thenReturn(group);
        when(groupService.isMember(1L, 1L)).thenReturn(1);
        mockMvc.perform(MockMvcRequestBuilders.get("/group/1").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("group/page"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("group", "isMember"))
                .andExpect(MockMvcResultMatchers.model().attribute("group", group))
                .andExpect(MockMvcResultMatchers.model().attribute("isMember", 1));
    }

    @Test
    public void viewGroupWall() throws Exception {
        when(groupService.isMember(1L, 1L)).thenReturn(1);
        when(cardService.fetchCardsForGroup(1L)).thenReturn(Collections.singletonList(card));
        when(groupService.findById(1L)).thenReturn(group);
        when(likeService.findCardsFor(1L)).thenReturn(Collections.emptyList());
        when(dislikeService.findCardsFor(1L)).thenReturn(Collections.emptyList());
        mockMvc.perform(MockMvcRequestBuilders.get("/group/1/wall").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("group/wall"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("cards", "group", "likedCards", "dislikedCards"))
                .andExpect(MockMvcResultMatchers.model().attribute("group", group))
                .andExpect(MockMvcResultMatchers.model().attribute("cards", Collections.singletonList(card)));
    }

    @Test
    public void viewMembers() throws Exception {
        when(groupService.findById(1L)).thenReturn(group);
        when(groupService.isOwner(1L, 1L)).thenReturn(true);
        when(groupService.isAdmin(1L, 1L)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.get("/group/1/members").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("group/members"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("group", "isOwner", "isAdmin"))
                .andExpect(MockMvcResultMatchers.model().attribute("group", group))
                .andExpect(MockMvcResultMatchers.model().attribute("isOwner", true))
                .andExpect(MockMvcResultMatchers.model().attribute("isAdmin", true));
    }

    @Test
    public void addAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/group/1/addAdmin?userId=3").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void removeAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/group/1/removeAdmin?userId=2").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

    }

    @Test
    public void viewJoinRequests() throws Exception {
        when(groupService.findById(1L)).thenReturn(group);
        when(groupService.fetchJoinRequests(1L)).thenReturn(Collections.singletonList(guest));
        when(groupService.isAdmin(1L, 1L)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.get("/group/1/requests").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("group/requests"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("group", "requests"))
                .andExpect(MockMvcResultMatchers.model().attribute("group", group))
                .andExpect(MockMvcResultMatchers.model().attribute("requests", Collections.singletonList(guest)));

    }

    @Test
    public void acceptRequests() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/group/1/requests/accept?userId=4").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/group/1/requests"));

    }

    @Test
    public void declineRequests() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/group/1/requests/decline?userId=4").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/group/1/requests"));

    }

    @Test
    public void sendJoinRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/group/1/join").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/group/1"));

    }

    @Test
    public void leaveGroup() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/group/1/leave").sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/group/1"));
    }

    @Test
    public void addPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/group/addPost/1", card).sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/group/1/wall"));

    }

    @Test
    public void addComment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/group/addComment/1", comment).sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/group/1/wall"));

    }

    @Test
    public void addLike() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/group/addLike/1", like).sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/group/1/wall"));

    }

    @Test
    public void addDislike() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/group/addDislike/1", dislike).sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/group/1/wall"));
    }

    @Test
    public void removeLike() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/group/removeLike/1", like).sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/group/1/wall"));
    }

    @Test
    public void removeDislike() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/group/removeDislike/1", dislike).sessionAttr("user_id", 1L))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/group/1/wall"));
    }
}