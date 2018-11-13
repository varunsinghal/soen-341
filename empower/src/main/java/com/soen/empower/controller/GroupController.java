package com.soen.empower.controller;

import com.soen.empower.entity.*;
import com.soen.empower.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * The GroupController Class consists the functionality of create new group
 * and search group.
 *
 * @author Varun
 * @version 5.0
 * @since 4.0
 */
@Controller
@RequestMapping("/group")
public class GroupController {

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
     * Default controller loaded with the list of groups in which the user is enrolled.
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
     * allGroup method fetches all the groups within the application.
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
     * Narrow search - searchGroup method Searches group using group name.
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
     * Creates the new group - group name, description, members.
     *
     * @return the string with group name
     */
    @RequestMapping("/create")
    public String createGroup() {
        return "group/create";
    }

    /**
     * Creates the group submit. The owner is by default added as admin and member. This control
     * is done in the UI as the model attribute resolves the object as a whole.
     *
     * @return the string
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createGroupSubmit(@ModelAttribute Group group) {
        groupService.add(group);
        return "redirect:/group";
    }

    /**
     * Group's info page created to show the status with respect to each user. Whether, they are
     * already a member or not. The reason for this page? - reusable cards of group would require
     * much more attributes to resolve such relation and it will make us revisit all the controllers
     * again. Specially the search controller.
     *
     * @param session Logged in HttpSession
     * @param groupId current visited group
     * @return the view group/page
     */
    @RequestMapping("/{id}")
    public ModelAndView viewGroupPage(HttpSession session, @PathVariable("id") long groupId) {
        ModelAndView model = new ModelAndView("group/page");
        long userId = (long) session.getAttribute("user_id");
        model.addObject("group", groupService.findById(groupId));
        model.addObject("isMember", groupService.isMember(userId, groupId));
        return model;
    }

    /**
     * View the wall of the group. All the functionalities have been replicated from the wallcontroller.
     *
     * @param session HttpSession of logged in user
     * @param groupId currently visited group
     * @return the view group/wall
     */
    @RequestMapping("/{id}/wall")
    public ModelAndView viewGroupWall(HttpSession session, @PathVariable("id") long groupId) {
        ModelAndView model = new ModelAndView("group/wall");
        long userId = (long) session.getAttribute("user_id");
        if (groupService.isMember(userId, groupId) == 1) {
            model.addObject("cards", cardService.fetchCardsForGroup(groupId));
            model.addObject("group", groupService.findById(groupId));
            model.addObject("likedCards", likeService.findCardsFor(userId));
            model.addObject("dislikedCards", dislikeService.findCardsFor(userId));
            return model;
        }
        throw new AccessDeniedException("403 returned");
    }

    /**
     * Display a list of members inside the currently visited group.
     * isOwner to activate the owner panel - promote/revoke admin access
     * isAdmin to activate the admin panel - remove member
     *
     * @param session HttpSession of logged in user
     * @param groupId currently visited group
     * @return the list of members
     */
    @RequestMapping("/{id}/members")
    public ModelAndView viewMembers(HttpSession session, @PathVariable("id") long groupId) {
        ModelAndView model = new ModelAndView("group/members");
        long userId = (long) session.getAttribute("user_id");
        model.addObject("isOwner", groupService.isOwner(userId, groupId));
        model.addObject("isAdmin", groupService.isAdmin(userId, groupId));
        model.addObject("group", groupService.findById(groupId));
        return model;
    }

    /**
     * This action is available to Owner.
     *
     * @param session HttpSession of logged in user
     * @param groupId currently visited group
     * @param userId  make admin
     * @return redirect to members page
     */
    @RequestMapping("/{id}/addAdmin")
    public String addAdmin(HttpSession session, @PathVariable("id") long groupId, @RequestParam("userId") long userId) {
        groupService.addAdmin(groupId, userId);
        return "redirect:/group/" + groupId + "/members";
    }

    /**
     * This action is available to owner of the group.
     *
     * @param session HttpSession of logged in user
     * @param groupId currently visited group
     * @param userId  remove from admin
     * @return redirect to members page
     */
    @RequestMapping("/{id}/removeAdmin")
    public String removeAdmin(HttpSession session, @PathVariable("id") long groupId, @RequestParam("userId") long userId) {
        groupService.removeAdmin(groupId, userId);
        return "redirect:/group/" + groupId + "/members";
    }

