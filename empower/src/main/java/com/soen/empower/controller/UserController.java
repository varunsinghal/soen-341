package com.soen.empower.controller;

import com.soen.empower.entity.Card;
import com.soen.empower.entity.Comment;
import com.soen.empower.entity.User;
import com.soen.empower.service.CardService;
import com.soen.empower.service.CommentService;
import com.soen.empower.service.FriendService;
import com.soen.empower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Controller shared by both the permission set - ROLE_TEACHER and ROLE_PARENT.
 *
 * @author Varun Singhal, Kishor Tare
 * @version 1.3
 * @since 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * Cards - news feed post as a service
     */
    @Autowired
    private CardService cardService;

    /**
     * Comments as a service
     */
    @Autowired
    private CommentService commentService;

    /**
     * User as a service
     */
    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;

    /**
     * Default method when controller is triggered via http://localhost:port/user/.
     *
     * @return redirect URL to home method.
     */
    @RequestMapping("")
    public String index() {
        return "redirect:/wall";
    }

    /**
     * Home method to serve news feed using cardService.
     *
     * @return the view with the name user/home.
     */
    @RequestMapping("/home")
    public String home() {
        //ModelAndView model = new ModelAndView("user/home");
        //model.addObject("cards", cardService.fetchAll());
        return "redirect:/wall";
    }

    /**
     * ViewProfile method on GET request delivers the view with name user/profile.
     *
     * @param session the session attribute to identify the logged in user.
     * @return the view named user/profile.
     */
    @RequestMapping("/profile")
    public ModelAndView viewProfile(HttpSession session) {
        long userId = (long) session.getAttribute("user_id");
        ModelAndView model = new ModelAndView("user/profile");
        model.addObject("user", userService.findById(userId));
        return model;
    }

    /**
     * ViewOtherProfile method on GET request delivers the view with the name user/profile.
     *
     * @param id the user id for which the public profile will be delivered.
     * @return the view named user/profile.
     */
    @RequestMapping("/profile/{id}")
    public ModelAndView viewOtherProfile(HttpSession session, @PathVariable(value = "id") Long id) {
        ModelAndView model = new ModelAndView("user/profile");
        Long userId = (Long) session.getAttribute("user_id");
        model.addObject("friendStatus", friendService.areFriends(userId, id));
        model.addObject("user", userService.findById(id));
        return model;
    }

    /**
     * editProfile method on GET request delivers the view with form in editable format. The editable fields are
     * managed by the view.
     *
     * @param session the session attribute to identify the logged in user
     * @return the view with name user/edit.
     */
    @RequestMapping("/edit")
    public ModelAndView editProfile(HttpSession session) {
        long userId = (long) session.getAttribute("user_id");
        ModelAndView model = new ModelAndView("user/profileEdit");
        model.addObject("user", userService.findById(userId));
        return model;
    }

    /**
     * saveProfile method on POST request uses the userService to update the user profile.
     *
     * @param user    the mapped data structure to the entity user.
     * @return redirect the user to their profile user/profile.
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProfile(HttpSession session, @ModelAttribute User user) {
        userService.save(user);
        session.setAttribute("full_name", user.getFullName());
        return "redirect:/user/profile";
    }

}
