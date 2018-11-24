package com.soen.empower.controller;

import com.soen.empower.entity.Friend;
import com.soen.empower.service.FriendService;
import com.soen.empower.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Friend controller
 *
 * @author Varun Singhal
 * @version 5.0
 * @since 3.0
 */
@Controller
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;
    @Autowired
    private NotificationService notificationService;

    /**
     * Default controller which list the friends of current logged in user.
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
     * Adds the friend. Since, add a record in the database requires creation of entity object,
     * it is preferred to resolve it using ModelAttribute.
     *
     * @param friend the friend
     * @return the string
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addFriend(@ModelAttribute Friend friend) {
        friendService.addFriend(friend);
        notificationService.notify(friend);
        return "redirect:/user/profile/" + friend.getOtherUser().getId();
    }

    /**
     * Removes the friend. Since, editing a pre-existing record or deleting a record first needs to
     * identify the exact same record and then the entity is deleted or saved. So, an {id} is
     * sufficient to carry out the process.
     *
     * @param session     the session
     * @param otherUserId the other user id
     * @return redirect to profile of other user because, the remove friend option is present on their profile.
     */
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String removeFriend(HttpSession session, @RequestParam("id") long otherUserId) {
        long userId = (long) session.getAttribute("user_id");
        friendService.removeFriend(userId, otherUserId);
        return "redirect:/user/profile/" + otherUserId;
    }


    /**
     * Accept friend requests. To save the queries, the exact same record is replicated in the UI screen.
     * On saving the object, it will update the pre-existing the record.
     *
     * @param friend the friend object.
     * @return the string
     */
    @RequestMapping("/accept")
    public String acceptRequests(@ModelAttribute Friend friend) {
        friendService.accept(friend);
        notificationService.notify(friend);
        return "redirect:/friend/received";
    }

    /**
     * Decline friend requests. To save the queries, the exact same record is replicated in the UI screen.
     * On saving the object, it will update the pre-existing the record.
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
