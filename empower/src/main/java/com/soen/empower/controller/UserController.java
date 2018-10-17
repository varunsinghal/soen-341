package com.soen.empower.controller;

import com.soen.empower.entity.Card;
import com.soen.empower.entity.Comment;
import com.soen.empower.entity.User;
import com.soen.empower.service.CardService;
import com.soen.empower.service.CommentService;
import com.soen.empower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * The Class UserController.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    
    /** The card service. */
    @Autowired
    private CardService cardService;

    /** The comment service. */
    @Autowired
    private CommentService commentService;

    /** The user service. */
    @Autowired
    private UserService userService;

    /**
     * Index.
     *
     * @return the string
     */
    @RequestMapping("")
    public String index() {
        return "redirect:/user/home";
    }

    /**
     * Home.
     *
     * @return the model and view
     */
    @RequestMapping("/home")
    public ModelAndView home() {
        ModelAndView model = new ModelAndView("user/home");
        model.addObject("cards", cardService.fetchAll());
        return model;
    }

    /**
     * Adds the post.
     *
     * @param card the card
     * @return the string
     */
    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public String addPost(@ModelAttribute Card card) {
        cardService.add(card);
        return "redirect:/user/home";
    }

    /**
     * Adds the comment.
     *
     * @param comment the comment
     * @return the string
     */
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public String addComment(@ModelAttribute Comment comment) {
        commentService.add(comment);
        return "redirect:/user/home";
    }


    /**
     * View profile.
     *
     * @param session the session
     * @return the model and view
     */
    @RequestMapping("/profile")
    public ModelAndView viewProfile(HttpSession session) {
        long userId = (long) session.getAttribute("user_id");
        ModelAndView model = new ModelAndView("user/profile");
        model.addObject("user", userService.findById(userId));
        return model;
    }

    /**
     * View other profile.
     *
     * @param session the session
     * @param id the id
     * @return the model and view
     */
    @RequestMapping("/profile/{id}")
    public ModelAndView viewOtherProfile(HttpSession session, @PathVariable(value = "id") Long id) {
        ModelAndView model = new ModelAndView("user/profile");
        model.addObject("user", userService.findById(id));
        return model;
    }

    /**
     * Edits the profile.
     *
     * @param session the session
     * @return the model and view
     */
    @RequestMapping("/edit")
    public ModelAndView editProfile(HttpSession session) {
        long userId = (long) session.getAttribute("user_id");
        ModelAndView model = new ModelAndView("user/profileEdit");
        model.addObject("user", userService.findById(userId));
        return model;
    }

    /**
     * Save profile.
     *
     * @param session the session
     * @param user the user
     * @return the string
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProfile(HttpSession session, @ModelAttribute User user) {
        userService.save(user);
        return "redirect:/user/profile";
    }

}
