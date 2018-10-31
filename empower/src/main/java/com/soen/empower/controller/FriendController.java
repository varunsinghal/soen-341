package com.soen.empower.controller;

import com.soen.empower.entity.Friend;
import com.soen.empower.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * The Class FriendController.
 */
@Controller
@RequestMapping("/friend")
public class FriendController {

    /** The friend service. */
    @Autowired
    private FriendService friendService;

    /**
     * Index friend.
     *
     * @param session the session
     * @return the model and view
     */
    @RequestMapping("")
    public ModelAndView indexFriend(HttpSession session) {
        ModelAndView model = new ModelAndView("friend/index");
        long userId = (long) session.getAttribute("user_id");
        model.addObject("friends", friendService.fetchFriends(userId));
        return model;
    }

    /**
     * Adds the friend.
     *
     * @param friend the friend
     * @return the string
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addFriend(@ModelAttribute Friend friend) {
        friendService.addFriend(friend);
        return "redirect:/user/profile/" + friend.getOtherUser().getId();
    }

    /**
     * Removes the friend.
     *
     * @param session the session
     * @param otherUserId the other user id
     * @return the string
     */
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String removeFriend(HttpSession session, @RequestParam("id") long otherUserId) {
        long userId = (long) session.getAttribute("user_id");
        friendService.removeFriend(userId, otherUserId);
        return "redirect:/user/profile/" + otherUserId;
    }


    /**
     * Accept requests.
     *
     * @param friend the friend
     * @return the string
     */
    @RequestMapping("/accept")
    public String acceptRequests(@ModelAttribute Friend friend) {
        friendService.accept(friend);
        return "redirect:/friend/received";
    }

    /**
     * Decline requests.
     *
     * @param friend the friend
     * @return the string
     */
    @RequestMapping("/decline")
    public String declineRequests(@ModelAttribute Friend friend) {
        friendService.decline(friend);
        return "redirect:/friend/received";
    }

    /**
     * View friend requests sent.
     *
     * @param session the session
     * @return the model and view
     */
    @RequestMapping("/sent")
    public ModelAndView viewFriendRequestsSent(HttpSession session) {
        long userId = (long) session.getAttribute("user_id");
        ModelAndView model = new ModelAndView("friend/sent");
        model.addObject("requests", friendService.viewSentRequests(userId));
        return model;
    }

    /**
     * View friend requests received.
     *
     * @param session the session
     * @return the model and view
     */
    @RequestMapping("/received")
    public ModelAndView viewFriendRequestsReceived(HttpSession session) {
        long userId = (long) session.getAttribute("user_id");
        ModelAndView model = new ModelAndView("friend/received");
        model.addObject("requests", friendService.viewReceivedRequests(userId));
        return model;
    }

}
