package com.soen.empower.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.soen.empower.entity.Card;
import com.soen.empower.entity.Comment;
import com.soen.empower.entity.Dislike;
import com.soen.empower.entity.Like;
import com.soen.empower.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/wall")
public class WallController {

    @Autowired
    private CardService cardService;

    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private DislikeService dislikeService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private NotificationService notificationService;

    @RequestMapping("")
    public String indexWall(HttpSession session) {
        long userId = (long) session.getAttribute("user_id");
        return "redirect:/wall/" + userId;
    }

    /**
     * View the wall of the user.
     *
     * @param session HttpSession of logged in user
     * @param userId  visited user's wall
     * @return the view wall
     */
    @RequestMapping("/{id}")
    public ModelAndView indexWall(HttpSession session, @PathVariable("id") long userId) {
        long loggedInUserId = (long) session.getAttribute("user_id");
        if (friendService.areFriends(loggedInUserId, userId).equals("1") || loggedInUserId == userId) {
            ModelAndView model = new ModelAndView("wall/index");
            model.addObject("cards", cardService.fetchCardsFor(userId));
            model.addObject("owner", userService.findById(userId));
            model.addObject("likedCards", likeService.findCardsFor(loggedInUserId));
            model.addObject("dislikedCards", dislikeService.findCardsFor(loggedInUserId));
            model.addObject("friends", friendService.fetchFriendsForTag(loggedInUserId));
            return model;
        }
        throw new AccessDeniedException("403 returned");
    }

    /**
     * addPost method receives POST request to save feed post.
     *
     * @param card   mapped data structure to entity with name card
     * @param userId visited user's wall
     * @return redirect the request to home.
     */
    @RequestMapping(value = "/addPost/{id}", method = RequestMethod.POST)
    public String addPost(@ModelAttribute Card card, @PathVariable("id") long userId) {
        cardService.add(card);
        notificationService.notify(card);
        return "redirect:/wall/" + userId;
    }

    /**
     * Adds the post which contains a caption and image to be posted on a specific group.
     *
     * @param card   entity named card
     * @param file   multipart file object
     * @param userId visited user's wall
     * @return redirect to the wall
     */
    @RequestMapping(value = "/addPostImage/{id}", method = RequestMethod.POST)
    public String addPostImage(@ModelAttribute Card card, @RequestParam("file") MultipartFile file, @PathVariable("id") long userId) {
        storageService.store(file);
        card.setFilename(file.getOriginalFilename());
        cardService.add(card);
        notificationService.notify(card);
        return "redirect:/wall/" + userId;
    }


    /**
     * addComment method receives POST request to save comment to the given feed post.
     *
     * @param comment mapped data structure to entity named comment
     * @param userId  visited user's wall
     * @return redirect the request to home.
     */
    @RequestMapping(value = "/addComment/{id}", method = RequestMethod.POST)
    public String addComment(@ModelAttribute Comment comment, @PathVariable("id") long userId) {
        commentService.add(comment);
        notificationService.notify(comment);
        return "redirect:/wall/" + userId;
    }

    /**
     * Add like to the card object. This object is instantiated using model attribute to save time of
     * mapping the object manually.
     *
     * @param like   entity
     * @param userId visited user's wall
     * @return redirect to wall
     */
    @RequestMapping(value = "/addLike/{id}", method = RequestMethod.POST)
    public String addLike(@ModelAttribute Like like, @PathVariable("id") long userId) {
        likeService.add(like);
        return "redirect:/wall/" + userId;
    }

    /**
     * Added dislike to the card object.
     *
     * @param dislike entity
     * @param userId  visited user's wall
     * @return redirect to the wall
     */
    @RequestMapping(value = "/addDislike/{id}", method = RequestMethod.POST)
    public String addDislike(@ModelAttribute Dislike dislike, @PathVariable("id") long userId) {
        dislikeService.add(dislike);
        return "redirect:/wall/" + userId;
    }

    /**
     * remove the like to make the post neutral.
     *
     * @param like   entity
     * @param userId visited user's wall
     * @return redirect to wall
     */
    @RequestMapping(value = "/removeLike/{id}", method = RequestMethod.POST)
    public String removeLike(@ModelAttribute Like like, @PathVariable("id") long userId) {
        likeService.remove(like);
        return "redirect:/wall/" + userId;
    }

    /**
     * remove dislike from the post to make it neutral.
     *
     * @param dislike entity
     * @param userId  visited user's wall
     * @return redirect to wall
     */
    @RequestMapping(value = "/removeDislike/{id}", method = RequestMethod.POST)
    public String removeDislike(@ModelAttribute Dislike dislike, @PathVariable("id") long userId) {
        dislikeService.remove(dislike);
        return "redirect:/wall/" + userId;
    }


}
