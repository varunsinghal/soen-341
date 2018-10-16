package com.soen.empower.controller;

import com.soen.empower.entity.Card;
import com.soen.empower.entity.Comment;
import com.soen.empower.service.CardService;
import com.soen.empower.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CardService cardService;

    @Autowired
    private CommentService commentService;

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

}
