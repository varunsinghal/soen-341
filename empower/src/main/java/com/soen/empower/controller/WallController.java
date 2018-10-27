package com.soen.empower.controller;

import com.soen.empower.service.CardService;
import com.soen.empower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("")
    public ModelAndView indexWall(HttpSession session){
        ModelAndView model = new ModelAndView("wall/index");
        long userId = (long) session.getAttribute("user_id");
        model.addObject("cards", cardService.fetchCardsFor(userId));
        model.addObject("owner", userService.findById(userId));
        return model;
    }
}
