package com.soen.empower.controller;

import com.soen.empower.entity.*;
import com.soen.empower.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * The GroupController Class consists the functionality of create new group
 * and search group.
 *
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/group")
public class GroupController {

    /**
     * The group as a service.
     */
    @Autowired
    private GroupService groupService;
    @Autowired
    private CardService cardService;
    @Autowired
    private UserService userService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private DislikeService dislikeService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private CommentService commentService;


    /**
     * Default controller loaded with the list of groups.
     *
     * @return the view group/index
     */
    @RequestMapping("")
    public ModelAndView indexGroup(HttpSession session) {
        ModelAndView model = new ModelAndView("group/index");
        long userId = (long) session.getAttribute("user_id");
        model.addObject("activeTab", "index");
        model.addObject("groups", groupService.fetchJoinedGroups(userId));
        return model;
    }

    /**
     * allGroup method All group.
     *
     * @return the model and view
     */
    @RequestMapping("/all")
    public ModelAndView allGroup() {
        ModelAndView model = new ModelAndView("group/index");
        model.addObject("activeTab", "all");
        model.addObject("groups", groupService.fetchAll());
        return model;
    }

    /**
     * searchGroup method Searches group using group name.
     *
     * @return the view named search/group
     */
    @RequestMapping("/search")
    public ModelAndView searchGroup(@RequestParam("name") String search) {
        ModelAndView model = new ModelAndView("group/index");
        model.addObject("activeTab", "search");
        model.addObject("groups", groupService.findByPartialName(search));
        model.addObject("searchString", search);
        return model;
    }

    /**
     * Creates the new group.
     *
     * @return the string with group name
     */
    @RequestMapping("/create")
    public String createGroup() {
        return "group/create";
    }

    /**
     * Creates the group submit.
     *
     * @return the string
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createGroupSubmit(@ModelAttribute Group group) {
        groupService.add(group);
        return "redirect:/group";
    }

    @RequestMapping("/{id}")
    public ModelAndView viewGroup(HttpSession session, @PathVariable("id") long groupId) {
        ModelAndView model = new ModelAndView("group/wall");
        long userId = (long) session.getAttribute("user_id");
        model.addObject("cards", cardService.fetchCardsForGroup(groupId));
        model.addObject("group", groupService.findById(groupId));
        model.addObject("likedCards", likeService.findCardsFor(userId));
        model.addObject("dislikedCards", dislikeService.findCardsFor(userId));
        return model;
    }

    /**
     * addPost method receives POST request to save feed post.
     *
     * @param card mapped data structure to entity with name card.
     * @return redirect the request to home.
     */
    @RequestMapping(value = "/addPost/{id}", method = RequestMethod.POST)
    public String addPost(@ModelAttribute Card card, @PathVariable("id") long groupId) {
        cardService.add(card);
        return "redirect:/group/" + groupId;
    }

    @RequestMapping(value="/addPostImage/{id}", method = RequestMethod.POST)
    public String addPostImage(@ModelAttribute Card card, @RequestParam("file") MultipartFile file, @PathVariable("id") long groupId) {
        storageService.store(file);
        card.setFilename(file.getOriginalFilename());
        cardService.add(card);
        return "redirect:/group/" + groupId;
    }


    /**
     * addComment method receives POST request to save comment to the given feed post.
     *
     * @param comment mapped data structure to entity named comment.
     * @return redirect the request to home.
     */
    @RequestMapping(value = "/addComment/{id}", method = RequestMethod.POST)
    public String addComment(@ModelAttribute Comment comment, @PathVariable("id") long groupId) {
        commentService.add(comment);
        return "redirect:/group/" + groupId;
    }

    @RequestMapping(value ="/addLike/{id}", method = RequestMethod.POST)
    public String addLike(@ModelAttribute Like like, @PathVariable("id") long groupId){
        likeService.add(like);
        return "redirect:/group/"+ groupId;
    }

    @RequestMapping(value ="/addDislike/{id}", method = RequestMethod.POST)
    public String addDislike(@ModelAttribute Dislike dislike, @PathVariable("id") long groupId){
        dislikeService.add(dislike);
        return "redirect:/group/"+ groupId;
    }

    @RequestMapping(value ="/removeLike/{id}", method = RequestMethod.POST)
    public String removeLike(@ModelAttribute Like like, @PathVariable("id") long groupId){
        likeService.remove(like);
        return "redirect:/group/"+ groupId;
    }

    @RequestMapping(value ="/removeDislike/{id}", method = RequestMethod.POST)
    public String removeDislike(@ModelAttribute Dislike dislike, @PathVariable("id") long groupId){
        dislikeService.remove(dislike);
        return "redirect:/group/"+ groupId;
    }


}
