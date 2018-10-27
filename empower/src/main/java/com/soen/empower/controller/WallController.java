package com.soen.empower.controller;

import com.soen.empower.service.CardService;
import com.soen.empower.service.FriendService;
import com.soen.empower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("")
    public ModelAndView indexWall(HttpSession session) {
        ModelAndView model = new ModelAndView("wall/index");
        long userId = (long) session.getAttribute("user_id");
        model.addObject("cards", cardService.fetchCardsFor(userId));
        model.addObject("owner", userService.findById(userId));
        return model;
    }

    @RequestMapping("/{id}")
    public ModelAndView indexWall(HttpSession session, @PathVariable("id") long userId) {
        if (friendService.areFriends((long) session.getAttribute("user_id"), userId).equals("1")) {
            ModelAndView model = new ModelAndView("wall/index");
            model.addObject("cards", cardService.fetchCardsFor(userId));
            model.addObject("owner", userService.findById(userId));
            return model;
        }
        throw new AccessDeniedException("403 returned");
    }


}
