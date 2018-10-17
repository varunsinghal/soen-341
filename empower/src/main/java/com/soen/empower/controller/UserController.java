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

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CardService cardService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String index() {
        return "redirect:/user/home";
    }

    @RequestMapping("/home")
    public ModelAndView home() {
        ModelAndView model = new ModelAndView("user/home");
        model.addObject("cards", cardService.fetchAll());
        return model;
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public String addPost(@ModelAttribute Card card) {
        cardService.add(card);
        return "redirect:/user/home";
    }

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public String addComment(@ModelAttribute Comment comment) {
        commentService.add(comment);
        return "redirect:/user/home";
    }


    @RequestMapping("/profile")
    public ModelAndView viewProfile(HttpSession session) {
        long userId = (long) session.getAttribute("user_id");
        ModelAndView model = new ModelAndView("user/profile");
        model.addObject("user", userService.findById(userId));
        return model;
    }

    @RequestMapping("/profile/{id}")
    public ModelAndView viewOtherProfile(HttpSession session, @PathVariable(value = "id") Long id) {
        ModelAndView model = new ModelAndView("user/profile");
        model.addObject("user", userService.findById(id));
        return model;
    }

    @RequestMapping("/edit")
    public ModelAndView editProfile(HttpSession session) {
        long userId = (long) session.getAttribute("user_id");
        ModelAndView model = new ModelAndView("user/profileEdit");
        model.addObject("user", userService.findById(userId));
        return model;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProfile(HttpSession session, @ModelAttribute User user) {
        userService.save(user);
        return "redirect:/user/profile";
    }

}
