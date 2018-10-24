package com.soen.empower.controller;

import com.soen.empower.entity.Friend;
import com.soen.empower.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addFriend(@ModelAttribute Friend friend) {
        friendService.addRequest(friend);
        return "redirect:/user/profile/" + friend.getOtherUser().getId();
    }

    @RequestMapping("/sent")
    public ModelAndView viewFriendRequests(HttpSession session){
        long userId = (long) session.getAttribute("user_id");
        ModelAndView model = new ModelAndView("friend/sent");
        model.addObject("requests", friendService.viewRequests(userId));
        return model;
    }

}
