package com.soen.empower.controller;

import com.soen.empower.entity.Card;
import com.soen.empower.entity.Comment;
import com.soen.empower.entity.Dislike;
import com.soen.empower.entity.Like;
import com.soen.empower.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping("")
    public String indexWall(HttpSession session) {
        long userId = (long) session.getAttribute("user_id");
        return "redirect:/wall/" + userId;
    }

    @RequestMapping("/{id}")
    public ModelAndView indexWall(HttpSession session, @PathVariable("id") long userId) {
        long loggedInUserId = (long) session.getAttribute("user_id");
        if (friendService.areFriends(loggedInUserId, userId).equals("1") || loggedInUserId == userId) {
            ModelAndView model = new ModelAndView("wall/index");
            model.addObject("cards", cardService.fetchCardsFor(userId));
            model.addObject("owner", userService.findById(userId));
            model.addObject("likedCards", likeService.findCardsFor(loggedInUserId));
            model.addObject("dislikedCards", dislikeService.findCardsFor(loggedInUserId));
            return model;
        }
        throw new AccessDeniedException("403 returned");
    }

    /**
     * addPost method receives POST request to save feed post.
     *
     * @param card mapped data structure to entity with name card.
     * @return redirect the request to home.
     */
    @RequestMapping(value = "/addPost/{id}", method = RequestMethod.POST)
    public String addPost(@ModelAttribute Card card, @PathVariable("id") long userId) {
        cardService.add(card);
        return "redirect:/wall/" + userId;
    }

    /**
     * addComment method receives POST request to save comment to the given feed post.
     *
     * @param comment mapped data structure to entity named comment.
     * @return redirect the request to home.
     */
    @RequestMapping(value = "/addComment/{id}", method = RequestMethod.POST)
    public String addComment(@ModelAttribute Comment comment, @PathVariable("id") long userId) {
        commentService.add(comment);
        return "redirect:/wall/" + userId;
    }

    @RequestMapping(value ="/addLike/{id}", method = RequestMethod.POST)
    public String addLike(@ModelAttribute Like like, @PathVariable("id") long userId){
        likeService.add(like);
        return "redirect:/wall/"+ userId;
    }

    @RequestMapping(value ="/addDislike/{id}", method = RequestMethod.POST)
    public String addDislike(@ModelAttribute Dislike dislike, @PathVariable("id") long userId){
        dislikeService.add(dislike);
        return "redirect:/wall/"+ userId;
    }

    @RequestMapping(value ="/removeLike/{id}", method = RequestMethod.POST)
    public String removeLike(@ModelAttribute Like like, @PathVariable("id") long userId){
        likeService.remove(like);
        return "redirect:/wall/"+ userId;
    }

    @RequestMapping(value ="/removeDislike/{id}", method = RequestMethod.POST)
    public String removeDislike(@ModelAttribute Dislike dislike, @PathVariable("id") long userId){
        dislikeService.remove(dislike);
        return "redirect:/wall/"+ userId;
    }



}