    /**
     * This action is available to admins only.
     *
     * @param session HttpSession of logged in user
     * @param groupId currently visited group
     * @return the view group/requests
     */
    @RequestMapping("/{id}/requests")
    public ModelAndView viewJoinRequests(HttpSession session, @PathVariable("id") long groupId) {
        long userId = (long) session.getAttribute("user_id");
        if (groupService.isAdmin(userId, groupId)) {
            ModelAndView model = new ModelAndView("/group/requests");
            model.addObject("group", groupService.findById(groupId));
            model.addObject("requests", groupService.fetchJoinRequests(groupId));
            return model;
        }
        throw new AccessDeniedException("403 returned");
    }

    /**
     * Action avaible only to admins to accept the join requests.
     *
     * @param session HttpSession of logged in user
     * @param groupId currently visited group
     * @param userId  to make member
     * @return redirect to requests page.
     */
    @RequestMapping("/{id}/requests/accept")
    public String acceptRequests(HttpSession session,
                                 @PathVariable("id") long groupId,
                                 @RequestParam("userId") long userId) {
        groupService.acceptRequest(userId, groupId);
        return "redirect:/group/" + groupId + "/requests";
    }

    /**
     * Action available to admins only.
     *
     * @param session HttpSession of logged in user
     * @param groupId currently visited group
     * @param userId  to remove the request
     * @return redirect to groups join requests page
     */
    @RequestMapping("/{id}/requests/decline")
    public String declineRequests(HttpSession session,
                                  @PathVariable("id") long groupId,
                                  @RequestParam("userId") long userId) {
        groupService.declineRequest(userId, groupId);
        return "redirect:/group/" + groupId + "/requests";
    }

    /**
     * Action available to any third person to request to join the group.
     *
     * @param session HttpSession of logged in user
     * @param groupId currently visited group
     * @return redirect to group info page
     */
    @RequestMapping("/{id}/join")
    public String sendJoinRequest(HttpSession session, @PathVariable("id") long groupId) {
        long userId = (long) session.getAttribute("user_id");
        groupService.addRequest(userId, groupId);
        return "redirect:/group/" + groupId;
    }

    /**
     * Action available to members through the group info page.
     *
     * @param session HttpSession of logged in user
     * @param groupId currently visited group
     * @return redirect to group info page
     */
    @RequestMapping("/{id}/leave")
    public String leaveGroup(HttpSession session, @PathVariable("id") long groupId) {
        long userId = (long) session.getAttribute("user_id");
        groupService.leave(userId, groupId);
        return "redirect:/group/" + groupId;
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
        return "redirect:/group/" + groupId + "/wall";
    }

    /**
     * Adds the post which contains a caption and image to be posted on a specific group.
     *
     * @param card    entity named card
     * @param file    multipart file object
     * @param groupId currently visited group
     * @return redirect to the wall
     */
    @RequestMapping(value = "/addPostImage/{id}", method = RequestMethod.POST)
    public String addPostImage(@ModelAttribute Card card, @RequestParam("file") MultipartFile file, @PathVariable("id") long groupId) {
        storageService.store(file);
        card.setFilename(file.getOriginalFilename());
        cardService.add(card);
        return "redirect:/group/" + groupId + "/wall";
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
        return "redirect:/group/" + groupId + "/wall";
    }

    /**
     * Add like to the card object. This object is instantiated using model attribute to save time of
     * mapping the object manually.
     *
     * @param like    entity
     * @param groupId visited group
     * @return redirect to group wall
     */
    @RequestMapping(value = "/addLike/{id}", method = RequestMethod.POST)
    public String addLike(@ModelAttribute Like like, @PathVariable("id") long groupId) {
        likeService.add(like);
        return "redirect:/group/" + groupId + "/wall";
    }

    /**
     * Added dislike to the card object.
     *
     * @param dislike entity
     * @param groupId visited group
     * @return redirect to the wall
     */
    @RequestMapping(value = "/addDislike/{id}", method = RequestMethod.POST)
    public String addDislike(@ModelAttribute Dislike dislike, @PathVariable("id") long groupId) {
        dislikeService.add(dislike);
        return "redirect:/group/" + groupId + "/wall";
    }

    /**
     * remove the like to make the post neutral.
     *
     * @param like    entity
     * @param groupId visited group
     * @return redirect to wall
     */
    @RequestMapping(value = "/removeLike/{id}", method = RequestMethod.POST)
    public String removeLike(@ModelAttribute Like like, @PathVariable("id") long groupId) {
        likeService.remove(like);
        return "redirect:/group/" + groupId + "/wall";
    }

    /**
     * remove dislike from the post to make it neutral.
     *
     * @param dislike entity
     * @param groupId visited group
     * @return redirect to wall
     */
    @RequestMapping(value = "/removeDislike/{id}", method = RequestMethod.POST)
    public String removeDislike(@ModelAttribute Dislike dislike, @PathVariable("id") long groupId) {
        dislikeService.remove(dislike);
        return "redirect:/group/" + groupId + "/wall";
    }
}
